package org.eclipse.xtext.parser.antlr.idea.completion;

import org.eclipse.xtext.common.idea.completion.TerminalsCompletionContributor;
import org.eclipse.xtext.idea.lang.AbstractXtextLanguage;

public class AbstractBug443221TestLanguageCompletionContributor extends TerminalsCompletionContributor {
	public AbstractBug443221TestLanguageCompletionContributor(AbstractXtextLanguage lang) {
		super(lang);
	}
}
