/**
 * Copyright (c) 2015 itemis AG (http://www.itemis.eu) and others.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 */
package org.eclipse.xtext.idea.documentation;

import com.google.inject.Inject;
import com.intellij.openapi.module.Module;
import com.intellij.openapi.module.ModuleUtilCore;
import org.eclipse.emf.ecore.EObject;
import org.eclipse.emf.ecore.resource.Resource;
import org.eclipse.xtend2.lib.StringConcatenation;
import org.eclipse.xtext.EcoreUtil2;
import org.eclipse.xtext.documentation.IEObjectDocumentationProvider;
import org.eclipse.xtext.naming.IQualifiedNameProvider;
import org.eclipse.xtext.naming.QualifiedName;
import org.eclipse.xtext.psi.PsiEObject;
import org.eclipse.xtext.xbase.lib.Functions.Function1;
import org.eclipse.xtext.xbase.lib.IterableExtensions;

/**
 * @author Moritz Eysholdt - Initial contribution and API
 */
@SuppressWarnings("all")
public class IdeaDeclarationDocumentationProvider {
  @Inject
  private IQualifiedNameProvider qNameProvider;
  
  @Inject
  private IEObjectDocumentationProvider eObjectDocProvider;
  
  protected String getElementInfo(final PsiEObject element) {
    final EObject obj = element.getEObject();
    final QualifiedName name = this.qNameProvider.getFullyQualifiedName(obj);
    if ((name != null)) {
      StringConcatenation _builder = new StringConcatenation();
      _builder.append("<b>");
      String _name = obj.eClass().getName();
      _builder.append(_name);
      _builder.append(" \'");
      _builder.append(name);
      _builder.append("\'</b>");
      return _builder.toString();
    } else {
      final Function1<EObject, QualifiedName> _function = (EObject it) -> {
        return this.qNameProvider.getFullyQualifiedName(it);
      };
      final Function1<QualifiedName, Boolean> _function_1 = (QualifiedName it) -> {
        return Boolean.valueOf((it != null));
      };
      final QualifiedName container = IterableExtensions.<QualifiedName>findFirst(IterableExtensions.<EObject, QualifiedName>map(EcoreUtil2.getAllContainers(obj), _function), _function_1);
      StringConcatenation _builder_1 = new StringConcatenation();
      _builder_1.append("<b>");
      String _name_1 = obj.eClass().getName();
      _builder_1.append(_name_1);
      _builder_1.append(" in \'");
      _builder_1.append(container);
      _builder_1.append("\'</b>");
      return _builder_1.toString();
    }
  }
  
  protected String getFileInfo(final PsiEObject element) {
    final Resource resource = element.getEObject().eResource();
    final Module module = ModuleUtilCore.findModuleForPsiElement(element);
    if ((module != null)) {
      StringConcatenation _builder = new StringConcatenation();
      _builder.append("[");
      String _name = module.getName();
      _builder.append(_name);
      _builder.append("] ");
      String _lastSegment = resource.getURI().lastSegment();
      _builder.append(_lastSegment);
      return _builder.toString();
    }
    return resource.getURI().lastSegment();
  }
  
  public String getQuickNavigateInfo(final PsiEObject element) {
    StringConcatenation _builder = new StringConcatenation();
    String _elementInfo = this.getElementInfo(element);
    _builder.append(_elementInfo);
    _builder.newLineIfNotEmpty();
    String _fileInfo = this.getFileInfo(element);
    _builder.append(_fileInfo);
    _builder.newLineIfNotEmpty();
    return _builder.toString();
  }
  
  public String generateDoc(final PsiEObject element) {
    StringConcatenation _builder = new StringConcatenation();
    _builder.append("<p>");
    _builder.newLine();
    _builder.append("\t");
    String _elementInfo = this.getElementInfo(element);
    _builder.append(_elementInfo, "\t");
    _builder.newLineIfNotEmpty();
    _builder.append("</p>");
    _builder.newLine();
    _builder.append("<br/>");
    _builder.newLine();
    _builder.append("<p>");
    _builder.newLine();
    _builder.append("\t");
    String _fileInfo = this.getFileInfo(element);
    _builder.append(_fileInfo, "\t");
    _builder.newLineIfNotEmpty();
    _builder.append("</p>");
    _builder.newLine();
    _builder.append("<br/>");
    _builder.newLine();
    _builder.append("<p>");
    _builder.newLine();
    _builder.append("\t");
    String _documentation = this.eObjectDocProvider.getDocumentation(element.getEObject());
    _builder.append(_documentation, "\t");
    _builder.newLineIfNotEmpty();
    _builder.append("</p>");
    _builder.newLine();
    return _builder.toString();
  }
}
