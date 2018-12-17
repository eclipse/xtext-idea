package org.eclipse.xtext.parser.terminalrules.idea.highlighting;

import com.intellij.openapi.fileTypes.SingleLazyInstanceSyntaxHighlighterFactory;
import com.intellij.openapi.fileTypes.SyntaxHighlighter;
import org.eclipse.xtext.parser.terminalrules.idea.lang.Bug292245TestLanguageLanguage;
import org.jetbrains.annotations.NotNull;

public class Bug292245TestLanguageSyntaxHighlighterFactory extends SingleLazyInstanceSyntaxHighlighterFactory {
	
	@Override
    @NotNull
    protected SyntaxHighlighter createHighlighter() {
        return Bug292245TestLanguageLanguage.INSTANCE.getInstance(SyntaxHighlighter.class);
    }

}
