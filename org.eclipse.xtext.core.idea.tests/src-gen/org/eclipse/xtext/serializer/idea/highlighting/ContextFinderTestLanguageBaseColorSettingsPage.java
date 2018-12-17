package org.eclipse.xtext.serializer.idea.highlighting;

import org.eclipse.xtext.idea.highlighting.AbstractColorSettingsPage;
import org.eclipse.xtext.serializer.idea.lang.ContextFinderTestLanguageLanguage;


public class ContextFinderTestLanguageBaseColorSettingsPage extends AbstractColorSettingsPage {
	
	public ContextFinderTestLanguageBaseColorSettingsPage() {
		ContextFinderTestLanguageLanguage.INSTANCE.injectMembers(this);
	}

	@Override
	public String getDisplayName() {
		return ContextFinderTestLanguageLanguage.INSTANCE.getDisplayName();
	}
}
