package org.eclipse.xtext.resource.idea.lang.psi.impl;

import com.intellij.openapi.fileTypes.FileType;
import com.intellij.psi.FileViewProvider;
import org.eclipse.xtext.psi.impl.BaseXtextFile;
import org.eclipse.xtext.resource.idea.lang.LiveContainerBuilderIntegerationTestLanguageFileType;
import org.eclipse.xtext.resource.idea.lang.LiveContainerBuilderIntegerationTestLanguageLanguage;

public final class LiveContainerBuilderIntegerationTestLanguageFileImpl extends BaseXtextFile {

	public LiveContainerBuilderIntegerationTestLanguageFileImpl(FileViewProvider viewProvider) {
		super(viewProvider, LiveContainerBuilderIntegerationTestLanguageLanguage.INSTANCE);
	}

	@Override
	public FileType getFileType() {
		return LiveContainerBuilderIntegerationTestLanguageFileType.INSTANCE;
	}

}
