package org.eclipse.xtext.parsetree.unassignedtext.idea.highlighting;

import com.intellij.openapi.fileTypes.SingleLazyInstanceSyntaxHighlighterFactory;
import com.intellij.openapi.fileTypes.SyntaxHighlighter;
import org.eclipse.xtext.parsetree.unassignedtext.idea.lang.UnassignedTextTestLanguageLanguage;
import org.jetbrains.annotations.NotNull;

public class UnassignedTextTestLanguageSyntaxHighlighterFactory extends SingleLazyInstanceSyntaxHighlighterFactory {
	
	@Override
    @NotNull
    protected SyntaxHighlighter createHighlighter() {
        return UnassignedTextTestLanguageLanguage.INSTANCE.getInstance(SyntaxHighlighter.class);
    }

}
