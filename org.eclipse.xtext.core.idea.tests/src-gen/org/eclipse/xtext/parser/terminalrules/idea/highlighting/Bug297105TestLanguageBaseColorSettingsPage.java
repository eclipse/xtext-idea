package org.eclipse.xtext.parser.terminalrules.idea.highlighting;

import org.eclipse.xtext.idea.highlighting.AbstractColorSettingsPage;
import org.eclipse.xtext.parser.terminalrules.idea.lang.Bug297105TestLanguageLanguage;


public class Bug297105TestLanguageBaseColorSettingsPage extends AbstractColorSettingsPage {
	
	public Bug297105TestLanguageBaseColorSettingsPage() {
		Bug297105TestLanguageLanguage.INSTANCE.injectMembers(this);
	}

	@Override
	public String getDisplayName() {
		return Bug297105TestLanguageLanguage.INSTANCE.getDisplayName();
	}
}
