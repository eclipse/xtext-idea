package org.eclipse.xtext.parser.unorderedGroups.idea.highlighting;

import com.intellij.openapi.fileTypes.SingleLazyInstanceSyntaxHighlighterFactory;
import com.intellij.openapi.fileTypes.SyntaxHighlighter;
import org.eclipse.xtext.parser.unorderedGroups.idea.lang.SimpleBacktrackingBug325745TestLanguageLanguage;
import org.jetbrains.annotations.NotNull;

public class SimpleBacktrackingBug325745TestLanguageSyntaxHighlighterFactory extends SingleLazyInstanceSyntaxHighlighterFactory {
	
	@Override
    @NotNull
    protected SyntaxHighlighter createHighlighter() {
        return SimpleBacktrackingBug325745TestLanguageLanguage.INSTANCE.getInstance(SyntaxHighlighter.class);
    }

}
