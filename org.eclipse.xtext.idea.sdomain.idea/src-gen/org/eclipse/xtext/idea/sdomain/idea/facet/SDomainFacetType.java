/*******************************************************************************
 * Copyright (c) 2015 itemis AG (http://www.itemis.eu) and others.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 *******************************************************************************/
package org.eclipse.xtext.idea.sdomain.idea.facet;

import com.intellij.facet.Facet;
import com.intellij.facet.FacetTypeId;
import org.eclipse.xtext.idea.facet.AbstractFacetType;
import org.eclipse.xtext.idea.sdomain.idea.lang.SDomainLanguage;

public class SDomainFacetType extends AbstractFacetType<SDomainFacetConfiguration> {

	public static final FacetTypeId<Facet<SDomainFacetConfiguration>> TYPEID = new FacetTypeId<Facet<SDomainFacetConfiguration>>("org.eclipse.xtext.idea.sdomain.SDomain");

	public SDomainFacetType() {
		super(TYPEID, "org.eclipse.xtext.idea.sdomain.SDomain", "SDomain");
		SDomainLanguage.INSTANCE.injectMembers(this);
	}
}
