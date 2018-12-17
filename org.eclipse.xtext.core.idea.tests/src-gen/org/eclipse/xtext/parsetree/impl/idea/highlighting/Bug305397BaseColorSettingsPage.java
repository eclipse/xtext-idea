package org.eclipse.xtext.parsetree.impl.idea.highlighting;

import org.eclipse.xtext.idea.highlighting.AbstractColorSettingsPage;
import org.eclipse.xtext.parsetree.impl.idea.lang.Bug305397Language;


public class Bug305397BaseColorSettingsPage extends AbstractColorSettingsPage {
	
	public Bug305397BaseColorSettingsPage() {
		Bug305397Language.INSTANCE.injectMembers(this);
	}

	@Override
	public String getDisplayName() {
		return Bug305397Language.INSTANCE.getDisplayName();
	}
}
