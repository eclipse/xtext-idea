package org.eclipse.xtext.parser.parameters.idea.highlighting;

import com.intellij.openapi.fileTypes.SingleLazyInstanceSyntaxHighlighterFactory;
import com.intellij.openapi.fileTypes.SyntaxHighlighter;
import org.eclipse.xtext.parser.parameters.idea.lang.ParametersTestLanguageLanguage;
import org.jetbrains.annotations.NotNull;

public class ParametersTestLanguageSyntaxHighlighterFactory extends SingleLazyInstanceSyntaxHighlighterFactory {
	
	@Override
    @NotNull
    protected SyntaxHighlighter createHighlighter() {
        return ParametersTestLanguageLanguage.INSTANCE.getInstance(SyntaxHighlighter.class);
    }

}
