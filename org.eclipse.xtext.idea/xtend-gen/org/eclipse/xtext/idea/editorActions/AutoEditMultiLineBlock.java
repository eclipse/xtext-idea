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
import com.intellij.openapi.editor.ex.DocumentEx;
import com.intellij.openapi.editor.highlighter.HighlighterIterator;
import com.intellij.psi.tree.TokenSet;
import java.util.Collections;
import java.util.Set;
import org.eclipse.xtend.lib.annotations.Accessors;
import org.eclipse.xtext.idea.editorActions.AbstractIndentableAutoEditBlock;
import org.eclipse.xtext.idea.editorActions.AutoEditBlockRegion;
import org.eclipse.xtext.idea.editorActions.AutoEditContext;
import org.eclipse.xtext.util.Strings;
import org.eclipse.xtext.util.TextRegion;
import org.eclipse.xtext.xbase.lib.CollectionLiterals;
import org.eclipse.xtext.xbase.lib.Extension;
import org.eclipse.xtext.xbase.lib.Pure;

/**
 * @author kosyakov - Initial contribution and API
 */
@Accessors
@SuppressWarnings("all")
public class AutoEditMultiLineBlock extends AbstractIndentableAutoEditBlock {
  public AutoEditMultiLineBlock(final String openingTerminal, final String closingTerminal) {
    super(openingTerminal, closingTerminal);
  }
  
  public AutoEditMultiLineBlock(final String openingTerminal, final String closingTerminal, final boolean nested) {
    super(openingTerminal, null, closingTerminal, nested);
  }
  
  public AutoEditMultiLineBlock(final String openingTerminal, final String indentationTerminal, final String closingTerminal) {
    super(openingTerminal, indentationTerminal, closingTerminal, false);
  }
  
  public AutoEditMultiLineBlock(final String openingTerminal, final String indentationTerminal, final String closingTerminal, final boolean nested) {
    super(openingTerminal, indentationTerminal, closingTerminal, nested);
  }
  
  @Override
  public AutoEditBlockRegion findRegion(@Extension final AutoEditContext context) {
    return this.findRegion(context.getCaretOffset(), context);
  }
  
  protected AutoEditBlockRegion findRegion(final int offset, @Extension final AutoEditContext context) {
    final TextRegion openingTerminal = this.findOpeningTerminal(offset, context);
    boolean _equals = Objects.equal(openingTerminal, null);
    if (_equals) {
      return null;
    }
    TextRegion closingTerminal = this.findClosingTerminal(offset, context);
    if (((!Objects.equal(closingTerminal, Integer.valueOf((-1)))) && this.isNested())) {
      TextRegion previousOpeningTerminal = openingTerminal;
      TextRegion previousClosingTerminal = closingTerminal;
      while ((((!Objects.equal(closingTerminal, null)) && (!Objects.equal(previousOpeningTerminal, null))) && (!Objects.equal(previousClosingTerminal, null)))) {
        {
          previousOpeningTerminal = this.findOpeningTerminal(previousOpeningTerminal.getOffset(), context);
          boolean _notEquals = (!Objects.equal(previousOpeningTerminal, null));
          if (_notEquals) {
            int _offset = previousClosingTerminal.getOffset();
            int _plus = (_offset + 1);
            previousClosingTerminal = this.findClosingTerminal(_plus, context);
            boolean _equals_1 = Objects.equal(previousClosingTerminal, null);
            if (_equals_1) {
              closingTerminal = null;
            }
          }
        }
      }
    }
    return new AutoEditBlockRegion(this, openingTerminal, closingTerminal);
  }
  
  protected TextRegion findOpeningTerminal(final int offset, @Extension final AutoEditContext context) {
    final String text = context.getEditor().getDocument().getText();
    int leftOffset = offset;
    int rightOffset = offset;
    while (true) {
      {
        final TextRegion openingTerminal = this.searchBackward(text, this.getOpeningTerminal(), leftOffset, context);
        boolean _equals = Objects.equal(openingTerminal, null);
        if (_equals) {
          return null;
        }
        final TextRegion closingTerminal = this.searchBackward(text, this.getClosingTerminal(), rightOffset, context);
        if ((Objects.equal(closingTerminal, null) || (closingTerminal.getOffset() < openingTerminal.getOffset()))) {
          return openingTerminal;
        }
        leftOffset = openingTerminal.getOffset();
        rightOffset = closingTerminal.getOffset();
      }
    }
  }
  
  protected TextRegion findClosingTerminal(final int offset, @Extension final AutoEditContext context) {
    final String text = context.getEditor().getDocument().getText();
    int leftOffset = offset;
    int rightOffset = offset;
    while (true) {
      {
        final TextRegion closingTerminal = this.searchForward(text, this.getClosingTerminal(), rightOffset, context);
        boolean _equals = Objects.equal(closingTerminal, null);
        if (_equals) {
          return null;
        }
        final TextRegion openingTerminal = this.searchForward(text, this.getOpeningTerminal(), leftOffset, context);
        if ((Objects.equal(openingTerminal, null) || (openingTerminal.getOffset() > closingTerminal.getOffset()))) {
          return closingTerminal;
        }
        int _offset = closingTerminal.getOffset();
        int _length = closingTerminal.getLength();
        int _plus = (_offset + _length);
        rightOffset = this.findNextOffset(rightOffset, _plus, context);
        int _offset_1 = openingTerminal.getOffset();
        int _length_1 = openingTerminal.getLength();
        int _plus_1 = (_offset_1 + _length_1);
        leftOffset = this.findNextOffset(leftOffset, _plus_1, context);
      }
    }
  }
  
  protected int findNextOffset(final int currentOffset, final int nextOffset, @Extension final AutoEditContext context) {
    final TokenSet tokenSet = context.getTokenSet(currentOffset);
    final HighlighterIterator iterator = context.createTokenIterator(nextOffset);
    int offset = nextOffset;
    while ((!iterator.atEnd())) {
      {
        int _start = iterator.getStart();
        boolean _greaterThan = (_start > offset);
        if (_greaterThan) {
          offset = iterator.getStart();
        }
        TokenSet _tokenSet = context.getTokenSet(iterator);
        boolean _equals = Objects.equal(tokenSet, _tokenSet);
        if (_equals) {
          return offset;
        }
        iterator.advance();
      }
    }
    return offset;
  }
  
  protected TextRegion searchForward(final String text, final String toFind, final int startOffset, @Extension final AutoEditContext context) {
    final int length = toFind.length();
    final TokenSet tokenSet = context.getTokenSet(startOffset);
    int index = text.indexOf(toFind, startOffset);
    while (((index >= 0) && (index < text.length()))) {
      {
        TokenSet _tokenSet = context.getTokenSet(index);
        boolean _equals = Objects.equal(_tokenSet, tokenSet);
        if (_equals) {
          int _length = toFind.length();
          return new TextRegion(index, _length);
        }
        index = text.indexOf(toFind, (index + length));
      }
    }
    final String trimmed = toFind.trim();
    if (((!trimmed.isEmpty()) && (trimmed.length() != toFind.length()))) {
      return this.searchForward(text, trimmed, startOffset, context);
    }
    return null;
  }
  
  protected TextRegion searchBackward(final String text, final String toFind, final int endOffset, @Extension final AutoEditContext context) {
    final int length = toFind.length();
    final TokenSet tokenSet = context.getTokenSet(endOffset);
    int index = text.lastIndexOf(toFind, (endOffset - length));
    while ((index >= 0)) {
      {
        TokenSet _tokenSet = context.getTokenSet(index);
        boolean _equals = Objects.equal(_tokenSet, tokenSet);
        if (_equals) {
          int _length = toFind.length();
          return new TextRegion(index, _length);
        }
        index = text.lastIndexOf(toFind, (index - length));
      }
    }
    final String trimmed = toFind.trim();
    if (((!trimmed.isEmpty()) && (trimmed.length() != toFind.length()))) {
      return this.searchBackward(text, trimmed, endOffset, context);
    }
    return null;
  }
  
  @Override
  public String indent(final AutoEditBlockRegion region, final String previousLineIndentation, @Extension final AutoEditContext context) {
    final int caretOffset = context.getCaretOffset();
    boolean _isSameLine = context.isSameLine(region.getOpeningTerminal().getOffset(), caretOffset);
    if (_isSameLine) {
      final TextRegion closingTerminal = this.getClosingTerminal(region, context);
      boolean _equals = Objects.equal(closingTerminal, null);
      if (_equals) {
        this.close(previousLineIndentation, context);
      } else {
        if ((context.isSameLine(closingTerminal.getOffset(), caretOffset) && (closingTerminal.getOffset() >= caretOffset))) {
          final String text = context.getText(caretOffset, closingTerminal.getOffset()).trim();
          DocumentEx _document = context.getDocument();
          int _offset = closingTerminal.getOffset();
          String _newLine = context.newLine(previousLineIndentation);
          String _plus = (text + _newLine);
          _document.replaceString(caretOffset, _offset, _plus);
        }
      }
      return this.getIndentationTerminal();
    }
    TextRegion _closingTerminal = region.getClosingTerminal();
    boolean _equals_1 = Objects.equal(_closingTerminal, null);
    if (_equals_1) {
      this.close(previousLineIndentation, context);
      return "";
    }
    return Strings.removeLeadingWhitespace(this.getIndentationTerminal());
  }
  
  protected TextRegion getClosingTerminal(final AutoEditBlockRegion region, @Extension final AutoEditContext context) {
    final TextRegion closingTerminal = region.getClosingTerminal();
    boolean _equals = Objects.equal(closingTerminal, null);
    if (_equals) {
      return null;
    }
    if (((closingTerminal.getLength() < this.getClosingTerminal().length()) && context.isSameLine(closingTerminal.getOffset(), context.getCaretOffset()))) {
      return null;
    }
    return closingTerminal;
  }
  
  protected void close(final String previousLineIndentation, @Extension final AutoEditContext context) {
    boolean _isEndOfLine = context.isEndOfLine(context.getCaretOffset());
    if (_isEndOfLine) {
      String _removeLeadingWhitespace = Strings.removeLeadingWhitespace(this.getClosingTerminal());
      context.getDocument().insertString(
        context.getCaretOffset(), 
        context.newLine((previousLineIndentation + _removeLeadingWhitespace)));
    }
  }
  
  @Override
  public void open(final char c, @Extension final AutoEditContext context) {
    final int newCaretOffset = context.type(c);
    boolean _shouldInsertClosingTerminal = this.shouldInsertClosingTerminal(newCaretOffset, context);
    if (_shouldInsertClosingTerminal) {
      context.getDocument().insertString(newCaretOffset, this.getClosingTerminal());
    }
  }
  
  @Override
  public boolean close(final char c, @Extension final AutoEditContext context) {
    final AutoEditBlockRegion region = this.findRegion(context.getCaretOffset(), context);
    boolean _equals = Objects.equal(region, null);
    if (_equals) {
      return false;
    }
    final AutoEditBlockRegion openedRegion = this.findOpenedRegion(region, context);
    boolean _notEquals = (!Objects.equal(openedRegion, null));
    if (_notEquals) {
      context.type(c);
    } else {
      if (((!Objects.equal(region.getClosingTerminal(), null)) && region.getClosingTerminal().contains(context.getCaretOffset()))) {
        EditorModificationUtil.moveCaretRelatively(context.getEditor(), 1);
      } else {
        context.type(c);
      }
    }
    return true;
  }
  
  protected AutoEditBlockRegion findOpenedRegion(final AutoEditBlockRegion region, @Extension final AutoEditContext context) {
    boolean _equals = Objects.equal(region, null);
    if (_equals) {
      return null;
    }
    TextRegion _closingTerminal = region.getClosingTerminal();
    boolean _equals_1 = Objects.equal(_closingTerminal, null);
    if (_equals_1) {
      return region;
    }
    final AutoEditBlockRegion nextRegion = this.findRegion(region.getOpeningTerminal().getOffset(), context);
    return this.findOpenedRegion(nextRegion, context);
  }
  
  private final static Set<Character> CHARACTERS = Collections.<Character>unmodifiableSet(CollectionLiterals.<Character>newHashSet(Character.valueOf('!'), Character.valueOf('-'), Character.valueOf('('), Character.valueOf('{'), Character.valueOf('['), Character.valueOf('\''), Character.valueOf('\"')));
  
  private boolean shouldInsertClosingTerminalBeforeIndentifier = false;
  
  private boolean shouldInsertClosingTerminalBeforeDigit = false;
  
  private boolean shouldInsertClosingTerminalBeforeSpecialCharacters = false;
  
  protected boolean shouldInsertClosingTerminal(final int offset, @Extension final AutoEditContext context) {
    int _textLength = context.getDocument().getTextLength();
    boolean _lessEqualsThan = (_textLength <= offset);
    if (_lessEqualsThan) {
      return true;
    }
    final char charAtOffset = context.getDocument().getCharsSequence().charAt(offset);
    if (((!this.shouldInsertClosingTerminalBeforeIndentifier) && Character.isJavaIdentifierStart(charAtOffset))) {
      return false;
    }
    if (((!this.shouldInsertClosingTerminalBeforeDigit) && Character.isDigit(charAtOffset))) {
      return false;
    }
    if (((!this.shouldInsertClosingTerminalBeforeSpecialCharacters) && this.getSpecialCharacters().contains(Character.valueOf(charAtOffset)))) {
      return false;
    }
    return true;
  }
  
  protected Set<Character> getSpecialCharacters() {
    return AutoEditMultiLineBlock.CHARACTERS;
  }
  
  @Pure
  public boolean isShouldInsertClosingTerminalBeforeIndentifier() {
    return this.shouldInsertClosingTerminalBeforeIndentifier;
  }
  
  public void setShouldInsertClosingTerminalBeforeIndentifier(final boolean shouldInsertClosingTerminalBeforeIndentifier) {
    this.shouldInsertClosingTerminalBeforeIndentifier = shouldInsertClosingTerminalBeforeIndentifier;
  }
  
  @Pure
  public boolean isShouldInsertClosingTerminalBeforeDigit() {
    return this.shouldInsertClosingTerminalBeforeDigit;
  }
  
  public void setShouldInsertClosingTerminalBeforeDigit(final boolean shouldInsertClosingTerminalBeforeDigit) {
    this.shouldInsertClosingTerminalBeforeDigit = shouldInsertClosingTerminalBeforeDigit;
  }
  
  @Pure
  public boolean isShouldInsertClosingTerminalBeforeSpecialCharacters() {
    return this.shouldInsertClosingTerminalBeforeSpecialCharacters;
  }
  
  public void setShouldInsertClosingTerminalBeforeSpecialCharacters(final boolean shouldInsertClosingTerminalBeforeSpecialCharacters) {
    this.shouldInsertClosingTerminalBeforeSpecialCharacters = shouldInsertClosingTerminalBeforeSpecialCharacters;
  }
}
