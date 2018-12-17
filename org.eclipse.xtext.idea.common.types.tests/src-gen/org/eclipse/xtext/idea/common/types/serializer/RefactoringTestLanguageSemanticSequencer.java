/*******************************************************************************
 * Copyright (c) 2015 itemis AG (http://www.itemis.eu) and others.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 *******************************************************************************/
package org.eclipse.xtext.idea.common.types.serializer;

import com.google.inject.Inject;
import org.eclipse.emf.ecore.EObject;
import org.eclipse.emf.ecore.EPackage;
import org.eclipse.xtext.Action;
import org.eclipse.xtext.Parameter;
import org.eclipse.xtext.ParserRule;
import org.eclipse.xtext.idea.common.types.refactoringTestLanguage.Model;
import org.eclipse.xtext.idea.common.types.refactoringTestLanguage.RefactoringTestLanguagePackage;
import org.eclipse.xtext.idea.common.types.refactoringTestLanguage.ReferenceHolder;
import org.eclipse.xtext.idea.common.types.services.RefactoringTestLanguageGrammarAccess;
import org.eclipse.xtext.serializer.ISerializationContext;
import org.eclipse.xtext.serializer.acceptor.SequenceFeeder;
import org.eclipse.xtext.serializer.sequencer.AbstractDelegatingSemanticSequencer;
import org.eclipse.xtext.serializer.sequencer.ITransientValueService.ValueTransient;

import java.util.Set;

@SuppressWarnings("all")
public class RefactoringTestLanguageSemanticSequencer extends AbstractDelegatingSemanticSequencer {

	@Inject
	private RefactoringTestLanguageGrammarAccess grammarAccess;
	
	@Override
	public void sequence(ISerializationContext context, EObject semanticObject) {
		EPackage epackage = semanticObject.eClass().getEPackage();
		ParserRule rule = context.getParserRule();
		Action action = context.getAssignedAction();
		Set<Parameter> parameters = context.getEnabledBooleanParameters();
		if (epackage == RefactoringTestLanguagePackage.eINSTANCE)
			switch (semanticObject.eClass().getClassifierID()) {
			case RefactoringTestLanguagePackage.MODEL:
				sequence_Model(context, (Model) semanticObject); 
				return; 
			case RefactoringTestLanguagePackage.REFERENCE_HOLDER:
				sequence_ReferenceHolder(context, (ReferenceHolder) semanticObject); 
				return; 
			}
		if (errorAcceptor != null)
			errorAcceptor.accept(diagnosticProvider.createInvalidContextOrTypeDiagnostic(semanticObject, context));
	}
	
	/**
	 * Contexts:
	 *     Model returns Model
	 *
	 * Constraint:
	 *     referenceHolder+=ReferenceHolder+
	 */
	protected void sequence_Model(ISerializationContext context, Model semanticObject) {
		genericSequencer.createSequence(context, semanticObject);
	}
	
	
	/**
	 * Contexts:
	 *     ReferenceHolder returns ReferenceHolder
	 *
	 * Constraint:
	 *     (name=ID defaultReference=[JvmType|FQN])
	 */
	protected void sequence_ReferenceHolder(ISerializationContext context, ReferenceHolder semanticObject) {
		if (errorAcceptor != null) {
			if (transientValues.isValueTransient(semanticObject, RefactoringTestLanguagePackage.Literals.REFERENCE_HOLDER__NAME) == ValueTransient.YES)
				errorAcceptor.accept(diagnosticProvider.createFeatureValueMissing(semanticObject, RefactoringTestLanguagePackage.Literals.REFERENCE_HOLDER__NAME));
			if (transientValues.isValueTransient(semanticObject, RefactoringTestLanguagePackage.Literals.REFERENCE_HOLDER__DEFAULT_REFERENCE) == ValueTransient.YES)
				errorAcceptor.accept(diagnosticProvider.createFeatureValueMissing(semanticObject, RefactoringTestLanguagePackage.Literals.REFERENCE_HOLDER__DEFAULT_REFERENCE));
		}
		SequenceFeeder feeder = createSequencerFeeder(context, semanticObject);
		feeder.accept(grammarAccess.getReferenceHolderAccess().getNameIDTerminalRuleCall_0_0(), semanticObject.getName());
		feeder.accept(grammarAccess.getReferenceHolderAccess().getDefaultReferenceJvmTypeFQNParserRuleCall_1_0_1(), semanticObject.getDefaultReference());
		feeder.finish();
	}
	
	
}
