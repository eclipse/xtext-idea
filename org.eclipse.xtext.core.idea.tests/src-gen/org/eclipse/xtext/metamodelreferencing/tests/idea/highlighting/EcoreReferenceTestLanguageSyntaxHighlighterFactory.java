package org.eclipse.xtext.metamodelreferencing.tests.idea.highlighting;

import com.intellij.openapi.fileTypes.SingleLazyInstanceSyntaxHighlighterFactory;
import com.intellij.openapi.fileTypes.SyntaxHighlighter;
import org.eclipse.xtext.metamodelreferencing.tests.idea.lang.EcoreReferenceTestLanguageLanguage;
import org.jetbrains.annotations.NotNull;

public class EcoreReferenceTestLanguageSyntaxHighlighterFactory extends SingleLazyInstanceSyntaxHighlighterFactory {
	
	@Override
    @NotNull
    protected SyntaxHighlighter createHighlighter() {
        return EcoreReferenceTestLanguageLanguage.INSTANCE.getInstance(SyntaxHighlighter.class);
    }

}
