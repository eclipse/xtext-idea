package org.eclipse.xtext.resource.idea.highlighting;

import com.intellij.openapi.fileTypes.SingleLazyInstanceSyntaxHighlighterFactory;
import com.intellij.openapi.fileTypes.SyntaxHighlighter;
import org.eclipse.xtext.resource.idea.lang.Bug385636Language;
import org.jetbrains.annotations.NotNull;

public class Bug385636SyntaxHighlighterFactory extends SingleLazyInstanceSyntaxHighlighterFactory {
	
	@Override
    @NotNull
    protected SyntaxHighlighter createHighlighter() {
        return Bug385636Language.INSTANCE.getInstance(SyntaxHighlighter.class);
    }

}
