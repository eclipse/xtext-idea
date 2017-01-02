/**
 * Copyright (c) 2015 itemis AG (http://www.itemis.eu) and others.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 */
package org.eclipse.xtext.idea.execution;

import com.google.common.base.Objects;
import com.google.inject.Inject;
import com.intellij.execution.Location;
import com.intellij.execution.PsiLocation;
import com.intellij.execution.actions.ConfigurationContext;
import com.intellij.execution.configurations.RunConfiguration;
import com.intellij.openapi.actionSystem.DataContext;
import com.intellij.openapi.actionSystem.DataContextWrapper;
import com.intellij.openapi.actionSystem.LangDataKeys;
import com.intellij.psi.PsiElement;
import com.intellij.psi.PsiFile;
import com.intellij.psi.PsiManager;
import com.intellij.psi.util.PsiTreeUtil;
import java.util.ArrayList;
import org.eclipse.xtext.idea.trace.IIdeaTrace;
import org.eclipse.xtext.idea.trace.ILocationInVirtualFile;
import org.eclipse.xtext.idea.trace.ITraceForVirtualFileProvider;
import org.eclipse.xtext.idea.trace.VirtualFileInProject;
import org.eclipse.xtext.psi.impl.BaseXtextFile;
import org.eclipse.xtext.xbase.lib.CollectionLiterals;
import org.eclipse.xtext.xbase.lib.IterableExtensions;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

/**
 * @author dhuebner - Initial contribution and API
 */
@SuppressWarnings("all")
public class ConfigurationProducerExtensions {
  @Inject
  private ITraceForVirtualFileProvider traceProvider;
  
  public ConfigurationContext prepareContextFor(final ConfigurationContext context, final PsiElement psiElement) {
    DataContext _dataContext = context.getDataContext();
    final ConfigurationContext ctx = ConfigurationContext.getFromContext(new DataContextWrapper(_dataContext) {
      @Override
      public Object getData(final String dataId) {
        boolean _is = RunConfiguration.DATA_KEY.is(dataId);
        if (_is) {
          return context.getOriginalConfiguration(null);
        } else {
          boolean _is_1 = LangDataKeys.MODULE.is(dataId);
          if (_is_1) {
            return context.getModule();
          } else {
            boolean _is_2 = Location.DATA_KEY.is(dataId);
            if (_is_2) {
              return PsiLocation.<PsiElement>fromPsiElement(psiElement);
            }
          }
        }
        return super.getData(dataId);
      }
    });
    return ctx;
  }
  
  @Nullable
  public PsiElement tracedJavaElement(@NotNull final ConfigurationContext context) {
    Location _location = context.getLocation();
    PsiElement _psiElement = null;
    if (_location!=null) {
      _psiElement=_location.getPsiElement();
    }
    boolean _tripleNotEquals = (_psiElement != null);
    if (_tripleNotEquals) {
      Location _location_1 = context.getLocation();
      PsiElement _psiElement_1 = null;
      if (_location_1!=null) {
        _psiElement_1=_location_1.getPsiElement();
      }
      final PsiElement psiElement = _psiElement_1;
      PsiElement javaElement = IterableExtensions.head(this.traceProvider.getGeneratedElements(psiElement));
      if ((javaElement == null)) {
        javaElement = IterableExtensions.<PsiFile>head(this.getJavaFiles(psiElement));
      }
      return javaElement;
    }
    return null;
  }
  
  private Iterable<PsiFile> getJavaFiles(@NotNull final PsiElement xtextElement) {
    final BaseXtextFile xtextFile = PsiTreeUtil.<BaseXtextFile>getParentOfType(xtextElement, BaseXtextFile.class, false);
    if ((xtextFile == null)) {
      return CollectionLiterals.<PsiFile>emptySet();
    }
    final VirtualFileInProject fileInProject = VirtualFileInProject.forPsiElement(xtextFile);
    boolean _equals = Objects.equal(fileInProject, null);
    if (_equals) {
      return CollectionLiterals.<PsiFile>emptySet();
    }
    final IIdeaTrace trace = this.traceProvider.getTraceToTarget(fileInProject);
    if ((trace != null)) {
      final ArrayList<PsiFile> javaFiles = CollectionLiterals.<PsiFile>newArrayList();
      Iterable<? extends ILocationInVirtualFile> _allAssociatedLocations = trace.getAllAssociatedLocations();
      for (final ILocationInVirtualFile uri : _allAssociatedLocations) {
        {
          final VirtualFileInProject res = uri.getPlatformResource();
          if ((res != null)) {
            final PsiFile javaPsiFile = PsiManager.getInstance(xtextFile.getProject()).findFile(res.getFile());
            if ((javaPsiFile != null)) {
              javaFiles.add(javaPsiFile);
            }
          }
        }
      }
      return javaFiles;
    }
    return CollectionLiterals.<PsiFile>emptySet();
  }
}
