package org.eclipse.xtext.parsetree.reconstr.idea;

import com.google.inject.Injector;
import org.eclipse.xtext.ISetup;
import org.eclipse.xtext.idea.extensions.EcoreGlobalRegistries;
import org.eclipse.xtext.parsetree.reconstr.idea.SerializationBug269362TestLanguageStandaloneSetupIdea;

@SuppressWarnings("all")
public class SerializationBug269362TestLanguageIdeaSetup implements ISetup {
  @Override
  public Injector createInjectorAndDoEMFRegistration() {
    Injector _xblockexpression = null;
    {
      EcoreGlobalRegistries.ensureInitialized();
      _xblockexpression = new SerializationBug269362TestLanguageStandaloneSetupIdea().createInjector();
    }
    return _xblockexpression;
  }
}
