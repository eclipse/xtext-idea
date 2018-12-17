package org.eclipse.xtext.parser.encoding.idea;

import com.intellij.openapi.extensions.ExtensionFactory;
import org.eclipse.xtext.parser.encoding.idea.lang.EncodingTestLanguageLanguage;

public class EncodingTestLanguageExtensionFactory implements ExtensionFactory {

	@Override
	public Object createInstance(final String factoryArgument, final String implementationClass) {
		Class<?> clazz;
		try {
			clazz = Class.forName(implementationClass);
		} catch (ClassNotFoundException e) {
			throw new IllegalArgumentException("Couldn't load "+implementationClass, e);
		}
		return EncodingTestLanguageLanguage.INSTANCE.<Object> getInstance(clazz);
	}

}
