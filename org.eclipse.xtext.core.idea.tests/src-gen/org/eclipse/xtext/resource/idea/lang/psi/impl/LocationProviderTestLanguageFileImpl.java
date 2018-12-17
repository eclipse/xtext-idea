package org.eclipse.xtext.resource.idea.lang.psi.impl;

import com.intellij.openapi.fileTypes.FileType;
import com.intellij.psi.FileViewProvider;
import org.eclipse.xtext.psi.impl.BaseXtextFile;
import org.eclipse.xtext.resource.idea.lang.LocationProviderTestLanguageFileType;
import org.eclipse.xtext.resource.idea.lang.LocationProviderTestLanguageLanguage;

public final class LocationProviderTestLanguageFileImpl extends BaseXtextFile {

	public LocationProviderTestLanguageFileImpl(FileViewProvider viewProvider) {
		super(viewProvider, LocationProviderTestLanguageLanguage.INSTANCE);
	}

	@Override
	public FileType getFileType() {
		return LocationProviderTestLanguageFileType.INSTANCE;
	}

}
