package org.eclipse.xtext.formatting2.regionaccess.internal.idea.facet;

import com.intellij.openapi.components.PersistentStateComponent;
import com.intellij.openapi.components.State;
import com.intellij.openapi.components.Storage;
import org.eclipse.xtext.idea.facet.AbstractFacetConfiguration;
import org.eclipse.xtext.idea.facet.GeneratorConfigurationState;

@State(name = "org.eclipse.xtext.formatting2.regionaccess.internal.RegionAccessTestLanguageGenerator", storages = {
		@Storage("RegionAccessTestLanguageGeneratorConfig.xml")})
public class RegionAccessTestLanguageFacetConfiguration extends AbstractFacetConfiguration implements PersistentStateComponent<GeneratorConfigurationState>{

}
