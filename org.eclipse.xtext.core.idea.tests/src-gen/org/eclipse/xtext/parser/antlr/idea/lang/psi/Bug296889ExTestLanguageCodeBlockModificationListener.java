package org.eclipse.xtext.parser.antlr.idea.lang.psi;

import com.intellij.psi.util.PsiModificationTracker;
import org.eclipse.xtext.parser.antlr.idea.lang.Bug296889ExTestLanguageLanguage;
import org.eclipse.xtext.psi.BaseXtextCodeBlockModificationListener;

public class Bug296889ExTestLanguageCodeBlockModificationListener extends BaseXtextCodeBlockModificationListener {

	public Bug296889ExTestLanguageCodeBlockModificationListener(PsiModificationTracker psiModificationTracker) {
		super(Bug296889ExTestLanguageLanguage.INSTANCE, psiModificationTracker);
	}

}
