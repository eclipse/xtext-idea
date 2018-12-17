package org.eclipse.xtext.parser.unorderedGroups.idea.highlighting;

import org.eclipse.xtext.idea.highlighting.AbstractColorSettingsPage;
import org.eclipse.xtext.parser.unorderedGroups.idea.lang.ExUnorderedGroupsTestLanguageLanguage;


public class ExUnorderedGroupsTestLanguageBaseColorSettingsPage extends AbstractColorSettingsPage {
	
	public ExUnorderedGroupsTestLanguageBaseColorSettingsPage() {
		ExUnorderedGroupsTestLanguageLanguage.INSTANCE.injectMembers(this);
	}

	@Override
	public String getDisplayName() {
		return ExUnorderedGroupsTestLanguageLanguage.INSTANCE.getDisplayName();
	}
}
