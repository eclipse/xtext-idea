package org.eclipse.xtext.valueconverter.idea;

import com.google.inject.Guice;
import com.google.inject.Injector;
import com.google.inject.Module;
import org.eclipse.xtext.util.Modules2;
import org.eclipse.xtext.valueconverter.QualifiedNameTestLanguageStandaloneSetupGenerated;

public class QualifiedNameTestLanguageStandaloneSetupIdea extends QualifiedNameTestLanguageStandaloneSetupGenerated {

    @Override
    public Injector createInjector() {
        Module runtimeModule = new org.eclipse.xtext.valueconverter.QualifiedNameTestLanguageRuntimeModule();
        Module ideaModule = new org.eclipse.xtext.valueconverter.idea.QualifiedNameTestLanguageIdeaModule();
        Module mergedModule = Modules2.mixin(runtimeModule, ideaModule);
        return Guice.createInjector(mergedModule);
    }

}
