package org.eclipse.xtext.parser.datatyperules.idea.highlighting;

import com.intellij.openapi.fileTypes.SingleLazyInstanceSyntaxHighlighterFactory;
import com.intellij.openapi.fileTypes.SyntaxHighlighter;
import org.eclipse.xtext.parser.datatyperules.idea.lang.DatatypeRulesTestLanguageLanguage;
import org.jetbrains.annotations.NotNull;

public class DatatypeRulesTestLanguageSyntaxHighlighterFactory extends SingleLazyInstanceSyntaxHighlighterFactory {
	
	@Override
    @NotNull
    protected SyntaxHighlighter createHighlighter() {
        return DatatypeRulesTestLanguageLanguage.INSTANCE.getInstance(SyntaxHighlighter.class);
    }

}
