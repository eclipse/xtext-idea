package org.eclipse.xtext.linking.idea.lang.psi.impl;

import com.intellij.openapi.fileTypes.FileType;
import com.intellij.psi.FileViewProvider;
import org.eclipse.xtext.linking.idea.lang.Bug362902FileType;
import org.eclipse.xtext.linking.idea.lang.Bug362902Language;
import org.eclipse.xtext.psi.impl.BaseXtextFile;

public final class Bug362902FileImpl extends BaseXtextFile {

	public Bug362902FileImpl(FileViewProvider viewProvider) {
		super(viewProvider, Bug362902Language.INSTANCE);
	}

	@Override
	public FileType getFileType() {
		return Bug362902FileType.INSTANCE;
	}

}
