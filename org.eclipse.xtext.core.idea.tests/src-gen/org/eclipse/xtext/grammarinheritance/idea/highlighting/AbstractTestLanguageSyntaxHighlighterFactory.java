package org.eclipse.xtext.grammarinheritance.idea.highlighting;

import com.intellij.openapi.fileTypes.SingleLazyInstanceSyntaxHighlighterFactory;
import com.intellij.openapi.fileTypes.SyntaxHighlighter;
import org.eclipse.xtext.grammarinheritance.idea.lang.AbstractTestLanguageLanguage;
import org.jetbrains.annotations.NotNull;

public class AbstractTestLanguageSyntaxHighlighterFactory extends SingleLazyInstanceSyntaxHighlighterFactory {
	
	@Override
    @NotNull
    protected SyntaxHighlighter createHighlighter() {
        return AbstractTestLanguageLanguage.INSTANCE.getInstance(SyntaxHighlighter.class);
    }

}
