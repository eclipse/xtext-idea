package org.eclipse.xtext.grammarinheritance.idea.facet;

import com.intellij.openapi.components.State;
import com.intellij.openapi.components.Storage;
import org.eclipse.xtext.idea.facet.AbstractFacetConfiguration;

@State(name = "org.eclipse.xtext.grammarinheritance.InheritanceTestLanguageGenerator", storages = {
		@Storage("InheritanceTestLanguageGeneratorConfig.xml")})
public class InheritanceTestLanguageFacetConfiguration extends AbstractFacetConfiguration {
	
}
