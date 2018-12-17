package org.eclipse.xtext.parser.unorderedGroups.idea.lang.psi;

import com.intellij.psi.util.PsiModificationTracker;
import org.eclipse.xtext.parser.unorderedGroups.idea.lang.UnorderedGroupsTestLanguageLanguage;
import org.eclipse.xtext.psi.BaseXtextCodeBlockModificationListener;

public class UnorderedGroupsTestLanguageCodeBlockModificationListener extends BaseXtextCodeBlockModificationListener {

	public UnorderedGroupsTestLanguageCodeBlockModificationListener(PsiModificationTracker psiModificationTracker) {
		super(UnorderedGroupsTestLanguageLanguage.INSTANCE, psiModificationTracker);
	}

}
