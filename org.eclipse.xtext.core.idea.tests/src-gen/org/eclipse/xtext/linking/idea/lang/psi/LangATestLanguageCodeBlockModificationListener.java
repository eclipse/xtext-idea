package org.eclipse.xtext.linking.idea.lang.psi;

import com.intellij.psi.util.PsiModificationTracker;
import org.eclipse.xtext.linking.idea.lang.LangATestLanguageLanguage;
import org.eclipse.xtext.psi.BaseXtextCodeBlockModificationListener;

public class LangATestLanguageCodeBlockModificationListener extends BaseXtextCodeBlockModificationListener {

	public LangATestLanguageCodeBlockModificationListener(PsiModificationTracker psiModificationTracker) {
		super(LangATestLanguageLanguage.INSTANCE, psiModificationTracker);
	}

}
