package org.eclipse.xtext.parser.unorderedGroups.idea;

import com.google.inject.Guice;
import com.google.inject.Injector;
import com.google.inject.Module;
import org.eclipse.xtext.parser.unorderedGroups.ExUnorderedGroupsTestLanguageStandaloneSetupGenerated;
import org.eclipse.xtext.util.Modules2;

public class ExUnorderedGroupsTestLanguageStandaloneSetupIdea extends ExUnorderedGroupsTestLanguageStandaloneSetupGenerated {

    @Override
    public Injector createInjector() {
        Module runtimeModule = new org.eclipse.xtext.parser.unorderedGroups.ExUnorderedGroupsTestLanguageRuntimeModule();
        Module ideaModule = new org.eclipse.xtext.parser.unorderedGroups.idea.ExUnorderedGroupsTestLanguageIdeaModule();
        Module mergedModule = Modules2.mixin(runtimeModule, ideaModule);
        return Guice.createInjector(mergedModule);
    }

}
