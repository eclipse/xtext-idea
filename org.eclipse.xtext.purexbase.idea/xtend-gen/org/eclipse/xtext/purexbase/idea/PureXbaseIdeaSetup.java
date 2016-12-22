package org.eclipse.xtext.purexbase.idea;

import com.google.inject.Injector;
import org.eclipse.xtext.ISetup;
import org.eclipse.xtext.idea.extensions.EcoreGlobalRegistries;
import org.eclipse.xtext.purexbase.idea.PureXbaseStandaloneSetupIdea;

@SuppressWarnings("all")
public class PureXbaseIdeaSetup implements ISetup {
  @Override
  public Injector createInjectorAndDoEMFRegistration() {
    Injector _xblockexpression = null;
    {
      EcoreGlobalRegistries.ensureInitialized();
      _xblockexpression = new PureXbaseStandaloneSetupIdea().createInjector();
    }
    return _xblockexpression;
  }
}
