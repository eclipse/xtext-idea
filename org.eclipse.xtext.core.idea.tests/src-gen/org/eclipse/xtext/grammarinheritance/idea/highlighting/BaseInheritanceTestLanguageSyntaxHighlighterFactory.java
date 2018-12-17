package org.eclipse.xtext.grammarinheritance.idea.highlighting;

import com.intellij.openapi.fileTypes.SingleLazyInstanceSyntaxHighlighterFactory;
import com.intellij.openapi.fileTypes.SyntaxHighlighter;
import org.eclipse.xtext.grammarinheritance.idea.lang.BaseInheritanceTestLanguageLanguage;
import org.jetbrains.annotations.NotNull;

public class BaseInheritanceTestLanguageSyntaxHighlighterFactory extends SingleLazyInstanceSyntaxHighlighterFactory {
	
	@Override
    @NotNull
    protected SyntaxHighlighter createHighlighter() {
        return BaseInheritanceTestLanguageLanguage.INSTANCE.getInstance(SyntaxHighlighter.class);
    }

}
