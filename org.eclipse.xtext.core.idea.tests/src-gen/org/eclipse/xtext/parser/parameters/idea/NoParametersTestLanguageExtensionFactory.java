package org.eclipse.xtext.parser.parameters.idea;

import com.intellij.openapi.extensions.ExtensionFactory;
import org.eclipse.xtext.parser.parameters.idea.lang.NoParametersTestLanguageLanguage;

public class NoParametersTestLanguageExtensionFactory implements ExtensionFactory {

	@Override
	public Object createInstance(final String factoryArgument, final String implementationClass) {
		Class<?> clazz;
		try {
			clazz = Class.forName(implementationClass);
		} catch (ClassNotFoundException e) {
			throw new IllegalArgumentException("Couldn't load "+implementationClass, e);
		}
		return NoParametersTestLanguageLanguage.INSTANCE.<Object> getInstance(clazz);
	}

}
