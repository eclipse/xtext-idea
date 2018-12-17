package org.eclipse.xtext.linking.idea.lang.psi.impl;

import com.intellij.openapi.fileTypes.FileType;
import com.intellij.psi.FileViewProvider;
import org.eclipse.xtext.linking.idea.lang.IgnoreCaseNamespacesTestLanguageFileType;
import org.eclipse.xtext.linking.idea.lang.IgnoreCaseNamespacesTestLanguageLanguage;
import org.eclipse.xtext.psi.impl.BaseXtextFile;

public final class IgnoreCaseNamespacesTestLanguageFileImpl extends BaseXtextFile {

	public IgnoreCaseNamespacesTestLanguageFileImpl(FileViewProvider viewProvider) {
		super(viewProvider, IgnoreCaseNamespacesTestLanguageLanguage.INSTANCE);
	}

	@Override
	public FileType getFileType() {
		return IgnoreCaseNamespacesTestLanguageFileType.INSTANCE;
	}

}
