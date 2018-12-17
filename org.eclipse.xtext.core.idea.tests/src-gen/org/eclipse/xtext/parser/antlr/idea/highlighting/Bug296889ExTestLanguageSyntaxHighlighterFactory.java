package org.eclipse.xtext.parser.antlr.idea.highlighting;

import com.intellij.openapi.fileTypes.SingleLazyInstanceSyntaxHighlighterFactory;
import com.intellij.openapi.fileTypes.SyntaxHighlighter;
import org.eclipse.xtext.parser.antlr.idea.lang.Bug296889ExTestLanguageLanguage;
import org.jetbrains.annotations.NotNull;

public class Bug296889ExTestLanguageSyntaxHighlighterFactory extends SingleLazyInstanceSyntaxHighlighterFactory {
	
	@Override
    @NotNull
    protected SyntaxHighlighter createHighlighter() {
        return Bug296889ExTestLanguageLanguage.INSTANCE.getInstance(SyntaxHighlighter.class);
    }

}
