package org.eclipse.xtext.parser.indentation.idea.lang.psi;

import com.intellij.psi.util.PsiModificationTracker;
import org.eclipse.xtext.parser.indentation.idea.lang.IndentationAwareTestLanguageLanguage;
import org.eclipse.xtext.psi.BaseXtextCodeBlockModificationListener;

public class IndentationAwareTestLanguageCodeBlockModificationListener extends BaseXtextCodeBlockModificationListener {

	public IndentationAwareTestLanguageCodeBlockModificationListener(PsiModificationTracker psiModificationTracker) {
		super(IndentationAwareTestLanguageLanguage.INSTANCE, psiModificationTracker);
	}

}
