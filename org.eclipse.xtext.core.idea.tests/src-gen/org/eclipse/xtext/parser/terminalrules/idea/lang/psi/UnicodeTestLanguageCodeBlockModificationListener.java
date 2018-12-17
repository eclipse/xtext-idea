package org.eclipse.xtext.parser.terminalrules.idea.lang.psi;

import com.intellij.psi.util.PsiModificationTracker;
import org.eclipse.xtext.parser.terminalrules.idea.lang.UnicodeTestLanguageLanguage;
import org.eclipse.xtext.psi.BaseXtextCodeBlockModificationListener;

public class UnicodeTestLanguageCodeBlockModificationListener extends BaseXtextCodeBlockModificationListener {

	public UnicodeTestLanguageCodeBlockModificationListener(PsiModificationTracker psiModificationTracker) {
		super(UnicodeTestLanguageLanguage.INSTANCE, psiModificationTracker);
	}

}
