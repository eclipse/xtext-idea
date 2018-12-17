package org.eclipse.xtext.serializer.idea;

import com.google.inject.Guice;
import com.google.inject.Injector;
import com.google.inject.Module;
import org.eclipse.xtext.serializer.HiddenTokenSequencerTestLanguageStandaloneSetupGenerated;
import org.eclipse.xtext.util.Modules2;

public class HiddenTokenSequencerTestLanguageStandaloneSetupIdea extends HiddenTokenSequencerTestLanguageStandaloneSetupGenerated {

    @Override
    public Injector createInjector() {
        Module runtimeModule = new org.eclipse.xtext.serializer.HiddenTokenSequencerTestLanguageRuntimeModule();
        Module ideaModule = new org.eclipse.xtext.serializer.idea.HiddenTokenSequencerTestLanguageIdeaModule();
        Module mergedModule = Modules2.mixin(runtimeModule, ideaModule);
        return Guice.createInjector(mergedModule);
    }

}
