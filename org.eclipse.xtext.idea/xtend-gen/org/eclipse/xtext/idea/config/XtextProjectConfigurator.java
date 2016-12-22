/**
 * Copyright (c) 2015 itemis AG (http://www.itemis.eu) and others.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 */
package org.eclipse.xtext.idea.config;

import com.intellij.facet.Facet;
import com.intellij.facet.FacetConfiguration;
import com.intellij.facet.FacetManager;
import com.intellij.facet.FacetType;
import com.intellij.facet.FacetTypeRegistry;
import com.intellij.openapi.module.Module;
import org.eclipse.xtext.idea.facet.AbstractFacetConfiguration;
import org.eclipse.xtext.idea.facet.AbstractFacetType;

/**
 * @author dhuebner - Initial contribution and API
 */
@SuppressWarnings("all")
public class XtextProjectConfigurator {
  public AbstractFacetConfiguration createOrGetFacetConf(final Module module, final String id) {
    final FacetType facetType = FacetTypeRegistry.getInstance().findFacetType(id);
    if ((!(facetType instanceof AbstractFacetType<?>))) {
      return null;
    }
    final FacetManager mnr = FacetManager.getInstance(module);
    Facet _elvis = null;
    Facet _findFacet = mnr.<Facet>findFacet(facetType.getId(), facetType.getDefaultFacetName());
    if (_findFacet != null) {
      _elvis = _findFacet;
    } else {
      Facet _addFacet = FacetManager.getInstance(module).<Facet, FacetConfiguration>addFacet(facetType, facetType.getDefaultFacetName(), null);
      _elvis = _addFacet;
    }
    Facet facet = _elvis;
    FacetConfiguration _configuration = facet.getConfiguration();
    return ((AbstractFacetConfiguration) _configuration);
  }
}
