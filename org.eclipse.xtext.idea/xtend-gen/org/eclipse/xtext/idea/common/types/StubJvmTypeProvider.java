/**
 * Copyright (c) 2015 itemis AG (http://www.itemis.eu) and others.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 */
package org.eclipse.xtext.idea.common.types;

import com.google.common.base.Objects;
import com.intellij.openapi.application.ApplicationManager;
import com.intellij.openapi.module.Module;
import com.intellij.openapi.progress.ProcessCanceledException;
import com.intellij.openapi.progress.ProgressIndicatorProvider;
import com.intellij.openapi.project.IndexNotReadyException;
import com.intellij.openapi.util.Computable;
import com.intellij.psi.JavaPsiFacade;
import com.intellij.psi.PsiClass;
import com.intellij.psi.impl.compiled.SignatureParsing;
import com.intellij.psi.impl.compiled.StubBuildingVisitor;
import com.intellij.psi.search.GlobalSearchScope;
import java.text.StringCharacterIterator;
import java.util.List;
import org.eclipse.emf.common.util.URI;
import org.eclipse.emf.ecore.EObject;
import org.eclipse.emf.ecore.resource.Resource;
import org.eclipse.emf.ecore.resource.ResourceSet;
import org.eclipse.xtend.lib.annotations.Accessors;
import org.eclipse.xtext.common.types.JvmDeclaredType;
import org.eclipse.xtext.common.types.JvmType;
import org.eclipse.xtext.common.types.access.IMirror;
import org.eclipse.xtext.common.types.access.TypeResource;
import org.eclipse.xtext.common.types.access.impl.AbstractJvmTypeProvider;
import org.eclipse.xtext.common.types.access.impl.AbstractRuntimeJvmTypeProvider;
import org.eclipse.xtext.common.types.access.impl.IClassMirror;
import org.eclipse.xtext.common.types.access.impl.ITypeFactory;
import org.eclipse.xtext.common.types.access.impl.IndexedJvmTypeAccess;
import org.eclipse.xtext.common.types.access.impl.TypeResourceServices;
import org.eclipse.xtext.idea.common.types.PsiBasedTypeFactory;
import org.eclipse.xtext.idea.common.types.PsiClassMirror;
import org.eclipse.xtext.idea.common.types.StubURIHelper;
import org.eclipse.xtext.psi.IPsiModelAssociator;
import org.eclipse.xtext.resource.ISynchronizable;
import org.eclipse.xtext.service.OperationCanceledError;
import org.eclipse.xtext.util.Strings;
import org.eclipse.xtext.util.concurrent.IUnitOfWork;
import org.eclipse.xtext.xbase.lib.Exceptions;
import org.eclipse.xtext.xbase.lib.Extension;
import org.eclipse.xtext.xbase.lib.IterableExtensions;
import org.eclipse.xtext.xbase.lib.Pure;

@SuppressWarnings("all")
public class StubJvmTypeProvider extends AbstractRuntimeJvmTypeProvider {
  private final ITypeFactory<PsiClass, JvmDeclaredType> psiClassFactory;
  
  @Accessors
  private final Module module;
  
  @Accessors
  @Extension
  private final StubURIHelper uriHelper;
  
  @Accessors
  private final GlobalSearchScope searchScope;
  
  public StubJvmTypeProvider(final Module module, final ResourceSet resourceSet, final IndexedJvmTypeAccess indexedJvmTypeAccess, final TypeResourceServices services, final IPsiModelAssociator psiModelAssociator, final GlobalSearchScope searchScope) {
    super(resourceSet, indexedJvmTypeAccess, services);
    this.module = module;
    this.searchScope = searchScope;
    this.uriHelper = this.createStubURIHelper();
    this.psiClassFactory = this.createPsiClassFactory(psiModelAssociator);
  }
  
  public PsiBasedTypeFactory createPsiClassFactory(final IPsiModelAssociator psiModelAssociator) {
    return new PsiBasedTypeFactory(this.uriHelper, psiModelAssociator);
  }
  
  protected StubURIHelper createStubURIHelper() {
    return new StubURIHelper();
  }
  
  @Override
  public JvmType findTypeByName(final String name) {
    return this.doFindTypeByName(name, false);
  }
  
  @Override
  public JvmType findTypeByName(final String name, final boolean binaryNestedTypeDelimiter) {
    JvmType _xblockexpression = null;
    {
      JvmType result = this.doFindTypeByName(name, false);
      if (((!Objects.equal(result, null)) || this.isBinaryNestedTypeDelimiter(name, binaryNestedTypeDelimiter))) {
        return result;
      }
      final AbstractJvmTypeProvider.ClassNameVariants nameVariants = new AbstractJvmTypeProvider.ClassNameVariants(name);
      while ((Objects.equal(result, null) && nameVariants.hasNext())) {
        {
          final String nextVariant = nameVariants.next();
          result = this.doFindTypeByName(nextVariant, true);
        }
      }
      _xblockexpression = result;
    }
    return _xblockexpression;
  }
  
  public JvmType doFindTypeByName(final String name, final boolean traverseNestedTypes) {
    try {
      JvmType _xblockexpression = null;
      {
        ProgressIndicatorProvider.checkCanceled();
        final String normalizedName = this.normalize(name);
        final URI resourceURI = this.uriHelper.createResourceURI(normalizedName);
        final String fragment = this.uriHelper.getFragment(normalizedName);
        JvmType _switchResult = null;
        ResourceSet _resourceSet = this.getResourceSet();
        final ResourceSet resourceSet = _resourceSet;
        boolean _matched = false;
        if (resourceSet instanceof ISynchronizable) {
          _matched=true;
          final IUnitOfWork<JvmType, ResourceSet> _function = (ResourceSet it) -> {
            return this.findType(resourceURI, fragment, traverseNestedTypes);
          };
          _switchResult = ((ISynchronizable<ResourceSet>)resourceSet).<JvmType>execute(_function);
        }
        if (!_matched) {
          _switchResult = this.findType(resourceURI, fragment, traverseNestedTypes);
        }
        _xblockexpression = _switchResult;
      }
      return _xblockexpression;
    } catch (Throwable _e) {
      throw Exceptions.sneakyThrow(_e);
    }
  }
  
  protected String normalize(final String name) {
    try {
      String _xifexpression = null;
      boolean _startsWith = name.startsWith("[");
      if (_startsWith) {
        StringCharacterIterator _stringCharacterIterator = new StringCharacterIterator(name);
        _xifexpression = SignatureParsing.parseTypeString(_stringCharacterIterator, StubBuildingVisitor.GUESSING_MAPPER);
      } else {
        _xifexpression = name;
      }
      return _xifexpression;
    } catch (Throwable _e) {
      throw Exceptions.sneakyThrow(_e);
    }
  }
  
  public JvmType findType(final URI resourceURI, final String fragment, final boolean traverseNestedTypes) {
    try {
      final IndexedJvmTypeAccess indexedJvmTypeAccess = this.getIndexedJvmTypeAccess();
      if ((indexedJvmTypeAccess != null)) {
        final URI proxyURI = resourceURI.appendFragment(fragment);
        try {
          final EObject candidate = indexedJvmTypeAccess.getIndexedJvmType(proxyURI, this.getResourceSet());
          if ((candidate instanceof JvmType)) {
            return ((JvmType)candidate);
          }
        } catch (final Throwable _t) {
          if (_t instanceof IndexedJvmTypeAccess.UnknownNestedTypeException) {
            final IndexedJvmTypeAccess.UnknownNestedTypeException e = (IndexedJvmTypeAccess.UnknownNestedTypeException)_t;
            return null;
          } else {
            throw Exceptions.sneakyThrow(_t);
          }
        }
      }
      ProgressIndicatorProvider.checkCanceled();
      try {
        final Resource existing = this.getResourceSet().getResource(resourceURI, false);
        boolean _notEquals = (!Objects.equal(existing, null));
        if (_notEquals) {
          return this.findType(existing, fragment, traverseNestedTypes);
        }
        final IMirror mirror = this.createMirror(resourceURI);
        boolean _equals = Objects.equal(mirror, null);
        if (_equals) {
          return null;
        }
        final TypeResource resource = this.doCreateResource(resourceURI);
        resource.setMirror(mirror);
        this.getResourceSet().getResources().add(resource);
        resource.load(null);
        return this.findType(resource, fragment, traverseNestedTypes);
      } catch (final Throwable _t_1) {
        if (_t_1 instanceof OperationCanceledError) {
          final OperationCanceledError e_1 = (OperationCanceledError)_t_1;
          throw e_1.getWrapped();
        } else {
          throw Exceptions.sneakyThrow(_t_1);
        }
      }
    } catch (Throwable _e) {
      throw Exceptions.sneakyThrow(_e);
    }
  }
  
  protected JvmType findType(final Resource resource, final String fragment, final boolean traverseNestedTypes) {
    final Computable<JvmType> _function = () -> {
      EObject _eObject = resource.getEObject(fragment);
      final JvmType result = ((JvmType) _eObject);
      if (((!Objects.equal(result, null)) || (!traverseNestedTypes))) {
        return result;
      }
      final EObject rootType = IterableExtensions.<EObject>head(resource.getContents());
      if ((rootType instanceof JvmDeclaredType)) {
        final String rootTypeName = resource.getURI().segment(1);
        int _length = rootTypeName.length();
        int _plus = (_length + 1);
        final String nestedTypeName = fragment.substring(_plus);
        final List<String> segments = Strings.split(nestedTypeName, "$");
        return this.findNestedType(((JvmDeclaredType)rootType), segments, 0);
      }
      return null;
    };
    return ApplicationManager.getApplication().<JvmType>runReadAction(_function);
  }
  
  @Override
  protected IMirror createMirrorForFQN(final String name) {
    final Computable<IClassMirror> _function = () -> {
      PsiClassMirror _xtrycatchfinallyexpression = null;
      try {
        PsiClassMirror _xblockexpression = null;
        {
          final PsiClass psiClass = JavaPsiFacade.getInstance(this.module.getProject()).findClass(name, this.searchScope);
          if ((Objects.equal(psiClass, null) || (!Objects.equal(psiClass.getContainingClass(), null)))) {
            return null;
          }
          _xblockexpression = new PsiClassMirror(psiClass, this.psiClassFactory);
        }
        _xtrycatchfinallyexpression = _xblockexpression;
      } catch (final Throwable _t) {
        if (_t instanceof IndexNotReadyException) {
          final IndexNotReadyException e = (IndexNotReadyException)_t;
          ProcessCanceledException _processCanceledException = new ProcessCanceledException();
          throw new OperationCanceledError(_processCanceledException);
        } else {
          throw Exceptions.sneakyThrow(_t);
        }
      }
      return _xtrycatchfinallyexpression;
    };
    return ApplicationManager.getApplication().<IClassMirror>runReadAction(_function);
  }
  
  protected GlobalSearchScope getSearchScope() {
    return this.searchScope;
  }
  
  @Pure
  public Module getModule() {
    return this.module;
  }
  
  @Pure
  public StubURIHelper getUriHelper() {
    return this.uriHelper;
  }
}
