package org.eclipse.xtext.resource.idea.facet;

import com.intellij.openapi.components.*;
import org.eclipse.xtext.idea.facet.AbstractFacetConfiguration;
import org.eclipse.xtext.idea.facet.GeneratorConfigurationState;

@State(name = "org.eclipse.xtext.resource.LocationProviderTestLanguageGenerator", storages = {
		@Storage(id = "default", file = StoragePathMacros.PROJECT_FILE),
		@Storage(id = "dir", file = StoragePathMacros.PROJECT_CONFIG_DIR
				+ "/LocationProviderTestLanguageGeneratorConfig.xml", scheme = StorageScheme.DIRECTORY_BASED)})
public class LocationProviderTestLanguageFacetConfiguration extends AbstractFacetConfiguration implements PersistentStateComponent<GeneratorConfigurationState>{

}
