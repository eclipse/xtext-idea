package org.eclipse.xtext.parser.assignments.idea.highlighting;

import com.intellij.openapi.fileTypes.SingleLazyInstanceSyntaxHighlighterFactory;
import com.intellij.openapi.fileTypes.SyntaxHighlighter;
import org.eclipse.xtext.parser.assignments.idea.lang.AssignmentsTestLanguageLanguage;
import org.jetbrains.annotations.NotNull;

public class AssignmentsTestLanguageSyntaxHighlighterFactory extends SingleLazyInstanceSyntaxHighlighterFactory {
	
	@Override
    @NotNull
    protected SyntaxHighlighter createHighlighter() {
        return AssignmentsTestLanguageLanguage.INSTANCE.getInstance(SyntaxHighlighter.class);
    }

}
