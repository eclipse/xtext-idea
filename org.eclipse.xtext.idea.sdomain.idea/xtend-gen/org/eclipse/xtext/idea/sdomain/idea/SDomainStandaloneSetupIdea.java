/**
 * Copyright (c) 2015 itemis AG (http://www.itemis.eu) and others.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 */
package org.eclipse.xtext.idea.sdomain.idea;

import com.google.inject.Guice;
import com.google.inject.Injector;
import com.google.inject.Module;
import org.eclipse.xtext.idea.sdomain.SDomainRuntimeModule;
import org.eclipse.xtext.idea.sdomain.SDomainStandaloneSetupGenerated;
import org.eclipse.xtext.idea.sdomain.idea.SDomainIdeaModule;
import org.eclipse.xtext.util.Modules2;

@SuppressWarnings("all")
public class SDomainStandaloneSetupIdea extends SDomainStandaloneSetupGenerated {
  @Override
  public Injector createInjector() {
    final SDomainRuntimeModule runtimeModule = new SDomainRuntimeModule();
    final SDomainIdeaModule ideaModule = new SDomainIdeaModule();
    final Module mergedModule = Modules2.mixin(runtimeModule, ideaModule);
    return Guice.createInjector(mergedModule);
  }
}
