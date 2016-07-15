/*******************************************************************************
 * Copyright (c) 2015 itemis AG (http://www.itemis.eu) and others.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 *******************************************************************************/
package org.eclipse.xtext.idea.common.types.ide.contentassist.antlr;

import com.google.inject.Inject;
import java.util.Collection;
import java.util.HashMap;
import java.util.Map;
import org.antlr.runtime.RecognitionException;
import org.eclipse.xtext.AbstractElement;
import org.eclipse.xtext.ide.editor.contentassist.antlr.AbstractContentAssistParser;
import org.eclipse.xtext.ide.editor.contentassist.antlr.FollowElement;
import org.eclipse.xtext.ide.editor.contentassist.antlr.internal.AbstractInternalContentAssistParser;
import org.eclipse.xtext.idea.common.types.ide.contentassist.antlr.internal.InternalRefactoringTestLanguageParser;
import org.eclipse.xtext.idea.common.types.services.RefactoringTestLanguageGrammarAccess;

public class RefactoringTestLanguageParser extends AbstractContentAssistParser {

	@Inject
	private RefactoringTestLanguageGrammarAccess grammarAccess;

	private Map<AbstractElement, String> nameMappings;

	@Override
	protected InternalRefactoringTestLanguageParser createParser() {
		InternalRefactoringTestLanguageParser result = new InternalRefactoringTestLanguageParser(null);
		result.setGrammarAccess(grammarAccess);
		return result;
	}

	@Override
	protected String getRuleName(AbstractElement element) {
		if (nameMappings == null) {
			nameMappings = new HashMap<AbstractElement, String>() {
				private static final long serialVersionUID = 1L;
				{
					put(grammarAccess.getReferenceHolderAccess().getGroup(), "rule__ReferenceHolder__Group__0");
					put(grammarAccess.getFQNAccess().getGroup(), "rule__FQN__Group__0");
					put(grammarAccess.getFQNAccess().getGroup_1(), "rule__FQN__Group_1__0");
					put(grammarAccess.getFQNAccess().getGroup_2(), "rule__FQN__Group_2__0");
					put(grammarAccess.getModelAccess().getReferenceHolderAssignment(), "rule__Model__ReferenceHolderAssignment");
					put(grammarAccess.getReferenceHolderAccess().getNameAssignment_0(), "rule__ReferenceHolder__NameAssignment_0");
					put(grammarAccess.getReferenceHolderAccess().getDefaultReferenceAssignment_1(), "rule__ReferenceHolder__DefaultReferenceAssignment_1");
				}
			};
		}
		return nameMappings.get(element);
	}

	@Override
	protected Collection<FollowElement> getFollowElements(AbstractInternalContentAssistParser parser) {
		try {
			InternalRefactoringTestLanguageParser typedParser = (InternalRefactoringTestLanguageParser) parser;
			typedParser.entryRuleModel();
			return typedParser.getFollowElements();
		} catch(RecognitionException ex) {
			throw new RuntimeException(ex);
		}
	}

	@Override
	protected String[] getInitialHiddenTokens() {
		return new String[] { "RULE_WS", "RULE_ML_COMMENT", "RULE_SL_COMMENT" };
	}

	public RefactoringTestLanguageGrammarAccess getGrammarAccess() {
		return this.grammarAccess;
	}

	public void setGrammarAccess(RefactoringTestLanguageGrammarAccess grammarAccess) {
		this.grammarAccess = grammarAccess;
	}
}
