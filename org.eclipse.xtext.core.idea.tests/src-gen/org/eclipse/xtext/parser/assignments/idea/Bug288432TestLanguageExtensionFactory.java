package org.eclipse.xtext.parser.assignments.idea;

import com.intellij.openapi.extensions.ExtensionFactory;
import org.eclipse.xtext.parser.assignments.idea.lang.Bug288432TestLanguageLanguage;

public class Bug288432TestLanguageExtensionFactory implements ExtensionFactory {

	@Override
	public Object createInstance(final String factoryArgument, final String implementationClass) {
		Class<?> clazz;
		try {
			clazz = Class.forName(implementationClass);
		} catch (ClassNotFoundException e) {
			throw new IllegalArgumentException("Couldn't load "+implementationClass, e);
		}
		return Bug288432TestLanguageLanguage.INSTANCE.<Object> getInstance(clazz);
	}

}
