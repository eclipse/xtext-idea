package org.eclipse.xtext.serializer.idea.facet;

import com.intellij.openapi.components.*;
import org.eclipse.xtext.idea.facet.AbstractFacetConfiguration;
import org.eclipse.xtext.idea.facet.GeneratorConfigurationState;

@State(name = "org.eclipse.xtext.serializer.SequencerTestLanguageGenerator", storages = {
		@Storage(id = "default", file = StoragePathMacros.PROJECT_FILE),
		@Storage(id = "dir", file = StoragePathMacros.PROJECT_CONFIG_DIR
				+ "/SequencerTestLanguageGeneratorConfig.xml", scheme = StorageScheme.DIRECTORY_BASED)})
public class SequencerTestLanguageFacetConfiguration extends AbstractFacetConfiguration implements PersistentStateComponent<GeneratorConfigurationState>{

}
