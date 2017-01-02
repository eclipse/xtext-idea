package org.eclipse.xtext.valueconverter.idea;

import com.google.inject.Injector;
import org.eclipse.xtext.ISetup;
import org.eclipse.xtext.idea.extensions.EcoreGlobalRegistries;
import org.eclipse.xtext.valueconverter.idea.Bug250313StandaloneSetupIdea;

@SuppressWarnings("all")
public class Bug250313IdeaSetup implements ISetup {
  @Override
  public Injector createInjectorAndDoEMFRegistration() {
    Injector _xblockexpression = null;
    {
      EcoreGlobalRegistries.ensureInitialized();
      _xblockexpression = new Bug250313StandaloneSetupIdea().createInjector();
    }
    return _xblockexpression;
  }
}
