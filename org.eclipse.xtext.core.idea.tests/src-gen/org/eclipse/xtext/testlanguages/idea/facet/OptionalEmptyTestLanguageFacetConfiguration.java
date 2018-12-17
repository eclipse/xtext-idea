package org.eclipse.xtext.testlanguages.idea.facet;

import com.intellij.openapi.components.PersistentStateComponent;
import com.intellij.openapi.components.State;
import com.intellij.openapi.components.Storage;
import org.eclipse.xtext.idea.facet.AbstractFacetConfiguration;
import org.eclipse.xtext.idea.facet.GeneratorConfigurationState;

@State(name = "org.eclipse.xtext.testlanguages.OptionalEmptyTestLanguageGenerator", storages = {
		@Storage("OptionalEmptyTestLanguageGeneratorConfig.xml")})
public class OptionalEmptyTestLanguageFacetConfiguration extends AbstractFacetConfiguration implements PersistentStateComponent<GeneratorConfigurationState>{

}
