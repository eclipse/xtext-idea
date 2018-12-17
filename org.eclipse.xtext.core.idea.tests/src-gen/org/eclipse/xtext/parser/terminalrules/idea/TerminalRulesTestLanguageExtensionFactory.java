package org.eclipse.xtext.parser.terminalrules.idea;

import com.intellij.openapi.extensions.ExtensionFactory;
import org.eclipse.xtext.parser.terminalrules.idea.lang.TerminalRulesTestLanguageLanguage;

public class TerminalRulesTestLanguageExtensionFactory implements ExtensionFactory {

	@Override
	public Object createInstance(final String factoryArgument, final String implementationClass) {
		Class<?> clazz;
		try {
			clazz = Class.forName(implementationClass);
		} catch (ClassNotFoundException e) {
			throw new IllegalArgumentException("Couldn't load "+implementationClass, e);
		}
		return TerminalRulesTestLanguageLanguage.INSTANCE.<Object> getInstance(clazz);
	}

}
