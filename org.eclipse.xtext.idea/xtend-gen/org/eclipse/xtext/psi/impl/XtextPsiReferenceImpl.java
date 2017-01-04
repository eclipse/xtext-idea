/**
 * Copyright (c) 2015 itemis AG (http://www.itemis.eu) and others.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 */
package org.eclipse.xtext.psi.impl;

import com.google.common.base.Objects;
import com.google.inject.Inject;
import com.intellij.codeInsight.lookup.LookupElementBuilder;
import com.intellij.lang.ASTNode;
import com.intellij.lang.Language;
import com.intellij.openapi.progress.ProgressIndicatorProvider;
import com.intellij.openapi.util.TextRange;
import com.intellij.psi.PsiElement;
import com.intellij.psi.PsiReferenceBase;
import com.intellij.psi.impl.source.tree.CompositeElement;
import com.intellij.psi.tree.IElementType;
import com.intellij.util.IncorrectOperationException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import org.eclipse.emf.ecore.EObject;
import org.eclipse.emf.ecore.EReference;
import org.eclipse.xtext.CrossReference;
import org.eclipse.xtext.GrammarUtil;
import org.eclipse.xtext.idea.lang.IXtextLanguage;
import org.eclipse.xtext.idea.linking.lazy.CrossReferenceDescription;
import org.eclipse.xtext.idea.linking.lazy.ICrossReferenceDescription;
import org.eclipse.xtext.naming.IQualifiedNameConverter;
import org.eclipse.xtext.nodemodel.INode;
import org.eclipse.xtext.nodemodel.util.NodeModelUtils;
import org.eclipse.xtext.psi.IPsiModelAssociations;
import org.eclipse.xtext.psi.PsiEObjectFactory;
import org.eclipse.xtext.psi.XtextPsiElement;
import org.eclipse.xtext.psi.XtextPsiReference;
import org.eclipse.xtext.psi.tree.IGrammarAwareElementType;
import org.eclipse.xtext.resource.IEObjectDescription;
import org.eclipse.xtext.util.ITextRegion;
import org.eclipse.xtext.xbase.lib.CollectionLiterals;
import org.eclipse.xtext.xbase.lib.Conversions;
import org.eclipse.xtext.xbase.lib.Extension;
import org.eclipse.xtext.xbase.lib.Functions.Function1;
import org.eclipse.xtext.xbase.lib.IterableExtensions;
import org.eclipse.xtext.xbase.lib.Pair;

@SuppressWarnings("all")
public class XtextPsiReferenceImpl extends PsiReferenceBase<XtextPsiElement> implements XtextPsiReference {
  @Inject
  @Extension
  private PsiEObjectFactory psiEObjectFactory;
  
  @Inject
  private IQualifiedNameConverter qualifiedNameConverter;
  
  @Inject
  @Extension
  private IPsiModelAssociations psiModelAssociations;
  
  @Inject
  private CrossReferenceDescription.CrossReferenceDescriptionProvider crossReferenceDescriptionProvider;
  
  public XtextPsiReferenceImpl(final XtextPsiElement element) {
    super(element);
    final Language language = element.getLanguage();
    if ((language instanceof IXtextLanguage)) {
      ((IXtextLanguage)language).injectMembers(this);
    }
  }
  
  @Override
  public TextRange getRangeToHighlightInElement() {
    final ArrayList<?> _cacheKey = CollectionLiterals.newArrayList();
    final TextRange _result;
    synchronized (_createCache_getRangeToHighlightInElement) {
      if (_createCache_getRangeToHighlightInElement.containsKey(_cacheKey)) {
        return _createCache_getRangeToHighlightInElement.get(_cacheKey);
      }
      TextRange _xblockexpression = null;
      {
        final ITextRegion textRegion = this.getCrossReferenceDescription().getTextRegion();
        int _offset = textRegion.getOffset();
        int _startOffset = this.myElement.getTextRange().getStartOffset();
        int startOffset = (_offset - _startOffset);
        TextRange _xifexpression = null;
        if ((startOffset < 0)) {
          _xifexpression = this.getRangeInElement();
        } else {
          TextRange _xblockexpression_1 = null;
          {
            int _length = textRegion.getLength();
            final int endOffset = (startOffset + _length);
            _xblockexpression_1 = new TextRange(startOffset, endOffset);
          }
          _xifexpression = _xblockexpression_1;
        }
        _xblockexpression = _xifexpression;
      }
      _result = _xblockexpression;
      _createCache_getRangeToHighlightInElement.put(_cacheKey, _result);
    }
    _init_getRangeToHighlightInElement(_result);
    return _result;
  }
  
  private final HashMap<ArrayList<?>, TextRange> _createCache_getRangeToHighlightInElement = CollectionLiterals.newHashMap();
  
  private void _init_getRangeToHighlightInElement(final TextRange it) {
  }
  
  @Override
  protected TextRange calculateDefaultRangeInElement() {
    TextRange _xblockexpression = null;
    {
      final ASTNode referencenNode = this.getReferenceNode();
      int _startOffset = referencenNode.getStartOffset();
      int _startOffset_1 = this.myElement.getNode().getStartOffset();
      final int startOffset = (_startOffset - _startOffset_1);
      int _textLength = this.getReferenceNode().getTextLength();
      final int endOffset = (startOffset + _textLength);
      _xblockexpression = new TextRange(startOffset, endOffset);
    }
    return _xblockexpression;
  }
  
  @Override
  public List<TextRange> getRanges() {
    List<TextRange> _xblockexpression = null;
    {
      final TextRange rangeInElement = this.getRangeInElement();
      final TextRange rangeToHighlightInElement = this.getRangeToHighlightInElement();
      List<TextRange> _xifexpression = null;
      boolean _equals = Objects.equal(rangeInElement, rangeToHighlightInElement);
      if (_equals) {
        _xifexpression = Collections.<TextRange>unmodifiableList(CollectionLiterals.<TextRange>newArrayList(rangeInElement));
      } else {
        _xifexpression = Collections.<TextRange>unmodifiableList(CollectionLiterals.<TextRange>newArrayList(rangeInElement, rangeToHighlightInElement));
      }
      _xblockexpression = _xifexpression;
    }
    return _xblockexpression;
  }
  
  @Override
  public PsiElement handleElementRename(final String newElementName) throws IncorrectOperationException {
    final ASTNode myNode = this.myElement.getNode();
    final ASTNode referenceNode = this.getReferenceNode();
    final ASTNode newReferenceNode = this.psiEObjectFactory.createLeafIdentifier(newElementName, referenceNode);
    referenceNode.getTreeParent().replaceChild(referenceNode, newReferenceNode);
    boolean _equals = Objects.equal(referenceNode, myNode);
    if (_equals) {
      return newReferenceNode.getPsi();
    }
    return this.myElement;
  }
  
  protected ASTNode getReferenceNode() {
    ASTNode _xblockexpression = null;
    {
      ASTNode referenceNode = this.myElement.getNode();
      while ((referenceNode instanceof CompositeElement)) {
        referenceNode = ((CompositeElement)referenceNode).getLastChildNode();
      }
      _xblockexpression = referenceNode;
    }
    return _xblockexpression;
  }
  
  protected ICrossReferenceDescription getCrossReferenceDescription() {
    ICrossReferenceDescription _xblockexpression = null;
    {
      IElementType _elementType = this.myElement.getNode().getElementType();
      EObject _grammarElement = ((IGrammarAwareElementType) _elementType).getGrammarElement();
      final CrossReference crossReference = ((CrossReference) _grammarElement);
      final INode node = this.myElement.getXtextFile().getINode(this.myElement.getNode());
      final EObject context = NodeModelUtils.findActualSemanticObjectFor(node);
      final EReference reference = GrammarUtil.getReference(crossReference);
      final Function1<Pair<Integer, INode>, Boolean> _function = (Pair<Integer, INode> it) -> {
        return Boolean.valueOf(((it.getValue().getTotalOffset() <= node.getTotalOffset()) && (it.getValue().getTotalEndOffset() >= node.getTotalEndOffset())));
      };
      Pair<Integer, INode> _findFirst = IterableExtensions.<Pair<Integer, INode>>findFirst(IterableExtensions.<INode>indexed(NodeModelUtils.findNodesForFeature(context, reference)), _function);
      Integer _key = null;
      if (_findFirst!=null) {
        _key=_findFirst.getKey();
      }
      final Integer index = _key;
      _xblockexpression = this.crossReferenceDescriptionProvider.get(context, reference, index);
    }
    return _xblockexpression;
  }
  
  @Override
  public Object[] getVariants() {
    ArrayList<LookupElementBuilder> _xblockexpression = null;
    {
      ProgressIndicatorProvider.checkCanceled();
      ICrossReferenceDescription crossReferenceDescription = this.getCrossReferenceDescription();
      if ((crossReferenceDescription == null)) {
        return ((Object[])Conversions.unwrapArray(CollectionLiterals.<Object>emptyList(), Object.class));
      }
      ArrayList<LookupElementBuilder> variants = CollectionLiterals.<LookupElementBuilder>newArrayList();
      Iterable<IEObjectDescription> _variants = crossReferenceDescription.getVariants();
      for (final IEObjectDescription objectDescription : _variants) {
        {
          ProgressIndicatorProvider.checkCanceled();
          String name = this.qualifiedNameConverter.toString(objectDescription.getName());
          if ((objectDescription.getEObjectOrProxy().eIsProxy() || (objectDescription.getEObjectOrProxy().eResource() != null))) {
            PsiElement element = this.psiModelAssociations.getPsiElement(objectDescription, this.myElement.getXtextFile().getResource());
            if ((element != null)) {
              LookupElementBuilder _withTypeText = LookupElementBuilder.create(name).withTypeText(element.getNavigationElement().getContainingFile().getName());
              variants.add(_withTypeText);
            }
          }
        }
      }
      _xblockexpression = variants;
    }
    return ((Object[])Conversions.unwrapArray(_xblockexpression, Object.class));
  }
  
  @Override
  public PsiElement resolve() {
    PsiElement _xblockexpression = null;
    {
      ProgressIndicatorProvider.checkCanceled();
      ICrossReferenceDescription crossReferenceDescription = this.getCrossReferenceDescription();
      if ((crossReferenceDescription == null)) {
        return null;
      }
      EObject object = crossReferenceDescription.resolve();
      ProgressIndicatorProvider.checkCanceled();
      _xblockexpression = this.psiModelAssociations.getPsiElement(object);
    }
    return _xblockexpression;
  }
  
  @Override
  public String toString() {
    return this.getClass().getSimpleName();
  }
}
