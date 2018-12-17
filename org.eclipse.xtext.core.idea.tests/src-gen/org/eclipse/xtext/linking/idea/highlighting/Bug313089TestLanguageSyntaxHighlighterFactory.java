package org.eclipse.xtext.linking.idea.highlighting;

import com.intellij.openapi.fileTypes.SingleLazyInstanceSyntaxHighlighterFactory;
import com.intellij.openapi.fileTypes.SyntaxHighlighter;
import org.eclipse.xtext.linking.idea.lang.Bug313089TestLanguageLanguage;
import org.jetbrains.annotations.NotNull;

public class Bug313089TestLanguageSyntaxHighlighterFactory extends SingleLazyInstanceSyntaxHighlighterFactory {
	
	@Override
    @NotNull
    protected SyntaxHighlighter createHighlighter() {
        return Bug313089TestLanguageLanguage.INSTANCE.getInstance(SyntaxHighlighter.class);
    }

}
