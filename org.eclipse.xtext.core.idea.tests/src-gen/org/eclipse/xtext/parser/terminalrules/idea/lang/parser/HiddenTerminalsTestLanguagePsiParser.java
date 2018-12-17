package org.eclipse.xtext.parser.terminalrules.idea.lang.parser;

import com.google.inject.Inject;
import com.intellij.lang.PsiBuilder;
import org.antlr.runtime.TokenStream;
import org.eclipse.xtext.idea.parser.AbstractPsiAntlrParser;
import org.eclipse.xtext.idea.parser.AbstractXtextPsiParser;
import org.eclipse.xtext.parser.terminalrules.idea.lang.HiddenTerminalsTestLanguageElementTypeProvider;
import org.eclipse.xtext.parser.terminalrules.idea.parser.antlr.internal.PsiInternalHiddenTerminalsTestLanguageParser;
import org.eclipse.xtext.parser.terminalrules.services.HiddenTerminalsTestLanguageGrammarAccess;

import java.util.Set;

import static java.util.Collections.emptySet;

public class HiddenTerminalsTestLanguagePsiParser extends AbstractXtextPsiParser {

	private static final Set<String> INITIAL_HIDDEN_TOKENS = emptySet();

	@Inject 
	private HiddenTerminalsTestLanguageGrammarAccess grammarAccess;

	@Inject 
	private HiddenTerminalsTestLanguageElementTypeProvider elementTypeProvider;

	@Override
	protected AbstractPsiAntlrParser createParser(PsiBuilder builder, TokenStream tokenStream) {
		return new PsiInternalHiddenTerminalsTestLanguageParser(builder, tokenStream, elementTypeProvider, grammarAccess);
	}

	@Override
	protected Set<String> getInitialHiddenTokens() {
		return INITIAL_HIDDEN_TOKENS;
	}

}
