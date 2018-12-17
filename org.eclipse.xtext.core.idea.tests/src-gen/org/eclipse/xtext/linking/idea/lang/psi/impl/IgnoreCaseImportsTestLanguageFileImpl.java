package org.eclipse.xtext.linking.idea.lang.psi.impl;

import com.intellij.openapi.fileTypes.FileType;
import com.intellij.psi.FileViewProvider;
import org.eclipse.xtext.linking.idea.lang.IgnoreCaseImportsTestLanguageFileType;
import org.eclipse.xtext.linking.idea.lang.IgnoreCaseImportsTestLanguageLanguage;
import org.eclipse.xtext.psi.impl.BaseXtextFile;

public final class IgnoreCaseImportsTestLanguageFileImpl extends BaseXtextFile {

	public IgnoreCaseImportsTestLanguageFileImpl(FileViewProvider viewProvider) {
		super(viewProvider, IgnoreCaseImportsTestLanguageLanguage.INSTANCE);
	}

	@Override
	public FileType getFileType() {
		return IgnoreCaseImportsTestLanguageFileType.INSTANCE;
	}

}
