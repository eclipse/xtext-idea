package org.eclipse.xtext.parser.antlr.idea.highlighting;

import com.intellij.openapi.fileTypes.SingleLazyInstanceSyntaxHighlighterFactory;
import com.intellij.openapi.fileTypes.SyntaxHighlighter;
import org.eclipse.xtext.parser.antlr.idea.lang.Bug289524ExTestLanguageLanguage;
import org.jetbrains.annotations.NotNull;

public class Bug289524ExTestLanguageSyntaxHighlighterFactory extends SingleLazyInstanceSyntaxHighlighterFactory {
	
	@Override
    @NotNull
    protected SyntaxHighlighter createHighlighter() {
        return Bug289524ExTestLanguageLanguage.INSTANCE.getInstance(SyntaxHighlighter.class);
    }

}
