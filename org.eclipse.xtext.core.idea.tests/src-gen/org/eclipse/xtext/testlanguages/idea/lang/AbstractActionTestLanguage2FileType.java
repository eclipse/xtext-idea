package org.eclipse.xtext.testlanguages.idea.lang;

import com.intellij.lang.Language;
import com.intellij.openapi.fileTypes.LanguageFileType;
import org.eclipse.xtext.idea.Icons;
import org.jetbrains.annotations.NonNls;

import javax.swing.*;

public class AbstractActionTestLanguage2FileType extends LanguageFileType {

	@NonNls 
	public static final String DEFAULT_EXTENSION = "actiontestlanguage2";

	protected AbstractActionTestLanguage2FileType(final Language language) {
		super(language);
	}

	@Override
	public String getDefaultExtension() {
		return DEFAULT_EXTENSION;
	}

	@Override
	public String getDescription() {
		return "ActionTestLanguage2 files";
	}

	@Override
	public Icon getIcon() {
		return Icons.DSL_FILE_TYPE;
	}

	@Override
	public String getName() {
		return "ActionTestLanguage2";
	}

}
