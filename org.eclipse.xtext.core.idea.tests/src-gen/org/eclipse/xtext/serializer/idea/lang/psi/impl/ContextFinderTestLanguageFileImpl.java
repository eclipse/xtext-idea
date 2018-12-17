package org.eclipse.xtext.serializer.idea.lang.psi.impl;

import com.intellij.openapi.fileTypes.FileType;
import com.intellij.psi.FileViewProvider;
import org.eclipse.xtext.psi.impl.BaseXtextFile;
import org.eclipse.xtext.serializer.idea.lang.ContextFinderTestLanguageFileType;
import org.eclipse.xtext.serializer.idea.lang.ContextFinderTestLanguageLanguage;

public final class ContextFinderTestLanguageFileImpl extends BaseXtextFile {

	public ContextFinderTestLanguageFileImpl(FileViewProvider viewProvider) {
		super(viewProvider, ContextFinderTestLanguageLanguage.INSTANCE);
	}

	@Override
	public FileType getFileType() {
		return ContextFinderTestLanguageFileType.INSTANCE;
	}

}
