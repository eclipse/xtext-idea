package org.eclipse.xtext.parser.antlr.idea.lang.psi;

import com.intellij.psi.util.PsiModificationTracker;
import org.eclipse.xtext.parser.antlr.idea.lang.Bug378967TestLanguageLanguage;
import org.eclipse.xtext.psi.BaseXtextCodeBlockModificationListener;

public class Bug378967TestLanguageCodeBlockModificationListener extends BaseXtextCodeBlockModificationListener {

	public Bug378967TestLanguageCodeBlockModificationListener(PsiModificationTracker psiModificationTracker) {
		super(Bug378967TestLanguageLanguage.INSTANCE, psiModificationTracker);
	}

}
