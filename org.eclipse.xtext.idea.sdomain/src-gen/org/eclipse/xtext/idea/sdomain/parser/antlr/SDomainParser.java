/*******************************************************************************
 * Copyright (c) 2015 itemis AG (http://www.itemis.eu) and others.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 *******************************************************************************/
package org.eclipse.xtext.idea.sdomain.parser.antlr;

import com.google.inject.Inject;
import org.eclipse.xtext.idea.sdomain.parser.antlr.internal.InternalSDomainParser;
import org.eclipse.xtext.idea.sdomain.services.SDomainGrammarAccess;
import org.eclipse.xtext.parser.antlr.AbstractAntlrParser;
import org.eclipse.xtext.parser.antlr.XtextTokenStream;

public class SDomainParser extends AbstractAntlrParser {

	@Inject
	private SDomainGrammarAccess grammarAccess;

	@Override
	protected void setInitialHiddenTokens(XtextTokenStream tokenStream) {
		tokenStream.setInitialHiddenTokens("RULE_WS", "RULE_ML_COMMENT", "RULE_SL_COMMENT");
	}
	

	@Override
	protected InternalSDomainParser createParser(XtextTokenStream stream) {
		return new InternalSDomainParser(stream, getGrammarAccess());
	}

	@Override 
	protected String getDefaultRuleName() {
		return "File";
	}

	public SDomainGrammarAccess getGrammarAccess() {
		return this.grammarAccess;
	}

	public void setGrammarAccess(SDomainGrammarAccess grammarAccess) {
		this.grammarAccess = grammarAccess;
	}
}
