package org.eclipse.xtext.parser.fragments.idea;

import com.intellij.openapi.extensions.ExtensionFactory;
import org.eclipse.xtext.parser.fragments.idea.lang.FragmentTestLanguageExLanguage;

public class FragmentTestLanguageExExtensionFactory implements ExtensionFactory {

	@Override
	public Object createInstance(final String factoryArgument, final String implementationClass) {
		Class<?> clazz;
		try {
			clazz = Class.forName(implementationClass);
		} catch (ClassNotFoundException e) {
			throw new IllegalArgumentException("Couldn't load "+implementationClass, e);
		}
		return FragmentTestLanguageExLanguage.INSTANCE.<Object> getInstance(clazz);
	}

}
