package org.eclipse.xtext.parser.unorderedGroups.idea.highlighting;

import org.eclipse.xtext.idea.highlighting.AbstractColorSettingsPage;
import org.eclipse.xtext.parser.unorderedGroups.idea.lang.UnorderedGroupsTestLanguageLanguage;


public class UnorderedGroupsTestLanguageBaseColorSettingsPage extends AbstractColorSettingsPage {
	
	public UnorderedGroupsTestLanguageBaseColorSettingsPage() {
		UnorderedGroupsTestLanguageLanguage.INSTANCE.injectMembers(this);
	}

	@Override
	public String getDisplayName() {
		return UnorderedGroupsTestLanguageLanguage.INSTANCE.getDisplayName();
	}
}
