package org.eclipse.xtext.parsetree.reconstr.idea.highlighting;

import com.intellij.openapi.fileTypes.SingleLazyInstanceSyntaxHighlighterFactory;
import com.intellij.openapi.fileTypes.SyntaxHighlighter;
import org.eclipse.xtext.parsetree.reconstr.idea.lang.Bug302128TestLanguageLanguage;
import org.jetbrains.annotations.NotNull;

public class Bug302128TestLanguageSyntaxHighlighterFactory extends SingleLazyInstanceSyntaxHighlighterFactory {
	
	@Override
    @NotNull
    protected SyntaxHighlighter createHighlighter() {
        return Bug302128TestLanguageLanguage.INSTANCE.getInstance(SyntaxHighlighter.class);
    }

}
