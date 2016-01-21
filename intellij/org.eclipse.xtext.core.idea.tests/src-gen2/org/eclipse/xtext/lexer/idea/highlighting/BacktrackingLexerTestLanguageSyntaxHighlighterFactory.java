/*
 * generated by Xtext
 */
package org.eclipse.xtext.lexer.idea.highlighting;

import com.intellij.openapi.fileTypes.SingleLazyInstanceSyntaxHighlighterFactory;
import com.intellij.openapi.fileTypes.SyntaxHighlighter;
import org.eclipse.xtext.lexer.idea.lang.BacktrackingLexerTestLanguageLanguage;
import org.jetbrains.annotations.NotNull;

public class BacktrackingLexerTestLanguageSyntaxHighlighterFactory extends SingleLazyInstanceSyntaxHighlighterFactory {
	
	@Override
	@NotNull
	protected SyntaxHighlighter createHighlighter() {
		return BacktrackingLexerTestLanguageLanguage.INSTANCE.getInstance(SyntaxHighlighter.class);
	}
}
