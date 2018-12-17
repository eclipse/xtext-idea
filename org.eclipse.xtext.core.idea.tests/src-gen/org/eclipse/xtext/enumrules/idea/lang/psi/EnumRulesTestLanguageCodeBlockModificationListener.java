package org.eclipse.xtext.enumrules.idea.lang.psi;

import com.intellij.psi.util.PsiModificationTracker;
import org.eclipse.xtext.enumrules.idea.lang.EnumRulesTestLanguageLanguage;
import org.eclipse.xtext.psi.BaseXtextCodeBlockModificationListener;

public class EnumRulesTestLanguageCodeBlockModificationListener extends BaseXtextCodeBlockModificationListener {

	public EnumRulesTestLanguageCodeBlockModificationListener(PsiModificationTracker psiModificationTracker) {
		super(EnumRulesTestLanguageLanguage.INSTANCE, psiModificationTracker);
	}

}
