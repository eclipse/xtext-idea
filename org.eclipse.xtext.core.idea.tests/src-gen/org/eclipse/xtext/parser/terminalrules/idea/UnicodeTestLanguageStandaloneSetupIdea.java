package org.eclipse.xtext.parser.terminalrules.idea;

import com.google.inject.Guice;
import com.google.inject.Injector;
import com.google.inject.Module;
import org.eclipse.xtext.parser.terminalrules.UnicodeTestLanguageStandaloneSetupGenerated;
import org.eclipse.xtext.util.Modules2;

public class UnicodeTestLanguageStandaloneSetupIdea extends UnicodeTestLanguageStandaloneSetupGenerated {

    @Override
    public Injector createInjector() {
        Module runtimeModule = new org.eclipse.xtext.parser.terminalrules.UnicodeTestLanguageRuntimeModule();
        Module ideaModule = new org.eclipse.xtext.parser.terminalrules.idea.UnicodeTestLanguageIdeaModule();
        Module mergedModule = Modules2.mixin(runtimeModule, ideaModule);
        return Guice.createInjector(mergedModule);
    }

}
