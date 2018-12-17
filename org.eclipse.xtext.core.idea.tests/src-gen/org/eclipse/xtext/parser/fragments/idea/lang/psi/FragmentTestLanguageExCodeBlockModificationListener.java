package org.eclipse.xtext.parser.fragments.idea.lang.psi;

import com.intellij.psi.util.PsiModificationTracker;
import org.eclipse.xtext.parser.fragments.idea.lang.FragmentTestLanguageExLanguage;
import org.eclipse.xtext.psi.BaseXtextCodeBlockModificationListener;

public class FragmentTestLanguageExCodeBlockModificationListener extends BaseXtextCodeBlockModificationListener {

	public FragmentTestLanguageExCodeBlockModificationListener(PsiModificationTracker psiModificationTracker) {
		super(FragmentTestLanguageExLanguage.INSTANCE, psiModificationTracker);
	}

}
