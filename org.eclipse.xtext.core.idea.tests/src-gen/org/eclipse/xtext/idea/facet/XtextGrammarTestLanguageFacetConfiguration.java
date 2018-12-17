package org.eclipse.xtext.idea.facet;

import com.intellij.openapi.components.PersistentStateComponent;
import com.intellij.openapi.components.State;
import com.intellij.openapi.components.Storage;

@State(name = "org.eclipse.xtext.XtextGrammarTestLanguageGenerator", storages = {
		@Storage("XtextGrammarTestLanguageGeneratorConfig.xml")})
public class XtextGrammarTestLanguageFacetConfiguration extends AbstractFacetConfiguration implements PersistentStateComponent<GeneratorConfigurationState>{

}
