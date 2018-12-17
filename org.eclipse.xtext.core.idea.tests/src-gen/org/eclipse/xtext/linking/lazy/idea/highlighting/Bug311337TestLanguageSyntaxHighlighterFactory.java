package org.eclipse.xtext.linking.lazy.idea.highlighting;

import com.intellij.openapi.fileTypes.SingleLazyInstanceSyntaxHighlighterFactory;
import com.intellij.openapi.fileTypes.SyntaxHighlighter;
import org.eclipse.xtext.linking.lazy.idea.lang.Bug311337TestLanguageLanguage;
import org.jetbrains.annotations.NotNull;

public class Bug311337TestLanguageSyntaxHighlighterFactory extends SingleLazyInstanceSyntaxHighlighterFactory {
	
	@Override
    @NotNull
    protected SyntaxHighlighter createHighlighter() {
        return Bug311337TestLanguageLanguage.INSTANCE.getInstance(SyntaxHighlighter.class);
    }

}
