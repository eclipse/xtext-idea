package org.eclipse.xtext.grammarinheritance.idea.highlighting;

import com.intellij.openapi.fileTypes.SingleLazyInstanceSyntaxHighlighterFactory;
import com.intellij.openapi.fileTypes.SyntaxHighlighter;
import org.eclipse.xtext.grammarinheritance.idea.lang.InheritanceTest3LanguageLanguage;
import org.jetbrains.annotations.NotNull;

public class InheritanceTest3LanguageSyntaxHighlighterFactory extends SingleLazyInstanceSyntaxHighlighterFactory {
	
	@Override
    @NotNull
    protected SyntaxHighlighter createHighlighter() {
        return InheritanceTest3LanguageLanguage.INSTANCE.getInstance(SyntaxHighlighter.class);
    }

}
