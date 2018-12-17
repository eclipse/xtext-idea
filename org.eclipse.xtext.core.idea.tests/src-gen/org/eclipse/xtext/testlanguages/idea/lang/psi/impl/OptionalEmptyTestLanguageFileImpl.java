package org.eclipse.xtext.testlanguages.idea.lang.psi.impl;

import com.intellij.openapi.fileTypes.FileType;
import com.intellij.psi.FileViewProvider;
import org.eclipse.xtext.psi.impl.BaseXtextFile;
import org.eclipse.xtext.testlanguages.idea.lang.OptionalEmptyTestLanguageFileType;
import org.eclipse.xtext.testlanguages.idea.lang.OptionalEmptyTestLanguageLanguage;

public final class OptionalEmptyTestLanguageFileImpl extends BaseXtextFile {

	public OptionalEmptyTestLanguageFileImpl(FileViewProvider viewProvider) {
		super(viewProvider, OptionalEmptyTestLanguageLanguage.INSTANCE);
	}

	@Override
	public FileType getFileType() {
		return OptionalEmptyTestLanguageFileType.INSTANCE;
	}

}
