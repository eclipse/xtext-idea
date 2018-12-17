package org.eclipse.xtext.dummy.idea.highlighting;

import com.intellij.openapi.fileTypes.SingleLazyInstanceSyntaxHighlighterFactory;
import com.intellij.openapi.fileTypes.SyntaxHighlighter;
import org.eclipse.xtext.dummy.idea.lang.DummyTestLanguageLanguage;
import org.jetbrains.annotations.NotNull;

public class DummyTestLanguageSyntaxHighlighterFactory extends SingleLazyInstanceSyntaxHighlighterFactory {
	
	@Override
    @NotNull
    protected SyntaxHighlighter createHighlighter() {
        return DummyTestLanguageLanguage.INSTANCE.getInstance(SyntaxHighlighter.class);
    }

}
