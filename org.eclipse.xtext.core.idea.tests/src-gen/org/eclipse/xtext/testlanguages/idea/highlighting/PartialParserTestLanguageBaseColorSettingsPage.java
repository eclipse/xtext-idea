package org.eclipse.xtext.testlanguages.idea.highlighting;

import org.eclipse.xtext.idea.highlighting.AbstractColorSettingsPage;
import org.eclipse.xtext.testlanguages.idea.lang.PartialParserTestLanguageLanguage;


public class PartialParserTestLanguageBaseColorSettingsPage extends AbstractColorSettingsPage {
	
	public PartialParserTestLanguageBaseColorSettingsPage() {
		PartialParserTestLanguageLanguage.INSTANCE.injectMembers(this);
	}

	@Override
	public String getDisplayName() {
		return PartialParserTestLanguageLanguage.INSTANCE.getDisplayName();
	}
}
