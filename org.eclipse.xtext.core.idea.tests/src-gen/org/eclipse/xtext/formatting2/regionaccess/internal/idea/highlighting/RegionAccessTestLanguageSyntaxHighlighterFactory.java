package org.eclipse.xtext.formatting2.regionaccess.internal.idea.highlighting;

import com.intellij.openapi.fileTypes.SingleLazyInstanceSyntaxHighlighterFactory;
import com.intellij.openapi.fileTypes.SyntaxHighlighter;
import org.eclipse.xtext.formatting2.regionaccess.internal.idea.lang.RegionAccessTestLanguageLanguage;
import org.jetbrains.annotations.NotNull;

public class RegionAccessTestLanguageSyntaxHighlighterFactory extends SingleLazyInstanceSyntaxHighlighterFactory {
	
	@Override
    @NotNull
    protected SyntaxHighlighter createHighlighter() {
        return RegionAccessTestLanguageLanguage.INSTANCE.getInstance(SyntaxHighlighter.class);
    }

}
