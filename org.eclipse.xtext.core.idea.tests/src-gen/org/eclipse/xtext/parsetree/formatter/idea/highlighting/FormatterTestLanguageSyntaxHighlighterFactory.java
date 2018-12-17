package org.eclipse.xtext.parsetree.formatter.idea.highlighting;

import com.intellij.openapi.fileTypes.SingleLazyInstanceSyntaxHighlighterFactory;
import com.intellij.openapi.fileTypes.SyntaxHighlighter;
import org.eclipse.xtext.parsetree.formatter.idea.lang.FormatterTestLanguageLanguage;
import org.jetbrains.annotations.NotNull;

public class FormatterTestLanguageSyntaxHighlighterFactory extends SingleLazyInstanceSyntaxHighlighterFactory {
	
	@Override
    @NotNull
    protected SyntaxHighlighter createHighlighter() {
        return FormatterTestLanguageLanguage.INSTANCE.getInstance(SyntaxHighlighter.class);
    }

}
