package org.eclipse.xtext.idea.highlighting;

import org.eclipse.xtext.idea.lang.XtextGrammarTestLanguageLanguage;

public class XtextGrammarTestLanguageSemanticHighlightVisitor extends SemanticHighlightVisitor {
	public XtextGrammarTestLanguageSemanticHighlightVisitor() {
		XtextGrammarTestLanguageLanguage.INSTANCE.injectMembers(this);
	}
}
