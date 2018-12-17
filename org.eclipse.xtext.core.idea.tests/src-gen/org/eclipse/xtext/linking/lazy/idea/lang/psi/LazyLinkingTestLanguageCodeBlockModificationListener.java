package org.eclipse.xtext.linking.lazy.idea.lang.psi;

import com.intellij.psi.util.PsiModificationTracker;
import org.eclipse.xtext.linking.lazy.idea.lang.LazyLinkingTestLanguageLanguage;
import org.eclipse.xtext.psi.BaseXtextCodeBlockModificationListener;

public class LazyLinkingTestLanguageCodeBlockModificationListener extends BaseXtextCodeBlockModificationListener {

	public LazyLinkingTestLanguageCodeBlockModificationListener(PsiModificationTracker psiModificationTracker) {
		super(LazyLinkingTestLanguageLanguage.INSTANCE, psiModificationTracker);
	}

}
