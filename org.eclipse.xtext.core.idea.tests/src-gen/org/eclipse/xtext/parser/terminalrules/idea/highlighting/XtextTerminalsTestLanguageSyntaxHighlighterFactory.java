package org.eclipse.xtext.parser.terminalrules.idea.highlighting;

import com.intellij.openapi.fileTypes.SingleLazyInstanceSyntaxHighlighterFactory;
import com.intellij.openapi.fileTypes.SyntaxHighlighter;
import org.eclipse.xtext.parser.terminalrules.idea.lang.XtextTerminalsTestLanguageLanguage;
import org.jetbrains.annotations.NotNull;

public class XtextTerminalsTestLanguageSyntaxHighlighterFactory extends SingleLazyInstanceSyntaxHighlighterFactory {
	
	@Override
    @NotNull
    protected SyntaxHighlighter createHighlighter() {
        return XtextTerminalsTestLanguageLanguage.INSTANCE.getInstance(SyntaxHighlighter.class);
    }

}
