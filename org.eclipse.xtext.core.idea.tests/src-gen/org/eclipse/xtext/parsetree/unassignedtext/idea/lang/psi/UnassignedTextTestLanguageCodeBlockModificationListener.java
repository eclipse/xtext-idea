package org.eclipse.xtext.parsetree.unassignedtext.idea.lang.psi;

import com.intellij.psi.util.PsiModificationTracker;
import org.eclipse.xtext.parsetree.unassignedtext.idea.lang.UnassignedTextTestLanguageLanguage;
import org.eclipse.xtext.psi.BaseXtextCodeBlockModificationListener;

public class UnassignedTextTestLanguageCodeBlockModificationListener extends BaseXtextCodeBlockModificationListener {

	public UnassignedTextTestLanguageCodeBlockModificationListener(PsiModificationTracker psiModificationTracker) {
		super(UnassignedTextTestLanguageLanguage.INSTANCE, psiModificationTracker);
	}

}
