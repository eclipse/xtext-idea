package org.eclipse.xtext.validation.idea.highlighting;

import org.eclipse.xtext.idea.highlighting.AbstractColorSettingsPage;
import org.eclipse.xtext.validation.idea.lang.ConcreteSyntaxValidationTestLanguageLanguage;


public class ConcreteSyntaxValidationTestLanguageBaseColorSettingsPage extends AbstractColorSettingsPage {
	
	public ConcreteSyntaxValidationTestLanguageBaseColorSettingsPage() {
		ConcreteSyntaxValidationTestLanguageLanguage.INSTANCE.injectMembers(this);
	}

	@Override
	public String getDisplayName() {
		return ConcreteSyntaxValidationTestLanguageLanguage.INSTANCE.getDisplayName();
	}
}
