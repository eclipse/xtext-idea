package org.eclipse.xtext.testlanguages.idea.highlighting;

import com.intellij.openapi.fileTypes.SingleLazyInstanceSyntaxHighlighterFactory;
import com.intellij.openapi.fileTypes.SyntaxHighlighter;
import org.eclipse.xtext.testlanguages.idea.lang.ActionTestLanguageLanguage;
import org.jetbrains.annotations.NotNull;

public class ActionTestLanguageSyntaxHighlighterFactory extends SingleLazyInstanceSyntaxHighlighterFactory {
	
	@Override
    @NotNull
    protected SyntaxHighlighter createHighlighter() {
        return ActionTestLanguageLanguage.INSTANCE.getInstance(SyntaxHighlighter.class);
    }

}
