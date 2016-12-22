/**
 * Copyright (c) 2015 itemis AG (http://www.itemis.eu) and others.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 */
package org.eclipse.xtext.idea.extensions;

import org.eclipse.xtend.lib.annotations.FinalFieldsConstructor;
import org.eclipse.xtext.idea.extensions.AbstractExecutableExtensionPoint;
import org.eclipse.xtext.internal.AbstractResourceServiceProviderDescriptor;

@FinalFieldsConstructor
@SuppressWarnings("all")
public class ResourceServiceProviderDescriptor extends AbstractResourceServiceProviderDescriptor {
  private final AbstractExecutableExtensionPoint extensionPoint;
  
  private Object myExtension;
  
  @Override
  protected String getContributor() {
    return this.extensionPoint.getContributor();
  }
  
  @Override
  protected Object getExtension() {
    if ((this.myExtension == null)) {
      Object _createInstance = this.extensionPoint.createInstance();
      this.myExtension = _createInstance;
    }
    return this.myExtension;
  }
  
  public ResourceServiceProviderDescriptor(final AbstractExecutableExtensionPoint extensionPoint) {
    super();
    this.extensionPoint = extensionPoint;
  }
}
