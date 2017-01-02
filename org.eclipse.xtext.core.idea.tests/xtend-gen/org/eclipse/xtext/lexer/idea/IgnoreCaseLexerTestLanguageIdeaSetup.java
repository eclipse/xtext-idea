package org.eclipse.xtext.lexer.idea;

import com.google.inject.Injector;
import org.eclipse.xtext.ISetup;
import org.eclipse.xtext.idea.extensions.EcoreGlobalRegistries;
import org.eclipse.xtext.lexer.idea.IgnoreCaseLexerTestLanguageStandaloneSetupIdea;

@SuppressWarnings("all")
public class IgnoreCaseLexerTestLanguageIdeaSetup implements ISetup {
  @Override
  public Injector createInjectorAndDoEMFRegistration() {
    Injector _xblockexpression = null;
    {
      EcoreGlobalRegistries.ensureInitialized();
      _xblockexpression = new IgnoreCaseLexerTestLanguageStandaloneSetupIdea().createInjector();
    }
    return _xblockexpression;
  }
}
