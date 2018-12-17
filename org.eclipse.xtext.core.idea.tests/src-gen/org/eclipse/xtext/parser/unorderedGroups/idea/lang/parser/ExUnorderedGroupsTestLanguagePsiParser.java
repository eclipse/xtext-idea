package org.eclipse.xtext.parser.unorderedGroups.idea.lang.parser;

import com.google.inject.Inject;
import com.intellij.lang.PsiBuilder;
import org.antlr.runtime.TokenStream;
import org.eclipse.xtext.idea.parser.AbstractPsiAntlrParser;
import org.eclipse.xtext.idea.parser.AbstractXtextPsiParser;
import org.eclipse.xtext.parser.unorderedGroups.idea.lang.ExUnorderedGroupsTestLanguageElementTypeProvider;
import org.eclipse.xtext.parser.unorderedGroups.idea.parser.antlr.internal.PsiInternalExUnorderedGroupsTestLanguageParser;
import org.eclipse.xtext.parser.unorderedGroups.services.ExUnorderedGroupsTestLanguageGrammarAccess;

import java.util.Arrays;
import java.util.HashSet;
import java.util.Set;

public class ExUnorderedGroupsTestLanguagePsiParser extends AbstractXtextPsiParser {

	private static final Set<String> INITIAL_HIDDEN_TOKENS = new HashSet<String>(Arrays.asList("RULE_WS", "RULE_ML_COMMENT", "RULE_SL_COMMENT"));

	@Inject 
	private ExUnorderedGroupsTestLanguageGrammarAccess grammarAccess;

	@Inject 
	private ExUnorderedGroupsTestLanguageElementTypeProvider elementTypeProvider;

	@Override
	protected AbstractPsiAntlrParser createParser(PsiBuilder builder, TokenStream tokenStream) {
		return new PsiInternalExUnorderedGroupsTestLanguageParser(builder, tokenStream, elementTypeProvider, grammarAccess);
	}

	@Override
	protected Set<String> getInitialHiddenTokens() {
		return INITIAL_HIDDEN_TOKENS;
	}

}
