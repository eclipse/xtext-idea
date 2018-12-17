package org.eclipse.xtext.grammarinheritance.idea.lang.psi;

import com.intellij.psi.util.PsiModificationTracker;
import org.eclipse.xtext.grammarinheritance.idea.lang.BaseInheritanceTestLanguageLanguage;
import org.eclipse.xtext.psi.BaseXtextCodeBlockModificationListener;

public class BaseInheritanceTestLanguageCodeBlockModificationListener extends BaseXtextCodeBlockModificationListener {

	public BaseInheritanceTestLanguageCodeBlockModificationListener(PsiModificationTracker psiModificationTracker) {
		super(BaseInheritanceTestLanguageLanguage.INSTANCE, psiModificationTracker);
	}

}
