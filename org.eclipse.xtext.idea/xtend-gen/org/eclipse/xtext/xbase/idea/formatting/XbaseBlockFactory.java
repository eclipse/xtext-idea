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
import com.intellij.formatting.SpacingBuilder;
import com.intellij.lang.Language;
import com.intellij.psi.codeStyle.CodeStyleSettings;
import java.util.Collections;
import java.util.List;
import org.eclipse.emf.ecore.EObject;
import org.eclipse.xtext.Action;
import org.eclipse.xtext.Keyword;
import org.eclipse.xtext.RuleCall;
import org.eclipse.xtext.idea.formatting.DefaultBlockFactory;
import org.eclipse.xtext.idea.lang.IElementTypeProvider;
import org.eclipse.xtext.psi.tree.IGrammarAwareElementType;
import org.eclipse.xtext.util.Pair;
import org.eclipse.xtext.xbase.lib.CollectionLiterals;
import org.eclipse.xtext.xbase.lib.Conversions;
import org.eclipse.xtext.xbase.lib.Extension;
import org.eclipse.xtext.xbase.lib.Functions.Function1;
import org.eclipse.xtext.xbase.lib.ListExtensions;
import org.eclipse.xtext.xbase.services.XbaseGrammarAccess;

/**
 * @author kosyakov - Initial contribution and API
 */
@Singleton
@SuppressWarnings("all")
public class XbaseBlockFactory extends DefaultBlockFactory {
  @Inject
  @Extension
  private XbaseGrammarAccess _xbaseGrammarAccess;
  
  @Inject
  @Extension
  private IElementTypeProvider elementTypeProvider;
  
  @Override
  protected SpacingBuilder createSpacingBuilder(final CodeStyleSettings settings, final Language language) {
    SpacingBuilder _xblockexpression = null;
    {
      final SpacingBuilder spacingBuilder = new SpacingBuilder(settings, language);
      final Function1<Pair<Keyword, Keyword>, org.eclipse.xtext.xbase.lib.Pair<IGrammarAwareElementType, IGrammarAwareElementType>> _function = (Pair<Keyword, Keyword> it) -> {
        IGrammarAwareElementType _findElementType = this.elementTypeProvider.findElementType(it.getFirst());
        IGrammarAwareElementType _findElementType_1 = this.elementTypeProvider.findElementType(it.getSecond());
        return org.eclipse.xtext.xbase.lib.Pair.<IGrammarAwareElementType, IGrammarAwareElementType>of(_findElementType, _findElementType_1);
      };
      List<org.eclipse.xtext.xbase.lib.Pair<IGrammarAwareElementType, IGrammarAwareElementType>> _map = ListExtensions.<Pair<Keyword, Keyword>, org.eclipse.xtext.xbase.lib.Pair<IGrammarAwareElementType, IGrammarAwareElementType>>map(this._xbaseGrammarAccess.findKeywordPairs("{", "}"), _function);
      for (final org.eclipse.xtext.xbase.lib.Pair<IGrammarAwareElementType, IGrammarAwareElementType> pair : _map) {
        {
          spacingBuilder.before(pair.getKey()).spaces(1);
          spacingBuilder.withinPair(pair.getKey(), pair.getValue()).lineBreakInCode();
        }
      }
      final Function1<Keyword, IGrammarAwareElementType> _function_1 = (Keyword it) -> {
        return this.elementTypeProvider.findElementType(it);
      };
      List<IGrammarAwareElementType> _map_1 = ListExtensions.<Keyword, IGrammarAwareElementType>map(this._xbaseGrammarAccess.findKeywords(((String[])Conversions.unwrapArray(Collections.<String>unmodifiableList(CollectionLiterals.<String>newArrayList(",")), String.class))), _function_1);
      for (final IGrammarAwareElementType commaType : _map_1) {
        {
          spacingBuilder.before(commaType).spaces(0);
          spacingBuilder.after(commaType).spaces(1);
        }
      }
      final Function1<Pair<Keyword, Keyword>, org.eclipse.xtext.xbase.lib.Pair<IGrammarAwareElementType, IGrammarAwareElementType>> _function_2 = (Pair<Keyword, Keyword> it) -> {
        IGrammarAwareElementType _findElementType = this.elementTypeProvider.findElementType(it.getFirst());
        IGrammarAwareElementType _findElementType_1 = this.elementTypeProvider.findElementType(it.getSecond());
        return org.eclipse.xtext.xbase.lib.Pair.<IGrammarAwareElementType, IGrammarAwareElementType>of(_findElementType, _findElementType_1);
      };
      List<org.eclipse.xtext.xbase.lib.Pair<IGrammarAwareElementType, IGrammarAwareElementType>> _map_2 = ListExtensions.<Pair<Keyword, Keyword>, org.eclipse.xtext.xbase.lib.Pair<IGrammarAwareElementType, IGrammarAwareElementType>>map(this._xbaseGrammarAccess.findKeywordPairs("(", ")"), _function_2);
      for (final org.eclipse.xtext.xbase.lib.Pair<IGrammarAwareElementType, IGrammarAwareElementType> pair_1 : _map_2) {
        {
          spacingBuilder.withinPair(pair_1.getKey(), pair_1.getValue()).none();
          spacingBuilder.around(pair_1.getKey()).spaces(0);
          spacingBuilder.before(pair_1.getValue()).spaces(0);
          spacingBuilder.after(pair_1.getValue()).spaces(1);
        }
      }
      _xblockexpression = spacingBuilder;
    }
    return _xblockexpression;
  }
  
  @Override
  protected boolean isContinuation(final EObject grammarElement) {
    boolean _xblockexpression = false;
    {
      boolean _equals = Objects.equal(grammarElement, null);
      if (_equals) {
        return false;
      }
      boolean _switchResult = false;
      boolean _matched = false;
      Action _xAssignmentAction_0_0 = this._xbaseGrammarAccess.getXAssignmentAccess().getXAssignmentAction_0_0();
      if (Objects.equal(grammarElement, _xAssignmentAction_0_0)) {
        _matched=true;
      }
      if (!_matched) {
        Action _xBinaryOperationLeftOperandAction_1_1_0_0_0 = this._xbaseGrammarAccess.getXAssignmentAccess().getXBinaryOperationLeftOperandAction_1_1_0_0_0();
        if (Objects.equal(grammarElement, _xBinaryOperationLeftOperandAction_1_1_0_0_0)) {
          _matched=true;
        }
      }
      if (!_matched) {
        Action _xBinaryOperationLeftOperandAction_1_0_0_0 = this._xbaseGrammarAccess.getXOrExpressionAccess().getXBinaryOperationLeftOperandAction_1_0_0_0();
        if (Objects.equal(grammarElement, _xBinaryOperationLeftOperandAction_1_0_0_0)) {
          _matched=true;
        }
      }
      if (!_matched) {
        Action _xBinaryOperationLeftOperandAction_1_0_0_0_1 = this._xbaseGrammarAccess.getXAndExpressionAccess().getXBinaryOperationLeftOperandAction_1_0_0_0();
        if (Objects.equal(grammarElement, _xBinaryOperationLeftOperandAction_1_0_0_0_1)) {
          _matched=true;
        }
      }
      if (!_matched) {
        Action _xBinaryOperationLeftOperandAction_1_0_0_0_2 = this._xbaseGrammarAccess.getXEqualityExpressionAccess().getXBinaryOperationLeftOperandAction_1_0_0_0();
        if (Objects.equal(grammarElement, _xBinaryOperationLeftOperandAction_1_0_0_0_2)) {
          _matched=true;
        }
      }
      if (!_matched) {
        Action _xInstanceOfExpressionExpressionAction_1_0_0_0_0 = this._xbaseGrammarAccess.getXRelationalExpressionAccess().getXInstanceOfExpressionExpressionAction_1_0_0_0_0();
        if (Objects.equal(grammarElement, _xInstanceOfExpressionExpressionAction_1_0_0_0_0)) {
          _matched=true;
        }
      }
      if (!_matched) {
        Action _xBinaryOperationLeftOperandAction_1_1_0_0_0_1 = this._xbaseGrammarAccess.getXRelationalExpressionAccess().getXBinaryOperationLeftOperandAction_1_1_0_0_0();
        if (Objects.equal(grammarElement, _xBinaryOperationLeftOperandAction_1_1_0_0_0_1)) {
          _matched=true;
        }
      }
      if (!_matched) {
        Action _xBinaryOperationLeftOperandAction_1_0_0_0_3 = this._xbaseGrammarAccess.getXOtherOperatorExpressionAccess().getXBinaryOperationLeftOperandAction_1_0_0_0();
        if (Objects.equal(grammarElement, _xBinaryOperationLeftOperandAction_1_0_0_0_3)) {
          _matched=true;
        }
      }
      if (!_matched) {
        Action _xBinaryOperationLeftOperandAction_1_0_0_0_4 = this._xbaseGrammarAccess.getXAdditiveExpressionAccess().getXBinaryOperationLeftOperandAction_1_0_0_0();
        if (Objects.equal(grammarElement, _xBinaryOperationLeftOperandAction_1_0_0_0_4)) {
          _matched=true;
        }
      }
      if (!_matched) {
        Action _xBinaryOperationLeftOperandAction_1_0_0_0_5 = this._xbaseGrammarAccess.getXMultiplicativeExpressionAccess().getXBinaryOperationLeftOperandAction_1_0_0_0();
        if (Objects.equal(grammarElement, _xBinaryOperationLeftOperandAction_1_0_0_0_5)) {
          _matched=true;
        }
      }
      if (!_matched) {
        Action _xUnaryOperationAction_0_0 = this._xbaseGrammarAccess.getXUnaryOperationAccess().getXUnaryOperationAction_0_0();
        if (Objects.equal(grammarElement, _xUnaryOperationAction_0_0)) {
          _matched=true;
        }
      }
      if (!_matched) {
        Action _xCastedExpressionTargetAction_1_0_0_0 = this._xbaseGrammarAccess.getXCastedExpressionAccess().getXCastedExpressionTargetAction_1_0_0_0();
        if (Objects.equal(grammarElement, _xCastedExpressionTargetAction_1_0_0_0)) {
          _matched=true;
        }
      }
      if (!_matched) {
        Action _xPostfixOperationOperandAction_1_0_0 = this._xbaseGrammarAccess.getXPostfixOperationAccess().getXPostfixOperationOperandAction_1_0_0();
        if (Objects.equal(grammarElement, _xPostfixOperationOperandAction_1_0_0)) {
          _matched=true;
        }
      }
      if (!_matched) {
        Action _xAssignmentAssignableAction_1_0_0_0_0 = this._xbaseGrammarAccess.getXMemberFeatureCallAccess().getXAssignmentAssignableAction_1_0_0_0_0();
        if (Objects.equal(grammarElement, _xAssignmentAssignableAction_1_0_0_0_0)) {
          _matched=true;
        }
      }
      if (!_matched) {
        Action _xClosureAction_0_0_0 = this._xbaseGrammarAccess.getXShortClosureAccess().getXClosureAction_0_0_0();
        if (Objects.equal(grammarElement, _xClosureAction_0_0_0)) {
          _matched=true;
        }
      }
      if (_matched) {
        _switchResult = true;
      }
      if (!_matched) {
        _switchResult = false;
      }
      _xblockexpression = _switchResult;
    }
    return _xblockexpression;
  }
  
  @Override
  protected boolean isStructural(final EObject grammarElement) {
    boolean _xblockexpression = false;
    {
      boolean _equals = Objects.equal(grammarElement, null);
      if (_equals) {
        return false;
      }
      boolean _switchResult = false;
      boolean _matched = false;
      RuleCall _thenXExpressionParserRuleCall_5_0 = this._xbaseGrammarAccess.getXIfExpressionAccess().getThenXExpressionParserRuleCall_5_0();
      if (Objects.equal(grammarElement, _thenXExpressionParserRuleCall_5_0)) {
        _matched=true;
      }
      if (!_matched) {
        RuleCall _elseXExpressionParserRuleCall_6_1_0 = this._xbaseGrammarAccess.getXIfExpressionAccess().getElseXExpressionParserRuleCall_6_1_0();
        if (Objects.equal(grammarElement, _elseXExpressionParserRuleCall_6_1_0)) {
          _matched=true;
        }
      }
      if (!_matched) {
        RuleCall _defaultXExpressionParserRuleCall_5_2_0 = this._xbaseGrammarAccess.getXSwitchExpressionAccess().getDefaultXExpressionParserRuleCall_5_2_0();
        if (Objects.equal(grammarElement, _defaultXExpressionParserRuleCall_5_2_0)) {
          _matched=true;
        }
      }
      if (!_matched) {
        RuleCall _thenXExpressionParserRuleCall_3_0_1_0 = this._xbaseGrammarAccess.getXCasePartAccess().getThenXExpressionParserRuleCall_3_0_1_0();
        if (Objects.equal(grammarElement, _thenXExpressionParserRuleCall_3_0_1_0)) {
          _matched=true;
        }
      }
      if (!_matched) {
        RuleCall _eachExpressionXExpressionParserRuleCall_3_0 = this._xbaseGrammarAccess.getXForLoopExpressionAccess().getEachExpressionXExpressionParserRuleCall_3_0();
        if (Objects.equal(grammarElement, _eachExpressionXExpressionParserRuleCall_3_0)) {
          _matched=true;
        }
      }
      if (!_matched) {
        RuleCall _eachExpressionXExpressionParserRuleCall_9_0 = this._xbaseGrammarAccess.getXBasicForLoopExpressionAccess().getEachExpressionXExpressionParserRuleCall_9_0();
        if (Objects.equal(grammarElement, _eachExpressionXExpressionParserRuleCall_9_0)) {
          _matched=true;
        }
      }
      if (!_matched) {
        RuleCall _bodyXExpressionParserRuleCall_5_0 = this._xbaseGrammarAccess.getXWhileExpressionAccess().getBodyXExpressionParserRuleCall_5_0();
        if (Objects.equal(grammarElement, _bodyXExpressionParserRuleCall_5_0)) {
          _matched=true;
        }
      }
      if (!_matched) {
        RuleCall _bodyXExpressionParserRuleCall_2_0 = this._xbaseGrammarAccess.getXDoWhileExpressionAccess().getBodyXExpressionParserRuleCall_2_0();
        if (Objects.equal(grammarElement, _bodyXExpressionParserRuleCall_2_0)) {
          _matched=true;
        }
      }
      if (!_matched) {
        RuleCall _rightXExpressionParserRuleCall_3_1_0 = this._xbaseGrammarAccess.getXVariableDeclarationAccess().getRightXExpressionParserRuleCall_3_1_0();
        if (Objects.equal(grammarElement, _rightXExpressionParserRuleCall_3_1_0)) {
          _matched=true;
        }
      }
      if (!_matched) {
        RuleCall _expressionXExpressionParserRuleCall_2_0 = this._xbaseGrammarAccess.getXThrowExpressionAccess().getExpressionXExpressionParserRuleCall_2_0();
        if (Objects.equal(grammarElement, _expressionXExpressionParserRuleCall_2_0)) {
          _matched=true;
        }
      }
      if (!_matched) {
        RuleCall _expressionXExpressionParserRuleCall_2_0_1 = this._xbaseGrammarAccess.getXReturnExpressionAccess().getExpressionXExpressionParserRuleCall_2_0();
        if (Objects.equal(grammarElement, _expressionXExpressionParserRuleCall_2_0_1)) {
          _matched=true;
        }
      }
      if (!_matched) {
        RuleCall _expressionXExpressionParserRuleCall_2_0_2 = this._xbaseGrammarAccess.getXTryCatchFinallyExpressionAccess().getExpressionXExpressionParserRuleCall_2_0();
        if (Objects.equal(grammarElement, _expressionXExpressionParserRuleCall_2_0_2)) {
          _matched=true;
        }
      }
      if (!_matched) {
        RuleCall _finallyExpressionXExpressionParserRuleCall_3_0_1_1_0 = this._xbaseGrammarAccess.getXTryCatchFinallyExpressionAccess().getFinallyExpressionXExpressionParserRuleCall_3_0_1_1_0();
        if (Objects.equal(grammarElement, _finallyExpressionXExpressionParserRuleCall_3_0_1_1_0)) {
          _matched=true;
        }
      }
      if (!_matched) {
        RuleCall _finallyExpressionXExpressionParserRuleCall_3_1_1_0 = this._xbaseGrammarAccess.getXTryCatchFinallyExpressionAccess().getFinallyExpressionXExpressionParserRuleCall_3_1_1_0();
        if (Objects.equal(grammarElement, _finallyExpressionXExpressionParserRuleCall_3_1_1_0)) {
          _matched=true;
        }
      }
      if (!_matched) {
        RuleCall _expressionXExpressionParserRuleCall_4_0 = this._xbaseGrammarAccess.getXCatchClauseAccess().getExpressionXExpressionParserRuleCall_4_0();
        if (Objects.equal(grammarElement, _expressionXExpressionParserRuleCall_4_0)) {
          _matched=true;
        }
      }
      if (!_matched) {
        RuleCall _expressionXExpressionParserRuleCall_3_0 = this._xbaseGrammarAccess.getXSynchronizedExpressionAccess().getExpressionXExpressionParserRuleCall_3_0();
        if (Objects.equal(grammarElement, _expressionXExpressionParserRuleCall_3_0)) {
          _matched=true;
        }
      }
      if (_matched) {
        _switchResult = true;
      }
      if (!_matched) {
        _switchResult = false;
      }
      _xblockexpression = _switchResult;
    }
    return _xblockexpression;
  }
}
