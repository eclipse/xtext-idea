package org.eclipse.xtext.serializer.idea;

import com.google.inject.Guice;
import com.google.inject.Injector;
import com.google.inject.Module;
import org.eclipse.xtext.serializer.AssignmentFinderTestLanguageStandaloneSetupGenerated;
import org.eclipse.xtext.util.Modules2;

public class AssignmentFinderTestLanguageStandaloneSetupIdea extends AssignmentFinderTestLanguageStandaloneSetupGenerated {

    @Override
    public Injector createInjector() {
        Module runtimeModule = new org.eclipse.xtext.serializer.AssignmentFinderTestLanguageRuntimeModule();
        Module ideaModule = new org.eclipse.xtext.serializer.idea.AssignmentFinderTestLanguageIdeaModule();
        Module mergedModule = Modules2.mixin(runtimeModule, ideaModule);
        return Guice.createInjector(mergedModule);
    }

}
