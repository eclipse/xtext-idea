package org.eclipse.xtext.parser.antlr.idea.lang;

import com.intellij.lang.Language;
import com.intellij.openapi.fileTypes.LanguageFileType;
import org.eclipse.xtext.idea.Icons;
import org.jetbrains.annotations.NonNls;

import javax.swing.*;

public class AbstractBug289515TestLanguageFileType extends LanguageFileType {

	@NonNls 
	public static final String DEFAULT_EXTENSION = "bug289515testlanguage";

	protected AbstractBug289515TestLanguageFileType(final Language language) {
		super(language);
	}

	@Override
	public String getDefaultExtension() {
		return DEFAULT_EXTENSION;
	}

	@Override
	public String getDescription() {
		return "Bug289515TestLanguage files";
	}

	@Override
	public Icon getIcon() {
		return Icons.DSL_FILE_TYPE;
	}

	@Override
	public String getName() {
		return "Bug289515TestLanguage";
	}

}
