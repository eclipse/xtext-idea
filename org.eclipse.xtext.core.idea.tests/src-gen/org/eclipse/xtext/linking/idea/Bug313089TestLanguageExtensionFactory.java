package org.eclipse.xtext.linking.idea;

import com.intellij.openapi.extensions.ExtensionFactory;
import org.eclipse.xtext.linking.idea.lang.Bug313089TestLanguageLanguage;

public class Bug313089TestLanguageExtensionFactory implements ExtensionFactory {

	@Override
	public Object createInstance(final String factoryArgument, final String implementationClass) {
		Class<?> clazz;
		try {
			clazz = Class.forName(implementationClass);
		} catch (ClassNotFoundException e) {
			throw new IllegalArgumentException("Couldn't load "+implementationClass, e);
		}
		return Bug313089TestLanguageLanguage.INSTANCE.<Object> getInstance(clazz);
	}

}
