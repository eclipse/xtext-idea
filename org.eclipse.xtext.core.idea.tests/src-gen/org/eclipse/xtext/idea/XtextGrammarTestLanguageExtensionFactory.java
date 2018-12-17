package org.eclipse.xtext.idea;

import com.intellij.openapi.extensions.ExtensionFactory;
import org.eclipse.xtext.idea.lang.XtextGrammarTestLanguageLanguage;

public class XtextGrammarTestLanguageExtensionFactory implements ExtensionFactory {

	@Override
	public Object createInstance(final String factoryArgument, final String implementationClass) {
		Class<?> clazz;
		try {
			clazz = Class.forName(implementationClass);
		} catch (ClassNotFoundException e) {
			throw new IllegalArgumentException("Couldn't load "+implementationClass, e);
		}
		return XtextGrammarTestLanguageLanguage.INSTANCE.<Object> getInstance(clazz);
	}

}
