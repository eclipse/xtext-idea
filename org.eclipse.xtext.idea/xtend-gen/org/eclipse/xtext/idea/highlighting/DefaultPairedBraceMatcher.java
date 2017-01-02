/**
 * Copyright (c) 2015 itemis AG (http://www.itemis.eu) and others.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 */
package org.eclipse.xtext.idea.highlighting;

import com.google.common.collect.Iterables;
import com.google.inject.Inject;
import com.intellij.lang.BracePair;
import com.intellij.lang.PairedBraceMatcher;
import com.intellij.psi.PsiFile;
import com.intellij.psi.tree.IElementType;
import com.intellij.psi.tree.TokenSet;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;
import org.eclipse.xtext.IGrammarAccess;
import org.eclipse.xtext.Keyword;
import org.eclipse.xtext.ide.editor.bracketmatching.IBracePairProvider;
import org.eclipse.xtext.idea.parser.TokenTypeProvider;
import org.eclipse.xtext.parser.antlr.ITokenDefProvider;
import org.eclipse.xtext.util.Pair;
import org.eclipse.xtext.xbase.lib.CollectionLiterals;
import org.eclipse.xtext.xbase.lib.Conversions;
import org.eclipse.xtext.xbase.lib.Functions.Function1;
import org.eclipse.xtext.xbase.lib.IterableExtensions;
import org.eclipse.xtext.xbase.lib.ListExtensions;

/**
 * @author kosyakov - Initial contribution and API
 */
@SuppressWarnings("all")
public class DefaultPairedBraceMatcher implements PairedBraceMatcher {
  private final static char KEYWORD_START_CHARACTER = '\'';
  
  private final BracePair[] pairs;
  
  private final TokenSet allowedTypes;
  
  private final Map<String, IElementType> tokenTypes;
  
  @Inject
  public DefaultPairedBraceMatcher(final ITokenDefProvider tokenDefProvider, final TokenTypeProvider tokenTypeProvider, final IBracePairProvider bracePairProvider, final IGrammarAccess grammarAccess) {
    this.tokenTypes = this.createTokenTypes(tokenDefProvider, tokenTypeProvider);
    this.pairs = ((BracePair[])Conversions.unwrapArray(this.createPairs(bracePairProvider, grammarAccess), BracePair.class));
    this.allowedTypes = this.createAllowedTypes(tokenTypeProvider, grammarAccess);
  }
  
  protected TokenSet createAllowedTypes(final TokenTypeProvider tokenTypeProvider, final IGrammarAccess grammarAccess) {
    final Function1<BracePair, List<IElementType>> _function = (BracePair it) -> {
      IElementType _leftBraceType = it.getLeftBraceType();
      IElementType _rightBraceType = it.getRightBraceType();
      return Collections.<IElementType>unmodifiableList(CollectionLiterals.<IElementType>newArrayList(_leftBraceType, _rightBraceType));
    };
    final Function1<Keyword, IElementType> _function_1 = (Keyword it) -> {
      return this.getTokenType(it);
    };
    return TokenSet.orSet(
      tokenTypeProvider.getCommentTokens(), 
      tokenTypeProvider.getWhitespaceTokens(), 
      TokenSet.create(((IElementType[])Conversions.unwrapArray(Iterables.<IElementType>concat(ListExtensions.<BracePair, List<IElementType>>map(((List<BracePair>)Conversions.doWrapArray(this.pairs)), _function)), IElementType.class))), 
      TokenSet.create(((IElementType[])Conversions.unwrapArray(ListExtensions.<Keyword, IElementType>map(grammarAccess.findKeywords(";", ","), _function_1), IElementType.class))));
  }
  
  protected HashMap<String, IElementType> createTokenTypes(final ITokenDefProvider tokenDefProvider, final TokenTypeProvider tokenTypeProvider) {
    HashMap<String, IElementType> _xblockexpression = null;
    {
      final HashMap<String, IElementType> tokenTypes = CollectionLiterals.<String, IElementType>newHashMap();
      final Map<Integer, String> tokenDefMap = tokenDefProvider.getTokenDefMap();
      Set<Integer> _keySet = tokenDefMap.keySet();
      for (final Integer antlrTokenType : _keySet) {
        {
          final String value = tokenDefMap.get(antlrTokenType);
          char _charAt = value.charAt(0);
          boolean _equals = (_charAt == DefaultPairedBraceMatcher.KEYWORD_START_CHARACTER);
          if (_equals) {
            final IElementType tokenType = tokenTypeProvider.getIElementType((antlrTokenType).intValue());
            tokenTypes.put(value, tokenType);
          }
        }
      }
      _xblockexpression = tokenTypes;
    }
    return _xblockexpression;
  }
  
  protected Iterable<BracePair> createPairs(final IBracePairProvider bracePairProvider, final IGrammarAccess grammarAccess) {
    final Function1<org.eclipse.xtext.ide.editor.bracketmatching.BracePair, List<BracePair>> _function = (org.eclipse.xtext.ide.editor.bracketmatching.BracePair it) -> {
      return this.findPairs(it.getLeftBrace(), it.getRightBrace(), it.isStructural(), grammarAccess);
    };
    return Iterables.<BracePair>concat(IterableExtensions.<org.eclipse.xtext.ide.editor.bracketmatching.BracePair, List<BracePair>>map(bracePairProvider.getPairs(), _function));
  }
  
  protected List<BracePair> findPairs(final String leftBraceKeyword, final String rightBraceKeyword, final boolean structural, final IGrammarAccess grammarAccess) {
    final Function1<Pair<Keyword, Keyword>, BracePair> _function = (Pair<Keyword, Keyword> it) -> {
      return this.createPair(it.getFirst(), it.getSecond(), structural);
    };
    return ListExtensions.<Pair<Keyword, Keyword>, BracePair>map(grammarAccess.findKeywordPairs(leftBraceKeyword, rightBraceKeyword), _function);
  }
  
  protected BracePair createPair(final Keyword leftBraceKeyword, final Keyword rightBraceKeyword, final boolean structural) {
    BracePair _xblockexpression = null;
    {
      final IElementType leftBraceTokenType = this.getTokenType(leftBraceKeyword);
      final IElementType rightBraceTokenType = this.getTokenType(rightBraceKeyword);
      _xblockexpression = this.createPair(leftBraceTokenType, rightBraceTokenType, structural);
    }
    return _xblockexpression;
  }
  
  protected BracePair createPair(final IElementType leftBraceTokenType, final IElementType rightBraceTokenType, final boolean structural) {
    return new BracePair(leftBraceTokenType, rightBraceTokenType, structural);
  }
  
  protected IElementType getTokenType(final Keyword keyword) {
    String _value = keyword.getValue();
    String _plus = ("\'" + _value);
    String _plus_1 = (_plus + "\'");
    return this.tokenTypes.get(_plus_1);
  }
  
  @Override
  public int getCodeConstructStart(final PsiFile file, final int openingBraceOffset) {
    return openingBraceOffset;
  }
  
  @Override
  public BracePair[] getPairs() {
    return this.pairs;
  }
  
  @Override
  public boolean isPairedBracesAllowedBeforeType(final IElementType lbraceType, final IElementType contextType) {
    return this.allowedTypes.contains(contextType);
  }
}
