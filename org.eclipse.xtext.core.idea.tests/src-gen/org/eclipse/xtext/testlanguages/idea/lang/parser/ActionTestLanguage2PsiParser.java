package org.eclipse.xtext.testlanguages.idea.lang.parser;

import com.google.inject.Inject;
import com.intellij.lang.PsiBuilder;
import org.antlr.runtime.TokenStream;
import org.eclipse.xtext.idea.parser.AbstractPsiAntlrParser;
import org.eclipse.xtext.idea.parser.AbstractXtextPsiParser;
import org.eclipse.xtext.testlanguages.idea.lang.ActionTestLanguage2ElementTypeProvider;
import org.eclipse.xtext.testlanguages.idea.parser.antlr.internal.PsiInternalActionTestLanguage2Parser;
import org.eclipse.xtext.testlanguages.services.ActionTestLanguage2GrammarAccess;

import java.util.Arrays;
import java.util.HashSet;
import java.util.Set;

public class ActionTestLanguage2PsiParser extends AbstractXtextPsiParser {

	private static final Set<String> INITIAL_HIDDEN_TOKENS = new HashSet<String>(Arrays.asList("RULE_WS", "RULE_ML_COMMENT", "RULE_SL_COMMENT"));

	@Inject 
	private ActionTestLanguage2GrammarAccess grammarAccess;

	@Inject 
	private ActionTestLanguage2ElementTypeProvider elementTypeProvider;

	@Override
	protected AbstractPsiAntlrParser createParser(PsiBuilder builder, TokenStream tokenStream) {
		return new PsiInternalActionTestLanguage2Parser(builder, tokenStream, elementTypeProvider, grammarAccess);
	}

	@Override
	protected Set<String> getInitialHiddenTokens() {
		return INITIAL_HIDDEN_TOKENS;
	}

}
