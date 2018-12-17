package org.eclipse.xtext.index.idea.completion;

import org.eclipse.xtext.common.idea.completion.TerminalsCompletionContributor;
import org.eclipse.xtext.idea.lang.AbstractXtextLanguage;

public class AbstractIndexTestLanguageCompletionContributor extends TerminalsCompletionContributor {
	public AbstractIndexTestLanguageCompletionContributor(AbstractXtextLanguage lang) {
		super(lang);
	}
}
