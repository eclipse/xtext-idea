/**
 * Copyright (c) 2015 itemis AG (http://www.itemis.eu) and others.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 */
package org.eclipse.xtext.idea.tests;

import com.google.common.collect.Lists;
import com.intellij.openapi.fileTypes.FileTypeManager;
import com.intellij.openapi.module.Module;
import com.intellij.openapi.roots.ModifiableRootModel;
import com.intellij.openapi.roots.ModuleRootModificationUtil;
import com.intellij.openapi.roots.OrderRootType;
import com.intellij.openapi.roots.libraries.Library;
import com.intellij.openapi.vfs.LocalFileSystem;
import com.intellij.openapi.vfs.VfsUtil;
import com.intellij.openapi.vfs.newvfs.impl.VfsRootAccess;
import com.intellij.util.Consumer;
import com.intellij.util.PathUtil;
import java.io.File;
import org.eclipse.xtend.lib.annotations.Data;
import org.eclipse.xtend.lib.macro.Active;
import org.eclipse.xtext.xbase.lib.Conversions;
import org.eclipse.xtext.xbase.lib.Functions.Function2;
import org.eclipse.xtext.xbase.lib.IterableExtensions;
import org.eclipse.xtext.xbase.lib.util.ToStringBuilder;

@SuppressWarnings("all")
public class LibraryUtil {
  public static void addGuavaLibrary(final Module it) {
    final Consumer<ModifiableRootModel> _function = (ModifiableRootModel it_1) -> {
      LibraryUtil.addGuavaLibrary(it_1);
    };
    ModuleRootModificationUtil.updateModel(it, _function);
  }
  
  public static void addGuavaLibrary(final ModifiableRootModel it) {
    LibraryUtil.addLibrary(it, "com.google.guava", Lists.class);
  }
  
  public static void addXbaseLibrary(final Module it) {
    final Consumer<ModifiableRootModel> _function = (ModifiableRootModel it_1) -> {
      LibraryUtil.addXbaseLibrary(it_1);
    };
    ModuleRootModificationUtil.updateModel(it, _function);
  }
  
  public static void addXbaseLibrary(final ModifiableRootModel it) {
    LibraryUtil.removeLibFromIgnoredFilesList();
    LibraryUtil.addGuavaLibrary(it);
    LibraryUtil.addLibrary(it, "org.eclipse.xtext.xbase.lib", ToStringBuilder.class);
  }
  
  public static void addXtendLibrary(final Module it) {
    final Consumer<ModifiableRootModel> _function = (ModifiableRootModel it_1) -> {
      LibraryUtil.addXtendLibrary(it_1);
    };
    ModuleRootModificationUtil.updateModel(it, _function);
  }
  
  public static void addXtendLibrary(final ModifiableRootModel it) {
    LibraryUtil.removeLibFromIgnoredFilesList();
    LibraryUtil.addXbaseLibrary(it);
    LibraryUtil.addLibrary(it, "org.eclipse.xtend.lib.macro", Active.class);
    LibraryUtil.addLibrary(it, "org.eclipse.xtend.lib", Data.class);
  }
  
  public static void removeLibFromIgnoredFilesList() {
    final FileTypeManager fileTypeManager = FileTypeManager.getInstance();
    final String[] ignoredFilesList = fileTypeManager.getIgnoredFilesList().split("\\*\\.lib;");
    int _length = ignoredFilesList.length;
    boolean _greaterThan = (_length > 1);
    if (_greaterThan) {
      final Function2<String, String, String> _function = (String p1, String p2) -> {
        return (p1 + p2);
      };
      fileTypeManager.setIgnoredFilesList(IterableExtensions.<String>reduce(((Iterable<? extends String>)Conversions.doWrapArray(ignoredFilesList)), _function));
    }
  }
  
  public static void addLibrary(final Module it, final String libName, final Class<?> clazz) {
    final Consumer<ModifiableRootModel> _function = (ModifiableRootModel it_1) -> {
      LibraryUtil.addLibrary(it_1, libName, clazz);
    };
    ModuleRootModificationUtil.updateModel(it, _function);
  }
  
  public static void addLibrary(final ModifiableRootModel it, final String libName, final Class<?> clazz) {
    final Library.ModifiableModel libraryModel = it.getModuleLibraryTable().createLibrary(libName).getModifiableModel();
    libraryModel.addRoot(LibraryUtil.getUrlForLibraryRoot(clazz), OrderRootType.CLASSES);
    libraryModel.commit();
  }
  
  public static String getUrlForLibraryRoot(final Class<?> clazz) {
    String _xblockexpression = null;
    {
      final String path = PathUtil.getJarPathForClass(clazz);
      VfsRootAccess.allowRootAccess(path);
      final File libraryRoot = new File(path);
      LocalFileSystem.getInstance().refreshAndFindFileByIoFile(libraryRoot);
      _xblockexpression = VfsUtil.getUrlForLibraryRoot(libraryRoot);
    }
    return _xblockexpression;
  }
}
