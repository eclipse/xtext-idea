package org.eclipse.xtext.parsetree.reconstr.idea.highlighting;

import com.intellij.openapi.fileTypes.SingleLazyInstanceSyntaxHighlighterFactory;
import com.intellij.openapi.fileTypes.SyntaxHighlighter;
import org.eclipse.xtext.parsetree.reconstr.idea.lang.Bug299395TestLanguageLanguage;
import org.jetbrains.annotations.NotNull;

public class Bug299395TestLanguageSyntaxHighlighterFactory extends SingleLazyInstanceSyntaxHighlighterFactory {
	
	@Override
    @NotNull
    protected SyntaxHighlighter createHighlighter() {
        return Bug299395TestLanguageLanguage.INSTANCE.getInstance(SyntaxHighlighter.class);
    }

}
