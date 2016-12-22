/**
 * Copyright (c) 2015 itemis AG (http://www.itemis.eu) and others.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 */
package org.eclipse.xtext.idea.extensions;

import com.google.common.base.Objects;
import com.google.common.collect.Iterables;
import com.intellij.openapi.module.Module;
import com.intellij.openapi.roots.ContentEntry;
import com.intellij.openapi.roots.ModuleRootManager;
import com.intellij.openapi.roots.SourceFolder;
import com.intellij.openapi.vfs.VfsUtil;
import com.intellij.openapi.vfs.VirtualFile;
import java.util.List;
import org.eclipse.xtext.xbase.lib.Conversions;
import org.eclipse.xtext.xbase.lib.Functions.Function1;
import org.eclipse.xtext.xbase.lib.IterableExtensions;
import org.eclipse.xtext.xbase.lib.ListExtensions;

@SuppressWarnings("all")
public class RootModelExtensions {
  public static Iterable<SourceFolder> getSourceFolders(final Module module) {
    final Function1<ContentEntry, List<SourceFolder>> _function = (ContentEntry it) -> {
      return IterableExtensions.<SourceFolder>toList(((Iterable<SourceFolder>)Conversions.doWrapArray(it.getSourceFolders())));
    };
    return Iterables.<SourceFolder>concat(ListExtensions.<ContentEntry, List<SourceFolder>>map(((List<ContentEntry>)Conversions.doWrapArray(ModuleRootManager.getInstance(module).getContentEntries())), _function));
  }
  
  public static Iterable<SourceFolder> getExistingSourceFolders(final Module module) {
    final Function1<SourceFolder, Boolean> _function = (SourceFolder it) -> {
      VirtualFile _file = it.getFile();
      return Boolean.valueOf((!Objects.equal(_file, null)));
    };
    return IterableExtensions.<SourceFolder>filter(RootModelExtensions.getSourceFolders(module), _function);
  }
  
  public static String getRelativePath(final SourceFolder sourceFolder) {
    return VfsUtil.getRelativePath(sourceFolder.getFile(), sourceFolder.getContentEntry().getFile());
  }
}
