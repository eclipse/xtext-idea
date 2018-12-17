package org.eclipse.xtext.enumrules.idea;

import com.google.inject.Guice;
import com.google.inject.Injector;
import com.google.inject.Module;
import org.eclipse.xtext.enumrules.EnumAndReferenceTestLanguageStandaloneSetupGenerated;
import org.eclipse.xtext.util.Modules2;

public class EnumAndReferenceTestLanguageStandaloneSetupIdea extends EnumAndReferenceTestLanguageStandaloneSetupGenerated {

    @Override
    public Injector createInjector() {
        Module runtimeModule = new org.eclipse.xtext.enumrules.EnumAndReferenceTestLanguageRuntimeModule();
        Module ideaModule = new org.eclipse.xtext.enumrules.idea.EnumAndReferenceTestLanguageIdeaModule();
        Module mergedModule = Modules2.mixin(runtimeModule, ideaModule);
        return Guice.createInjector(mergedModule);
    }

}
