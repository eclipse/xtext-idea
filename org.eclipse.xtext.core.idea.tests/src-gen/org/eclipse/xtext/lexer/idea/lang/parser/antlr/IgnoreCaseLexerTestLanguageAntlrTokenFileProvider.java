package org.eclipse.xtext.lexer.idea.lang.parser.antlr;

import org.eclipse.xtext.parser.antlr.IAntlrTokenFileProvider;

import java.io.InputStream;

public class IgnoreCaseLexerTestLanguageAntlrTokenFileProvider implements IAntlrTokenFileProvider {
	
	@Override
	public InputStream getAntlrTokenFile() {
		ClassLoader classLoader = getClass().getClassLoader();
    	return classLoader.getResourceAsStream("org/eclipse/xtext/lexer/idea/parser/antlr/internal/PsiInternalIgnoreCaseLexerTestLanguage.tokens");
	}
}
