package org.eclipse.xtext.parser.antlr.idea.highlighting;

import org.eclipse.xtext.idea.highlighting.AbstractColorSettingsPage;
import org.eclipse.xtext.parser.antlr.idea.lang.Bug378967TestLanguageLanguage;


public class Bug378967TestLanguageBaseColorSettingsPage extends AbstractColorSettingsPage {
	
	public Bug378967TestLanguageBaseColorSettingsPage() {
		Bug378967TestLanguageLanguage.INSTANCE.injectMembers(this);
	}

	@Override
	public String getDisplayName() {
		return Bug378967TestLanguageLanguage.INSTANCE.getDisplayName();
	}
}
