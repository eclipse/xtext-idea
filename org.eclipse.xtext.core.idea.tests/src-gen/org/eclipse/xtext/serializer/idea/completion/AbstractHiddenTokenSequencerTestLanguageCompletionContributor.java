package org.eclipse.xtext.serializer.idea.completion;

import org.eclipse.xtext.common.idea.completion.TerminalsCompletionContributor;
import org.eclipse.xtext.idea.lang.AbstractXtextLanguage;

public class AbstractHiddenTokenSequencerTestLanguageCompletionContributor extends TerminalsCompletionContributor {
	public AbstractHiddenTokenSequencerTestLanguageCompletionContributor(AbstractXtextLanguage lang) {
		super(lang);
	}
}
