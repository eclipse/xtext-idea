package org.eclipse.xtext.grammarinheritance.idea;

import com.google.inject.Guice;
import com.google.inject.Injector;
import com.google.inject.Module;
import org.eclipse.xtext.grammarinheritance.InheritanceTestLanguageStandaloneSetupGenerated;
import org.eclipse.xtext.util.Modules2;

public class InheritanceTestLanguageStandaloneSetupIdea extends InheritanceTestLanguageStandaloneSetupGenerated {

    @Override
    public Injector createInjector() {
        Module runtimeModule = new org.eclipse.xtext.grammarinheritance.InheritanceTestLanguageRuntimeModule();
        Module ideaModule = new org.eclipse.xtext.grammarinheritance.idea.InheritanceTestLanguageIdeaModule();
        Module mergedModule = Modules2.mixin(runtimeModule, ideaModule);
        return Guice.createInjector(mergedModule);
    }

}
