package org.eclipse.xtext.linking.idea.facet;

import com.intellij.openapi.components.PersistentStateComponent;
import com.intellij.openapi.components.State;
import com.intellij.openapi.components.Storage;
import org.eclipse.xtext.idea.facet.AbstractFacetConfiguration;
import org.eclipse.xtext.idea.facet.GeneratorConfigurationState;

@State(name = "org.eclipse.xtext.linking.AbstractIgnoreCaseLinkingTestLanguageGenerator", storages = {
		@Storage("AbstractIgnoreCaseLinkingTestLanguageGeneratorConfig.xml")})
public class AbstractIgnoreCaseLinkingTestLanguageFacetConfiguration extends AbstractFacetConfiguration  implements PersistentStateComponent<GeneratorConfigurationState>{
}
