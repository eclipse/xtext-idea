package org.eclipse.xtext.testlanguages.idea;

import com.google.inject.Injector;
import org.eclipse.xtext.ISetup;
import org.eclipse.xtext.idea.extensions.EcoreGlobalRegistries;
import org.eclipse.xtext.testlanguages.idea.ActionTestLanguage2StandaloneSetupIdea;

@SuppressWarnings("all")
public class ActionTestLanguage2IdeaSetup implements ISetup {
  @Override
  public Injector createInjectorAndDoEMFRegistration() {
    Injector _xblockexpression = null;
    {
      EcoreGlobalRegistries.ensureInitialized();
      _xblockexpression = new ActionTestLanguage2StandaloneSetupIdea().createInjector();
    }
    return _xblockexpression;
  }
}
