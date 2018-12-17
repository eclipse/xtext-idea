package org.eclipse.xtext.testlanguages.idea.highlighting;

import com.intellij.openapi.fileTypes.SingleLazyInstanceSyntaxHighlighterFactory;
import com.intellij.openapi.fileTypes.SyntaxHighlighter;
import org.eclipse.xtext.testlanguages.idea.lang.ActionTestLanguage2Language;
import org.jetbrains.annotations.NotNull;

public class ActionTestLanguage2SyntaxHighlighterFactory extends SingleLazyInstanceSyntaxHighlighterFactory {
	
	@Override
    @NotNull
    protected SyntaxHighlighter createHighlighter() {
        return ActionTestLanguage2Language.INSTANCE.getInstance(SyntaxHighlighter.class);
    }

}
