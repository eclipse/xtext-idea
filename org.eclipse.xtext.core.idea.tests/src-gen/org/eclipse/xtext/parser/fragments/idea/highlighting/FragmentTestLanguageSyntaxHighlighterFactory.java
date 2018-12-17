package org.eclipse.xtext.parser.fragments.idea.highlighting;

import com.intellij.openapi.fileTypes.SingleLazyInstanceSyntaxHighlighterFactory;
import com.intellij.openapi.fileTypes.SyntaxHighlighter;
import org.eclipse.xtext.parser.fragments.idea.lang.FragmentTestLanguageLanguage;
import org.jetbrains.annotations.NotNull;

public class FragmentTestLanguageSyntaxHighlighterFactory extends SingleLazyInstanceSyntaxHighlighterFactory {
	
	@Override
    @NotNull
    protected SyntaxHighlighter createHighlighter() {
        return FragmentTestLanguageLanguage.INSTANCE.getInstance(SyntaxHighlighter.class);
    }

}
