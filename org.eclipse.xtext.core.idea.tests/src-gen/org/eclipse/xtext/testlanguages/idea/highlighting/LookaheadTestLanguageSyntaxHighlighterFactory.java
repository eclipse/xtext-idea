package org.eclipse.xtext.testlanguages.idea.highlighting;

import com.intellij.openapi.fileTypes.SingleLazyInstanceSyntaxHighlighterFactory;
import com.intellij.openapi.fileTypes.SyntaxHighlighter;
import org.eclipse.xtext.testlanguages.idea.lang.LookaheadTestLanguageLanguage;
import org.jetbrains.annotations.NotNull;

public class LookaheadTestLanguageSyntaxHighlighterFactory extends SingleLazyInstanceSyntaxHighlighterFactory {
	
	@Override
    @NotNull
    protected SyntaxHighlighter createHighlighter() {
        return LookaheadTestLanguageLanguage.INSTANCE.getInstance(SyntaxHighlighter.class);
    }

}
