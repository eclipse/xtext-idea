package org.eclipse.xtext.metamodelreferencing.tests.idea.highlighting;

import com.intellij.openapi.fileTypes.SingleLazyInstanceSyntaxHighlighterFactory;
import com.intellij.openapi.fileTypes.SyntaxHighlighter;
import org.eclipse.xtext.metamodelreferencing.tests.idea.lang.MetamodelRefTestLanguageLanguage;
import org.jetbrains.annotations.NotNull;

public class MetamodelRefTestLanguageSyntaxHighlighterFactory extends SingleLazyInstanceSyntaxHighlighterFactory {
	
	@Override
    @NotNull
    protected SyntaxHighlighter createHighlighter() {
        return MetamodelRefTestLanguageLanguage.INSTANCE.getInstance(SyntaxHighlighter.class);
    }

}
