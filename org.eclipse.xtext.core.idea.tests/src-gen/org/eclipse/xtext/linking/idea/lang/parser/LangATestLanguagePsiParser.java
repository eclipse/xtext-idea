package org.eclipse.xtext.linking.idea.lang.parser;

import com.google.inject.Inject;
import com.intellij.lang.PsiBuilder;
import org.antlr.runtime.TokenStream;
import org.eclipse.xtext.idea.parser.AbstractPsiAntlrParser;
import org.eclipse.xtext.idea.parser.AbstractXtextPsiParser;
import org.eclipse.xtext.linking.idea.lang.LangATestLanguageElementTypeProvider;
import org.eclipse.xtext.linking.idea.parser.antlr.internal.PsiInternalLangATestLanguageParser;
import org.eclipse.xtext.linking.services.LangATestLanguageGrammarAccess;

import java.util.Arrays;
import java.util.HashSet;
import java.util.Set;

public class LangATestLanguagePsiParser extends AbstractXtextPsiParser {

	private static final Set<String> INITIAL_HIDDEN_TOKENS = new HashSet<String>(Arrays.asList("RULE_WS", "RULE_ML_COMMENT", "RULE_SL_COMMENT"));

	@Inject 
	private LangATestLanguageGrammarAccess grammarAccess;

	@Inject 
	private LangATestLanguageElementTypeProvider elementTypeProvider;

	@Override
	protected AbstractPsiAntlrParser createParser(PsiBuilder builder, TokenStream tokenStream) {
		return new PsiInternalLangATestLanguageParser(builder, tokenStream, elementTypeProvider, grammarAccess);
	}

	@Override
	protected Set<String> getInitialHiddenTokens() {
		return INITIAL_HIDDEN_TOKENS;
	}

}
