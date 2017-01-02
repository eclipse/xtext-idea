package org.eclipse.xtext.testlanguages.idea;

import com.google.inject.Injector;
import org.eclipse.xtext.ISetup;
import org.eclipse.xtext.idea.extensions.EcoreGlobalRegistries;
import org.eclipse.xtext.testlanguages.idea.ReferenceGrammarTestLanguageStandaloneSetupIdea;

@SuppressWarnings("all")
public class ReferenceGrammarTestLanguageIdeaSetup implements ISetup {
  @Override
  public Injector createInjectorAndDoEMFRegistration() {
    Injector _xblockexpression = null;
    {
      EcoreGlobalRegistries.ensureInitialized();
      _xblockexpression = new ReferenceGrammarTestLanguageStandaloneSetupIdea().createInjector();
    }
    return _xblockexpression;
  }
}
