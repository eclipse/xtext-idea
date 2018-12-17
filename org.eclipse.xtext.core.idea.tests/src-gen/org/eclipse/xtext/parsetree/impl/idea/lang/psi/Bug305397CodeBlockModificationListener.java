package org.eclipse.xtext.parsetree.impl.idea.lang.psi;

import com.intellij.psi.util.PsiModificationTracker;
import org.eclipse.xtext.parsetree.impl.idea.lang.Bug305397Language;
import org.eclipse.xtext.psi.BaseXtextCodeBlockModificationListener;

public class Bug305397CodeBlockModificationListener extends BaseXtextCodeBlockModificationListener {

	public Bug305397CodeBlockModificationListener(PsiModificationTracker psiModificationTracker) {
		super(Bug305397Language.INSTANCE, psiModificationTracker);
	}

}
