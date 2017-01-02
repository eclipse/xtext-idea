package org.eclipse.xtext.formatting2.regionaccess.internal.idea;

import com.google.inject.Injector;
import org.eclipse.xtext.ISetup;
import org.eclipse.xtext.formatting2.regionaccess.internal.idea.RegionAccessTestLanguageStandaloneSetupIdea;
import org.eclipse.xtext.idea.extensions.EcoreGlobalRegistries;

@SuppressWarnings("all")
public class RegionAccessTestLanguageIdeaSetup implements ISetup {
  @Override
  public Injector createInjectorAndDoEMFRegistration() {
    Injector _xblockexpression = null;
    {
      EcoreGlobalRegistries.ensureInitialized();
      _xblockexpression = new RegionAccessTestLanguageStandaloneSetupIdea().createInjector();
    }
    return _xblockexpression;
  }
}
