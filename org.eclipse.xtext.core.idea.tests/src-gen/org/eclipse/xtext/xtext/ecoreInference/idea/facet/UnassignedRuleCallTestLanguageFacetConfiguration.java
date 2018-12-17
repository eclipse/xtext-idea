package org.eclipse.xtext.xtext.ecoreInference.idea.facet;

import com.intellij.openapi.components.PersistentStateComponent;
import com.intellij.openapi.components.State;
import com.intellij.openapi.components.Storage;
import org.eclipse.xtext.idea.facet.AbstractFacetConfiguration;
import org.eclipse.xtext.idea.facet.GeneratorConfigurationState;

@State(name = "org.eclipse.xtext.xtext.ecoreInference.UnassignedRuleCallTestLanguageGenerator", storages = {
		@Storage("UnassignedRuleCallTestLanguageGeneratorConfig.xml")})
public class UnassignedRuleCallTestLanguageFacetConfiguration extends AbstractFacetConfiguration implements PersistentStateComponent<GeneratorConfigurationState>{

}
