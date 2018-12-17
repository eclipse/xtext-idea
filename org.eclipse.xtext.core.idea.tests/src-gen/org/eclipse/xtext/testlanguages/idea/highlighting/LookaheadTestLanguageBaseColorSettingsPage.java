package org.eclipse.xtext.testlanguages.idea.highlighting;

import org.eclipse.xtext.idea.highlighting.AbstractColorSettingsPage;
import org.eclipse.xtext.testlanguages.idea.lang.LookaheadTestLanguageLanguage;


public class LookaheadTestLanguageBaseColorSettingsPage extends AbstractColorSettingsPage {
	
	public LookaheadTestLanguageBaseColorSettingsPage() {
		LookaheadTestLanguageLanguage.INSTANCE.injectMembers(this);
	}

	@Override
	public String getDisplayName() {
		return LookaheadTestLanguageLanguage.INSTANCE.getDisplayName();
	}
}
