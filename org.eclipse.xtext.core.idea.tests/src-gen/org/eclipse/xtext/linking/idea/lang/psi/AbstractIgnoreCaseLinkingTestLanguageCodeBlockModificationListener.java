package org.eclipse.xtext.linking.idea.lang.psi;

import com.intellij.psi.util.PsiModificationTracker;
import org.eclipse.xtext.linking.idea.lang.AbstractIgnoreCaseLinkingTestLanguageLanguage;
import org.eclipse.xtext.psi.BaseXtextCodeBlockModificationListener;

public class AbstractIgnoreCaseLinkingTestLanguageCodeBlockModificationListener extends BaseXtextCodeBlockModificationListener {

	public AbstractIgnoreCaseLinkingTestLanguageCodeBlockModificationListener(PsiModificationTracker psiModificationTracker) {
		super(AbstractIgnoreCaseLinkingTestLanguageLanguage.INSTANCE, psiModificationTracker);
	}

}
