package org.eclipse.xtext.parsetree.transientvalues.idea.highlighting;

import com.intellij.openapi.fileTypes.SingleLazyInstanceSyntaxHighlighterFactory;
import com.intellij.openapi.fileTypes.SyntaxHighlighter;
import org.eclipse.xtext.parsetree.transientvalues.idea.lang.TransientValuesTestLanguage;
import org.jetbrains.annotations.NotNull;

public class TransientValuesTestSyntaxHighlighterFactory extends SingleLazyInstanceSyntaxHighlighterFactory {
	
	@Override
    @NotNull
    protected SyntaxHighlighter createHighlighter() {
        return TransientValuesTestLanguage.INSTANCE.getInstance(SyntaxHighlighter.class);
    }

}
