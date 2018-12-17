package org.eclipse.xtext.parsetree.reconstr.idea.lang.parser;

import com.google.inject.Inject;
import com.intellij.lang.PsiBuilder;
import org.antlr.runtime.TokenStream;
import org.eclipse.xtext.idea.parser.AbstractPsiAntlrParser;
import org.eclipse.xtext.idea.parser.AbstractXtextPsiParser;
import org.eclipse.xtext.parsetree.reconstr.idea.lang.SerializationErrorTestLanguageElementTypeProvider;
import org.eclipse.xtext.parsetree.reconstr.idea.parser.antlr.internal.PsiInternalSerializationErrorTestLanguageParser;
import org.eclipse.xtext.parsetree.reconstr.services.SerializationErrorTestLanguageGrammarAccess;

import java.util.Arrays;
import java.util.HashSet;
import java.util.Set;

public class SerializationErrorTestLanguagePsiParser extends AbstractXtextPsiParser {

	private static final Set<String> INITIAL_HIDDEN_TOKENS = new HashSet<String>(Arrays.asList("RULE_WS", "RULE_ML_COMMENT", "RULE_SL_COMMENT"));

	@Inject 
	private SerializationErrorTestLanguageGrammarAccess grammarAccess;

	@Inject 
	private SerializationErrorTestLanguageElementTypeProvider elementTypeProvider;

	@Override
	protected AbstractPsiAntlrParser createParser(PsiBuilder builder, TokenStream tokenStream) {
		return new PsiInternalSerializationErrorTestLanguageParser(builder, tokenStream, elementTypeProvider, grammarAccess);
	}

	@Override
	protected Set<String> getInitialHiddenTokens() {
		return INITIAL_HIDDEN_TOKENS;
	}

}
