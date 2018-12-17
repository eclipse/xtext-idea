package org.eclipse.xtext.parsetree.unassignedtext.idea.facet;

import com.intellij.openapi.components.PersistentStateComponent;
import com.intellij.openapi.components.State;
import com.intellij.openapi.components.Storage;
import org.eclipse.xtext.idea.facet.AbstractFacetConfiguration;
import org.eclipse.xtext.idea.facet.GeneratorConfigurationState;

@State(name = "org.eclipse.xtext.parsetree.unassignedtext.UnassignedTextTestLanguageGenerator", storages = {
		@Storage("UnassignedTextTestLanguageGeneratorConfig.xml")})
public class UnassignedTextTestLanguageFacetConfiguration extends AbstractFacetConfiguration implements PersistentStateComponent<GeneratorConfigurationState>{

}
