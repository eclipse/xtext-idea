package org.eclipse.xtext.parser.fragments.idea.lang.psi;

import com.intellij.psi.util.PsiModificationTracker;
import org.eclipse.xtext.parser.fragments.idea.lang.FragmentTestLanguageLanguage;
import org.eclipse.xtext.psi.BaseXtextCodeBlockModificationListener;

public class FragmentTestLanguageCodeBlockModificationListener extends BaseXtextCodeBlockModificationListener {

	public FragmentTestLanguageCodeBlockModificationListener(PsiModificationTracker psiModificationTracker) {
		super(FragmentTestLanguageLanguage.INSTANCE, psiModificationTracker);
	}

}
