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
import com.intellij.openapi.application.Application;
import com.intellij.openapi.application.ApplicationManager;
import com.intellij.openapi.progress.ProgressIndicatorProvider;
import com.intellij.openapi.project.Project;
import com.intellij.openapi.util.Computable;
import com.intellij.psi.JavaPsiFacade;
import com.intellij.psi.JavaTokenType;
import com.intellij.psi.PsiAnnotation;
import com.intellij.psi.PsiAnnotationMemberValue;
import com.intellij.psi.PsiAnnotationMethod;
import com.intellij.psi.PsiAnnotationParameterList;
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
import com.intellij.psi.PsiJavaCodeReferenceElement;
import com.intellij.psi.PsiJavaFile;
import com.intellij.psi.PsiJavaToken;
import com.intellij.psi.PsiMethod;
import com.intellij.psi.PsiModifier;
import com.intellij.psi.PsiModifierList;
import com.intellij.psi.PsiModifierListOwner;
import com.intellij.psi.PsiNameHelper;
import com.intellij.psi.PsiNameValuePair;
import com.intellij.psi.PsiParameter;
import com.intellij.psi.PsiParameterList;
import com.intellij.psi.PsiReferenceExpression;
import com.intellij.psi.PsiReferenceList;
import com.intellij.psi.PsiSubstitutor;
import com.intellij.psi.PsiType;
import com.intellij.psi.PsiTypeElement;
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
import org.eclipse.xtext.common.types.JvmTypeConstraint;
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
        Application _application = ApplicationManager.getApplication();
        final Computable<JvmDeclaredType> _function = () -> {
          JvmDeclaredType _xblockexpression_1 = null;
          {
            final StringBuilder buffer = new StringBuilder(100);
            final String packageName = this.getPackageName(psiClass);
            if ((packageName != null)) {
              StringBuilder _append = buffer.append(packageName);
              _append.append(".");
            }
            final JvmDeclaredType type = this.createType(psiClass, buffer);
            type.setPackageName(packageName);
            _xblockexpression_1 = type;
          }
          return _xblockexpression_1;
        };
        _xblockexpression = _application.<JvmDeclaredType>runReadAction(_function);
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
          boolean _isInterface = psiClass.isInterface();
          genericType.setInterface(_isInterface);
          boolean _hasModifierProperty = psiClass.hasModifierProperty(PsiModifier.STRICTFP);
          genericType.setStrictFloatingPoint(_hasModifierProperty);
          this.createTypeParameters(genericType, psiClass);
          _xblockexpression_1 = genericType;
        }
        _switchResult = _xblockexpression_1;
      }
      final Procedure1<JvmDeclaredType> _function = (JvmDeclaredType it) -> {
        this.setTypeModifiers(it, psiClass);
        this.setVisibility(it, psiClass);
        boolean _isDeprecated = psiClass.isDeprecated();
        it.setDeprecated(_isDeprecated);
        String _name = psiClass.getName();
        it.setSimpleName(_name);
        String _name_1 = psiClass.getName();
        fqn.append(_name_1);
        String _string = fqn.toString();
        it.internalSetIdentifier(_string);
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
        if ((annotationReference != null)) {
          EList<JvmAnnotationReference> _annotations_1 = it.getAnnotations();
          this.<JvmAnnotationReference>addUnique(_annotations_1, annotationReference);
        }
      }
    }
  }
  
  protected JvmAnnotationReference createAnnotationReference(final PsiAnnotation annotation) {
    JvmAnnotationReference _xblockexpression = null;
    {
      PsiJavaCodeReferenceElement _nameReferenceElement = annotation.getNameReferenceElement();
      final PsiElement psiClass = _nameReferenceElement.resolve();
      JvmAnnotationReference _xifexpression = null;
      if ((psiClass instanceof PsiClass)) {
        JvmAnnotationReference _createJvmAnnotationReference = this._typesFactory.createJvmAnnotationReference();
        final Procedure1<JvmAnnotationReference> _function = (JvmAnnotationReference it) -> {
          JvmAnnotationType _createAnnotationProxy = this.createAnnotationProxy(((PsiClass)psiClass));
          it.setAnnotation(_createAnnotationProxy);
          PsiAnnotationParameterList _parameterList = annotation.getParameterList();
          PsiNameValuePair[] _attributes = _parameterList.getAttributes();
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
              PsiAnnotationMemberValue _value = attribute.getValue();
              Project _project = annotation.getProject();
              final Object value = this.computeAnnotationValue(_value, _project);
              PsiMethod[] _methods = ((PsiClass)psiClass).getMethods();
              final Function1<PsiMethod, Boolean> _function_1 = (PsiMethod it_1) -> {
                String _name_1 = it_1.getName();
                return Boolean.valueOf(Objects.equal(_name_1, attributeName));
              };
              final PsiMethod method = IterableExtensions.<PsiMethod>findFirst(((Iterable<PsiMethod>)Conversions.doWrapArray(_methods)), _function_1);
              final JvmAnnotationValue annotationValue = this.createAnnotationValue(value, method);
              JvmOperation _createMethodProxy = this.createMethodProxy(method);
              annotationValue.setOperation(_createMethodProxy);
              EList<JvmAnnotationValue> _explicitValues = it.getExplicitValues();
              this.<JvmAnnotationValue>addUnique(_explicitValues, annotationValue);
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
        EList<JvmMember> _members = it.getMembers();
        JvmField _createField = this.createField(field, fqn);
        this.<JvmField>addUnique(_members, _createField);
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
          if ((value != null)) {
            it.setConstant(true);
            it.setConstantValue(value);
          } else {
            it.setConstant(false);
          }
        };
        _switchResult = ObjectExtensions.<JvmField>operator_doubleArrow(_createJvmField, _function);
      }
      final Procedure1<JvmField> _function_1 = (JvmField it) -> {
        String _name = field.getName();
        StringBuilder _append = fqn.append(_name);
        String _string = _append.toString();
        it.internalSetIdentifier(_string);
        String _name_1 = field.getName();
        it.setSimpleName(_name_1);
        boolean _hasModifierProperty = field.hasModifierProperty(PsiModifier.FINAL);
        it.setFinal(_hasModifierProperty);
        boolean _hasModifierProperty_1 = field.hasModifierProperty(PsiModifier.STATIC);
        it.setStatic(_hasModifierProperty_1);
        boolean _hasModifierProperty_2 = field.hasModifierProperty(PsiModifier.TRANSIENT);
        it.setTransient(_hasModifierProperty_2);
        boolean _hasModifierProperty_3 = field.hasModifierProperty(PsiModifier.VOLATILE);
        it.setVolatile(_hasModifierProperty_3);
        boolean _isDeprecated = field.isDeprecated();
        it.setDeprecated(_isDeprecated);
        this.setVisibility(it, field);
        PsiType _type = field.getType();
        JvmTypeReference _createTypeReference = this.createTypeReference(_type);
        it.setType(_createTypeReference);
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
        PsiType _type = field.getType();
        final String fieldType = _type.getCanonicalText();
        PsiJavaToken _operationSign = ((PsiBinaryExpression)initializer).getOperationSign();
        IElementType _tokenType = _operationSign.getTokenType();
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
      EList<JvmTypeReference> _superTypes_1 = it.getSuperTypes();
      JvmTypeReference _createTypeReference = this.createTypeReference(superType);
      this.<JvmTypeReference>addUnique(_superTypes_1, _createTypeReference);
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
            EList<JvmMember> _members = it.getMembers();
            this.<JvmExecutable>addUnique(_members, operation);
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
          EList<JvmMember> _members = it.getMembers();
          JvmConstructor _createDefaultConstructor = this.createDefaultConstructor(psiClass, fqn);
          this.<JvmConstructor>addUnique(_members, _createDefaultConstructor);
        };
        this.preserve(fqn, _function_1);
      }
      StringBuilder _xifexpression = null;
      boolean _isEnum = psiClass.isEnum();
      if (_isEnum) {
        StringBuilder _xblockexpression_1 = null;
        {
          final Procedure0 _function_2 = () -> {
            EList<JvmMember> _members = it.getMembers();
            JvmOperation _createValuesOperation = this.createValuesOperation(psiClass, fqn);
            this.<JvmOperation>addUnique(_members, _createValuesOperation);
          };
          this.preserve(fqn, _function_2);
          final Procedure0 _function_3 = () -> {
            EList<JvmMember> _members = it.getMembers();
            JvmOperation _createValueOfOperation = this.createValueOfOperation(psiClass, fqn);
            this.<JvmOperation>addUnique(_members, _createValueOfOperation);
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
      PsiAnnotationMemberValue _defaultValue = ((PsiAnnotationMethod)method).getDefaultValue();
      Project _project = ((PsiAnnotationMethod)method).getProject();
      final Object defaultValue = this.computeAnnotationValue(_defaultValue, _project);
      if ((defaultValue != null)) {
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
      EList<Boolean> _values = ((JvmBooleanAnnotationValue)it).getValues();
      this.<Boolean>addUnique(_values, ((Boolean) value));
    }
    if (!_matched) {
      if (it instanceof JvmIntAnnotationValue) {
        _matched=true;
        EList<Integer> _values = ((JvmIntAnnotationValue)it).getValues();
        Integer _asInteger = this.asInteger(value);
        this.<Integer>addUnique(_values, _asInteger);
      }
    }
    if (!_matched) {
      if (it instanceof JvmLongAnnotationValue) {
        _matched=true;
        EList<Long> _values = ((JvmLongAnnotationValue)it).getValues();
        Long _asLong = this.asLong(value);
        this.<Long>addUnique(_values, _asLong);
      }
    }
    if (!_matched) {
      if (it instanceof JvmShortAnnotationValue) {
        _matched=true;
        EList<Short> _values = ((JvmShortAnnotationValue)it).getValues();
        Short _asShort = this.asShort(value);
        this.<Short>addUnique(_values, _asShort);
      }
    }
    if (!_matched) {
      if (it instanceof JvmFloatAnnotationValue) {
        _matched=true;
        EList<Float> _values = ((JvmFloatAnnotationValue)it).getValues();
        Float _asFloat = this.asFloat(value);
        this.<Float>addUnique(_values, _asFloat);
      }
    }
    if (!_matched) {
      if (it instanceof JvmDoubleAnnotationValue) {
        _matched=true;
        EList<Double> _values = ((JvmDoubleAnnotationValue)it).getValues();
        Double _asDouble = this.asDouble(value);
        this.<Double>addUnique(_values, _asDouble);
      }
    }
    if (!_matched) {
      if (it instanceof JvmCharAnnotationValue) {
        _matched=true;
        EList<Character> _values = ((JvmCharAnnotationValue)it).getValues();
        Character _asCharacter = this.asCharacter(value);
        this.<Character>addUnique(_values, _asCharacter);
      }
    }
    if (!_matched) {
      if (it instanceof JvmByteAnnotationValue) {
        _matched=true;
        EList<Byte> _values = ((JvmByteAnnotationValue)it).getValues();
        Byte _asByte = this.asByte(value);
        this.<Byte>addUnique(_values, _asByte);
      }
    }
    if (!_matched) {
      if (it instanceof JvmStringAnnotationValue) {
        _matched=true;
        EList<String> _values = ((JvmStringAnnotationValue)it).getValues();
        this.<String>addUnique(_values, ((String) value));
      }
    }
    if (!_matched) {
      if (it instanceof JvmTypeAnnotationValue) {
        _matched=true;
        EList<JvmTypeReference> _values = ((JvmTypeAnnotationValue)it).getValues();
        JvmTypeReference _createTypeReference = this.createTypeReference(((PsiType) value));
        this.<JvmTypeReference>addUnique(_values, _createTypeReference);
      }
    }
    if (!_matched) {
      if (it instanceof JvmAnnotationAnnotationValue) {
        _matched=true;
        final JvmAnnotationReference annotationReference = this.createAnnotationReference(((PsiAnnotation) value));
        if ((annotationReference != null)) {
          EList<JvmAnnotationReference> _values = ((JvmAnnotationAnnotationValue)it).getValues();
          this.<JvmAnnotationReference>addUnique(_values, annotationReference);
        }
      }
    }
    if (!_matched) {
      if (it instanceof JvmEnumAnnotationValue) {
        _matched=true;
        EList<JvmEnumerationLiteral> _values = ((JvmEnumAnnotationValue)it).getValues();
        JvmEnumerationLiteral _createEnumLiteralProxy = this.createEnumLiteralProxy(((PsiEnumConstant) value));
        this.<JvmEnumerationLiteral>addUnique(_values, _createEnumLiteralProxy);
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
    JavaPsiFacade _instance = JavaPsiFacade.getInstance(project);
    final PsiConstantEvaluationHelper constantEvaluationHelper = _instance.getConstantEvaluationHelper();
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
        PsiTypeElement _operand = ((PsiClassObjectAccessExpression)value).getOperand();
        _switchResult = _operand.getType();
      }
    }
    if (!_matched) {
      if (value instanceof PsiArrayInitializerMemberValue) {
        _matched=true;
        PsiAnnotationMemberValue[] _initializers = ((PsiArrayInitializerMemberValue)value).getInitializers();
        final Function1<PsiAnnotationMemberValue, Object> _function = (PsiAnnotationMemberValue it) -> {
          return this.computeAnnotationValue(it, helper);
        };
        List<Object> _map = ListExtensions.<PsiAnnotationMemberValue, Object>map(((List<PsiAnnotationMemberValue>)Conversions.doWrapArray(_initializers)), _function);
        _switchResult = _map.toArray();
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
      StringBuilder _append = fqn.append("values()");
      String _string = _append.toString();
      it.internalSetIdentifier(_string);
      it.setSimpleName("values");
      it.setVisibility(JvmVisibility.PUBLIC);
      it.setStatic(true);
      JvmGenericArrayTypeReference _createJvmGenericArrayTypeReference = this._typesFactory.createJvmGenericArrayTypeReference();
      final Procedure1<JvmGenericArrayTypeReference> _function_1 = (JvmGenericArrayTypeReference it_1) -> {
        Project _project = enumType.getProject();
        PsiElementFactory _psiElementFactory = this.getPsiElementFactory(_project);
        PsiClassType _createType = _psiElementFactory.createType(enumType);
        JvmTypeReference _createTypeReference = this.createTypeReference(_createType);
        it_1.setComponentType(_createTypeReference);
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
      Project _project = enumType.getProject();
      final PsiElementFactory psiElementFactory = this.getPsiElementFactory(_project);
      JvmOperation _createJvmOperation = this._typesFactory.createJvmOperation();
      final Procedure1<JvmOperation> _function = (JvmOperation it) -> {
        StringBuilder _append = fqn.append("valueOf(java.lang.String)");
        String _string = _append.toString();
        it.internalSetIdentifier(_string);
        it.setSimpleName("valueOf");
        it.setVisibility(JvmVisibility.PUBLIC);
        it.setStatic(true);
        PsiClassType _createType = psiElementFactory.createType(enumType);
        JvmTypeReference _createTypeReference = this.createTypeReference(_createType);
        it.setReturnType(_createTypeReference);
        EList<JvmFormalParameter> _parameters = it.getParameters();
        JvmFormalParameter _createJvmFormalParameter = this._typesFactory.createJvmFormalParameter();
        final Procedure1<JvmFormalParameter> _function_1 = (JvmFormalParameter it_1) -> {
          it_1.setName("name");
          JvmParameterizedTypeReference _createStringReference = this.createStringReference();
          it_1.setParameterType(_createStringReference);
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
      String _name = psiClass.getName();
      StringBuilder _append = fqn.append(_name);
      StringBuilder _append_1 = _append.append("()");
      String _string = _append_1.toString();
      it.internalSetIdentifier(_string);
      String _name_1 = psiClass.getName();
      it.setSimpleName(_name_1);
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
        boolean _hasModifierProperty = method.hasModifierProperty(PsiModifier.ABSTRACT);
        it.setAbstract(_hasModifierProperty);
        boolean _hasModifierProperty_1 = method.hasModifierProperty(PsiModifier.FINAL);
        it.setFinal(_hasModifierProperty_1);
        boolean _hasModifierProperty_2 = method.hasModifierProperty(PsiModifier.STATIC);
        it.setStatic(_hasModifierProperty_2);
        boolean _hasModifierProperty_3 = method.hasModifierProperty(PsiModifier.STRICTFP);
        it.setStrictFloatingPoint(_hasModifierProperty_3);
        boolean _hasModifierProperty_4 = method.hasModifierProperty(PsiModifier.SYNCHRONIZED);
        it.setSynchronized(_hasModifierProperty_4);
        boolean _hasModifierProperty_5 = method.hasModifierProperty(PsiModifier.NATIVE);
        it.setNative(_hasModifierProperty_5);
        PsiType _returnType = method.getReturnType();
        JvmTypeReference _createTypeReference = this.createTypeReference(_returnType);
        it.setReturnType(_createTypeReference);
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
    String _name = psiMethod.getName();
    StringBuilder _append = fqn.append(_name);
    _append.append("(");
    this.createFormalParameters(it, psiMethod, fqn);
    StringBuilder _append_1 = fqn.append(")");
    final String identifier = _append_1.toString();
    it.internalSetIdentifier(identifier);
    String _name_1 = psiMethod.getName();
    it.setSimpleName(_name_1);
    this.setVisibility(it, psiMethod);
    boolean _isDeprecated = psiMethod.isDeprecated();
    it.setDeprecated(_isDeprecated);
    boolean _isVarArgs = psiMethod.isVarArgs();
    it.setVarArgs(_isVarArgs);
    PsiReferenceList _throwsList = psiMethod.getThrowsList();
    PsiClassType[] _referencedTypes = _throwsList.getReferencedTypes();
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
        PsiType _type = parameter.getType();
        this.uriHelper.appendTypeName(fqn, _type);
        EList<JvmFormalParameter> _parameters = it.getParameters();
        JvmFormalParameter _createFormalParameter = this.createFormalParameter(parameter);
        this.<JvmFormalParameter>addUnique(_parameters, _createFormalParameter);
      }
    }
  }
  
  protected void createTypeParameters(final JvmTypeParameterDeclarator it, final PsiTypeParameterListOwner psiTypeParameterListOwner) {
    PsiTypeParameter[] _typeParameters = psiTypeParameterListOwner.getTypeParameters();
    for (final PsiTypeParameter typeParameter : _typeParameters) {
      EList<JvmTypeParameter> _typeParameters_1 = it.getTypeParameters();
      JvmTypeParameter _createTypeParameter = this.createTypeParameter(typeParameter);
      this.<JvmTypeParameter>addUnique(_typeParameters_1, _createTypeParameter);
    }
  }
  
  protected JvmFormalParameter createFormalParameter(final PsiParameter parameter) {
    JvmFormalParameter _createJvmFormalParameter = this._typesFactory.createJvmFormalParameter();
    final Procedure1<JvmFormalParameter> _function = (JvmFormalParameter it) -> {
      String _name = parameter.getName();
      it.setName(_name);
      PsiType _type = parameter.getType();
      JvmTypeReference _createTypeReference = this.createTypeReference(_type);
      it.setParameterType(_createTypeReference);
      this.createAnnotationValues(it, parameter);
    };
    return ObjectExtensions.<JvmFormalParameter>operator_doubleArrow(_createJvmFormalParameter, _function);
  }
  
  protected JvmTypeParameter createTypeParameter(final PsiTypeParameter parameter) {
    JvmTypeParameter _createJvmTypeParameter = this._typesFactory.createJvmTypeParameter();
    final Procedure1<JvmTypeParameter> _function = (JvmTypeParameter it) -> {
      String _name = parameter.getName();
      it.setName(_name);
      final PsiClassType[] extendsListTypes = parameter.getExtendsListTypes();
      int _length = extendsListTypes.length;
      boolean _notEquals = (_length != 0);
      if (_notEquals) {
        for (final PsiClassType upperBound : extendsListTypes) {
          {
            JvmUpperBound _createJvmUpperBound = this._typesFactory.createJvmUpperBound();
            final JvmTypeConstraintImplCustom jvmUpperBound = ((JvmTypeConstraintImplCustom) _createJvmUpperBound);
            JvmTypeReference _createTypeReference = this.createTypeReference(upperBound);
            jvmUpperBound.internalSetTypeReference(_createTypeReference);
            EList<JvmTypeConstraint> _constraints = it.getConstraints();
            this.<JvmTypeConstraintImplCustom>addUnique(_constraints, jvmUpperBound);
          }
        }
      } else {
        JvmUpperBound _createJvmUpperBound = this._typesFactory.createJvmUpperBound();
        final JvmTypeConstraintImplCustom jvmUpperBound = ((JvmTypeConstraintImplCustom) _createJvmUpperBound);
        JvmParameterizedTypeReference _createObjectReference = this.createObjectReference();
        jvmUpperBound.internalSetTypeReference(_createObjectReference);
        EList<JvmTypeConstraint> _constraints = it.getConstraints();
        this.<JvmTypeConstraintImplCustom>addUnique(_constraints, jvmUpperBound);
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
    JvmType _get = AbstractDeclaredTypeFactory.COMMON_PROXIES.get(AbstractDeclaredTypeFactory.STRING_CLASS_NAME);
    result.setType(_get);
    return result;
  }
  
  protected JvmTypeReference createTypeReference(final PsiType psiType) {
    JvmUnknownTypeReference _xtrycatchfinallyexpression = null;
    try {
      boolean _matched = false;
      if (psiType instanceof PsiArrayType) {
        _matched=true;
        PsiType _componentType = ((PsiArrayType)psiType).getComponentType();
        return this.createArrayTypeReference(_componentType);
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
              String _className = ((PsiClassType)psiType).getClassName();
              it.setQualifiedName(_className);
            };
            return ObjectExtensions.<JvmUnknownTypeReference>operator_doubleArrow(_createJvmUnknownTypeReference, _function);
          } else {
            JvmParameterizedTypeReference _createClassTypeReference = this.createClassTypeReference(resolveResult);
            final Procedure1<JvmParameterizedTypeReference> _function_1 = (JvmParameterizedTypeReference it) -> {
              try {
                PsiClassType _rawType = ((PsiClassType)psiType).rawType();
                JvmType _createProxy = this.createProxy(_rawType);
                it.setType(_createProxy);
                PsiType[] _parameters = ((PsiClassType)psiType).getParameters();
                for (final PsiType parameter : _parameters) {
                  EList<JvmTypeReference> _arguments = it.getArguments();
                  JvmTypeReference _createTypeArgument = this.createTypeArgument(parameter);
                  this.<JvmTypeReference>addUnique(_arguments, _createTypeArgument);
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
          JvmType _createProxy = this.createProxy(psiType);
          it.setType(_createProxy);
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
          Project _project = psiClass.getProject();
          final PsiElementFactory psiElementFactory = this.getPsiElementFactory(_project);
          PsiSubstitutor _substitutor = resolveResult.getSubstitutor();
          final PsiClassType outerType = psiElementFactory.createType(containingClass, _substitutor);
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
    return ((psiClass.getContainingClass() != null) && (!psiClass.hasModifierProperty(PsiModifier.STATIC)));
  }
  
  protected JvmTypeReference _createTypeArgument(final PsiType type) {
    return this.createTypeReference(type);
  }
  
  protected JvmTypeReference _createTypeArgument(final PsiWildcardType type) {
    JvmWildcardTypeReference _createJvmWildcardTypeReference = this._typesFactory.createJvmWildcardTypeReference();
    final Procedure1<JvmWildcardTypeReference> _function = (JvmWildcardTypeReference it) -> {
      JvmUpperBound _createJvmUpperBound = this._typesFactory.createJvmUpperBound();
      final JvmTypeConstraintImplCustom upperBound = ((JvmTypeConstraintImplCustom) _createJvmUpperBound);
      JvmTypeReference _createUpperBoundReference = this.createUpperBoundReference(type);
      upperBound.internalSetTypeReference(_createUpperBoundReference);
      EList<JvmTypeConstraint> _constraints = it.getConstraints();
      this.<JvmTypeConstraintImplCustom>addUnique(_constraints, upperBound);
      final PsiType superBound = type.getSuperBound();
      boolean _notEquals = (!Objects.equal(superBound, PsiType.NULL));
      if (_notEquals) {
        JvmLowerBound _createJvmLowerBound = this._typesFactory.createJvmLowerBound();
        final JvmTypeConstraintImplCustom lowerBound = ((JvmTypeConstraintImplCustom) _createJvmLowerBound);
        JvmTypeReference _createTypeReference = this.createTypeReference(superBound);
        lowerBound.internalSetTypeReference(_createTypeReference);
        EList<JvmTypeConstraint> _constraints_1 = it.getConstraints();
        this.<JvmTypeConstraintImplCustom>addUnique(_constraints_1, lowerBound);
      }
    };
    return ObjectExtensions.<JvmWildcardTypeReference>operator_doubleArrow(_createJvmWildcardTypeReference, _function);
  }
  
  protected JvmTypeReference _createTypeArgument(final PsiCapturedWildcardType type) {
    PsiWildcardType _wildcard = type.getWildcard();
    return this._createTypeArgument(_wildcard);
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
      JvmTypeReference _createTypeReference = this.createTypeReference(psiComponentType);
      it.setComponentType(_createTypeReference);
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
          EList<JvmMember> _members = it.getMembers();
          JvmDeclaredType _createType = this.createType(innerClass, fqn);
          this.<JvmDeclaredType>addUnique(_members, _createType);
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
    boolean _hasModifierProperty = modifierList.hasModifierProperty(PsiModifier.ABSTRACT);
    it.setAbstract(_hasModifierProperty);
    boolean _hasModifierProperty_1 = modifierList.hasModifierProperty(PsiModifier.STATIC);
    it.setStatic(_hasModifierProperty_1);
    boolean _isEnum = psiClass.isEnum();
    boolean _not = (!_isEnum);
    if (_not) {
      boolean _hasModifierProperty_2 = modifierList.hasModifierProperty(PsiModifier.FINAL);
      it.setFinal(_hasModifierProperty_2);
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
      String _canonicalText = ((PsiClassType)type).getCanonicalText();
      String _qualifiedClassName = PsiNameHelper.getQualifiedClassName(_canonicalText, true);
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
      PsiClass _resolve = ((PsiClassType)type).resolve();
      _xifexpression = _resolve.isAnnotationType();
    } else {
      _xifexpression = false;
    }
    return _xifexpression;
  }
  
  protected boolean isEnum(final PsiType type) {
    boolean _xifexpression = false;
    if ((type instanceof PsiClassType)) {
      PsiClass _resolve = ((PsiClassType)type).resolve();
      _xifexpression = _resolve.isEnum();
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
