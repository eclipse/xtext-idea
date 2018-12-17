package org.eclipse.xtext.grammarinheritance.idea.highlighting;

import org.eclipse.xtext.grammarinheritance.idea.lang.ConcreteTestLanguageLanguage;
import org.eclipse.xtext.idea.highlighting.SemanticHighlightVisitor;

public class ConcreteTestLanguageSemanticHighlightVisitor extends SemanticHighlightVisitor {
	public ConcreteTestLanguageSemanticHighlightVisitor() {
		ConcreteTestLanguageLanguage.INSTANCE.injectMembers(this);
	}
}
