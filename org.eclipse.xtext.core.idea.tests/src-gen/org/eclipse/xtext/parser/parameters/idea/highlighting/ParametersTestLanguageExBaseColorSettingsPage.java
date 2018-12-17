package org.eclipse.xtext.parser.parameters.idea.highlighting;

import org.eclipse.xtext.idea.highlighting.AbstractColorSettingsPage;
import org.eclipse.xtext.parser.parameters.idea.lang.ParametersTestLanguageExLanguage;


public class ParametersTestLanguageExBaseColorSettingsPage extends AbstractColorSettingsPage {
	
	public ParametersTestLanguageExBaseColorSettingsPage() {
		ParametersTestLanguageExLanguage.INSTANCE.injectMembers(this);
	}

	@Override
	public String getDisplayName() {
		return ParametersTestLanguageExLanguage.INSTANCE.getDisplayName();
	}
}
