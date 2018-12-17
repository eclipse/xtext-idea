package org.eclipse.xtext.linking.idea.lang.psi.impl;

import com.intellij.openapi.fileTypes.FileType;
import com.intellij.psi.FileViewProvider;
import org.eclipse.xtext.linking.idea.lang.LangATestLanguageFileType;
import org.eclipse.xtext.linking.idea.lang.LangATestLanguageLanguage;
import org.eclipse.xtext.psi.impl.BaseXtextFile;

public final class LangATestLanguageFileImpl extends BaseXtextFile {

	public LangATestLanguageFileImpl(FileViewProvider viewProvider) {
		super(viewProvider, LangATestLanguageLanguage.INSTANCE);
	}

	@Override
	public FileType getFileType() {
		return LangATestLanguageFileType.INSTANCE;
	}

}
