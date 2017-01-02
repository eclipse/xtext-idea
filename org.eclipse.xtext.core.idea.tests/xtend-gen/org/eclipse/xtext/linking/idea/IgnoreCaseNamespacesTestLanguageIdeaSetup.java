package org.eclipse.xtext.linking.idea;

import com.google.inject.Injector;
import org.eclipse.xtext.ISetup;
import org.eclipse.xtext.idea.extensions.EcoreGlobalRegistries;
import org.eclipse.xtext.linking.idea.IgnoreCaseNamespacesTestLanguageStandaloneSetupIdea;

@SuppressWarnings("all")
public class IgnoreCaseNamespacesTestLanguageIdeaSetup implements ISetup {
  @Override
  public Injector createInjectorAndDoEMFRegistration() {
    Injector _xblockexpression = null;
    {
      EcoreGlobalRegistries.ensureInitialized();
      _xblockexpression = new IgnoreCaseNamespacesTestLanguageStandaloneSetupIdea().createInjector();
    }
    return _xblockexpression;
  }
}
