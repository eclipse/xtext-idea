package org.eclipse.xtext.parser.terminalrules.idea.lang.psi;

import com.intellij.psi.util.PsiModificationTracker;
import org.eclipse.xtext.parser.terminalrules.idea.lang.EcoreTerminalsTestLanguageLanguage;
import org.eclipse.xtext.psi.BaseXtextCodeBlockModificationListener;

public class EcoreTerminalsTestLanguageCodeBlockModificationListener extends BaseXtextCodeBlockModificationListener {

	public EcoreTerminalsTestLanguageCodeBlockModificationListener(PsiModificationTracker psiModificationTracker) {
		super(EcoreTerminalsTestLanguageLanguage.INSTANCE, psiModificationTracker);
	}

}
