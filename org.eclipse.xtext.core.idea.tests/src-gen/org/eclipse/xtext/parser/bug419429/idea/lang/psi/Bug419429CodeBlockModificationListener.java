package org.eclipse.xtext.parser.bug419429.idea.lang.psi;

import com.intellij.psi.util.PsiModificationTracker;
import org.eclipse.xtext.parser.bug419429.idea.lang.Bug419429Language;
import org.eclipse.xtext.psi.BaseXtextCodeBlockModificationListener;

public class Bug419429CodeBlockModificationListener extends BaseXtextCodeBlockModificationListener {

	public Bug419429CodeBlockModificationListener(PsiModificationTracker psiModificationTracker) {
		super(Bug419429Language.INSTANCE, psiModificationTracker);
	}

}
