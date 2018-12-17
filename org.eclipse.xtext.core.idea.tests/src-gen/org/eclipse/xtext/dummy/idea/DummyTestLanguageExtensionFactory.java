package org.eclipse.xtext.dummy.idea;

import com.intellij.openapi.extensions.ExtensionFactory;
import org.eclipse.xtext.dummy.idea.lang.DummyTestLanguageLanguage;

public class DummyTestLanguageExtensionFactory implements ExtensionFactory {

	@Override
	public Object createInstance(final String factoryArgument, final String implementationClass) {
		Class<?> clazz;
		try {
			clazz = Class.forName(implementationClass);
		} catch (ClassNotFoundException e) {
			throw new IllegalArgumentException("Couldn't load "+implementationClass, e);
		}
		return DummyTestLanguageLanguage.INSTANCE.<Object> getInstance(clazz);
	}

}
