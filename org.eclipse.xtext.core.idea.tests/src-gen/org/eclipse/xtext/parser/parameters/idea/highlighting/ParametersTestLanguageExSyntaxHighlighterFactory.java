package org.eclipse.xtext.parser.parameters.idea.highlighting;

import com.intellij.openapi.fileTypes.SingleLazyInstanceSyntaxHighlighterFactory;
import com.intellij.openapi.fileTypes.SyntaxHighlighter;
import org.eclipse.xtext.parser.parameters.idea.lang.ParametersTestLanguageExLanguage;
import org.jetbrains.annotations.NotNull;

public class ParametersTestLanguageExSyntaxHighlighterFactory extends SingleLazyInstanceSyntaxHighlighterFactory {
	
	@Override
    @NotNull
    protected SyntaxHighlighter createHighlighter() {
        return ParametersTestLanguageExLanguage.INSTANCE.getInstance(SyntaxHighlighter.class);
    }

}
