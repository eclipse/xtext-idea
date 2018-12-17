package org.eclipse.xtext.parser.keywords.idea.completion;

import org.eclipse.xtext.common.idea.completion.TerminalsCompletionContributor;
import org.eclipse.xtext.idea.lang.AbstractXtextLanguage;

public class AbstractKeywordsTestLanguageCompletionContributor extends TerminalsCompletionContributor {
	public AbstractKeywordsTestLanguageCompletionContributor(AbstractXtextLanguage lang) {
		super(lang);
	}
}
