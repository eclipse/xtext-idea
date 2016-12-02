/**
 * Copyright (c) 2015 itemis AG (http://www.itemis.eu) and others.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 */
package org.eclipse.xtext.idea.build;

import com.google.common.collect.Sets;
import com.google.inject.Inject;
import com.intellij.facet.Facet;
import com.intellij.openapi.application.ApplicationManager;
import com.intellij.openapi.module.Module;
import com.intellij.openapi.roots.ModuleRootManager;
import com.intellij.openapi.roots.SourceFolder;
import com.intellij.openapi.util.Computable;
import com.intellij.openapi.util.io.FileUtil;
import com.intellij.openapi.vfs.VirtualFile;
import java.io.File;
import java.util.Set;
import org.eclipse.emf.ecore.resource.Resource;
import org.eclipse.emf.ecore.resource.ResourceSet;
import org.eclipse.xtext.generator.IContextualOutputConfigurationProvider;
import org.eclipse.xtext.generator.IContextualOutputConfigurationProvider2;
import org.eclipse.xtext.generator.IFileSystemAccess;
import org.eclipse.xtext.generator.IOutputConfigurationProvider;
import org.eclipse.xtext.generator.OutputConfiguration;
import org.eclipse.xtext.idea.extensions.RootModelExtensions;
import org.eclipse.xtext.idea.facet.AbstractFacetConfiguration;
import org.eclipse.xtext.idea.facet.FacetProvider;
import org.eclipse.xtext.idea.facet.GeneratorConfigurationState;
import org.eclipse.xtext.resource.XtextResourceSet;
import org.eclipse.xtext.xbase.lib.Conversions;
import org.eclipse.xtext.xbase.lib.IterableExtensions;

/**
 * @author dhuebner - Initial contribution and API
 */
@SuppressWarnings("all")
public class IdeaOutputConfigurationProvider implements IContextualOutputConfigurationProvider, IContextualOutputConfigurationProvider2 {
  @Inject
  private FacetProvider facetProvider;
  
  @Inject
  private IOutputConfigurationProvider defaultOutput;
  
  @Override
  public Set<OutputConfiguration> getOutputConfigurations(final Resource context) {
    return this.getOutputConfigurations(context.getResourceSet());
  }
  
  @Override
  public Set<OutputConfiguration> getOutputConfigurations(final ResourceSet context) {
    final Object module = ((XtextResourceSet) context).getClasspathURIContext();
    if ((module instanceof Module)) {
      return this.getOutputConfigurations(((Module)module));
    }
    return this.defaultOutput.getOutputConfigurations();
  }
  
  public Set<OutputConfiguration> getOutputConfigurations(final Module module) {
    final Facet<? extends AbstractFacetConfiguration> facet = this.facetProvider.getFacet(module);
    if ((facet != null)) {
      final GeneratorConfigurationState generatorConf = facet.getConfiguration().getState();
      final OutputConfiguration defOut = new OutputConfiguration(IFileSystemAccess.DEFAULT_OUTPUT);
      defOut.setOutputDirectory(this.toModuleRelativePath(generatorConf.getOutputDirectory(), module));
      defOut.setCreateOutputDirectory(generatorConf.isCreateDirectory());
      defOut.setCanClearOutputDirectory(generatorConf.isDeleteGenerated());
      defOut.setOverrideExistingResources(generatorConf.isOverwriteExisting());
      defOut.setUseOutputPerSourceFolder(true);
      final Iterable<SourceFolder> allSrcFolders = RootModelExtensions.getExistingSourceFolders(module);
      for (final SourceFolder srcFolder : allSrcFolders) {
        {
          String _relativePath = RootModelExtensions.getRelativePath(srcFolder);
          final OutputConfiguration.SourceMapping mapping = new OutputConfiguration.SourceMapping(_relativePath);
          boolean _isTestSource = srcFolder.isTestSource();
          if (_isTestSource) {
            mapping.setOutputDirectory(this.toModuleRelativePath(generatorConf.getTestOutputDirectory(), module));
          } else {
            mapping.setOutputDirectory(this.toModuleRelativePath(generatorConf.getOutputDirectory(), module));
          }
          defOut.getSourceMappings().add(mapping);
        }
      }
      return Sets.<OutputConfiguration>newHashSet(defOut);
    }
    return this.defaultOutput.getOutputConfigurations();
  }
  
  public String toModuleRelativePath(final String path, final Module module) {
    boolean _isAbsolute = FileUtil.isAbsolute(path);
    if (_isAbsolute) {
      final Computable<String> _function = () -> {
        final VirtualFile root = IterableExtensions.<VirtualFile>head(((Iterable<VirtualFile>)Conversions.doWrapArray(ModuleRootManager.getInstance(module).getContentRoots())));
        final String relativePath = FileUtil.getRelativePath(root.getPath(), path, File.separatorChar);
        return relativePath;
      };
      return ApplicationManager.getApplication().<String>runReadAction(_function);
    }
    return path;
  }
}
