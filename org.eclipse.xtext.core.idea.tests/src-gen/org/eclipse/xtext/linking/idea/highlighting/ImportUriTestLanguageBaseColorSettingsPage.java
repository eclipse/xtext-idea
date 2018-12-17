package org.eclipse.xtext.linking.idea.highlighting;

import org.eclipse.xtext.idea.highlighting.AbstractColorSettingsPage;
import org.eclipse.xtext.linking.idea.lang.ImportUriTestLanguageLanguage;


public class ImportUriTestLanguageBaseColorSettingsPage extends AbstractColorSettingsPage {
	
	public ImportUriTestLanguageBaseColorSettingsPage() {
		ImportUriTestLanguageLanguage.INSTANCE.injectMembers(this);
	}

	@Override
	public String getDisplayName() {
		return ImportUriTestLanguageLanguage.INSTANCE.getDisplayName();
	}
}
