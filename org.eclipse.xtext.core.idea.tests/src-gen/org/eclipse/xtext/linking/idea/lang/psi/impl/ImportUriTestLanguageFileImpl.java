package org.eclipse.xtext.linking.idea.lang.psi.impl;

import com.intellij.openapi.fileTypes.FileType;
import com.intellij.psi.FileViewProvider;
import org.eclipse.xtext.linking.idea.lang.ImportUriTestLanguageFileType;
import org.eclipse.xtext.linking.idea.lang.ImportUriTestLanguageLanguage;
import org.eclipse.xtext.psi.impl.BaseXtextFile;

public final class ImportUriTestLanguageFileImpl extends BaseXtextFile {

	public ImportUriTestLanguageFileImpl(FileViewProvider viewProvider) {
		super(viewProvider, ImportUriTestLanguageLanguage.INSTANCE);
	}

	@Override
	public FileType getFileType() {
		return ImportUriTestLanguageFileType.INSTANCE;
	}

}
