package org.eclipse.xtext.generator.ecore.idea.highlighting;

import org.eclipse.xtext.generator.ecore.idea.lang.EcoreFragmentTestLanguageLanguage;
import org.eclipse.xtext.idea.highlighting.SemanticHighlightVisitor;

public class EcoreFragmentTestLanguageSemanticHighlightVisitor extends SemanticHighlightVisitor {
	public EcoreFragmentTestLanguageSemanticHighlightVisitor() {
		EcoreFragmentTestLanguageLanguage.INSTANCE.injectMembers(this);
	}
}
