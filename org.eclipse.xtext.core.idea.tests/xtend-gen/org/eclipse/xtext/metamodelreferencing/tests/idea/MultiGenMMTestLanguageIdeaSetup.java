package org.eclipse.xtext.metamodelreferencing.tests.idea;

import com.google.inject.Injector;
import org.eclipse.xtext.ISetup;
import org.eclipse.xtext.idea.extensions.EcoreGlobalRegistries;
import org.eclipse.xtext.metamodelreferencing.tests.idea.MultiGenMMTestLanguageStandaloneSetupIdea;

@SuppressWarnings("all")
public class MultiGenMMTestLanguageIdeaSetup implements ISetup {
  @Override
  public Injector createInjectorAndDoEMFRegistration() {
    Injector _xblockexpression = null;
    {
      EcoreGlobalRegistries.ensureInitialized();
      _xblockexpression = new MultiGenMMTestLanguageStandaloneSetupIdea().createInjector();
    }
    return _xblockexpression;
  }
}
