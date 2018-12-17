package org.eclipse.xtext.idea.lang.psi;

import com.intellij.psi.util.PsiModificationTracker;
import org.eclipse.xtext.idea.lang.XtextGrammarTestLanguageLanguage;
import org.eclipse.xtext.psi.BaseXtextCodeBlockModificationListener;

public class XtextGrammarTestLanguageCodeBlockModificationListener extends BaseXtextCodeBlockModificationListener {

	public XtextGrammarTestLanguageCodeBlockModificationListener(PsiModificationTracker psiModificationTracker) {
		super(XtextGrammarTestLanguageLanguage.INSTANCE, psiModificationTracker);
	}

}
