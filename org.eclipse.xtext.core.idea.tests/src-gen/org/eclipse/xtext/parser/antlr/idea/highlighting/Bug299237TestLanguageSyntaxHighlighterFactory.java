package org.eclipse.xtext.parser.antlr.idea.highlighting;

import com.intellij.openapi.fileTypes.SingleLazyInstanceSyntaxHighlighterFactory;
import com.intellij.openapi.fileTypes.SyntaxHighlighter;
import org.eclipse.xtext.parser.antlr.idea.lang.Bug299237TestLanguageLanguage;
import org.jetbrains.annotations.NotNull;

public class Bug299237TestLanguageSyntaxHighlighterFactory extends SingleLazyInstanceSyntaxHighlighterFactory {
	
	@Override
    @NotNull
    protected SyntaxHighlighter createHighlighter() {
        return Bug299237TestLanguageLanguage.INSTANCE.getInstance(SyntaxHighlighter.class);
    }

}
