package org.eclipse.xtext.valueconverter.idea.highlighting;

import org.eclipse.xtext.idea.highlighting.AbstractColorSettingsPage;
import org.eclipse.xtext.valueconverter.idea.lang.QualifiedNameTestLanguageLanguage;


public class QualifiedNameTestLanguageBaseColorSettingsPage extends AbstractColorSettingsPage {
	
	public QualifiedNameTestLanguageBaseColorSettingsPage() {
		QualifiedNameTestLanguageLanguage.INSTANCE.injectMembers(this);
	}

	@Override
	public String getDisplayName() {
		return QualifiedNameTestLanguageLanguage.INSTANCE.getDisplayName();
	}
}
