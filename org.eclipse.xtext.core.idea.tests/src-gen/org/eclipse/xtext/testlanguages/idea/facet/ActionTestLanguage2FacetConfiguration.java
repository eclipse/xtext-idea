package org.eclipse.xtext.testlanguages.idea.facet;

import com.intellij.openapi.components.PersistentStateComponent;
import com.intellij.openapi.components.State;
import com.intellij.openapi.components.Storage;
import org.eclipse.xtext.idea.facet.AbstractFacetConfiguration;
import org.eclipse.xtext.idea.facet.GeneratorConfigurationState;

@State(name = "org.eclipse.xtext.testlanguages.ActionTestLanguage2Generator", storages = {
		@Storage("ActionTestLanguage2GeneratorConfig.xml")})
public class ActionTestLanguage2FacetConfiguration extends AbstractFacetConfiguration implements PersistentStateComponent<GeneratorConfigurationState>{

}
