package org.eclipse.xtext.resource.idea.facet;

import com.intellij.openapi.components.PersistentStateComponent;
import com.intellij.openapi.components.State;
import com.intellij.openapi.components.Storage;
import org.eclipse.xtext.idea.facet.AbstractFacetConfiguration;
import org.eclipse.xtext.idea.facet.GeneratorConfigurationState;

@State(name = "org.eclipse.xtext.resource.EObjectAtOffsetTestLanguageGenerator", storages = {
		@Storage("EObjectAtOffsetTestLanguageGeneratorConfig.xml")})
public class EObjectAtOffsetTestLanguageFacetConfiguration extends AbstractFacetConfiguration implements PersistentStateComponent<GeneratorConfigurationState>{

}
