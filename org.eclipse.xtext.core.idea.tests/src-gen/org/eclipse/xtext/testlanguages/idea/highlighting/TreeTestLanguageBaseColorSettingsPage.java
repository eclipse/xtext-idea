package org.eclipse.xtext.testlanguages.idea.highlighting;

import org.eclipse.xtext.idea.highlighting.AbstractColorSettingsPage;
import org.eclipse.xtext.testlanguages.idea.lang.TreeTestLanguageLanguage;


public class TreeTestLanguageBaseColorSettingsPage extends AbstractColorSettingsPage {
	
	public TreeTestLanguageBaseColorSettingsPage() {
		TreeTestLanguageLanguage.INSTANCE.injectMembers(this);
	}

	@Override
	public String getDisplayName() {
		return TreeTestLanguageLanguage.INSTANCE.getDisplayName();
	}
}
