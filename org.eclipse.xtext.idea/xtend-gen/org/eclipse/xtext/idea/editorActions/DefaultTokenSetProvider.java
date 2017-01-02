/**
 * Copyright (c) 2015 itemis AG (http://www.itemis.eu) and others.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 */
package org.eclipse.xtext.idea.editorActions;

import com.google.inject.Inject;
import com.intellij.lang.CodeDocumentationAwareCommenter;
import com.intellij.openapi.editor.ex.DocumentEx;
import com.intellij.openapi.editor.ex.EditorEx;
import com.intellij.openapi.editor.highlighter.HighlighterIterator;
import com.intellij.openapi.util.TextRange;
import com.intellij.psi.tree.IElementType;
import com.intellij.psi.tree.TokenSet;
import javax.inject.Singleton;
import org.eclipse.xtext.idea.editorActions.TokenSetProvider;
import org.eclipse.xtext.idea.parser.TokenTypeProvider;

/**
 * @author kosyakov - Initial contribution and API
 */
@Singleton
@SuppressWarnings("all")
public class DefaultTokenSetProvider implements TokenSetProvider {
  @Inject
  private TokenTypeProvider tokenTypeProvider;
  
  private TokenSet slCommentTokens;
  
  private TokenSet mlCommentTokens;
  
  @Inject
  public void setCommenter(final CodeDocumentationAwareCommenter commenter) {
    this.slCommentTokens = TokenSet.create(commenter.getLineCommentTokenType());
    this.mlCommentTokens = TokenSet.create(commenter.getBlockCommentTokenType());
  }
  
  @Override
  public TokenSet getTokenSet(final EditorEx editor, final int offset) {
    return this.getTokenSet(this.getTokenType(editor, offset));
  }
  
  @Override
  public TokenSet getTokenSet(final IElementType tokenType) {
    boolean _contains = this.getStringLiteralTokens().contains(tokenType);
    if (_contains) {
      return this.getStringLiteralTokens();
    }
    boolean _contains_1 = this.getSingleLineCommentTokens().contains(tokenType);
    if (_contains_1) {
      return this.getSingleLineCommentTokens();
    }
    boolean _contains_2 = this.getMultiLineCommentTokens().contains(tokenType);
    if (_contains_2) {
      return this.getMultiLineCommentTokens();
    }
    boolean _contains_3 = this.getCommentTokens().contains(tokenType);
    if (_contains_3) {
      return this.getCommentTokens();
    }
    return this.getDefaultTokens();
  }
  
  protected IElementType getTokenType(final EditorEx editor, final int offset) {
    IElementType _xblockexpression = null;
    {
      if (((offset < 0) || (offset > editor.getDocument().getTextLength()))) {
        return null;
      }
      final HighlighterIterator iterator = editor.getHighlighter().createIterator(offset);
      boolean _atEnd = iterator.atEnd();
      if (_atEnd) {
        return null;
      }
      _xblockexpression = iterator.getTokenType();
    }
    return _xblockexpression;
  }
  
  @Override
  public TokenSet getCommentTokens() {
    return this.tokenTypeProvider.getCommentTokens();
  }
  
  @Override
  public TokenSet getSingleLineCommentTokens() {
    return this.slCommentTokens;
  }
  
  @Override
  public TokenSet getMultiLineCommentTokens() {
    return this.mlCommentTokens;
  }
  
  @Override
  public TokenSet getStringLiteralTokens() {
    return this.tokenTypeProvider.getStringLiteralTokens();
  }
  
  @Override
  public boolean isStartOfLine(final EditorEx editor, final int offset) {
    return this.isStartOfLine(this.getTokenSet(editor, offset), editor, offset);
  }
  
  @Override
  public boolean isStartOfLine(final TokenSet tokenSet, final EditorEx editor, final int offset) {
    return this.getBeginningOfLine(editor, offset).trim().isEmpty();
  }
  
  @Override
  public boolean isEndOfLine(final EditorEx editor, final int offset) {
    return this.isEndOfLine(this.getTokenSet(editor, offset), editor, offset);
  }
  
  @Override
  public boolean isEndOfLine(final TokenSet tokenSet, final EditorEx editor, final int offset) {
    return this.getEndOfLine(editor, offset).trim().isEmpty();
  }
  
  protected String getBeginningOfLine(final EditorEx editor, final int offset) {
    String _xblockexpression = null;
    {
      final DocumentEx document = editor.getDocument();
      final int lineNumber = editor.getDocument().getLineNumber(offset);
      final int lineStartOffset = editor.getDocument().getLineStartOffset(lineNumber);
      TextRange _textRange = new TextRange(lineStartOffset, offset);
      _xblockexpression = document.getText(_textRange);
    }
    return _xblockexpression;
  }
  
  protected String getEndOfLine(final EditorEx editor, final int offset) {
    String _xblockexpression = null;
    {
      final DocumentEx document = editor.getDocument();
      final int lineNumber = editor.getDocument().getLineNumber(offset);
      final int lineEndOffset = editor.getDocument().getLineEndOffset(lineNumber);
      TextRange _textRange = new TextRange(offset, lineEndOffset);
      _xblockexpression = document.getText(_textRange);
    }
    return _xblockexpression;
  }
  
  @Override
  public TokenSet getDefaultTokens() {
    return null;
  }
}
