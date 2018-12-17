package org.eclipse.xtext.parsetree.impl.idea.highlighting;

import com.intellij.openapi.fileTypes.SingleLazyInstanceSyntaxHighlighterFactory;
import com.intellij.openapi.fileTypes.SyntaxHighlighter;
import org.eclipse.xtext.parsetree.impl.idea.lang.Bug305397Language;
import org.jetbrains.annotations.NotNull;

public class Bug305397SyntaxHighlighterFactory extends SingleLazyInstanceSyntaxHighlighterFactory {
	
	@Override
    @NotNull
    protected SyntaxHighlighter createHighlighter() {
        return Bug305397Language.INSTANCE.getInstance(SyntaxHighlighter.class);
    }

}
