/*
 * generated by Xtext
 */
package org.eclipse.xtext.generator.ecore.idea.highlighting;

import com.intellij.openapi.fileTypes.SingleLazyInstanceSyntaxHighlighterFactory;
import com.intellij.openapi.fileTypes.SyntaxHighlighter;
import org.eclipse.xtext.generator.ecore.idea.lang.SuperTestLanguageLanguage;
import org.jetbrains.annotations.NotNull;

public class SuperTestLanguageSyntaxHighlighterFactory extends SingleLazyInstanceSyntaxHighlighterFactory {
	
	@Override
	@NotNull
	protected SyntaxHighlighter createHighlighter() {
		return SuperTestLanguageLanguage.INSTANCE.getInstance(SyntaxHighlighter.class);
	}
}
