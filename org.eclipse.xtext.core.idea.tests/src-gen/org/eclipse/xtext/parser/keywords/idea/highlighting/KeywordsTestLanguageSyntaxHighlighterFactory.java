package org.eclipse.xtext.parser.keywords.idea.highlighting;

import com.intellij.openapi.fileTypes.SingleLazyInstanceSyntaxHighlighterFactory;
import com.intellij.openapi.fileTypes.SyntaxHighlighter;
import org.eclipse.xtext.parser.keywords.idea.lang.KeywordsTestLanguageLanguage;
import org.jetbrains.annotations.NotNull;

public class KeywordsTestLanguageSyntaxHighlighterFactory extends SingleLazyInstanceSyntaxHighlighterFactory {
	
	@Override
    @NotNull
    protected SyntaxHighlighter createHighlighter() {
        return KeywordsTestLanguageLanguage.INSTANCE.getInstance(SyntaxHighlighter.class);
    }

}
