/**
 * Copyright (c) 2015 itemis AG (http://www.itemis.eu) and others.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 */
package org.eclipse.xtext.idea.editorActions;

import com.intellij.codeInsight.editorActions.BackspaceHandlerDelegate;
import com.intellij.openapi.editor.Editor;
import com.intellij.openapi.editor.ex.EditorEx;
import com.intellij.psi.PsiFile;
import org.eclipse.xtext.idea.editorActions.IdeaAutoEditHandler;
import org.eclipse.xtext.idea.editorActions.IdeaAutoEditHandlerExtension;

/**
 * @author kosyakov - Initial contribution and API
 */
@SuppressWarnings("all")
public class XtextAutoEditBackspaceHandler extends BackspaceHandlerDelegate {
  @Override
  public void beforeCharDeleted(final char c, final PsiFile file, final Editor editor) {
    final IdeaAutoEditHandler autoEditHandler = IdeaAutoEditHandlerExtension.INSTANCE.forLanguage(file.getLanguage());
    if ((autoEditHandler != null)) {
      autoEditHandler.beforeCharDeleted(c, file, ((EditorEx) editor));
    }
  }
  
  @Override
  public boolean charDeleted(final char c, final PsiFile file, final Editor editor) {
    boolean _xblockexpression = false;
    {
      final IdeaAutoEditHandler autoEditHandler = IdeaAutoEditHandlerExtension.INSTANCE.forLanguage(file.getLanguage());
      boolean _xifexpression = false;
      if ((autoEditHandler != null)) {
        _xifexpression = autoEditHandler.charDeleted(c, file, ((EditorEx) editor));
      } else {
        _xifexpression = false;
      }
      _xblockexpression = _xifexpression;
    }
    return _xblockexpression;
  }
}
