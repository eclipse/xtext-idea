package org.eclipse.xtext.enumrules.idea.highlighting;

import com.intellij.openapi.fileTypes.SingleLazyInstanceSyntaxHighlighterFactory;
import com.intellij.openapi.fileTypes.SyntaxHighlighter;
import org.eclipse.xtext.enumrules.idea.lang.EnumAndReferenceTestLanguageLanguage;
import org.jetbrains.annotations.NotNull;

public class EnumAndReferenceTestLanguageSyntaxHighlighterFactory extends SingleLazyInstanceSyntaxHighlighterFactory {
	
	@Override
    @NotNull
    protected SyntaxHighlighter createHighlighter() {
        return EnumAndReferenceTestLanguageLanguage.INSTANCE.getInstance(SyntaxHighlighter.class);
    }

}
