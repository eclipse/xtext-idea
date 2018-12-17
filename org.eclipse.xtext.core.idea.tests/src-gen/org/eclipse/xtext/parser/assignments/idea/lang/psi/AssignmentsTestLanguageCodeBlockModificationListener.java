package org.eclipse.xtext.parser.assignments.idea.lang.psi;

import com.intellij.psi.util.PsiModificationTracker;
import org.eclipse.xtext.parser.assignments.idea.lang.AssignmentsTestLanguageLanguage;
import org.eclipse.xtext.psi.BaseXtextCodeBlockModificationListener;

public class AssignmentsTestLanguageCodeBlockModificationListener extends BaseXtextCodeBlockModificationListener {

	public AssignmentsTestLanguageCodeBlockModificationListener(PsiModificationTracker psiModificationTracker) {
		super(AssignmentsTestLanguageLanguage.INSTANCE, psiModificationTracker);
	}

}
