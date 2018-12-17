package org.eclipse.xtext.parser.epatch.idea.highlighting;

import com.intellij.openapi.fileTypes.SingleLazyInstanceSyntaxHighlighterFactory;
import com.intellij.openapi.fileTypes.SyntaxHighlighter;
import org.eclipse.xtext.parser.epatch.idea.lang.EpatchTestLanguageLanguage;
import org.jetbrains.annotations.NotNull;

public class EpatchTestLanguageSyntaxHighlighterFactory extends SingleLazyInstanceSyntaxHighlighterFactory {
	
	@Override
    @NotNull
    protected SyntaxHighlighter createHighlighter() {
        return EpatchTestLanguageLanguage.INSTANCE.getInstance(SyntaxHighlighter.class);
    }

}
