package org.eclipse.xtext.parser.terminalrules.idea;

import com.intellij.openapi.extensions.ExtensionFactory;
import org.eclipse.xtext.parser.terminalrules.idea.lang.Bug292245TestLanguageLanguage;

public class Bug292245TestLanguageExtensionFactory implements ExtensionFactory {

	@Override
	public Object createInstance(final String factoryArgument, final String implementationClass) {
		Class<?> clazz;
		try {
			clazz = Class.forName(implementationClass);
		} catch (ClassNotFoundException e) {
			throw new IllegalArgumentException("Couldn't load "+implementationClass, e);
		}
		return Bug292245TestLanguageLanguage.INSTANCE.<Object> getInstance(clazz);
	}

}
