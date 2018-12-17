package org.eclipse.xtext.parser.bug419429.idea.lang;

import com.intellij.lang.Language;
import com.intellij.openapi.fileTypes.LanguageFileType;
import org.eclipse.xtext.idea.Icons;
import org.jetbrains.annotations.NonNls;

import javax.swing.*;

public class AbstractBug419429FileType extends LanguageFileType {

	@NonNls 
	public static final String DEFAULT_EXTENSION = "bug419429";

	protected AbstractBug419429FileType(final Language language) {
		super(language);
	}

	@Override
	public String getDefaultExtension() {
		return DEFAULT_EXTENSION;
	}

	@Override
	public String getDescription() {
		return "Bug419429 files";
	}

	@Override
	public Icon getIcon() {
		return Icons.DSL_FILE_TYPE;
	}

	@Override
	public String getName() {
		return "Bug419429";
	}

}
