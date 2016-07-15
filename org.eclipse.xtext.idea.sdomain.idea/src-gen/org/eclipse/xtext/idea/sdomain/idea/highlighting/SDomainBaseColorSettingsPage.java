/*******************************************************************************
 * Copyright (c) 2015 itemis AG (http://www.itemis.eu) and others.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 *******************************************************************************/
package org.eclipse.xtext.idea.sdomain.idea.highlighting;

import org.eclipse.xtext.idea.highlighting.AbstractColorSettingsPage;
import org.eclipse.xtext.idea.sdomain.idea.lang.SDomainLanguage;

public class SDomainBaseColorSettingsPage extends AbstractColorSettingsPage {
	
	public SDomainBaseColorSettingsPage() {
		SDomainLanguage.INSTANCE.injectMembers(this);
	}

	@Override
	public String getDisplayName() {
		return SDomainLanguage.INSTANCE.getDisplayName();
	}
}
