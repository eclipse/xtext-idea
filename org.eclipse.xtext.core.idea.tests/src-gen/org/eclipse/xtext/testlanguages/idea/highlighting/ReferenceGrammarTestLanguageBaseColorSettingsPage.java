package org.eclipse.xtext.testlanguages.idea.highlighting;

import org.eclipse.xtext.idea.highlighting.AbstractColorSettingsPage;
import org.eclipse.xtext.testlanguages.idea.lang.ReferenceGrammarTestLanguageLanguage;


public class ReferenceGrammarTestLanguageBaseColorSettingsPage extends AbstractColorSettingsPage {
	
	public ReferenceGrammarTestLanguageBaseColorSettingsPage() {
		ReferenceGrammarTestLanguageLanguage.INSTANCE.injectMembers(this);
	}

	@Override
	public String getDisplayName() {
		return ReferenceGrammarTestLanguageLanguage.INSTANCE.getDisplayName();
	}
}
