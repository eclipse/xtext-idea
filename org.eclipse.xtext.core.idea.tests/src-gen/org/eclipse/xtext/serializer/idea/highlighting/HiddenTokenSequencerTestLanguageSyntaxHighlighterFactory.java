package org.eclipse.xtext.serializer.idea.highlighting;

import com.intellij.openapi.fileTypes.SingleLazyInstanceSyntaxHighlighterFactory;
import com.intellij.openapi.fileTypes.SyntaxHighlighter;
import org.eclipse.xtext.serializer.idea.lang.HiddenTokenSequencerTestLanguageLanguage;
import org.jetbrains.annotations.NotNull;

public class HiddenTokenSequencerTestLanguageSyntaxHighlighterFactory extends SingleLazyInstanceSyntaxHighlighterFactory {
	
	@Override
    @NotNull
    protected SyntaxHighlighter createHighlighter() {
        return HiddenTokenSequencerTestLanguageLanguage.INSTANCE.getInstance(SyntaxHighlighter.class);
    }

}
