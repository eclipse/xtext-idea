package org.eclipse.xtext.parser.indentation.idea.highlighting;

import com.intellij.openapi.fileTypes.SingleLazyInstanceSyntaxHighlighterFactory;
import com.intellij.openapi.fileTypes.SyntaxHighlighter;
import org.eclipse.xtext.parser.indentation.idea.lang.IndentationAwareTestLanguageLanguage;
import org.jetbrains.annotations.NotNull;

public class IndentationAwareTestLanguageSyntaxHighlighterFactory extends SingleLazyInstanceSyntaxHighlighterFactory {
	
	@Override
    @NotNull
    protected SyntaxHighlighter createHighlighter() {
        return IndentationAwareTestLanguageLanguage.INSTANCE.getInstance(SyntaxHighlighter.class);
    }

}
