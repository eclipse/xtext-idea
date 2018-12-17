package org.eclipse.xtext.parsetree.impl.idea;

import com.google.inject.Guice;
import com.google.inject.Injector;
import com.google.inject.Module;
import org.eclipse.xtext.parsetree.impl.CommentAssociationTestLanguageStandaloneSetupGenerated;
import org.eclipse.xtext.util.Modules2;

public class CommentAssociationTestLanguageStandaloneSetupIdea extends CommentAssociationTestLanguageStandaloneSetupGenerated {

    @Override
    public Injector createInjector() {
        Module runtimeModule = new org.eclipse.xtext.parsetree.impl.CommentAssociationTestLanguageRuntimeModule();
        Module ideaModule = new org.eclipse.xtext.parsetree.impl.idea.CommentAssociationTestLanguageIdeaModule();
        Module mergedModule = Modules2.mixin(runtimeModule, ideaModule);
        return Guice.createInjector(mergedModule);
    }

}
