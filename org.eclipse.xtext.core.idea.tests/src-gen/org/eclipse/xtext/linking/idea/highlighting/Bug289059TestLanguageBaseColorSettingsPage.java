package org.eclipse.xtext.linking.idea.highlighting;

import org.eclipse.xtext.idea.highlighting.AbstractColorSettingsPage;
import org.eclipse.xtext.linking.idea.lang.Bug289059TestLanguageLanguage;


public class Bug289059TestLanguageBaseColorSettingsPage extends AbstractColorSettingsPage {
	
	public Bug289059TestLanguageBaseColorSettingsPage() {
		Bug289059TestLanguageLanguage.INSTANCE.injectMembers(this);
	}

	@Override
	public String getDisplayName() {
		return Bug289059TestLanguageLanguage.INSTANCE.getDisplayName();
	}
}
