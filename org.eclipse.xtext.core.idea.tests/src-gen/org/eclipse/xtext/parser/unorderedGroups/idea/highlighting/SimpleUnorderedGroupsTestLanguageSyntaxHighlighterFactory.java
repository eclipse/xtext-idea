package org.eclipse.xtext.parser.unorderedGroups.idea.highlighting;

import com.intellij.openapi.fileTypes.SingleLazyInstanceSyntaxHighlighterFactory;
import com.intellij.openapi.fileTypes.SyntaxHighlighter;
import org.eclipse.xtext.parser.unorderedGroups.idea.lang.SimpleUnorderedGroupsTestLanguageLanguage;
import org.jetbrains.annotations.NotNull;

public class SimpleUnorderedGroupsTestLanguageSyntaxHighlighterFactory extends SingleLazyInstanceSyntaxHighlighterFactory {
	
	@Override
    @NotNull
    protected SyntaxHighlighter createHighlighter() {
        return SimpleUnorderedGroupsTestLanguageLanguage.INSTANCE.getInstance(SyntaxHighlighter.class);
    }

}
