package org.eclipse.xtext.grammarinheritance.idea.facet;

import com.intellij.openapi.components.PersistentStateComponent;
import com.intellij.openapi.components.State;
import com.intellij.openapi.components.Storage;
import org.eclipse.xtext.idea.facet.AbstractFacetConfiguration;
import org.eclipse.xtext.idea.facet.GeneratorConfigurationState;

@State(name = "org.eclipse.xtext.grammarinheritance.AbstractTestLanguageGenerator", storages = {
		@Storage("AbstractTestLanguageGeneratorConfig.xml")})
public class AbstractTestLanguageFacetConfiguration extends AbstractFacetConfiguration  implements PersistentStateComponent<GeneratorConfigurationState>{
	
}
