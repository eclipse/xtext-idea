package org.eclipse.xtext.parser.fragments.idea.highlighting;

import org.eclipse.xtext.idea.highlighting.AbstractColorSettingsPage;
import org.eclipse.xtext.parser.fragments.idea.lang.FragmentTestLanguageLanguage;


public class FragmentTestLanguageBaseColorSettingsPage extends AbstractColorSettingsPage {
	
	public FragmentTestLanguageBaseColorSettingsPage() {
		FragmentTestLanguageLanguage.INSTANCE.injectMembers(this);
	}

	@Override
	public String getDisplayName() {
		return FragmentTestLanguageLanguage.INSTANCE.getDisplayName();
	}
}
