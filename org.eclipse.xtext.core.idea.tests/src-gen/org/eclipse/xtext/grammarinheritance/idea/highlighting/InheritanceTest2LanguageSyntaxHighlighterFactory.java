package org.eclipse.xtext.grammarinheritance.idea.highlighting;

import com.intellij.openapi.fileTypes.SingleLazyInstanceSyntaxHighlighterFactory;
import com.intellij.openapi.fileTypes.SyntaxHighlighter;
import org.eclipse.xtext.grammarinheritance.idea.lang.InheritanceTest2LanguageLanguage;
import org.jetbrains.annotations.NotNull;

public class InheritanceTest2LanguageSyntaxHighlighterFactory extends SingleLazyInstanceSyntaxHighlighterFactory {
	
	@Override
    @NotNull
    protected SyntaxHighlighter createHighlighter() {
        return InheritanceTest2LanguageLanguage.INSTANCE.getInstance(SyntaxHighlighter.class);
    }

}
