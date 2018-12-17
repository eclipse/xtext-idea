package org.eclipse.xtext.valueconverter.idea;

import com.intellij.openapi.extensions.ExtensionFactory;
import org.eclipse.xtext.valueconverter.idea.lang.QualifiedNameTestLanguageLanguage;

public class QualifiedNameTestLanguageExtensionFactory implements ExtensionFactory {

	@Override
	public Object createInstance(final String factoryArgument, final String implementationClass) {
		Class<?> clazz;
		try {
			clazz = Class.forName(implementationClass);
		} catch (ClassNotFoundException e) {
			throw new IllegalArgumentException("Couldn't load "+implementationClass, e);
		}
		return QualifiedNameTestLanguageLanguage.INSTANCE.<Object> getInstance(clazz);
	}

}
