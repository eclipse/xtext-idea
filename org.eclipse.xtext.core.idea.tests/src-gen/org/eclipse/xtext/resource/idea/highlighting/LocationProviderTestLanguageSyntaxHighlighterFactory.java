package org.eclipse.xtext.resource.idea.highlighting;

import com.intellij.openapi.fileTypes.SingleLazyInstanceSyntaxHighlighterFactory;
import com.intellij.openapi.fileTypes.SyntaxHighlighter;
import org.eclipse.xtext.resource.idea.lang.LocationProviderTestLanguageLanguage;
import org.jetbrains.annotations.NotNull;

public class LocationProviderTestLanguageSyntaxHighlighterFactory extends SingleLazyInstanceSyntaxHighlighterFactory {
	
	@Override
    @NotNull
    protected SyntaxHighlighter createHighlighter() {
        return LocationProviderTestLanguageLanguage.INSTANCE.getInstance(SyntaxHighlighter.class);
    }

}
