package org.eclipse.xtext.parsetree.impl.idea.highlighting;

import com.intellij.openapi.fileTypes.SingleLazyInstanceSyntaxHighlighterFactory;
import com.intellij.openapi.fileTypes.SyntaxHighlighter;
import org.eclipse.xtext.parsetree.impl.idea.lang.CommentAssociationTestLanguageLanguage;
import org.jetbrains.annotations.NotNull;

public class CommentAssociationTestLanguageSyntaxHighlighterFactory extends SingleLazyInstanceSyntaxHighlighterFactory {
	
	@Override
    @NotNull
    protected SyntaxHighlighter createHighlighter() {
        return CommentAssociationTestLanguageLanguage.INSTANCE.getInstance(SyntaxHighlighter.class);
    }

}
