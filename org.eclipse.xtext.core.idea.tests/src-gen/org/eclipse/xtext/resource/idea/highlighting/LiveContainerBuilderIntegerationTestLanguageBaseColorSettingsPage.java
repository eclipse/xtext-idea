package org.eclipse.xtext.resource.idea.highlighting;

import org.eclipse.xtext.idea.highlighting.AbstractColorSettingsPage;
import org.eclipse.xtext.resource.idea.lang.LiveContainerBuilderIntegerationTestLanguageLanguage;


public class LiveContainerBuilderIntegerationTestLanguageBaseColorSettingsPage extends AbstractColorSettingsPage {
	
	public LiveContainerBuilderIntegerationTestLanguageBaseColorSettingsPage() {
		LiveContainerBuilderIntegerationTestLanguageLanguage.INSTANCE.injectMembers(this);
	}

	@Override
	public String getDisplayName() {
		return LiveContainerBuilderIntegerationTestLanguageLanguage.INSTANCE.getDisplayName();
	}
}
