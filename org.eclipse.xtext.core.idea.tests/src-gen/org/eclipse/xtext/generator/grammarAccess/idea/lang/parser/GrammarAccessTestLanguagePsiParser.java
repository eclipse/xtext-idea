package org.eclipse.xtext.generator.grammarAccess.idea.lang.parser;

import com.google.inject.Inject;
import com.intellij.lang.PsiBuilder;
import org.antlr.runtime.TokenStream;
import org.eclipse.xtext.generator.grammarAccess.idea.lang.GrammarAccessTestLanguageElementTypeProvider;
import org.eclipse.xtext.generator.grammarAccess.idea.parser.antlr.internal.PsiInternalGrammarAccessTestLanguageParser;
import org.eclipse.xtext.generator.grammarAccess.services.GrammarAccessTestLanguageGrammarAccess;
import org.eclipse.xtext.idea.parser.AbstractPsiAntlrParser;
import org.eclipse.xtext.idea.parser.AbstractXtextPsiParser;

import java.util.Arrays;
import java.util.HashSet;
import java.util.Set;

public class GrammarAccessTestLanguagePsiParser extends AbstractXtextPsiParser {

	private static final Set<String> INITIAL_HIDDEN_TOKENS = new HashSet<String>(Arrays.asList("RULE_WS", "RULE_ML_COMMENT", "RULE_SL_COMMENT"));

	@Inject 
	private GrammarAccessTestLanguageGrammarAccess grammarAccess;

	@Inject 
	private GrammarAccessTestLanguageElementTypeProvider elementTypeProvider;

	@Override
	protected AbstractPsiAntlrParser createParser(PsiBuilder builder, TokenStream tokenStream) {
		return new PsiInternalGrammarAccessTestLanguageParser(builder, tokenStream, elementTypeProvider, grammarAccess);
	}

	@Override
	protected Set<String> getInitialHiddenTokens() {
		return INITIAL_HIDDEN_TOKENS;
	}

}
