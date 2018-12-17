package org.eclipse.xtext.parser.fragments.idea.highlighting;

import org.eclipse.xtext.idea.highlighting.AbstractColorSettingsPage;
import org.eclipse.xtext.parser.fragments.idea.lang.FragmentTestLanguageExLanguage;


public class FragmentTestLanguageExBaseColorSettingsPage extends AbstractColorSettingsPage {
	
	public FragmentTestLanguageExBaseColorSettingsPage() {
		FragmentTestLanguageExLanguage.INSTANCE.injectMembers(this);
	}

	@Override
	public String getDisplayName() {
		return FragmentTestLanguageExLanguage.INSTANCE.getDisplayName();
	}
}
