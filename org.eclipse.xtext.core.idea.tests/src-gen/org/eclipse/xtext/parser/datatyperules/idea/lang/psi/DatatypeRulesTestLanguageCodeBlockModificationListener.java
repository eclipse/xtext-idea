package org.eclipse.xtext.parser.datatyperules.idea.lang.psi;

import com.intellij.psi.util.PsiModificationTracker;
import org.eclipse.xtext.parser.datatyperules.idea.lang.DatatypeRulesTestLanguageLanguage;
import org.eclipse.xtext.psi.BaseXtextCodeBlockModificationListener;

public class DatatypeRulesTestLanguageCodeBlockModificationListener extends BaseXtextCodeBlockModificationListener {

	public DatatypeRulesTestLanguageCodeBlockModificationListener(PsiModificationTracker psiModificationTracker) {
		super(DatatypeRulesTestLanguageLanguage.INSTANCE, psiModificationTracker);
	}

}
