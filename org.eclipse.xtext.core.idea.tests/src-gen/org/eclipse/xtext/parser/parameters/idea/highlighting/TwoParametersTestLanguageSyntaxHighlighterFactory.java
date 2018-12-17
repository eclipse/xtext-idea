package org.eclipse.xtext.parser.parameters.idea.highlighting;

import com.intellij.openapi.fileTypes.SingleLazyInstanceSyntaxHighlighterFactory;
import com.intellij.openapi.fileTypes.SyntaxHighlighter;
import org.eclipse.xtext.parser.parameters.idea.lang.TwoParametersTestLanguageLanguage;
import org.jetbrains.annotations.NotNull;

public class TwoParametersTestLanguageSyntaxHighlighterFactory extends SingleLazyInstanceSyntaxHighlighterFactory {
	
	@Override
    @NotNull
    protected SyntaxHighlighter createHighlighter() {
        return TwoParametersTestLanguageLanguage.INSTANCE.getInstance(SyntaxHighlighter.class);
    }

}
