package org.eclipse.xtext.xtext.ecoreInference.idea;

import com.intellij.openapi.extensions.ExtensionFactory;
import org.eclipse.xtext.xtext.ecoreInference.idea.lang.UnassignedRuleCallTestLanguageLanguage;

public class UnassignedRuleCallTestLanguageExtensionFactory implements ExtensionFactory {

	@Override
	public Object createInstance(final String factoryArgument, final String implementationClass) {
		Class<?> clazz;
		try {
			clazz = Class.forName(implementationClass);
		} catch (ClassNotFoundException e) {
			throw new IllegalArgumentException("Couldn't load "+implementationClass, e);
		}
		return UnassignedRuleCallTestLanguageLanguage.INSTANCE.<Object> getInstance(clazz);
	}

}
