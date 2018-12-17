package org.eclipse.xtext.serializer.idea;

import com.intellij.openapi.extensions.ExtensionFactory;
import org.eclipse.xtext.serializer.idea.lang.AssignmentFinderTestLanguageLanguage;

public class AssignmentFinderTestLanguageExtensionFactory implements ExtensionFactory {

	@Override
	public Object createInstance(final String factoryArgument, final String implementationClass) {
		Class<?> clazz;
		try {
			clazz = Class.forName(implementationClass);
		} catch (ClassNotFoundException e) {
			throw new IllegalArgumentException("Couldn't load "+implementationClass, e);
		}
		return AssignmentFinderTestLanguageLanguage.INSTANCE.<Object> getInstance(clazz);
	}

}
