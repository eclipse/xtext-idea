package org.eclipse.xtext.parser.unorderedGroups.idea;

import com.google.inject.Guice;
import com.google.inject.Injector;
import com.google.inject.Module;
import org.eclipse.xtext.parser.unorderedGroups.ExBacktrackingBug325745TestLanguageStandaloneSetupGenerated;
import org.eclipse.xtext.util.Modules2;

public class ExBacktrackingBug325745TestLanguageStandaloneSetupIdea extends ExBacktrackingBug325745TestLanguageStandaloneSetupGenerated {

    @Override
    public Injector createInjector() {
        Module runtimeModule = new org.eclipse.xtext.parser.unorderedGroups.ExBacktrackingBug325745TestLanguageRuntimeModule();
        Module ideaModule = new org.eclipse.xtext.parser.unorderedGroups.idea.ExBacktrackingBug325745TestLanguageIdeaModule();
        Module mergedModule = Modules2.mixin(runtimeModule, ideaModule);
        return Guice.createInjector(mergedModule);
    }

}
