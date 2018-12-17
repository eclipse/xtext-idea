package org.eclipse.xtext.parser.parameters.idea.highlighting;

import com.intellij.openapi.fileTypes.SingleLazyInstanceSyntaxHighlighterFactory;
import com.intellij.openapi.fileTypes.SyntaxHighlighter;
import org.eclipse.xtext.parser.parameters.idea.lang.NoParametersTestLanguageLanguage;
import org.jetbrains.annotations.NotNull;

public class NoParametersTestLanguageSyntaxHighlighterFactory extends SingleLazyInstanceSyntaxHighlighterFactory {
	
	@Override
    @NotNull
    protected SyntaxHighlighter createHighlighter() {
        return NoParametersTestLanguageLanguage.INSTANCE.getInstance(SyntaxHighlighter.class);
    }

}
