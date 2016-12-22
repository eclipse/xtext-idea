/**
 * Copyright (c) 2015 itemis AG (http://www.itemis.eu) and others.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 */
package org.eclipse.xtext.idea.extensions;

import com.google.common.base.Objects;
import com.intellij.openapi.application.ApplicationManager;
import com.intellij.openapi.extensions.Extensions;
import java.util.List;
import java.util.Map;
import java.util.function.Consumer;
import org.eclipse.emf.ecore.EPackage;
import org.eclipse.emf.ecore.resource.Resource;
import org.eclipse.xtext.idea.extensions.EPackageEP;
import org.eclipse.xtext.idea.extensions.ResourceFactoryEP;
import org.eclipse.xtext.idea.extensions.ResourceServiceProviderEP;
import org.eclipse.xtext.resource.IResourceServiceProvider;
import org.eclipse.xtext.xbase.lib.Conversions;

/**
 * @author kosyakov - Initial contribution and API
 */
@SuppressWarnings("all")
public class EcoreGlobalRegistries {
  public EcoreGlobalRegistries() {
    final EPackage.Registry packageRegistry = EPackage.Registry.INSTANCE;
    final Consumer<EPackageEP> _function = (EPackageEP it) -> {
      packageRegistry.put(it.getNsURI(), it.createDescriptor());
    };
    ((List<EPackageEP>)Conversions.doWrapArray(Extensions.<EPackageEP>getExtensions(EPackageEP.EP_NAME))).forEach(_function);
    final Map<String, Object> extensionToFactoryMap = Resource.Factory.Registry.INSTANCE.getExtensionToFactoryMap();
    final Consumer<ResourceFactoryEP> _function_1 = (ResourceFactoryEP it) -> {
      extensionToFactoryMap.put(it.getType(), it.createDescriptor());
    };
    ((List<ResourceFactoryEP>)Conversions.doWrapArray(Extensions.<ResourceFactoryEP>getExtensions(ResourceFactoryEP.EP_NAME))).forEach(_function_1);
    final IResourceServiceProvider.Registry registry = IResourceServiceProvider.Registry.INSTANCE;
    final Consumer<ResourceServiceProviderEP> _function_2 = (ResourceServiceProviderEP it) -> {
      String _uriExtension = it.getUriExtension();
      boolean _notEquals = (!Objects.equal(_uriExtension, null));
      if (_notEquals) {
        registry.getExtensionToFactoryMap().put(it.getUriExtension(), it.createDescriptor());
      }
      String _protocolName = it.getProtocolName();
      boolean _notEquals_1 = (!Objects.equal(_protocolName, null));
      if (_notEquals_1) {
        registry.getProtocolToFactoryMap().put(it.getProtocolName(), it.createDescriptor());
      }
      String _contentTypeIdentifier = it.getContentTypeIdentifier();
      boolean _notEquals_2 = (!Objects.equal(_contentTypeIdentifier, null));
      if (_notEquals_2) {
        registry.getContentTypeToFactoryMap().put(it.getContentTypeIdentifier(), it.createDescriptor());
      }
    };
    ((List<ResourceServiceProviderEP>)Conversions.doWrapArray(Extensions.<ResourceServiceProviderEP>getExtensions(ResourceServiceProviderEP.EP_NAME))).forEach(_function_2);
  }
  
  public static EcoreGlobalRegistries ensureInitialized() {
    return ApplicationManager.getApplication().<EcoreGlobalRegistries>getComponent(EcoreGlobalRegistries.class);
  }
}
