/**
 * Copyright (c) 2015 itemis AG (http://www.itemis.eu) and others.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 */
package org.eclipse.xtext.idea.debug;

import com.google.common.base.Objects;
import com.google.inject.Inject;
import com.intellij.debugger.SourcePosition;
import com.intellij.debugger.engine.DebugProcess;
import com.intellij.debugger.engine.DebugProcessImpl;
import com.intellij.debugger.engine.PositionManagerImpl;
import com.intellij.openapi.application.ApplicationManager;
import com.intellij.openapi.project.Project;
import com.intellij.openapi.util.Computable;
import com.intellij.openapi.vfs.VirtualFile;
import com.intellij.psi.PsiFile;
import com.intellij.psi.PsiManager;
import com.sun.jdi.Location;
import java.lang.reflect.Method;
import java.util.HashMap;
import java.util.Map;
import org.eclipse.emf.common.util.URI;
import org.eclipse.xtext.generator.trace.AbstractTraceRegion;
import org.eclipse.xtext.generator.trace.TraceFileNameProvider;
import org.eclipse.xtext.generator.trace.TraceRegionSerializer;
import org.eclipse.xtext.idea.build.XtextAutoBuilderComponent;
import org.eclipse.xtext.idea.resource.VirtualFileURIUtil;
import org.eclipse.xtext.xbase.lib.CollectionLiterals;
import org.eclipse.xtext.xbase.lib.Exceptions;
import org.eclipse.xtext.xbase.lib.Functions.Function1;
import org.eclipse.xtext.xbase.lib.IterableExtensions;

/**
 * @author Sven Efftinge - Initial contribution and API
 */
@SuppressWarnings("all")
public class DebugProcessExtensions {
  @Inject
  private TraceRegionSerializer traceRegionSerializer;
  
  @Inject
  private TraceFileNameProvider traceFileNameProvider;
  
  public PsiFile getPsiFile(final DebugProcess process, final Location location) {
    final PositionManagerImpl delegate = this.getJavaPositionManger(process);
    final Computable<PsiFile> _function = () -> {
      try {
        final Method method = PositionManagerImpl.class.getDeclaredMethod("getPsiFileByLocation", Project.class, Location.class);
        method.setAccessible(true);
        final Object result = method.invoke(delegate, process.getProject(), location);
        return ((PsiFile) result);
      } catch (Throwable _e) {
        throw Exceptions.sneakyThrow(_e);
      }
    };
    return ApplicationManager.getApplication().<PsiFile>runReadAction(_function);
  }
  
  public AbstractTraceRegion getTraceForJava(final SourcePosition javaSource) {
    try {
      final URI uri = VirtualFileURIUtil.getURI(javaSource.getFile().getVirtualFile());
      final String lastSegmentOfTrace = this.traceFileNameProvider.getTraceFromJava(uri.lastSegment());
      final VirtualFile virtualFile = VirtualFileURIUtil.getVirtualFile(uri.trimSegments(1).appendSegment(lastSegmentOfTrace));
      if (((virtualFile == null) || (!virtualFile.exists()))) {
        return null;
      }
      final AbstractTraceRegion trace = this.traceRegionSerializer.readTraceRegionFrom(virtualFile.getInputStream());
      return trace;
    } catch (Throwable _e) {
      throw Exceptions.sneakyThrow(_e);
    }
  }
  
  public PsiFile getPsiFile(final DebugProcess process, final URI uri) {
    final Computable<PsiFile> _function = () -> {
      return PsiManager.getInstance(process.getProject()).findFile(VirtualFileURIUtil.getVirtualFile(uri));
    };
    return ApplicationManager.getApplication().<PsiFile>runReadAction(_function);
  }
  
  public Map<URI, AbstractTraceRegion> getTracesForSource(final DebugProcess process, final SourcePosition source) {
    try {
      final XtextAutoBuilderComponent builder = process.getProject().<XtextAutoBuilderComponent>getComponent(XtextAutoBuilderComponent.class);
      final Iterable<URI> generated = builder.getGeneratedSources(VirtualFileURIUtil.getURI(source.getFile().getVirtualFile()));
      final HashMap<URI, AbstractTraceRegion> result = CollectionLiterals.<URI, AbstractTraceRegion>newHashMap();
      final Function1<URI, Boolean> _function = (URI it) -> {
        String _fileExtension = it.fileExtension();
        return Boolean.valueOf(Objects.equal(_fileExtension, "java"));
      };
      Iterable<URI> _filter = IterableExtensions.<URI>filter(generated, _function);
      for (final URI uri : _filter) {
        {
          final String lastSegmentOfTrace = this.traceFileNameProvider.getTraceFromJava(uri.lastSegment());
          final VirtualFile virtualFile = VirtualFileURIUtil.getVirtualFile(uri.trimSegments(1).appendSegment(lastSegmentOfTrace));
          if (((virtualFile != null) && virtualFile.exists())) {
            final AbstractTraceRegion trace = this.traceRegionSerializer.readTraceRegionFrom(virtualFile.getInputStream());
            result.put(uri, trace);
          }
        }
      }
      return result;
    } catch (Throwable _e) {
      throw Exceptions.sneakyThrow(_e);
    }
  }
  
  public PositionManagerImpl getJavaPositionManger(final DebugProcess process) {
    return new PositionManagerImpl(((DebugProcessImpl) process));
  }
  
  public URI findOriginalDeclaration(final DebugProcess process, final Location location) {
    final PsiFile psiFile = this.getPsiFile(process, location);
    if ((psiFile == null)) {
      return null;
    } else {
      return IterableExtensions.<URI>head(process.getProject().<XtextAutoBuilderComponent>getComponent(XtextAutoBuilderComponent.class).getSource4GeneratedSource(VirtualFileURIUtil.getURI(psiFile.getVirtualFile())));
    }
  }
}
