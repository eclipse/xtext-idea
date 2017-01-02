package org.eclipse.xtext.parser.keywords.idea;

import com.google.inject.Injector;
import org.eclipse.xtext.ISetup;
import org.eclipse.xtext.idea.extensions.EcoreGlobalRegistries;
import org.eclipse.xtext.parser.keywords.idea.KeywordsTestLanguageStandaloneSetupIdea;

@SuppressWarnings("all")
public class KeywordsTestLanguageIdeaSetup implements ISetup {
  @Override
  public Injector createInjectorAndDoEMFRegistration() {
    Injector _xblockexpression = null;
    {
      EcoreGlobalRegistries.ensureInitialized();
      _xblockexpression = new KeywordsTestLanguageStandaloneSetupIdea().createInjector();
    }
    return _xblockexpression;
  }
}
