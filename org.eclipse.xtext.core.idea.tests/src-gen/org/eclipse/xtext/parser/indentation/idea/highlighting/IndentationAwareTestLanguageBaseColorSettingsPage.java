package org.eclipse.xtext.parser.indentation.idea.highlighting;

import org.eclipse.xtext.idea.highlighting.AbstractColorSettingsPage;
import org.eclipse.xtext.parser.indentation.idea.lang.IndentationAwareTestLanguageLanguage;


public class IndentationAwareTestLanguageBaseColorSettingsPage extends AbstractColorSettingsPage {
	
	public IndentationAwareTestLanguageBaseColorSettingsPage() {
		IndentationAwareTestLanguageLanguage.INSTANCE.injectMembers(this);
	}

	@Override
	public String getDisplayName() {
		return IndentationAwareTestLanguageLanguage.INSTANCE.getDisplayName();
	}
}
