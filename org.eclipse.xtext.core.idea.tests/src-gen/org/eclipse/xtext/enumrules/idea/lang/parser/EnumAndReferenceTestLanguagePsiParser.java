package org.eclipse.xtext.enumrules.idea.lang.parser;

import com.google.inject.Inject;
import com.intellij.lang.PsiBuilder;
import org.antlr.runtime.TokenStream;
import org.eclipse.xtext.enumrules.idea.lang.EnumAndReferenceTestLanguageElementTypeProvider;
import org.eclipse.xtext.enumrules.idea.parser.antlr.internal.PsiInternalEnumAndReferenceTestLanguageParser;
import org.eclipse.xtext.enumrules.services.EnumAndReferenceTestLanguageGrammarAccess;
import org.eclipse.xtext.idea.parser.AbstractPsiAntlrParser;
import org.eclipse.xtext.idea.parser.AbstractXtextPsiParser;

import java.util.Arrays;
import java.util.HashSet;
import java.util.Set;

public class EnumAndReferenceTestLanguagePsiParser extends AbstractXtextPsiParser {

	private static final Set<String> INITIAL_HIDDEN_TOKENS = new HashSet<String>(Arrays.asList("RULE_WS", "RULE_ML_COMMENT", "RULE_SL_COMMENT"));

	@Inject 
	private EnumAndReferenceTestLanguageGrammarAccess grammarAccess;

	@Inject 
	private EnumAndReferenceTestLanguageElementTypeProvider elementTypeProvider;

	@Override
	protected AbstractPsiAntlrParser createParser(PsiBuilder builder, TokenStream tokenStream) {
		return new PsiInternalEnumAndReferenceTestLanguageParser(builder, tokenStream, elementTypeProvider, grammarAccess);
	}

	@Override
	protected Set<String> getInitialHiddenTokens() {
		return INITIAL_HIDDEN_TOKENS;
	}

}
