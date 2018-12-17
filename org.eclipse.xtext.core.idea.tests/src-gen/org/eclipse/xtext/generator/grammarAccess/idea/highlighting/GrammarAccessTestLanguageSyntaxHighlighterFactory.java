package org.eclipse.xtext.generator.grammarAccess.idea.highlighting;

import com.intellij.openapi.fileTypes.SingleLazyInstanceSyntaxHighlighterFactory;
import com.intellij.openapi.fileTypes.SyntaxHighlighter;
import org.eclipse.xtext.generator.grammarAccess.idea.lang.GrammarAccessTestLanguageLanguage;
import org.jetbrains.annotations.NotNull;

public class GrammarAccessTestLanguageSyntaxHighlighterFactory extends SingleLazyInstanceSyntaxHighlighterFactory {
	
	@Override
    @NotNull
    protected SyntaxHighlighter createHighlighter() {
        return GrammarAccessTestLanguageLanguage.INSTANCE.getInstance(SyntaxHighlighter.class);
    }

}
