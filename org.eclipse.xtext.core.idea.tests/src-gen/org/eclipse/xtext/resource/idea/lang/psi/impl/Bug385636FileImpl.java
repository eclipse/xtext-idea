package org.eclipse.xtext.resource.idea.lang.psi.impl;

import com.intellij.openapi.fileTypes.FileType;
import com.intellij.psi.FileViewProvider;
import org.eclipse.xtext.psi.impl.BaseXtextFile;
import org.eclipse.xtext.resource.idea.lang.Bug385636FileType;
import org.eclipse.xtext.resource.idea.lang.Bug385636Language;

public final class Bug385636FileImpl extends BaseXtextFile {

	public Bug385636FileImpl(FileViewProvider viewProvider) {
		super(viewProvider, Bug385636Language.INSTANCE);
	}

	@Override
	public FileType getFileType() {
		return Bug385636FileType.INSTANCE;
	}

}
