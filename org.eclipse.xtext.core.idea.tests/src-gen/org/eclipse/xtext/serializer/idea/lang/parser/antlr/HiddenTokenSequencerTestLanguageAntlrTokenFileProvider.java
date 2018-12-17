package org.eclipse.xtext.serializer.idea.lang.parser.antlr;

import org.eclipse.xtext.parser.antlr.IAntlrTokenFileProvider;

import java.io.InputStream;

public class HiddenTokenSequencerTestLanguageAntlrTokenFileProvider implements IAntlrTokenFileProvider {
	
	@Override
	public InputStream getAntlrTokenFile() {
		ClassLoader classLoader = getClass().getClassLoader();
    	return classLoader.getResourceAsStream("org/eclipse/xtext/serializer/idea/parser/antlr/internal/PsiInternalHiddenTokenSequencerTestLanguage.tokens");
	}
}
