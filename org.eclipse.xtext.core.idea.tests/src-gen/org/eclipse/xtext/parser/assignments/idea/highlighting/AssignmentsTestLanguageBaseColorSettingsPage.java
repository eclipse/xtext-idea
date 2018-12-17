package org.eclipse.xtext.parser.assignments.idea.highlighting;

import org.eclipse.xtext.idea.highlighting.AbstractColorSettingsPage;
import org.eclipse.xtext.parser.assignments.idea.lang.AssignmentsTestLanguageLanguage;


public class AssignmentsTestLanguageBaseColorSettingsPage extends AbstractColorSettingsPage {
	
	public AssignmentsTestLanguageBaseColorSettingsPage() {
		AssignmentsTestLanguageLanguage.INSTANCE.injectMembers(this);
	}

	@Override
	public String getDisplayName() {
		return AssignmentsTestLanguageLanguage.INSTANCE.getDisplayName();
	}
}
