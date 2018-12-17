package org.eclipse.xtext.linking.idea.highlighting;

import com.intellij.openapi.fileTypes.SingleLazyInstanceSyntaxHighlighterFactory;
import com.intellij.openapi.fileTypes.SyntaxHighlighter;
import org.eclipse.xtext.linking.idea.lang.AbstractIgnoreCaseLinkingTestLanguageLanguage;
import org.jetbrains.annotations.NotNull;

public class AbstractIgnoreCaseLinkingTestLanguageSyntaxHighlighterFactory extends SingleLazyInstanceSyntaxHighlighterFactory {
	
	@Override
    @NotNull
    protected SyntaxHighlighter createHighlighter() {
        return AbstractIgnoreCaseLinkingTestLanguageLanguage.INSTANCE.getInstance(SyntaxHighlighter.class);
    }

}
