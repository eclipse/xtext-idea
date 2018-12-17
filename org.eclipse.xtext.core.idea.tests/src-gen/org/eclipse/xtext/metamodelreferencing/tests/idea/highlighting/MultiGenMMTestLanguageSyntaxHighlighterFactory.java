package org.eclipse.xtext.metamodelreferencing.tests.idea.highlighting;

import com.intellij.openapi.fileTypes.SingleLazyInstanceSyntaxHighlighterFactory;
import com.intellij.openapi.fileTypes.SyntaxHighlighter;
import org.eclipse.xtext.metamodelreferencing.tests.idea.lang.MultiGenMMTestLanguageLanguage;
import org.jetbrains.annotations.NotNull;

public class MultiGenMMTestLanguageSyntaxHighlighterFactory extends SingleLazyInstanceSyntaxHighlighterFactory {
	
	@Override
    @NotNull
    protected SyntaxHighlighter createHighlighter() {
        return MultiGenMMTestLanguageLanguage.INSTANCE.getInstance(SyntaxHighlighter.class);
    }

}
