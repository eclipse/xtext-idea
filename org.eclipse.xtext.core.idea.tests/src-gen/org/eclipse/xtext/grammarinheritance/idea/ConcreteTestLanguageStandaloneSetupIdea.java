package org.eclipse.xtext.grammarinheritance.idea;

import com.google.inject.Guice;
import com.google.inject.Injector;
import com.google.inject.Module;
import org.eclipse.xtext.grammarinheritance.ConcreteTestLanguageStandaloneSetupGenerated;
import org.eclipse.xtext.util.Modules2;

public class ConcreteTestLanguageStandaloneSetupIdea extends ConcreteTestLanguageStandaloneSetupGenerated {

    @Override
    public Injector createInjector() {
        Module runtimeModule = new org.eclipse.xtext.grammarinheritance.ConcreteTestLanguageRuntimeModule();
        Module ideaModule = new org.eclipse.xtext.grammarinheritance.idea.ConcreteTestLanguageIdeaModule();
        Module mergedModule = Modules2.mixin(runtimeModule, ideaModule);
        return Guice.createInjector(mergedModule);
    }

}
