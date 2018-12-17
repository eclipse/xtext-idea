package org.eclipse.xtext.testlanguages.idea.highlighting;

import com.intellij.openapi.fileTypes.SingleLazyInstanceSyntaxHighlighterFactory;
import com.intellij.openapi.fileTypes.SyntaxHighlighter;
import org.eclipse.xtext.testlanguages.idea.lang.ReferenceGrammarTestLanguageLanguage;
import org.jetbrains.annotations.NotNull;

public class ReferenceGrammarTestLanguageSyntaxHighlighterFactory extends SingleLazyInstanceSyntaxHighlighterFactory {
	
	@Override
    @NotNull
    protected SyntaxHighlighter createHighlighter() {
        return ReferenceGrammarTestLanguageLanguage.INSTANCE.getInstance(SyntaxHighlighter.class);
    }

}
