package org.eclipse.xtext.index.idea;

import com.google.inject.Guice;
import com.google.inject.Injector;
import com.google.inject.Module;
import org.eclipse.xtext.index.IndexTestLanguageStandaloneSetupGenerated;
import org.eclipse.xtext.util.Modules2;

public class IndexTestLanguageStandaloneSetupIdea extends IndexTestLanguageStandaloneSetupGenerated {

    @Override
    public Injector createInjector() {
        Module runtimeModule = new org.eclipse.xtext.index.IndexTestLanguageRuntimeModule();
        Module ideaModule = new org.eclipse.xtext.index.idea.IndexTestLanguageIdeaModule();
        Module mergedModule = Modules2.mixin(runtimeModule, ideaModule);
        return Guice.createInjector(mergedModule);
    }

}
