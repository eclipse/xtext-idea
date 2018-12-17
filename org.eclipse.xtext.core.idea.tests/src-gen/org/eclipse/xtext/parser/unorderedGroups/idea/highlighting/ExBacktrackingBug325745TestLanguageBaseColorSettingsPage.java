package org.eclipse.xtext.parser.unorderedGroups.idea.highlighting;

import org.eclipse.xtext.idea.highlighting.AbstractColorSettingsPage;
import org.eclipse.xtext.parser.unorderedGroups.idea.lang.ExBacktrackingBug325745TestLanguageLanguage;


public class ExBacktrackingBug325745TestLanguageBaseColorSettingsPage extends AbstractColorSettingsPage {
	
	public ExBacktrackingBug325745TestLanguageBaseColorSettingsPage() {
		ExBacktrackingBug325745TestLanguageLanguage.INSTANCE.injectMembers(this);
	}

	@Override
	public String getDisplayName() {
		return ExBacktrackingBug325745TestLanguageLanguage.INSTANCE.getDisplayName();
	}
}
