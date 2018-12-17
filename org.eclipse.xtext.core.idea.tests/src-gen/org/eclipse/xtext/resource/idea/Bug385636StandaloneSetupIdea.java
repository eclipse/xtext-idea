package org.eclipse.xtext.resource.idea;

import com.google.inject.Guice;
import com.google.inject.Injector;
import com.google.inject.Module;
import org.eclipse.xtext.resource.Bug385636StandaloneSetupGenerated;
import org.eclipse.xtext.util.Modules2;

public class Bug385636StandaloneSetupIdea extends Bug385636StandaloneSetupGenerated {

    @Override
    public Injector createInjector() {
        Module runtimeModule = new org.eclipse.xtext.resource.Bug385636RuntimeModule();
        Module ideaModule = new org.eclipse.xtext.resource.idea.Bug385636IdeaModule();
        Module mergedModule = Modules2.mixin(runtimeModule, ideaModule);
        return Guice.createInjector(mergedModule);
    }

}
