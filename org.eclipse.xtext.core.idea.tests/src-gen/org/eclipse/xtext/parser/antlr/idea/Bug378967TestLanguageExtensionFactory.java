package org.eclipse.xtext.parser.antlr.idea;

import com.intellij.openapi.extensions.ExtensionFactory;
import org.eclipse.xtext.parser.antlr.idea.lang.Bug378967TestLanguageLanguage;

public class Bug378967TestLanguageExtensionFactory implements ExtensionFactory {

	@Override
	public Object createInstance(final String factoryArgument, final String implementationClass) {
		Class<?> clazz;
		try {
			clazz = Class.forName(implementationClass);
		} catch (ClassNotFoundException e) {
			throw new IllegalArgumentException("Couldn't load "+implementationClass, e);
		}
		return Bug378967TestLanguageLanguage.INSTANCE.<Object> getInstance(clazz);
	}

}
