package org.eclipse.xtext.testlanguages.indent.idea;

import com.intellij.openapi.extensions.ExtensionFactory;
import org.eclipse.xtext.testlanguages.indent.idea.lang.IndentationAwareTestLanguageLanguage;

public class IndentationAwareTestLanguageExtensionFactory implements ExtensionFactory {

	@Override
	public Object createInstance(final String factoryArgument, final String implementationClass) {
		Class<?> clazz;
		try {
			clazz = Class.forName(implementationClass);
		} catch (ClassNotFoundException e) {
			throw new IllegalArgumentException("Couldn't load "+implementationClass, e);
		}
		return IndentationAwareTestLanguageLanguage.INSTANCE.<Object> getInstance(clazz);
	}

}
