package org.eclipse.xtext.serializer.idea.highlighting;

import org.eclipse.xtext.idea.highlighting.AbstractColorSettingsPage;
import org.eclipse.xtext.serializer.idea.lang.SequencerTestLanguageLanguage;


public class SequencerTestLanguageBaseColorSettingsPage extends AbstractColorSettingsPage {
	
	public SequencerTestLanguageBaseColorSettingsPage() {
		SequencerTestLanguageLanguage.INSTANCE.injectMembers(this);
	}

	@Override
	public String getDisplayName() {
		return SequencerTestLanguageLanguage.INSTANCE.getDisplayName();
	}
}
