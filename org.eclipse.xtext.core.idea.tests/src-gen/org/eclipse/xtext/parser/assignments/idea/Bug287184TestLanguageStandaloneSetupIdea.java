package org.eclipse.xtext.parser.assignments.idea;

import com.google.inject.Guice;
import com.google.inject.Injector;
import com.google.inject.Module;
import org.eclipse.xtext.parser.assignments.Bug287184TestLanguageStandaloneSetupGenerated;
import org.eclipse.xtext.util.Modules2;

public class Bug287184TestLanguageStandaloneSetupIdea extends Bug287184TestLanguageStandaloneSetupGenerated {

    @Override
    public Injector createInjector() {
        Module runtimeModule = new org.eclipse.xtext.parser.assignments.Bug287184TestLanguageRuntimeModule();
        Module ideaModule = new org.eclipse.xtext.parser.assignments.idea.Bug287184TestLanguageIdeaModule();
        Module mergedModule = Modules2.mixin(runtimeModule, ideaModule);
        return Guice.createInjector(mergedModule);
    }

}
