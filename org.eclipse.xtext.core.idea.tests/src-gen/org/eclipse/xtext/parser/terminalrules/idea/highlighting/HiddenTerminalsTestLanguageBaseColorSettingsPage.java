package org.eclipse.xtext.parser.terminalrules.idea.highlighting;

import org.eclipse.xtext.idea.highlighting.AbstractColorSettingsPage;
import org.eclipse.xtext.parser.terminalrules.idea.lang.HiddenTerminalsTestLanguageLanguage;


public class HiddenTerminalsTestLanguageBaseColorSettingsPage extends AbstractColorSettingsPage {
	
	public HiddenTerminalsTestLanguageBaseColorSettingsPage() {
		HiddenTerminalsTestLanguageLanguage.INSTANCE.injectMembers(this);
	}

	@Override
	public String getDisplayName() {
		return HiddenTerminalsTestLanguageLanguage.INSTANCE.getDisplayName();
	}
}
