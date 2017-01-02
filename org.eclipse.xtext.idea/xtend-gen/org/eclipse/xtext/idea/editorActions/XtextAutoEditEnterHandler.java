/**
 * Copyright (c) 2015 itemis AG (http://www.itemis.eu) and others.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 */
package org.eclipse.xtext.idea.editorActions;

import com.google.common.base.Objects;
import com.intellij.codeInsight.editorActions.enter.EnterHandlerDelegate;
import com.intellij.codeInsight.editorActions.enter.EnterHandlerDelegateAdapter;
import com.intellij.openapi.actionSystem.DataContext;
import com.intellij.openapi.editor.Editor;
import com.intellij.openapi.editor.actionSystem.EditorActionHandler;
import com.intellij.openapi.editor.ex.EditorEx;
import com.intellij.openapi.util.Ref;
import com.intellij.psi.PsiFile;
import org.eclipse.xtext.idea.editorActions.IdeaAutoEditHandler;
import org.eclipse.xtext.idea.editorActions.IdeaAutoEditHandlerExtension;

/**
 * @author kosyakov - Initial contribution and API
 */
@SuppressWarnings("all")
public class XtextAutoEditEnterHandler extends EnterHandlerDelegateAdapter {
  @Override
  public EnterHandlerDelegate.Result preprocessEnter(final PsiFile file, final Editor editor, final Ref<Integer> caretOffset, final Ref<Integer> caretAdvance, final DataContext dataContext, final EditorActionHandler originalHandler) {
    final IdeaAutoEditHandler autoEditHandler = IdeaAutoEditHandlerExtension.INSTANCE.forLanguage(file.getLanguage());
    boolean _equals = Objects.equal(autoEditHandler, null);
    if (_equals) {
      return super.preprocessEnter(file, editor, caretOffset, caretAdvance, dataContext, originalHandler);
    }
    return this.translateResult(autoEditHandler.beforeEnterTyped(file, 
      ((EditorEx) editor), caretOffset, caretAdvance, dataContext, originalHandler));
  }
  
  @Override
  public EnterHandlerDelegate.Result postProcessEnter(final PsiFile file, final Editor editor, final DataContext dataContext) {
    final IdeaAutoEditHandler autoEditHandler = IdeaAutoEditHandlerExtension.INSTANCE.forLanguage(file.getLanguage());
    boolean _equals = Objects.equal(autoEditHandler, null);
    if (_equals) {
      return super.postProcessEnter(file, editor, dataContext);
    }
    return this.translateResult(autoEditHandler.enterTyped(file, ((EditorEx) editor), dataContext));
  }
  
  protected EnterHandlerDelegate.Result translateResult(final IdeaAutoEditHandler.Result result) {
    EnterHandlerDelegate.Result _switchResult = null;
    if (result != null) {
      switch (result) {
        case DEFAULT:
          _switchResult = EnterHandlerDelegate.Result.Default;
          break;
        case CONTINUE:
          _switchResult = EnterHandlerDelegate.Result.Continue;
          break;
        case STOP:
          _switchResult = EnterHandlerDelegate.Result.Stop;
          break;
        default:
          break;
      }
    }
    return _switchResult;
  }
}
