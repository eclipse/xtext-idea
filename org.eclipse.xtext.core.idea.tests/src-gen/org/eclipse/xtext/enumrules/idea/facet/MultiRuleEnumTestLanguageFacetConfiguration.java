package org.eclipse.xtext.enumrules.idea.facet;

import com.intellij.openapi.components.PersistentStateComponent;
import com.intellij.openapi.components.State;
import com.intellij.openapi.components.Storage;
import org.eclipse.xtext.idea.facet.AbstractFacetConfiguration;
import org.eclipse.xtext.idea.facet.GeneratorConfigurationState;

@State(name = "org.eclipse.xtext.enumrules.MultiRuleEnumTestLanguageGenerator", storages = {
		@Storage("MultiRuleEnumTestLanguageGeneratorConfig.xml")})
public class MultiRuleEnumTestLanguageFacetConfiguration extends AbstractFacetConfiguration implements PersistentStateComponent<GeneratorConfigurationState>{

}
