package org.eclipse.xtext.parser.assignments.idea.highlighting;

import com.intellij.openapi.fileTypes.SingleLazyInstanceSyntaxHighlighterFactory;
import com.intellij.openapi.fileTypes.SyntaxHighlighter;
import org.eclipse.xtext.parser.assignments.idea.lang.Bug287184TestLanguageLanguage;
import org.jetbrains.annotations.NotNull;

public class Bug287184TestLanguageSyntaxHighlighterFactory extends SingleLazyInstanceSyntaxHighlighterFactory {
	
	@Override
    @NotNull
    protected SyntaxHighlighter createHighlighter() {
        return Bug287184TestLanguageLanguage.INSTANCE.getInstance(SyntaxHighlighter.class);
    }

}
