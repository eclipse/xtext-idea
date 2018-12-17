package org.eclipse.xtext.parsetree.unassignedtext.idea;

import com.intellij.openapi.extensions.ExtensionFactory;
import org.eclipse.xtext.parsetree.unassignedtext.idea.lang.UnassignedTextTestLanguageLanguage;

public class UnassignedTextTestLanguageExtensionFactory implements ExtensionFactory {

	@Override
	public Object createInstance(final String factoryArgument, final String implementationClass) {
		Class<?> clazz;
		try {
			clazz = Class.forName(implementationClass);
		} catch (ClassNotFoundException e) {
			throw new IllegalArgumentException("Couldn't load "+implementationClass, e);
		}
		return UnassignedTextTestLanguageLanguage.INSTANCE.<Object> getInstance(clazz);
	}

}
