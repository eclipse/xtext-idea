/*******************************************************************************
 * Copyright (c) 2015 itemis AG (http://www.itemis.eu) and others.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 *******************************************************************************/
package org.eclipse.xtext.idea.sdomain.formatting2

import com.google.inject.Inject
import org.eclipse.xtext.formatting2.AbstractFormatter2
import org.eclipse.xtext.formatting2.IFormattableDocument
import org.eclipse.xtext.idea.sdomain.sDomain.Element
import org.eclipse.xtext.idea.sdomain.sDomain.File
import org.eclipse.xtext.idea.sdomain.sDomain.Namespace
import org.eclipse.xtext.idea.sdomain.services.SDomainGrammarAccess

class SDomainFormatter extends AbstractFormatter2 {
	
	@Inject extension SDomainGrammarAccess

	def dispatch void format(File file, extension IFormattableDocument document) {
		// TODO: format HiddenRegions around keywords, attributes, cross references, etc. 
		for (Element elements : file.getElements()) {
			elements.format;
		}
	}

	def dispatch void format(Namespace namespace, extension IFormattableDocument document) {
		// TODO: format HiddenRegions around keywords, attributes, cross references, etc. 
		for (Element elements : namespace.getElements()) {
			elements.format;
		}
	}
	
	// TODO: implement for Entity
}
