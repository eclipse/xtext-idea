/**
 * Copyright (c) 2015 itemis AG (http://www.itemis.eu) and others.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 */
package org.eclipse.xtext.idea.resource;

import com.google.common.base.Objects;
import org.eclipse.xtend.lib.annotations.Accessors;
import org.eclipse.xtend.lib.annotations.FinalFieldsConstructor;
import org.eclipse.xtext.conversion.ValueConverterException;
import org.eclipse.xtext.idea.resource.ErrorContext;
import org.eclipse.xtext.idea.resource.PsiToEcoreTransformationContext;
import org.eclipse.xtext.parser.antlr.ISyntaxErrorMessageProvider;
import org.eclipse.xtext.xbase.lib.Pure;

@FinalFieldsConstructor
@SuppressWarnings("all")
public class ValueConverterErrorContext extends ErrorContext implements ISyntaxErrorMessageProvider.IValueConverterErrorContext {
  @Accessors
  private final ValueConverterException valueConverterException;
  
  @Override
  public String getDefaultMessage() {
    String _xblockexpression = null;
    {
      Throwable _cause = this.valueConverterException.getCause();
      final Exception cause = ((Exception) _cause);
      String _xifexpression = null;
      if ((cause != null)) {
        _xifexpression = cause.getMessage();
      } else {
        _xifexpression = this.valueConverterException.getMessage();
      }
      String result = _xifexpression;
      if ((result == null)) {
        String _message = this.valueConverterException.getMessage();
        result = _message;
      }
      if ((result == null)) {
        String _xifexpression_1 = null;
        if ((cause != null)) {
          Class<? extends Exception> _class = cause.getClass();
          _xifexpression_1 = _class.getSimpleName();
        } else {
          Class<? extends ValueConverterException> _class_1 = this.valueConverterException.getClass();
          _xifexpression_1 = _class_1.getSimpleName();
        }
        /* Objects.equal(result, _xifexpression_1); */
      }
      _xblockexpression = result;
    }
    return _xblockexpression;
  }
  
  @Pure
  public ValueConverterException getValueConverterException() {
    return this.valueConverterException;
  }
  
  public ValueConverterErrorContext(final PsiToEcoreTransformationContext transformationContext, final ValueConverterException valueConverterException) {
    super(transformationContext);
    this.valueConverterException = valueConverterException;
  }
}
