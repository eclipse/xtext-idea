/**
 * Copyright (c) 2015 itemis AG (http://www.itemis.eu) and others.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 */
package org.eclipse.xtext.idea.common.types;

import com.google.common.base.Objects;
import com.google.inject.Inject;
import com.intellij.openapi.application.ApplicationManager;
import com.intellij.openapi.progress.ProgressIndicatorProvider;
import com.intellij.openapi.project.Project;
import com.intellij.openapi.util.Computable;
import com.intellij.psi.JavaPsiFacade;
import com.intellij.psi.JavaTokenType;
import com.intellij.psi.PsiAnnotation;
import com.intellij.psi.PsiAnnotationMemberValue;
import com.intellij.psi.PsiAnnotationMethod;
import com.intellij.psi.PsiAnonymousClass;
import com.intellij.psi.PsiArrayInitializerMemberValue;
import com.intellij.psi.PsiArrayType;
import com.intellij.psi.PsiBinaryExpression;
import com.intellij.psi.PsiCapturedWildcardType;
import com.intellij.psi.PsiClass;
import com.intellij.psi.PsiClassObjectAccessExpression;
import com.intellij.psi.PsiClassType;
import com.intellij.psi.PsiCompiledElement;
import com.intellij.psi.PsiConstantEvaluationHelper;
import com.intellij.psi.PsiElement;
import com.intellij.psi.PsiElementFactory;
import com.intellij.psi.PsiEnumConstant;
import com.intellij.psi.PsiExpression;
import com.intellij.psi.PsiField;
import com.intellij.psi.PsiFile;
import com.intellij.psi.PsiJavaFile;
import com.intellij.psi.PsiMethod;
import com.intellij.psi.PsiModifier;
import com.intellij.psi.PsiModifierList;
import com.intellij.psi.PsiModifierListOwner;
import com.intellij.psi.PsiNameHelper;
import com.intellij.psi.PsiNameValuePair;
import com.intellij.psi.PsiParameter;
import com.intellij.psi.PsiParameterList;
import com.intellij.psi.PsiReferenceExpression;
import com.intellij.psi.PsiType;
import com.intellij.psi.PsiTypeParameter;
import com.intellij.psi.PsiTypeParameterListOwner;
import com.intellij.psi.PsiWildcardType;
import com.intellij.psi.tree.IElementType;
import java.util.Arrays;
import java.util.List;
import org.eclipse.emf.common.util.EList;
import org.eclipse.emf.common.util.URI;
import org.eclipse.emf.ecore.InternalEObject;
import org.eclipse.emf.ecore.util.InternalEList;
import org.eclipse.xtext.common.types.JvmAnnotationAnnotationValue;
import org.eclipse.xtext.common.types.JvmAnnotationReference;
import org.eclipse.xtext.common.types.JvmAnnotationTarget;
import org.eclipse.xtext.common.types.JvmAnnotationType;
import org.eclipse.xtext.common.types.JvmAnnotationValue;
import org.eclipse.xtext.common.types.JvmBooleanAnnotationValue;
import org.eclipse.xtext.common.types.JvmByteAnnotationValue;
import org.eclipse.xtext.common.types.JvmCharAnnotationValue;
import org.eclipse.xtext.common.types.JvmConstructor;
import org.eclipse.xtext.common.types.JvmDeclaredType;
import org.eclipse.xtext.common.types.JvmDoubleAnnotationValue;
import org.eclipse.xtext.common.types.JvmEnumAnnotationValue;
import org.eclipse.xtext.common.types.JvmEnumerationLiteral;
import org.eclipse.xtext.common.types.JvmExecutable;
import org.eclipse.xtext.common.types.JvmField;
import org.eclipse.xtext.common.types.JvmFloatAnnotationValue;
import org.eclipse.xtext.common.types.JvmFormalParameter;
import org.eclipse.xtext.common.types.JvmGenericArrayTypeReference;
import org.eclipse.xtext.common.types.JvmGenericType;
import org.eclipse.xtext.common.types.JvmInnerTypeReference;
import org.eclipse.xtext.common.types.JvmIntAnnotationValue;
import org.eclipse.xtext.common.types.JvmLongAnnotationValue;
import org.eclipse.xtext.common.types.JvmLowerBound;
import org.eclipse.xtext.common.types.JvmMember;
import org.eclipse.xtext.common.types.JvmOperation;
import org.eclipse.xtext.common.types.JvmParameterizedTypeReference;
import org.eclipse.xtext.common.types.JvmShortAnnotationValue;
import org.eclipse.xtext.common.types.JvmStringAnnotationValue;
import org.eclipse.xtext.common.types.JvmType;
import org.eclipse.xtext.common.types.JvmTypeAnnotationValue;
import org.eclipse.xtext.common.types.JvmTypeParameter;
import org.eclipse.xtext.common.types.JvmTypeParameterDeclarator;
import org.eclipse.xtext.common.types.JvmTypeReference;
import org.eclipse.xtext.common.types.JvmUnknownTypeReference;
import org.eclipse.xtext.common.types.JvmUpperBound;
import org.eclipse.xtext.common.types.JvmVisibility;
import org.eclipse.xtext.common.types.JvmVoid;
import org.eclipse.xtext.common.types.JvmWildcardTypeReference;
import org.eclipse.xtext.common.types.TypesFactory;
import org.eclipse.xtext.common.types.access.impl.AbstractDeclaredTypeFactory;
import org.eclipse.xtext.common.types.access.impl.ITypeFactory;
import org.eclipse.xtext.common.types.impl.JvmTypeConstraintImplCustom;
import org.eclipse.xtext.idea.common.types.StubURIHelper;
import org.eclipse.xtext.idea.common.types.UnresolvedPsiClassType;
import org.eclipse.xtext.psi.IPsiModelAssociator;
import org.eclipse.xtext.psi.PsiElementProvider;
import org.eclipse.xtext.util.internal.Stopwatches;
import org.eclipse.xtext.xbase.lib.Conversions;
import org.eclipse.xtext.xbase.lib.Exceptions;
import org.eclipse.xtext.xbase.lib.Extension;
import org.eclipse.xtext.xbase.lib.Functions.Function1;
import org.eclipse.xtext.xbase.lib.IterableExtensions;
import org.eclipse.xtext.xbase.lib.ListExtensions;
import org.eclipse.xtext.xbase.lib.ObjectExtensions;
import org.eclipse.xtext.xbase.lib.Procedures.Procedure0;
import org.eclipse.xtext.xbase.lib.Procedures.Procedure1;

@SuppressWarnings("all")
public class PsiBasedTypeFactory extends AbstractDeclaredTypeFactory implements ITypeFactory<PsiClass, JvmDeclaredType> {
  /**
   * FIXME: Remove the constants below when com.intellij.psi.impl.compiled.ClsFieldImpl#computeConstantValue() is fixed.
   */
  private final static String DOUBLE_POSITIVE_INF = "1.0 / 0.0";
  
  private final static String DOUBLE_NEGATIVE_INF = "-1.0 / 0.0";
  
  private final static String DOUBLE_NAN = "0.0d / 0.0";
  
  private final static String FLOAT_POSITIVE_INF = "1.0f / 0.0";
  
  private final static String FLOAT_NEGATIVE_INF = "-1.0f / 0.0";
  
  private final static String FLOAT_NAN = "0.0f / 0.0";
  
  private final Stopwatches.StoppedTask createTypeTask = Stopwatches.forTask("PsiClassFactory.createType");
  
  @Extension
  private final TypesFactory _typesFactory = TypesFactory.eINSTANCE;
  
  @Extension
  private final StubURIHelper uriHelper;
  
  @Extension
  private final IPsiModelAssociator psiModelAssociator;
  
  @Inject
  public PsiBasedTypeFactory(final StubURIHelper uriHelper, final IPsiModelAssociator psiModelAssociator) {
    this.uriHelper = uriHelper;
    this.psiModelAssociator = psiModelAssociator;
  }
  
  @Override
  public JvmDeclaredType createType(final PsiClass psiClass) {
    JvmDeclaredType _xtrycatchfinallyexpression = null;
    try {
      JvmDeclaredType _xblockexpression = null;
      {
        this.createTypeTask.start();
        final Computable<JvmDeclaredType> _function = () -> {
          JvmDeclaredType _xblockexpression_1 = null;
          {
            final StringBuilder buffer = new StringBuilder(100);
            final String packageName = this.getPackageName(psiClass);
            boolean _notEquals = (!Objects.equal(packageName, null));
            if (_notEquals) {
              buffer.append(packageName).append(".");
            }
            final JvmDeclaredType type = this.createType(psiClass, buffer);
            type.setPackageName(packageName);
            _xblockexpression_1 = type;
          }
          return _xblockexpression_1;
        };
        _xblockexpression = ApplicationManager.getApplication().<JvmDeclaredType>runReadAction(_function);
      }
      _xtrycatchfinallyexpression = _xblockexpression;
    } finally {
      this.createTypeTask.stop();
    }
    return _xtrycatchfinallyexpression;
  }
  
  protected JvmDeclaredType createType(final PsiClass psiClass, final StringBuilder fqn) {
    JvmDeclaredType _xblockexpression = null;
    {
      ProgressIndicatorProvider.checkCanceled();
      if ((this.isAnonymous(psiClass) || this.isSynthetic(psiClass))) {
        throw new IllegalStateException("Cannot create type for anonymous or synthetic classes");
      }
      JvmDeclaredType _switchResult = null;
      boolean _matched = false;
      boolean _isAnnotationType = psiClass.isAnnotationType();
      if (_isAnnotationType) {
        _matched=true;
        _switchResult = this._typesFactory.createJvmAnnotationType();
      }
      if (!_matched) {
        boolean _isEnum = psiClass.isEnum();
        if (_isEnum) {
          _matched=true;
          _switchResult = this._typesFactory.createJvmEnumerationType();
        }
      }
      if (!_matched) {
        JvmGenericType _xblockexpression_1 = null;
        {
          final JvmGenericType genericType = this._typesFactory.createJvmGenericType();
          genericType.setInterface(psiClass.isInterface());
          genericType.setStrictFloatingPoint(psiClass.hasModifierProperty(PsiModifier.STRICTFP));
          this.createTypeParameters(genericType, psiClass);
          _xblockexpression_1 = genericType;
        }
        _switchResult = _xblockexpression_1;
      }
      final Procedure1<JvmDeclaredType> _function = (JvmDeclaredType it) -> {
        this.setTypeModifiers(it, psiClass);
        this.setVisibility(it, psiClass);
        it.setDeprecated(psiClass.isDeprecated());
        it.setSimpleName(psiClass.getName());
        fqn.append(psiClass.getName());
        it.internalSetIdentifier(fqn.toString());
        final Procedure0 _function_1 = () -> {
          this.createNestedTypes(it, psiClass, fqn);
        };
        this.append(fqn, "$", _function_1);
        fqn.append(".");
        this.createMethods(it, psiClass, fqn);
        boolean _isAnnotationType_1 = psiClass.isAnnotationType();
        boolean _not = (!_isAnnotationType_1);
        if (_not) {
          this.createFields(it, psiClass, fqn);
        }
        this.createSuperTypes(it, psiClass);
        this.createAnnotationValues(it, psiClass);
        final PsiElementProvider _function_2 = () -> {
          return psiClass;
        };
        this.psiModelAssociator.associate(it, _function_2);
      };
      _xblockexpression = ObjectExtensions.<JvmDeclaredType>operator_doubleArrow(_switchResult, _function);
    }
    return _xblockexpression;
  }
  
  protected void createAnnotationValues(final JvmAnnotationTarget it, final PsiModifierListOwner psiModifierListOwner) {
    final PsiModifierList modifierList = psiModifierListOwner.getModifierList();
    PsiAnnotation[] _annotations = modifierList.getAnnotations();
    for (final PsiAnnotation annotation : _annotations) {
      {
        final JvmAnnotationReference annotationReference = this.createAnnotationReference(annotation);
        boolean _notEquals = (!Objects.equal(annotationReference, null));
        if (_notEquals) {
          this.<JvmAnnotationReference>addUnique(it.getAnnotations(), annotationReference);
        }
      }
    }
  }
  
  protected JvmAnnotationReference createAnnotationReference(final PsiAnnotation annotation) {
    JvmAnnotationReference _xblockexpression = null;
    {
      final PsiElement psiClass = annotation.getNameReferenceElement().resolve();
      JvmAnnotationReference _xifexpression = null;
      if ((psiClass instanceof PsiClass)) {
        JvmAnnotationReference _createJvmAnnotationReference = this._typesFactory.createJvmAnnotationReference();
        final Procedure1<JvmAnnotationReference> _function = (JvmAnnotationReference it) -> {
          it.setAnnotation(this.createAnnotationProxy(((PsiClass)psiClass)));
          PsiNameValuePair[] _attributes = annotation.getParameterList().getAttributes();
          for (final PsiNameValuePair attribute : _attributes) {
            {
              String _elvis = null;
              String _name = attribute.getName();
              if (_name != null) {
                _elvis = _name;
              } else {
                _elvis = "value";
              }
              final String attributeName = _elvis;
              final Object value = this.computeAnnotationValue(attribute.getValue(), annotation.getProject());
              final Function1<PsiMethod, Boolean> _function_1 = (PsiMethod it_1) -> {
                String _name_1 = it_1.getName();
                return Boolean.valueOf(Objects.equal(_name_1, attributeName));
              };
              final PsiMethod method = IterableExtensions.<PsiMethod>findFirst(((Iterable<PsiMethod>)Conversions.doWrapArray(((PsiClass)psiClass).getMethods())), _function_1);
              final JvmAnnotationValue annotationValue = this.createAnnotationValue(value, method);
              annotationValue.setOperation(this.createMethodProxy(method));
              this.<JvmAnnotationValue>addUnique(it.getExplicitValues(), annotationValue);
            }
          }
        };
        _xifexpression = ObjectExtensions.<JvmAnnotationReference>operator_doubleArrow(_createJvmAnnotationReference, _function);
      }
      _xblockexpression = _xifexpression;
    }
    return _xblockexpression;
  }
  
  protected JvmOperation createMethodProxy(final PsiMethod method) {
    JvmOperation _createJvmOperation = this._typesFactory.createJvmOperation();
    final Procedure1<JvmOperation> _function = (JvmOperation it) -> {
      final URI uri = this.uriHelper.getFullURI(method);
      if ((it instanceof InternalEObject)) {
        ((InternalEObject)it).eSetProxyURI(uri);
      }
    };
    return ObjectExtensions.<JvmOperation>operator_doubleArrow(_createJvmOperation, _function);
  }
  
  protected JvmEnumerationLiteral createEnumLiteralProxy(final PsiEnumConstant constant) {
    JvmEnumerationLiteral _createJvmEnumerationLiteral = this._typesFactory.createJvmEnumerationLiteral();
    final Procedure1<JvmEnumerationLiteral> _function = (JvmEnumerationLiteral it) -> {
      final URI uri = this.uriHelper.getFullURI(constant);
      if ((it instanceof InternalEObject)) {
        ((InternalEObject)it).eSetProxyURI(uri);
      }
    };
    return ObjectExtensions.<JvmEnumerationLiteral>operator_doubleArrow(_createJvmEnumerationLiteral, _function);
  }
  
  protected JvmAnnotationType createAnnotationProxy(final PsiClass annotationType) {
    JvmAnnotationType _createJvmAnnotationType = this._typesFactory.createJvmAnnotationType();
    final Procedure1<JvmAnnotationType> _function = (JvmAnnotationType it) -> {
      final URI uri = this.uriHelper.getFullURI(annotationType);
      if ((it instanceof InternalEObject)) {
        ((InternalEObject)it).eSetProxyURI(uri);
      }
    };
    return ObjectExtensions.<JvmAnnotationType>operator_doubleArrow(_createJvmAnnotationType, _function);
  }
  
  protected void createFields(final JvmDeclaredType it, final PsiClass psiClass, final StringBuilder fqn) {
    PsiField[] _fields = psiClass.getFields();
    for (final PsiField field : _fields) {
      final Procedure0 _function = () -> {
        this.<JvmField>addUnique(it.getMembers(), this.createField(field, fqn));
      };
      this.preserve(fqn, _function);
    }
  }
  
  protected JvmField createField(final PsiField field, final StringBuilder fqn) {
    JvmField _xblockexpression = null;
    {
      ProgressIndicatorProvider.checkCanceled();
      JvmField _switchResult = null;
      boolean _matched = false;
      if (field instanceof PsiEnumConstant) {
        _matched=true;
        _switchResult = this._typesFactory.createJvmEnumerationLiteral();
      }
      if (!_matched) {
        JvmField _createJvmField = this._typesFactory.createJvmField();
        final Procedure1<JvmField> _function = (JvmField it) -> {
          final Object value = this.getConstantValue(field);
          boolean _notEquals = (!Objects.equal(value, null));
          if (_notEquals) {
            it.setConstant(true);
            it.setConstantValue(value);
          } else {
            it.setConstant(false);
          }
        };
        _switchResult = ObjectExtensions.<JvmField>operator_doubleArrow(_createJvmField, _function);
      }
      final Procedure1<JvmField> _function_1 = (JvmField it) -> {
        it.internalSetIdentifier(fqn.append(field.getName()).toString());
        it.setSimpleName(field.getName());
        it.setFinal(field.hasModifierProperty(PsiModifier.FINAL));
        it.setStatic(field.hasModifierProperty(PsiModifier.STATIC));
        it.setTransient(field.hasModifierProperty(PsiModifier.TRANSIENT));
        it.setVolatile(field.hasModifierProperty(PsiModifier.VOLATILE));
        it.setDeprecated(field.isDeprecated());
        this.setVisibility(it, field);
        it.setType(this.createTypeReference(field.getType()));
        this.createAnnotationValues(it, field);
        final PsiElementProvider _function_2 = () -> {
          return field;
        };
        this.psiModelAssociator.associate(it, _function_2);
      };
      _xblockexpression = ObjectExtensions.<JvmField>operator_doubleArrow(_switchResult, _function_1);
    }
    return _xblockexpression;
  }
  
  /**
   * FIXME: Remove this method when com.intellij.psi.impl.compiled.ClsFieldImpl#computeConstantValue() is fixed.
   */
  private Object getConstantValue(final PsiField field) {
    final PsiExpression initializer = field.getInitializer();
    if ((initializer instanceof PsiCompiledElement)) {
      if ((initializer instanceof PsiBinaryExpression)) {
        final String fieldType = field.getType().getCanonicalText();
        IElementType _tokenType = ((PsiBinaryExpression)initializer).getOperationSign().getTokenType();
        boolean _equals = Objects.equal(_tokenType, JavaTokenType.DIV);
        if (_equals) {
          boolean _matched = false;
          String _simpleName = Double.TYPE.getSimpleName();
          if (Objects.equal(fieldType, _simpleName)) {
            _matched=true;
            String _text = initializer.getText();
            if (_text != null) {
              switch (_text) {
                case PsiBasedTypeFactory.DOUBLE_NAN:
                  return Double.valueOf(Double.NaN);
                case PsiBasedTypeFactory.DOUBLE_POSITIVE_INF:
                  return Double.valueOf(Double.POSITIVE_INFINITY);
                case PsiBasedTypeFactory.DOUBLE_NEGATIVE_INF:
                  return Double.valueOf(Double.NEGATIVE_INFINITY);
              }
            }
          }
          if (!_matched) {
            String _simpleName_1 = Float.TYPE.getSimpleName();
            if (Objects.equal(fieldType, _simpleName_1)) {
              _matched=true;
              String _text_1 = initializer.getText();
              if (_text_1 != null) {
                switch (_text_1) {
                  case PsiBasedTypeFactory.FLOAT_NAN:
                    return Float.valueOf(Float.NaN);
                  case PsiBasedTypeFactory.FLOAT_POSITIVE_INF:
                    return Float.valueOf(Float.POSITIVE_INFINITY);
                  case PsiBasedTypeFactory.FLOAT_NEGATIVE_INF:
                    return Float.valueOf(Float.NEGATIVE_INFINITY);
                }
              }
            }
          }
        }
      }
    }
    return field.computeConstantValue();
  }
  
  protected void createSuperTypes(final JvmDeclaredType it, final PsiClass psiClass) {
    PsiClassType[] _superTypes = psiClass.getSuperTypes();
    for (final PsiClassType superType : _superTypes) {
      this.<JvmTypeReference>addUnique(it.getSuperTypes(), this.createTypeReference(superType));
    }
  }
  
  protected StringBuilder createMethods(final JvmDeclaredType it, final PsiClass psiClass, final StringBuilder fqn) {
    StringBuilder _xblockexpression = null;
    {
      final boolean intf = (psiClass.isInterface() && (!psiClass.isAnnotationType()));
      PsiMethod[] _methods = psiClass.getMethods();
      for (final PsiMethod method : _methods) {
        final Procedure0 _function = () -> {
          try {
            JvmExecutable _xifexpression = null;
            boolean _isConstructor = method.isConstructor();
            if (_isConstructor) {
              _xifexpression = this.createConstructor(method, fqn);
            } else {
              JvmOperation _createOperation = this.createOperation(method, fqn);
              final Procedure1<JvmOperation> _function_1 = (JvmOperation it_1) -> {
                this.setDefaultValue(it_1, method);
                if (((intf && (!it_1.isAbstract())) && (!it_1.isStatic()))) {
                  it_1.setDefault(true);
                }
              };
              _xifexpression = ObjectExtensions.<JvmOperation>operator_doubleArrow(_createOperation, _function_1);
            }
            final JvmExecutable operation = _xifexpression;
            this.<JvmExecutable>addUnique(it.getMembers(), operation);
          } catch (final Throwable _t) {
            if (_t instanceof UnresolvedPsiClassType) {
              final UnresolvedPsiClassType e = (UnresolvedPsiClassType)_t;
            } else {
              throw Exceptions.sneakyThrow(_t);
            }
          }
        };
        this.preserve(fqn, _function);
      }
      boolean _hasDefaultConstructor = this.hasDefaultConstructor(psiClass);
      if (_hasDefaultConstructor) {
        final Procedure0 _function_1 = () -> {
          this.<JvmConstructor>addUnique(it.getMembers(), this.createDefaultConstructor(psiClass, fqn));
        };
        this.preserve(fqn, _function_1);
      }
      StringBuilder _xifexpression = null;
      boolean _isEnum = psiClass.isEnum();
      if (_isEnum) {
        StringBuilder _xblockexpression_1 = null;
        {
          final Procedure0 _function_2 = () -> {
            this.<JvmOperation>addUnique(it.getMembers(), this.createValuesOperation(psiClass, fqn));
          };
          this.preserve(fqn, _function_2);
          final Procedure0 _function_3 = () -> {
            this.<JvmOperation>addUnique(it.getMembers(), this.createValueOfOperation(psiClass, fqn));
          };
          _xblockexpression_1 = this.preserve(fqn, _function_3);
        }
        _xifexpression = _xblockexpression_1;
      }
      _xblockexpression = _xifexpression;
    }
    return _xblockexpression;
  }
  
  protected boolean hasDefaultConstructor(final PsiClass psiClass) {
    return (((!psiClass.isInterface()) && (!psiClass.isAnnotationType())) && (!IterableExtensions.<PsiMethod>exists(((Iterable<PsiMethod>)Conversions.doWrapArray(psiClass.getMethods())), ((Function1<PsiMethod, Boolean>) (PsiMethod it) -> {
      return Boolean.valueOf(it.isConstructor());
    }))));
  }
  
  protected void setDefaultValue(final JvmOperation operation, final PsiMethod method) {
    if ((method instanceof PsiAnnotationMethod)) {
      final Object defaultValue = this.computeAnnotationValue(((PsiAnnotationMethod)method).getDefaultValue(), ((PsiAnnotationMethod)method).getProject());
      boolean _notEquals = (!Objects.equal(defaultValue, null));
      if (_notEquals) {
        final JvmAnnotationValue annotationValue = this.createAnnotationValue(defaultValue, method);
        operation.setDefaultValue(annotationValue);
        annotationValue.setOperation(operation);
      }
    }
  }
  
  protected JvmAnnotationValue createAnnotationValue(final Object value, final PsiMethod method) {
    JvmAnnotationValue _xblockexpression = null;
    {
      final PsiType returnType = method.getReturnType();
      JvmAnnotationValue _xifexpression = null;
      boolean _isArray = this.isArray(returnType);
      if (_isArray) {
        _xifexpression = this.createArrayAnnotationValue(value, returnType);
      } else {
        _xifexpression = this.createAnnotationValue(value, returnType);
      }
      _xblockexpression = _xifexpression;
    }
    return _xblockexpression;
  }
  
  protected JvmAnnotationValue createAnnotationValue(final Object value, final PsiType type) {
    JvmAnnotationValue _createAnnotationValue = this.createAnnotationValue(type);
    final Procedure1<JvmAnnotationValue> _function = (JvmAnnotationValue it) -> {
      this.addValue(it, value);
    };
    return ObjectExtensions.<JvmAnnotationValue>operator_doubleArrow(_createAnnotationValue, _function);
  }
  
  protected JvmAnnotationValue createArrayAnnotationValue(final Object value, final PsiType type) {
    if ((type instanceof PsiArrayType)) {
      final PsiType componentType = ((PsiArrayType)type).getComponentType();
      JvmAnnotationValue _createAnnotationValue = this.createAnnotationValue(componentType);
      final Procedure1<JvmAnnotationValue> _function = (JvmAnnotationValue it) -> {
        if ((value instanceof Object[])) {
          for (final Object element : ((Object[])value)) {
            this.addValue(it, element);
          }
        } else {
          this.addValue(it, value);
        }
      };
      return ObjectExtensions.<JvmAnnotationValue>operator_doubleArrow(_createAnnotationValue, _function);
    }
    String _canonicalText = type.getCanonicalText();
    String _plus = ("type is not an array type: " + _canonicalText);
    throw new IllegalArgumentException(_plus);
  }
  
  protected void addValue(final JvmAnnotationValue it, final Object value) {
    boolean _matched = false;
    if (it instanceof JvmBooleanAnnotationValue) {
      _matched=true;
      this.<Boolean>addUnique(((JvmBooleanAnnotationValue)it).getValues(), ((Boolean) value));
    }
    if (!_matched) {
      if (it instanceof JvmIntAnnotationValue) {
        _matched=true;
        this.<Integer>addUnique(((JvmIntAnnotationValue)it).getValues(), this.asInteger(value));
      }
    }
    if (!_matched) {
      if (it instanceof JvmLongAnnotationValue) {
        _matched=true;
        this.<Long>addUnique(((JvmLongAnnotationValue)it).getValues(), this.asLong(value));
      }
    }
    if (!_matched) {
      if (it instanceof JvmShortAnnotationValue) {
        _matched=true;
        this.<Short>addUnique(((JvmShortAnnotationValue)it).getValues(), this.asShort(value));
      }
    }
    if (!_matched) {
      if (it instanceof JvmFloatAnnotationValue) {
        _matched=true;
        this.<Float>addUnique(((JvmFloatAnnotationValue)it).getValues(), this.asFloat(value));
      }
    }
    if (!_matched) {
      if (it instanceof JvmDoubleAnnotationValue) {
        _matched=true;
        this.<Double>addUnique(((JvmDoubleAnnotationValue)it).getValues(), this.asDouble(value));
      }
    }
    if (!_matched) {
      if (it instanceof JvmCharAnnotationValue) {
        _matched=true;
        this.<Character>addUnique(((JvmCharAnnotationValue)it).getValues(), this.asCharacter(value));
      }
    }
    if (!_matched) {
      if (it instanceof JvmByteAnnotationValue) {
        _matched=true;
        this.<Byte>addUnique(((JvmByteAnnotationValue)it).getValues(), this.asByte(value));
      }
    }
    if (!_matched) {
      if (it instanceof JvmStringAnnotationValue) {
        _matched=true;
        this.<String>addUnique(((JvmStringAnnotationValue)it).getValues(), ((String) value));
      }
    }
    if (!_matched) {
      if (it instanceof JvmTypeAnnotationValue) {
        _matched=true;
        this.<JvmTypeReference>addUnique(((JvmTypeAnnotationValue)it).getValues(), this.createTypeReference(((PsiType) value)));
      }
    }
    if (!_matched) {
      if (it instanceof JvmAnnotationAnnotationValue) {
        _matched=true;
        final JvmAnnotationReference annotationReference = this.createAnnotationReference(((PsiAnnotation) value));
        boolean _notEquals = (!Objects.equal(annotationReference, null));
        if (_notEquals) {
          this.<JvmAnnotationReference>addUnique(((JvmAnnotationAnnotationValue)it).getValues(), annotationReference);
        }
      }
    }
    if (!_matched) {
      if (it instanceof JvmEnumAnnotationValue) {
        _matched=true;
        this.<JvmEnumerationLiteral>addUnique(((JvmEnumAnnotationValue)it).getValues(), this.createEnumLiteralProxy(((PsiEnumConstant) value)));
      }
    }
  }
  
  protected Integer asInteger(final Object value) {
    Integer _switchResult = null;
    boolean _matched = false;
    if (value instanceof Integer) {
      _matched=true;
      _switchResult = ((Integer)value);
    }
    if (!_matched) {
      if (value instanceof Number) {
        _matched=true;
        _switchResult = Integer.valueOf(((Number)value).intValue());
      }
    }
    if (!_matched) {
      _switchResult = ((Integer) value);
    }
    return _switchResult;
  }
  
  protected Long asLong(final Object value) {
    Long _switchResult = null;
    boolean _matched = false;
    if (value instanceof Long) {
      _matched=true;
      _switchResult = ((Long)value);
    }
    if (!_matched) {
      if (value instanceof Number) {
        _matched=true;
        _switchResult = Long.valueOf(((Number)value).longValue());
      }
    }
    if (!_matched) {
      _switchResult = ((Long) value);
    }
    return _switchResult;
  }
  
  protected Short asShort(final Object value) {
    Short _switchResult = null;
    boolean _matched = false;
    if (value instanceof Short) {
      _matched=true;
      _switchResult = ((Short)value);
    }
    if (!_matched) {
      if (value instanceof Number) {
        _matched=true;
        _switchResult = Short.valueOf(((Number)value).shortValue());
      }
    }
    if (!_matched) {
      _switchResult = ((Short) value);
    }
    return _switchResult;
  }
  
  protected Float asFloat(final Object value) {
    Float _switchResult = null;
    boolean _matched = false;
    if (value instanceof Float) {
      _matched=true;
      _switchResult = ((Float)value);
    }
    if (!_matched) {
      if (value instanceof Number) {
        _matched=true;
        _switchResult = Float.valueOf(((Number)value).floatValue());
      }
    }
    if (!_matched) {
      _switchResult = ((Float) value);
    }
    return _switchResult;
  }
  
  protected Double asDouble(final Object value) {
    Double _switchResult = null;
    boolean _matched = false;
    if (value instanceof Double) {
      _matched=true;
      _switchResult = ((Double)value);
    }
    if (!_matched) {
      if (value instanceof Number) {
        _matched=true;
        _switchResult = Double.valueOf(((Number)value).doubleValue());
      }
    }
    if (!_matched) {
      _switchResult = ((Double) value);
    }
    return _switchResult;
  }
  
  protected Character asCharacter(final Object value) {
    Character _switchResult = null;
    boolean _matched = false;
    if (value instanceof Character) {
      _matched=true;
      _switchResult = ((Character)value);
    }
    if (!_matched) {
      if (value instanceof Number) {
        _matched=true;
        byte _byteValue = ((Number)value).byteValue();
        _switchResult = Character.valueOf(((char) _byteValue));
      }
    }
    if (!_matched) {
      _switchResult = ((Character) value);
    }
    return _switchResult;
  }
  
  protected Byte asByte(final Object value) {
    Byte _switchResult = null;
    boolean _matched = false;
    if (value instanceof Byte) {
      _matched=true;
      _switchResult = ((Byte)value);
    }
    if (!_matched) {
      if (value instanceof Number) {
        _matched=true;
        _switchResult = Byte.valueOf(((Number)value).byteValue());
      }
    }
    if (!_matched) {
      _switchResult = ((Byte) value);
    }
    return _switchResult;
  }
  
  protected JvmAnnotationValue createAnnotationValue(final PsiType type) {
    JvmAnnotationValue _switchResult = null;
    boolean _matched = false;
    if (Objects.equal(type, PsiType.BOOLEAN)) {
      _matched=true;
      _switchResult = this._typesFactory.createJvmBooleanAnnotationValue();
    }
    if (!_matched) {
      if (Objects.equal(type, PsiType.INT)) {
        _matched=true;
        _switchResult = this._typesFactory.createJvmIntAnnotationValue();
      }
    }
    if (!_matched) {
      if (Objects.equal(type, PsiType.LONG)) {
        _matched=true;
        _switchResult = this._typesFactory.createJvmLongAnnotationValue();
      }
    }
    if (!_matched) {
      if (Objects.equal(type, PsiType.SHORT)) {
        _matched=true;
        _switchResult = this._typesFactory.createJvmShortAnnotationValue();
      }
    }
    if (!_matched) {
      if (Objects.equal(type, PsiType.FLOAT)) {
        _matched=true;
        _switchResult = this._typesFactory.createJvmFloatAnnotationValue();
      }
    }
    if (!_matched) {
      if (Objects.equal(type, PsiType.DOUBLE)) {
        _matched=true;
        _switchResult = this._typesFactory.createJvmDoubleAnnotationValue();
      }
    }
    if (!_matched) {
      if (Objects.equal(type, PsiType.CHAR)) {
        _matched=true;
        _switchResult = this._typesFactory.createJvmCharAnnotationValue();
      }
    }
    if (!_matched) {
      if (Objects.equal(type, PsiType.BYTE)) {
        _matched=true;
        _switchResult = this._typesFactory.createJvmByteAnnotationValue();
      }
    }
    if (!_matched) {
      boolean _isClassType = this.isClassType(type, String.class);
      if (_isClassType) {
        _matched=true;
        _switchResult = this._typesFactory.createJvmStringAnnotationValue();
      }
    }
    if (!_matched) {
      boolean _isClassType_1 = this.isClassType(type, Class.class);
      if (_isClassType_1) {
        _matched=true;
        _switchResult = this._typesFactory.createJvmTypeAnnotationValue();
      }
    }
    if (!_matched) {
      boolean _isAnnotation = this.isAnnotation(type);
      if (_isAnnotation) {
        _matched=true;
        _switchResult = this._typesFactory.createJvmAnnotationAnnotationValue();
      }
    }
    if (!_matched) {
      boolean _isEnum = this.isEnum(type);
      if (_isEnum) {
        _matched=true;
        _switchResult = this._typesFactory.createJvmEnumAnnotationValue();
      }
    }
    if (!_matched) {
      String _canonicalText = type.getCanonicalText();
      String _plus = ("Unexpected type: " + _canonicalText);
      throw new IllegalArgumentException(_plus);
    }
    return _switchResult;
  }
  
  protected Object computeAnnotationValue(final PsiAnnotationMemberValue value, final Project project) {
    final PsiConstantEvaluationHelper constantEvaluationHelper = JavaPsiFacade.getInstance(project).getConstantEvaluationHelper();
    return this.computeAnnotationValue(value, constantEvaluationHelper);
  }
  
  protected Object computeAnnotationValue(final PsiAnnotationMemberValue value, @Extension final PsiConstantEvaluationHelper helper) {
    Object _switchResult = null;
    boolean _matched = false;
    if (value instanceof PsiAnnotation) {
      _matched=true;
      _switchResult = value;
    }
    if (!_matched) {
      if (value instanceof PsiReferenceExpression) {
        _matched=true;
        Object _switchResult_1 = null;
        PsiElement _resolve = ((PsiReferenceExpression)value).resolve();
        final PsiElement r = _resolve;
        boolean _matched_1 = false;
        if (r instanceof PsiEnumConstant) {
          _matched_1=true;
          _switchResult_1 = r;
        }
        if (!_matched_1) {
          _switchResult_1 = helper.computeConstantExpression(value);
        }
        _switchResult = _switchResult_1;
      }
    }
    if (!_matched) {
      if (value instanceof PsiClassObjectAccessExpression) {
        _matched=true;
        _switchResult = ((PsiClassObjectAccessExpression)value).getOperand().getType();
      }
    }
    if (!_matched) {
      if (value instanceof PsiArrayInitializerMemberValue) {
        _matched=true;
        final Function1<PsiAnnotationMemberValue, Object> _function = (PsiAnnotationMemberValue it) -> {
          return this.computeAnnotationValue(it, helper);
        };
        _switchResult = ListExtensions.<PsiAnnotationMemberValue, Object>map(((List<PsiAnnotationMemberValue>)Conversions.doWrapArray(((PsiArrayInitializerMemberValue)value).getInitializers())), _function).toArray();
      }
    }
    if (!_matched) {
      _switchResult = helper.computeConstantExpression(value);
    }
    return _switchResult;
  }
  
  protected String getPackageName(final PsiClass psiClass) {
    final PsiFile javaFile = psiClass.getContainingFile();
    if ((javaFile instanceof PsiJavaFile)) {
      final String psiPackageName = ((PsiJavaFile)javaFile).getPackageName();
      boolean _isEmpty = psiPackageName.isEmpty();
      boolean _not = (!_isEmpty);
      if (_not) {
        return psiPackageName;
      }
    }
    return null;
  }
  
  protected JvmOperation createValuesOperation(final PsiClass enumType, final StringBuilder fqn) {
    JvmOperation _createJvmOperation = this._typesFactory.createJvmOperation();
    final Procedure1<JvmOperation> _function = (JvmOperation it) -> {
      it.internalSetIdentifier(fqn.append("values()").toString());
      it.setSimpleName("values");
      it.setVisibility(JvmVisibility.PUBLIC);
      it.setStatic(true);
      JvmGenericArrayTypeReference _createJvmGenericArrayTypeReference = this._typesFactory.createJvmGenericArrayTypeReference();
      final Procedure1<JvmGenericArrayTypeReference> _function_1 = (JvmGenericArrayTypeReference it_1) -> {
        it_1.setComponentType(this.createTypeReference(this.getPsiElementFactory(enumType.getProject()).createType(enumType)));
      };
      JvmGenericArrayTypeReference _doubleArrow = ObjectExtensions.<JvmGenericArrayTypeReference>operator_doubleArrow(_createJvmGenericArrayTypeReference, _function_1);
      it.setReturnType(_doubleArrow);
      it.setDeprecated(false);
      final PsiElementProvider _function_2 = () -> {
        return enumType;
      };
      this.psiModelAssociator.associate(it, _function_2);
    };
    return ObjectExtensions.<JvmOperation>operator_doubleArrow(_createJvmOperation, _function);
  }
  
  protected JvmOperation createValueOfOperation(final PsiClass enumType, final StringBuilder fqn) {
    JvmOperation _xblockexpression = null;
    {
      final PsiElementFactory psiElementFactory = this.getPsiElementFactory(enumType.getProject());
      JvmOperation _createJvmOperation = this._typesFactory.createJvmOperation();
      final Procedure1<JvmOperation> _function = (JvmOperation it) -> {
        it.internalSetIdentifier(fqn.append("valueOf(java.lang.String)").toString());
        it.setSimpleName("valueOf");
        it.setVisibility(JvmVisibility.PUBLIC);
        it.setStatic(true);
        it.setReturnType(this.createTypeReference(psiElementFactory.createType(enumType)));
        EList<JvmFormalParameter> _parameters = it.getParameters();
        JvmFormalParameter _createJvmFormalParameter = this._typesFactory.createJvmFormalParameter();
        final Procedure1<JvmFormalParameter> _function_1 = (JvmFormalParameter it_1) -> {
          it_1.setName("name");
          it_1.setParameterType(this.createStringReference());
        };
        JvmFormalParameter _doubleArrow = ObjectExtensions.<JvmFormalParameter>operator_doubleArrow(_createJvmFormalParameter, _function_1);
        this.<JvmFormalParameter>addUnique(_parameters, _doubleArrow);
        it.setDeprecated(false);
        final PsiElementProvider _function_2 = () -> {
          return enumType;
        };
        this.psiModelAssociator.associate(it, _function_2);
      };
      _xblockexpression = ObjectExtensions.<JvmOperation>operator_doubleArrow(_createJvmOperation, _function);
    }
    return _xblockexpression;
  }
  
  protected JvmConstructor createDefaultConstructor(final PsiClass psiClass, final StringBuilder fqn) {
    JvmConstructor _createJvmConstructor = this._typesFactory.createJvmConstructor();
    final Procedure1<JvmConstructor> _function = (JvmConstructor it) -> {
      it.internalSetIdentifier(fqn.append(psiClass.getName()).append("()").toString());
      it.setSimpleName(psiClass.getName());
      it.setVisibility(JvmVisibility.PUBLIC);
      it.setDeprecated(false);
      final PsiElementProvider _function_1 = () -> {
        return psiClass;
      };
      this.psiModelAssociator.associate(it, _function_1);
    };
    return ObjectExtensions.<JvmConstructor>operator_doubleArrow(_createJvmConstructor, _function);
  }
  
  protected JvmConstructor createConstructor(final PsiMethod psiMethod, final StringBuilder fqn) {
    JvmConstructor _createJvmConstructor = this._typesFactory.createJvmConstructor();
    final Procedure1<JvmConstructor> _function = (JvmConstructor it) -> {
      this.enhanceExecutable(it, psiMethod, fqn);
      this.createAnnotationValues(it, psiMethod);
      final PsiElementProvider _function_1 = () -> {
        return psiMethod;
      };
      this.psiModelAssociator.associate(it, _function_1);
    };
    return ObjectExtensions.<JvmConstructor>operator_doubleArrow(_createJvmConstructor, _function);
  }
  
  protected JvmOperation createOperation(final PsiMethod method, final StringBuilder fqn) {
    JvmOperation _xblockexpression = null;
    {
      ProgressIndicatorProvider.checkCanceled();
      JvmOperation _createJvmOperation = this._typesFactory.createJvmOperation();
      final Procedure1<JvmOperation> _function = (JvmOperation it) -> {
        this.enhanceExecutable(it, method, fqn);
        it.setAbstract(method.hasModifierProperty(PsiModifier.ABSTRACT));
        it.setFinal(method.hasModifierProperty(PsiModifier.FINAL));
        it.setStatic(method.hasModifierProperty(PsiModifier.STATIC));
        it.setStrictFloatingPoint(method.hasModifierProperty(PsiModifier.STRICTFP));
        it.setSynchronized(method.hasModifierProperty(PsiModifier.SYNCHRONIZED));
        it.setNative(method.hasModifierProperty(PsiModifier.NATIVE));
        it.setReturnType(this.createTypeReference(method.getReturnType()));
        this.createAnnotationValues(it, method);
        final PsiElementProvider _function_1 = () -> {
          return method;
        };
        this.psiModelAssociator.associate(it, _function_1);
      };
      _xblockexpression = ObjectExtensions.<JvmOperation>operator_doubleArrow(_createJvmOperation, _function);
    }
    return _xblockexpression;
  }
  
  protected void enhanceExecutable(final JvmExecutable it, final PsiMethod psiMethod, final StringBuilder fqn) {
    this.createTypeParameters(it, psiMethod);
    fqn.append(psiMethod.getName()).append("(");
    this.createFormalParameters(it, psiMethod, fqn);
    final String identifier = fqn.append(")").toString();
    it.internalSetIdentifier(identifier);
    it.setSimpleName(psiMethod.getName());
    this.setVisibility(it, psiMethod);
    it.setDeprecated(psiMethod.isDeprecated());
    it.setVarArgs(psiMethod.isVarArgs());
    PsiClassType[] _referencedTypes = psiMethod.getThrowsList().getReferencedTypes();
    for (final PsiClassType exceptionType : _referencedTypes) {
      EList<JvmTypeReference> _exceptions = it.getExceptions();
      JvmTypeReference _createTypeReference = this.createTypeReference(exceptionType);
      _exceptions.add(_createTypeReference);
    }
  }
  
  protected void createFormalParameters(final JvmExecutable it, final PsiMethod psiMethod, final StringBuilder fqn) {
    final PsiParameterList parameterList = psiMethod.getParameterList();
    final int max = parameterList.getParametersCount();
    final PsiParameter[] parameters = parameterList.getParameters();
    for (int i = 0; (i < max); i++) {
      {
        final PsiParameter parameter = parameters[i];
        if ((i != 0)) {
          fqn.append(",");
        }
        this.uriHelper.appendTypeName(fqn, parameter.getType());
        this.<JvmFormalParameter>addUnique(it.getParameters(), this.createFormalParameter(parameter));
      }
    }
  }
  
  protected void createTypeParameters(final JvmTypeParameterDeclarator it, final PsiTypeParameterListOwner psiTypeParameterListOwner) {
    PsiTypeParameter[] _typeParameters = psiTypeParameterListOwner.getTypeParameters();
    for (final PsiTypeParameter typeParameter : _typeParameters) {
      this.<JvmTypeParameter>addUnique(it.getTypeParameters(), this.createTypeParameter(typeParameter));
    }
  }
  
  protected JvmFormalParameter createFormalParameter(final PsiParameter parameter) {
    JvmFormalParameter _createJvmFormalParameter = this._typesFactory.createJvmFormalParameter();
    final Procedure1<JvmFormalParameter> _function = (JvmFormalParameter it) -> {
      it.setName(parameter.getName());
      it.setParameterType(this.createTypeReference(parameter.getType()));
      this.createAnnotationValues(it, parameter);
    };
    return ObjectExtensions.<JvmFormalParameter>operator_doubleArrow(_createJvmFormalParameter, _function);
  }
  
  protected JvmTypeParameter createTypeParameter(final PsiTypeParameter parameter) {
    JvmTypeParameter _createJvmTypeParameter = this._typesFactory.createJvmTypeParameter();
    final Procedure1<JvmTypeParameter> _function = (JvmTypeParameter it) -> {
      it.setName(parameter.getName());
      final PsiClassType[] extendsListTypes = parameter.getExtendsListTypes();
      int _length = extendsListTypes.length;
      boolean _notEquals = (_length != 0);
      if (_notEquals) {
        for (final PsiClassType upperBound : extendsListTypes) {
          {
            JvmUpperBound _createJvmUpperBound = this._typesFactory.createJvmUpperBound();
            final JvmTypeConstraintImplCustom jvmUpperBound = ((JvmTypeConstraintImplCustom) _createJvmUpperBound);
            jvmUpperBound.internalSetTypeReference(this.createTypeReference(upperBound));
            this.<JvmTypeConstraintImplCustom>addUnique(it.getConstraints(), jvmUpperBound);
          }
        }
      } else {
        JvmUpperBound _createJvmUpperBound = this._typesFactory.createJvmUpperBound();
        final JvmTypeConstraintImplCustom jvmUpperBound = ((JvmTypeConstraintImplCustom) _createJvmUpperBound);
        jvmUpperBound.internalSetTypeReference(this.createObjectReference());
        this.<JvmTypeConstraintImplCustom>addUnique(it.getConstraints(), jvmUpperBound);
      }
    };
    return ObjectExtensions.<JvmTypeParameter>operator_doubleArrow(_createJvmTypeParameter, _function);
  }
  
  protected JvmParameterizedTypeReference createObjectReference() {
    final JvmParameterizedTypeReference result = this._typesFactory.createJvmParameterizedTypeReference();
    result.setType(AbstractDeclaredTypeFactory.OBJECT_CLASS_PROXY);
    return result;
  }
  
  protected JvmParameterizedTypeReference createStringReference() {
    final JvmParameterizedTypeReference result = this._typesFactory.createJvmParameterizedTypeReference();
    result.setType(AbstractDeclaredTypeFactory.COMMON_PROXIES.get(AbstractDeclaredTypeFactory.STRING_CLASS_NAME));
    return result;
  }
  
  protected JvmTypeReference createTypeReference(final PsiType psiType) {
    JvmUnknownTypeReference _xtrycatchfinallyexpression = null;
    try {
      boolean _matched = false;
      if (psiType instanceof PsiArrayType) {
        _matched=true;
        return this.createArrayTypeReference(((PsiArrayType)psiType).getComponentType());
      }
      if (!_matched) {
        if (psiType instanceof PsiClassType) {
          _matched=true;
          final PsiClassType.ClassResolveResult resolveResult = ((PsiClassType)psiType).resolveGenerics();
          boolean _isValidResult = resolveResult.isValidResult();
          boolean _not = (!_isValidResult);
          if (_not) {
            JvmUnknownTypeReference _createJvmUnknownTypeReference = this._typesFactory.createJvmUnknownTypeReference();
            final Procedure1<JvmUnknownTypeReference> _function = (JvmUnknownTypeReference it) -> {
              it.setQualifiedName(((PsiClassType)psiType).getClassName());
            };
            return ObjectExtensions.<JvmUnknownTypeReference>operator_doubleArrow(_createJvmUnknownTypeReference, _function);
          } else {
            JvmParameterizedTypeReference _createClassTypeReference = this.createClassTypeReference(resolveResult);
            final Procedure1<JvmParameterizedTypeReference> _function_1 = (JvmParameterizedTypeReference it) -> {
              try {
                it.setType(this.createProxy(((PsiClassType)psiType).rawType()));
                PsiType[] _parameters = ((PsiClassType)psiType).getParameters();
                for (final PsiType parameter : _parameters) {
                  this.<JvmTypeReference>addUnique(it.getArguments(), this.createTypeArgument(parameter));
                }
              } catch (Throwable _e) {
                throw Exceptions.sneakyThrow(_e);
              }
            };
            return ObjectExtensions.<JvmParameterizedTypeReference>operator_doubleArrow(_createClassTypeReference, _function_1);
          }
        }
      }
      JvmParameterizedTypeReference _createJvmParameterizedTypeReference = this._typesFactory.createJvmParameterizedTypeReference();
      final Procedure1<JvmParameterizedTypeReference> _function = (JvmParameterizedTypeReference it) -> {
        try {
          it.setType(this.createProxy(psiType));
        } catch (Throwable _e) {
          throw Exceptions.sneakyThrow(_e);
        }
      };
      return ObjectExtensions.<JvmParameterizedTypeReference>operator_doubleArrow(_createJvmParameterizedTypeReference, _function);
    } catch (final Throwable _t) {
      if (_t instanceof UnresolvedPsiClassType) {
        final UnresolvedPsiClassType e = (UnresolvedPsiClassType)_t;
        _xtrycatchfinallyexpression = this._typesFactory.createJvmUnknownTypeReference();
      } else {
        throw Exceptions.sneakyThrow(_t);
      }
    }
    return _xtrycatchfinallyexpression;
  }
  
  protected JvmParameterizedTypeReference createClassTypeReference(final PsiClassType.ClassResolveResult resolveResult) {
    JvmParameterizedTypeReference _xblockexpression = null;
    {
      final PsiClass psiClass = resolveResult.getElement();
      JvmParameterizedTypeReference _xifexpression = null;
      boolean _isInnerTypeReference = this.isInnerTypeReference(psiClass);
      if (_isInnerTypeReference) {
        JvmInnerTypeReference _createJvmInnerTypeReference = this._typesFactory.createJvmInnerTypeReference();
        final Procedure1<JvmInnerTypeReference> _function = (JvmInnerTypeReference it) -> {
          final PsiClass containingClass = psiClass.getContainingClass();
          final PsiElementFactory psiElementFactory = this.getPsiElementFactory(psiClass.getProject());
          final PsiClassType outerType = psiElementFactory.createType(containingClass, resolveResult.getSubstitutor());
          JvmTypeReference _createTypeReference = this.createTypeReference(outerType);
          it.setOuter(((JvmParameterizedTypeReference) _createTypeReference));
        };
        _xifexpression = ObjectExtensions.<JvmInnerTypeReference>operator_doubleArrow(_createJvmInnerTypeReference, _function);
      } else {
        _xifexpression = this._typesFactory.createJvmParameterizedTypeReference();
      }
      _xblockexpression = _xifexpression;
    }
    return _xblockexpression;
  }
  
  protected boolean isInnerTypeReference(final PsiClass psiClass) {
    return ((!Objects.equal(psiClass.getContainingClass(), null)) && (!psiClass.hasModifierProperty(PsiModifier.STATIC)));
  }
  
  protected JvmTypeReference _createTypeArgument(final PsiType type) {
    return this.createTypeReference(type);
  }
  
  protected JvmTypeReference _createTypeArgument(final PsiWildcardType type) {
    JvmWildcardTypeReference _createJvmWildcardTypeReference = this._typesFactory.createJvmWildcardTypeReference();
    final Procedure1<JvmWildcardTypeReference> _function = (JvmWildcardTypeReference it) -> {
      JvmUpperBound _createJvmUpperBound = this._typesFactory.createJvmUpperBound();
      final JvmTypeConstraintImplCustom upperBound = ((JvmTypeConstraintImplCustom) _createJvmUpperBound);
      upperBound.internalSetTypeReference(this.createUpperBoundReference(type));
      this.<JvmTypeConstraintImplCustom>addUnique(it.getConstraints(), upperBound);
      final PsiType superBound = type.getSuperBound();
      boolean _notEquals = (!Objects.equal(superBound, PsiType.NULL));
      if (_notEquals) {
        JvmLowerBound _createJvmLowerBound = this._typesFactory.createJvmLowerBound();
        final JvmTypeConstraintImplCustom lowerBound = ((JvmTypeConstraintImplCustom) _createJvmLowerBound);
        lowerBound.internalSetTypeReference(this.createTypeReference(superBound));
        this.<JvmTypeConstraintImplCustom>addUnique(it.getConstraints(), lowerBound);
      }
    };
    return ObjectExtensions.<JvmWildcardTypeReference>operator_doubleArrow(_createJvmWildcardTypeReference, _function);
  }
  
  protected JvmTypeReference _createTypeArgument(final PsiCapturedWildcardType type) {
    return this._createTypeArgument(type.getWildcard());
  }
  
  protected JvmTypeReference createUpperBoundReference(final PsiWildcardType type) {
    JvmTypeReference _xblockexpression = null;
    {
      final PsiType extendsBound = type.getExtendsBound();
      JvmTypeReference _xifexpression = null;
      boolean _notEquals = (!Objects.equal(extendsBound, PsiType.NULL));
      if (_notEquals) {
        _xifexpression = this.createTypeReference(extendsBound);
      } else {
        _xifexpression = this.createObjectReference();
      }
      _xblockexpression = _xifexpression;
    }
    return _xblockexpression;
  }
  
  protected JvmGenericArrayTypeReference createArrayTypeReference(final PsiType psiComponentType) {
    JvmGenericArrayTypeReference _createJvmGenericArrayTypeReference = this._typesFactory.createJvmGenericArrayTypeReference();
    final Procedure1<JvmGenericArrayTypeReference> _function = (JvmGenericArrayTypeReference it) -> {
      it.setComponentType(this.createTypeReference(psiComponentType));
    };
    return ObjectExtensions.<JvmGenericArrayTypeReference>operator_doubleArrow(_createJvmGenericArrayTypeReference, _function);
  }
  
  protected JvmType createProxy(final PsiType psiType) throws UnresolvedPsiClassType {
    final JvmVoid result = this._typesFactory.createJvmVoid();
    final Procedure1<InternalEObject> _function = (InternalEObject it) -> {
      try {
        final URI uri = this.uriHelper.getFullURI(psiType);
        it.eSetProxyURI(uri);
      } catch (Throwable _e) {
        throw Exceptions.sneakyThrow(_e);
      }
    };
    ObjectExtensions.<InternalEObject>operator_doubleArrow(((InternalEObject) result), _function);
    return result;
  }
  
  protected void createNestedTypes(final JvmDeclaredType it, final PsiClass psiClass, final StringBuilder fqn) {
    PsiClass[] _innerClasses = psiClass.getInnerClasses();
    for (final PsiClass innerClass : _innerClasses) {
      if (((!this.isAnonymous(innerClass)) && (!this.isSynthetic(innerClass)))) {
        final Procedure0 _function = () -> {
          this.<JvmDeclaredType>addUnique(it.getMembers(), this.createType(innerClass, fqn));
        };
        this.preserve(fqn, _function);
      }
    }
  }
  
  protected boolean isAnonymous(final PsiClass psiClass) {
    return (psiClass instanceof PsiAnonymousClass);
  }
  
  protected boolean isSynthetic(final PsiClass psiClass) {
    return false;
  }
  
  protected void setVisibility(final JvmMember it, final PsiModifierListOwner modifierListOwner) {
    JvmVisibility _switchResult = null;
    boolean _matched = false;
    boolean _hasModifierProperty = modifierListOwner.hasModifierProperty(PsiModifier.PRIVATE);
    if (_hasModifierProperty) {
      _matched=true;
      _switchResult = JvmVisibility.PRIVATE;
    }
    if (!_matched) {
      boolean _hasModifierProperty_1 = modifierListOwner.hasModifierProperty(PsiModifier.PACKAGE_LOCAL);
      if (_hasModifierProperty_1) {
        _matched=true;
        _switchResult = JvmVisibility.DEFAULT;
      }
    }
    if (!_matched) {
      boolean _hasModifierProperty_2 = modifierListOwner.hasModifierProperty(PsiModifier.PROTECTED);
      if (_hasModifierProperty_2) {
        _matched=true;
        _switchResult = JvmVisibility.PROTECTED;
      }
    }
    if (!_matched) {
      boolean _hasModifierProperty_3 = modifierListOwner.hasModifierProperty(PsiModifier.PUBLIC);
      if (_hasModifierProperty_3) {
        _matched=true;
        _switchResult = JvmVisibility.PUBLIC;
      }
    }
    it.setVisibility(_switchResult);
  }
  
  protected void setTypeModifiers(final JvmDeclaredType it, final PsiClass psiClass) {
    final PsiModifierList modifierList = psiClass.getModifierList();
    it.setAbstract(modifierList.hasModifierProperty(PsiModifier.ABSTRACT));
    it.setStatic(modifierList.hasModifierProperty(PsiModifier.STATIC));
    boolean _isEnum = psiClass.isEnum();
    boolean _not = (!_isEnum);
    if (_not) {
      it.setFinal(modifierList.hasModifierProperty(PsiModifier.FINAL));
    }
  }
  
  protected <T extends Object> void addUnique(final EList<? super T> list, final T object) {
    if ((object != null)) {
      ((InternalEList<T>) list).addUnique(object);
    }
  }
  
  protected StringBuilder append(final StringBuilder builder, final String value, final Procedure0 procedure) {
    StringBuilder _xblockexpression = null;
    {
      final int length = builder.length();
      builder.append(value);
      procedure.apply();
      builder.setLength(length);
      _xblockexpression = builder;
    }
    return _xblockexpression;
  }
  
  protected StringBuilder preserve(final StringBuilder builder, final Procedure0 procedure) {
    StringBuilder _xblockexpression = null;
    {
      final int length = builder.length();
      procedure.apply();
      builder.setLength(length);
      _xblockexpression = builder;
    }
    return _xblockexpression;
  }
  
  protected boolean isClassType(final PsiType type, final Class<?> clazz) {
    boolean _xifexpression = false;
    if ((type instanceof PsiClassType)) {
      String _qualifiedClassName = PsiNameHelper.getQualifiedClassName(((PsiClassType)type).getCanonicalText(), true);
      String _name = clazz.getName();
      return Objects.equal(_qualifiedClassName, _name);
    } else {
      _xifexpression = false;
    }
    return _xifexpression;
  }
  
  protected boolean isAnnotation(final PsiType type) {
    boolean _xifexpression = false;
    if ((type instanceof PsiClassType)) {
      _xifexpression = ((PsiClassType)type).resolve().isAnnotationType();
    } else {
      _xifexpression = false;
    }
    return _xifexpression;
  }
  
  protected boolean isEnum(final PsiType type) {
    boolean _xifexpression = false;
    if ((type instanceof PsiClassType)) {
      _xifexpression = ((PsiClassType)type).resolve().isEnum();
    } else {
      _xifexpression = false;
    }
    return _xifexpression;
  }
  
  protected boolean isArray(final PsiType type) {
    return (type instanceof PsiArrayType);
  }
  
  private PsiElementFactory getPsiElementFactory(final Project project) {
    return PsiElementFactory.SERVICE.getInstance(project);
  }
  
  protected JvmTypeReference createTypeArgument(final PsiType type) {
    if (type instanceof PsiCapturedWildcardType) {
      return _createTypeArgument((PsiCapturedWildcardType)type);
    } else if (type instanceof PsiWildcardType) {
      return _createTypeArgument((PsiWildcardType)type);
    } else if (type != null) {
      return _createTypeArgument(type);
    } else {
      throw new IllegalArgumentException("Unhandled parameter types: " +
        Arrays.<Object>asList(type).toString());
    }
  }
}
