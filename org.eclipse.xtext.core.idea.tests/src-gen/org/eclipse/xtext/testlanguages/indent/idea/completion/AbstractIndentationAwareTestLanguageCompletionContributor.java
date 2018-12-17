package org.eclipse.xtext.testlanguages.indent.idea.completion;

import org.eclipse.xtext.idea.completion.AbstractCompletionContributor;
import org.eclipse.xtext.idea.lang.AbstractXtextLanguage;

public class AbstractIndentationAwareTestLanguageCompletionContributor extends AbstractCompletionContributor {
	public AbstractIndentationAwareTestLanguageCompletionContributor(AbstractXtextLanguage lang) {
		super(lang);
	}
}
