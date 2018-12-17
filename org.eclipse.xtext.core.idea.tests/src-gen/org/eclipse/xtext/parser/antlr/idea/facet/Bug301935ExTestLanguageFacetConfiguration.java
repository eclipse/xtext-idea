package org.eclipse.xtext.parser.antlr.idea.facet;

import com.intellij.openapi.components.PersistentStateComponent;
import com.intellij.openapi.components.State;
import com.intellij.openapi.components.Storage;
import org.eclipse.xtext.idea.facet.AbstractFacetConfiguration;
import org.eclipse.xtext.idea.facet.GeneratorConfigurationState;

@State(name = "org.eclipse.xtext.parser.antlr.Bug301935ExTestLanguageGenerator", storages = {
		@Storage("Bug301935ExTestLanguageGeneratorConfig.xml")})
public class Bug301935ExTestLanguageFacetConfiguration extends AbstractFacetConfiguration implements PersistentStateComponent<GeneratorConfigurationState>{

}
