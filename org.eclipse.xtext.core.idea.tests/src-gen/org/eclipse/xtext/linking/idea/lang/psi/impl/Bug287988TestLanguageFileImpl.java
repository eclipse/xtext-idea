package org.eclipse.xtext.linking.idea.lang.psi.impl;

import com.intellij.openapi.fileTypes.FileType;
import com.intellij.psi.FileViewProvider;
import org.eclipse.xtext.linking.idea.lang.Bug287988TestLanguageFileType;
import org.eclipse.xtext.linking.idea.lang.Bug287988TestLanguageLanguage;
import org.eclipse.xtext.psi.impl.BaseXtextFile;

public final class Bug287988TestLanguageFileImpl extends BaseXtextFile {

	public Bug287988TestLanguageFileImpl(FileViewProvider viewProvider) {
		super(viewProvider, Bug287988TestLanguageLanguage.INSTANCE);
	}

	@Override
	public FileType getFileType() {
		return Bug287988TestLanguageFileType.INSTANCE;
	}

}
