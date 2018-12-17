package org.eclipse.xtext.valueconverter.idea.highlighting;

import com.intellij.openapi.fileTypes.SingleLazyInstanceSyntaxHighlighterFactory;
import com.intellij.openapi.fileTypes.SyntaxHighlighter;
import org.eclipse.xtext.valueconverter.idea.lang.Bug250313Language;
import org.jetbrains.annotations.NotNull;

public class Bug250313SyntaxHighlighterFactory extends SingleLazyInstanceSyntaxHighlighterFactory {
	
	@Override
    @NotNull
    protected SyntaxHighlighter createHighlighter() {
        return Bug250313Language.INSTANCE.getInstance(SyntaxHighlighter.class);
    }

}
