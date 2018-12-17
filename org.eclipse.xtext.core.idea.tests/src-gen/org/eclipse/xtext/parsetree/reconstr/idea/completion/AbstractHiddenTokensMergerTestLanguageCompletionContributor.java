package org.eclipse.xtext.parsetree.reconstr.idea.completion;

import org.eclipse.xtext.common.idea.completion.TerminalsCompletionContributor;
import org.eclipse.xtext.idea.lang.AbstractXtextLanguage;

public class AbstractHiddenTokensMergerTestLanguageCompletionContributor extends TerminalsCompletionContributor {
	public AbstractHiddenTokensMergerTestLanguageCompletionContributor(AbstractXtextLanguage lang) {
		super(lang);
	}
}
