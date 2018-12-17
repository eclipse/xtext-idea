package org.eclipse.xtext.serializer.idea.lang.parser;

import com.google.inject.Inject;
import com.intellij.lang.PsiBuilder;
import org.antlr.runtime.TokenStream;
import org.eclipse.xtext.idea.parser.AbstractPsiAntlrParser;
import org.eclipse.xtext.idea.parser.AbstractXtextPsiParser;
import org.eclipse.xtext.serializer.idea.lang.SequencerTestLanguageElementTypeProvider;
import org.eclipse.xtext.serializer.idea.parser.antlr.internal.PsiInternalSequencerTestLanguageParser;
import org.eclipse.xtext.serializer.services.SequencerTestLanguageGrammarAccess;

import java.util.Arrays;
import java.util.HashSet;
import java.util.Set;

public class SequencerTestLanguagePsiParser extends AbstractXtextPsiParser {

	private static final Set<String> INITIAL_HIDDEN_TOKENS = new HashSet<String>(Arrays.asList("RULE_WS", "RULE_ML_COMMENT", "RULE_SL_COMMENT"));

	@Inject 
	private SequencerTestLanguageGrammarAccess grammarAccess;

	@Inject 
	private SequencerTestLanguageElementTypeProvider elementTypeProvider;

	@Override
	protected AbstractPsiAntlrParser createParser(PsiBuilder builder, TokenStream tokenStream) {
		return new PsiInternalSequencerTestLanguageParser(builder, tokenStream, elementTypeProvider, grammarAccess);
	}

	@Override
	protected Set<String> getInitialHiddenTokens() {
		return INITIAL_HIDDEN_TOKENS;
	}

}
