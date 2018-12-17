package org.eclipse.xtext.parsetree.transientvalues.idea;

import com.google.inject.Guice;
import com.google.inject.Injector;
import com.google.inject.Module;
import org.eclipse.xtext.parsetree.transientvalues.TransientValuesTestStandaloneSetupGenerated;
import org.eclipse.xtext.util.Modules2;

public class TransientValuesTestStandaloneSetupIdea extends TransientValuesTestStandaloneSetupGenerated {

    @Override
    public Injector createInjector() {
        Module runtimeModule = new org.eclipse.xtext.parsetree.transientvalues.TransientValuesTestRuntimeModule();
        Module ideaModule = new org.eclipse.xtext.parsetree.transientvalues.idea.TransientValuesTestIdeaModule();
        Module mergedModule = Modules2.mixin(runtimeModule, ideaModule);
        return Guice.createInjector(mergedModule);
    }

}
