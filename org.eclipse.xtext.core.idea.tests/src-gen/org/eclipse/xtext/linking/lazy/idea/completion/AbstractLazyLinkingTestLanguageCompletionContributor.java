package org.eclipse.xtext.linking.lazy.idea.completion;

import org.eclipse.xtext.common.idea.completion.TerminalsCompletionContributor;
import org.eclipse.xtext.idea.lang.AbstractXtextLanguage;

public class AbstractLazyLinkingTestLanguageCompletionContributor extends TerminalsCompletionContributor {
	public AbstractLazyLinkingTestLanguageCompletionContributor(AbstractXtextLanguage lang) {
		super(lang);
	}
}
