package org.eclipse.xtext.parsetree.transientvalues.idea.lang.parser;

import com.google.inject.Inject;
import com.intellij.lang.PsiBuilder;
import org.antlr.runtime.TokenStream;
import org.eclipse.xtext.idea.parser.AbstractPsiAntlrParser;
import org.eclipse.xtext.idea.parser.AbstractXtextPsiParser;
import org.eclipse.xtext.parsetree.transientvalues.idea.lang.TransientValuesTestElementTypeProvider;
import org.eclipse.xtext.parsetree.transientvalues.idea.parser.antlr.internal.PsiInternalTransientValuesTestParser;
import org.eclipse.xtext.parsetree.transientvalues.services.TransientValuesTestGrammarAccess;

import java.util.Arrays;
import java.util.HashSet;
import java.util.Set;

public class TransientValuesTestPsiParser extends AbstractXtextPsiParser {

	private static final Set<String> INITIAL_HIDDEN_TOKENS = new HashSet<String>(Arrays.asList("RULE_WS", "RULE_ML_COMMENT", "RULE_SL_COMMENT"));

	@Inject 
	private TransientValuesTestGrammarAccess grammarAccess;

	@Inject 
	private TransientValuesTestElementTypeProvider elementTypeProvider;

	@Override
	protected AbstractPsiAntlrParser createParser(PsiBuilder builder, TokenStream tokenStream) {
		return new PsiInternalTransientValuesTestParser(builder, tokenStream, elementTypeProvider, grammarAccess);
	}

	@Override
	protected Set<String> getInitialHiddenTokens() {
		return INITIAL_HIDDEN_TOKENS;
	}

}
