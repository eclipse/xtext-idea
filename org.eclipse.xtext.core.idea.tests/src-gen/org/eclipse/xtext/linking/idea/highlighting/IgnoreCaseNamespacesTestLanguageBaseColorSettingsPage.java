package org.eclipse.xtext.linking.idea.highlighting;

import org.eclipse.xtext.idea.highlighting.AbstractColorSettingsPage;
import org.eclipse.xtext.linking.idea.lang.IgnoreCaseNamespacesTestLanguageLanguage;


public class IgnoreCaseNamespacesTestLanguageBaseColorSettingsPage extends AbstractColorSettingsPage {
	
	public IgnoreCaseNamespacesTestLanguageBaseColorSettingsPage() {
		IgnoreCaseNamespacesTestLanguageLanguage.INSTANCE.injectMembers(this);
	}

	@Override
	public String getDisplayName() {
		return IgnoreCaseNamespacesTestLanguageLanguage.INSTANCE.getDisplayName();
	}
}
