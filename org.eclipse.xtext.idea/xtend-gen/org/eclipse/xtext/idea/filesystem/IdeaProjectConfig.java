/**
 * Copyright (c) 2015 itemis AG (http://www.itemis.eu) and others.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 */
package org.eclipse.xtext.idea.filesystem;

import com.google.common.base.Objects;
import com.intellij.ide.projectView.impl.ProjectRootsUtil;
import com.intellij.openapi.module.Module;
import com.intellij.openapi.project.Project;
import com.intellij.openapi.roots.ModuleRootManager;
import com.intellij.openapi.roots.ProjectRootManager;
import com.intellij.openapi.roots.SourceFolder;
import com.intellij.openapi.vfs.VfsUtil;
import com.intellij.openapi.vfs.VirtualFile;
import com.intellij.openapi.vfs.VirtualFileManager;
import java.util.Set;
import org.eclipse.emf.common.util.URI;
import org.eclipse.xtend.lib.annotations.Data;
import org.eclipse.xtext.idea.extensions.RootModelExtensions;
import org.eclipse.xtext.idea.filesystem.IdeaSourceFolder;
import org.eclipse.xtext.idea.filesystem.IdeaWorkspaceConfig;
import org.eclipse.xtext.util.UriUtil;
import org.eclipse.xtext.workspace.IProjectConfig;
import org.eclipse.xtext.workspace.IWorkspaceConfig;
import org.eclipse.xtext.xbase.lib.Conversions;
import org.eclipse.xtext.xbase.lib.Functions.Function1;
import org.eclipse.xtext.xbase.lib.IterableExtensions;
import org.eclipse.xtext.xbase.lib.Pure;
import org.eclipse.xtext.xbase.lib.util.ToStringBuilder;

@Data
@SuppressWarnings("all")
public class IdeaProjectConfig implements IProjectConfig {
  private final Module module;
  
  private final VirtualFile contentRoot;
  
  public IdeaProjectConfig(final Module module) {
    this.module = module;
    this.contentRoot = IterableExtensions.<VirtualFile>head(((Iterable<VirtualFile>)Conversions.doWrapArray(ModuleRootManager.getInstance(module).getContentRoots())));
  }
  
  @Override
  public IdeaSourceFolder findSourceFolderContaining(final URI member) {
    final VirtualFile file = VirtualFileManager.getInstance().findFileByUrl(member.toString());
    boolean _equals = Objects.equal(file, null);
    if (_equals) {
      return null;
    }
    final VirtualFile sourceRoot = ProjectRootManager.getInstance(this.module.getProject()).getFileIndex().getSourceRootForFile(file);
    boolean _equals_1 = Objects.equal(sourceRoot, null);
    if (_equals_1) {
      return null;
    }
    final SourceFolder sourceFolder = ProjectRootsUtil.findSourceFolder(this.module, sourceRoot);
    if ((Objects.equal(sourceFolder, null) || (!Objects.equal(sourceFolder.getContentEntry().getFile(), this.contentRoot)))) {
      return null;
    }
    return new IdeaSourceFolder(sourceFolder);
  }
  
  @Override
  public String getName() {
    return this.module.getName();
  }
  
  @Override
  public URI getPath() {
    return UriUtil.toFolderURI(URI.createURI(this.contentRoot.getUrl()));
  }
  
  @Override
  public Set<? extends IdeaSourceFolder> getSourceFolders() {
    final Function1<SourceFolder, Boolean> _function = (SourceFolder it) -> {
      return Boolean.valueOf((Objects.equal(it.getFile(), this.contentRoot) || VfsUtil.isAncestor(this.contentRoot, it.getFile(), false)));
    };
    final Function1<SourceFolder, IdeaSourceFolder> _function_1 = (SourceFolder sourceFolder) -> {
      return new IdeaSourceFolder(sourceFolder);
    };
    return IterableExtensions.<IdeaSourceFolder>toSet(IterableExtensions.<SourceFolder, IdeaSourceFolder>map(IterableExtensions.<SourceFolder>filter(RootModelExtensions.getExistingSourceFolders(this.module), _function), _function_1));
  }
  
  @Override
  public IWorkspaceConfig getWorkspaceConfig() {
    Project _project = this.module.getProject();
    return new IdeaWorkspaceConfig(_project);
  }
  
  @Override
  @Pure
  public int hashCode() {
    final int prime = 31;
    int result = 1;
    result = prime * result + ((this.module== null) ? 0 : this.module.hashCode());
    result = prime * result + ((this.contentRoot== null) ? 0 : this.contentRoot.hashCode());
    return result;
  }
  
  @Override
  @Pure
  public boolean equals(final Object obj) {
    if (this == obj)
      return true;
    if (obj == null)
      return false;
    if (getClass() != obj.getClass())
      return false;
    IdeaProjectConfig other = (IdeaProjectConfig) obj;
    if (this.module == null) {
      if (other.module != null)
        return false;
    } else if (!this.module.equals(other.module))
      return false;
    if (this.contentRoot == null) {
      if (other.contentRoot != null)
        return false;
    } else if (!this.contentRoot.equals(other.contentRoot))
      return false;
    return true;
  }
  
  @Override
  @Pure
  public String toString() {
    ToStringBuilder b = new ToStringBuilder(this);
    b.add("module", this.module);
    b.add("contentRoot", this.contentRoot);
    return b.toString();
  }
  
  @Pure
  public Module getModule() {
    return this.module;
  }
  
  @Pure
  public VirtualFile getContentRoot() {
    return this.contentRoot;
  }
}
