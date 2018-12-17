package org.eclipse.xtext.metamodelreferencing.tests.idea.highlighting;

import org.eclipse.xtext.idea.highlighting.AbstractColorSettingsPage;
import org.eclipse.xtext.metamodelreferencing.tests.idea.lang.MultiGenMMTestLanguageLanguage;


public class MultiGenMMTestLanguageBaseColorSettingsPage extends AbstractColorSettingsPage {
	
	public MultiGenMMTestLanguageBaseColorSettingsPage() {
		MultiGenMMTestLanguageLanguage.INSTANCE.injectMembers(this);
	}

	@Override
	public String getDisplayName() {
		return MultiGenMMTestLanguageLanguage.INSTANCE.getDisplayName();
	}
}
