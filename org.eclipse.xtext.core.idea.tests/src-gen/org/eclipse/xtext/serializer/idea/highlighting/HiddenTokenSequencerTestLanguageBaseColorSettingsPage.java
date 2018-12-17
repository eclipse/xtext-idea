package org.eclipse.xtext.serializer.idea.highlighting;

import org.eclipse.xtext.idea.highlighting.AbstractColorSettingsPage;
import org.eclipse.xtext.serializer.idea.lang.HiddenTokenSequencerTestLanguageLanguage;


public class HiddenTokenSequencerTestLanguageBaseColorSettingsPage extends AbstractColorSettingsPage {
	
	public HiddenTokenSequencerTestLanguageBaseColorSettingsPage() {
		HiddenTokenSequencerTestLanguageLanguage.INSTANCE.injectMembers(this);
	}

	@Override
	public String getDisplayName() {
		return HiddenTokenSequencerTestLanguageLanguage.INSTANCE.getDisplayName();
	}
}
