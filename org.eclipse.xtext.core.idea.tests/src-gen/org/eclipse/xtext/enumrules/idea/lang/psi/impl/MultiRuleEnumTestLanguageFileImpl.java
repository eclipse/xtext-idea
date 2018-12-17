package org.eclipse.xtext.enumrules.idea.lang.psi.impl;

import com.intellij.openapi.fileTypes.FileType;
import com.intellij.psi.FileViewProvider;
import org.eclipse.xtext.enumrules.idea.lang.MultiRuleEnumTestLanguageFileType;
import org.eclipse.xtext.enumrules.idea.lang.MultiRuleEnumTestLanguageLanguage;
import org.eclipse.xtext.psi.impl.BaseXtextFile;

public final class MultiRuleEnumTestLanguageFileImpl extends BaseXtextFile {

	public MultiRuleEnumTestLanguageFileImpl(FileViewProvider viewProvider) {
		super(viewProvider, MultiRuleEnumTestLanguageLanguage.INSTANCE);
	}

	@Override
	public FileType getFileType() {
		return MultiRuleEnumTestLanguageFileType.INSTANCE;
	}

}
