package org.eclipse.xtext.valueconverter.idea.facet;

import com.intellij.openapi.components.PersistentStateComponent;
import com.intellij.openapi.components.State;
import com.intellij.openapi.components.Storage;
import org.eclipse.xtext.idea.facet.AbstractFacetConfiguration;
import org.eclipse.xtext.idea.facet.GeneratorConfigurationState;

@State(name = "org.eclipse.xtext.valueconverter.QualifiedNameTestLanguageGenerator", storages = {
		@Storage("QualifiedNameTestLanguageGeneratorConfig.xml")})
public class QualifiedNameTestLanguageFacetConfiguration extends AbstractFacetConfiguration implements PersistentStateComponent<GeneratorConfigurationState>{

}
