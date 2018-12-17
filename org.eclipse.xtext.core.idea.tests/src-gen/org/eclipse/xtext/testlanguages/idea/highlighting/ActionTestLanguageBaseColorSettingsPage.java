package org.eclipse.xtext.testlanguages.idea.highlighting;

import org.eclipse.xtext.idea.highlighting.AbstractColorSettingsPage;
import org.eclipse.xtext.testlanguages.idea.lang.ActionTestLanguageLanguage;


public class ActionTestLanguageBaseColorSettingsPage extends AbstractColorSettingsPage {
	
	public ActionTestLanguageBaseColorSettingsPage() {
		ActionTestLanguageLanguage.INSTANCE.injectMembers(this);
	}

	@Override
	public String getDisplayName() {
		return ActionTestLanguageLanguage.INSTANCE.getDisplayName();
	}
}
