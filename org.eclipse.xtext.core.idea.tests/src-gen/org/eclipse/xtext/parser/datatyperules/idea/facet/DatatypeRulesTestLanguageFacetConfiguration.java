package org.eclipse.xtext.parser.datatyperules.idea.facet;

import com.intellij.openapi.components.PersistentStateComponent;
import com.intellij.openapi.components.State;
import com.intellij.openapi.components.Storage;
import org.eclipse.xtext.idea.facet.AbstractFacetConfiguration;
import org.eclipse.xtext.idea.facet.GeneratorConfigurationState;

@State(name = "org.eclipse.xtext.parser.datatyperules.DatatypeRulesTestLanguageGenerator", storages = {
		@Storage("DatatypeRulesTestLanguageGeneratorConfig.xml")})
public class DatatypeRulesTestLanguageFacetConfiguration extends AbstractFacetConfiguration implements PersistentStateComponent<GeneratorConfigurationState>{

}
