package org.eclipse.xtext.index.idea.highlighting;

import org.eclipse.xtext.idea.highlighting.AbstractColorSettingsPage;
import org.eclipse.xtext.index.idea.lang.IndexTestLanguageLanguage;


public class IndexTestLanguageBaseColorSettingsPage extends AbstractColorSettingsPage {
	
	public IndexTestLanguageBaseColorSettingsPage() {
		IndexTestLanguageLanguage.INSTANCE.injectMembers(this);
	}

	@Override
	public String getDisplayName() {
		return IndexTestLanguageLanguage.INSTANCE.getDisplayName();
	}
}
