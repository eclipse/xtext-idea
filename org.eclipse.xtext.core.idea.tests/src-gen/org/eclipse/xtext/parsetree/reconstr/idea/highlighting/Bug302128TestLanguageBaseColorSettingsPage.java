package org.eclipse.xtext.parsetree.reconstr.idea.highlighting;

import org.eclipse.xtext.idea.highlighting.AbstractColorSettingsPage;
import org.eclipse.xtext.parsetree.reconstr.idea.lang.Bug302128TestLanguageLanguage;


public class Bug302128TestLanguageBaseColorSettingsPage extends AbstractColorSettingsPage {
	
	public Bug302128TestLanguageBaseColorSettingsPage() {
		Bug302128TestLanguageLanguage.INSTANCE.injectMembers(this);
	}

	@Override
	public String getDisplayName() {
		return Bug302128TestLanguageLanguage.INSTANCE.getDisplayName();
	}
}
