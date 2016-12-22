/**
 * Copyright (c) 2015 itemis AG (http://www.itemis.eu) and others.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 */
package org.eclipse.xtext.idea.common.types;

import com.intellij.openapi.application.ApplicationManager;
import com.intellij.openapi.util.Computable;
import com.intellij.psi.PsiClass;
import org.eclipse.xtext.common.types.JvmDeclaredType;
import org.eclipse.xtext.common.types.access.TypeResource;
import org.eclipse.xtext.common.types.access.impl.AbstractClassMirror;
import org.eclipse.xtext.common.types.access.impl.ITypeFactory;

@SuppressWarnings("all")
public class PsiClassMirror extends AbstractClassMirror {
  private final PsiClass psiClass;
  
  private final ITypeFactory<PsiClass, JvmDeclaredType> typeFactory;
  
  public PsiClassMirror(final PsiClass psiClass, final ITypeFactory<PsiClass, JvmDeclaredType> typeFactory) {
    this.psiClass = psiClass;
    this.typeFactory = typeFactory;
  }
  
  @Override
  protected String getTypeName() {
    final Computable<String> _function = () -> {
      return this.psiClass.getQualifiedName();
    };
    return ApplicationManager.getApplication().<String>runReadAction(_function);
  }
  
  @Override
  public void initialize(final TypeResource typeResource) {
    typeResource.getContents().add(this.typeFactory.createType(this.psiClass));
  }
  
  @Override
  public boolean isSealed() {
    return true;
  }
}
