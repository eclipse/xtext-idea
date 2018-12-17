package org.eclipse.xtext.parser.terminalrules.idea.highlighting;

import org.eclipse.xtext.idea.highlighting.AbstractColorSettingsPage;
import org.eclipse.xtext.parser.terminalrules.idea.lang.TerminalRulesTestLanguageLanguage;


public class TerminalRulesTestLanguageBaseColorSettingsPage extends AbstractColorSettingsPage {
	
	public TerminalRulesTestLanguageBaseColorSettingsPage() {
		TerminalRulesTestLanguageLanguage.INSTANCE.injectMembers(this);
	}

	@Override
	public String getDisplayName() {
		return TerminalRulesTestLanguageLanguage.INSTANCE.getDisplayName();
	}
}
