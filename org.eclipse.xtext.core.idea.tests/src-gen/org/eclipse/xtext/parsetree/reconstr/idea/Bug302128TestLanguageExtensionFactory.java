package org.eclipse.xtext.parsetree.reconstr.idea;

import com.intellij.openapi.extensions.ExtensionFactory;
import org.eclipse.xtext.parsetree.reconstr.idea.lang.Bug302128TestLanguageLanguage;

public class Bug302128TestLanguageExtensionFactory implements ExtensionFactory {

	@Override
	public Object createInstance(final String factoryArgument, final String implementationClass) {
		Class<?> clazz;
		try {
			clazz = Class.forName(implementationClass);
		} catch (ClassNotFoundException e) {
			throw new IllegalArgumentException("Couldn't load "+implementationClass, e);
		}
		return Bug302128TestLanguageLanguage.INSTANCE.<Object> getInstance(clazz);
	}

}
