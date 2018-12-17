package org.eclipse.xtext.generator.ecore.idea.highlighting;

import com.intellij.openapi.fileTypes.SingleLazyInstanceSyntaxHighlighterFactory;
import com.intellij.openapi.fileTypes.SyntaxHighlighter;
import org.eclipse.xtext.generator.ecore.idea.lang.EcoreFragmentTestLanguageLanguage;
import org.jetbrains.annotations.NotNull;

public class EcoreFragmentTestLanguageSyntaxHighlighterFactory extends SingleLazyInstanceSyntaxHighlighterFactory {
	
	@Override
    @NotNull
    protected SyntaxHighlighter createHighlighter() {
        return EcoreFragmentTestLanguageLanguage.INSTANCE.getInstance(SyntaxHighlighter.class);
    }

}
