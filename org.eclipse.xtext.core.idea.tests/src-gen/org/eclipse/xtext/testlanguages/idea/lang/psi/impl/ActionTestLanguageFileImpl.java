package org.eclipse.xtext.testlanguages.idea.lang.psi.impl;

import com.intellij.openapi.fileTypes.FileType;
import com.intellij.psi.FileViewProvider;
import org.eclipse.xtext.psi.impl.BaseXtextFile;
import org.eclipse.xtext.testlanguages.idea.lang.ActionTestLanguageFileType;
import org.eclipse.xtext.testlanguages.idea.lang.ActionTestLanguageLanguage;

public final class ActionTestLanguageFileImpl extends BaseXtextFile {

	public ActionTestLanguageFileImpl(FileViewProvider viewProvider) {
		super(viewProvider, ActionTestLanguageLanguage.INSTANCE);
	}

	@Override
	public FileType getFileType() {
		return ActionTestLanguageFileType.INSTANCE;
	}

}
