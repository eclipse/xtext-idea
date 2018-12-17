package org.eclipse.xtext.linking.idea.lang.parser;

import com.google.inject.Inject;
import com.intellij.lang.PsiBuilder;
import org.antlr.runtime.TokenStream;
import org.eclipse.xtext.idea.parser.AbstractPsiAntlrParser;
import org.eclipse.xtext.idea.parser.AbstractXtextPsiParser;
import org.eclipse.xtext.linking.idea.lang.Bug313089TestLanguageElementTypeProvider;
import org.eclipse.xtext.linking.idea.parser.antlr.internal.PsiInternalBug313089TestLanguageParser;
import org.eclipse.xtext.linking.services.Bug313089TestLanguageGrammarAccess;

import java.util.Arrays;
import java.util.HashSet;
import java.util.Set;

public class Bug313089TestLanguagePsiParser extends AbstractXtextPsiParser {

	private static final Set<String> INITIAL_HIDDEN_TOKENS = new HashSet<String>(Arrays.asList("RULE_WS", "RULE_ML_COMMENT", "RULE_SL_COMMENT"));

	@Inject 
	private Bug313089TestLanguageGrammarAccess grammarAccess;

	@Inject 
	private Bug313089TestLanguageElementTypeProvider elementTypeProvider;

	@Override
	protected AbstractPsiAntlrParser createParser(PsiBuilder builder, TokenStream tokenStream) {
		return new PsiInternalBug313089TestLanguageParser(builder, tokenStream, elementTypeProvider, grammarAccess);
	}

	@Override
	protected Set<String> getInitialHiddenTokens() {
		return INITIAL_HIDDEN_TOKENS;
	}

}
