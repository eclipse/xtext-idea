package org.eclipse.xtext.testlanguages.idea.highlighting;

import org.eclipse.xtext.idea.highlighting.AbstractColorSettingsPage;
import org.eclipse.xtext.testlanguages.idea.lang.FowlerDslTestLanguageLanguage;


public class FowlerDslTestLanguageBaseColorSettingsPage extends AbstractColorSettingsPage {
	
	public FowlerDslTestLanguageBaseColorSettingsPage() {
		FowlerDslTestLanguageLanguage.INSTANCE.injectMembers(this);
	}

	@Override
	public String getDisplayName() {
		return FowlerDslTestLanguageLanguage.INSTANCE.getDisplayName();
	}
}
