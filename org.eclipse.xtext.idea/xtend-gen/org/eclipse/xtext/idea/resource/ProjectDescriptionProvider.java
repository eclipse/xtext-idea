/**
 * Copyright (c) 2015 itemis AG (http://www.itemis.eu) and others.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 */
package org.eclipse.xtext.idea.resource;

import com.intellij.openapi.module.Module;
import com.intellij.openapi.roots.LibraryOrderEntry;
import com.intellij.openapi.roots.ModuleOrderEntry;
import com.intellij.openapi.roots.ModuleRootManager;
import com.intellij.openapi.roots.OrderEntry;
import java.util.ArrayList;
import java.util.List;
import java.util.function.Consumer;
import org.eclipse.xtext.resource.impl.ProjectDescription;
import org.eclipse.xtext.xbase.lib.CollectionLiterals;
import org.eclipse.xtext.xbase.lib.Conversions;
import org.eclipse.xtext.xbase.lib.ObjectExtensions;
import org.eclipse.xtext.xbase.lib.Procedures.Procedure1;

/**
 * @author Sven Efftinge - Initial contribution and API
 */
@SuppressWarnings("all")
public class ProjectDescriptionProvider {
  public ProjectDescription getProjectDescription(final Object module) {
    if ((module instanceof Module)) {
      ProjectDescription _projectDescription = new ProjectDescription();
      final Procedure1<ProjectDescription> _function = (ProjectDescription it) -> {
        it.setName(((Module)module).getName());
        final OrderEntry[] enumerator = ModuleRootManager.getInstance(((Module)module)).getOrderEntries();
        final ArrayList<String> dependencyNames = CollectionLiterals.<String>newArrayList();
        final Consumer<OrderEntry> _function_1 = (OrderEntry it_1) -> {
          boolean _matched = false;
          if (it_1 instanceof LibraryOrderEntry) {
            _matched=true;
            String _libraryName = ((LibraryOrderEntry)it_1).getLibraryName();
            dependencyNames.add(_libraryName);
          }
          if (!_matched) {
            if (it_1 instanceof ModuleOrderEntry) {
              _matched=true;
              String _moduleName = ((ModuleOrderEntry)it_1).getModuleName();
              dependencyNames.add(_moduleName);
            }
          }
        };
        ((List<OrderEntry>)Conversions.doWrapArray(enumerator)).forEach(_function_1);
        it.setDependencies(dependencyNames);
      };
      return ObjectExtensions.<ProjectDescription>operator_doubleArrow(_projectDescription, _function);
    }
    throw new IllegalArgumentException(("Cannot create project description from " + module));
  }
}
