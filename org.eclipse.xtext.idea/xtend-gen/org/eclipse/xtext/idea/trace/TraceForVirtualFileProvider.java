/**
 * Copyright (c) 2015 itemis AG (http://www.itemis.eu) and others.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 */
package org.eclipse.xtext.idea.trace;

import com.google.inject.Inject;
import com.google.inject.Provider;
import com.intellij.openapi.application.ApplicationManager;
import com.intellij.openapi.module.Module;
import com.intellij.openapi.project.Project;
import com.intellij.openapi.roots.ModuleRootManager;
import com.intellij.openapi.util.Computable;
import com.intellij.openapi.util.TextRange;
import com.intellij.openapi.vfs.JarFileSystem;
import com.intellij.openapi.vfs.VfsUtilCore;
import com.intellij.openapi.vfs.VirtualFile;
import com.intellij.openapi.vfs.VirtualFileManager;
import com.intellij.psi.PsiElement;
import com.intellij.psi.PsiFile;
import com.intellij.psi.PsiManager;
import java.io.ByteArrayInputStream;
import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.util.List;
import java.util.Set;
import org.eclipse.emf.common.util.URI;
import org.eclipse.xtend.lib.annotations.FinalFieldsConstructor;
import org.eclipse.xtext.generator.OutputConfiguration;
import org.eclipse.xtext.generator.trace.AbsoluteURI;
import org.eclipse.xtext.generator.trace.ITraceURIConverter;
import org.eclipse.xtext.generator.trace.SourceRelativeURI;
import org.eclipse.xtext.generator.trace.internal.AbstractTraceForURIProvider;
import org.eclipse.xtext.idea.build.IdeaOutputConfigurationProvider;
import org.eclipse.xtext.idea.build.XtextAutoBuilderComponent;
import org.eclipse.xtext.idea.filesystem.IdeaProjectConfig;
import org.eclipse.xtext.idea.filesystem.IdeaProjectConfigProvider;
import org.eclipse.xtext.idea.resource.VirtualFileURIUtil;
import org.eclipse.xtext.idea.trace.IIdeaTrace;
import org.eclipse.xtext.idea.trace.ILocationInVirtualFile;
import org.eclipse.xtext.idea.trace.ITraceForVirtualFileProvider;
import org.eclipse.xtext.idea.trace.VirtualFileBasedTrace;
import org.eclipse.xtext.idea.trace.VirtualFileInProject;
import org.eclipse.xtext.util.ITextRegionWithLineInformation;
import org.eclipse.xtext.util.TextRegion;
import org.eclipse.xtext.workspace.IProjectConfig;
import org.eclipse.xtext.workspace.ISourceFolder;
import org.eclipse.xtext.xbase.lib.CollectionLiterals;
import org.eclipse.xtext.xbase.lib.Exceptions;
import org.eclipse.xtext.xbase.lib.Functions.Function1;
import org.eclipse.xtext.xbase.lib.IterableExtensions;
import org.eclipse.xtext.xbase.lib.StringExtensions;

/**
 * @author Sebastian Zarnekow - Initial contribution and API
 */
@SuppressWarnings("all")
public class TraceForVirtualFileProvider extends AbstractTraceForURIProvider<VirtualFileInProject, VirtualFileBasedTrace> implements ITraceForVirtualFileProvider {
  @FinalFieldsConstructor
  protected static class VirtualFilePersistedTrace implements AbstractTraceForURIProvider.PersistedTrace {
    private final VirtualFile file;
    
    private final TraceForVirtualFileProvider traceProvider;
    
    @Override
    public AbsoluteURI getPath() {
      return this.traceProvider.getAbsoluteLocation(this.file);
    }
    
    @Override
    public long getTimestamp() {
      return this.file.getModificationStamp();
    }
    
    @Override
    public InputStream openStream() throws IOException {
      final Computable<InputStream> _function = () -> {
        try {
          byte[] _contentsToByteArray = this.file.contentsToByteArray();
          return new ByteArrayInputStream(_contentsToByteArray);
        } catch (Throwable _e) {
          throw Exceptions.sneakyThrow(_e);
        }
      };
      return ApplicationManager.getApplication().<InputStream>runReadAction(_function);
    }
    
    @Override
    public boolean exists() {
      return ((this.file != null) && this.file.exists());
    }
    
    public VirtualFilePersistedTrace(final VirtualFile file, final TraceForVirtualFileProvider traceProvider) {
      super();
      this.file = file;
      this.traceProvider = traceProvider;
    }
  }
  
  @Inject
  private Provider<VirtualFileBasedTrace> traceProvider;
  
  @Override
  public List<? extends PsiElement> getGeneratedElements(final PsiElement element) {
    final VirtualFileInProject fileInProject = VirtualFileInProject.forPsiElement(element);
    if ((fileInProject == null)) {
      return CollectionLiterals.<PsiElement>emptyList();
    }
    final IIdeaTrace traceToTarget = this.getTraceToTarget(fileInProject);
    return this.getTracedElements(traceToTarget, element);
  }
  
  private List<PsiElement> getTracedElements(final IIdeaTrace trace, final PsiElement element) {
    if ((trace == null)) {
      return CollectionLiterals.<PsiElement>emptyList();
    }
    final TextRange range = element.getTextRange();
    final int offset = element.getTextOffset();
    int _endOffset = range.getEndOffset();
    int _minus = (_endOffset - offset);
    final TextRegion region = new TextRegion(offset, _minus);
    final Iterable<? extends ILocationInVirtualFile> targetLocations = trace.getAllAssociatedLocations(region);
    final PsiManager mngr = element.getManager();
    final Function1<ILocationInVirtualFile, Boolean> _function = (ILocationInVirtualFile it) -> {
      int _length = it.getTextRegion().getLength();
      return Boolean.valueOf((_length > 0));
    };
    final Function1<ILocationInVirtualFile, PsiElement> _function_1 = (ILocationInVirtualFile it) -> {
      PsiElement _xblockexpression = null;
      {
        final VirtualFileInProject platformResource = it.getPlatformResource();
        if ((platformResource == null)) {
          return null;
        }
        final VirtualFile targetFile = platformResource.getFile();
        final ITextRegionWithLineInformation textRegion = it.getTextRegion();
        PsiFile _findFile = mngr.findFile(targetFile);
        PsiElement _findElementAt = null;
        if (_findFile!=null) {
          _findElementAt=_findFile.findElementAt(textRegion.getOffset());
        }
        PsiElement result = _findElementAt;
        if (((result == null) || (result.getTextLength() == 0))) {
          return null;
        }
        while ((!result.getTextRange().containsRange(textRegion.getOffset(), (textRegion.getOffset() + textRegion.getLength())))) {
          result = result.getParent();
        }
        int _textLength = result.getTextLength();
        boolean _equals = (_textLength == 0);
        if (_equals) {
          return null;
        }
        _xblockexpression = result;
      }
      return _xblockexpression;
    };
    final List<PsiElement> result = IterableExtensions.<PsiElement>toList(IterableExtensions.<PsiElement>toSet(IterableExtensions.<PsiElement>filterNull(IterableExtensions.map(IterableExtensions.filter(targetLocations, _function), _function_1))));
    return result;
  }
  
  @Override
  public List<? extends PsiElement> getOriginalElements(final PsiElement element) {
    final VirtualFileInProject fileInProject = VirtualFileInProject.forPsiElement(element);
    if ((fileInProject == null)) {
      return CollectionLiterals.<PsiElement>emptyList();
    }
    final VirtualFileBasedTrace traceToSource = this.getTraceToSource(fileInProject);
    return this.getTracedElements(traceToSource, element);
  }
  
  @Override
  protected VirtualFileInProject asFile(final AbsoluteURI absoluteURI, final IProjectConfig project) {
    final VirtualFile file = VirtualFileURIUtil.getVirtualFile(absoluteURI.getURI());
    final Module module = ((IdeaProjectConfig) project).getModule();
    final Project ideaProject = module.getProject();
    return new VirtualFileInProject(file, ideaProject);
  }
  
  @Override
  protected List<AbstractTraceForURIProvider.PersistedTrace> findInverseTraceFiles(final VirtualFileInProject sourceFile) {
    final Project ideaProject = sourceFile.getProject();
    final XtextAutoBuilderComponent builder = ideaProject.<XtextAutoBuilderComponent>getComponent(XtextAutoBuilderComponent.class);
    if ((builder == null)) {
      return CollectionLiterals.<AbstractTraceForURIProvider.PersistedTrace>newArrayList();
    }
    final Iterable<URI> generatedSources = builder.getGeneratedSources(VirtualFileURIUtil.getURI(sourceFile.getFile()));
    final Function1<URI, VirtualFile> _function = (URI it) -> {
      return VirtualFileURIUtil.getVirtualFile(it);
    };
    final Function1<VirtualFile, Boolean> _function_1 = (VirtualFile it) -> {
      return Boolean.valueOf(this.isTraceFile(it));
    };
    final Iterable<VirtualFile> generatedTraces = IterableExtensions.<VirtualFile>filter(IterableExtensions.<VirtualFile>filterNull(IterableExtensions.<URI, VirtualFile>map(generatedSources, _function)), _function_1);
    final Function1<VirtualFile, AbstractTraceForURIProvider.PersistedTrace> _function_2 = (VirtualFile it) -> {
      TraceForVirtualFileProvider.VirtualFilePersistedTrace _virtualFilePersistedTrace = new TraceForVirtualFileProvider.VirtualFilePersistedTrace(it, this);
      return ((AbstractTraceForURIProvider.PersistedTrace) _virtualFilePersistedTrace);
    };
    final List<AbstractTraceForURIProvider.PersistedTrace> result = IterableExtensions.<AbstractTraceForURIProvider.PersistedTrace>toList(IterableExtensions.<VirtualFile, AbstractTraceForURIProvider.PersistedTrace>map(generatedTraces, _function_2));
    return result;
  }
  
  @Override
  public SourceRelativeURI getGeneratedUriForTrace(final IProjectConfig projectConfig, final AbsoluteURI absoluteSourceResource, final AbsoluteURI generatedFileURI, final ITraceURIConverter traceURIConverter) {
    final Module module = ((IdeaProjectConfig) projectConfig).getModule();
    final IdeaOutputConfigurationProvider outputConfigurationProvider = this.getServiceProvider(absoluteSourceResource).<IdeaOutputConfigurationProvider>get(IdeaOutputConfigurationProvider.class);
    final Set<OutputConfiguration> outputConfigurations = outputConfigurationProvider.getOutputConfigurations(module);
    final ISourceFolder sourceFolder = projectConfig.findSourceFolderContaining(absoluteSourceResource.getURI());
    if ((sourceFolder == null)) {
      return null;
    }
    final String outputFolder = IterableExtensions.<OutputConfiguration>head(outputConfigurations).getOutputDirectory(sourceFolder.getName());
    final Computable<VirtualFile> _function = () -> {
      boolean _isAbsolutePath = this.isAbsolutePath(outputFolder);
      if (_isAbsolutePath) {
        return VirtualFileManager.getInstance().findFileByUrl(VfsUtilCore.pathToUrl(outputFolder));
      } else {
        VirtualFile[] _contentRoots = ModuleRootManager.getInstance(module).getContentRoots();
        for (final VirtualFile contentRoot : _contentRoots) {
          {
            final VirtualFile result = contentRoot.findFileByRelativePath(outputFolder);
            if ((result != null)) {
              return result;
            }
          }
        }
        return null;
      }
    };
    final VirtualFile outputSourceFolder = ApplicationManager.getApplication().<VirtualFile>runReadAction(_function);
    if (((outputSourceFolder == null) || (!outputSourceFolder.exists()))) {
      final SourceRelativeURI result = super.getGeneratedUriForTrace(projectConfig, absoluteSourceResource, generatedFileURI, traceURIConverter);
      return result;
    }
    final URI sourceFolderURI = VirtualFileURIUtil.getURI(outputSourceFolder);
    final SourceRelativeURI result_1 = generatedFileURI.deresolve(sourceFolderURI);
    return result_1;
  }
  
  private boolean isAbsolutePath(final String path) {
    boolean _isNullOrEmpty = StringExtensions.isNullOrEmpty(path);
    if (_isNullOrEmpty) {
      return false;
    }
    final char start = path.charAt(0);
    return (start == File.pathSeparatorChar);
  }
  
  private boolean isTraceFile(final VirtualFile file) {
    return this.getTraceFileNameProvider().isTraceFileName(file.getName());
  }
  
  @Override
  public IIdeaTrace getTraceToTarget(final VirtualFileInProject sourceResource) {
    return this.getTraceToTarget(sourceResource, this.getAbsoluteLocation(sourceResource), this.getProjectConfig(sourceResource));
  }
  
  @Override
  protected AbstractTraceForURIProvider.PersistedTrace findPersistedTrace(final VirtualFileInProject generatedFile) {
    final VirtualFile virtualFile = generatedFile.getFile();
    final VirtualFile traceFile = this.getTraceFile(virtualFile);
    return new TraceForVirtualFileProvider.VirtualFilePersistedTrace(traceFile, this);
  }
  
  @Override
  protected AbsoluteURI getAbsoluteLocation(final VirtualFileInProject file) {
    return this.getAbsoluteLocation(file.getFile());
  }
  
  protected AbsoluteURI getAbsoluteLocation(final VirtualFile file) {
    URI _uRI = VirtualFileURIUtil.getURI(file);
    return new AbsoluteURI(_uRI);
  }
  
  @Override
  protected IdeaProjectConfig getProjectConfig(final VirtualFileInProject sourceFile) {
    final Module module = sourceFile.getModule();
    if ((module == null)) {
      return null;
    } else {
      return new IdeaProjectConfig(module);
    }
  }
  
  @Override
  public boolean isGenerated(final VirtualFileInProject file) {
    return this.findPersistedTrace(file).exists();
  }
  
  @Override
  public VirtualFileBasedTrace getTraceToTarget(final VirtualFileInProject sourceFile, final AbsoluteURI absoluteSourceResource, final IProjectConfig projectConfig) {
    final VirtualFileBasedTrace result = super.getTraceToTarget(sourceFile, absoluteSourceResource, projectConfig);
    if ((result != null)) {
      final IdeaOutputConfigurationProvider outputConfigurationProvider = this.getServiceProvider(absoluteSourceResource).<IdeaOutputConfigurationProvider>get(IdeaOutputConfigurationProvider.class);
      final IdeaProjectConfigProvider projectConfigProvider = this.getServiceProvider(absoluteSourceResource).<IdeaProjectConfigProvider>get(IdeaProjectConfigProvider.class);
      result.setOutputConfigurationProvider(outputConfigurationProvider);
      result.setProjectConfigProvider(projectConfigProvider);
    }
    return result;
  }
  
  @Override
  protected VirtualFileBasedTrace newAbstractTrace(final VirtualFileInProject file) {
    final VirtualFileBasedTrace result = this.traceProvider.get();
    result.setLocalStorage(file);
    final VirtualFile jarRoot = JarFileSystem.getInstance().getRootByEntry(file.getFile());
    if ((jarRoot != null)) {
      result.setJarRoot(jarRoot);
    } else {
      result.setLocalProjectConfig(this.getProjectConfig(file));
    }
    return result;
  }
  
  protected VirtualFile getTraceFile(final VirtualFile generatedFile) {
    final VirtualFile parent = generatedFile.getParent();
    if ((parent == null)) {
      return null;
    }
    final VirtualFile result = parent.findChild(this.getTraceFileNameProvider().getTraceFromJava(generatedFile.getName()));
    return result;
  }
}
