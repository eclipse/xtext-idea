package org.eclipse.xtext.grammarinheritance.idea.highlighting;

import com.intellij.openapi.fileTypes.SingleLazyInstanceSyntaxHighlighterFactory;
import com.intellij.openapi.fileTypes.SyntaxHighlighter;
import org.eclipse.xtext.grammarinheritance.idea.lang.InheritanceTestLanguageLanguage;
import org.jetbrains.annotations.NotNull;

public class InheritanceTestLanguageSyntaxHighlighterFactory extends SingleLazyInstanceSyntaxHighlighterFactory {
	
	@Override
    @NotNull
    protected SyntaxHighlighter createHighlighter() {
        return InheritanceTestLanguageLanguage.INSTANCE.getInstance(SyntaxHighlighter.class);
    }

}
