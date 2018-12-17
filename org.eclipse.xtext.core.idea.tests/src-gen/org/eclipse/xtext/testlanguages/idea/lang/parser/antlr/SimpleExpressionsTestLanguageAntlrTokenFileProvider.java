package org.eclipse.xtext.testlanguages.idea.lang.parser.antlr;

import org.eclipse.xtext.parser.antlr.IAntlrTokenFileProvider;

import java.io.InputStream;

public class SimpleExpressionsTestLanguageAntlrTokenFileProvider implements IAntlrTokenFileProvider {
	
	@Override
	public InputStream getAntlrTokenFile() {
		ClassLoader classLoader = getClass().getClassLoader();
    	return classLoader.getResourceAsStream("org/eclipse/xtext/testlanguages/idea/parser/antlr/internal/PsiInternalSimpleExpressionsTestLanguage.tokens");
	}
}
