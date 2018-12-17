package org.eclipse.xtext.generator.grammarAccess.idea.completion;

import org.eclipse.xtext.common.idea.completion.TerminalsCompletionContributor;
import org.eclipse.xtext.idea.lang.AbstractXtextLanguage;

public class AbstractGrammarAccessTestLanguageCompletionContributor extends TerminalsCompletionContributor {
	public AbstractGrammarAccessTestLanguageCompletionContributor(AbstractXtextLanguage lang) {
		super(lang);
	}
}
