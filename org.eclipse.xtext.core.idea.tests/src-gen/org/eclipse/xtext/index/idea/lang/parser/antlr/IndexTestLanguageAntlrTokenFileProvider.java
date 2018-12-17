package org.eclipse.xtext.index.idea.lang.parser.antlr;

import org.eclipse.xtext.parser.antlr.IAntlrTokenFileProvider;

import java.io.InputStream;

public class IndexTestLanguageAntlrTokenFileProvider implements IAntlrTokenFileProvider {
	
	@Override
	public InputStream getAntlrTokenFile() {
		ClassLoader classLoader = getClass().getClassLoader();
    	return classLoader.getResourceAsStream("org/eclipse/xtext/index/idea/parser/antlr/internal/PsiInternalIndexTestLanguage.tokens");
	}
}
