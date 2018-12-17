package org.eclipse.xtext.enumrules.idea.highlighting;

import com.intellij.openapi.fileTypes.SingleLazyInstanceSyntaxHighlighterFactory;
import com.intellij.openapi.fileTypes.SyntaxHighlighter;
import org.eclipse.xtext.enumrules.idea.lang.EnumRulesTestLanguageLanguage;
import org.jetbrains.annotations.NotNull;

public class EnumRulesTestLanguageSyntaxHighlighterFactory extends SingleLazyInstanceSyntaxHighlighterFactory {
	
	@Override
    @NotNull
    protected SyntaxHighlighter createHighlighter() {
        return EnumRulesTestLanguageLanguage.INSTANCE.getInstance(SyntaxHighlighter.class);
    }

}
