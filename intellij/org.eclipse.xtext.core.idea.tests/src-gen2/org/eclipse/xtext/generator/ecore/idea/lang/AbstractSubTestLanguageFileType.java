/*
 * generated by Xtext
 */
package org.eclipse.xtext.generator.ecore.idea.lang;

import com.intellij.lang.Language;
import com.intellij.openapi.fileTypes.LanguageFileType;
import javax.swing.Icon;
import org.eclipse.xtext.idea.Icons;
import org.jetbrains.annotations.NonNls;

public class AbstractSubTestLanguageFileType extends LanguageFileType {

	@NonNls 
	public static final String DEFAULT_EXTENSION = "subtestlanguage";

	protected AbstractSubTestLanguageFileType(final Language language) {
		super(language);
	}

	@Override
	public String getDefaultExtension() {
		return DEFAULT_EXTENSION;
	}

	@Override
	public String getDescription() {
		return "SubTestLanguage files";
	}

	@Override
	public Icon getIcon() {
		return Icons.DSL_FILE_TYPE;
	}

	@Override
	public String getName() {
		return "SubTestLanguage";
	}

}
