package org.eclipse.xtext.xtext.ecoreInference.idea.highlighting;

import com.intellij.openapi.fileTypes.SingleLazyInstanceSyntaxHighlighterFactory;
import com.intellij.openapi.fileTypes.SyntaxHighlighter;
import org.eclipse.xtext.xtext.ecoreInference.idea.lang.MultiValueFeatureTestLanguageLanguage;
import org.jetbrains.annotations.NotNull;

public class MultiValueFeatureTestLanguageSyntaxHighlighterFactory extends SingleLazyInstanceSyntaxHighlighterFactory {
	
	@Override
    @NotNull
    protected SyntaxHighlighter createHighlighter() {
        return MultiValueFeatureTestLanguageLanguage.INSTANCE.getInstance(SyntaxHighlighter.class);
    }

}
