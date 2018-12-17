package org.eclipse.xtext.grammarinheritance.idea.facet;

import com.intellij.openapi.components.PersistentStateComponent;
import com.intellij.openapi.components.State;
import com.intellij.openapi.components.Storage;
import org.eclipse.xtext.idea.facet.AbstractFacetConfiguration;
import org.eclipse.xtext.idea.facet.GeneratorConfigurationState;

@State(name = "org.eclipse.xtext.grammarinheritance.BaseInheritanceTestLanguageGenerator", storages = {
		@Storage("BaseInheritanceTestLanguageGeneratorConfig.xml")})
public class BaseInheritanceTestLanguageFacetConfiguration extends AbstractFacetConfiguration implements PersistentStateComponent<GeneratorConfigurationState>{

}
