package org.eclipse.xtext.metamodelreferencing.tests.idea;

import com.intellij.openapi.extensions.ExtensionFactory;
import org.eclipse.xtext.metamodelreferencing.tests.idea.lang.EcoreReferenceTestLanguageLanguage;

public class EcoreReferenceTestLanguageExtensionFactory implements ExtensionFactory {

	@Override
	public Object createInstance(final String factoryArgument, final String implementationClass) {
		Class<?> clazz;
		try {
			clazz = Class.forName(implementationClass);
		} catch (ClassNotFoundException e) {
			throw new IllegalArgumentException("Couldn't load "+implementationClass, e);
		}
		return EcoreReferenceTestLanguageLanguage.INSTANCE.<Object> getInstance(clazz);
	}

}
