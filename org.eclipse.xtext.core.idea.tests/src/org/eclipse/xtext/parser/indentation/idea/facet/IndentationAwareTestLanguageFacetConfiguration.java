package org.eclipse.xtext.parser.indentation.idea.facet;

import com.intellij.openapi.components.State;
import com.intellij.openapi.components.Storage;
import org.eclipse.xtext.idea.facet.AbstractFacetConfiguration;

@State(name = "org.eclipse.xtext.parser.indentation.IndentationAwareTestLanguageGenerator", storages = {
		@Storage("IndentationAwareTestLanguageGeneratorConfig.xml")})
public class IndentationAwareTestLanguageFacetConfiguration extends AbstractFacetConfiguration {
	
}
