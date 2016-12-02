/**
 * Copyright (c) 2015 itemis AG (http://www.itemis.eu) and others.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 */
package org.eclipse.xtext.idea.common.types;

import com.intellij.openapi.application.Application;
import com.intellij.openapi.application.ApplicationManager;
import com.intellij.openapi.module.Module;
import com.intellij.openapi.progress.ProcessCanceledException;
import com.intellij.openapi.progress.ProgressIndicatorProvider;
import com.intellij.openapi.project.IndexNotReadyException;
import com.intellij.openapi.project.Project;
import com.intellij.openapi.util.Computable;
import com.intellij.psi.JavaPsiFacade;
import com.intellij.psi.PsiClass;
import com.intellij.psi.impl.compiled.SignatureParsing;
import com.intellij.psi.impl.compiled.StubBuildingVisitor;
import com.intellij.psi.search.GlobalSearchScope;
import java.text.StringCharacterIterator;
import java.util.List;
import org.eclipse.emf.common.util.EList;
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
    StubURIHelper _createStubURIHelper = this.createStubURIHelper();
    this.uriHelper = _createStubURIHelper;
    PsiBasedTypeFactory _createPsiClassFactory = this.createPsiClassFactory(psiModelAssociator);
    this.psiClassFactory = _createPsiClassFactory;
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
      if (((result != null) || this.isBinaryNestedTypeDelimiter(name, binaryNestedTypeDelimiter))) {
        return result;
      }
      final AbstractJvmTypeProvider.ClassNameVariants nameVariants = new AbstractJvmTypeProvider.ClassNameVariants(name);
      while (((result == null) && nameVariants.hasNext())) {
        {
          final String nextVariant = nameVariants.next();
          JvmType _doFindTypeByName = this.doFindTypeByName(nextVariant, true);
          result = _doFindTypeByName;
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
          ResourceSet _resourceSet = this.getResourceSet();
          final EObject candidate = indexedJvmTypeAccess.getIndexedJvmType(proxyURI, _resourceSet);
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
        ResourceSet _resourceSet_1 = this.getResourceSet();
        final Resource existing = _resourceSet_1.getResource(resourceURI, false);
        if ((existing != null)) {
          return this.findType(existing, fragment, traverseNestedTypes);
        }
        final IMirror mirror = this.createMirror(resourceURI);
        if ((mirror == null)) {
          return null;
        }
        final TypeResource resource = this.doCreateResource(resourceURI);
        resource.setMirror(mirror);
        ResourceSet _resourceSet_2 = this.getResourceSet();
        EList<Resource> _resources = _resourceSet_2.getResources();
        _resources.add(resource);
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
    Application _application = ApplicationManager.getApplication();
    final Computable<JvmType> _function = () -> {
      EObject _eObject = resource.getEObject(fragment);
      final JvmType result = ((JvmType) _eObject);
      if (((result != null) || (!traverseNestedTypes))) {
        return result;
      }
      EList<EObject> _contents = resource.getContents();
      final EObject rootType = IterableExtensions.<EObject>head(_contents);
      if ((rootType instanceof JvmDeclaredType)) {
        URI _uRI = resource.getURI();
        final String rootTypeName = _uRI.segment(1);
        int _length = rootTypeName.length();
        int _plus = (_length + 1);
        final String nestedTypeName = fragment.substring(_plus);
        final List<String> segments = Strings.split(nestedTypeName, "$");
        return this.findNestedType(((JvmDeclaredType)rootType), segments, 0);
      }
      return null;
    };
    return _application.<JvmType>runReadAction(_function);
  }
  
  @Override
  protected IMirror createMirrorForFQN(final String name) {
    Application _application = ApplicationManager.getApplication();
    final Computable<IClassMirror> _function = () -> {
      PsiClassMirror _xtrycatchfinallyexpression = null;
      try {
        PsiClassMirror _xblockexpression = null;
        {
          Project _project = this.module.getProject();
          JavaPsiFacade _instance = JavaPsiFacade.getInstance(_project);
          final PsiClass psiClass = _instance.findClass(name, this.searchScope);
          if (((psiClass == null) || (psiClass.getContainingClass() != null))) {
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
    return _application.<IClassMirror>runReadAction(_function);
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
