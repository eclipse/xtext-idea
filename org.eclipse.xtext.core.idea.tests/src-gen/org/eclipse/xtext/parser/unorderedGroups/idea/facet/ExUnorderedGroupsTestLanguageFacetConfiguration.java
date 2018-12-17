package org.eclipse.xtext.parser.unorderedGroups.idea.facet;

import com.intellij.openapi.components.PersistentStateComponent;
import com.intellij.openapi.components.State;
import com.intellij.openapi.components.Storage;
import org.eclipse.xtext.idea.facet.AbstractFacetConfiguration;
import org.eclipse.xtext.idea.facet.GeneratorConfigurationState;

@State(name = "org.eclipse.xtext.parser.unorderedGroups.ExUnorderedGroupsTestLanguageGenerator", storages = {
		@Storage("ExUnorderedGroupsTestLanguageGeneratorConfig.xml")})
public class ExUnorderedGroupsTestLanguageFacetConfiguration extends AbstractFacetConfiguration implements PersistentStateComponent<GeneratorConfigurationState>{

}
