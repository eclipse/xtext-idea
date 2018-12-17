package org.eclipse.xtext.grammarinheritance.idea;

import com.intellij.openapi.extensions.ExtensionFactory;
import org.eclipse.xtext.grammarinheritance.idea.lang.InheritanceTest2LanguageLanguage;

public class InheritanceTest2LanguageExtensionFactory implements ExtensionFactory {

	@Override
	public Object createInstance(final String factoryArgument, final String implementationClass) {
		Class<?> clazz;
		try {
			clazz = Class.forName(implementationClass);
		} catch (ClassNotFoundException e) {
			throw new IllegalArgumentException("Couldn't load "+implementationClass, e);
		}
		return InheritanceTest2LanguageLanguage.INSTANCE.<Object> getInstance(clazz);
	}

}
