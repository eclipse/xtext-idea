package org.eclipse.xtext.parser.fragments.idea.highlighting;

import com.intellij.openapi.fileTypes.SingleLazyInstanceSyntaxHighlighterFactory;
import com.intellij.openapi.fileTypes.SyntaxHighlighter;
import org.eclipse.xtext.parser.fragments.idea.lang.FragmentTestLanguageExLanguage;
import org.jetbrains.annotations.NotNull;

public class FragmentTestLanguageExSyntaxHighlighterFactory extends SingleLazyInstanceSyntaxHighlighterFactory {
	
	@Override
    @NotNull
    protected SyntaxHighlighter createHighlighter() {
        return FragmentTestLanguageExLanguage.INSTANCE.getInstance(SyntaxHighlighter.class);
    }

}
