package org.eclipse.xtext.formatting2.regionaccess.internal.idea;

import com.intellij.openapi.extensions.ExtensionFactory;
import org.eclipse.xtext.formatting2.regionaccess.internal.idea.lang.RegionAccessTestLanguageLanguage;

public class RegionAccessTestLanguageExtensionFactory implements ExtensionFactory {

	@Override
	public Object createInstance(final String factoryArgument, final String implementationClass) {
		Class<?> clazz;
		try {
			clazz = Class.forName(implementationClass);
		} catch (ClassNotFoundException e) {
			throw new IllegalArgumentException("Couldn't load "+implementationClass, e);
		}
		return RegionAccessTestLanguageLanguage.INSTANCE.<Object> getInstance(clazz);
	}

}
