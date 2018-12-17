package org.eclipse.xtext.linking.idea.highlighting;

import com.intellij.openapi.fileTypes.SingleLazyInstanceSyntaxHighlighterFactory;
import com.intellij.openapi.fileTypes.SyntaxHighlighter;
import org.eclipse.xtext.linking.idea.lang.ImportUriTestLanguageLanguage;
import org.jetbrains.annotations.NotNull;

public class ImportUriTestLanguageSyntaxHighlighterFactory extends SingleLazyInstanceSyntaxHighlighterFactory {
	
	@Override
    @NotNull
    protected SyntaxHighlighter createHighlighter() {
        return ImportUriTestLanguageLanguage.INSTANCE.getInstance(SyntaxHighlighter.class);
    }

}
