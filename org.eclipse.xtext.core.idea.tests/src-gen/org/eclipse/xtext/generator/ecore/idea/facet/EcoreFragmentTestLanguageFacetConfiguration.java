package org.eclipse.xtext.generator.ecore.idea.facet;

import com.intellij.openapi.components.PersistentStateComponent;
import com.intellij.openapi.components.State;
import com.intellij.openapi.components.Storage;
import org.eclipse.xtext.idea.facet.AbstractFacetConfiguration;
import org.eclipse.xtext.idea.facet.GeneratorConfigurationState;

@State(name = "org.eclipse.xtext.generator.ecore.EcoreFragmentTestLanguageGenerator", storages = {
		@Storage("EcoreFragmentTestLanguageGeneratorConfig.xml")})
public class EcoreFragmentTestLanguageFacetConfiguration extends AbstractFacetConfiguration implements PersistentStateComponent<GeneratorConfigurationState>{

}
