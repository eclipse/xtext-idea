package org.eclipse.xtext.resource.idea.lang;

import com.intellij.lang.Language;
import com.intellij.openapi.fileTypes.LanguageFileType;
import org.eclipse.xtext.idea.Icons;
import org.jetbrains.annotations.NonNls;

import javax.swing.*;

public class AbstractEObjectAtOffsetTestLanguageFileType extends LanguageFileType {

	@NonNls 
	public static final String DEFAULT_EXTENSION = "eobjectatoffsettestlanguage";

	protected AbstractEObjectAtOffsetTestLanguageFileType(final Language language) {
		super(language);
	}

	@Override
	public String getDefaultExtension() {
		return DEFAULT_EXTENSION;
	}

	@Override
	public String getDescription() {
		return "EObjectAtOffsetTestLanguage files";
	}

	@Override
	public Icon getIcon() {
		return Icons.DSL_FILE_TYPE;
	}

	@Override
	public String getName() {
		return "EObjectAtOffsetTestLanguage";
	}

}
