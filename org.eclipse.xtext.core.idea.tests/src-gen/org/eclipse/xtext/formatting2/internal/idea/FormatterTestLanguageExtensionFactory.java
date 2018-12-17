package org.eclipse.xtext.formatting2.internal.idea;

import com.intellij.openapi.extensions.ExtensionFactory;
import org.eclipse.xtext.formatting2.internal.idea.lang.FormatterTestLanguageLanguage;

public class FormatterTestLanguageExtensionFactory implements ExtensionFactory {

	@Override
	public Object createInstance(final String factoryArgument, final String implementationClass) {
		Class<?> clazz;
		try {
			clazz = Class.forName(implementationClass);
		} catch (ClassNotFoundException e) {
			throw new IllegalArgumentException("Couldn't load "+implementationClass, e);
		}
		return FormatterTestLanguageLanguage.INSTANCE.<Object> getInstance(clazz);
	}

}
