/**
 * Copyright (c) 2015 itemis AG (http://www.itemis.eu) and others.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 */
package org.eclipse.xtext.idea.formatting;

import com.google.inject.Inject;
import com.google.inject.Singleton;
import com.intellij.formatting.Block;
import com.intellij.formatting.ChildAttributes;
import com.intellij.formatting.Indent;
import java.util.List;
import org.eclipse.emf.ecore.EObject;
import org.eclipse.xtext.idea.formatting.BlockExtension;
import org.eclipse.xtext.idea.formatting.ChildAttributesProvider;
import org.eclipse.xtext.idea.formatting.SyntheticXtextBlock;
import org.eclipse.xtext.xbase.lib.Extension;

/**
 * @author kosyakov - Initial contribution and API
 */
@Singleton
@SuppressWarnings("all")
public class DefaultChildAttributesProvider implements ChildAttributesProvider {
  @Inject
  @Extension
  private BlockExtension _blockExtension;
  
  @Override
  public ChildAttributes getChildAttributes(final Block block, final int newChildIndex) {
    Indent _elvis = null;
    Indent _childIndent = this.getChildIndent(block, newChildIndex);
    if (_childIndent != null) {
      _elvis = _childIndent;
    } else {
      Indent _noneIndent = Indent.getNoneIndent();
      _elvis = _noneIndent;
    }
    final Indent indent = _elvis;
    return new ChildAttributes(indent, null);
  }
  
  protected Indent getChildIndent(final Block block, final int newChildIndex) {
    final List<Block> children = block.getSubBlocks();
    boolean _isEmpty = children.isEmpty();
    if (_isEmpty) {
      return null;
    }
    int _size = children.size();
    boolean _greaterEqualsThan = (newChildIndex >= _size);
    if (_greaterEqualsThan) {
      return this.getAfterChildIndent(block, children.size());
    }
    final Indent indent = this.getBeforeChildIndent(block, newChildIndex);
    if ((indent == null)) {
      return this.getAfterChildIndent(block, newChildIndex);
    }
    return indent;
  }
  
  protected Indent getBeforeChildIndent(final Block block, final int newChildIndex) {
    final List<Block> children = block.getSubBlocks();
    boolean _isEmpty = children.isEmpty();
    if (_isEmpty) {
      return null;
    }
    int _size = children.size();
    boolean _greaterEqualsThan = (newChildIndex >= _size);
    if (_greaterEqualsThan) {
      return null;
    }
    final Block child = children.get(newChildIndex);
    boolean _isLeaf = child.isLeaf();
    if (_isLeaf) {
      return this.getBeforeChildIndent(child);
    }
    return this.getChildIndent(child, 0);
  }
  
  protected Indent getAfterChildIndent(final Block block, final int newChildIndex) {
    if ((newChildIndex == 0)) {
      return null;
    }
    final List<Block> children = block.getSubBlocks();
    boolean _isEmpty = children.isEmpty();
    if (_isEmpty) {
      return null;
    }
    final Block childBefore = children.get((newChildIndex - 1));
    boolean _isLeaf = childBefore.isLeaf();
    if (_isLeaf) {
      return this.getAfterChildIndent(childBefore);
    }
    final int size = childBefore.getSubBlocks().size();
    return this.getChildIndent(childBefore, size);
  }
  
  protected Indent getAfterChildIndent(final Block block) {
    if ((block == null)) {
      return null;
    }
    final Indent grammarElementIndent = this.getIndentAfter(this._blockExtension.getGrammarElement(block));
    if ((grammarElementIndent != null)) {
      return grammarElementIndent;
    }
    if ((block instanceof SyntheticXtextBlock)) {
      return ((SyntheticXtextBlock)block).getIndent();
    }
    boolean _isOpening = this._blockExtension.isOpening(block);
    if (_isOpening) {
      return this._blockExtension.getIndent(this._blockExtension.getBracePairForOpeningBrace(block), false);
    }
    return null;
  }
  
  protected Indent getIndentAfter(final EObject grammarElement) {
    return null;
  }
  
  protected Indent getBeforeChildIndent(final Block block) {
    if ((block == null)) {
      return null;
    }
    final Indent grammarElementIndent = this.getIndentBefore(this._blockExtension.getGrammarElement(block));
    if ((grammarElementIndent != null)) {
      return grammarElementIndent;
    }
    if ((block instanceof SyntheticXtextBlock)) {
      return ((SyntheticXtextBlock)block).getIndent();
    }
    boolean _isClosing = this._blockExtension.isClosing(block);
    if (_isClosing) {
      return this._blockExtension.getIndent(this._blockExtension.getBracePairForClosingBrace(block), false);
    }
    return null;
  }
  
  protected Indent getIndentBefore(final EObject grammarElement) {
    return null;
  }
}
