package org.eclipse.xtext.linking.lazy.idea.highlighting;

import com.intellij.openapi.fileTypes.SingleLazyInstanceSyntaxHighlighterFactory;
import com.intellij.openapi.fileTypes.SyntaxHighlighter;
import org.eclipse.xtext.linking.lazy.idea.lang.LazyLinkingTestLanguageLanguage;
import org.jetbrains.annotations.NotNull;

public class LazyLinkingTestLanguageSyntaxHighlighterFactory extends SingleLazyInstanceSyntaxHighlighterFactory {
	
	@Override
    @NotNull
    protected SyntaxHighlighter createHighlighter() {
        return LazyLinkingTestLanguageLanguage.INSTANCE.getInstance(SyntaxHighlighter.class);
    }

}
