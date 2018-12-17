package org.eclipse.xtext.parser.unorderedGroups.idea.lang.psi;

import com.intellij.psi.util.PsiModificationTracker;
import org.eclipse.xtext.parser.unorderedGroups.idea.lang.SimpleUnorderedGroupsTestLanguageLanguage;
import org.eclipse.xtext.psi.BaseXtextCodeBlockModificationListener;

public class SimpleUnorderedGroupsTestLanguageCodeBlockModificationListener extends BaseXtextCodeBlockModificationListener {

	public SimpleUnorderedGroupsTestLanguageCodeBlockModificationListener(PsiModificationTracker psiModificationTracker) {
		super(SimpleUnorderedGroupsTestLanguageLanguage.INSTANCE, psiModificationTracker);
	}

}
