package org.eclipse.xtext.validation.idea.completion;

import org.eclipse.xtext.common.idea.completion.TerminalsCompletionContributor;
import org.eclipse.xtext.idea.lang.AbstractXtextLanguage;

public class AbstractConcreteSyntaxValidationTestLanguageCompletionContributor extends TerminalsCompletionContributor {
	public AbstractConcreteSyntaxValidationTestLanguageCompletionContributor(AbstractXtextLanguage lang) {
		super(lang);
	}
}
