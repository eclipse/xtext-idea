package org.eclipse.xtext.linking.idea.highlighting;

import com.intellij.openapi.fileTypes.SingleLazyInstanceSyntaxHighlighterFactory;
import com.intellij.openapi.fileTypes.SyntaxHighlighter;
import org.eclipse.xtext.linking.idea.lang.LangATestLanguageLanguage;
import org.jetbrains.annotations.NotNull;

public class LangATestLanguageSyntaxHighlighterFactory extends SingleLazyInstanceSyntaxHighlighterFactory {
	
	@Override
    @NotNull
    protected SyntaxHighlighter createHighlighter() {
        return LangATestLanguageLanguage.INSTANCE.getInstance(SyntaxHighlighter.class);
    }

}
