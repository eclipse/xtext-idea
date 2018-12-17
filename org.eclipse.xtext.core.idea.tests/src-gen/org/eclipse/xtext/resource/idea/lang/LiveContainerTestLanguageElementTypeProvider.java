package org.eclipse.xtext.resource.idea.lang;

import com.intellij.psi.tree.IFileElementType;
import org.eclipse.emf.ecore.EObject;
import org.eclipse.xtext.idea.lang.IElementTypeProvider;
import org.eclipse.xtext.psi.stubs.XtextFileElementType;
import org.eclipse.xtext.psi.stubs.XtextFileStub;
import org.eclipse.xtext.psi.tree.IGrammarAwareElementType;
import org.eclipse.xtext.resource.idea.lang.psi.impl.LiveContainerTestLanguageFileImpl;
import org.eclipse.xtext.resource.services.LiveContainerTestLanguageGrammarAccess;

import java.util.HashMap;
import java.util.Map;

public class LiveContainerTestLanguageElementTypeProvider implements IElementTypeProvider {

	public static final IFileElementType FILE_TYPE = new XtextFileElementType<XtextFileStub<LiveContainerTestLanguageFileImpl>>(LiveContainerTestLanguageLanguage.INSTANCE);

	private static final Map<EObject, IGrammarAwareElementType> GRAMMAR_ELEMENT_TYPE = new HashMap<EObject, IGrammarAwareElementType>();

	private static IGrammarAwareElementType associate(IGrammarAwareElementType grammarAwareElementType) {
		GRAMMAR_ELEMENT_TYPE.put(grammarAwareElementType.getGrammarElement(), grammarAwareElementType);
		return grammarAwareElementType;
	}

	private static final LiveContainerTestLanguageGrammarAccess GRAMMAR_ACCESS = LiveContainerTestLanguageLanguage.INSTANCE.getInstance(LiveContainerTestLanguageGrammarAccess.class);

	private static class ModelFactory {
		public static IGrammarAwareElementType createModelElementType() {
			return new IGrammarAwareElementType("Model_ELEMENT_TYPE", LiveContainerTestLanguageLanguage.INSTANCE, GRAMMAR_ACCESS.getModelRule());
		}
		public static IGrammarAwareElementType createModel_NameAssignmentElementType() {
			return new IGrammarAwareElementType("Model_NameAssignment_ELEMENT_TYPE", LiveContainerTestLanguageLanguage.INSTANCE, GRAMMAR_ACCESS.getModelAccess().getNameAssignment());
		}
		public static IGrammarAwareElementType createModel_NameIDTerminalRuleCall_0ElementType() {
			return new IGrammarAwareElementType("Model_NameIDTerminalRuleCall_0_ELEMENT_TYPE", LiveContainerTestLanguageLanguage.INSTANCE, GRAMMAR_ACCESS.getModelAccess().getNameIDTerminalRuleCall_0());
		}
	}

	public static final IGrammarAwareElementType Model_ELEMENT_TYPE = associate(ModelFactory.createModelElementType());

	public static final IGrammarAwareElementType Model_NameAssignment_ELEMENT_TYPE = associate(ModelFactory.createModel_NameAssignmentElementType());

	public static final IGrammarAwareElementType Model_NameIDTerminalRuleCall_0_ELEMENT_TYPE = associate(ModelFactory.createModel_NameIDTerminalRuleCall_0ElementType());

	@Override
	public IFileElementType getFileType() {
		return FILE_TYPE;
	}

	@Override
	public IGrammarAwareElementType findElementType(EObject grammarElement) {
		return GRAMMAR_ELEMENT_TYPE.get(grammarElement);
	}

	public IGrammarAwareElementType getModelElementType() {
		return Model_ELEMENT_TYPE;
	}

	public IGrammarAwareElementType getModel_NameAssignmentElementType() {
		return Model_NameAssignment_ELEMENT_TYPE;
	}

	public IGrammarAwareElementType getModel_NameIDTerminalRuleCall_0ElementType() {
		return Model_NameIDTerminalRuleCall_0_ELEMENT_TYPE;
	}

}
