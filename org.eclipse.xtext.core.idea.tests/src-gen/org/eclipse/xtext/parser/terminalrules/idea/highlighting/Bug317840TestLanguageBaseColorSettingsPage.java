package org.eclipse.xtext.parser.terminalrules.idea.highlighting;

import org.eclipse.xtext.idea.highlighting.AbstractColorSettingsPage;
import org.eclipse.xtext.parser.terminalrules.idea.lang.Bug317840TestLanguageLanguage;


public class Bug317840TestLanguageBaseColorSettingsPage extends AbstractColorSettingsPage {
	
	public Bug317840TestLanguageBaseColorSettingsPage() {
		Bug317840TestLanguageLanguage.INSTANCE.injectMembers(this);
	}

	@Override
	public String getDisplayName() {
		return Bug317840TestLanguageLanguage.INSTANCE.getDisplayName();
	}
}
