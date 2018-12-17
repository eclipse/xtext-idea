package org.eclipse.xtext.parsetree.transientvalues.idea;

import com.intellij.openapi.extensions.ExtensionFactory;
import org.eclipse.xtext.parsetree.transientvalues.idea.lang.TransientValuesTestLanguage;

public class TransientValuesTestExtensionFactory implements ExtensionFactory {

	@Override
	public Object createInstance(final String factoryArgument, final String implementationClass) {
		Class<?> clazz;
		try {
			clazz = Class.forName(implementationClass);
		} catch (ClassNotFoundException e) {
			throw new IllegalArgumentException("Couldn't load "+implementationClass, e);
		}
		return TransientValuesTestLanguage.INSTANCE.<Object> getInstance(clazz);
	}

}
