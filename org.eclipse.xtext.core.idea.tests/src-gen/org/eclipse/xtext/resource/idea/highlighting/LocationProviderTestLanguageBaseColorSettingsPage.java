package org.eclipse.xtext.resource.idea.highlighting;

import org.eclipse.xtext.idea.highlighting.AbstractColorSettingsPage;
import org.eclipse.xtext.resource.idea.lang.LocationProviderTestLanguageLanguage;


public class LocationProviderTestLanguageBaseColorSettingsPage extends AbstractColorSettingsPage {
	
	public LocationProviderTestLanguageBaseColorSettingsPage() {
		LocationProviderTestLanguageLanguage.INSTANCE.injectMembers(this);
	}

	@Override
	public String getDisplayName() {
		return LocationProviderTestLanguageLanguage.INSTANCE.getDisplayName();
	}
}
