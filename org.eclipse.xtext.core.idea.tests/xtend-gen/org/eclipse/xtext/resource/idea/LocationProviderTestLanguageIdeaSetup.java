package org.eclipse.xtext.resource.idea;

import com.google.inject.Injector;
import org.eclipse.xtext.ISetup;
import org.eclipse.xtext.idea.extensions.EcoreGlobalRegistries;
import org.eclipse.xtext.resource.idea.LocationProviderTestLanguageStandaloneSetupIdea;

@SuppressWarnings("all")
public class LocationProviderTestLanguageIdeaSetup implements ISetup {
  @Override
  public Injector createInjectorAndDoEMFRegistration() {
    Injector _xblockexpression = null;
    {
      EcoreGlobalRegistries.ensureInitialized();
      _xblockexpression = new LocationProviderTestLanguageStandaloneSetupIdea().createInjector();
    }
    return _xblockexpression;
  }
}
