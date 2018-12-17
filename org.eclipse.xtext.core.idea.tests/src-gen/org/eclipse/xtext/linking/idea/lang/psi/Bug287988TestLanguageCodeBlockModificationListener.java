package org.eclipse.xtext.linking.idea.lang.psi;

import com.intellij.psi.util.PsiModificationTracker;
import org.eclipse.xtext.linking.idea.lang.Bug287988TestLanguageLanguage;
import org.eclipse.xtext.psi.BaseXtextCodeBlockModificationListener;

public class Bug287988TestLanguageCodeBlockModificationListener extends BaseXtextCodeBlockModificationListener {

	public Bug287988TestLanguageCodeBlockModificationListener(PsiModificationTracker psiModificationTracker) {
		super(Bug287988TestLanguageLanguage.INSTANCE, psiModificationTracker);
	}

}
