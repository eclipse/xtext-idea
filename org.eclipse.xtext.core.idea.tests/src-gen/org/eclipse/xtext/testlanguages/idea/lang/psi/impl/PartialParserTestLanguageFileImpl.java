package org.eclipse.xtext.testlanguages.idea.lang.psi.impl;

import com.intellij.openapi.fileTypes.FileType;
import com.intellij.psi.FileViewProvider;
import org.eclipse.xtext.psi.impl.BaseXtextFile;
import org.eclipse.xtext.testlanguages.idea.lang.PartialParserTestLanguageFileType;
import org.eclipse.xtext.testlanguages.idea.lang.PartialParserTestLanguageLanguage;

public final class PartialParserTestLanguageFileImpl extends BaseXtextFile {

	public PartialParserTestLanguageFileImpl(FileViewProvider viewProvider) {
		super(viewProvider, PartialParserTestLanguageLanguage.INSTANCE);
	}

	@Override
	public FileType getFileType() {
		return PartialParserTestLanguageFileType.INSTANCE;
	}

}
