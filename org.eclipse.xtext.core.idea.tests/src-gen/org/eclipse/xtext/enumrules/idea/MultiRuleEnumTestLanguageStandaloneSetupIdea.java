package org.eclipse.xtext.enumrules.idea;

import com.google.inject.Guice;
import com.google.inject.Injector;
import com.google.inject.Module;
import org.eclipse.xtext.enumrules.MultiRuleEnumTestLanguageStandaloneSetupGenerated;
import org.eclipse.xtext.util.Modules2;

public class MultiRuleEnumTestLanguageStandaloneSetupIdea extends MultiRuleEnumTestLanguageStandaloneSetupGenerated {

    @Override
    public Injector createInjector() {
        Module runtimeModule = new org.eclipse.xtext.enumrules.MultiRuleEnumTestLanguageRuntimeModule();
        Module ideaModule = new org.eclipse.xtext.enumrules.idea.MultiRuleEnumTestLanguageIdeaModule();
        Module mergedModule = Modules2.mixin(runtimeModule, ideaModule);
        return Guice.createInjector(mergedModule);
    }

}
