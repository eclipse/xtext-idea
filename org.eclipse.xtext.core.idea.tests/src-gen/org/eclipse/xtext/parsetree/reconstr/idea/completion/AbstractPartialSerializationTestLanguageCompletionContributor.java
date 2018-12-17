package org.eclipse.xtext.parsetree.reconstr.idea.completion;

import org.eclipse.xtext.common.idea.completion.TerminalsCompletionContributor;
import org.eclipse.xtext.idea.lang.AbstractXtextLanguage;

public class AbstractPartialSerializationTestLanguageCompletionContributor extends TerminalsCompletionContributor {
	public AbstractPartialSerializationTestLanguageCompletionContributor(AbstractXtextLanguage lang) {
		super(lang);
	}
}
