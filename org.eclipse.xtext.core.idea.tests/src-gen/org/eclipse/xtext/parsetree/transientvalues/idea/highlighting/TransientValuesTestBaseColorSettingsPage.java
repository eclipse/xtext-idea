package org.eclipse.xtext.parsetree.transientvalues.idea.highlighting;

import org.eclipse.xtext.idea.highlighting.AbstractColorSettingsPage;
import org.eclipse.xtext.parsetree.transientvalues.idea.lang.TransientValuesTestLanguage;


public class TransientValuesTestBaseColorSettingsPage extends AbstractColorSettingsPage {
	
	public TransientValuesTestBaseColorSettingsPage() {
		TransientValuesTestLanguage.INSTANCE.injectMembers(this);
	}

	@Override
	public String getDisplayName() {
		return TransientValuesTestLanguage.INSTANCE.getDisplayName();
	}
}
