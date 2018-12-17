package org.eclipse.xtext.resource.idea.highlighting;

import com.intellij.openapi.fileTypes.SingleLazyInstanceSyntaxHighlighterFactory;
import com.intellij.openapi.fileTypes.SyntaxHighlighter;
import org.eclipse.xtext.resource.idea.lang.EObjectAtOffsetTestLanguageLanguage;
import org.jetbrains.annotations.NotNull;

public class EObjectAtOffsetTestLanguageSyntaxHighlighterFactory extends SingleLazyInstanceSyntaxHighlighterFactory {
	
	@Override
    @NotNull
    protected SyntaxHighlighter createHighlighter() {
        return EObjectAtOffsetTestLanguageLanguage.INSTANCE.getInstance(SyntaxHighlighter.class);
    }

}
