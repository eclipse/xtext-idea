package org.eclipse.xtext.enumrules.idea.lang.psi;

import com.intellij.psi.util.PsiModificationTracker;
import org.eclipse.xtext.enumrules.idea.lang.MultiRuleEnumTestLanguageLanguage;
import org.eclipse.xtext.psi.BaseXtextCodeBlockModificationListener;

public class MultiRuleEnumTestLanguageCodeBlockModificationListener extends BaseXtextCodeBlockModificationListener {

	public MultiRuleEnumTestLanguageCodeBlockModificationListener(PsiModificationTracker psiModificationTracker) {
		super(MultiRuleEnumTestLanguageLanguage.INSTANCE, psiModificationTracker);
	}

}
