package org.eclipse.xtext.resource.idea.highlighting;

import org.eclipse.xtext.idea.highlighting.AbstractColorSettingsPage;
import org.eclipse.xtext.resource.idea.lang.EObjectAtOffsetTestLanguageLanguage;


public class EObjectAtOffsetTestLanguageBaseColorSettingsPage extends AbstractColorSettingsPage {
	
	public EObjectAtOffsetTestLanguageBaseColorSettingsPage() {
		EObjectAtOffsetTestLanguageLanguage.INSTANCE.injectMembers(this);
	}

	@Override
	public String getDisplayName() {
		return EObjectAtOffsetTestLanguageLanguage.INSTANCE.getDisplayName();
	}
}
