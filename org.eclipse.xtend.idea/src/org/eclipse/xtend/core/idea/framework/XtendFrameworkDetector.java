/*******************************************************************************
 * Copyright (c) 2015, 2017 itemis AG (http://www.itemis.eu) and others.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 *******************************************************************************/
package org.eclipse.xtend.core.idea.framework;

import com.google.inject.Inject;
import com.intellij.facet.Facet;
import com.intellij.facet.FacetType;
import com.intellij.facet.FacetTypeRegistry;
import com.intellij.framework.addSupport.FrameworkSupportInModuleConfigurable;
import com.intellij.framework.detection.FacetBasedFrameworkDetector;
import com.intellij.framework.detection.FileContentPattern;
import com.intellij.ide.util.frameworkSupport.FrameworkSupportModelImpl;
import com.intellij.ide.util.newProjectWizard.impl.FrameworkSupportModelBase;
import com.intellij.openapi.fileTypes.FileType;
import com.intellij.openapi.module.Module;
import com.intellij.openapi.roots.IdeaModifiableModelsProvider;
import com.intellij.openapi.roots.ModifiableRootModel;
import com.intellij.openapi.roots.ModuleRootManager;
import com.intellij.openapi.roots.ui.configuration.projectRoot.LibrariesContainer;
import com.intellij.openapi.roots.ui.configuration.projectRoot.LibrariesContainerFactory;
import com.intellij.openapi.vfs.VirtualFile;
import com.intellij.patterns.ElementPattern;
import com.intellij.patterns.StandardPatterns;
import com.intellij.util.indexing.FileContent;
import org.eclipse.xtend.core.idea.facet.XtendFacetConfiguration;
import org.eclipse.xtend.core.idea.facet.XtendFacetType;
import org.eclipse.xtend.core.idea.lang.XtendFileType;
import org.eclipse.xtext.idea.util.ProjectLifecycleUtil;

/**
 * @author dhuebner - Initial contribution and API
 */
public class XtendFrameworkDetector extends FacetBasedFrameworkDetector<Facet<XtendFacetConfiguration>, XtendFacetConfiguration> {
	@Inject
	private XtendFrameworkSupportProvider frameworkSupport;
	@Inject
	ProjectLifecycleUtil projectUtil;

	public XtendFrameworkDetector() {
		super("xtend");
		org.eclipse.xtend.core.idea.lang.XtendLanguage.INSTANCE.injectMembers(this);
	}

	@Override
	public void setupFacet(Facet<XtendFacetConfiguration> facet, ModifiableRootModel model) {
		super.setupFacet(facet, model);
		final Module module = model.getModule();
		VirtualFile[] roots = ModuleRootManager.getInstance(module).getContentRoots();
		final VirtualFile baseDir = roots.length > 0 ? roots[0] : module.getProject().getBaseDir();
		final String baseDirectoryForLibraries = baseDir != null ? baseDir.getPath() : "";

		LibrariesContainer librariesContainer = LibrariesContainerFactory.createContainer(module.getProject());
		FrameworkSupportModelBase frameworkModel = new FrameworkSupportModelImpl(module.getProject(), baseDirectoryForLibraries,
				librariesContainer);
		final FrameworkSupportInModuleConfigurable configurable = frameworkSupport.createConfigurable(frameworkModel);
		final IdeaModifiableModelsProvider modelsProvider = new IdeaModifiableModelsProvider();
		if (projectUtil.isProjectReadyForPsiAccess(module.getProject())) {
			configurable.addSupport(module, model, modelsProvider);
		} else {
			projectUtil.executeWritableWhenProjectReady(module.getProject(), new Runnable() {
				@Override
				public void run() {
					final ModifiableRootModel modifiableModel = ModuleRootManager.getInstance(module).getModifiableModel();
					configurable.addSupport(module, modifiableModel, modelsProvider);
					modelsProvider.commitModuleModifiableModel(modifiableModel);
				}
			});
		}
	}

	@Override
	public FacetType<Facet<XtendFacetConfiguration>, XtendFacetConfiguration> getFacetType() {
		return FacetTypeRegistry.getInstance().findFacetType(XtendFacetType.TYPEID);
	}

	@Override
	public FileType getFileType() {
		return XtendFileType.INSTANCE;
	}

	@Override
	public ElementPattern<FileContent> createSuitableFilePattern() {
		return FileContentPattern.fileContent().withName(StandardPatterns.string().endsWith(".xtend"));
	}

}
