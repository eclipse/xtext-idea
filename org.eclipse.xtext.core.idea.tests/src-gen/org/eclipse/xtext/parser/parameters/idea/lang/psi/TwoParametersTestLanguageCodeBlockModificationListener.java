package org.eclipse.xtext.parser.parameters.idea.lang.psi;

import com.intellij.psi.util.PsiModificationTracker;
import org.eclipse.xtext.parser.parameters.idea.lang.TwoParametersTestLanguageLanguage;
import org.eclipse.xtext.psi.BaseXtextCodeBlockModificationListener;

public class TwoParametersTestLanguageCodeBlockModificationListener extends BaseXtextCodeBlockModificationListener {

	public TwoParametersTestLanguageCodeBlockModificationListener(PsiModificationTracker psiModificationTracker) {
		super(TwoParametersTestLanguageLanguage.INSTANCE, psiModificationTracker);
	}

}
