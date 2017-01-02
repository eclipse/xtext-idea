package org.eclipse.xtext.linking.lazy.idea;

import com.google.inject.Injector;
import org.eclipse.xtext.ISetup;
import org.eclipse.xtext.idea.extensions.EcoreGlobalRegistries;
import org.eclipse.xtext.linking.lazy.idea.LazyLinkingTestLanguageStandaloneSetupIdea;

@SuppressWarnings("all")
public class LazyLinkingTestLanguageIdeaSetup implements ISetup {
  @Override
  public Injector createInjectorAndDoEMFRegistration() {
    Injector _xblockexpression = null;
    {
      EcoreGlobalRegistries.ensureInitialized();
      _xblockexpression = new LazyLinkingTestLanguageStandaloneSetupIdea().createInjector();
    }
    return _xblockexpression;
  }
}
