/**
 * Copyright (c) 2015 itemis AG (http://www.itemis.eu) and others.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 */
package org.eclipse.xtext.idea.extensions;

import com.intellij.openapi.application.Application;
import com.intellij.openapi.application.ApplicationManager;
import com.intellij.openapi.extensions.Extensions;
import java.util.List;
import java.util.Map;
import java.util.function.Consumer;
import org.eclipse.emf.ecore.EPackage;
import org.eclipse.emf.ecore.resource.Resource;
import org.eclipse.xtext.idea.extensions.EPackageEP;
import org.eclipse.xtext.idea.extensions.ResourceFactoryDescriptor;
import org.eclipse.xtext.idea.extensions.ResourceFactoryEP;
import org.eclipse.xtext.idea.extensions.ResourceServiceProviderDescriptor;
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
    EPackageEP[] _extensions = Extensions.<EPackageEP>getExtensions(EPackageEP.EP_NAME);
    final Consumer<EPackageEP> _function = (EPackageEP it) -> {
      String _nsURI = it.getNsURI();
      EPackageEP _createDescriptor = it.createDescriptor();
      packageRegistry.put(_nsURI, _createDescriptor);
    };
    ((List<EPackageEP>)Conversions.doWrapArray(_extensions)).forEach(_function);
    final Map<String, Object> extensionToFactoryMap = Resource.Factory.Registry.INSTANCE.getExtensionToFactoryMap();
    ResourceFactoryEP[] _extensions_1 = Extensions.<ResourceFactoryEP>getExtensions(ResourceFactoryEP.EP_NAME);
    final Consumer<ResourceFactoryEP> _function_1 = (ResourceFactoryEP it) -> {
      String _type = it.getType();
      ResourceFactoryDescriptor _createDescriptor = it.createDescriptor();
      extensionToFactoryMap.put(_type, _createDescriptor);
    };
    ((List<ResourceFactoryEP>)Conversions.doWrapArray(_extensions_1)).forEach(_function_1);
    final IResourceServiceProvider.Registry registry = IResourceServiceProvider.Registry.INSTANCE;
    ResourceServiceProviderEP[] _extensions_2 = Extensions.<ResourceServiceProviderEP>getExtensions(ResourceServiceProviderEP.EP_NAME);
    final Consumer<ResourceServiceProviderEP> _function_2 = (ResourceServiceProviderEP it) -> {
      String _uriExtension = it.getUriExtension();
      boolean _tripleNotEquals = (_uriExtension != null);
      if (_tripleNotEquals) {
        Map<String, Object> _extensionToFactoryMap = registry.getExtensionToFactoryMap();
        String _uriExtension_1 = it.getUriExtension();
        ResourceServiceProviderDescriptor _createDescriptor = it.createDescriptor();
        _extensionToFactoryMap.put(_uriExtension_1, _createDescriptor);
      }
      String _protocolName = it.getProtocolName();
      boolean _tripleNotEquals_1 = (_protocolName != null);
      if (_tripleNotEquals_1) {
        Map<String, Object> _protocolToFactoryMap = registry.getProtocolToFactoryMap();
        String _protocolName_1 = it.getProtocolName();
        ResourceServiceProviderDescriptor _createDescriptor_1 = it.createDescriptor();
        _protocolToFactoryMap.put(_protocolName_1, _createDescriptor_1);
      }
      String _contentTypeIdentifier = it.getContentTypeIdentifier();
      boolean _tripleNotEquals_2 = (_contentTypeIdentifier != null);
      if (_tripleNotEquals_2) {
        Map<String, Object> _contentTypeToFactoryMap = registry.getContentTypeToFactoryMap();
        String _contentTypeIdentifier_1 = it.getContentTypeIdentifier();
        ResourceServiceProviderDescriptor _createDescriptor_2 = it.createDescriptor();
        _contentTypeToFactoryMap.put(_contentTypeIdentifier_1, _createDescriptor_2);
      }
    };
    ((List<ResourceServiceProviderEP>)Conversions.doWrapArray(_extensions_2)).forEach(_function_2);
  }
  
  public static EcoreGlobalRegistries ensureInitialized() {
    Application _application = ApplicationManager.getApplication();
    return _application.<EcoreGlobalRegistries>getComponent(EcoreGlobalRegistries.class);
  }
}
