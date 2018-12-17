package org.eclipse.xtext.metamodelreferencing.tests.idea.facet;

import com.intellij.openapi.components.PersistentStateComponent;
import com.intellij.openapi.components.State;
import com.intellij.openapi.components.Storage;
import org.eclipse.xtext.idea.facet.AbstractFacetConfiguration;
import org.eclipse.xtext.idea.facet.GeneratorConfigurationState;

@State(name = "org.eclipse.xtext.metamodelreferencing.tests.MultiGenMMTestLanguageGenerator", storages = {
		@Storage("MultiGenMMTestLanguageGeneratorConfig.xml")})
public class MultiGenMMTestLanguageFacetConfiguration extends AbstractFacetConfiguration implements PersistentStateComponent<GeneratorConfigurationState>{

}
