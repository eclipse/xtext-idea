package org.eclipse.xtext.parser.terminalrules.idea.facet;

import com.intellij.openapi.components.PersistentStateComponent;
import com.intellij.openapi.components.State;
import com.intellij.openapi.components.Storage;
import org.eclipse.xtext.idea.facet.AbstractFacetConfiguration;
import org.eclipse.xtext.idea.facet.GeneratorConfigurationState;

@State(name = "org.eclipse.xtext.parser.terminalrules.EcoreTerminalsTestLanguageGenerator", storages = {
		@Storage("EcoreTerminalsTestLanguageGeneratorConfig.xml")})
public class EcoreTerminalsTestLanguageFacetConfiguration extends AbstractFacetConfiguration implements PersistentStateComponent<GeneratorConfigurationState>{

}
