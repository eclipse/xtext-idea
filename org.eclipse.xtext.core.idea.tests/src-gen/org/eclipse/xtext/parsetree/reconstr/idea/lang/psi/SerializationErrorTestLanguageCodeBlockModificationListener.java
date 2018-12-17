package org.eclipse.xtext.parsetree.reconstr.idea.lang.psi;

import com.intellij.psi.util.PsiModificationTracker;
import org.eclipse.xtext.parsetree.reconstr.idea.lang.SerializationErrorTestLanguageLanguage;
import org.eclipse.xtext.psi.BaseXtextCodeBlockModificationListener;

public class SerializationErrorTestLanguageCodeBlockModificationListener extends BaseXtextCodeBlockModificationListener {

	public SerializationErrorTestLanguageCodeBlockModificationListener(PsiModificationTracker psiModificationTracker) {
		super(SerializationErrorTestLanguageLanguage.INSTANCE, psiModificationTracker);
	}

}
