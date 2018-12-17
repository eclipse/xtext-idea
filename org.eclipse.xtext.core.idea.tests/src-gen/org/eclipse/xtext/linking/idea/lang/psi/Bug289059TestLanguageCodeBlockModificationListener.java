package org.eclipse.xtext.linking.idea.lang.psi;

import com.intellij.psi.util.PsiModificationTracker;
import org.eclipse.xtext.linking.idea.lang.Bug289059TestLanguageLanguage;
import org.eclipse.xtext.psi.BaseXtextCodeBlockModificationListener;

public class Bug289059TestLanguageCodeBlockModificationListener extends BaseXtextCodeBlockModificationListener {

	public Bug289059TestLanguageCodeBlockModificationListener(PsiModificationTracker psiModificationTracker) {
		super(Bug289059TestLanguageLanguage.INSTANCE, psiModificationTracker);
	}

}
