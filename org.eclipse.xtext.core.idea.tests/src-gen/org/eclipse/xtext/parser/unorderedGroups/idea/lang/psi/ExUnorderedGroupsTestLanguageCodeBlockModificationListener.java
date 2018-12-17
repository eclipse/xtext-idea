package org.eclipse.xtext.parser.unorderedGroups.idea.lang.psi;

import com.intellij.psi.util.PsiModificationTracker;
import org.eclipse.xtext.parser.unorderedGroups.idea.lang.ExUnorderedGroupsTestLanguageLanguage;
import org.eclipse.xtext.psi.BaseXtextCodeBlockModificationListener;

public class ExUnorderedGroupsTestLanguageCodeBlockModificationListener extends BaseXtextCodeBlockModificationListener {

	public ExUnorderedGroupsTestLanguageCodeBlockModificationListener(PsiModificationTracker psiModificationTracker) {
		super(ExUnorderedGroupsTestLanguageLanguage.INSTANCE, psiModificationTracker);
	}

}
