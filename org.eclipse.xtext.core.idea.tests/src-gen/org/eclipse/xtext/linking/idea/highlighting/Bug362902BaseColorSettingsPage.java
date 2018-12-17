package org.eclipse.xtext.linking.idea.highlighting;

import org.eclipse.xtext.idea.highlighting.AbstractColorSettingsPage;
import org.eclipse.xtext.linking.idea.lang.Bug362902Language;


public class Bug362902BaseColorSettingsPage extends AbstractColorSettingsPage {
	
	public Bug362902BaseColorSettingsPage() {
		Bug362902Language.INSTANCE.injectMembers(this);
	}

	@Override
	public String getDisplayName() {
		return Bug362902Language.INSTANCE.getDisplayName();
	}
}
