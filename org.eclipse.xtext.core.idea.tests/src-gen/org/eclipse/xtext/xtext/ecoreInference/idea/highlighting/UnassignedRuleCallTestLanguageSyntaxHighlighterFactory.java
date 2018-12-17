package org.eclipse.xtext.xtext.ecoreInference.idea.highlighting;

import com.intellij.openapi.fileTypes.SingleLazyInstanceSyntaxHighlighterFactory;
import com.intellij.openapi.fileTypes.SyntaxHighlighter;
import org.eclipse.xtext.xtext.ecoreInference.idea.lang.UnassignedRuleCallTestLanguageLanguage;
import org.jetbrains.annotations.NotNull;

public class UnassignedRuleCallTestLanguageSyntaxHighlighterFactory extends SingleLazyInstanceSyntaxHighlighterFactory {
	
	@Override
    @NotNull
    protected SyntaxHighlighter createHighlighter() {
        return UnassignedRuleCallTestLanguageLanguage.INSTANCE.getInstance(SyntaxHighlighter.class);
    }

}
