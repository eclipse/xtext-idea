/**
 * Copyright (c) 2015 itemis AG (http://www.itemis.eu) and others.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 */
package org.eclipse.xtext.idea.common.types;

import com.intellij.psi.PsiArrayType;
import com.intellij.psi.PsiClass;
import com.intellij.psi.PsiClassType;
import com.intellij.psi.PsiField;
import com.intellij.psi.PsiMethod;
import com.intellij.psi.PsiNameHelper;
import com.intellij.psi.PsiParameter;
import com.intellij.psi.PsiParameterList;
import com.intellij.psi.PsiPrimitiveType;
import com.intellij.psi.PsiType;
import com.intellij.psi.PsiTypeParameter;
import com.intellij.psi.PsiTypeParameterListOwner;
import org.eclipse.emf.common.util.URI;
import org.eclipse.xtext.common.types.access.impl.Primitives;
import org.eclipse.xtext.common.types.access.impl.URIHelperConstants;
import org.eclipse.xtext.idea.common.types.UnresolvedPsiClassType;

@SuppressWarnings("all")
public class StubURIHelper implements URIHelperConstants {
  public URI getFullURI(final String name) {
    return this.createURI(this.appendTypeFragment(this.appendClassResourceURI(this.createURIBuilder(), name).append("#"), name));
  }
  
  public URI createResourceURI(final String name) {
    return this.createURI(this.appendClassResourceURI(this.createURIBuilder(), name));
  }
  
  public String getFragment(final String name) {
    return this.appendTypeFragment(this.createFragmentBuilder(), name).toString();
  }
  
  protected StringBuilder appendClassResourceURI(final StringBuilder builder, final String name) {
    final String topLevelTypeName = this.trimBrackets(this.trimInnerType(name));
    Class<?> _forName = Primitives.forName(topLevelTypeName);
    boolean _tripleNotEquals = (_forName != null);
    if (_tripleNotEquals) {
      builder.append(URIHelperConstants.PRIMITIVES);
    } else {
      builder.append(URIHelperConstants.OBJECTS).append(topLevelTypeName);
    }
    return builder;
  }
  
  protected String trimInnerType(final String name) {
    final int innerTypeIndex = name.indexOf("$");
    if ((innerTypeIndex == (-1))) {
      return name;
    }
    final int simpleNameIndex = name.lastIndexOf(".");
    if (((simpleNameIndex + 1) == innerTypeIndex)) {
      return name;
    }
    return name.substring(0, innerTypeIndex);
  }
  
  protected String trimBrackets(final String name) {
    boolean _endsWith = name.endsWith("]");
    if (_endsWith) {
      final int endIndex = name.indexOf("[");
      return name.substring(0, endIndex);
    } else {
      return name;
    }
  }
  
  protected StringBuilder appendTypeFragment(final StringBuilder builder, final String name) {
    return builder.append(name);
  }
  
  public URI getFullURI(final PsiClass psiClass) {
    return this.createURI(this.appendFullURI(this.createURIBuilder(), psiClass));
  }
  
  public URI getFullURI(final PsiMethod method) {
    URI _xblockexpression = null;
    {
      int _parametersCount = method.getParameterList().getParametersCount();
      boolean _notEquals = (_parametersCount != 0);
      if (_notEquals) {
        String _string = method.toString();
        throw new IllegalArgumentException(_string);
      }
      _xblockexpression = this.createURI(this.appendFullURI(this.createURIBuilder(), method.getContainingClass()).append(".").append(method.getName()).append("()"));
    }
    return _xblockexpression;
  }
  
  public URI getFullURI(final PsiField field) {
    return this.createURI(this.appendFullURI(this.createURIBuilder(), field.getContainingClass()).append(".").append(field.getName()));
  }
  
  public URI getFullURI(final PsiType type) throws UnresolvedPsiClassType {
    return this.createURI(this.appendFullURI(this.createURIBuilder(), type));
  }
  
  protected StringBuilder appendFullURI(final StringBuilder it, final PsiType type) throws UnresolvedPsiClassType {
    return this.appendTypeFragment(this.appendTypeResourceURI(it, type).append("#"), type);
  }
  
  protected StringBuilder appendFullURI(final StringBuilder it, final PsiClass psiClass) {
    return this.appendClassFragment(this.appendClassResourceURI(it, psiClass).append("#"), psiClass);
  }
  
  protected StringBuilder appendTypeResourceURI(final StringBuilder builder, final PsiType type) throws UnresolvedPsiClassType {
    StringBuilder _switchResult = null;
    boolean _matched = false;
    if (type instanceof PsiArrayType) {
      _matched=true;
      _switchResult = this.appendTypeResourceURI(builder, ((PsiArrayType)type).getComponentType());
    }
    if (!_matched) {
      if (type instanceof PsiPrimitiveType) {
        _matched=true;
        _switchResult = builder.append(URIHelperConstants.PRIMITIVES);
      }
    }
    if (!_matched) {
      if (type instanceof PsiClassType) {
        _matched=true;
        StringBuilder _xblockexpression = null;
        {
          final PsiClassType.ClassResolveResult resolveResult = ((PsiClassType)type).resolveGenerics();
          boolean _isValidResult = resolveResult.isValidResult();
          boolean _not = (!_isValidResult);
          if (_not) {
            throw new UnresolvedPsiClassType(((PsiClassType)type), resolveResult);
          }
          _xblockexpression = this.appendClassResourceURI(builder, resolveResult.getElement());
        }
        _switchResult = _xblockexpression;
      }
    }
    if (!_matched) {
      throw new IllegalStateException(("Unknown type: " + type));
    }
    return _switchResult;
  }
  
  protected StringBuilder appendClassResourceURI(final StringBuilder builder, final PsiClass psiClass) {
    StringBuilder _xblockexpression = null;
    {
      if ((psiClass instanceof PsiTypeParameter)) {
        return this.appendTypeParameterResourceURI(builder, ((PsiTypeParameter)psiClass));
      }
      final PsiClass containingClass = psiClass.getContainingClass();
      StringBuilder _xifexpression = null;
      if ((containingClass != null)) {
        _xifexpression = this.appendClassResourceURI(builder, containingClass);
      } else {
        _xifexpression = builder.append(URIHelperConstants.OBJECTS).append(psiClass.getQualifiedName());
      }
      _xblockexpression = _xifexpression;
    }
    return _xblockexpression;
  }
  
  protected StringBuilder appendTypeParameterResourceURI(final StringBuilder builder, final PsiTypeParameter typeParameter) {
    StringBuilder _switchResult = null;
    PsiTypeParameterListOwner _owner = typeParameter.getOwner();
    final PsiTypeParameterListOwner owner = _owner;
    boolean _matched = false;
    if (owner instanceof PsiClass) {
      _matched=true;
      _switchResult = this.appendClassResourceURI(builder, ((PsiClass)owner));
    }
    if (!_matched) {
      if (owner instanceof PsiMethod) {
        _matched=true;
        _switchResult = this.appendClassResourceURI(builder, ((PsiMethod)owner).getContainingClass());
      }
    }
    return _switchResult;
  }
  
  protected StringBuilder appendTypeFragment(final StringBuilder builder, final PsiType type) throws UnresolvedPsiClassType {
    StringBuilder _switchResult = null;
    boolean _matched = false;
    if (type instanceof PsiPrimitiveType) {
      _matched=true;
      _switchResult = builder.append(((PsiPrimitiveType)type).getCanonicalText(false));
    }
    if (!_matched) {
      if (type instanceof PsiClassType) {
        _matched=true;
        StringBuilder _xblockexpression = null;
        {
          final PsiClassType.ClassResolveResult resolveResult = ((PsiClassType)type).resolveGenerics();
          boolean _isValidResult = resolveResult.isValidResult();
          boolean _not = (!_isValidResult);
          if (_not) {
            throw new UnresolvedPsiClassType(((PsiClassType)type), resolveResult);
          }
          _xblockexpression = this.appendClassFragment(builder, resolveResult.getElement());
        }
        _switchResult = _xblockexpression;
      }
    }
    if (!_matched) {
      if (type instanceof PsiArrayType) {
        _matched=true;
        _switchResult = this.appendTypeFragment(builder, ((PsiArrayType)type).getComponentType()).append("[]");
      }
    }
    if (!_matched) {
      throw new IllegalStateException(("Unknown type: " + type));
    }
    return _switchResult;
  }
  
  protected StringBuilder appendClassFragment(final StringBuilder builder, final PsiClass psiClass) {
    if ((psiClass instanceof PsiTypeParameter)) {
      return this.appendTypeParameterFragment(builder, ((PsiTypeParameter)psiClass));
    }
    final PsiClass containingClass = psiClass.getContainingClass();
    if ((containingClass == null)) {
      return builder.append(psiClass.getQualifiedName());
    } else {
      return this.appendClassFragment(builder, containingClass).append("$").append(psiClass.getName());
    }
  }
  
  protected StringBuilder appendTypeParameterFragment(final StringBuilder builder, final PsiTypeParameter typeParameter) {
    PsiTypeParameterListOwner _owner = typeParameter.getOwner();
    final PsiTypeParameterListOwner owner = _owner;
    boolean _matched = false;
    if (owner instanceof PsiClass) {
      _matched=true;
      this.appendClassFragment(builder, ((PsiClass)owner));
    }
    if (!_matched) {
      if (owner instanceof PsiMethod) {
        _matched=true;
        this.appendMethodFragment(builder, ((PsiMethod)owner));
      }
    }
    return builder.append("/").append(typeParameter.getName());
  }
  
  protected StringBuilder appendMethodFragment(final StringBuilder builder, final PsiMethod method) {
    this.appendClassFragment(builder, method.getContainingClass());
    builder.append(".").append(method.getName()).append("(");
    final PsiParameterList parameterList = method.getParameterList();
    final int parameterCount = parameterList.getParametersCount();
    if ((parameterCount > 0)) {
      final PsiParameter[] parameters = parameterList.getParameters();
      for (int i = 0; (i < parameterCount); i++) {
        {
          if ((i != 0)) {
            builder.append(",");
          }
          this.appendTypeName(builder, parameters[i].getType());
        }
      }
    }
    return builder.append(")");
  }
  
  public StringBuilder appendTypeName(final StringBuilder builder, final PsiType type) {
    StringBuilder _switchResult = null;
    boolean _matched = false;
    if (type instanceof PsiPrimitiveType) {
      _matched=true;
      _switchResult = builder.append(((PsiPrimitiveType)type).getCanonicalText());
    }
    if (!_matched) {
      if (type instanceof PsiClassType) {
        _matched=true;
        StringBuilder _xblockexpression = null;
        {
          final PsiClass resolved = ((PsiClassType)type).resolve();
          StringBuilder _switchResult_1 = null;
          boolean _matched_1 = false;
          if (resolved instanceof PsiTypeParameter) {
            _matched_1=true;
            _switchResult_1 = builder.append(((PsiTypeParameter)resolved).getName());
          }
          if (!_matched_1) {
            if ((resolved != null)) {
              _matched_1=true;
              _switchResult_1 = this.appendTypeName(builder, resolved);
            }
          }
          if (!_matched_1) {
            _switchResult_1 = builder.append(PsiNameHelper.getQualifiedClassName(((PsiClassType)type).getCanonicalText(), false));
          }
          _xblockexpression = _switchResult_1;
        }
        _switchResult = _xblockexpression;
      }
    }
    if (!_matched) {
      if (type instanceof PsiArrayType) {
        _matched=true;
        _switchResult = this.appendTypeName(builder, ((PsiArrayType)type).getComponentType()).append("[]");
      }
    }
    if (!_matched) {
      String _canonicalText = null;
      if (type!=null) {
        _canonicalText=type.getCanonicalText();
      }
      String _plus = ("Unknown type: " + _canonicalText);
      throw new IllegalStateException(_plus);
    }
    return _switchResult;
  }
  
  private StringBuilder appendTypeName(final StringBuilder builder, final PsiClass clazz) {
    StringBuilder _xblockexpression = null;
    {
      final PsiClass container = clazz.getContainingClass();
      StringBuilder _xifexpression = null;
      if ((container != null)) {
        _xifexpression = this.appendTypeName(builder, container).append("$").append(clazz.getName());
      } else {
        _xifexpression = builder.append(clazz.getQualifiedName());
      }
      _xblockexpression = _xifexpression;
    }
    return _xblockexpression;
  }
  
  protected StringBuilder createURIBuilder() {
    return new StringBuilder(48).append(URIHelperConstants.PROTOCOL).append(":");
  }
  
  protected StringBuilder createFragmentBuilder() {
    return new StringBuilder(32);
  }
  
  protected URI createURI(final StringBuilder uriBuilder) {
    return URI.createURI(uriBuilder.toString());
  }
}
