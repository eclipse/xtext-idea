package org.eclipse.xtext.xtext.ecoreInference.idea.facet;

import com.intellij.openapi.components.PersistentStateComponent;
import com.intellij.openapi.components.State;
import com.intellij.openapi.components.Storage;
import org.eclipse.xtext.idea.facet.AbstractFacetConfiguration;
import org.eclipse.xtext.idea.facet.GeneratorConfigurationState;

@State(name = "org.eclipse.xtext.xtext.ecoreInference.MultiValueFeatureTestLanguageGenerator", storages = {
		@Storage("MultiValueFeatureTestLanguageGeneratorConfig.xml")})
public class MultiValueFeatureTestLanguageFacetConfiguration extends AbstractFacetConfiguration implements PersistentStateComponent<GeneratorConfigurationState>{

}
