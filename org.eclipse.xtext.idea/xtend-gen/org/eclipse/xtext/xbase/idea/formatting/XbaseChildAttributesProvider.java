/**
 * Copyright (c) 2015 itemis AG (http://www.itemis.eu) and others.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 */
package org.eclipse.xtext.xbase.idea.formatting;

import com.google.common.base.Objects;
import com.google.inject.Inject;
import com.google.inject.Singleton;
import com.intellij.formatting.Indent;
import org.eclipse.emf.ecore.EObject;
import org.eclipse.xtext.Keyword;
import org.eclipse.xtext.idea.formatting.DefaultChildAttributesProvider;
import org.eclipse.xtext.xbase.lib.Extension;
import org.eclipse.xtext.xbase.services.XbaseGrammarAccess;

/**
 * @author kosyakov - Initial contribution and API
 */
@Singleton
@SuppressWarnings("all")
public class XbaseChildAttributesProvider extends DefaultChildAttributesProvider {
  @Inject
  @Extension
  private XbaseGrammarAccess _xbaseGrammarAccess;
  
  @Override
  protected Indent getIndentAfter(final EObject grammarElement) {
    boolean _equals = Objects.equal(grammarElement, null);
    if (_equals) {
      return null;
    }
    boolean _matched = false;
    Keyword _colonKeyword_5_1 = this._xbaseGrammarAccess.getXSwitchExpressionAccess().getColonKeyword_5_1();
    if (Objects.equal(grammarElement, _colonKeyword_5_1)) {
      _matched=true;
    }
    if (!_matched) {
      Keyword _returnKeyword_1 = this._xbaseGrammarAccess.getXReturnExpressionAccess().getReturnKeyword_1();
      if (Objects.equal(grammarElement, _returnKeyword_1)) {
        _matched=true;
      }
    }
    if (!_matched) {
      Keyword _rightParenthesisKeyword_2 = this._xbaseGrammarAccess.getXSynchronizedExpressionAccess().getRightParenthesisKeyword_2();
      if (Objects.equal(grammarElement, _rightParenthesisKeyword_2)) {
        _matched=true;
      }
    }
    if (!_matched) {
      Keyword _rightParenthesisKeyword_2_1 = this._xbaseGrammarAccess.getXForLoopExpressionAccess().getRightParenthesisKeyword_2();
      if (Objects.equal(grammarElement, _rightParenthesisKeyword_2_1)) {
        _matched=true;
      }
    }
    if (!_matched) {
      Keyword _rightParenthesisKeyword_8 = this._xbaseGrammarAccess.getXBasicForLoopExpressionAccess().getRightParenthesisKeyword_8();
      if (Objects.equal(grammarElement, _rightParenthesisKeyword_8)) {
        _matched=true;
      }
    }
    if (!_matched) {
      Keyword _rightParenthesisKeyword_4 = this._xbaseGrammarAccess.getXWhileExpressionAccess().getRightParenthesisKeyword_4();
      if (Objects.equal(grammarElement, _rightParenthesisKeyword_4)) {
        _matched=true;
      }
    }
    if (!_matched) {
      Keyword _doKeyword_1 = this._xbaseGrammarAccess.getXDoWhileExpressionAccess().getDoKeyword_1();
      if (Objects.equal(grammarElement, _doKeyword_1)) {
        _matched=true;
      }
    }
    if (!_matched) {
      Keyword _rightParenthesisKeyword_4_1 = this._xbaseGrammarAccess.getXIfExpressionAccess().getRightParenthesisKeyword_4();
      if (Objects.equal(grammarElement, _rightParenthesisKeyword_4_1)) {
        _matched=true;
      }
    }
    if (!_matched) {
      Keyword _elseKeyword_6_0 = this._xbaseGrammarAccess.getXIfExpressionAccess().getElseKeyword_6_0();
      if (Objects.equal(grammarElement, _elseKeyword_6_0)) {
        _matched=true;
      }
    }
    if (!_matched) {
      Keyword _colonKeyword_3_0_0 = this._xbaseGrammarAccess.getXCasePartAccess().getColonKeyword_3_0_0();
      if (Objects.equal(grammarElement, _colonKeyword_3_0_0)) {
        _matched=true;
      }
    }
    if (!_matched) {
      Keyword _rightParenthesisKeyword_3 = this._xbaseGrammarAccess.getXCatchClauseAccess().getRightParenthesisKeyword_3();
      if (Objects.equal(grammarElement, _rightParenthesisKeyword_3)) {
        _matched=true;
      }
    }
    if (!_matched) {
      Keyword _throwKeyword_1 = this._xbaseGrammarAccess.getXThrowExpressionAccess().getThrowKeyword_1();
      if (Objects.equal(grammarElement, _throwKeyword_1)) {
        _matched=true;
      }
    }
    if (!_matched) {
      Keyword _tryKeyword_1 = this._xbaseGrammarAccess.getXTryCatchFinallyExpressionAccess().getTryKeyword_1();
      if (Objects.equal(grammarElement, _tryKeyword_1)) {
        _matched=true;
      }
    }
    if (!_matched) {
      Keyword _finallyKeyword_3_1_0 = this._xbaseGrammarAccess.getXTryCatchFinallyExpressionAccess().getFinallyKeyword_3_1_0();
      if (Objects.equal(grammarElement, _finallyKeyword_3_1_0)) {
        _matched=true;
      }
    }
    if (!_matched) {
      Keyword _finallyKeyword_3_0_1_0 = this._xbaseGrammarAccess.getXTryCatchFinallyExpressionAccess().getFinallyKeyword_3_0_1_0();
      if (Objects.equal(grammarElement, _finallyKeyword_3_0_1_0)) {
        _matched=true;
      }
    }
    if (!_matched) {
      Keyword _equalsSignKeyword_3_0 = this._xbaseGrammarAccess.getXVariableDeclarationAccess().getEqualsSignKeyword_3_0();
      if (Objects.equal(grammarElement, _equalsSignKeyword_3_0)) {
        _matched=true;
      }
    }
    if (_matched) {
      return Indent.getNormalIndent();
    }
    return null;
  }
}
