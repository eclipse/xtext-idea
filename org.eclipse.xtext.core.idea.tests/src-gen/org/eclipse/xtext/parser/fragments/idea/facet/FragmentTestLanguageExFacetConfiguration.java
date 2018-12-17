package org.eclipse.xtext.parser.fragments.idea.facet;

import com.intellij.openapi.components.PersistentStateComponent;
import com.intellij.openapi.components.State;
import com.intellij.openapi.components.Storage;
import org.eclipse.xtext.idea.facet.AbstractFacetConfiguration;
import org.eclipse.xtext.idea.facet.GeneratorConfigurationState;

@State(name = "org.eclipse.xtext.parser.fragments.FragmentTestLanguageExGenerator", storages = {
		@Storage("FragmentTestLanguageExGeneratorConfig.xml")})
public class FragmentTestLanguageExFacetConfiguration extends AbstractFacetConfiguration implements PersistentStateComponent<GeneratorConfigurationState>{

}
