/*******************************************************************************
 * Copyright (c) 2015 itemis AG (http://www.itemis.eu) and others.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 *******************************************************************************/
package org.eclipse.xtext.idea.sdomain.idea.lang.psi.impl;

import com.intellij.openapi.fileTypes.FileType;
import com.intellij.psi.FileViewProvider;
import org.eclipse.xtext.idea.sdomain.idea.lang.SDomainFileType;
import org.eclipse.xtext.idea.sdomain.idea.lang.SDomainLanguage;
import org.eclipse.xtext.psi.impl.BaseXtextFile;

public final class SDomainFileImpl extends BaseXtextFile {

	public SDomainFileImpl(FileViewProvider viewProvider) {
		super(viewProvider, SDomainLanguage.INSTANCE);
	}

	@Override
	public FileType getFileType() {
		return SDomainFileType.INSTANCE;
	}
}
