package org.eclipse.xtext.linking.idea.lang;

import com.intellij.lang.Language;
import com.intellij.openapi.fileTypes.LanguageFileType;
import org.eclipse.xtext.idea.Icons;
import org.jetbrains.annotations.NonNls;

import javax.swing.*;

public class AbstractBug362902FileType extends LanguageFileType {

	@NonNls 
	public static final String DEFAULT_EXTENSION = "bug362902";

	protected AbstractBug362902FileType(final Language language) {
		super(language);
	}

	@Override
	public String getDefaultExtension() {
		return DEFAULT_EXTENSION;
	}

	@Override
	public String getDescription() {
		return "Bug362902 files";
	}

	@Override
	public Icon getIcon() {
		return Icons.DSL_FILE_TYPE;
	}

	@Override
	public String getName() {
		return "Bug362902";
	}

}
