package org.eclipse.xtext.resource.idea.lang.psi.impl;

import com.intellij.openapi.fileTypes.FileType;
import com.intellij.psi.FileViewProvider;
import org.eclipse.xtext.psi.impl.BaseXtextFile;
import org.eclipse.xtext.resource.idea.lang.LiveContainerTestLanguageFileType;
import org.eclipse.xtext.resource.idea.lang.LiveContainerTestLanguageLanguage;

public final class LiveContainerTestLanguageFileImpl extends BaseXtextFile {

	public LiveContainerTestLanguageFileImpl(FileViewProvider viewProvider) {
		super(viewProvider, LiveContainerTestLanguageLanguage.INSTANCE);
	}

	@Override
	public FileType getFileType() {
		return LiveContainerTestLanguageFileType.INSTANCE;
	}

}
