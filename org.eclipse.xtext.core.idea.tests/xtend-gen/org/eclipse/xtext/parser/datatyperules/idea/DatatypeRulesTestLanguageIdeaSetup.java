package org.eclipse.xtext.parser.datatyperules.idea;

import com.google.inject.Injector;
import org.eclipse.xtext.ISetup;
import org.eclipse.xtext.idea.extensions.EcoreGlobalRegistries;
import org.eclipse.xtext.parser.datatyperules.idea.DatatypeRulesTestLanguageStandaloneSetupIdea;

@SuppressWarnings("all")
public class DatatypeRulesTestLanguageIdeaSetup implements ISetup {
  @Override
  public Injector createInjectorAndDoEMFRegistration() {
    Injector _xblockexpression = null;
    {
      EcoreGlobalRegistries.ensureInitialized();
      _xblockexpression = new DatatypeRulesTestLanguageStandaloneSetupIdea().createInjector();
    }
    return _xblockexpression;
  }
}
