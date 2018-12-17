package org.eclipse.xtext.linking.idea.lang.psi;

import com.intellij.psi.util.PsiModificationTracker;
import org.eclipse.xtext.linking.idea.lang.IgnoreCaseNamespacesTestLanguageLanguage;
import org.eclipse.xtext.psi.BaseXtextCodeBlockModificationListener;

public class IgnoreCaseNamespacesTestLanguageCodeBlockModificationListener extends BaseXtextCodeBlockModificationListener {

	public IgnoreCaseNamespacesTestLanguageCodeBlockModificationListener(PsiModificationTracker psiModificationTracker) {
		super(IgnoreCaseNamespacesTestLanguageLanguage.INSTANCE, psiModificationTracker);
	}

}
