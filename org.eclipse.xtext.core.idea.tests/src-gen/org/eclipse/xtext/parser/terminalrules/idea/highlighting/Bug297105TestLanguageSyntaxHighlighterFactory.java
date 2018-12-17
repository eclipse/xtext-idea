package org.eclipse.xtext.parser.terminalrules.idea.highlighting;

import com.intellij.openapi.fileTypes.SingleLazyInstanceSyntaxHighlighterFactory;
import com.intellij.openapi.fileTypes.SyntaxHighlighter;
import org.eclipse.xtext.parser.terminalrules.idea.lang.Bug297105TestLanguageLanguage;
import org.jetbrains.annotations.NotNull;

public class Bug297105TestLanguageSyntaxHighlighterFactory extends SingleLazyInstanceSyntaxHighlighterFactory {
	
	@Override
    @NotNull
    protected SyntaxHighlighter createHighlighter() {
        return Bug297105TestLanguageLanguage.INSTANCE.getInstance(SyntaxHighlighter.class);
    }

}
