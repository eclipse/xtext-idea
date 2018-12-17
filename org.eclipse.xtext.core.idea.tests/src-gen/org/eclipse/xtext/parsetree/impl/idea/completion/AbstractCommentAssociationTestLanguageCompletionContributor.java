package org.eclipse.xtext.parsetree.impl.idea.completion;

import org.eclipse.xtext.common.idea.completion.TerminalsCompletionContributor;
import org.eclipse.xtext.idea.lang.AbstractXtextLanguage;

public class AbstractCommentAssociationTestLanguageCompletionContributor extends TerminalsCompletionContributor {
	public AbstractCommentAssociationTestLanguageCompletionContributor(AbstractXtextLanguage lang) {
		super(lang);
	}
}
