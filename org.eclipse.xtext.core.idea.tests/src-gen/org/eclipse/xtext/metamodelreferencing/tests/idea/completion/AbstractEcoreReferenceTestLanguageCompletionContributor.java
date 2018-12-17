package org.eclipse.xtext.metamodelreferencing.tests.idea.completion;

import org.eclipse.xtext.common.idea.completion.TerminalsCompletionContributor;
import org.eclipse.xtext.idea.lang.AbstractXtextLanguage;

public class AbstractEcoreReferenceTestLanguageCompletionContributor extends TerminalsCompletionContributor {
	public AbstractEcoreReferenceTestLanguageCompletionContributor(AbstractXtextLanguage lang) {
		super(lang);
	}
}
