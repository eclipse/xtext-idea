package org.eclipse.xtext.validation.idea.highlighting;

import com.intellij.openapi.fileTypes.SingleLazyInstanceSyntaxHighlighterFactory;
import com.intellij.openapi.fileTypes.SyntaxHighlighter;
import org.eclipse.xtext.validation.idea.lang.ConcreteSyntaxValidationTestLanguageLanguage;
import org.jetbrains.annotations.NotNull;

public class ConcreteSyntaxValidationTestLanguageSyntaxHighlighterFactory extends SingleLazyInstanceSyntaxHighlighterFactory {
	
	@Override
    @NotNull
    protected SyntaxHighlighter createHighlighter() {
        return ConcreteSyntaxValidationTestLanguageLanguage.INSTANCE.getInstance(SyntaxHighlighter.class);
    }

}
