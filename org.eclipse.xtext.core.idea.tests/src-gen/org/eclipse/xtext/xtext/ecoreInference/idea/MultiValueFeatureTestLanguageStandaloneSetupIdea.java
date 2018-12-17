package org.eclipse.xtext.xtext.ecoreInference.idea;

import com.google.inject.Guice;
import com.google.inject.Injector;
import com.google.inject.Module;
import org.eclipse.xtext.util.Modules2;
import org.eclipse.xtext.xtext.ecoreInference.MultiValueFeatureTestLanguageStandaloneSetupGenerated;

public class MultiValueFeatureTestLanguageStandaloneSetupIdea extends MultiValueFeatureTestLanguageStandaloneSetupGenerated {

    @Override
    public Injector createInjector() {
        Module runtimeModule = new org.eclipse.xtext.xtext.ecoreInference.MultiValueFeatureTestLanguageRuntimeModule();
        Module ideaModule = new org.eclipse.xtext.xtext.ecoreInference.idea.MultiValueFeatureTestLanguageIdeaModule();
        Module mergedModule = Modules2.mixin(runtimeModule, ideaModule);
        return Guice.createInjector(mergedModule);
    }

}
