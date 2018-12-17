package org.eclipse.xtext.serializer.idea.lang.psi.impl;

import com.intellij.openapi.fileTypes.FileType;
import com.intellij.psi.FileViewProvider;
import org.eclipse.xtext.psi.impl.BaseXtextFile;
import org.eclipse.xtext.serializer.idea.lang.HiddenTokenSequencerTestLanguageFileType;
import org.eclipse.xtext.serializer.idea.lang.HiddenTokenSequencerTestLanguageLanguage;

public final class HiddenTokenSequencerTestLanguageFileImpl extends BaseXtextFile {

	public HiddenTokenSequencerTestLanguageFileImpl(FileViewProvider viewProvider) {
		super(viewProvider, HiddenTokenSequencerTestLanguageLanguage.INSTANCE);
	}

	@Override
	public FileType getFileType() {
		return HiddenTokenSequencerTestLanguageFileType.INSTANCE;
	}

}
