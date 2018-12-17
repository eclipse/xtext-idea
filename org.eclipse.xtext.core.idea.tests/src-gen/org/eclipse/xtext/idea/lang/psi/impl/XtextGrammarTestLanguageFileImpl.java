package org.eclipse.xtext.idea.lang.psi.impl;

import com.intellij.openapi.fileTypes.FileType;
import com.intellij.psi.FileViewProvider;
import org.eclipse.xtext.idea.lang.XtextGrammarTestLanguageFileType;
import org.eclipse.xtext.idea.lang.XtextGrammarTestLanguageLanguage;
import org.eclipse.xtext.psi.impl.BaseXtextFile;

public final class XtextGrammarTestLanguageFileImpl extends BaseXtextFile {

	public XtextGrammarTestLanguageFileImpl(FileViewProvider viewProvider) {
		super(viewProvider, XtextGrammarTestLanguageLanguage.INSTANCE);
	}

	@Override
	public FileType getFileType() {
		return XtextGrammarTestLanguageFileType.INSTANCE;
	}

}
