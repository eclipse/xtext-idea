package org.eclipse.xtext.dummy.idea.completion;

import org.eclipse.xtext.common.idea.completion.TerminalsCompletionContributor;
import org.eclipse.xtext.idea.lang.AbstractXtextLanguage;

public class AbstractDummyTestLanguageCompletionContributor extends TerminalsCompletionContributor {
	public AbstractDummyTestLanguageCompletionContributor(AbstractXtextLanguage lang) {
		super(lang);
	}
}
