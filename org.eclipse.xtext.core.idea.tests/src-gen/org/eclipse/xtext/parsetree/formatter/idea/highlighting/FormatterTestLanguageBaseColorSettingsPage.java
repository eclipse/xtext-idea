package org.eclipse.xtext.parsetree.formatter.idea.highlighting;

import org.eclipse.xtext.idea.highlighting.AbstractColorSettingsPage;
import org.eclipse.xtext.parsetree.formatter.idea.lang.FormatterTestLanguageLanguage;


public class FormatterTestLanguageBaseColorSettingsPage extends AbstractColorSettingsPage {
	
	public FormatterTestLanguageBaseColorSettingsPage() {
		FormatterTestLanguageLanguage.INSTANCE.injectMembers(this);
	}

	@Override
	public String getDisplayName() {
		return FormatterTestLanguageLanguage.INSTANCE.getDisplayName();
	}
}
