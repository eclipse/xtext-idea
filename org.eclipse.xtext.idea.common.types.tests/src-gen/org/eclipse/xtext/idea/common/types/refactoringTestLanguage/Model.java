/**
 * ******************************************************************************
 * Copyright (c) 2015 itemis AG (http://www.itemis.eu) and others.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 *  ******************************************************************************
 */
package org.eclipse.xtext.idea.common.types.refactoringTestLanguage;

import org.eclipse.emf.common.util.EList;
import org.eclipse.emf.ecore.EObject;

/**
 * <!-- begin-user-doc -->
 * A representation of the model object '<em><b>Model</b></em>'.
 * <!-- end-user-doc -->
 *
 * <p>
 * The following features are supported:
 * </p>
 * <ul>
 *   <li>{@link org.eclipse.xtext.idea.common.types.refactoringTestLanguage.Model#getReferenceHolder <em>Reference Holder</em>}</li>
 * </ul>
 *
 * @see org.eclipse.xtext.idea.common.types.refactoringTestLanguage.RefactoringTestLanguagePackage#getModel()
 * @model
 * @generated
 */
public interface Model extends EObject
{
  /**
   * Returns the value of the '<em><b>Reference Holder</b></em>' containment reference list.
   * The list contents are of type {@link org.eclipse.xtext.idea.common.types.refactoringTestLanguage.ReferenceHolder}.
   * <!-- begin-user-doc -->
   * <p>
   * If the meaning of the '<em>Reference Holder</em>' containment reference list isn't clear,
   * there really should be more of a description here...
   * </p>
   * <!-- end-user-doc -->
   * @return the value of the '<em>Reference Holder</em>' containment reference list.
   * @see org.eclipse.xtext.idea.common.types.refactoringTestLanguage.RefactoringTestLanguagePackage#getModel_ReferenceHolder()
   * @model containment="true"
   * @generated
   */
  EList<ReferenceHolder> getReferenceHolder();

} // Model
