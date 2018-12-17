package org.eclipse.xtext.enumrules.idea.highlighting;

import org.eclipse.xtext.enumrules.idea.lang.EnumRulesTestLanguageLanguage;
import org.eclipse.xtext.idea.highlighting.SemanticHighlightVisitor;

public class EnumRulesTestLanguageSemanticHighlightVisitor extends SemanticHighlightVisitor {
	public EnumRulesTestLanguageSemanticHighlightVisitor() {
		EnumRulesTestLanguageLanguage.INSTANCE.injectMembers(this);
	}
}
