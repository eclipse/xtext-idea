package org.eclipse.xtext.parser.antlr.idea;

import com.google.inject.Injector;
import org.eclipse.xtext.ISetup;
import org.eclipse.xtext.idea.extensions.EcoreGlobalRegistries;
import org.eclipse.xtext.parser.antlr.idea.Bug289524ExTestLanguageStandaloneSetupIdea;

@SuppressWarnings("all")
public class Bug289524ExTestLanguageIdeaSetup implements ISetup {
  @Override
  public Injector createInjectorAndDoEMFRegistration() {
    Injector _xblockexpression = null;
    {
      EcoreGlobalRegistries.ensureInitialized();
      _xblockexpression = new Bug289524ExTestLanguageStandaloneSetupIdea().createInjector();
    }
    return _xblockexpression;
  }
}
