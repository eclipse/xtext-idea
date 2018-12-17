package org.eclipse.xtext.idea.lang.parser.antlr;

import org.eclipse.xtext.parser.antlr.IAntlrTokenFileProvider;

import java.io.InputStream;

public class XtextGrammarTestLanguageAntlrTokenFileProvider implements IAntlrTokenFileProvider {
	
	@Override
	public InputStream getAntlrTokenFile() {
		ClassLoader classLoader = getClass().getClassLoader();
    	return classLoader.getResourceAsStream("org/eclipse/xtext/idea/parser/antlr/internal/PsiInternalXtextGrammarTestLanguage.tokens");
	}
}
