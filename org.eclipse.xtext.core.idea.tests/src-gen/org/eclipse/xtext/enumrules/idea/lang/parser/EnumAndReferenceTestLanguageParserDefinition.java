package org.eclipse.xtext.enumrules.idea.lang.parser;

import com.google.inject.Inject;
import com.intellij.lang.ASTNode;
import com.intellij.psi.FileViewProvider;
import com.intellij.psi.PsiElement;
import com.intellij.psi.PsiFile;
import com.intellij.psi.tree.IElementType;
import org.eclipse.xtext.enumrules.idea.lang.EnumAndReferenceTestLanguageElementTypeProvider;
import org.eclipse.xtext.enumrules.idea.lang.psi.impl.EnumAndReferenceTestLanguageFileImpl;
import org.eclipse.xtext.idea.nodemodel.IASTNodeAwareNodeModelBuilder;
import org.eclipse.xtext.idea.parser.AbstractXtextParserDefinition;
import org.eclipse.xtext.psi.impl.PsiNamedEObjectImpl;

public class EnumAndReferenceTestLanguageParserDefinition extends AbstractXtextParserDefinition {

	@Inject 
	private EnumAndReferenceTestLanguageElementTypeProvider elementTypeProvider;

	@Override
	public PsiFile createFile(FileViewProvider viewProvider) {
		return new EnumAndReferenceTestLanguageFileImpl(viewProvider);
	}

	@Override
	@SuppressWarnings("rawtypes")
	public PsiElement createElement(ASTNode node) {
		Boolean hasSemanticElement = node.getUserData(IASTNodeAwareNodeModelBuilder.HAS_SEMANTIC_ELEMENT_KEY);
		if (hasSemanticElement != null && hasSemanticElement) {
			IElementType elementType = node.getElementType();
			if (elementType == elementTypeProvider.getEntityWithEnumAndReferenceElementType()) {
				return new PsiNamedEObjectImpl(node) {};
			}
			throw new IllegalStateException("Unexpected element type: " + elementType);
		}
		return super.createElement(node);
	}

}
