package org.eclipse.xtext.parser.terminalrules.idea.lang.psi;

import com.intellij.psi.util.PsiModificationTracker;
import org.eclipse.xtext.parser.terminalrules.idea.lang.Bug292245TestLanguageLanguage;
import org.eclipse.xtext.psi.BaseXtextCodeBlockModificationListener;

public class Bug292245TestLanguageCodeBlockModificationListener extends BaseXtextCodeBlockModificationListener {

	public Bug292245TestLanguageCodeBlockModificationListener(PsiModificationTracker psiModificationTracker) {
		super(Bug292245TestLanguageLanguage.INSTANCE, psiModificationTracker);
	}

}
