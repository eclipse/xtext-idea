/*******************************************************************************
 * Copyright (c) 2015 itemis AG (http://www.itemis.eu) and others.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 *******************************************************************************/
package org.eclipse.xtext.idea.sdomain.idea.lang;

import org.eclipse.xtext.idea.lang.AbstractXtextLanguage;

public final class SDomainLanguage extends AbstractXtextLanguage {

	public static final SDomainLanguage INSTANCE = new SDomainLanguage();

	private SDomainLanguage() {
		super("org.eclipse.xtext.idea.sdomain.SDomain");
	}

}
