/**
 * Copyright (c) 2015 itemis AG (http://www.itemis.eu) and others.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 */
package org.eclipse.xtext.idea.common.types.ide;

import com.google.inject.Guice;
import com.google.inject.Injector;
import org.eclipse.xtext.idea.common.types.RefactoringTestLanguageRuntimeModule;
import org.eclipse.xtext.idea.common.types.RefactoringTestLanguageStandaloneSetup;
import org.eclipse.xtext.idea.common.types.ide.RefactoringTestLanguageIdeModule;

/**
 * Initialization support for running Xtext languages without Equinox extension registry.
 */
@SuppressWarnings("all")
public class RefactoringTestLanguageIdeSetup extends RefactoringTestLanguageStandaloneSetup {
  @Override
  public Injector createInjector() {
    RefactoringTestLanguageRuntimeModule _refactoringTestLanguageRuntimeModule = new RefactoringTestLanguageRuntimeModule();
    RefactoringTestLanguageIdeModule _refactoringTestLanguageIdeModule = new RefactoringTestLanguageIdeModule();
    return Guice.createInjector(_refactoringTestLanguageRuntimeModule, _refactoringTestLanguageIdeModule);
  }
}
