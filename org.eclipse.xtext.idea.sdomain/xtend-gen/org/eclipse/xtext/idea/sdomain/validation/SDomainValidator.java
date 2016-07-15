/**
 * Copyright (c) 2015 itemis AG (http://www.itemis.eu) and others.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 */
package org.eclipse.xtext.idea.sdomain.validation;

import org.eclipse.xtext.idea.sdomain.sDomain.Entity;
import org.eclipse.xtext.idea.sdomain.sDomain.SDomainPackage;
import org.eclipse.xtext.idea.sdomain.validation.AbstractSDomainValidator;
import org.eclipse.xtext.validation.Check;

/**
 * Custom validation rules.
 * 
 * See https://www.eclipse.org/Xtext/documentation/303_runtime_concepts.html#validation
 */
@SuppressWarnings("all")
public class SDomainValidator extends AbstractSDomainValidator {
  public final static String INVALID_NAME = "invalidName";
  
  @Check
  public void checkEntityNameStartsWithCapital(final Entity entity) {
    String _name = entity.getName();
    char _charAt = _name.charAt(0);
    boolean _isUpperCase = Character.isUpperCase(_charAt);
    boolean _not = (!_isUpperCase);
    if (_not) {
      this.warning("Name should start with a capital", 
        SDomainPackage.Literals.TYPE__NAME, 
        SDomainValidator.INVALID_NAME);
    }
  }
}
