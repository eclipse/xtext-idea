package org.eclipse.xtext.parsetree.formatter.idea.lang.psi;

import com.intellij.psi.util.PsiModificationTracker;
import org.eclipse.xtext.parsetree.formatter.idea.lang.FormatterTestLanguageLanguage;
import org.eclipse.xtext.psi.BaseXtextCodeBlockModificationListener;

public class FormatterTestLanguageCodeBlockModificationListener extends BaseXtextCodeBlockModificationListener {

	public FormatterTestLanguageCodeBlockModificationListener(PsiModificationTracker psiModificationTracker) {
		super(FormatterTestLanguageLanguage.INSTANCE, psiModificationTracker);
	}

}
