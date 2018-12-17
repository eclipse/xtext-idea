package org.eclipse.xtext.linking.lazy.idea;

import com.intellij.openapi.extensions.ExtensionFactory;
import org.eclipse.xtext.linking.lazy.idea.lang.LazyLinkingTestLanguageLanguage;

public class LazyLinkingTestLanguageExtensionFactory implements ExtensionFactory {

	@Override
	public Object createInstance(final String factoryArgument, final String implementationClass) {
		Class<?> clazz;
		try {
			clazz = Class.forName(implementationClass);
		} catch (ClassNotFoundException e) {
			throw new IllegalArgumentException("Couldn't load "+implementationClass, e);
		}
		return LazyLinkingTestLanguageLanguage.INSTANCE.<Object> getInstance(clazz);
	}

}
