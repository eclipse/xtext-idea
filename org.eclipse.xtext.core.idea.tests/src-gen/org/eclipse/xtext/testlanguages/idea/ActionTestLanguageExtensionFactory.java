package org.eclipse.xtext.testlanguages.idea;

import com.intellij.openapi.extensions.ExtensionFactory;
import org.eclipse.xtext.testlanguages.idea.lang.ActionTestLanguageLanguage;

public class ActionTestLanguageExtensionFactory implements ExtensionFactory {

	@Override
	public Object createInstance(final String factoryArgument, final String implementationClass) {
		Class<?> clazz;
		try {
			clazz = Class.forName(implementationClass);
		} catch (ClassNotFoundException e) {
			throw new IllegalArgumentException("Couldn't load "+implementationClass, e);
		}
		return ActionTestLanguageLanguage.INSTANCE.<Object> getInstance(clazz);
	}

}
