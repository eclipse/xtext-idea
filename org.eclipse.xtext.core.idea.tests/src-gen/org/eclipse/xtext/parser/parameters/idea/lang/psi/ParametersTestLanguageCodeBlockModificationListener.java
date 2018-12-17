package org.eclipse.xtext.parser.parameters.idea.lang.psi;

import com.intellij.psi.util.PsiModificationTracker;
import org.eclipse.xtext.parser.parameters.idea.lang.ParametersTestLanguageLanguage;
import org.eclipse.xtext.psi.BaseXtextCodeBlockModificationListener;

public class ParametersTestLanguageCodeBlockModificationListener extends BaseXtextCodeBlockModificationListener {

	public ParametersTestLanguageCodeBlockModificationListener(PsiModificationTracker psiModificationTracker) {
		super(ParametersTestLanguageLanguage.INSTANCE, psiModificationTracker);
	}

}
