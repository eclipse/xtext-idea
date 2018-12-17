package org.eclipse.xtext.dummy.idea.lang.psi.impl;

import com.intellij.openapi.fileTypes.FileType;
import com.intellij.psi.FileViewProvider;
import org.eclipse.xtext.dummy.idea.lang.DummyTestLanguageFileType;
import org.eclipse.xtext.dummy.idea.lang.DummyTestLanguageLanguage;
import org.eclipse.xtext.psi.impl.BaseXtextFile;

public final class DummyTestLanguageFileImpl extends BaseXtextFile {

	public DummyTestLanguageFileImpl(FileViewProvider viewProvider) {
		super(viewProvider, DummyTestLanguageLanguage.INSTANCE);
	}

	@Override
	public FileType getFileType() {
		return DummyTestLanguageFileType.INSTANCE;
	}

}
