package org.eclipse.xtext.parser.parameters.idea;

import com.google.inject.Guice;
import com.google.inject.Injector;
import com.google.inject.Module;
import org.eclipse.xtext.parser.parameters.TwoParametersTestLanguageStandaloneSetupGenerated;
import org.eclipse.xtext.util.Modules2;

public class TwoParametersTestLanguageStandaloneSetupIdea extends TwoParametersTestLanguageStandaloneSetupGenerated {

    @Override
    public Injector createInjector() {
        Module runtimeModule = new org.eclipse.xtext.parser.parameters.TwoParametersTestLanguageRuntimeModule();
        Module ideaModule = new org.eclipse.xtext.parser.parameters.idea.TwoParametersTestLanguageIdeaModule();
        Module mergedModule = Modules2.mixin(runtimeModule, ideaModule);
        return Guice.createInjector(mergedModule);
    }

}
