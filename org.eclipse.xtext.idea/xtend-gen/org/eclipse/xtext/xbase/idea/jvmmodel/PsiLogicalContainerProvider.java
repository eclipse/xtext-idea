/**
 * Copyright (c) 2014 itemis AG (http://www.itemis.eu) and others.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 */
package org.eclipse.xtext.xbase.idea.jvmmodel;

import com.google.inject.Inject;
import com.google.inject.Singleton;
import com.intellij.psi.PsiElement;
import org.eclipse.emf.ecore.EObject;
import org.eclipse.xtext.psi.IPsiModelAssociations;
import org.eclipse.xtext.xbase.idea.jvmmodel.IPsiLogicalContainerProvider;
import org.eclipse.xtext.xbase.jvmmodel.ILogicalContainerProvider;
import org.eclipse.xtext.xbase.lib.Extension;

/**
 * @author kosyakov - Initial contribution and API
 */
@Singleton
@SuppressWarnings("all")
public class PsiLogicalContainerProvider implements IPsiLogicalContainerProvider {
  @Inject
  @Extension
  private IPsiModelAssociations _iPsiModelAssociations;
  
  @Inject
  private ILogicalContainerProvider logicalContainerProvider;
  
  @Override
  public PsiElement getNearestLogicalContainer(final PsiElement expr) {
    return this.getNearestLogicalContainer(this._iPsiModelAssociations.getEObject(expr));
  }
  
  @Override
  public PsiElement getNearestLogicalContainer(final EObject expr) {
    return this._iPsiModelAssociations.getPsiElement(this.logicalContainerProvider.getNearestLogicalContainer(expr));
  }
}
