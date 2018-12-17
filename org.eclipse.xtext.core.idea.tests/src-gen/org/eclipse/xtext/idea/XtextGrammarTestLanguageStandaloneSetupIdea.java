package org.eclipse.xtext.idea;

import com.google.inject.Guice;
import com.google.inject.Injector;
import com.google.inject.Module;
import org.eclipse.xtext.XtextGrammarTestLanguageStandaloneSetupGenerated;
import org.eclipse.xtext.util.Modules2;

public class XtextGrammarTestLanguageStandaloneSetupIdea extends XtextGrammarTestLanguageStandaloneSetupGenerated {

    @Override
    public Injector createInjector() {
        Module runtimeModule = new org.eclipse.xtext.XtextGrammarTestLanguageRuntimeModule();
        Module ideaModule = new org.eclipse.xtext.idea.XtextGrammarTestLanguageIdeaModule();
        Module mergedModule = Modules2.mixin(runtimeModule, ideaModule);
        return Guice.createInjector(mergedModule);
    }

}
