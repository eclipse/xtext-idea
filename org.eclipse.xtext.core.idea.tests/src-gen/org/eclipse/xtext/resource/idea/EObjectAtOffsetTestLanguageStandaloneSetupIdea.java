package org.eclipse.xtext.resource.idea;

import com.google.inject.Guice;
import com.google.inject.Injector;
import com.google.inject.Module;
import org.eclipse.xtext.resource.EObjectAtOffsetTestLanguageStandaloneSetupGenerated;
import org.eclipse.xtext.util.Modules2;

public class EObjectAtOffsetTestLanguageStandaloneSetupIdea extends EObjectAtOffsetTestLanguageStandaloneSetupGenerated {

    @Override
    public Injector createInjector() {
        Module runtimeModule = new org.eclipse.xtext.resource.EObjectAtOffsetTestLanguageRuntimeModule();
        Module ideaModule = new org.eclipse.xtext.resource.idea.EObjectAtOffsetTestLanguageIdeaModule();
        Module mergedModule = Modules2.mixin(runtimeModule, ideaModule);
        return Guice.createInjector(mergedModule);
    }

}
