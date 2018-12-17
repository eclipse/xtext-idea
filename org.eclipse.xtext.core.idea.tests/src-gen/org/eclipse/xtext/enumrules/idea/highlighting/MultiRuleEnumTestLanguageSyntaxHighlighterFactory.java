package org.eclipse.xtext.enumrules.idea.highlighting;

import com.intellij.openapi.fileTypes.SingleLazyInstanceSyntaxHighlighterFactory;
import com.intellij.openapi.fileTypes.SyntaxHighlighter;
import org.eclipse.xtext.enumrules.idea.lang.MultiRuleEnumTestLanguageLanguage;
import org.jetbrains.annotations.NotNull;

public class MultiRuleEnumTestLanguageSyntaxHighlighterFactory extends SingleLazyInstanceSyntaxHighlighterFactory {
	
	@Override
    @NotNull
    protected SyntaxHighlighter createHighlighter() {
        return MultiRuleEnumTestLanguageLanguage.INSTANCE.getInstance(SyntaxHighlighter.class);
    }

}
