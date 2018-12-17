package org.eclipse.xtext.testlanguages.idea.highlighting;

import com.intellij.openapi.fileTypes.SingleLazyInstanceSyntaxHighlighterFactory;
import com.intellij.openapi.fileTypes.SyntaxHighlighter;
import org.eclipse.xtext.testlanguages.idea.lang.OptionalEmptyTestLanguageLanguage;
import org.jetbrains.annotations.NotNull;

public class OptionalEmptyTestLanguageSyntaxHighlighterFactory extends SingleLazyInstanceSyntaxHighlighterFactory {
	
	@Override
    @NotNull
    protected SyntaxHighlighter createHighlighter() {
        return OptionalEmptyTestLanguageLanguage.INSTANCE.getInstance(SyntaxHighlighter.class);
    }

}
