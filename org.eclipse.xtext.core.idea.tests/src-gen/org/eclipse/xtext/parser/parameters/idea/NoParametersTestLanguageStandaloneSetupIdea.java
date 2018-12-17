package org.eclipse.xtext.parser.parameters.idea;

import com.google.inject.Guice;
import com.google.inject.Injector;
import com.google.inject.Module;
import org.eclipse.xtext.parser.parameters.NoParametersTestLanguageStandaloneSetupGenerated;
import org.eclipse.xtext.util.Modules2;

public class NoParametersTestLanguageStandaloneSetupIdea extends NoParametersTestLanguageStandaloneSetupGenerated {

    @Override
    public Injector createInjector() {
        Module runtimeModule = new org.eclipse.xtext.parser.parameters.NoParametersTestLanguageRuntimeModule();
        Module ideaModule = new org.eclipse.xtext.parser.parameters.idea.NoParametersTestLanguageIdeaModule();
        Module mergedModule = Modules2.mixin(runtimeModule, ideaModule);
        return Guice.createInjector(mergedModule);
    }

}
