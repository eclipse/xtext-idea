package org.eclipse.xtext.enumrules.idea.highlighting;

import org.eclipse.xtext.enumrules.idea.lang.EnumAndReferenceTestLanguageLanguage;
import org.eclipse.xtext.idea.highlighting.SemanticHighlightVisitor;

public class EnumAndReferenceTestLanguageSemanticHighlightVisitor extends SemanticHighlightVisitor {
	public EnumAndReferenceTestLanguageSemanticHighlightVisitor() {
		EnumAndReferenceTestLanguageLanguage.INSTANCE.injectMembers(this);
	}
}
