/**
 * Copyright (c) 2015 itemis AG (http://www.itemis.eu) and others.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 */
package org.eclipse.xtext.idea.resource;

import com.intellij.openapi.application.ApplicationManager;
import com.intellij.openapi.vfs.VirtualFile;
import com.intellij.openapi.vfs.VirtualFileManager;
import com.intellij.openapi.vfs.encoding.EncodingRegistry;
import java.nio.charset.Charset;
import org.eclipse.emf.common.util.URI;
import org.eclipse.xtext.idea.resource.VirtualFileURIUtil;
import org.eclipse.xtext.parser.IEncodingProvider;

@SuppressWarnings("all")
public class IdeaEncodingProvider implements IEncodingProvider {
  @Override
  public String getEncoding(final URI uri) {
    final VirtualFileManager fileManager = ApplicationManager.getApplication().<VirtualFileManager>getComponent(VirtualFileManager.class);
    if ((fileManager == null)) {
      return new IEncodingProvider.Runtime().getEncoding(uri);
    }
    String _elvis = null;
    Charset _charset = this.getCharset(uri);
    String _name = null;
    if (_charset!=null) {
      _name=_charset.name();
    }
    if (_name != null) {
      _elvis = _name;
    } else {
      _elvis = "UTF-8";
    }
    return _elvis;
  }
  
  protected Charset getCharset(final URI uri) {
    Charset _elvis = null;
    VirtualFile _findVirtualFile = this.findVirtualFile(uri);
    Charset _charset = null;
    if (_findVirtualFile!=null) {
      _charset=_findVirtualFile.getCharset();
    }
    if (_charset != null) {
      _elvis = _charset;
    } else {
      Charset _defaultCharset = EncodingRegistry.getInstance().getDefaultCharset();
      _elvis = _defaultCharset;
    }
    return _elvis;
  }
  
  protected VirtualFile findVirtualFile(final URI uri) {
    VirtualFile _xblockexpression = null;
    {
      if ((uri == null)) {
        return null;
      }
      final VirtualFile file = VirtualFileURIUtil.getVirtualFile(uri);
      if ((file != null)) {
        return file;
      }
      int _segmentCount = uri.segmentCount();
      boolean _equals = (_segmentCount == 0);
      if (_equals) {
        return null;
      }
      _xblockexpression = this.findVirtualFile(uri.trimSegments(1));
    }
    return _xblockexpression;
  }
}
