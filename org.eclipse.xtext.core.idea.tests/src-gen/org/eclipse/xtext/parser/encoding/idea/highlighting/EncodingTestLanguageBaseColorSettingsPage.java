package org.eclipse.xtext.parser.encoding.idea.highlighting;

import org.eclipse.xtext.idea.highlighting.AbstractColorSettingsPage;
import org.eclipse.xtext.parser.encoding.idea.lang.EncodingTestLanguageLanguage;


public class EncodingTestLanguageBaseColorSettingsPage extends AbstractColorSettingsPage {
	
	public EncodingTestLanguageBaseColorSettingsPage() {
		EncodingTestLanguageLanguage.INSTANCE.injectMembers(this);
	}

	@Override
	public String getDisplayName() {
		return EncodingTestLanguageLanguage.INSTANCE.getDisplayName();
	}
}
