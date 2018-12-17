package org.eclipse.xtext.parser.terminalrules.idea.highlighting;

import com.intellij.openapi.fileTypes.SingleLazyInstanceSyntaxHighlighterFactory;
import com.intellij.openapi.fileTypes.SyntaxHighlighter;
import org.eclipse.xtext.parser.terminalrules.idea.lang.Bug317840TestLanguageLanguage;
import org.jetbrains.annotations.NotNull;

public class Bug317840TestLanguageSyntaxHighlighterFactory extends SingleLazyInstanceSyntaxHighlighterFactory {
	
	@Override
    @NotNull
    protected SyntaxHighlighter createHighlighter() {
        return Bug317840TestLanguageLanguage.INSTANCE.getInstance(SyntaxHighlighter.class);
    }

}
