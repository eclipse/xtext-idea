package org.eclipse.xtext.parser.parameters.idea.facet;

import com.intellij.openapi.components.*;
import org.eclipse.xtext.idea.facet.AbstractFacetConfiguration;
import org.eclipse.xtext.idea.facet.GeneratorConfigurationState;

@State(name = "org.eclipse.xtext.parser.parameters.TwoParametersTestLanguageGenerator", storages = {
		@Storage(id = "default", file = StoragePathMacros.PROJECT_FILE),
		@Storage(id = "dir", file = StoragePathMacros.PROJECT_CONFIG_DIR
				+ "/TwoParametersTestLanguageGeneratorConfig.xml", scheme = StorageScheme.DIRECTORY_BASED)})
public class TwoParametersTestLanguageFacetConfiguration extends AbstractFacetConfiguration implements PersistentStateComponent<GeneratorConfigurationState>{

}
