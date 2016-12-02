/*******************************************************************************
 * Copyright (c) 2015 itemis AG (http://www.itemis.eu) and others.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 *******************************************************************************/
package org.eclipse.xtext.psi

import com.google.inject.Inject
import com.google.inject.Singleton
import com.intellij.psi.PsiElement
import org.eclipse.emf.ecore.EObject
import org.eclipse.emf.ecore.resource.Resource
import org.eclipse.xtext.idea.resource.IResourceIdeaServiceProvider
import org.eclipse.xtext.psi.impl.BaseXtextFile
import org.eclipse.xtext.resource.IEObjectDescription
import org.eclipse.xtext.resource.IResourceServiceProvider
import org.eclipse.emf.ecore.util.EcoreUtil

/**
 * @author kosyakov - Initial contribution and API
 */
@Singleton
class GlobalPsiModelAssociations implements IPsiModelAssociations {

	@Inject
	IResourceServiceProvider.Registry resourceServiceProviderRegistry

	override getEObject(PsiElement element) {
		if (element === null) return null

		val containingFile = element.containingFile
		if (containingFile instanceof BaseXtextFile) {
			val resourceURI = containingFile.URI
			val resourceServiceProvider = resourceServiceProviderRegistry.getResourceServiceProvider(resourceURI)
			if (resourceServiceProvider instanceof IResourceIdeaServiceProvider)
				return resourceServiceProvider.psiModelAssociations.getEObject(element)
		}
		return null
	}

	override getPsiElement(EObject object) {
		if (object === null) return null

		val resourceURI = EcoreUtil.getURI(object).trimFragment
		val resourceServiceProvider = resourceServiceProviderRegistry.getResourceServiceProvider(resourceURI)
		if (resourceServiceProvider instanceof IResourceIdeaServiceProvider)
			resourceServiceProvider.psiModelAssociations.getPsiElement(object)
	}

	override getPsiElement(IEObjectDescription objectDescription, Resource context) {
		if (objectDescription === null || context === null) return null
		
		val resourceURI = context.URI
		val resourceServiceProvider = resourceServiceProviderRegistry.getResourceServiceProvider(resourceURI)
		if (resourceServiceProvider instanceof IResourceIdeaServiceProvider)
			resourceServiceProvider.psiModelAssociations.getPsiElement(objectDescription, context)
	}

}