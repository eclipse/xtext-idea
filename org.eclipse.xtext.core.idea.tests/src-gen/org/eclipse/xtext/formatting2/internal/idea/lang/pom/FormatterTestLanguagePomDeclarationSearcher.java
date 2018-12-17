package org.eclipse.xtext.formatting2.internal.idea.lang.pom;

import org.eclipse.xtext.formatting2.internal.idea.lang.FormatterTestLanguageLanguage;
import org.eclipse.xtext.idea.pom.AbstractXtextPomDeclarationSearcher;

public class FormatterTestLanguagePomDeclarationSearcher extends AbstractXtextPomDeclarationSearcher {

	public FormatterTestLanguagePomDeclarationSearcher() {
		super(FormatterTestLanguageLanguage.INSTANCE);
	}

}
