package org.eclipse.xtext.parser.antlr.idea.facet;

import com.intellij.openapi.components.State;
import com.intellij.openapi.components.Storage;
import org.eclipse.xtext.idea.facet.AbstractFacetConfiguration;

@State(name = "org.eclipse.xtext.parser.antlr.Bug301935TestLanguageGenerator", storages = {
		@Storage("Bug301935TestLanguageGeneratorConfig.xml")})
public class Bug301935TestLanguageFacetConfiguration extends AbstractFacetConfiguration {
	
}
