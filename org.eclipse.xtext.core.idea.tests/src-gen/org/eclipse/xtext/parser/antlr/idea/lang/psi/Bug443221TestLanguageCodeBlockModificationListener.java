package org.eclipse.xtext.parser.antlr.idea.lang.psi;

import com.intellij.psi.util.PsiModificationTracker;
import org.eclipse.xtext.parser.antlr.idea.lang.Bug443221TestLanguageLanguage;
import org.eclipse.xtext.psi.BaseXtextCodeBlockModificationListener;

public class Bug443221TestLanguageCodeBlockModificationListener extends BaseXtextCodeBlockModificationListener {

	public Bug443221TestLanguageCodeBlockModificationListener(PsiModificationTracker psiModificationTracker) {
		super(Bug443221TestLanguageLanguage.INSTANCE, psiModificationTracker);
	}

}
