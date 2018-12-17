package org.eclipse.xtext.serializer.idea.highlighting;

import com.intellij.openapi.fileTypes.SingleLazyInstanceSyntaxHighlighterFactory;
import com.intellij.openapi.fileTypes.SyntaxHighlighter;
import org.eclipse.xtext.serializer.idea.lang.SyntacticSequencerTestLanguageLanguage;
import org.jetbrains.annotations.NotNull;

public class SyntacticSequencerTestLanguageSyntaxHighlighterFactory extends SingleLazyInstanceSyntaxHighlighterFactory {
	
	@Override
    @NotNull
    protected SyntaxHighlighter createHighlighter() {
        return SyntacticSequencerTestLanguageLanguage.INSTANCE.getInstance(SyntaxHighlighter.class);
    }

}
