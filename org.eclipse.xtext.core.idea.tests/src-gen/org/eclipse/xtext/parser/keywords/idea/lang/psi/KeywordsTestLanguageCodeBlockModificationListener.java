package org.eclipse.xtext.parser.keywords.idea.lang.psi;

import com.intellij.psi.util.PsiModificationTracker;
import org.eclipse.xtext.parser.keywords.idea.lang.KeywordsTestLanguageLanguage;
import org.eclipse.xtext.psi.BaseXtextCodeBlockModificationListener;

public class KeywordsTestLanguageCodeBlockModificationListener extends BaseXtextCodeBlockModificationListener {

	public KeywordsTestLanguageCodeBlockModificationListener(PsiModificationTracker psiModificationTracker) {
		super(KeywordsTestLanguageLanguage.INSTANCE, psiModificationTracker);
	}

}
