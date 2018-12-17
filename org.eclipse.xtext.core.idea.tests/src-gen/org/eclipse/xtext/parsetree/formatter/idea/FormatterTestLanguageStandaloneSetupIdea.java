package org.eclipse.xtext.parsetree.formatter.idea;

import com.google.inject.Guice;
import com.google.inject.Injector;
import com.google.inject.Module;
import org.eclipse.xtext.parsetree.formatter.FormatterTestLanguageStandaloneSetupGenerated;
import org.eclipse.xtext.util.Modules2;

public class FormatterTestLanguageStandaloneSetupIdea extends FormatterTestLanguageStandaloneSetupGenerated {

    @Override
    public Injector createInjector() {
        Module runtimeModule = new org.eclipse.xtext.parsetree.formatter.FormatterTestLanguageRuntimeModule();
        Module ideaModule = new org.eclipse.xtext.parsetree.formatter.idea.FormatterTestLanguageIdeaModule();
        Module mergedModule = Modules2.mixin(runtimeModule, ideaModule);
        return Guice.createInjector(mergedModule);
    }

}
