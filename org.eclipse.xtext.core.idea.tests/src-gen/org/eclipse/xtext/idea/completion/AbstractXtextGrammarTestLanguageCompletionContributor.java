package org.eclipse.xtext.idea.completion;

import org.eclipse.xtext.common.idea.completion.TerminalsCompletionContributor;
import org.eclipse.xtext.idea.lang.AbstractXtextLanguage;

public class AbstractXtextGrammarTestLanguageCompletionContributor extends TerminalsCompletionContributor {
	public AbstractXtextGrammarTestLanguageCompletionContributor(AbstractXtextLanguage lang) {
		super(lang);
	}
}
