package org.eclipse.xtext.lexer.idea.highlighting;

import com.intellij.openapi.fileTypes.SingleLazyInstanceSyntaxHighlighterFactory;
import com.intellij.openapi.fileTypes.SyntaxHighlighter;
import org.eclipse.xtext.lexer.idea.lang.IgnoreCaseLexerTestLanguageLanguage;
import org.jetbrains.annotations.NotNull;

public class IgnoreCaseLexerTestLanguageSyntaxHighlighterFactory extends SingleLazyInstanceSyntaxHighlighterFactory {
	
	@Override
    @NotNull
    protected SyntaxHighlighter createHighlighter() {
        return IgnoreCaseLexerTestLanguageLanguage.INSTANCE.getInstance(SyntaxHighlighter.class);
    }

}
