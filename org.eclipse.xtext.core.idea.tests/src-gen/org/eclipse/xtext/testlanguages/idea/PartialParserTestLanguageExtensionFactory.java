package org.eclipse.xtext.testlanguages.idea;

import com.intellij.openapi.extensions.ExtensionFactory;
import org.eclipse.xtext.testlanguages.idea.lang.PartialParserTestLanguageLanguage;

public class PartialParserTestLanguageExtensionFactory implements ExtensionFactory {

	@Override
	public Object createInstance(final String factoryArgument, final String implementationClass) {
		Class<?> clazz;
		try {
			clazz = Class.forName(implementationClass);
		} catch (ClassNotFoundException e) {
			throw new IllegalArgumentException("Couldn't load "+implementationClass, e);
		}
		return PartialParserTestLanguageLanguage.INSTANCE.<Object> getInstance(clazz);
	}

}
