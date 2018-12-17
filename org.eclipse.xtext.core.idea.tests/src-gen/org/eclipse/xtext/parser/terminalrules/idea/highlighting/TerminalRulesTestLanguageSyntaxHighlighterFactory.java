package org.eclipse.xtext.parser.terminalrules.idea.highlighting;

import com.intellij.openapi.fileTypes.SingleLazyInstanceSyntaxHighlighterFactory;
import com.intellij.openapi.fileTypes.SyntaxHighlighter;
import org.eclipse.xtext.parser.terminalrules.idea.lang.TerminalRulesTestLanguageLanguage;
import org.jetbrains.annotations.NotNull;

public class TerminalRulesTestLanguageSyntaxHighlighterFactory extends SingleLazyInstanceSyntaxHighlighterFactory {
	
	@Override
    @NotNull
    protected SyntaxHighlighter createHighlighter() {
        return TerminalRulesTestLanguageLanguage.INSTANCE.getInstance(SyntaxHighlighter.class);
    }

}
