package org.eclipse.xtext.generator.ecore.idea;

import com.intellij.openapi.extensions.ExtensionFactory;
import org.eclipse.xtext.generator.ecore.idea.lang.EcoreFragmentTestLanguageLanguage;

public class EcoreFragmentTestLanguageExtensionFactory implements ExtensionFactory {

	@Override
	public Object createInstance(final String factoryArgument, final String implementationClass) {
		Class<?> clazz;
		try {
			clazz = Class.forName(implementationClass);
		} catch (ClassNotFoundException e) {
			throw new IllegalArgumentException("Couldn't load "+implementationClass, e);
		}
		return EcoreFragmentTestLanguageLanguage.INSTANCE.<Object> getInstance(clazz);
	}

}
