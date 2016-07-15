/*******************************************************************************
 * Copyright (c) 2015 itemis AG (http://www.itemis.eu) and others.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 *******************************************************************************/
package org.eclipse.xtext.idea.common.types.idea.facet;

import com.intellij.facet.Facet;
import com.intellij.facet.FacetTypeId;
import org.eclipse.xtext.idea.common.types.idea.lang.RefactoringTestLanguageLanguage;
import org.eclipse.xtext.idea.facet.AbstractFacetType;

public class RefactoringTestLanguageFacetType extends AbstractFacetType<RefactoringTestLanguageFacetConfiguration> {

	public static final FacetTypeId<Facet<RefactoringTestLanguageFacetConfiguration>> TYPEID = new FacetTypeId<Facet<RefactoringTestLanguageFacetConfiguration>>("org.eclipse.xtext.idea.common.types.RefactoringTestLanguage");

	public RefactoringTestLanguageFacetType() {
		super(TYPEID, "org.eclipse.xtext.idea.common.types.RefactoringTestLanguage", "RefactoringTestLanguage");
		RefactoringTestLanguageLanguage.INSTANCE.injectMembers(this);
	}
}
