package org.eclipse.xtext.linking.idea.highlighting;

import org.eclipse.xtext.idea.highlighting.AbstractColorSettingsPage;
import org.eclipse.xtext.linking.idea.lang.AbstractIgnoreCaseLinkingTestLanguageLanguage;


public class AbstractIgnoreCaseLinkingTestLanguageBaseColorSettingsPage extends AbstractColorSettingsPage {
	
	public AbstractIgnoreCaseLinkingTestLanguageBaseColorSettingsPage() {
		AbstractIgnoreCaseLinkingTestLanguageLanguage.INSTANCE.injectMembers(this);
	}

	@Override
	public String getDisplayName() {
		return AbstractIgnoreCaseLinkingTestLanguageLanguage.INSTANCE.getDisplayName();
	}
}
