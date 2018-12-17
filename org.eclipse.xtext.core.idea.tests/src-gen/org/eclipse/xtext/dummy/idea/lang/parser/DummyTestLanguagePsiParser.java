package org.eclipse.xtext.dummy.idea.lang.parser;

import com.google.inject.Inject;
import com.intellij.lang.PsiBuilder;
import org.antlr.runtime.TokenStream;
import org.eclipse.xtext.dummy.idea.lang.DummyTestLanguageElementTypeProvider;
import org.eclipse.xtext.dummy.idea.parser.antlr.internal.PsiInternalDummyTestLanguageParser;
import org.eclipse.xtext.dummy.services.DummyTestLanguageGrammarAccess;
import org.eclipse.xtext.idea.parser.AbstractPsiAntlrParser;
import org.eclipse.xtext.idea.parser.AbstractXtextPsiParser;

import java.util.Arrays;
import java.util.HashSet;
import java.util.Set;

public class DummyTestLanguagePsiParser extends AbstractXtextPsiParser {

	private static final Set<String> INITIAL_HIDDEN_TOKENS = new HashSet<String>(Arrays.asList("RULE_WS", "RULE_ML_COMMENT", "RULE_SL_COMMENT"));

	@Inject 
	private DummyTestLanguageGrammarAccess grammarAccess;

	@Inject 
	private DummyTestLanguageElementTypeProvider elementTypeProvider;

	@Override
	protected AbstractPsiAntlrParser createParser(PsiBuilder builder, TokenStream tokenStream) {
		return new PsiInternalDummyTestLanguageParser(builder, tokenStream, elementTypeProvider, grammarAccess);
	}

	@Override
	protected Set<String> getInitialHiddenTokens() {
		return INITIAL_HIDDEN_TOKENS;
	}

}
