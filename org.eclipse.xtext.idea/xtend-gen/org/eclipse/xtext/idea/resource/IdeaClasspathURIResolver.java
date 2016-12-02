/**
 * Copyright (c) 2015 itemis AG (http://www.itemis.eu) and others.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 */
package org.eclipse.xtext.idea.resource;

import com.intellij.openapi.application.ApplicationManager;
import com.intellij.openapi.module.Module;
import com.intellij.openapi.util.Computable;
import com.intellij.openapi.vfs.VirtualFile;
import com.intellij.psi.PsiFile;
import com.intellij.psi.search.FilenameIndex;
import com.intellij.psi.search.GlobalSearchScope;
import org.eclipse.emf.common.util.URI;
import org.eclipse.xtext.idea.resource.VirtualFileURIUtil;
import org.eclipse.xtext.psi.XtextPsiUtils;
import org.eclipse.xtext.resource.ClasspathUriUtil;
import org.eclipse.xtext.resource.IClasspathUriResolver;
import org.eclipse.xtext.xbase.lib.Functions.Function1;

/**
 * @author Sven Efftinge - Initial contribution and API
 */
@SuppressWarnings("all")
public class IdeaClasspathURIResolver implements IClasspathUriResolver {
  @Override
  public URI resolve(final Object context, final URI classpathUri) {
    if ((!(context instanceof Module))) {
      throw new IllegalArgumentException(("Expected a module as context but got " + context));
    }
    boolean _isClasspathUri = ClasspathUriUtil.isClasspathUri(classpathUri);
    boolean _not = (!_isClasspathUri);
    if (_not) {
      return classpathUri;
    }
    final Module module = ((Module) context);
    final GlobalSearchScope scope = GlobalSearchScope.moduleWithDependenciesAndLibrariesScope(module);
    final Function1<Object, URI> _function = (Object it) -> {
      final PsiFile[] files = FilenameIndex.getFilesByName(module.getProject(), classpathUri.lastSegment(), scope);
      for (final PsiFile file : files) {
        {
          final VirtualFile vf = XtextPsiUtils.findVirtualFile(file);
          if (((vf != null) && vf.exists())) {
            final URI uri = VirtualFileURIUtil.getURI(vf);
            boolean _endsWith = uri.toString().endsWith(classpathUri.path());
            if (_endsWith) {
              return uri;
            }
          }
        }
      }
      return null;
    };
    final URI uri = ApplicationManager.getApplication().<URI>runReadAction(
      ((Computable<URI>) new Computable<URI>() {
          public URI compute() {
            return _function.apply(null);
          }
      }));
    URI _elvis = null;
    if (uri != null) {
      _elvis = uri;
    } else {
      _elvis = classpathUri;
    }
    return _elvis;
  }
}
