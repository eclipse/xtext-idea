package org.eclipse.xtext.parser.fragments.idea;

import com.google.inject.Guice;
import com.google.inject.Injector;
import com.google.inject.Module;
import org.eclipse.xtext.parser.fragments.FragmentTestLanguageExStandaloneSetupGenerated;
import org.eclipse.xtext.util.Modules2;

public class FragmentTestLanguageExStandaloneSetupIdea extends FragmentTestLanguageExStandaloneSetupGenerated {

    @Override
    public Injector createInjector() {
        Module runtimeModule = new org.eclipse.xtext.parser.fragments.FragmentTestLanguageExRuntimeModule();
        Module ideaModule = new org.eclipse.xtext.parser.fragments.idea.FragmentTestLanguageExIdeaModule();
        Module mergedModule = Modules2.mixin(runtimeModule, ideaModule);
        return Guice.createInjector(mergedModule);
    }

}
