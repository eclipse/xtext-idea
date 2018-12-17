package org.eclipse.xtext.parser.parameters.idea.lang.psi;

import com.intellij.psi.util.PsiModificationTracker;
import org.eclipse.xtext.parser.parameters.idea.lang.NoParametersTestLanguageLanguage;
import org.eclipse.xtext.psi.BaseXtextCodeBlockModificationListener;

public class NoParametersTestLanguageCodeBlockModificationListener extends BaseXtextCodeBlockModificationListener {

	public NoParametersTestLanguageCodeBlockModificationListener(PsiModificationTracker psiModificationTracker) {
		super(NoParametersTestLanguageLanguage.INSTANCE, psiModificationTracker);
	}

}
