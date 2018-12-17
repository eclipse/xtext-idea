/*******************************************************************************
 * Copyright (c) 2010, 2017 itemis AG (http://www.itemis.eu) and others.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 *******************************************************************************/
package org.eclipse.xtend.core.idea.facet

import com.intellij.openapi.components.PersistentStateComponent
import com.intellij.openapi.components.State
import com.intellij.openapi.components.Storage
import com.intellij.openapi.components.StoragePathMacros
import com.intellij.openapi.components.StorageScheme
import org.eclipse.xtext.xbase.idea.facet.XbaseFacetConfiguration
import org.eclipse.xtext.xbase.idea.facet.XbaseGeneratorConfigurationState

@State(name = "org.eclipse.xtend.core.XtendGenerator", storages = #[
		@Storage("XtendGeneratorConfig.xml")])
class XtendFacetConfiguration extends XbaseFacetConfiguration implements PersistentStateComponent<XbaseGeneratorConfigurationState>{

}
