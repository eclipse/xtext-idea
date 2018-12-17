package org.eclipse.xtext.xtext.ecoreInference.idea.completion;

import org.eclipse.xtext.common.idea.completion.TerminalsCompletionContributor;
import org.eclipse.xtext.idea.lang.AbstractXtextLanguage;

public class AbstractUnassignedRuleCallTestLanguageCompletionContributor extends TerminalsCompletionContributor {
	public AbstractUnassignedRuleCallTestLanguageCompletionContributor(AbstractXtextLanguage lang) {
		super(lang);
	}
}
