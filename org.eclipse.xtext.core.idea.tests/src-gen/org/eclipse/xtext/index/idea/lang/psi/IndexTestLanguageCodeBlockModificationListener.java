package org.eclipse.xtext.index.idea.lang.psi;

import com.intellij.psi.util.PsiModificationTracker;
import org.eclipse.xtext.index.idea.lang.IndexTestLanguageLanguage;
import org.eclipse.xtext.psi.BaseXtextCodeBlockModificationListener;

public class IndexTestLanguageCodeBlockModificationListener extends BaseXtextCodeBlockModificationListener {

	public IndexTestLanguageCodeBlockModificationListener(PsiModificationTracker psiModificationTracker) {
		super(IndexTestLanguageLanguage.INSTANCE, psiModificationTracker);
	}

}
