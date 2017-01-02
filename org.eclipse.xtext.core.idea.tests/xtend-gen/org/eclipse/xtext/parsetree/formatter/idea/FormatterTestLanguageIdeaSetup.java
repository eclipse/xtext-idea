package org.eclipse.xtext.parsetree.formatter.idea;

import com.google.inject.Injector;
import org.eclipse.xtext.ISetup;
import org.eclipse.xtext.idea.extensions.EcoreGlobalRegistries;
import org.eclipse.xtext.parsetree.formatter.idea.FormatterTestLanguageStandaloneSetupIdea;

@SuppressWarnings("all")
public class FormatterTestLanguageIdeaSetup implements ISetup {
  @Override
  public Injector createInjectorAndDoEMFRegistration() {
    Injector _xblockexpression = null;
    {
      EcoreGlobalRegistries.ensureInitialized();
      _xblockexpression = new FormatterTestLanguageStandaloneSetupIdea().createInjector();
    }
    return _xblockexpression;
  }
}
