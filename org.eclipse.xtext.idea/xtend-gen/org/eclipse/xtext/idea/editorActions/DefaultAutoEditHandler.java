/**
 * Copyright (c) 2015 itemis AG (http://www.itemis.eu) and others.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 */
package org.eclipse.xtext.idea.editorActions;

import com.google.common.base.Objects;
import com.google.inject.Inject;
import com.google.inject.Singleton;
import com.intellij.codeInsight.CodeInsightSettings;
import com.intellij.openapi.actionSystem.DataContext;
import com.intellij.openapi.editor.EditorModificationUtil;
import com.intellij.openapi.editor.actionSystem.EditorActionHandler;
import com.intellij.openapi.editor.ex.EditorEx;
import com.intellij.openapi.fileTypes.FileType;
import com.intellij.openapi.project.Project;
import com.intellij.openapi.util.Ref;
import com.intellij.psi.PsiFile;
import com.intellij.psi.tree.TokenSet;
import com.intellij.util.text.CharArrayUtil;
import org.eclipse.xtext.idea.editorActions.AbstractAutoEditBlock;
import org.eclipse.xtext.idea.editorActions.AbstractIndentableAutoEditBlock;
import org.eclipse.xtext.idea.editorActions.AutoEditBlockProvider;
import org.eclipse.xtext.idea.editorActions.AutoEditBlockRegion;
import org.eclipse.xtext.idea.editorActions.AutoEditContext;
import org.eclipse.xtext.idea.editorActions.IdeaAutoEditHandler;
import org.eclipse.xtext.idea.editorActions.TokenSetProvider;
import org.eclipse.xtext.xbase.lib.Extension;
import org.eclipse.xtext.xbase.lib.Functions.Function1;
import org.eclipse.xtext.xbase.lib.Functions.Function2;
import org.eclipse.xtext.xbase.lib.IterableExtensions;

/**
 * @author kosyakov - Initial contribution and API
 */
@Singleton
@SuppressWarnings("all")
public class DefaultAutoEditHandler extends IdeaAutoEditHandler {
  @Inject
  @Extension
  private TokenSetProvider tokenSetProvider;
  
  @Inject
  @Extension
  private AutoEditBlockProvider blockProvider;
  
  protected Iterable<AbstractIndentableAutoEditBlock> getBlocks(final EditorEx editor) {
    return this.blockProvider.getBlocks(this.tokenSetProvider.getTokenSet(editor, editor.getCaretModel().getOffset()));
  }
  
  @Override
  public IdeaAutoEditHandler.Result beforeEnterTyped(final PsiFile file, final EditorEx editor, final Ref<Integer> caretOffset, final Ref<Integer> caretAdvance, final DataContext dataContext, final EditorActionHandler originalHandler) {
    final AutoEditContext context = new AutoEditContext(editor, this.tokenSetProvider);
    final AutoEditBlockRegion region = this.findBlockRegion(context);
    boolean _equals = Objects.equal(region, null);
    if (_equals) {
      return IdeaAutoEditHandler.Result.CONTINUE;
    }
    return this.handleIndentation(region, context);
  }
  
  protected final static String WHITESPACE_CHARACTERS = " \t";
  
  protected IdeaAutoEditHandler.Result handleIndentation(final AutoEditBlockRegion region, @Extension final AutoEditContext context) {
    final String previousLineIndentation = this.getPreviousLineIndentaiton(context);
    final String blockIndentaion = this.indentBlock(region, previousLineIndentation, context);
    final String string = context.newLine((previousLineIndentation + blockIndentaion));
    final int cursorShift = string.length();
    EditorModificationUtil.insertStringAtCaret(context.getEditor(), string, false, false);
    EditorModificationUtil.moveCaretRelatively(context.getEditor(), cursorShift);
    return IdeaAutoEditHandler.Result.STOP;
  }
  
  protected String indentBlock(final AutoEditBlockRegion region, final String previousLineIndentation, final AutoEditContext context) {
    boolean _shouldIndentBlock = this.shouldIndentBlock(region, previousLineIndentation, context);
    if (_shouldIndentBlock) {
      return region.getBlock().indent(region, previousLineIndentation, context);
    }
    return "";
  }
  
  protected boolean shouldIndentBlock(final AutoEditBlockRegion region, final String previousLineIndentation, final AutoEditContext context) {
    return ((CodeInsightSettings.getInstance().INSERT_BRACE_ON_ENTER && Objects.equal(region.getBlock().getClosingTerminal(), "}")) || 
      CodeInsightSettings.getInstance().AUTOINSERT_PAIR_BRACKET);
  }
  
  protected String getPreviousLineIndentaiton(@Extension final AutoEditContext context) {
    String _xblockexpression = null;
    {
      boolean _shouldIndent = this.shouldIndent(context);
      boolean _not = (!_shouldIndent);
      if (_not) {
        return "";
      }
      final int lineNumber = context.getDocument().getLineNumber(context.getCaretOffset());
      final int lineStartOffset = context.getDocument().getLineStartOffset(lineNumber);
      final int textStartOffset = CharArrayUtil.shiftForward(
        context.getDocument().getText(), lineStartOffset, 
        context.getCaretOffset(), 
        DefaultAutoEditHandler.WHITESPACE_CHARACTERS);
      String _xifexpression = null;
      if ((textStartOffset > lineStartOffset)) {
        _xifexpression = context.getText(lineStartOffset, textStartOffset);
      } else {
        _xifexpression = "";
      }
      _xblockexpression = _xifexpression;
    }
    return _xblockexpression;
  }
  
  protected boolean shouldIndent(@Extension final AutoEditContext context) {
    TokenSet _tokenSet = context.getTokenSet(context.getCaretOffset());
    TokenSet _stringLiteralTokens = this.tokenSetProvider.getStringLiteralTokens();
    return (!Objects.equal(_tokenSet, _stringLiteralTokens));
  }
  
  protected AutoEditBlockRegion findBlockRegion(@Extension final AutoEditContext context) {
    final Function1<AbstractIndentableAutoEditBlock, AutoEditBlockRegion> _function = (AbstractIndentableAutoEditBlock it) -> {
      return it.findRegion(context);
    };
    final Function2<AutoEditBlockRegion, AutoEditBlockRegion, AutoEditBlockRegion> _function_1 = (AutoEditBlockRegion $0, AutoEditBlockRegion $1) -> {
      AutoEditBlockRegion _xifexpression = null;
      int _offset = $0.getOpeningTerminal().getOffset();
      int _offset_1 = $1.getOpeningTerminal().getOffset();
      boolean _lessThan = (_offset < _offset_1);
      if (_lessThan) {
        _xifexpression = $1;
      } else {
        _xifexpression = $0;
      }
      return _xifexpression;
    };
    return IterableExtensions.<AutoEditBlockRegion>reduce(IterableExtensions.<AutoEditBlockRegion>filterNull(IterableExtensions.<AbstractIndentableAutoEditBlock, AutoEditBlockRegion>map(this.getBlocks(context.getEditor()), _function)), _function_1);
  }
  
  @Override
  public IdeaAutoEditHandler.Result beforeCharTyped(final char c, final Project project, final EditorEx editor, final PsiFile file, final FileType fileType) {
    final AutoEditContext context = new AutoEditContext(editor, this.tokenSetProvider);
    if (CodeInsightSettings.getInstance().AUTOINSERT_PAIR_BRACKET) {
      final Function1<AbstractIndentableAutoEditBlock, IdeaAutoEditHandler.Result> _function = (AbstractIndentableAutoEditBlock it) -> {
        return this.closeBlock(it, c, context);
      };
      Iterable<IdeaAutoEditHandler.Result> _map = IterableExtensions.<AbstractIndentableAutoEditBlock, IdeaAutoEditHandler.Result>map(this.getBlocks(editor), _function);
      for (final IdeaAutoEditHandler.Result result : _map) {
        if ((Objects.equal(result, IdeaAutoEditHandler.Result.DEFAULT) || Objects.equal(result, IdeaAutoEditHandler.Result.STOP))) {
          return result;
        }
      }
    }
    if (CodeInsightSettings.getInstance().AUTOINSERT_PAIR_QUOTE) {
      final Function1<AbstractAutoEditBlock, IdeaAutoEditHandler.Result> _function_1 = (AbstractAutoEditBlock it) -> {
        return this.closeBlock(it, c, context);
      };
      Iterable<IdeaAutoEditHandler.Result> _map_1 = IterableExtensions.<AbstractAutoEditBlock, IdeaAutoEditHandler.Result>map(this.blockProvider.getQuotes(), _function_1);
      for (final IdeaAutoEditHandler.Result result_1 : _map_1) {
        if ((Objects.equal(result_1, IdeaAutoEditHandler.Result.DEFAULT) || Objects.equal(result_1, IdeaAutoEditHandler.Result.STOP))) {
          return result_1;
        }
      }
    }
    if (CodeInsightSettings.getInstance().AUTOINSERT_PAIR_BRACKET) {
      final Function1<AbstractIndentableAutoEditBlock, IdeaAutoEditHandler.Result> _function_2 = (AbstractIndentableAutoEditBlock it) -> {
        return this.openBlock(it, c, context);
      };
      Iterable<IdeaAutoEditHandler.Result> _map_2 = IterableExtensions.<AbstractIndentableAutoEditBlock, IdeaAutoEditHandler.Result>map(this.getBlocks(editor), _function_2);
      for (final IdeaAutoEditHandler.Result result_2 : _map_2) {
        if ((Objects.equal(result_2, IdeaAutoEditHandler.Result.DEFAULT) || Objects.equal(result_2, IdeaAutoEditHandler.Result.STOP))) {
          return result_2;
        }
      }
    }
    if (CodeInsightSettings.getInstance().AUTOINSERT_PAIR_QUOTE) {
      final Function1<AbstractAutoEditBlock, IdeaAutoEditHandler.Result> _function_3 = (AbstractAutoEditBlock it) -> {
        return this.openBlock(it, c, context);
      };
      Iterable<IdeaAutoEditHandler.Result> _map_3 = IterableExtensions.<AbstractAutoEditBlock, IdeaAutoEditHandler.Result>map(this.blockProvider.getQuotes(), _function_3);
      for (final IdeaAutoEditHandler.Result result_3 : _map_3) {
        if ((Objects.equal(result_3, IdeaAutoEditHandler.Result.DEFAULT) || Objects.equal(result_3, IdeaAutoEditHandler.Result.STOP))) {
          return result_3;
        }
      }
    }
    return super.beforeCharTyped(c, project, editor, file, fileType);
  }
  
  @Override
  public boolean charDeleted(final char c, final PsiFile file, final EditorEx editor) {
    final AutoEditContext context = new AutoEditContext(editor, this.tokenSetProvider);
    if ((CodeInsightSettings.getInstance().AUTOINSERT_PAIR_BRACKET && IterableExtensions.<AbstractIndentableAutoEditBlock>exists(this.getBlocks(editor), ((Function1<AbstractIndentableAutoEditBlock, Boolean>) (AbstractIndentableAutoEditBlock it) -> {
      return Boolean.valueOf(it.delete(c, context));
    })))) {
      return true;
    }
    if ((CodeInsightSettings.getInstance().AUTOINSERT_PAIR_QUOTE && IterableExtensions.<AbstractAutoEditBlock>exists(this.blockProvider.getQuotes(), ((Function1<AbstractAutoEditBlock, Boolean>) (AbstractAutoEditBlock it) -> {
      return Boolean.valueOf(it.delete(c, context));
    })))) {
      return true;
    }
    return super.charDeleted(c, file, editor);
  }
  
  protected IdeaAutoEditHandler.Result closeBlock(final AbstractAutoEditBlock block, final char c, final AutoEditContext context) {
    boolean _isAtTerminal = this.isAtTerminal(block.getClosingTerminal(), c, context);
    if (_isAtTerminal) {
      boolean _close = block.close(c, context);
      if (_close) {
        return IdeaAutoEditHandler.Result.STOP;
      }
    }
    return IdeaAutoEditHandler.Result.CONTINUE;
  }
  
  protected IdeaAutoEditHandler.Result openBlock(final AbstractAutoEditBlock block, final char c, final AutoEditContext context) {
    boolean _isAtTerminal = this.isAtTerminal(block.getOpeningTerminal(), c, context);
    if (_isAtTerminal) {
      block.open(c, context);
      return IdeaAutoEditHandler.Result.STOP;
    }
    return IdeaAutoEditHandler.Result.CONTINUE;
  }
  
  protected boolean isAtTerminal(final String terminal, final char c, @Extension final AutoEditContext context) {
    boolean _xblockexpression = false;
    {
      int _caretOffset = context.getCaretOffset();
      int _length = terminal.length();
      int _minus = (_caretOffset - _length);
      final int startOffset = (_minus + 1);
      String _xifexpression = null;
      int _caretOffset_1 = context.getCaretOffset();
      boolean _equals = (startOffset == _caretOffset_1);
      if (_equals) {
        _xifexpression = "";
      } else {
        _xifexpression = context.getText(Math.max(0, startOffset), context.getCaretOffset());
      }
      final String startText = _xifexpression;
      int _caretOffset_2 = context.getCaretOffset();
      int _length_1 = terminal.length();
      int _plus = (_caretOffset_2 + _length_1);
      final int endOffset = (_plus - 1);
      String _xifexpression_1 = null;
      int _caretOffset_3 = context.getCaretOffset();
      boolean _equals_1 = (endOffset == _caretOffset_3);
      if (_equals_1) {
        _xifexpression_1 = "";
      } else {
        _xifexpression_1 = context.getText(context.getCaretOffset(), Math.min(endOffset, context.getDocument().getTextLength()));
      }
      final String endText = _xifexpression_1;
      final String text = ((startText + Character.valueOf(c)) + endText);
      int _lastIndexOf = text.lastIndexOf(terminal);
      _xblockexpression = (_lastIndexOf != (-1));
    }
    return _xblockexpression;
  }
}
