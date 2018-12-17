package org.eclipse.xtext.formatting2.regionaccess.internal.idea;

import com.google.inject.Guice;
import com.google.inject.Injector;
import com.google.inject.Module;
import org.eclipse.xtext.formatting2.regionaccess.internal.RegionAccessTestLanguageStandaloneSetupGenerated;
import org.eclipse.xtext.util.Modules2;

public class RegionAccessTestLanguageStandaloneSetupIdea extends RegionAccessTestLanguageStandaloneSetupGenerated {

    @Override
    public Injector createInjector() {
        Module runtimeModule = new org.eclipse.xtext.formatting2.regionaccess.internal.RegionAccessTestLanguageRuntimeModule();
        Module ideaModule = new org.eclipse.xtext.formatting2.regionaccess.internal.idea.RegionAccessTestLanguageIdeaModule();
        Module mergedModule = Modules2.mixin(runtimeModule, ideaModule);
        return Guice.createInjector(mergedModule);
    }

}
