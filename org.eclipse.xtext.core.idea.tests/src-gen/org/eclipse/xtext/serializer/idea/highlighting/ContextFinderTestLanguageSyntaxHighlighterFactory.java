package org.eclipse.xtext.serializer.idea.highlighting;

import com.intellij.openapi.fileTypes.SingleLazyInstanceSyntaxHighlighterFactory;
import com.intellij.openapi.fileTypes.SyntaxHighlighter;
import org.eclipse.xtext.serializer.idea.lang.ContextFinderTestLanguageLanguage;
import org.jetbrains.annotations.NotNull;

public class ContextFinderTestLanguageSyntaxHighlighterFactory extends SingleLazyInstanceSyntaxHighlighterFactory {
	
	@Override
    @NotNull
    protected SyntaxHighlighter createHighlighter() {
        return ContextFinderTestLanguageLanguage.INSTANCE.getInstance(SyntaxHighlighter.class);
    }

}
