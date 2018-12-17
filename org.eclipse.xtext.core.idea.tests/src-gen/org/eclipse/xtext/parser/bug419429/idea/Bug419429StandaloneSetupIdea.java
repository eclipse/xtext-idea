package org.eclipse.xtext.parser.bug419429.idea;

import com.google.inject.Guice;
import com.google.inject.Injector;
import com.google.inject.Module;
import org.eclipse.xtext.parser.bug419429.Bug419429StandaloneSetupGenerated;
import org.eclipse.xtext.util.Modules2;

public class Bug419429StandaloneSetupIdea extends Bug419429StandaloneSetupGenerated {

    @Override
    public Injector createInjector() {
        Module runtimeModule = new org.eclipse.xtext.parser.bug419429.Bug419429RuntimeModule();
        Module ideaModule = new org.eclipse.xtext.parser.bug419429.idea.Bug419429IdeaModule();
        Module mergedModule = Modules2.mixin(runtimeModule, ideaModule);
        return Guice.createInjector(mergedModule);
    }

}
