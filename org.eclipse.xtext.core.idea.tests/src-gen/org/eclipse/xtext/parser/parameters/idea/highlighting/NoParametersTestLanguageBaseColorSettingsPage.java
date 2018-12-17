package org.eclipse.xtext.parser.parameters.idea.highlighting;

import org.eclipse.xtext.idea.highlighting.AbstractColorSettingsPage;
import org.eclipse.xtext.parser.parameters.idea.lang.NoParametersTestLanguageLanguage;


public class NoParametersTestLanguageBaseColorSettingsPage extends AbstractColorSettingsPage {
	
	public NoParametersTestLanguageBaseColorSettingsPage() {
		NoParametersTestLanguageLanguage.INSTANCE.injectMembers(this);
	}

	@Override
	public String getDisplayName() {
		return NoParametersTestLanguageLanguage.INSTANCE.getDisplayName();
	}
}
