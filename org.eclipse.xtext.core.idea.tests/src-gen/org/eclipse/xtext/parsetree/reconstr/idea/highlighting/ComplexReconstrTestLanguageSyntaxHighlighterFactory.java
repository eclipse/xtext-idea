package org.eclipse.xtext.parsetree.reconstr.idea.highlighting;

import com.intellij.openapi.fileTypes.SingleLazyInstanceSyntaxHighlighterFactory;
import com.intellij.openapi.fileTypes.SyntaxHighlighter;
import org.eclipse.xtext.parsetree.reconstr.idea.lang.ComplexReconstrTestLanguageLanguage;
import org.jetbrains.annotations.NotNull;

public class ComplexReconstrTestLanguageSyntaxHighlighterFactory extends SingleLazyInstanceSyntaxHighlighterFactory {
	
	@Override
    @NotNull
    protected SyntaxHighlighter createHighlighter() {
        return ComplexReconstrTestLanguageLanguage.INSTANCE.getInstance(SyntaxHighlighter.class);
    }

}
