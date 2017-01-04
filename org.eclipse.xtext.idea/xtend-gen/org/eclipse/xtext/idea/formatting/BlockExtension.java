/**
 * Copyright (c) 2015 itemis AG (http://www.itemis.eu) and others.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 */
package org.eclipse.xtext.idea.formatting;

import com.google.common.base.Objects;
import com.google.inject.Inject;
import com.google.inject.Singleton;
import com.intellij.formatting.ASTBlock;
import com.intellij.formatting.Block;
import com.intellij.formatting.Indent;
import com.intellij.lang.ASTNode;
import com.intellij.psi.tree.IElementType;
import java.util.Set;
import org.eclipse.emf.ecore.EObject;
import org.eclipse.xtext.ide.editor.bracketmatching.BracePair;
import org.eclipse.xtext.ide.editor.bracketmatching.IBracePairProvider;
import org.eclipse.xtext.idea.formatting.ModifiableBlock;
import org.eclipse.xtext.psi.tree.IGrammarAwareElementType;
import org.eclipse.xtext.xbase.lib.Functions.Function1;
import org.eclipse.xtext.xbase.lib.IterableExtensions;

/**
 * @author kosyakov - Initial contribution and API
 */
@Singleton
@SuppressWarnings("all")
public class BlockExtension {
  @Inject
  private IBracePairProvider bracePairProvider;
  
  public Block getParentBlock(final Block block) {
    Block _xifexpression = null;
    if ((block instanceof ModifiableBlock)) {
      _xifexpression = ((ModifiableBlock)block).getParentBlock();
    }
    return _xifexpression;
  }
  
  public ASTNode getNode(final Block block) {
    ASTNode _xifexpression = null;
    if ((block instanceof ASTBlock)) {
      _xifexpression = ((ASTBlock)block).getNode();
    }
    return _xifexpression;
  }
  
  public IElementType getElementType(final Block block) {
    ASTNode _node = this.getNode(block);
    IElementType _elementType = null;
    if (_node!=null) {
      _elementType=_node.getElementType();
    }
    return _elementType;
  }
  
  public EObject getGrammarElement(final Block block) {
    EObject _xblockexpression = null;
    {
      final IElementType elementType = this.getElementType(block);
      EObject _xifexpression = null;
      if ((elementType instanceof IGrammarAwareElementType)) {
        _xifexpression = ((IGrammarAwareElementType)elementType).getGrammarElement();
      }
      _xblockexpression = _xifexpression;
    }
    return _xblockexpression;
  }
  
  public boolean isOpening(final Block block) {
    boolean _xifexpression = false;
    if ((block instanceof ASTBlock)) {
      _xifexpression = this.isOpening(((ASTBlock)block).getNode());
    }
    return _xifexpression;
  }
  
  public boolean isClosing(final Block block) {
    boolean _xifexpression = false;
    if ((block instanceof ASTBlock)) {
      _xifexpression = this.isClosing(((ASTBlock)block).getNode());
    }
    return _xifexpression;
  }
  
  public boolean isOpening(final ASTNode node) {
    boolean _xblockexpression = false;
    {
      if ((node == null)) {
        return false;
      }
      final String text = node.getText();
      final Function1<BracePair, Boolean> _function = (BracePair it) -> {
        String _leftBrace = it.getLeftBrace();
        return Boolean.valueOf(Objects.equal(text, _leftBrace));
      };
      _xblockexpression = IterableExtensions.<BracePair>exists(this.getBracePairs(), _function);
    }
    return _xblockexpression;
  }
  
  public boolean isClosing(final ASTNode node) {
    boolean _xblockexpression = false;
    {
      if ((node == null)) {
        return false;
      }
      final String text = node.getText();
      final Function1<BracePair, Boolean> _function = (BracePair it) -> {
        String _rightBrace = it.getRightBrace();
        return Boolean.valueOf(Objects.equal(text, _rightBrace));
      };
      _xblockexpression = IterableExtensions.<BracePair>exists(this.getBracePairs(), _function);
    }
    return _xblockexpression;
  }
  
  public BracePair getBracePairForOpeningBrace(final Block block) {
    return this.getBracePairForOpeningNode(this.getNode(block));
  }
  
  public BracePair getBracePairForOpeningNode(final ASTNode node) {
    BracePair _xblockexpression = null;
    {
      if ((node == null)) {
        return null;
      }
      final String openingBrace = node.getText();
      final Function1<BracePair, Boolean> _function = (BracePair it) -> {
        String _leftBrace = it.getLeftBrace();
        return Boolean.valueOf(Objects.equal(_leftBrace, openingBrace));
      };
      _xblockexpression = IterableExtensions.<BracePair>findFirst(this.getBracePairs(), _function);
    }
    return _xblockexpression;
  }
  
  public BracePair getBracePairForClosingBrace(final Block block) {
    return this.getBracePairForClosingNode(this.getNode(block));
  }
  
  public BracePair getBracePairForClosingNode(final ASTNode node) {
    BracePair _xblockexpression = null;
    {
      if ((node == null)) {
        return null;
      }
      final String openingBrace = node.getText();
      final Function1<BracePair, Boolean> _function = (BracePair it) -> {
        String _rightBrace = it.getRightBrace();
        return Boolean.valueOf(Objects.equal(_rightBrace, openingBrace));
      };
      _xblockexpression = IterableExtensions.<BracePair>findFirst(this.getBracePairs(), _function);
    }
    return _xblockexpression;
  }
  
  public boolean isStructural(final Block block) {
    boolean _xblockexpression = false;
    {
      final ASTNode node = this.getNode(block);
      int _startOffset = block.getTextRange().getStartOffset();
      int _startOffset_1 = node.getStartOffset();
      final int offset = (_startOffset - _startOffset_1);
      final ASTNode leafElement = node.findLeafElementAt(offset);
      final BracePair bracePair = this.getBracePairForOpeningNode(leafElement);
      _xblockexpression = ((bracePair != null) && bracePair.isStructural());
    }
    return _xblockexpression;
  }
  
  public Set<BracePair> getBracePairs() {
    return this.bracePairProvider.getPairs();
  }
  
  public final static Indent STRUCTURAL_INDENT = Indent.getIndent(Indent.Type.NORMAL, false, true);
  
  public Indent getIndent(final BracePair bracePair, final boolean enforceIndentToChildren) {
    if ((bracePair == null)) {
      return Indent.getNoneIndent();
    }
    boolean _isStructural = bracePair.isStructural();
    if (_isStructural) {
      if (enforceIndentToChildren) {
        return BlockExtension.STRUCTURAL_INDENT;
      } else {
        return Indent.getNormalIndent();
      }
    }
    return Indent.getContinuationIndent();
  }
}
