/*******************************************************************************
 * Copyright (c) 2015 itemis AG (http://www.itemis.eu) and others.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 *******************************************************************************/
package org.eclipse.xtext.idea.parser

import com.google.inject.Inject
import com.intellij.lang.PsiBuilder
import com.intellij.lang.PsiParser
import com.intellij.psi.tree.IElementType
import org.antlr.runtime.TokenSource
import org.antlr.runtime.TokenStream
import org.eclipse.xtend.lib.annotations.Accessors
import org.eclipse.xtext.AbstractRule
import org.eclipse.xtext.RuleCall
import org.eclipse.xtext.idea.nodemodel.IASTNodeAwareNodeModelBuilder
import org.eclipse.xtext.parser.antlr.ITokenDefProvider
import org.eclipse.xtext.parser.antlr.TokenSourceProvider
import org.eclipse.xtext.psi.tree.IGrammarAwareElementType
import org.eclipse.xtext.parser.antlr.IUnorderedGroupHelper
import com.google.inject.Provider
import java.util.Set

abstract class AbstractXtextPsiParser implements PsiParser {

	@Inject
	@Accessors(PROTECTED_GETTER)
	ITokenDefProvider tokenDefProvider

	@Inject
	@Accessors(PROTECTED_GETTER)
	TokenTypeProvider tokenTypeProvider
	
	@Inject
	@Accessors(PROTECTED_GETTER)
	TokenSourceProvider tokenSourceProvider
	
	@Inject
	@Accessors(PROTECTED_GETTER)
	Provider<IUnorderedGroupHelper> unorderedGroupHelperProvider

	override parse(IElementType root, PsiBuilder builder) {
		val parser = builder.createParser(builder.createTokenStream) => [
			tokenTypeMap = tokenDefProvider.tokenDefMap
			unorderedGroupHelper = unorderedGroupHelperProvider.get
			unorderedGroupHelper.initializeWith(it)
		]

		var rootMarker = builder.mark

		val entryRuleName = root.entryRuleName
		if (entryRuleName !== null) {
			parser.parse(entryRuleName)
		} else {
			parser.parse
		}

		rootMarker.done(root)
		builder.treeBuilt
	}
	
	protected def getEntryRuleName(IElementType type) {
		if (type instanceof IGrammarAwareElementType) {
			switch grammarElement: type.grammarElement {
				AbstractRule: grammarElement.name
				RuleCall: grammarElement.rule.name
			}
		}
	}

	protected def AbstractPsiAntlrParser createParser(PsiBuilder builder, TokenStream tokenStream)

	protected def createTokenStream(PsiBuilder builder) {
		val tokenSource = builder.createTokenSource
		new PsiXtextTokenStream(builder, tokenSource, tokenDefProvider) => [
			val lookAhead = builder.getUserData(IASTNodeAwareNodeModelBuilder.LOOK_AHEAD_KEY)
			if (lookAhead !== null) {
				initCurrentLookAhead(lookAhead)
			}
			initialHiddenTokens = initialHiddenTokens
		]
	}

	protected def TokenSource createTokenSource(PsiBuilder builder) {
		tokenSourceProvider.createTokenSource(builder.originalText)
	}

	protected def Set<String> getInitialHiddenTokens()

}
