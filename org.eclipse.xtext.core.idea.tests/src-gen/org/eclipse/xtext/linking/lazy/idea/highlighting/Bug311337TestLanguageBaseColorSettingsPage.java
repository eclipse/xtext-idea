package org.eclipse.xtext.linking.lazy.idea.highlighting;

import org.eclipse.xtext.idea.highlighting.AbstractColorSettingsPage;
import org.eclipse.xtext.linking.lazy.idea.lang.Bug311337TestLanguageLanguage;


public class Bug311337TestLanguageBaseColorSettingsPage extends AbstractColorSettingsPage {
	
	public Bug311337TestLanguageBaseColorSettingsPage() {
		Bug311337TestLanguageLanguage.INSTANCE.injectMembers(this);
	}

	@Override
	public String getDisplayName() {
		return Bug311337TestLanguageLanguage.INSTANCE.getDisplayName();
	}
}
