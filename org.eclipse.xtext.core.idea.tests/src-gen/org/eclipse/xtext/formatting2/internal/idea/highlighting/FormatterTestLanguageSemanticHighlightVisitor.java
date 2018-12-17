package org.eclipse.xtext.formatting2.internal.idea.highlighting;

import org.eclipse.xtext.formatting2.internal.idea.lang.FormatterTestLanguageLanguage;
import org.eclipse.xtext.idea.highlighting.SemanticHighlightVisitor;

public class FormatterTestLanguageSemanticHighlightVisitor extends SemanticHighlightVisitor {
	public FormatterTestLanguageSemanticHighlightVisitor() {
		FormatterTestLanguageLanguage.INSTANCE.injectMembers(this);
	}
}
