package org.eclipse.xtext.parsetree.unassignedtext.idea.highlighting;

import org.eclipse.xtext.idea.highlighting.AbstractColorSettingsPage;
import org.eclipse.xtext.parsetree.unassignedtext.idea.lang.UnassignedTextTestLanguageLanguage;


public class UnassignedTextTestLanguageBaseColorSettingsPage extends AbstractColorSettingsPage {
	
	public UnassignedTextTestLanguageBaseColorSettingsPage() {
		UnassignedTextTestLanguageLanguage.INSTANCE.injectMembers(this);
	}

	@Override
	public String getDisplayName() {
		return UnassignedTextTestLanguageLanguage.INSTANCE.getDisplayName();
	}
}
