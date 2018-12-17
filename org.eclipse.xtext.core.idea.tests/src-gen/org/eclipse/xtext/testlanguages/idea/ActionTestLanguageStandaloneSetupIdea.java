package org.eclipse.xtext.testlanguages.idea;

import com.google.inject.Guice;
import com.google.inject.Injector;
import com.google.inject.Module;
import org.eclipse.xtext.testlanguages.ActionTestLanguageStandaloneSetupGenerated;
import org.eclipse.xtext.util.Modules2;

public class ActionTestLanguageStandaloneSetupIdea extends ActionTestLanguageStandaloneSetupGenerated {

    @Override
    public Injector createInjector() {
        Module runtimeModule = new org.eclipse.xtext.testlanguages.ActionTestLanguageRuntimeModule();
        Module ideaModule = new org.eclipse.xtext.testlanguages.idea.ActionTestLanguageIdeaModule();
        Module mergedModule = Modules2.mixin(runtimeModule, ideaModule);
        return Guice.createInjector(mergedModule);
    }

}
