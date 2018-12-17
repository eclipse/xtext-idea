package org.eclipse.xtext.parsetree.impl.idea.lang.psi;

import com.intellij.psi.util.PsiModificationTracker;
import org.eclipse.xtext.parsetree.impl.idea.lang.CommentAssociationTestLanguageLanguage;
import org.eclipse.xtext.psi.BaseXtextCodeBlockModificationListener;

public class CommentAssociationTestLanguageCodeBlockModificationListener extends BaseXtextCodeBlockModificationListener {

	public CommentAssociationTestLanguageCodeBlockModificationListener(PsiModificationTracker psiModificationTracker) {
		super(CommentAssociationTestLanguageLanguage.INSTANCE, psiModificationTracker);
	}

}
