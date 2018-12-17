package org.eclipse.xtext.parser.antlr.idea.highlighting;

import com.intellij.openapi.fileTypes.SingleLazyInstanceSyntaxHighlighterFactory;
import com.intellij.openapi.fileTypes.SyntaxHighlighter;
import org.eclipse.xtext.parser.antlr.idea.lang.Bug289515TestLanguageLanguage;
import org.jetbrains.annotations.NotNull;

public class Bug289515TestLanguageSyntaxHighlighterFactory extends SingleLazyInstanceSyntaxHighlighterFactory {
	
	@Override
    @NotNull
    protected SyntaxHighlighter createHighlighter() {
        return Bug289515TestLanguageLanguage.INSTANCE.getInstance(SyntaxHighlighter.class);
    }

}
