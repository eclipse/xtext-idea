package org.eclipse.xtext.dummy.idea.highlighting;

import org.eclipse.xtext.dummy.idea.lang.DummyTestLanguageLanguage;
import org.eclipse.xtext.idea.highlighting.SemanticHighlightVisitor;

public class DummyTestLanguageSemanticHighlightVisitor extends SemanticHighlightVisitor {
	public DummyTestLanguageSemanticHighlightVisitor() {
		DummyTestLanguageLanguage.INSTANCE.injectMembers(this);
	}
}
