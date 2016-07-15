/*******************************************************************************
 * Copyright (c) 2015 itemis AG (http://www.itemis.eu) and others.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 *******************************************************************************/
package org.eclipse.xtext.idea.sdomain.ide

import com.google.inject.Guice
import org.eclipse.xtext.idea.sdomain.SDomainRuntimeModule
import org.eclipse.xtext.idea.sdomain.SDomainStandaloneSetup

/**
 * Initialization support for running Xtext languages without Equinox extension registry.
 */
class SDomainIdeSetup extends SDomainStandaloneSetup {

	override createInjector() {
		Guice.createInjector(new SDomainRuntimeModule, new SDomainIdeModule)
	}
}
