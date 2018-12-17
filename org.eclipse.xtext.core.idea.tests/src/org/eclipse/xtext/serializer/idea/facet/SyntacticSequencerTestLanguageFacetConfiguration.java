package org.eclipse.xtext.serializer.idea.facet;

import com.intellij.openapi.components.State;
import com.intellij.openapi.components.Storage;
import org.eclipse.xtext.idea.facet.AbstractFacetConfiguration;

@State(name = "org.eclipse.xtext.serializer.SyntacticSequencerTestLanguageGenerator", storages = {
		@Storage("SyntacticSequencerTestLanguageGeneratorConfig.xml")})
public class SyntacticSequencerTestLanguageFacetConfiguration extends AbstractFacetConfiguration {
	
}
