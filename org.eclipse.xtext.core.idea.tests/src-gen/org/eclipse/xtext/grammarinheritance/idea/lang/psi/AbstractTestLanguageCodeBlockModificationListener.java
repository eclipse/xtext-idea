package org.eclipse.xtext.grammarinheritance.idea.lang.psi;

import com.intellij.psi.util.PsiModificationTracker;
import org.eclipse.xtext.grammarinheritance.idea.lang.AbstractTestLanguageLanguage;
import org.eclipse.xtext.psi.BaseXtextCodeBlockModificationListener;

public class AbstractTestLanguageCodeBlockModificationListener extends BaseXtextCodeBlockModificationListener {

	public AbstractTestLanguageCodeBlockModificationListener(PsiModificationTracker psiModificationTracker) {
		super(AbstractTestLanguageLanguage.INSTANCE, psiModificationTracker);
	}

}
