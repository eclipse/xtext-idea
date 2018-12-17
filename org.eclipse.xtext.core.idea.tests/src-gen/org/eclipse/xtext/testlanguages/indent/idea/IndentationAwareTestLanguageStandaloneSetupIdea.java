package org.eclipse.xtext.testlanguages.indent.idea;

import com.google.inject.Guice;
import com.google.inject.Injector;
import com.google.inject.Module;
import org.eclipse.xtext.testlanguages.indent.IndentationAwareTestLanguageStandaloneSetupGenerated;
import org.eclipse.xtext.util.Modules2;

public class IndentationAwareTestLanguageStandaloneSetupIdea extends IndentationAwareTestLanguageStandaloneSetupGenerated {

    @Override
    public Injector createInjector() {
        Module runtimeModule = new org.eclipse.xtext.testlanguages.indent.IndentationAwareTestLanguageRuntimeModule();
        Module ideaModule = new org.eclipse.xtext.testlanguages.indent.idea.IndentationAwareTestLanguageIdeaModule();
        Module mergedModule = Modules2.mixin(runtimeModule, ideaModule);
        return Guice.createInjector(mergedModule);
    }

}
