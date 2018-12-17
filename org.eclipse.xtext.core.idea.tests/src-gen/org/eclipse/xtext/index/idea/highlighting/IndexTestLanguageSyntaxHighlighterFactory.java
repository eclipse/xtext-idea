package org.eclipse.xtext.index.idea.highlighting;

import com.intellij.openapi.fileTypes.SingleLazyInstanceSyntaxHighlighterFactory;
import com.intellij.openapi.fileTypes.SyntaxHighlighter;
import org.eclipse.xtext.index.idea.lang.IndexTestLanguageLanguage;
import org.jetbrains.annotations.NotNull;

public class IndexTestLanguageSyntaxHighlighterFactory extends SingleLazyInstanceSyntaxHighlighterFactory {
	
	@Override
    @NotNull
    protected SyntaxHighlighter createHighlighter() {
        return IndexTestLanguageLanguage.INSTANCE.getInstance(SyntaxHighlighter.class);
    }

}
