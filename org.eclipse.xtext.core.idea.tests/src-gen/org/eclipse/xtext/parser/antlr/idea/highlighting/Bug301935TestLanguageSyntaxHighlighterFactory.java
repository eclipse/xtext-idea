package org.eclipse.xtext.parser.antlr.idea.highlighting;

import com.intellij.openapi.fileTypes.SingleLazyInstanceSyntaxHighlighterFactory;
import com.intellij.openapi.fileTypes.SyntaxHighlighter;
import org.eclipse.xtext.parser.antlr.idea.lang.Bug301935TestLanguageLanguage;
import org.jetbrains.annotations.NotNull;

public class Bug301935TestLanguageSyntaxHighlighterFactory extends SingleLazyInstanceSyntaxHighlighterFactory {
	
	@Override
    @NotNull
    protected SyntaxHighlighter createHighlighter() {
        return Bug301935TestLanguageLanguage.INSTANCE.getInstance(SyntaxHighlighter.class);
    }

}
