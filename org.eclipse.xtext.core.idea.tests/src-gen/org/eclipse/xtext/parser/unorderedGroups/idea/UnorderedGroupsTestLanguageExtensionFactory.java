package org.eclipse.xtext.parser.unorderedGroups.idea;

import com.intellij.openapi.extensions.ExtensionFactory;
import org.eclipse.xtext.parser.unorderedGroups.idea.lang.UnorderedGroupsTestLanguageLanguage;

public class UnorderedGroupsTestLanguageExtensionFactory implements ExtensionFactory {

	@Override
	public Object createInstance(final String factoryArgument, final String implementationClass) {
		Class<?> clazz;
		try {
			clazz = Class.forName(implementationClass);
		} catch (ClassNotFoundException e) {
			throw new IllegalArgumentException("Couldn't load "+implementationClass, e);
		}
		return UnorderedGroupsTestLanguageLanguage.INSTANCE.<Object> getInstance(clazz);
	}

}
