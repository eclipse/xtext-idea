package org.eclipse.xtext.resource.idea.completion;

import org.eclipse.xtext.common.idea.completion.TerminalsCompletionContributor;
import org.eclipse.xtext.idea.lang.AbstractXtextLanguage;

public class AbstractLocationProviderTestLanguageCompletionContributor extends TerminalsCompletionContributor {
	public AbstractLocationProviderTestLanguageCompletionContributor(AbstractXtextLanguage lang) {
		super(lang);
	}
}
