package org.eclipse.xtext.enumrules.idea;

import com.intellij.openapi.extensions.ExtensionFactory;
import org.eclipse.xtext.enumrules.idea.lang.EnumAndReferenceTestLanguageLanguage;

public class EnumAndReferenceTestLanguageExtensionFactory implements ExtensionFactory {

	@Override
	public Object createInstance(final String factoryArgument, final String implementationClass) {
		Class<?> clazz;
		try {
			clazz = Class.forName(implementationClass);
		} catch (ClassNotFoundException e) {
			throw new IllegalArgumentException("Couldn't load "+implementationClass, e);
		}
		return EnumAndReferenceTestLanguageLanguage.INSTANCE.<Object> getInstance(clazz);
	}

}
