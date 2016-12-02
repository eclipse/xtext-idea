/**
 * Copyright (c) 2015 itemis AG (http://www.itemis.eu) and others.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 */
package org.eclipse.xtext.idea.formatting;

import com.google.common.base.Objects;
import com.google.common.collect.Iterables;
import com.google.common.collect.LinkedListMultimap;
import com.google.inject.Inject;
import com.google.inject.Injector;
import com.intellij.formatting.Alignment;
import com.intellij.formatting.Block;
import com.intellij.formatting.ChildAttributes;
import com.intellij.formatting.Indent;
import com.intellij.formatting.Spacing;
import com.intellij.formatting.SpacingBuilder;
import com.intellij.formatting.Wrap;
import com.intellij.lang.ASTNode;
import com.intellij.openapi.util.TextRange;
import com.intellij.psi.TokenType;
import com.intellij.psi.formatter.common.AbstractBlock;
import java.util.Comparator;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.function.Consumer;
import org.eclipse.xtend.lib.annotations.Accessors;
import org.eclipse.xtext.ide.editor.bracketmatching.BracePair;
import org.eclipse.xtext.idea.formatting.BlockExtension;
import org.eclipse.xtext.idea.formatting.BlockFactory;
import org.eclipse.xtext.idea.formatting.ChildAttributesProvider;
import org.eclipse.xtext.idea.formatting.ModifiableBlock;
import org.eclipse.xtext.idea.formatting.SyntheticXtextBlock;
import org.eclipse.xtext.xbase.lib.CollectionLiterals;
import org.eclipse.xtext.xbase.lib.Conversions;
import org.eclipse.xtext.xbase.lib.Extension;
import org.eclipse.xtext.xbase.lib.Functions.Function1;
import org.eclipse.xtext.xbase.lib.IterableExtensions;
import org.eclipse.xtext.xbase.lib.ListExtensions;
import org.eclipse.xtext.xbase.lib.Pure;

/**
 * @author kosyakov - Initial contribution and API
 */
@SuppressWarnings("all")
public class DefaultXtextBlock extends AbstractBlock implements ModifiableBlock {
  @Accessors
  private Block parentBlock;
  
  @Accessors
  private Boolean incomplete;
  
  @Accessors
  private TextRange textRange;
  
  @Accessors
  private Indent indent = Indent.getNoneIndent();
  
  @Accessors
  private SpacingBuilder spacingBuilder;
  
  @Inject
  private Injector injector;
  
  @Inject
  @Extension
  private BlockFactory _blockFactory;
  
  @Inject
  @Extension
  private BlockExtension _blockExtension;
  
  @Inject
  private ChildAttributesProvider childAttributesProvider;
  
  public DefaultXtextBlock(final ASTNode node, final Wrap wrap, final Alignment alignment) {
    super(node, wrap, alignment);
  }
  
  @Override
  public boolean isIncomplete() {
    if ((this.incomplete != null)) {
      return (this.incomplete).booleanValue();
    }
    return super.isIncomplete();
  }
  
  @Override
  public TextRange getTextRange() {
    if ((this.textRange != null)) {
      return this.textRange;
    }
    return super.getTextRange();
  }
  
  @Override
  protected List<Block> buildChildren() {
    ASTNode[] _children = this.myNode.getChildren(null);
    final Function1<ASTNode, Block> _function = (ASTNode it) -> {
      return this._blockFactory.createBlock(it, this);
    };
    List<Block> _map = ListExtensions.<ASTNode, Block>map(((List<ASTNode>)Conversions.doWrapArray(_children)), _function);
    Iterable<Block> _filterNull = IterableExtensions.<Block>filterNull(_map);
    return this.group(_filterNull);
  }
  
  protected List<Block> group(final Iterable<Block> blocks) {
    LinkedList<Block> _xblockexpression = null;
    {
      final LinkedListMultimap<BracePair, Integer> openingBlockIndex = LinkedListMultimap.<BracePair, Integer>create();
      final LinkedList<Block> stack = CollectionLiterals.<Block>newLinkedList();
      for (final Block block : blocks) {
        {
          boolean _isOpening = this._blockExtension.isOpening(block);
          if (_isOpening) {
            final BracePair bracePair = this._blockExtension.getBracePairForOpeningBrace(block);
            int _size = stack.size();
            openingBlockIndex.put(bracePair, Integer.valueOf(_size));
          }
          boolean _isClosing = this._blockExtension.isClosing(block);
          if (_isClosing) {
            final BracePair bracePair_1 = this._blockExtension.getBracePairForClosingBrace(block);
            List<Integer> _get = openingBlockIndex.get(bracePair_1);
            final Integer index = IterableExtensions.<Integer>last(_get);
            if ((index != null)) {
              openingBlockIndex.remove(bracePair_1, index);
              this.group(stack, index, bracePair_1, block);
            }
          }
          stack.addLast(block);
        }
      }
      List<Map.Entry<BracePair, Integer>> _entries = openingBlockIndex.entries();
      final Comparator<Map.Entry<BracePair, Integer>> _function = (Map.Entry<BracePair, Integer> $0, Map.Entry<BracePair, Integer> $1) -> {
        Integer _value = $0.getValue();
        Integer _value_1 = $1.getValue();
        return _value.compareTo(_value_1);
      };
      List<Map.Entry<BracePair, Integer>> _sortWith = IterableExtensions.<Map.Entry<BracePair, Integer>>sortWith(_entries, _function);
      List<Map.Entry<BracePair, Integer>> _reverse = ListExtensions.<Map.Entry<BracePair, Integer>>reverse(_sortWith);
      for (final Map.Entry<BracePair, Integer> entry : _reverse) {
        Integer _value = entry.getValue();
        BracePair _key = entry.getKey();
        this.group(stack, _value, _key, null);
      }
      _xblockexpression = stack;
    }
    return _xblockexpression;
  }
  
  protected void group(final LinkedList<Block> stack, final Integer openingBlockIndex, final BracePair bracePair, final Block closingBlock) {
    final List<Block> children = this.collectChildren(stack, openingBlockIndex);
    boolean _isEmpty = children.isEmpty();
    if (_isEmpty) {
      return;
    }
    final SyntheticXtextBlock groupBlock = this.createGroup(children);
    groupBlock.setIncomplete(Boolean.valueOf(((closingBlock == null) || Objects.equal(this._blockExtension.getElementType(closingBlock), TokenType.ERROR_ELEMENT))));
    final boolean enforceIndentToChildren = this.shouldEnforceIndentToChildren(children);
    Indent _indent = this._blockExtension.getIndent(bracePair, enforceIndentToChildren);
    groupBlock.setIndent(_indent);
    Iterable<ModifiableBlock> _filter = Iterables.<ModifiableBlock>filter(children, ModifiableBlock.class);
    ModifiableBlock _head = IterableExtensions.<ModifiableBlock>head(_filter);
    Block _parentBlock = _head.getParentBlock();
    groupBlock.setParentBlock(_parentBlock);
    Iterable<ModifiableBlock> _filter_1 = Iterables.<ModifiableBlock>filter(children, ModifiableBlock.class);
    final Consumer<ModifiableBlock> _function = (ModifiableBlock child) -> {
      child.setParentBlock(groupBlock);
    };
    _filter_1.forEach(_function);
    stack.addLast(groupBlock);
  }
  
  protected List<Block> collectChildren(final LinkedList<Block> stack, final Integer openingBlockIndex) {
    LinkedList<Block> _xblockexpression = null;
    {
      final LinkedList<Block> children = CollectionLiterals.<Block>newLinkedList();
      for (int i = (stack.size() - 1); (i > (openingBlockIndex).intValue()); i--) {
        {
          final Block childBlock = stack.removeLast();
          children.addFirst(childBlock);
        }
      }
      _xblockexpression = children;
    }
    return _xblockexpression;
  }
  
  protected boolean shouldEnforceIndentToChildren(final List<Block> children) {
    int _size = children.size();
    boolean _greaterThan = (_size > 3);
    if (_greaterThan) {
      return true;
    }
    Block _head = IterableExtensions.<Block>head(children);
    final BracePair bracePair = this._blockExtension.getBracePairForOpeningBrace(_head);
    if (((bracePair == null) || (!bracePair.isStructural()))) {
      return true;
    }
    Block _last = IterableExtensions.<Block>last(children);
    BracePair _bracePairForClosingBrace = this._blockExtension.getBracePairForClosingBrace(_last);
    return (!Objects.equal(_bracePairForClosingBrace, bracePair));
  }
  
  protected SyntheticXtextBlock createGroup(final Iterable<Block> children) {
    SyntheticXtextBlock _xblockexpression = null;
    {
      final SyntheticXtextBlock groupBlock = new SyntheticXtextBlock();
      this.injector.injectMembers(groupBlock);
      groupBlock.setSpacingBuilder(this.spacingBuilder);
      List<Block> _children = groupBlock.getChildren();
      Iterables.<Block>addAll(_children, children);
      _xblockexpression = groupBlock;
    }
    return _xblockexpression;
  }
  
  @Override
  public ChildAttributes getChildAttributes(final int newChildIndex) {
    return this.childAttributesProvider.getChildAttributes(this, newChildIndex);
  }
  
  @Override
  public boolean isLeaf() {
    ASTNode _firstChildNode = this.myNode.getFirstChildNode();
    return (_firstChildNode == null);
  }
  
  @Override
  public Spacing getSpacing(final Block child1, final Block child2) {
    Spacing _spacing = null;
    if (this.spacingBuilder!=null) {
      _spacing=this.spacingBuilder.getSpacing(this, child1, child2);
    }
    return _spacing;
  }
  
  @Pure
  public Block getParentBlock() {
    return this.parentBlock;
  }
  
  public void setParentBlock(final Block parentBlock) {
    this.parentBlock = parentBlock;
  }
  
  @Pure
  public Boolean getIncomplete() {
    return this.incomplete;
  }
  
  public void setIncomplete(final Boolean incomplete) {
    this.incomplete = incomplete;
  }
  
  public void setTextRange(final TextRange textRange) {
    this.textRange = textRange;
  }
  
  @Pure
  public Indent getIndent() {
    return this.indent;
  }
  
  public void setIndent(final Indent indent) {
    this.indent = indent;
  }
  
  @Pure
  public SpacingBuilder getSpacingBuilder() {
    return this.spacingBuilder;
  }
  
  public void setSpacingBuilder(final SpacingBuilder spacingBuilder) {
    this.spacingBuilder = spacingBuilder;
  }
}
