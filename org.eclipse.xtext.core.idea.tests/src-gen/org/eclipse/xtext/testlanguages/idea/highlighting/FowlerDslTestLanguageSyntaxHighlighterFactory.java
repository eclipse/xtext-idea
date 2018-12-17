package org.eclipse.xtext.testlanguages.idea.highlighting;

import com.intellij.openapi.fileTypes.SingleLazyInstanceSyntaxHighlighterFactory;
import com.intellij.openapi.fileTypes.SyntaxHighlighter;
import org.eclipse.xtext.testlanguages.idea.lang.FowlerDslTestLanguageLanguage;
import org.jetbrains.annotations.NotNull;

public class FowlerDslTestLanguageSyntaxHighlighterFactory extends SingleLazyInstanceSyntaxHighlighterFactory {
	
	@Override
    @NotNull
    protected SyntaxHighlighter createHighlighter() {
        return FowlerDslTestLanguageLanguage.INSTANCE.getInstance(SyntaxHighlighter.class);
    }

}
