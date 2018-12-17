package org.eclipse.xtext.parsetree.reconstr.idea.highlighting;

import com.intellij.openapi.fileTypes.SingleLazyInstanceSyntaxHighlighterFactory;
import com.intellij.openapi.fileTypes.SyntaxHighlighter;
import org.eclipse.xtext.parsetree.reconstr.idea.lang.SerializationErrorTestLanguageLanguage;
import org.jetbrains.annotations.NotNull;

public class SerializationErrorTestLanguageSyntaxHighlighterFactory extends SingleLazyInstanceSyntaxHighlighterFactory {
	
	@Override
    @NotNull
    protected SyntaxHighlighter createHighlighter() {
        return SerializationErrorTestLanguageLanguage.INSTANCE.getInstance(SyntaxHighlighter.class);
    }

}
