package org.eclipse.xtext.parsetree.formatter.idea.completion;

import org.eclipse.xtext.common.idea.completion.TerminalsCompletionContributor;
import org.eclipse.xtext.idea.lang.AbstractXtextLanguage;

public class AbstractFormatterTestLanguageCompletionContributor extends TerminalsCompletionContributor {
	public AbstractFormatterTestLanguageCompletionContributor(AbstractXtextLanguage lang) {
		super(lang);
	}
}
