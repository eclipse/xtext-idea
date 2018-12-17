package org.eclipse.xtext.metamodelreferencing.tests.idea.completion;

import org.eclipse.xtext.common.idea.completion.TerminalsCompletionContributor;
import org.eclipse.xtext.idea.lang.AbstractXtextLanguage;

public class AbstractMetamodelRefTestLanguageCompletionContributor extends TerminalsCompletionContributor {
	public AbstractMetamodelRefTestLanguageCompletionContributor(AbstractXtextLanguage lang) {
		super(lang);
	}
}
