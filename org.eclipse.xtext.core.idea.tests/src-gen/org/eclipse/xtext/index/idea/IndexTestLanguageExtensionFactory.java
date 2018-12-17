package org.eclipse.xtext.index.idea;

import com.intellij.openapi.extensions.ExtensionFactory;
import org.eclipse.xtext.index.idea.lang.IndexTestLanguageLanguage;

public class IndexTestLanguageExtensionFactory implements ExtensionFactory {

	@Override
	public Object createInstance(final String factoryArgument, final String implementationClass) {
		Class<?> clazz;
		try {
			clazz = Class.forName(implementationClass);
		} catch (ClassNotFoundException e) {
			throw new IllegalArgumentException("Couldn't load "+implementationClass, e);
		}
		return IndexTestLanguageLanguage.INSTANCE.<Object> getInstance(clazz);
	}

}
