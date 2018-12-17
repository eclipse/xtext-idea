package org.eclipse.xtext.parser.unorderedGroups.idea.highlighting;

import org.eclipse.xtext.idea.highlighting.AbstractColorSettingsPage;
import org.eclipse.xtext.parser.unorderedGroups.idea.lang.SimpleUnorderedGroupsTestLanguageLanguage;


public class SimpleUnorderedGroupsTestLanguageBaseColorSettingsPage extends AbstractColorSettingsPage {
	
	public SimpleUnorderedGroupsTestLanguageBaseColorSettingsPage() {
		SimpleUnorderedGroupsTestLanguageLanguage.INSTANCE.injectMembers(this);
	}

	@Override
	public String getDisplayName() {
		return SimpleUnorderedGroupsTestLanguageLanguage.INSTANCE.getDisplayName();
	}
}
