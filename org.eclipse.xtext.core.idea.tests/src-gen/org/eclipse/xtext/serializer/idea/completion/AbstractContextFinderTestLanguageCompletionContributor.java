package org.eclipse.xtext.serializer.idea.completion;

import org.eclipse.xtext.common.idea.completion.TerminalsCompletionContributor;
import org.eclipse.xtext.idea.lang.AbstractXtextLanguage;

public class AbstractContextFinderTestLanguageCompletionContributor extends TerminalsCompletionContributor {
	public AbstractContextFinderTestLanguageCompletionContributor(AbstractXtextLanguage lang) {
		super(lang);
	}
}
