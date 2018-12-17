package org.eclipse.xtext.parsetree.reconstr.idea;

import com.google.inject.Guice;
import com.google.inject.Injector;
import com.google.inject.Module;
import org.eclipse.xtext.parsetree.reconstr.ComplexReconstrTestLanguageStandaloneSetupGenerated;
import org.eclipse.xtext.util.Modules2;

public class ComplexReconstrTestLanguageStandaloneSetupIdea extends ComplexReconstrTestLanguageStandaloneSetupGenerated {

    @Override
    public Injector createInjector() {
        Module runtimeModule = new org.eclipse.xtext.parsetree.reconstr.ComplexReconstrTestLanguageRuntimeModule();
        Module ideaModule = new org.eclipse.xtext.parsetree.reconstr.idea.ComplexReconstrTestLanguageIdeaModule();
        Module mergedModule = Modules2.mixin(runtimeModule, ideaModule);
        return Guice.createInjector(mergedModule);
    }

}
