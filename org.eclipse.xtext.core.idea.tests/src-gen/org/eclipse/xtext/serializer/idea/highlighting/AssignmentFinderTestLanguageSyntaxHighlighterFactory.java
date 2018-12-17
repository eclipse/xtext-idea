package org.eclipse.xtext.serializer.idea.highlighting;

import com.intellij.openapi.fileTypes.SingleLazyInstanceSyntaxHighlighterFactory;
import com.intellij.openapi.fileTypes.SyntaxHighlighter;
import org.eclipse.xtext.serializer.idea.lang.AssignmentFinderTestLanguageLanguage;
import org.jetbrains.annotations.NotNull;

public class AssignmentFinderTestLanguageSyntaxHighlighterFactory extends SingleLazyInstanceSyntaxHighlighterFactory {
	
	@Override
    @NotNull
    protected SyntaxHighlighter createHighlighter() {
        return AssignmentFinderTestLanguageLanguage.INSTANCE.getInstance(SyntaxHighlighter.class);
    }

}
