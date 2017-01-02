/**
 * Copyright (c) 2015 itemis AG (http://www.itemis.eu) and others.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 */
package org.eclipse.xtext.idea.editorActions;

import com.google.common.base.Objects;
import org.eclipse.xtend.lib.annotations.Accessors;
import org.eclipse.xtend.lib.annotations.FinalFieldsConstructor;
import org.eclipse.xtext.idea.editorActions.AutoEditContext;
import org.eclipse.xtext.xbase.lib.Extension;
import org.eclipse.xtext.xbase.lib.Pure;

/**
 * @author kosyakov - Initial contribution and API
 */
@Accessors
@FinalFieldsConstructor
@SuppressWarnings("all")
public abstract class AbstractAutoEditBlock {
  private final String openingTerminal;
  
  private final String closingTerminal;
  
  public abstract void open(final char c, @Extension final AutoEditContext context);
  
  public abstract boolean close(final char c, @Extension final AutoEditContext context);
  
  private boolean shouldDeleteClosing = false;
  
  public boolean delete(final char c, @Extension final AutoEditContext context) {
    if ((!this.shouldDeleteClosing)) {
      return false;
    }
    char _charAt = this.openingTerminal.charAt(0);
    boolean _notEquals = (c != _charAt);
    if (_notEquals) {
      return false;
    }
    final int beginOffset = context.getCaretOffset();
    if ((beginOffset < 0)) {
      return false;
    }
    int _length = this.closingTerminal.length();
    final int endOffset = (beginOffset + _length);
    int _textLength = context.getDocument().getTextLength();
    boolean _greaterThan = (endOffset > _textLength);
    if (_greaterThan) {
      return false;
    }
    final String s = context.getText(beginOffset, endOffset);
    boolean _notEquals_1 = (!Objects.equal(s, this.closingTerminal));
    if (_notEquals_1) {
      return false;
    }
    context.getEditor().getDocument().deleteString(beginOffset, endOffset);
    return true;
  }
  
  public AbstractAutoEditBlock(final String openingTerminal, final String closingTerminal) {
    super();
    this.openingTerminal = openingTerminal;
    this.closingTerminal = closingTerminal;
  }
  
  @Pure
  public String getOpeningTerminal() {
    return this.openingTerminal;
  }
  
  @Pure
  public String getClosingTerminal() {
    return this.closingTerminal;
  }
  
  @Pure
  public boolean isShouldDeleteClosing() {
    return this.shouldDeleteClosing;
  }
  
  public void setShouldDeleteClosing(final boolean shouldDeleteClosing) {
    this.shouldDeleteClosing = shouldDeleteClosing;
  }
}
