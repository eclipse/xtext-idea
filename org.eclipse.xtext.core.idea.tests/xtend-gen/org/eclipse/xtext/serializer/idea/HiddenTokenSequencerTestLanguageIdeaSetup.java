package org.eclipse.xtext.serializer.idea;

import com.google.inject.Injector;
import org.eclipse.xtext.ISetup;
import org.eclipse.xtext.idea.extensions.EcoreGlobalRegistries;
import org.eclipse.xtext.serializer.idea.HiddenTokenSequencerTestLanguageStandaloneSetupIdea;

@SuppressWarnings("all")
public class HiddenTokenSequencerTestLanguageIdeaSetup implements ISetup {
  @Override
  public Injector createInjectorAndDoEMFRegistration() {
    Injector _xblockexpression = null;
    {
      EcoreGlobalRegistries.ensureInitialized();
      _xblockexpression = new HiddenTokenSequencerTestLanguageStandaloneSetupIdea().createInjector();
    }
    return _xblockexpression;
  }
}
