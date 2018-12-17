package org.eclipse.xtext.parsetree.reconstr.idea.highlighting;

import com.intellij.openapi.fileTypes.SingleLazyInstanceSyntaxHighlighterFactory;
import com.intellij.openapi.fileTypes.SyntaxHighlighter;
import org.eclipse.xtext.parsetree.reconstr.idea.lang.SerializationBug269362TestLanguageLanguage;
import org.jetbrains.annotations.NotNull;

public class SerializationBug269362TestLanguageSyntaxHighlighterFactory extends SingleLazyInstanceSyntaxHighlighterFactory {
	
	@Override
    @NotNull
    protected SyntaxHighlighter createHighlighter() {
        return SerializationBug269362TestLanguageLanguage.INSTANCE.getInstance(SyntaxHighlighter.class);
    }

}
