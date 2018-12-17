package org.eclipse.xtext.resource.idea;

import com.intellij.openapi.extensions.ExtensionFactory;
import org.eclipse.xtext.resource.idea.lang.Bug385636Language;

public class Bug385636ExtensionFactory implements ExtensionFactory {

	@Override
	public Object createInstance(final String factoryArgument, final String implementationClass) {
		Class<?> clazz;
		try {
			clazz = Class.forName(implementationClass);
		} catch (ClassNotFoundException e) {
			throw new IllegalArgumentException("Couldn't load "+implementationClass, e);
		}
		return Bug385636Language.INSTANCE.<Object> getInstance(clazz);
	}

}
