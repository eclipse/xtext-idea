/**
 * Copyright (c) 2015 itemis AG (http://www.itemis.eu) and others.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 */
package org.eclipse.xtext.psi;

import com.google.inject.Inject;
import com.intellij.openapi.util.Condition;
import com.intellij.psi.PsiElement;
import com.intellij.psi.util.PsiTreeUtil;
import org.eclipse.emf.ecore.EObject;
import org.eclipse.xtext.psi.IPsiModelAssociations;

/**
 * @author Sven Efftinge - Initial contribution and API
 */
@SuppressWarnings("all")
public class XtextPsiExtensions {
  @Inject
  private IPsiModelAssociations associations;
  
  public PsiElement findEObjectAssociatedPsiElement(final PsiElement ctx, final int offset) {
    final PsiElement element = ctx.getContainingFile().findElementAt(offset);
    return this.findEObjectAssociatedPsiElement(element);
  }
  
  public EObject findEObject(final PsiElement ctx, final int offset) {
    return this.associations.getEObject(this.findEObjectAssociatedPsiElement(ctx, offset));
  }
  
  public PsiElement findEObjectAssociatedPsiElement(final PsiElement element) {
    final Condition<PsiElement> _function = (PsiElement it) -> {
      EObject _eObject = this.associations.getEObject(it);
      return (_eObject != null);
    };
    return PsiTreeUtil.findFirstParent(element, false, _function);
  }
  
  public EObject findEObject(final PsiElement ctx) {
    return this.associations.getEObject(this.findEObjectAssociatedPsiElement(ctx));
  }
}
