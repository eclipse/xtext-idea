package org.eclipse.xtext.parser.parameters.idea;

import com.google.inject.Guice;
import com.google.inject.Injector;
import com.google.inject.Module;
import org.eclipse.xtext.parser.parameters.ParametersTestLanguageExStandaloneSetupGenerated;
import org.eclipse.xtext.util.Modules2;

public class ParametersTestLanguageExStandaloneSetupIdea extends ParametersTestLanguageExStandaloneSetupGenerated {

    @Override
    public Injector createInjector() {
        Module runtimeModule = new org.eclipse.xtext.parser.parameters.ParametersTestLanguageExRuntimeModule();
        Module ideaModule = new org.eclipse.xtext.parser.parameters.idea.ParametersTestLanguageExIdeaModule();
        Module mergedModule = Modules2.mixin(runtimeModule, ideaModule);
        return Guice.createInjector(mergedModule);
    }

}
