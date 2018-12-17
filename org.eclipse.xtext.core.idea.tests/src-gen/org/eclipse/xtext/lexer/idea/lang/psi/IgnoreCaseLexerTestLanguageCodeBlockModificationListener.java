package org.eclipse.xtext.lexer.idea.lang.psi;

import com.intellij.psi.util.PsiModificationTracker;
import org.eclipse.xtext.lexer.idea.lang.IgnoreCaseLexerTestLanguageLanguage;
import org.eclipse.xtext.psi.BaseXtextCodeBlockModificationListener;

public class IgnoreCaseLexerTestLanguageCodeBlockModificationListener extends BaseXtextCodeBlockModificationListener {

	public IgnoreCaseLexerTestLanguageCodeBlockModificationListener(PsiModificationTracker psiModificationTracker) {
		super(IgnoreCaseLexerTestLanguageLanguage.INSTANCE, psiModificationTracker);
	}

}
