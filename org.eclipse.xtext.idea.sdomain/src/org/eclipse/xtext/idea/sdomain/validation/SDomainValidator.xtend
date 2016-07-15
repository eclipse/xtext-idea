/*******************************************************************************
 * Copyright (c) 2015 itemis AG (http://www.itemis.eu) and others.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 *******************************************************************************/
package org.eclipse.xtext.idea.sdomain.validation

import org.eclipse.xtext.idea.sdomain.sDomain.Entity
import org.eclipse.xtext.idea.sdomain.sDomain.SDomainPackage
import org.eclipse.xtext.validation.Check

//import org.eclipse.xtext.validation.Check

/**
 * Custom validation rules. 
 *
 * See https://www.eclipse.org/Xtext/documentation/303_runtime_concepts.html#validation
 */
class SDomainValidator extends AbstractSDomainValidator {

  public static val INVALID_NAME = 'invalidName'

	@Check
	def checkEntityNameStartsWithCapital(Entity entity) {
		if (!Character.isUpperCase(entity.name.charAt(0))) {
			warning('Name should start with a capital', 
					SDomainPackage.Literals.TYPE__NAME,
					INVALID_NAME)
		}
	}
}
