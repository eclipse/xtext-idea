package org.eclipse.xtext.grammarinheritance.idea.facet;

import com.intellij.openapi.components.PersistentStateComponent;
import com.intellij.openapi.components.State;
import com.intellij.openapi.components.Storage;
import org.eclipse.xtext.idea.facet.AbstractFacetConfiguration;
import org.eclipse.xtext.idea.facet.GeneratorConfigurationState;

@State(name = "org.eclipse.xtext.grammarinheritance.ConcreteTestLanguageGenerator", storages = {
		@Storage("ConcreteTestLanguageGeneratorConfig.xml")})
public class ConcreteTestLanguageFacetConfiguration extends AbstractFacetConfiguration implements PersistentStateComponent<GeneratorConfigurationState>{

}
