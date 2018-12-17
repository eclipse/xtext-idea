package org.eclipse.xtext.parser.bug419429.idea.highlighting;

import com.intellij.openapi.fileTypes.SingleLazyInstanceSyntaxHighlighterFactory;
import com.intellij.openapi.fileTypes.SyntaxHighlighter;
import org.eclipse.xtext.parser.bug419429.idea.lang.Bug419429Language;
import org.jetbrains.annotations.NotNull;

public class Bug419429SyntaxHighlighterFactory extends SingleLazyInstanceSyntaxHighlighterFactory {
	
	@Override
    @NotNull
    protected SyntaxHighlighter createHighlighter() {
        return Bug419429Language.INSTANCE.getInstance(SyntaxHighlighter.class);
    }

}
