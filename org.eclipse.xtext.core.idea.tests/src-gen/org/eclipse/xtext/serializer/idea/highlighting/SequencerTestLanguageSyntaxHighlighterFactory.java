package org.eclipse.xtext.serializer.idea.highlighting;

import com.intellij.openapi.fileTypes.SingleLazyInstanceSyntaxHighlighterFactory;
import com.intellij.openapi.fileTypes.SyntaxHighlighter;
import org.eclipse.xtext.serializer.idea.lang.SequencerTestLanguageLanguage;
import org.jetbrains.annotations.NotNull;

public class SequencerTestLanguageSyntaxHighlighterFactory extends SingleLazyInstanceSyntaxHighlighterFactory {
	
	@Override
    @NotNull
    protected SyntaxHighlighter createHighlighter() {
        return SequencerTestLanguageLanguage.INSTANCE.getInstance(SyntaxHighlighter.class);
    }

}
