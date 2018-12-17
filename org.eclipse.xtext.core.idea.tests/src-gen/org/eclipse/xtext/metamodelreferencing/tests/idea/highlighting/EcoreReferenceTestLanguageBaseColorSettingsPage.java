package org.eclipse.xtext.metamodelreferencing.tests.idea.highlighting;

import org.eclipse.xtext.idea.highlighting.AbstractColorSettingsPage;
import org.eclipse.xtext.metamodelreferencing.tests.idea.lang.EcoreReferenceTestLanguageLanguage;


public class EcoreReferenceTestLanguageBaseColorSettingsPage extends AbstractColorSettingsPage {
	
	public EcoreReferenceTestLanguageBaseColorSettingsPage() {
		EcoreReferenceTestLanguageLanguage.INSTANCE.injectMembers(this);
	}

	@Override
	public String getDisplayName() {
		return EcoreReferenceTestLanguageLanguage.INSTANCE.getDisplayName();
	}
}
