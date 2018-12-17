package org.eclipse.xtext.grammarinheritance.idea.lang.psi;

import com.intellij.psi.util.PsiModificationTracker;
import org.eclipse.xtext.grammarinheritance.idea.lang.ConcreteTestLanguageLanguage;
import org.eclipse.xtext.psi.BaseXtextCodeBlockModificationListener;

public class ConcreteTestLanguageCodeBlockModificationListener extends BaseXtextCodeBlockModificationListener {

	public ConcreteTestLanguageCodeBlockModificationListener(PsiModificationTracker psiModificationTracker) {
		super(ConcreteTestLanguageLanguage.INSTANCE, psiModificationTracker);
	}

}
