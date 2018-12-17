package org.eclipse.xtext.linking.idea.highlighting;

import com.intellij.openapi.fileTypes.SingleLazyInstanceSyntaxHighlighterFactory;
import com.intellij.openapi.fileTypes.SyntaxHighlighter;
import org.eclipse.xtext.linking.idea.lang.IgnoreCaseImportsTestLanguageLanguage;
import org.jetbrains.annotations.NotNull;

public class IgnoreCaseImportsTestLanguageSyntaxHighlighterFactory extends SingleLazyInstanceSyntaxHighlighterFactory {
	
	@Override
    @NotNull
    protected SyntaxHighlighter createHighlighter() {
        return IgnoreCaseImportsTestLanguageLanguage.INSTANCE.getInstance(SyntaxHighlighter.class);
    }

}
