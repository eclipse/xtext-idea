package org.eclipse.xtext.testlanguages.idea.completion;

import org.eclipse.xtext.common.idea.completion.TerminalsCompletionContributor;
import org.eclipse.xtext.idea.lang.AbstractXtextLanguage;

public class AbstractLookaheadTestLanguageCompletionContributor extends TerminalsCompletionContributor {
	public AbstractLookaheadTestLanguageCompletionContributor(AbstractXtextLanguage lang) {
		super(lang);
	}
}
