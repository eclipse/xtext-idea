package org.eclipse.xtext.testlanguages.backtracking.idea;

import com.google.inject.Injector;
import org.eclipse.xtext.ISetup;
import org.eclipse.xtext.idea.extensions.EcoreGlobalRegistries;
import org.eclipse.xtext.testlanguages.backtracking.idea.SimpleBeeLangTestLanguageStandaloneSetupIdea;

@SuppressWarnings("all")
public class SimpleBeeLangTestLanguageIdeaSetup implements ISetup {
  @Override
  public Injector createInjectorAndDoEMFRegistration() {
    Injector _xblockexpression = null;
    {
      EcoreGlobalRegistries.ensureInitialized();
      _xblockexpression = new SimpleBeeLangTestLanguageStandaloneSetupIdea().createInjector();
    }
    return _xblockexpression;
  }
}
