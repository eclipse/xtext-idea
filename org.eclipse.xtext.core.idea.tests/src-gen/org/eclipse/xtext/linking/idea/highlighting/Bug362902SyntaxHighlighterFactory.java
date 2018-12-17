package org.eclipse.xtext.linking.idea.highlighting;

import com.intellij.openapi.fileTypes.SingleLazyInstanceSyntaxHighlighterFactory;
import com.intellij.openapi.fileTypes.SyntaxHighlighter;
import org.eclipse.xtext.linking.idea.lang.Bug362902Language;
import org.jetbrains.annotations.NotNull;

public class Bug362902SyntaxHighlighterFactory extends SingleLazyInstanceSyntaxHighlighterFactory {
	
	@Override
    @NotNull
    protected SyntaxHighlighter createHighlighter() {
        return Bug362902Language.INSTANCE.getInstance(SyntaxHighlighter.class);
    }

}
