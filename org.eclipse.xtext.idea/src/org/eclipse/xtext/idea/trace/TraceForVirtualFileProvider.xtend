/*******************************************************************************
 * Copyright (c) 2015 itemis AG (http://www.itemis.eu) and others.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 *******************************************************************************/
package org.eclipse.xtext.idea.trace

import com.google.inject.Inject
import com.google.inject.Provider
import com.intellij.openapi.application.ApplicationManager
import com.intellij.openapi.roots.ModuleRootManager
import com.intellij.openapi.vfs.JarFileSystem
import com.intellij.openapi.vfs.VfsUtilCore
import com.intellij.openapi.vfs.VirtualFile
import com.intellij.openapi.vfs.VirtualFileManager
import com.intellij.psi.PsiElement
import java.io.ByteArrayInputStream
import java.io.File
import java.io.IOException
import java.io.InputStream
import org.eclipse.xtend.lib.annotations.FinalFieldsConstructor
import org.eclipse.xtext.generator.trace.AbsoluteURI
import org.eclipse.xtext.generator.trace.ITraceURIConverter
import org.eclipse.xtext.generator.trace.SourceRelativeURI
import org.eclipse.xtext.generator.trace.internal.AbstractTraceForURIProvider
import org.eclipse.xtext.idea.build.IdeaOutputConfigurationProvider
import org.eclipse.xtext.idea.build.XtextAutoBuilderComponent
import org.eclipse.xtext.idea.filesystem.IdeaProjectConfig
import org.eclipse.xtext.idea.filesystem.IdeaProjectConfigProvider
import org.eclipse.xtext.util.TextRegion
import org.eclipse.xtext.workspace.IProjectConfig

import static extension org.eclipse.xtext.idea.resource.VirtualFileURIUtil.*

/**
 * @author Sebastian Zarnekow - Initial contribution and API
 */
class TraceForVirtualFileProvider extends AbstractTraceForURIProvider<VirtualFileInProject, VirtualFileBasedTrace> implements ITraceForVirtualFileProvider {
	
	@FinalFieldsConstructor
	protected static class VirtualFilePersistedTrace implements PersistedTrace {

		val VirtualFile file
		val TraceForVirtualFileProvider traceProvider

		override AbsoluteURI getPath() {
			return traceProvider.getAbsoluteLocation(file);
		}

		override long getTimestamp() {
			// we use modificationStamp here, as it guarantees increments between changes
			return file.modificationStamp
		}

		override InputStream openStream() throws IOException {
			ApplicationManager.application.<InputStream>runReadAction [
				return new ByteArrayInputStream(file.contentsToByteArray)
			]
		}

		override boolean exists() {
			return file !== null && file.exists();
		}
		
	}
	
	@Inject Provider<VirtualFileBasedTrace> traceProvider
	
	override getGeneratedElements(PsiElement element) {
		val fileInProject = VirtualFileInProject.forPsiElement(element)
		if (fileInProject === null) {
			return emptyList
		}
		val traceToTarget = getTraceToTarget(fileInProject)
		return getTracedElements(traceToTarget, element)
	}
	
	private def getTracedElements(IIdeaTrace trace, PsiElement element) {
		if (trace === null) {
			return emptyList
		}
		// element.textRange.offset does include comments; elenent.textOffset doens't.
		val range = element.textRange
		val offset = element.textOffset
		val region = new TextRegion(offset, range.endOffset - offset)
		val targetLocations = trace.getAllAssociatedLocations(region)
		val mngr = element.manager
		val result = targetLocations.filter[textRegion.length > 0].map [ it |
			val platformResource = it.platformResource
			if (platformResource === null)
				return null
			val targetFile = platformResource.file
			val textRegion = it.textRegion
			var result = mngr.findFile(targetFile)?.findElementAt(textRegion.offset)
			if (result === null || result.textLength == 0) {
				return null
			}
			while (!result.textRange.containsRange(textRegion.offset, textRegion.offset + textRegion.length)) {
				result = result.parent
			}
			if (result.textLength == 0) {
				return null
			}
			result
		].filterNull.toSet.toList
		return result
	}
	
	override getOriginalElements(PsiElement element) {
		val fileInProject = VirtualFileInProject.forPsiElement(element)
		if (fileInProject === null)
			return emptyList

		val traceToSource = getTraceToSource(fileInProject)
		return getTracedElements(traceToSource, element)
	}
	
	override protected asFile(AbsoluteURI absoluteURI, IProjectConfig project) {
		val file = absoluteURI.URI.virtualFile
		val module = (project as IdeaProjectConfig).module
		val ideaProject = module.project
		return new VirtualFileInProject(file, ideaProject)
	}
	
	override protected findInverseTraceFiles(VirtualFileInProject sourceFile) {
		//TODO we need a way to find generated files for sources in jars.
		val ideaProject = sourceFile.project
		val builder = ideaProject.getComponent(XtextAutoBuilderComponent)
		if(builder === null) {
			return newArrayList
		}
		val generatedSources = builder.getGeneratedSources(sourceFile.file.URI)
		val generatedTraces = generatedSources.map[virtualFile].filterNull.filter[isTraceFile]
		val result = generatedTraces.map[
			new VirtualFilePersistedTrace(it, this) as PersistedTrace
		].toList
		return result
	}
	
	override SourceRelativeURI getGeneratedUriForTrace(IProjectConfig projectConfig, AbsoluteURI absoluteSourceResource, AbsoluteURI generatedFileURI, ITraceURIConverter traceURIConverter) {
		val module = (projectConfig as IdeaProjectConfig).module
		val outputConfigurationProvider = getServiceProvider(absoluteSourceResource).get(IdeaOutputConfigurationProvider)
		val outputConfigurations = outputConfigurationProvider.getOutputConfigurations(module)
		val sourceFolder = projectConfig.findSourceFolderContaining(absoluteSourceResource.getURI)
		if(sourceFolder === null) {
			return null
		}
		val outputFolder = outputConfigurations.head.getOutputDirectory(sourceFolder.name)
		val outputSourceFolder = ApplicationManager.application.<VirtualFile>runReadAction [ 
			if (outputFolder.isAbsolutePath) {
				return VirtualFileManager.getInstance.findFileByUrl(VfsUtilCore.pathToUrl(outputFolder))
			} else {
				for (contentRoot : ModuleRootManager.getInstance(module).contentRoots) {
					val result = contentRoot.findFileByRelativePath(outputFolder)
					if (result !== null)
						return result
				}
				return null
			}
		]
		if (outputSourceFolder === null || !outputSourceFolder.exists) {
			val result = super.getGeneratedUriForTrace(projectConfig, absoluteSourceResource, generatedFileURI, traceURIConverter)
			return result
		}
		val sourceFolderURI = outputSourceFolder.URI
		val result = generatedFileURI.deresolve(sourceFolderURI)
		return result
	}
	
	def private boolean isAbsolutePath(String path) {
		if (path.isNullOrEmpty)
			return false
		val char start = path.charAt(0);
		return start === File.pathSeparatorChar
	}
	
	def private boolean isTraceFile(VirtualFile file) {
		return getTraceFileNameProvider().isTraceFileName(file.getName())
	}
	
	override getTraceToTarget(VirtualFileInProject sourceResource) {
		return getTraceToTarget(sourceResource, getAbsoluteLocation(sourceResource), getProjectConfig(sourceResource));
	}
	
	override protected findPersistedTrace(VirtualFileInProject generatedFile) {
		val virtualFile = generatedFile.file
		val traceFile = virtualFile.getTraceFile
		return new VirtualFilePersistedTrace(traceFile, this)
	}
	
	override protected getAbsoluteLocation(VirtualFileInProject file) {
		return getAbsoluteLocation(file.file)
	}
	
	def protected getAbsoluteLocation(VirtualFile file) {
		return new AbsoluteURI(file.URI)
	}
	
	override protected IdeaProjectConfig getProjectConfig(VirtualFileInProject sourceFile) {
		val module = sourceFile.module
		if (module === null) {
			return null
		} else {
			return new IdeaProjectConfig(module)
		} 
	}
	
	override isGenerated(VirtualFileInProject file) {
		return findPersistedTrace(file).exists
	}
	
	override getTraceToTarget(VirtualFileInProject sourceFile, AbsoluteURI absoluteSourceResource, IProjectConfig projectConfig) {
		val result = super.getTraceToTarget(sourceFile, absoluteSourceResource, projectConfig)
		if (result !== null) {
			val outputConfigurationProvider = getServiceProvider(absoluteSourceResource).get(IdeaOutputConfigurationProvider)
			val projectConfigProvider = getServiceProvider(absoluteSourceResource).get(IdeaProjectConfigProvider)
			result.outputConfigurationProvider = outputConfigurationProvider
			result.projectConfigProvider = projectConfigProvider 
		}
		return result
	}
	
	override protected newAbstractTrace(VirtualFileInProject file) {
		val result = traceProvider.get();
		result.localStorage = file
		val jarRoot = JarFileSystem.instance.getRootByEntry(file.file)
		if (jarRoot !== null) {
			result.jarRoot = jarRoot			
		} else {
			result.localProjectConfig = file.projectConfig
		}
		return result;
	}
	
	def protected VirtualFile getTraceFile(VirtualFile generatedFile) {
		val parent = generatedFile.parent
		if (parent === null) {
			return null
		}
		val result = parent.findChild(traceFileNameProvider.getTraceFromJava(generatedFile.name))
		return result
	}
	
}