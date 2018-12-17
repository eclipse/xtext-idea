package org.eclipse.xtext.resource.idea.highlighting;

import com.intellij.openapi.fileTypes.SingleLazyInstanceSyntaxHighlighterFactory;
import com.intellij.openapi.fileTypes.SyntaxHighlighter;
import org.eclipse.xtext.resource.idea.lang.LiveContainerBuilderIntegerationTestLanguageLanguage;
import org.jetbrains.annotations.NotNull;

public class LiveContainerBuilderIntegerationTestLanguageSyntaxHighlighterFactory extends SingleLazyInstanceSyntaxHighlighterFactory {
	
	@Override
    @NotNull
    protected SyntaxHighlighter createHighlighter() {
        return LiveContainerBuilderIntegerationTestLanguageLanguage.INSTANCE.getInstance(SyntaxHighlighter.class);
    }

}
