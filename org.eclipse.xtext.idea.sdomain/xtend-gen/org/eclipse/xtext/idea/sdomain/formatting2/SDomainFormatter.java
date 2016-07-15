/**
 * Copyright (c) 2015 itemis AG (http://www.itemis.eu) and others.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 */
package org.eclipse.xtext.idea.sdomain.formatting2;

import com.google.inject.Inject;
import java.util.Arrays;
import org.eclipse.emf.common.util.EList;
import org.eclipse.emf.ecore.EObject;
import org.eclipse.xtext.formatting2.AbstractFormatter2;
import org.eclipse.xtext.formatting2.IFormattableDocument;
import org.eclipse.xtext.idea.sdomain.sDomain.Element;
import org.eclipse.xtext.idea.sdomain.sDomain.File;
import org.eclipse.xtext.idea.sdomain.sDomain.Namespace;
import org.eclipse.xtext.idea.sdomain.services.SDomainGrammarAccess;
import org.eclipse.xtext.resource.XtextResource;
import org.eclipse.xtext.xbase.lib.Extension;

@SuppressWarnings("all")
public class SDomainFormatter extends AbstractFormatter2 {
  @Inject
  @Extension
  private SDomainGrammarAccess _sDomainGrammarAccess;
  
  protected void _format(final File file, @Extension final IFormattableDocument document) {
    EList<Element> _elements = file.getElements();
    for (final Element elements : _elements) {
      document.<Element>format(elements);
    }
  }
  
  protected void _format(final Namespace namespace, @Extension final IFormattableDocument document) {
    EList<Element> _elements = namespace.getElements();
    for (final Element elements : _elements) {
      document.<Element>format(elements);
    }
  }
  
  public void format(final Object namespace, final IFormattableDocument document) {
    if (namespace instanceof XtextResource) {
      _format((XtextResource)namespace, document);
      return;
    } else if (namespace instanceof Namespace) {
      _format((Namespace)namespace, document);
      return;
    } else if (namespace instanceof File) {
      _format((File)namespace, document);
      return;
    } else if (namespace instanceof EObject) {
      _format((EObject)namespace, document);
      return;
    } else if (namespace == null) {
      _format((Void)null, document);
      return;
    } else if (namespace != null) {
      _format(namespace, document);
      return;
    } else {
      throw new IllegalArgumentException("Unhandled parameter types: " +
        Arrays.<Object>asList(namespace, document).toString());
    }
  }
}
