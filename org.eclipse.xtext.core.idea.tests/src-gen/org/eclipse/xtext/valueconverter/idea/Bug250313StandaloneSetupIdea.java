package org.eclipse.xtext.valueconverter.idea;

import com.google.inject.Guice;
import com.google.inject.Injector;
import com.google.inject.Module;
import org.eclipse.xtext.util.Modules2;
import org.eclipse.xtext.valueconverter.Bug250313StandaloneSetupGenerated;

public class Bug250313StandaloneSetupIdea extends Bug250313StandaloneSetupGenerated {

    @Override
    public Injector createInjector() {
        Module runtimeModule = new org.eclipse.xtext.valueconverter.Bug250313RuntimeModule();
        Module ideaModule = new org.eclipse.xtext.valueconverter.idea.Bug250313IdeaModule();
        Module mergedModule = Modules2.mixin(runtimeModule, ideaModule);
        return Guice.createInjector(mergedModule);
    }

}
