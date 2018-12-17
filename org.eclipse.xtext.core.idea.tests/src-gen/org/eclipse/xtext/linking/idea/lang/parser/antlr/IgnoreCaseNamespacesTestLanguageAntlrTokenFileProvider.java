package org.eclipse.xtext.linking.idea.lang.parser.antlr;

import org.eclipse.xtext.parser.antlr.IAntlrTokenFileProvider;

import java.io.InputStream;

public class IgnoreCaseNamespacesTestLanguageAntlrTokenFileProvider implements IAntlrTokenFileProvider {
	
	@Override
	public InputStream getAntlrTokenFile() {
		ClassLoader classLoader = getClass().getClassLoader();
    	return classLoader.getResourceAsStream("org/eclipse/xtext/linking/idea/parser/antlr/internal/PsiInternalIgnoreCaseNamespacesTestLanguage.tokens");
	}
}
