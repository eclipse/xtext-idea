package org.eclipse.xtext.parsetree.transientvalues.idea.lang.psi;

import com.intellij.psi.util.PsiModificationTracker;
import org.eclipse.xtext.parsetree.transientvalues.idea.lang.TransientValuesTestLanguage;
import org.eclipse.xtext.psi.BaseXtextCodeBlockModificationListener;

public class TransientValuesTestCodeBlockModificationListener extends BaseXtextCodeBlockModificationListener {

	public TransientValuesTestCodeBlockModificationListener(PsiModificationTracker psiModificationTracker) {
		super(TransientValuesTestLanguage.INSTANCE, psiModificationTracker);
	}

}
