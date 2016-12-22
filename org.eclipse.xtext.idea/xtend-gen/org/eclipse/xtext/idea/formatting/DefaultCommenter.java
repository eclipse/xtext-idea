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
import com.google.inject.name.Named;
import com.intellij.lang.CodeDocumentationAwareCommenter;
import com.intellij.psi.PsiComment;
import com.intellij.psi.tree.IElementType;
import java.util.Map;
import java.util.Set;
import org.eclipse.xtext.idea.parser.TokenTypeProvider;
import org.eclipse.xtext.parser.antlr.ITokenDefProvider;
import org.eclipse.xtext.xbase.lib.Extension;
import org.eclipse.xtext.xbase.lib.Functions.Function1;
import org.eclipse.xtext.xbase.lib.IterableExtensions;

/**
 * @author kosyakov - Initial contribution and API
 */
@SuppressWarnings("all")
public class DefaultCommenter implements CodeDocumentationAwareCommenter {
  public final static String LINE_COMMENT_PREFIX = "org.eclipse.xtext.idea.formatting.DefaultCommenter.lineCommentPrefix";
  
  public final static String BLOCK_COMMENT_PREFIX = "org.eclipse.xtext.idea.formatting.DefaultCommenter.blockCommentPrefix";
  
  public final static String BLOCK_COMMENT_SUFFIX = "org.eclipse.xtext.idea.formatting.DefaultCommenter.blockCommentPrefix";
  
  public final static String BLOCK_COMMENT_LINE_PREFIX = "org.eclipse.xtext.idea.formatting.DefaultCommenter.blockCommentLinePrefix";
  
  private IElementType mlCommentTokenType;
  
  private IElementType slCommentTokenType;
  
  @Inject(optional = true)
  @Named(DefaultCommenter.LINE_COMMENT_PREFIX)
  private String lineCommentPrefix = "//";
  
  @Inject(optional = true)
  @Named(DefaultCommenter.BLOCK_COMMENT_PREFIX)
  private String blockCommentPrefix = "/*";
  
  @Inject(optional = true)
  @Named(DefaultCommenter.BLOCK_COMMENT_SUFFIX)
  private String blockCommentSuffix = "*/";
  
  @Inject(optional = true)
  @Named(DefaultCommenter.BLOCK_COMMENT_LINE_PREFIX)
  private String documentationCommentLinePrefix = "*";
  
  @Inject
  public void setTokenTypes(final TokenTypeProvider tokenTypeProvider, final ITokenDefProvider tokenDefProvider) {
    IElementType _tokenType = this.getTokenType("RULE_SL_COMMENT", tokenTypeProvider, tokenDefProvider);
    this.slCommentTokenType = _tokenType;
    IElementType _tokenType_1 = this.getTokenType("RULE_ML_COMMENT", tokenTypeProvider, tokenDefProvider);
    this.mlCommentTokenType = _tokenType_1;
  }
  
  protected IElementType getTokenType(final String tokenName, @Extension final TokenTypeProvider tokenTypeProvider, @Extension final ITokenDefProvider tokenDefProvider) {
    IElementType _xblockexpression = null;
    {
      Map<Integer, String> _tokenDefMap = tokenDefProvider.getTokenDefMap();
      Set<Map.Entry<Integer, String>> _entrySet = _tokenDefMap.entrySet();
      final Function1<Map.Entry<Integer, String>, Boolean> _function = (Map.Entry<Integer, String> it) -> {
        String _value = it.getValue();
        return Boolean.valueOf(Objects.equal(_value, tokenName));
      };
      final Map.Entry<Integer, String> mlCommentEntry = IterableExtensions.<Map.Entry<Integer, String>>findFirst(_entrySet, _function);
      IElementType _xifexpression = null;
      if ((mlCommentEntry != null)) {
        Integer _key = mlCommentEntry.getKey();
        _xifexpression = tokenTypeProvider.getIElementType((_key).intValue());
      }
      _xblockexpression = _xifexpression;
    }
    return _xblockexpression;
  }
  
  @Override
  public String getLineCommentPrefix() {
    if ((this.slCommentTokenType != null)) {
      return this.lineCommentPrefix;
    }
    return null;
  }
  
  @Override
  public String getBlockCommentPrefix() {
    if ((this.mlCommentTokenType != null)) {
      return this.blockCommentPrefix;
    }
    return null;
  }
  
  @Override
  public String getBlockCommentSuffix() {
    if ((this.mlCommentTokenType != null)) {
      return this.blockCommentSuffix;
    }
    return null;
  }
  
  @Override
  public String getCommentedBlockCommentPrefix() {
    return null;
  }
  
  @Override
  public String getCommentedBlockCommentSuffix() {
    return null;
  }
  
  @Override
  public IElementType getBlockCommentTokenType() {
    return this.mlCommentTokenType;
  }
  
  @Override
  public String getDocumentationCommentLinePrefix() {
    if ((this.mlCommentTokenType != null)) {
      return this.documentationCommentLinePrefix;
    }
    return null;
  }
  
  @Override
  public String getDocumentationCommentPrefix() {
    return null;
  }
  
  @Override
  public String getDocumentationCommentSuffix() {
    return null;
  }
  
  @Override
  public IElementType getDocumentationCommentTokenType() {
    return this.mlCommentTokenType;
  }
  
  @Override
  public IElementType getLineCommentTokenType() {
    return this.slCommentTokenType;
  }
  
  @Override
  public boolean isDocumentationComment(final PsiComment element) {
    return false;
  }
}
