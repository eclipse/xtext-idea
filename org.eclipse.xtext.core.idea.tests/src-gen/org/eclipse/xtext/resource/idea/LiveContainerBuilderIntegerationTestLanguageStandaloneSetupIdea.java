package org.eclipse.xtext.resource.idea;

import com.google.inject.Guice;
import com.google.inject.Injector;
import com.google.inject.Module;
import org.eclipse.xtext.resource.LiveContainerBuilderIntegerationTestLanguageStandaloneSetupGenerated;
import org.eclipse.xtext.util.Modules2;

public class LiveContainerBuilderIntegerationTestLanguageStandaloneSetupIdea extends LiveContainerBuilderIntegerationTestLanguageStandaloneSetupGenerated {

    @Override
    public Injector createInjector() {
        Module runtimeModule = new org.eclipse.xtext.resource.LiveContainerBuilderIntegerationTestLanguageRuntimeModule();
        Module ideaModule = new org.eclipse.xtext.resource.idea.LiveContainerBuilderIntegerationTestLanguageIdeaModule();
        Module mergedModule = Modules2.mixin(runtimeModule, ideaModule);
        return Guice.createInjector(mergedModule);
    }

}
