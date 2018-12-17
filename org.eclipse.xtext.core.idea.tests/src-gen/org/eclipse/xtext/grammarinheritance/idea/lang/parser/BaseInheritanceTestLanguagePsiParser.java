package org.eclipse.xtext.grammarinheritance.idea.lang.parser;

import com.google.inject.Inject;
import com.intellij.lang.PsiBuilder;
import org.antlr.runtime.TokenStream;
import org.eclipse.xtext.grammarinheritance.idea.lang.BaseInheritanceTestLanguageElementTypeProvider;
import org.eclipse.xtext.grammarinheritance.idea.parser.antlr.internal.PsiInternalBaseInheritanceTestLanguageParser;
import org.eclipse.xtext.grammarinheritance.services.BaseInheritanceTestLanguageGrammarAccess;
import org.eclipse.xtext.idea.parser.AbstractPsiAntlrParser;
import org.eclipse.xtext.idea.parser.AbstractXtextPsiParser;

import java.util.Arrays;
import java.util.HashSet;
import java.util.Set;

public class BaseInheritanceTestLanguagePsiParser extends AbstractXtextPsiParser {

	private static final Set<String> INITIAL_HIDDEN_TOKENS = new HashSet<String>(Arrays.asList("RULE_WS", "RULE_ML_COMMENT", "RULE_SL_COMMENT"));

	@Inject 
	private BaseInheritanceTestLanguageGrammarAccess grammarAccess;

	@Inject 
	private BaseInheritanceTestLanguageElementTypeProvider elementTypeProvider;

	@Override
	protected AbstractPsiAntlrParser createParser(PsiBuilder builder, TokenStream tokenStream) {
		return new PsiInternalBaseInheritanceTestLanguageParser(builder, tokenStream, elementTypeProvider, grammarAccess);
	}

	@Override
	protected Set<String> getInitialHiddenTokens() {
		return INITIAL_HIDDEN_TOKENS;
	}

}
