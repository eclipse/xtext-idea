package org.eclipse.xtext.serializer.idea.highlighting;

import org.eclipse.xtext.idea.highlighting.AbstractColorSettingsPage;
import org.eclipse.xtext.serializer.idea.lang.AssignmentFinderTestLanguageLanguage;


public class AssignmentFinderTestLanguageBaseColorSettingsPage extends AbstractColorSettingsPage {
	
	public AssignmentFinderTestLanguageBaseColorSettingsPage() {
		AssignmentFinderTestLanguageLanguage.INSTANCE.injectMembers(this);
	}

	@Override
	public String getDisplayName() {
		return AssignmentFinderTestLanguageLanguage.INSTANCE.getDisplayName();
	}
}
