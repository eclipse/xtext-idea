package org.eclipse.xtext.enumrules.idea.lang.parser.antlr;

import org.eclipse.xtext.parser.antlr.IAntlrTokenFileProvider;

import java.io.InputStream;

public class EnumAndReferenceTestLanguageAntlrTokenFileProvider implements IAntlrTokenFileProvider {
	
	@Override
	public InputStream getAntlrTokenFile() {
		ClassLoader classLoader = getClass().getClassLoader();
    	return classLoader.getResourceAsStream("org/eclipse/xtext/enumrules/idea/parser/antlr/internal/PsiInternalEnumAndReferenceTestLanguage.tokens");
	}
}
