/**
 * Copyright (c) 2015 itemis AG (http://www.itemis.eu) and others.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 */
package org.eclipse.xtext.idea.editorActions;

import com.google.common.base.Objects;
import com.intellij.openapi.editor.EditorModificationUtil;
import com.intellij.openapi.editor.highlighter.HighlighterIterator;
import com.intellij.psi.tree.TokenSet;
import org.eclipse.xtext.idea.editorActions.AbstractAutoEditBlock;
import org.eclipse.xtext.idea.editorActions.AutoEditContext;
import org.eclipse.xtext.idea.editorActions.AutoEditStringRegion;
import org.eclipse.xtext.util.TextRegion;
import org.eclipse.xtext.xbase.lib.Extension;

/**
 * @author kosyakov - Initial contribution and API
 */
@SuppressWarnings("all")
public class AutoEditString extends AbstractAutoEditBlock {
  private final TokenSet stringLiteralTokens;
  
  public AutoEditString(final String quote, final TokenSet stringLiteralTokens) {
    this(quote, quote, stringLiteralTokens);
  }
  
  public AutoEditString(final String openingTerminal, final String closingTerminal, final TokenSet stringLiteralTokens) {
    super(openingTerminal, closingTerminal);
    this.setShouldDeleteClosing(true);
    this.stringLiteralTokens = stringLiteralTokens;
  }
  
  @Override
  public void open(final char c, @Extension final AutoEditContext context) {
    final int newCaretOffset = context.type(c);
    boolean _shouldInsertClosingQuote = this.shouldInsertClosingQuote(newCaretOffset, context);
    if (_shouldInsertClosingQuote) {
      context.getDocument().insertString(newCaretOffset, this.getClosingTerminal());
    }
  }
  
  @Override
  public boolean close(final char c, @Extension final AutoEditContext context) {
    final AutoEditStringRegion stringRegion = this.findRegion(context.getCaretOffset(), context);
    boolean _equals = Objects.equal(stringRegion, null);
    if (_equals) {
      return false;
    }
    TextRegion _closingTerminal = stringRegion.getClosingTerminal();
    boolean _equals_1 = Objects.equal(_closingTerminal, null);
    if (_equals_1) {
      context.type(c);
    } else {
      boolean _contains = stringRegion.getClosingTerminal().contains(context.getCaretOffset());
      if (_contains) {
        EditorModificationUtil.moveCaretRelatively(context.getEditor(), 1);
      } else {
        context.type(c);
      }
    }
    return true;
  }
  
  protected AutoEditStringRegion findRegion(final int offset, @Extension final AutoEditContext context) {
    final TextRegion openingTerminal = this.findOpeningTerminal(offset, context);
    boolean _equals = Objects.equal(openingTerminal, null);
    if (_equals) {
      return null;
    }
    final TextRegion closingTerminal = this.findClosingTerminal(offset, openingTerminal.getOffset(), context);
    boolean _notEquals = (!Objects.equal(closingTerminal, null));
    if (_notEquals) {
      int _offset = openingTerminal.getOffset();
      boolean _greaterEqualsThan = (_offset >= offset);
      if (_greaterEqualsThan) {
        return null;
      }
      int _offset_1 = closingTerminal.getOffset();
      int _length = closingTerminal.getLength();
      int _plus = (_offset_1 + _length);
      boolean _lessEqualsThan = (_plus <= offset);
      if (_lessEqualsThan) {
        return null;
      }
    }
    return new AutoEditStringRegion(openingTerminal, closingTerminal);
  }
  
  protected TextRegion findOpeningTerminal(final int offset, @Extension final AutoEditContext context) {
    Object _xblockexpression = null;
    {
      final HighlighterIterator iterator = context.createTokenIterator(offset);
      boolean _isStringLiteral = this.isStringLiteral(iterator, context);
      boolean _not = (!_isStringLiteral);
      if (_not) {
        return null;
      }
      while ((!iterator.atEnd())) {
        {
          final TextRegion openingTerminal = this.getOpeningTerminal(iterator, context);
          boolean _notEquals = (!Objects.equal(openingTerminal, null));
          if (_notEquals) {
            return openingTerminal;
          }
          iterator.retreat();
        }
      }
      _xblockexpression = null;
    }
    return ((TextRegion)_xblockexpression);
  }
  
  protected TextRegion findClosingTerminal(final int offset, final int openingTokenOffset, @Extension final AutoEditContext context) {
    Object _xblockexpression = null;
    {
      final HighlighterIterator iterator = context.createTokenIterator(offset);
      boolean _isStringLiteral = this.isStringLiteral(iterator, context);
      boolean _not = (!_isStringLiteral);
      if (_not) {
        return null;
      }
      while ((!iterator.atEnd())) {
        {
          final TextRegion closingTerminal = this.getClosingTerminal(iterator, openingTokenOffset, context);
          boolean _notEquals = (!Objects.equal(closingTerminal, null));
          if (_notEquals) {
            return closingTerminal;
          }
          iterator.advance();
        }
      }
      _xblockexpression = null;
    }
    return ((TextRegion)_xblockexpression);
  }
  
  protected TextRegion getOpeningTerminal(final HighlighterIterator iterator, @Extension final AutoEditContext context) {
    boolean _equals = Objects.equal(iterator, null);
    if (_equals) {
      return null;
    }
    int _end = iterator.getEnd();
    int _start = iterator.getStart();
    int _minus = (_end - _start);
    int _length = this.getOpeningTerminal().length();
    boolean _lessThan = (_minus < _length);
    if (_lessThan) {
      return null;
    }
    int _start_1 = iterator.getStart();
    int _start_2 = iterator.getStart();
    int _length_1 = this.getOpeningTerminal().length();
    int _plus = (_start_2 + _length_1);
    String _text = context.getText(_start_1, _plus);
    String _openingTerminal = this.getOpeningTerminal();
    boolean _notEquals = (!Objects.equal(_text, _openingTerminal));
    if (_notEquals) {
      return null;
    }
    int _start_3 = iterator.getStart();
    int _length_2 = this.getOpeningTerminal().length();
    return new TextRegion(_start_3, _length_2);
  }
  
  protected TextRegion getClosingTerminal(final HighlighterIterator iterator, final int openingTokenOffset, @Extension final AutoEditContext context) {
    boolean _equals = Objects.equal(iterator, null);
    if (_equals) {
      return null;
    }
    int _end = iterator.getEnd();
    int _minus = (_end - openingTokenOffset);
    int _length = this.getOpeningTerminal().length();
    int _length_1 = this.getClosingTerminal().length();
    int _plus = (_length + _length_1);
    boolean _lessThan = (_minus < _plus);
    if (_lessThan) {
      return null;
    }
    int _end_1 = iterator.getEnd();
    int _length_2 = this.getClosingTerminal().length();
    int _minus_1 = (_end_1 - _length_2);
    String _text = context.getText(_minus_1, iterator.getEnd());
    String _closingTerminal = this.getClosingTerminal();
    boolean _notEquals = (!Objects.equal(_text, _closingTerminal));
    if (_notEquals) {
      return null;
    }
    int _end_2 = iterator.getEnd();
    int _length_3 = this.getClosingTerminal().length();
    int _minus_2 = (_end_2 - _length_3);
    int _length_4 = this.getClosingTerminal().length();
    return new TextRegion(_minus_2, _length_4);
  }
  
  protected boolean shouldInsertClosingQuote(final int offset, @Extension final AutoEditContext context) {
    boolean _isIdentifierPart = context.isIdentifierPart(offset);
    return (!_isIdentifierPart);
  }
  
  protected boolean isStringLiteral(final HighlighterIterator iterator, @Extension final AutoEditContext context) {
    boolean _xblockexpression = false;
    {
      boolean _atEnd = iterator.atEnd();
      if (_atEnd) {
        return false;
      }
      _xblockexpression = this.isStringLiteral(context.getTokenSet(iterator), context);
    }
    return _xblockexpression;
  }
  
  protected boolean isStringLiteral(final TokenSet tokenSet, @Extension final AutoEditContext context) {
    return Objects.equal(tokenSet, this.stringLiteralTokens);
  }
}
