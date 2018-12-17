package org.eclipse.xtext.grammarinheritance.idea;

import com.google.inject.Guice;
import com.google.inject.Injector;
import com.google.inject.Module;
import org.eclipse.xtext.grammarinheritance.InheritanceTest2LanguageStandaloneSetupGenerated;
import org.eclipse.xtext.util.Modules2;

public class InheritanceTest2LanguageStandaloneSetupIdea extends InheritanceTest2LanguageStandaloneSetupGenerated {

    @Override
    public Injector createInjector() {
        Module runtimeModule = new org.eclipse.xtext.grammarinheritance.InheritanceTest2LanguageRuntimeModule();
        Module ideaModule = new org.eclipse.xtext.grammarinheritance.idea.InheritanceTest2LanguageIdeaModule();
        Module mergedModule = Modules2.mixin(runtimeModule, ideaModule);
        return Guice.createInjector(mergedModule);
    }

}
