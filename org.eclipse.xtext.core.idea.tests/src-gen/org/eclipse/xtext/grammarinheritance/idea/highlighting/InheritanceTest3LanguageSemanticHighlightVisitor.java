package org.eclipse.xtext.grammarinheritance.idea.highlighting;

import org.eclipse.xtext.grammarinheritance.idea.lang.InheritanceTest3LanguageLanguage;
import org.eclipse.xtext.idea.highlighting.SemanticHighlightVisitor;

public class InheritanceTest3LanguageSemanticHighlightVisitor extends SemanticHighlightVisitor {
	public InheritanceTest3LanguageSemanticHighlightVisitor() {
		InheritanceTest3LanguageLanguage.INSTANCE.injectMembers(this);
	}
}
