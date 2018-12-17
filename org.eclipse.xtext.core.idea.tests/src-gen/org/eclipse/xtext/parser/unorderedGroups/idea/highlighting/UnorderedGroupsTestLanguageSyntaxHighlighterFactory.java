package org.eclipse.xtext.parser.unorderedGroups.idea.highlighting;

import com.intellij.openapi.fileTypes.SingleLazyInstanceSyntaxHighlighterFactory;
import com.intellij.openapi.fileTypes.SyntaxHighlighter;
import org.eclipse.xtext.parser.unorderedGroups.idea.lang.UnorderedGroupsTestLanguageLanguage;
import org.jetbrains.annotations.NotNull;

public class UnorderedGroupsTestLanguageSyntaxHighlighterFactory extends SingleLazyInstanceSyntaxHighlighterFactory {
	
	@Override
    @NotNull
    protected SyntaxHighlighter createHighlighter() {
        return UnorderedGroupsTestLanguageLanguage.INSTANCE.getInstance(SyntaxHighlighter.class);
    }

}
