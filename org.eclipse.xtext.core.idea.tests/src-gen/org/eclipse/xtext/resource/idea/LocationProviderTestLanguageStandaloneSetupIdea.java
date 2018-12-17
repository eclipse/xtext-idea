package org.eclipse.xtext.resource.idea;

import com.google.inject.Guice;
import com.google.inject.Injector;
import com.google.inject.Module;
import org.eclipse.xtext.resource.LocationProviderTestLanguageStandaloneSetupGenerated;
import org.eclipse.xtext.util.Modules2;

public class LocationProviderTestLanguageStandaloneSetupIdea extends LocationProviderTestLanguageStandaloneSetupGenerated {

    @Override
    public Injector createInjector() {
        Module runtimeModule = new org.eclipse.xtext.resource.LocationProviderTestLanguageRuntimeModule();
        Module ideaModule = new org.eclipse.xtext.resource.idea.LocationProviderTestLanguageIdeaModule();
        Module mergedModule = Modules2.mixin(runtimeModule, ideaModule);
        return Guice.createInjector(mergedModule);
    }

}
