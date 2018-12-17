package org.eclipse.xtext.testlanguages.idea.highlighting;

import org.eclipse.xtext.idea.highlighting.AbstractColorSettingsPage;
import org.eclipse.xtext.testlanguages.idea.lang.SimpleExpressionsTestLanguageLanguage;


public class SimpleExpressionsTestLanguageBaseColorSettingsPage extends AbstractColorSettingsPage {
	
	public SimpleExpressionsTestLanguageBaseColorSettingsPage() {
		SimpleExpressionsTestLanguageLanguage.INSTANCE.injectMembers(this);
	}

	@Override
	public String getDisplayName() {
		return SimpleExpressionsTestLanguageLanguage.INSTANCE.getDisplayName();
	}
}
