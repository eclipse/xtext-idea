package org.eclipse.xtext.lexer.idea.facet;

import com.intellij.openapi.components.PersistentStateComponent;
import com.intellij.openapi.components.State;
import com.intellij.openapi.components.Storage;
import org.eclipse.xtext.idea.facet.AbstractFacetConfiguration;
import org.eclipse.xtext.idea.facet.GeneratorConfigurationState;

@State(name = "org.eclipse.xtext.lexer.IgnoreCaseLexerTestLanguageGenerator", storages = {
		@Storage("IgnoreCaseLexerTestLanguageGeneratorConfig.xml")})
public class IgnoreCaseLexerTestLanguageFacetConfiguration extends AbstractFacetConfiguration implements PersistentStateComponent<GeneratorConfigurationState>{

}
