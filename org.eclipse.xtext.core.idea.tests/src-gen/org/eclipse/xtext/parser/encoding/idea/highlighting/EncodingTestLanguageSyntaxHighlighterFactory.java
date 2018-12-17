package org.eclipse.xtext.parser.encoding.idea.highlighting;

import com.intellij.openapi.fileTypes.SingleLazyInstanceSyntaxHighlighterFactory;
import com.intellij.openapi.fileTypes.SyntaxHighlighter;
import org.eclipse.xtext.parser.encoding.idea.lang.EncodingTestLanguageLanguage;
import org.jetbrains.annotations.NotNull;

public class EncodingTestLanguageSyntaxHighlighterFactory extends SingleLazyInstanceSyntaxHighlighterFactory {
	
	@Override
    @NotNull
    protected SyntaxHighlighter createHighlighter() {
        return EncodingTestLanguageLanguage.INSTANCE.getInstance(SyntaxHighlighter.class);
    }

}
