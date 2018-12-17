package org.eclipse.xtext.testlanguages.idea;

import com.google.inject.Guice;
import com.google.inject.Injector;
import com.google.inject.Module;
import org.eclipse.xtext.testlanguages.FowlerDslTestLanguageStandaloneSetupGenerated;
import org.eclipse.xtext.util.Modules2;

public class FowlerDslTestLanguageStandaloneSetupIdea extends FowlerDslTestLanguageStandaloneSetupGenerated {

    @Override
    public Injector createInjector() {
        Module runtimeModule = new org.eclipse.xtext.testlanguages.FowlerDslTestLanguageRuntimeModule();
        Module ideaModule = new org.eclipse.xtext.testlanguages.idea.FowlerDslTestLanguageIdeaModule();
        Module mergedModule = Modules2.mixin(runtimeModule, ideaModule);
        return Guice.createInjector(mergedModule);
    }

}
