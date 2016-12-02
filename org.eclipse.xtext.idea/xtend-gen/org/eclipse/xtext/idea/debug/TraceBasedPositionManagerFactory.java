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
import com.intellij.debugger.NoDataException;
import com.intellij.debugger.PositionManager;
import com.intellij.debugger.PositionManagerFactory;
import com.intellij.debugger.SourcePosition;
import com.intellij.debugger.engine.DebugProcess;
import com.intellij.debugger.engine.PositionManagerImpl;
import com.intellij.debugger.requests.ClassPrepareRequestor;
import com.intellij.debugger.requests.RequestManager;
import com.intellij.lang.Language;
import com.intellij.openapi.application.Application;
import com.intellij.openapi.application.ApplicationManager;
import com.intellij.openapi.editor.Document;
import com.intellij.openapi.project.Project;
import com.intellij.openapi.util.Computable;
import com.intellij.openapi.util.TextRange;
import com.intellij.psi.PsiDocumentManager;
import com.intellij.psi.PsiElement;
import com.intellij.psi.PsiFile;
import com.intellij.util.DocumentUtil;
import com.sun.jdi.AbsentInformationException;
import com.sun.jdi.Location;
import com.sun.jdi.ReferenceType;
import com.sun.jdi.request.ClassPrepareRequest;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Set;
import org.eclipse.emf.common.util.TreeIterator;
import org.eclipse.emf.common.util.URI;
import org.eclipse.xtext.generator.trace.AbstractTraceRegion;
import org.eclipse.xtext.generator.trace.ILocationData;
import org.eclipse.xtext.generator.trace.SourceRelativeURI;
import org.eclipse.xtext.idea.debug.DebugProcessExtensions;
import org.eclipse.xtext.idea.lang.IXtextLanguage;
import org.eclipse.xtext.idea.trace.IIdeaTrace;
import org.eclipse.xtext.idea.trace.ILocationInVirtualFile;
import org.eclipse.xtext.idea.trace.ITraceForVirtualFileProvider;
import org.eclipse.xtext.idea.trace.VirtualFileInProject;
import org.eclipse.xtext.util.ITextRegionWithLineInformation;
import org.eclipse.xtext.util.TextRegion;
import org.eclipse.xtext.xbase.lib.CollectionLiterals;
import org.eclipse.xtext.xbase.lib.Exceptions;
import org.eclipse.xtext.xbase.lib.Extension;
import org.eclipse.xtext.xbase.lib.Functions.Function1;
import org.eclipse.xtext.xbase.lib.IterableExtensions;
import org.eclipse.xtext.xbase.lib.IteratorExtensions;

/**
 * @author Sven Efftinge - Initial contribution and API
 */
@SuppressWarnings("all")
public class TraceBasedPositionManagerFactory extends PositionManagerFactory {
  public static class TraceBasedPositionManager implements PositionManager {
    private final DebugProcess process;
    
    private final IXtextLanguage language;
    
    @Inject
    @Extension
    private DebugProcessExtensions _debugProcessExtensions;
    
    @Inject
    private ITraceForVirtualFileProvider traceForVirtualFileProvider;
    
    public TraceBasedPositionManager(final DebugProcess process, final IXtextLanguage language) {
      this.process = process;
      this.language = language;
    }
    
    /**
     * This one is called
     */
    @Override
    public ClassPrepareRequest createPrepareRequest(final ClassPrepareRequestor requestor, final SourcePosition source) throws NoDataException {
      PsiFile _file = source.getFile();
      Language _language = _file.getLanguage();
      boolean _notEquals = (!Objects.equal(_language, this.language));
      if (_notEquals) {
        throw NoDataException.INSTANCE;
      }
      Application _application = ApplicationManager.getApplication();
      final Computable<Set<String>> _function = () -> {
        try {
          PsiElement _elementAt = source.getElementAt();
          final VirtualFileInProject sourceResource = VirtualFileInProject.forPsiElement(_elementAt);
          if ((sourceResource == null)) {
            throw NoDataException.INSTANCE;
          }
          final IIdeaTrace trace = this.traceForVirtualFileProvider.getTraceToTarget(sourceResource);
          if ((trace == null)) {
            throw NoDataException.INSTANCE;
          }
          Iterable<? extends ILocationInVirtualFile> _allAssociatedLocations = trace.getAllAssociatedLocations();
          final Function1<ILocationInVirtualFile, String> _function_1 = (ILocationInVirtualFile it) -> {
            SourceRelativeURI _srcRelativeResourceURI = it.getSrcRelativeResourceURI();
            URI _uRI = _srcRelativeResourceURI.getURI();
            return _uRI.lastSegment();
          };
          Iterable<String> _map = IterableExtensions.map(_allAssociatedLocations, _function_1);
          return IterableExtensions.<String>toSet(_map);
        } catch (Throwable _e) {
          throw Exceptions.sneakyThrow(_e);
        }
      };
      final Set<String> names = _application.<Set<String>>runReadAction(_function);
      boolean _isEmpty = names.isEmpty();
      if (_isEmpty) {
        throw NoDataException.INSTANCE;
      }
      RequestManager _requestsManager = this.process.getRequestsManager();
      final ClassPrepareRequestor _function_1 = (DebugProcess process, ReferenceType refType) -> {
        try {
          String _sourceName = refType.sourceName();
          boolean _contains = names.contains(_sourceName);
          if (_contains) {
            requestor.processClassPrepare(process, refType);
          }
        } catch (final Throwable _t) {
          if (_t instanceof AbsentInformationException) {
            final AbsentInformationException ignore = (AbsentInformationException)_t;
          } else {
            throw Exceptions.sneakyThrow(_t);
          }
        }
      };
      return _requestsManager.createClassPrepareRequest(_function_1, "*");
    }
    
    @Override
    public List<ReferenceType> getAllClasses(final SourcePosition source) throws NoDataException {
      PsiFile _file = source.getFile();
      Language _language = _file.getLanguage();
      boolean _notEquals = (!Objects.equal(_language, this.language));
      if (_notEquals) {
        throw NoDataException.INSTANCE;
      }
      final Map<URI, AbstractTraceRegion> traces = this._debugProcessExtensions.getTracesForSource(this.process, source);
      final ArrayList<ReferenceType> allClasses = CollectionLiterals.<ReferenceType>newArrayList();
      final int line = source.getLine();
      Set<Map.Entry<URI, AbstractTraceRegion>> _entrySet = traces.entrySet();
      for (final Map.Entry<URI, AbstractTraceRegion> uri2trace : _entrySet) {
        {
          AbstractTraceRegion _value = uri2trace.getValue();
          TreeIterator<AbstractTraceRegion> _treeIterator = _value.treeIterator();
          final Function1<AbstractTraceRegion, Boolean> _function = (AbstractTraceRegion it) -> {
            List<ILocationData> _associatedLocations = it.getAssociatedLocations();
            ILocationData _head = IterableExtensions.<ILocationData>head(_associatedLocations);
            int _lineNumber = 0;
            if (_head!=null) {
              _lineNumber=_head.getLineNumber();
            }
            return Boolean.valueOf((_lineNumber == line));
          };
          final AbstractTraceRegion region = IteratorExtensions.<AbstractTraceRegion>findFirst(_treeIterator, _function);
          if ((region != null)) {
            URI _key = uri2trace.getKey();
            final PsiFile psiFile = this._debugProcessExtensions.getPsiFile(this.process, _key);
            PositionManagerImpl _javaPositionManger = this._debugProcessExtensions.getJavaPositionManger(this.process);
            int _myLineNumber = region.getMyLineNumber();
            int _plus = (_myLineNumber + 1);
            SourcePosition _createFromLine = SourcePosition.createFromLine(psiFile, _plus);
            final List<ReferenceType> classes = _javaPositionManger.getAllClasses(_createFromLine);
            allClasses.addAll(classes);
          }
        }
      }
      return allClasses;
    }
    
    @Override
    public SourcePosition getSourcePosition(final Location location) throws NoDataException {
      int _lineNumber = location.lineNumber();
      final int line = (_lineNumber - 1);
      final PsiFile psiFile = this._debugProcessExtensions.getPsiFile(this.process, location);
      if ((psiFile == null)) {
        throw NoDataException.INSTANCE;
      }
      SourcePosition _createFromLine = SourcePosition.createFromLine(psiFile, line);
      final AbstractTraceRegion trace = this._debugProcessExtensions.getTraceForJava(_createFromLine);
      if ((trace == null)) {
        throw NoDataException.INSTANCE;
      }
      final URI sourceURI = this._debugProcessExtensions.findOriginalDeclaration(this.process, location);
      SourcePosition fallBack = null;
      SourcePosition secondaryFallBack = null;
      TreeIterator<AbstractTraceRegion> iter = trace.treeIterator();
      while (iter.hasNext()) {
        {
          final AbstractTraceRegion n = iter.next();
          int _myLineNumber = n.getMyLineNumber();
          boolean _tripleEquals = (_myLineNumber == line);
          if (_tripleEquals) {
            final ILocationData mergedAssociatedLocation = n.getMergedAssociatedLocation();
            if (((n.isUseForDebugging() && (n.getMyEndLineNumber() == line)) && (mergedAssociatedLocation.getLineNumber() == mergedAssociatedLocation.getEndLineNumber()))) {
              final PsiFile psi = this._debugProcessExtensions.getPsiFile(this.process, sourceURI);
              int _offset = mergedAssociatedLocation.getOffset();
              return SourcePosition.createFromOffset(psi, _offset);
            } else {
              final PsiFile psi_1 = this._debugProcessExtensions.getPsiFile(this.process, sourceURI);
              int _offset_1 = mergedAssociatedLocation.getOffset();
              SourcePosition _createFromOffset = SourcePosition.createFromOffset(psi_1, _offset_1);
              fallBack = _createFromOffset;
            }
          }
          int _myEndLineNumber = n.getMyEndLineNumber();
          boolean _tripleEquals_1 = (_myEndLineNumber == line);
          if (_tripleEquals_1) {
            final ILocationData mergedAssociatedLocation_1 = n.getMergedAssociatedLocation();
            final PsiFile psi_2 = this._debugProcessExtensions.getPsiFile(this.process, sourceURI);
            int _endLineNumber = mergedAssociatedLocation_1.getEndLineNumber();
            SourcePosition _createFromLine_1 = SourcePosition.createFromLine(psi_2, _endLineNumber);
            secondaryFallBack = _createFromLine_1;
          }
        }
      }
      SourcePosition _elvis = null;
      if (fallBack != null) {
        _elvis = fallBack;
      } else {
        _elvis = secondaryFallBack;
      }
      return _elvis;
    }
    
    @Override
    public List<Location> locationsOfLine(final ReferenceType type, final SourcePosition position) throws NoDataException {
      PsiFile _file = position.getFile();
      Language _language = _file.getLanguage();
      boolean _notEquals = (!Objects.equal(_language, this.language));
      if (_notEquals) {
        throw NoDataException.INSTANCE;
      }
      Application _application = ApplicationManager.getApplication();
      final Computable<List<Location>> _function = () -> {
        try {
          final PsiElement psi = position.getElementAt();
          Project _project = psi.getProject();
          PsiDocumentManager _instance = PsiDocumentManager.getInstance(_project);
          PsiFile _containingFile = psi.getContainingFile();
          final Document document = _instance.getDocument(_containingFile);
          int _line = position.getLine();
          final TextRange range = DocumentUtil.getLineTextRange(document, _line);
          final VirtualFileInProject sourceResource = VirtualFileInProject.forPsiElement(psi);
          if ((sourceResource == null)) {
            throw NoDataException.INSTANCE;
          }
          final IIdeaTrace traceToTarget = this.traceForVirtualFileProvider.getTraceToTarget(sourceResource);
          if ((traceToTarget == null)) {
            throw NoDataException.INSTANCE;
          }
          final ArrayList<Location> result = CollectionLiterals.<Location>newArrayList();
          int _startOffset = range.getStartOffset();
          int _length = range.getLength();
          TextRegion _textRegion = new TextRegion(_startOffset, _length);
          Iterable<? extends ILocationInVirtualFile> _allAssociatedLocations = traceToTarget.getAllAssociatedLocations(_textRegion);
          final Function1<ILocationInVirtualFile, Integer> _function_1 = (ILocationInVirtualFile it) -> {
            ITextRegionWithLineInformation _textRegion_1 = it.getTextRegion();
            return Integer.valueOf(_textRegion_1.getLineNumber());
          };
          List<? extends ILocationInVirtualFile> _sortBy = IterableExtensions.sortBy(_allAssociatedLocations, _function_1);
          for (final ILocationInVirtualFile location : _sortBy) {
            if ((Objects.equal(type.sourceName(), location.getSrcRelativeResourceURI().getURI().lastSegment().toString()) && (location.getTextRegion().getLineNumber() == location.getTextRegion().getEndLineNumber()))) {
              ITextRegionWithLineInformation _textRegion_1 = location.getTextRegion();
              int _lineNumber = _textRegion_1.getLineNumber();
              int _plus = (_lineNumber + 1);
              final List<Location> locationsOfLine = type.locationsOfLine(_plus);
              boolean _isEmpty = locationsOfLine.isEmpty();
              boolean _not = (!_isEmpty);
              if (_not) {
                result.addAll(locationsOfLine);
                return result;
              }
            }
          }
          throw NoDataException.INSTANCE;
        } catch (Throwable _e) {
          throw Exceptions.sneakyThrow(_e);
        }
      };
      return _application.<List<Location>>runReadAction(_function);
    }
  }
  
  private IXtextLanguage language;
  
  public TraceBasedPositionManagerFactory(final IXtextLanguage language) {
    this.language = language;
  }
  
  @Override
  public PositionManager createPositionManager(final DebugProcess process) {
    final TraceBasedPositionManagerFactory.TraceBasedPositionManager manager = new TraceBasedPositionManagerFactory.TraceBasedPositionManager(process, this.language);
    this.language.injectMembers(manager);
    return manager;
  }
}
