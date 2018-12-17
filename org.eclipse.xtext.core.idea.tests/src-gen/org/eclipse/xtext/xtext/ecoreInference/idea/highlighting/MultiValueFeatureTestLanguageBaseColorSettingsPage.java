package org.eclipse.xtext.xtext.ecoreInference.idea.highlighting;

import org.eclipse.xtext.idea.highlighting.AbstractColorSettingsPage;
import org.eclipse.xtext.xtext.ecoreInference.idea.lang.MultiValueFeatureTestLanguageLanguage;


public class MultiValueFeatureTestLanguageBaseColorSettingsPage extends AbstractColorSettingsPage {
	
	public MultiValueFeatureTestLanguageBaseColorSettingsPage() {
		MultiValueFeatureTestLanguageLanguage.INSTANCE.injectMembers(this);
	}

	@Override
	public String getDisplayName() {
		return MultiValueFeatureTestLanguageLanguage.INSTANCE.getDisplayName();
	}
}
