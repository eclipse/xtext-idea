package org.eclipse.xtext.idea.highlighting;

import com.intellij.openapi.fileTypes.SingleLazyInstanceSyntaxHighlighterFactory;
import com.intellij.openapi.fileTypes.SyntaxHighlighter;
import org.eclipse.xtext.idea.lang.XtextGrammarTestLanguageLanguage;
import org.jetbrains.annotations.NotNull;

public class XtextGrammarTestLanguageSyntaxHighlighterFactory extends SingleLazyInstanceSyntaxHighlighterFactory {
	
	@Override
    @NotNull
    protected SyntaxHighlighter createHighlighter() {
        return XtextGrammarTestLanguageLanguage.INSTANCE.getInstance(SyntaxHighlighter.class);
    }

}
