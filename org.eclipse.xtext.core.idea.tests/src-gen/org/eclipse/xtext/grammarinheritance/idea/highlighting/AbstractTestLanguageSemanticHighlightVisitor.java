package org.eclipse.xtext.grammarinheritance.idea.highlighting;

import org.eclipse.xtext.grammarinheritance.idea.lang.AbstractTestLanguageLanguage;
import org.eclipse.xtext.idea.highlighting.SemanticHighlightVisitor;

public class AbstractTestLanguageSemanticHighlightVisitor extends SemanticHighlightVisitor {
	public AbstractTestLanguageSemanticHighlightVisitor() {
		AbstractTestLanguageLanguage.INSTANCE.injectMembers(this);
	}
}
