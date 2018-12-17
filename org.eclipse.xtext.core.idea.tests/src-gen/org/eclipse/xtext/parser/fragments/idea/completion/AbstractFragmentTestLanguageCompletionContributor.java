package org.eclipse.xtext.parser.fragments.idea.completion;

import org.eclipse.xtext.common.idea.completion.TerminalsCompletionContributor;
import org.eclipse.xtext.idea.lang.AbstractXtextLanguage;

public class AbstractFragmentTestLanguageCompletionContributor extends TerminalsCompletionContributor {
	public AbstractFragmentTestLanguageCompletionContributor(AbstractXtextLanguage lang) {
		super(lang);
	}
}
