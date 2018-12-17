package org.eclipse.xtext.parser.terminalrules.idea.highlighting;

import com.intellij.openapi.fileTypes.SingleLazyInstanceSyntaxHighlighterFactory;
import com.intellij.openapi.fileTypes.SyntaxHighlighter;
import org.eclipse.xtext.parser.terminalrules.idea.lang.UnicodeTestLanguageLanguage;
import org.jetbrains.annotations.NotNull;

public class UnicodeTestLanguageSyntaxHighlighterFactory extends SingleLazyInstanceSyntaxHighlighterFactory {
	
	@Override
    @NotNull
    protected SyntaxHighlighter createHighlighter() {
        return UnicodeTestLanguageLanguage.INSTANCE.getInstance(SyntaxHighlighter.class);
    }

}
