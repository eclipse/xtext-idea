package org.eclipse.xtext.parser.unorderedGroups.idea.completion;

import org.eclipse.xtext.common.idea.completion.TerminalsCompletionContributor;
import org.eclipse.xtext.idea.lang.AbstractXtextLanguage;

public class AbstractBacktrackingBug325745TestLanguageCompletionContributor extends TerminalsCompletionContributor {
	public AbstractBacktrackingBug325745TestLanguageCompletionContributor(AbstractXtextLanguage lang) {
		super(lang);
	}
}
