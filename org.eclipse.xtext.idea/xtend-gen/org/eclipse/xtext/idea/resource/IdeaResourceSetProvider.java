/**
 * Copyright (c) 2015 itemis AG (http://www.itemis.eu) and others.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 */
package org.eclipse.xtext.idea.resource;

import com.google.common.base.Objects;
import com.google.common.collect.Iterables;
import com.google.inject.Inject;
import com.google.inject.Provider;
import com.google.inject.Singleton;
import com.intellij.openapi.application.ApplicationManager;
import com.intellij.openapi.editor.Document;
import com.intellij.openapi.fileEditor.FileDocumentManager;
import com.intellij.openapi.module.Module;
import com.intellij.openapi.util.Computable;
import com.intellij.openapi.vfs.VirtualFile;
import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.nio.charset.Charset;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;
import org.apache.log4j.Logger;
import org.eclipse.emf.common.notify.Notifier;
import org.eclipse.emf.common.util.EList;
import org.eclipse.emf.common.util.URI;
import org.eclipse.emf.ecore.resource.URIConverter;
import org.eclipse.emf.ecore.resource.URIHandler;
import org.eclipse.xtend.lib.annotations.Accessors;
import org.eclipse.xtext.EcoreUtil2;
import org.eclipse.xtext.idea.build.XtextAutoBuilderComponent;
import org.eclipse.xtext.idea.common.types.StubTypeProviderFactory;
import org.eclipse.xtext.idea.resource.IdeaClasspathURIResolver;
import org.eclipse.xtext.idea.resource.ProjectDescriptionProvider;
import org.eclipse.xtext.idea.resource.VirtualFileURIUtil;
import org.eclipse.xtext.resource.XtextResourceSet;
import org.eclipse.xtext.resource.impl.ProjectDescription;
import org.eclipse.xtext.util.LazyStringInputStream;
import org.eclipse.xtext.util.internal.Log;
import org.eclipse.xtext.xbase.lib.CollectionLiterals;
import org.eclipse.xtext.xbase.lib.Conversions;
import org.eclipse.xtext.xbase.lib.Exceptions;
import org.eclipse.xtext.xbase.lib.Functions.Function1;
import org.eclipse.xtext.xbase.lib.IterableExtensions;
import org.eclipse.xtext.xbase.lib.ListExtensions;
import org.eclipse.xtext.xbase.lib.Pure;

@Singleton
@Log
@SuppressWarnings("all")
public class IdeaResourceSetProvider {
  public static class VirtualFileBasedUriHandler implements URIHandler {
    @Accessors
    public static class ContentDescriptor {
      private byte[] content;
      
      private long timeStamp;
      
      @Pure
      public byte[] getContent() {
        return this.content;
      }
      
      public void setContent(final byte[] content) {
        this.content = content;
      }
      
      @Pure
      public long getTimeStamp() {
        return this.timeStamp;
      }
      
      public void setTimeStamp(final long timeStamp) {
        this.timeStamp = timeStamp;
      }
    }
    
    public static IdeaResourceSetProvider.VirtualFileBasedUriHandler find(final Notifier notifier) {
      return IterableExtensions.<IdeaResourceSetProvider.VirtualFileBasedUriHandler>head(Iterables.<IdeaResourceSetProvider.VirtualFileBasedUriHandler>filter(EcoreUtil2.getResourceSet(notifier).getURIConverter().getURIHandlers(), IdeaResourceSetProvider.VirtualFileBasedUriHandler.class));
    }
    
    @Accessors
    private Map<URI, IdeaResourceSetProvider.VirtualFileBasedUriHandler.ContentDescriptor> writtenContents = CollectionLiterals.<URI, IdeaResourceSetProvider.VirtualFileBasedUriHandler.ContentDescriptor>newHashMap();
    
    @Accessors
    private Set<URI> deleted = CollectionLiterals.<URI>newHashSet();
    
    @Override
    public boolean canHandle(final URI uri) {
      return true;
    }
    
    public void flushToDisk() {
      boolean _isDebugEnabled = IdeaResourceSetProvider.LOG.isDebugEnabled();
      if (_isDebugEnabled) {
        String _join = IterableExtensions.join(this.writtenContents.keySet(), ", ");
        String _plus = ("writing : " + _join);
        IdeaResourceSetProvider.LOG.debug(_plus);
        String _join_1 = IterableExtensions.join(this.deleted, ", ");
        String _plus_1 = ("deleting: " + _join_1);
        IdeaResourceSetProvider.LOG.debug(_plus_1);
      }
      final Map<URI, IdeaResourceSetProvider.VirtualFileBasedUriHandler.ContentDescriptor> localWritten = this.writtenContents;
      this.writtenContents = CollectionLiterals.<URI, IdeaResourceSetProvider.VirtualFileBasedUriHandler.ContentDescriptor>newHashMap();
      final Set<URI> localDeleted = this.deleted;
      this.deleted = CollectionLiterals.<URI>newHashSet();
      if ((localDeleted.isEmpty() && localWritten.isEmpty())) {
        return;
      }
      final Runnable _function = () -> {
        try {
          if ((!XtextAutoBuilderComponent.TEST_MODE)) {
            FileDocumentManager.getInstance().saveAllDocuments();
          }
          Set<URI> _keySet = localWritten.keySet();
          for (final URI uri : _keySet) {
            {
              VirtualFile file = VirtualFileURIUtil.getOrCreateVirtualFile(uri);
              final IdeaResourceSetProvider.VirtualFileBasedUriHandler.ContentDescriptor contentDescriptor = localWritten.get(uri);
              final byte[] newContent = contentDescriptor.content;
              final byte[] oldContent = file.contentsToByteArray();
              boolean _equals = Arrays.equals(newContent, oldContent);
              boolean _not = (!_equals);
              if (_not) {
                file.setBinaryContent(newContent, (-1), contentDescriptor.timeStamp, this.getRequestor());
              }
            }
          }
          for (final URI uri_1 : localDeleted) {
            {
              final VirtualFile file = VirtualFileURIUtil.getVirtualFile(uri_1);
              if (((file != null) && file.exists())) {
                file.delete(this.getRequestor());
              }
            }
          }
        } catch (Throwable _e) {
          throw Exceptions.sneakyThrow(_e);
        }
      };
      ApplicationManager.getApplication().runWriteAction(_function);
    }
    
    @Override
    public Map<String, ?> contentDescription(final URI uri, final Map<?, ?> options) throws IOException {
      return CollectionLiterals.<String, Object>emptyMap();
    }
    
    @Override
    public InputStream createInputStream(final URI uri, final Map<?, ?> options) throws IOException {
      boolean _contains = this.deleted.contains(uri);
      if (_contains) {
        throw new IOException((("resource " + uri) + " is deleted."));
      }
      boolean _containsKey = this.writtenContents.containsKey(uri);
      if (_containsKey) {
        return new ByteArrayInputStream(this.writtenContents.get(uri).content);
      }
      final VirtualFile virtualFile = VirtualFileURIUtil.getVirtualFile(uri);
      if ((virtualFile == null)) {
        throw new FileNotFoundException(("Couldn\'t find virtual file for " + uri));
      }
      final Document cachedDocument = FileDocumentManager.getInstance().getCachedDocument(virtualFile);
      if ((cachedDocument != null)) {
        String _text = cachedDocument.getText();
        Charset _charset = virtualFile.getCharset();
        return new LazyStringInputStream(_text, _charset);
      }
      final Computable<InputStream> _function = () -> {
        try {
          byte[] _contentsToByteArray = virtualFile.contentsToByteArray();
          return new ByteArrayInputStream(_contentsToByteArray);
        } catch (Throwable _e) {
          throw Exceptions.sneakyThrow(_e);
        }
      };
      return ApplicationManager.getApplication().<InputStream>runReadAction(_function);
    }
    
    @Override
    public OutputStream createOutputStream(final URI uri, final Map<?, ?> options) throws IOException {
      return new ByteArrayOutputStream() {
        @Override
        public void close() throws IOException {
          super.close();
          final byte[] bytes = this.toByteArray();
          VirtualFileBasedUriHandler.this.deleted.remove(uri);
          final IdeaResourceSetProvider.VirtualFileBasedUriHandler.ContentDescriptor contentDescriptor = new IdeaResourceSetProvider.VirtualFileBasedUriHandler.ContentDescriptor();
          contentDescriptor.content = bytes;
          contentDescriptor.timeStamp = System.currentTimeMillis();
          VirtualFileBasedUriHandler.this.writtenContents.put(uri, contentDescriptor);
        }
      };
    }
    
    @Override
    public void delete(final URI uri, final Map<?, ?> options) throws IOException {
      this.writtenContents.remove(uri);
      this.deleted.add(uri);
    }
    
    public Object getRequestor() {
      return null;
    }
    
    @Override
    public boolean exists(final URI uri, final Map<?, ?> options) {
      boolean _contains = this.deleted.contains(uri);
      if (_contains) {
        return false;
      }
      boolean _containsKey = this.writtenContents.containsKey(uri);
      if (_containsKey) {
        return true;
      }
      IdeaResourceSetProvider.VirtualFileBasedUriHandler.ContentDescriptor _folderDescriptor = this.getFolderDescriptor(uri);
      boolean _tripleNotEquals = (_folderDescriptor != null);
      if (_tripleNotEquals) {
        return true;
      }
      VirtualFile _virtualFile = VirtualFileURIUtil.getVirtualFile(uri);
      boolean _exists = false;
      if (_virtualFile!=null) {
        _exists=_virtualFile.exists();
      }
      return _exists;
    }
    
    @Override
    public Map<String, ?> getAttributes(final URI uri, final Map<?, ?> options) {
      boolean _contains = this.deleted.contains(uri);
      if (_contains) {
        return CollectionLiterals.<String, Object>emptyMap();
      }
      Object _get = options.get(URIConverter.OPTION_REQUESTED_ATTRIBUTES);
      final Set<String> requestedAttributes = ((Set<String>) _get);
      if (((requestedAttributes == null) || requestedAttributes.isEmpty())) {
        return CollectionLiterals.<String, Object>emptyMap();
      }
      final IdeaResourceSetProvider.VirtualFileBasedUriHandler.ContentDescriptor fileDescriptor = this.writtenContents.get(uri);
      if ((fileDescriptor != null)) {
        final HashMap<String, Object> attributes = CollectionLiterals.<String, Object>newHashMap();
        boolean _contains_1 = requestedAttributes.contains(URIConverter.ATTRIBUTE_DIRECTORY);
        if (_contains_1) {
          attributes.put(URIConverter.ATTRIBUTE_DIRECTORY, Boolean.valueOf(false));
        }
        boolean _contains_2 = requestedAttributes.contains(URIConverter.ATTRIBUTE_TIME_STAMP);
        if (_contains_2) {
          attributes.put(URIConverter.ATTRIBUTE_TIME_STAMP, Long.valueOf(fileDescriptor.timeStamp));
        }
        return attributes;
      }
      final IdeaResourceSetProvider.VirtualFileBasedUriHandler.ContentDescriptor folderDescriptor = this.getFolderDescriptor(uri);
      if ((folderDescriptor != null)) {
        final HashMap<String, Object> attributes_1 = CollectionLiterals.<String, Object>newHashMap();
        boolean _contains_3 = requestedAttributes.contains(URIConverter.ATTRIBUTE_DIRECTORY);
        if (_contains_3) {
          attributes_1.put(URIConverter.ATTRIBUTE_DIRECTORY, Boolean.valueOf(true));
        }
        boolean _contains_4 = requestedAttributes.contains(URIConverter.ATTRIBUTE_TIME_STAMP);
        if (_contains_4) {
          attributes_1.put(URIConverter.ATTRIBUTE_TIME_STAMP, Long.valueOf(folderDescriptor.timeStamp));
        }
        return attributes_1;
      }
      final VirtualFile file = VirtualFileURIUtil.getVirtualFile(uri);
      if ((file != null)) {
        final HashMap<String, Object> attributes_2 = CollectionLiterals.<String, Object>newHashMap();
        boolean _contains_5 = requestedAttributes.contains(URIConverter.ATTRIBUTE_DIRECTORY);
        if (_contains_5) {
          attributes_2.put(URIConverter.ATTRIBUTE_DIRECTORY, Boolean.valueOf(file.isDirectory()));
        }
        boolean _contains_6 = requestedAttributes.contains(URIConverter.ATTRIBUTE_TIME_STAMP);
        if (_contains_6) {
          attributes_2.put(URIConverter.ATTRIBUTE_TIME_STAMP, Long.valueOf(file.getTimeStamp()));
        }
        return attributes_2;
      }
      return CollectionLiterals.<String, Object>emptyMap();
    }
    
    protected IdeaResourceSetProvider.VirtualFileBasedUriHandler.ContentDescriptor getFolderDescriptor(final URI uri) {
      final Function1<Map.Entry<URI, IdeaResourceSetProvider.VirtualFileBasedUriHandler.ContentDescriptor>, Boolean> _function = (Map.Entry<URI, IdeaResourceSetProvider.VirtualFileBasedUriHandler.ContentDescriptor> fileDescriptor) -> {
        boolean _xblockexpression = false;
        {
          final URI relativeURI = fileDescriptor.getKey().deresolve(uri);
          URI _key = fileDescriptor.getKey();
          _xblockexpression = (!Objects.equal(relativeURI, _key));
        }
        return Boolean.valueOf(_xblockexpression);
      };
      Map.Entry<URI, IdeaResourceSetProvider.VirtualFileBasedUriHandler.ContentDescriptor> _head = IterableExtensions.<Map.Entry<URI, IdeaResourceSetProvider.VirtualFileBasedUriHandler.ContentDescriptor>>head(IterableExtensions.<Map.Entry<URI, IdeaResourceSetProvider.VirtualFileBasedUriHandler.ContentDescriptor>>filter(this.writtenContents.entrySet(), _function));
      IdeaResourceSetProvider.VirtualFileBasedUriHandler.ContentDescriptor _value = null;
      if (_head!=null) {
        _value=_head.getValue();
      }
      return _value;
    }
    
    @Override
    public void setAttributes(final URI uri, final Map<String, ?> attributes, final Map<?, ?> options) throws IOException {
    }
    
    public Set<URI> getChildren(final URI uri) {
      Set<URI> _xblockexpression = null;
      {
        final VirtualFile file = VirtualFileURIUtil.getVirtualFile(uri);
        Set<URI> _xifexpression = null;
        if ((file != null)) {
          final Function1<VirtualFile, URI> _function = (VirtualFile it) -> {
            return VirtualFileURIUtil.getURI(it);
          };
          _xifexpression = IterableExtensions.<URI>toSet(ListExtensions.<VirtualFile, URI>map(((List<VirtualFile>)Conversions.doWrapArray(file.getChildren())), _function));
        } else {
          _xifexpression = CollectionLiterals.<URI>newLinkedHashSet();
        }
        final Set<URI> children = _xifexpression;
        final Function1<URI, URI> _function_1 = (URI uriToWrite) -> {
          URI _xblockexpression_1 = null;
          {
            final URI relativeURI = uriToWrite.deresolve(uri);
            URI _xifexpression_1 = null;
            if (((!relativeURI.isEmpty()) && (!Objects.equal(relativeURI, uriToWrite)))) {
              int _segmentCount = relativeURI.segmentCount();
              int _minus = (_segmentCount - 1);
              _xifexpression_1 = relativeURI.trimSegments(_minus).resolve(uri);
            }
            _xblockexpression_1 = _xifexpression_1;
          }
          return _xblockexpression_1;
        };
        Iterable<URI> _filterNull = IterableExtensions.<URI>filterNull(IterableExtensions.<URI, URI>map(this.writtenContents.keySet(), _function_1));
        Iterables.<URI>addAll(children, _filterNull);
        Iterables.removeAll(children, this.deleted);
        _xblockexpression = children;
      }
      return _xblockexpression;
    }
    
    @Pure
    public Map<URI, IdeaResourceSetProvider.VirtualFileBasedUriHandler.ContentDescriptor> getWrittenContents() {
      return this.writtenContents;
    }
    
    public void setWrittenContents(final Map<URI, IdeaResourceSetProvider.VirtualFileBasedUriHandler.ContentDescriptor> writtenContents) {
      this.writtenContents = writtenContents;
    }
    
    @Pure
    public Set<URI> getDeleted() {
      return this.deleted;
    }
    
    public void setDeleted(final Set<URI> deleted) {
      this.deleted = deleted;
    }
  }
  
  @Inject
  private Provider<XtextResourceSet> resourceSetProvider;
  
  @Inject
  private StubTypeProviderFactory stubTypeProviderFactory;
  
  @Inject
  private ProjectDescriptionProvider projectDescriptionProvider;
  
  @Inject
  private Provider<IdeaClasspathURIResolver> classpathURIResolverProvider;
  
  public XtextResourceSet get(final Module module) {
    final XtextResourceSet resourceSet = this.resourceSetProvider.get();
    resourceSet.setClasspathURIContext(module);
    resourceSet.setClasspathUriResolver(this.classpathURIResolverProvider.get());
    resourceSet.getURIConverter().getURIHandlers().clear();
    EList<URIHandler> _uRIHandlers = resourceSet.getURIConverter().getURIHandlers();
    IdeaResourceSetProvider.VirtualFileBasedUriHandler _virtualFileBasedUriHandler = new IdeaResourceSetProvider.VirtualFileBasedUriHandler();
    _uRIHandlers.add(_virtualFileBasedUriHandler);
    final ProjectDescription desc = this.projectDescriptionProvider.getProjectDescription(module);
    desc.attachToEmfObject(resourceSet);
    final XtextAutoBuilderComponent builder = module.getProject().<XtextAutoBuilderComponent>getComponent(XtextAutoBuilderComponent.class);
    builder.installCopyOfResourceDescriptions(resourceSet);
    this.stubTypeProviderFactory.createTypeProvider(resourceSet);
    return resourceSet;
  }
  
  private final static Logger LOG = Logger.getLogger(IdeaResourceSetProvider.class);
}
