package org.eclipse.xtext.testlanguages.idea.highlighting;

import com.intellij.openapi.fileTypes.SingleLazyInstanceSyntaxHighlighterFactory;
import com.intellij.openapi.fileTypes.SyntaxHighlighter;
import org.eclipse.xtext.testlanguages.idea.lang.SimpleExpressionsTestLanguageLanguage;
import org.jetbrains.annotations.NotNull;

public class SimpleExpressionsTestLanguageSyntaxHighlighterFactory extends SingleLazyInstanceSyntaxHighlighterFactory {
	
	@Override
    @NotNull
    protected SyntaxHighlighter createHighlighter() {
        return SimpleExpressionsTestLanguageLanguage.INSTANCE.getInstance(SyntaxHighlighter.class);
    }

}
