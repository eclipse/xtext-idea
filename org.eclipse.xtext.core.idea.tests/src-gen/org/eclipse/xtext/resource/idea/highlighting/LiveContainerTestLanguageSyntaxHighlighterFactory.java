package org.eclipse.xtext.resource.idea.highlighting;

import com.intellij.openapi.fileTypes.SingleLazyInstanceSyntaxHighlighterFactory;
import com.intellij.openapi.fileTypes.SyntaxHighlighter;
import org.eclipse.xtext.resource.idea.lang.LiveContainerTestLanguageLanguage;
import org.jetbrains.annotations.NotNull;

public class LiveContainerTestLanguageSyntaxHighlighterFactory extends SingleLazyInstanceSyntaxHighlighterFactory {
	
	@Override
    @NotNull
    protected SyntaxHighlighter createHighlighter() {
        return LiveContainerTestLanguageLanguage.INSTANCE.getInstance(SyntaxHighlighter.class);
    }

}
