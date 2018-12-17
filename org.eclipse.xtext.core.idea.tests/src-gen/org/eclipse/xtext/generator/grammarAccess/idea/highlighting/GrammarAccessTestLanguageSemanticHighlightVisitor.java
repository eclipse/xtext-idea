package org.eclipse.xtext.generator.grammarAccess.idea.highlighting;

import org.eclipse.xtext.generator.grammarAccess.idea.lang.GrammarAccessTestLanguageLanguage;
import org.eclipse.xtext.idea.highlighting.SemanticHighlightVisitor;

public class GrammarAccessTestLanguageSemanticHighlightVisitor extends SemanticHighlightVisitor {
	public GrammarAccessTestLanguageSemanticHighlightVisitor() {
		GrammarAccessTestLanguageLanguage.INSTANCE.injectMembers(this);
	}
}
