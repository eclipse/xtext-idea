package org.eclipse.xtext.testlanguages.idea;

import com.intellij.openapi.extensions.ExtensionFactory;
import org.eclipse.xtext.testlanguages.idea.lang.TestLanguageLanguage;

public class TestLanguageExtensionFactory implements ExtensionFactory {

	@Override
	public Object createInstance(final String factoryArgument, final String implementationClass) {
		Class<?> clazz;
		try {
			clazz = Class.forName(implementationClass);
		} catch (ClassNotFoundException e) {
			throw new IllegalArgumentException("Couldn't load "+implementationClass, e);
		}
		return TestLanguageLanguage.INSTANCE.<Object> getInstance(clazz);
	}

}
