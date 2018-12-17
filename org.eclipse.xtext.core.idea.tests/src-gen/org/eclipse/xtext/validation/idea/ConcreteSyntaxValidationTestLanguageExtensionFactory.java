package org.eclipse.xtext.validation.idea;

import com.intellij.openapi.extensions.ExtensionFactory;
import org.eclipse.xtext.validation.idea.lang.ConcreteSyntaxValidationTestLanguageLanguage;

public class ConcreteSyntaxValidationTestLanguageExtensionFactory implements ExtensionFactory {

	@Override
	public Object createInstance(final String factoryArgument, final String implementationClass) {
		Class<?> clazz;
		try {
			clazz = Class.forName(implementationClass);
		} catch (ClassNotFoundException e) {
			throw new IllegalArgumentException("Couldn't load "+implementationClass, e);
		}
		return ConcreteSyntaxValidationTestLanguageLanguage.INSTANCE.<Object> getInstance(clazz);
	}

}
