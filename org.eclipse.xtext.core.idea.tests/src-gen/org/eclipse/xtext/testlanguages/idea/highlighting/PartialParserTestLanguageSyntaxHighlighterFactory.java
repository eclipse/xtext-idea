package org.eclipse.xtext.testlanguages.idea.highlighting;

import com.intellij.openapi.fileTypes.SingleLazyInstanceSyntaxHighlighterFactory;
import com.intellij.openapi.fileTypes.SyntaxHighlighter;
import org.eclipse.xtext.testlanguages.idea.lang.PartialParserTestLanguageLanguage;
import org.jetbrains.annotations.NotNull;

public class PartialParserTestLanguageSyntaxHighlighterFactory extends SingleLazyInstanceSyntaxHighlighterFactory {
	
	@Override
    @NotNull
    protected SyntaxHighlighter createHighlighter() {
        return PartialParserTestLanguageLanguage.INSTANCE.getInstance(SyntaxHighlighter.class);
    }

}
