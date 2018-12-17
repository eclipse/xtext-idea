package org.eclipse.xtext.generator.grammarAccess.idea.lang.psi;

import com.intellij.psi.util.PsiModificationTracker;
import org.eclipse.xtext.generator.grammarAccess.idea.lang.GrammarAccessTestLanguageLanguage;
import org.eclipse.xtext.psi.BaseXtextCodeBlockModificationListener;

public class GrammarAccessTestLanguageCodeBlockModificationListener extends BaseXtextCodeBlockModificationListener {

	public GrammarAccessTestLanguageCodeBlockModificationListener(PsiModificationTracker psiModificationTracker) {
		super(GrammarAccessTestLanguageLanguage.INSTANCE, psiModificationTracker);
	}

}
