package org.eclipse.xtext.serializer.idea.lang.psi.impl;

import com.intellij.openapi.fileTypes.FileType;
import com.intellij.psi.FileViewProvider;
import org.eclipse.xtext.psi.impl.BaseXtextFile;
import org.eclipse.xtext.serializer.idea.lang.AssignmentFinderTestLanguageFileType;
import org.eclipse.xtext.serializer.idea.lang.AssignmentFinderTestLanguageLanguage;

public final class AssignmentFinderTestLanguageFileImpl extends BaseXtextFile {

	public AssignmentFinderTestLanguageFileImpl(FileViewProvider viewProvider) {
		super(viewProvider, AssignmentFinderTestLanguageLanguage.INSTANCE);
	}

	@Override
	public FileType getFileType() {
		return AssignmentFinderTestLanguageFileType.INSTANCE;
	}

}
