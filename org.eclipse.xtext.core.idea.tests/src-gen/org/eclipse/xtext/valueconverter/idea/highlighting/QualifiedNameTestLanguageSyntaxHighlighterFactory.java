package org.eclipse.xtext.valueconverter.idea.highlighting;

import com.intellij.openapi.fileTypes.SingleLazyInstanceSyntaxHighlighterFactory;
import com.intellij.openapi.fileTypes.SyntaxHighlighter;
import org.eclipse.xtext.valueconverter.idea.lang.QualifiedNameTestLanguageLanguage;
import org.jetbrains.annotations.NotNull;

public class QualifiedNameTestLanguageSyntaxHighlighterFactory extends SingleLazyInstanceSyntaxHighlighterFactory {
	
	@Override
    @NotNull
    protected SyntaxHighlighter createHighlighter() {
        return QualifiedNameTestLanguageLanguage.INSTANCE.getInstance(SyntaxHighlighter.class);
    }

}
