package org.eclipse.xtext.parser.assignments.idea;

import com.google.inject.Guice;
import com.google.inject.Injector;
import com.google.inject.Module;
import org.eclipse.xtext.parser.assignments.AssignmentsTestLanguageStandaloneSetupGenerated;
import org.eclipse.xtext.util.Modules2;

public class AssignmentsTestLanguageStandaloneSetupIdea extends AssignmentsTestLanguageStandaloneSetupGenerated {

    @Override
    public Injector createInjector() {
        Module runtimeModule = new org.eclipse.xtext.parser.assignments.AssignmentsTestLanguageRuntimeModule();
        Module ideaModule = new org.eclipse.xtext.parser.assignments.idea.AssignmentsTestLanguageIdeaModule();
        Module mergedModule = Modules2.mixin(runtimeModule, ideaModule);
        return Guice.createInjector(mergedModule);
    }

}
