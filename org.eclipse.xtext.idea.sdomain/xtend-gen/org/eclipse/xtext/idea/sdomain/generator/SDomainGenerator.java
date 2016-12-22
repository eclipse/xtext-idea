/**
 * Copyright (c) 2015 itemis AG (http://www.itemis.eu) and others.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 */
package org.eclipse.xtext.idea.sdomain.generator;

import com.google.common.collect.Iterators;
import org.eclipse.emf.ecore.resource.Resource;
import org.eclipse.xtend2.lib.StringConcatenation;
import org.eclipse.xtext.generator.AbstractGenerator;
import org.eclipse.xtext.generator.IFileSystemAccess2;
import org.eclipse.xtext.generator.IGeneratorContext;
import org.eclipse.xtext.idea.sdomain.sDomain.Entity;
import org.eclipse.xtext.xbase.lib.Functions.Function1;
import org.eclipse.xtext.xbase.lib.IteratorExtensions;

/**
 * Generates code from your model files on save.
 * 
 * See https://www.eclipse.org/Xtext/documentation/303_runtime_concepts.html#code-generation
 */
@SuppressWarnings("all")
public class SDomainGenerator extends AbstractGenerator {
  @Override
  public void doGenerate(final Resource resource, final IFileSystemAccess2 fsa, final IGeneratorContext context) {
    String _lastSegment = resource.getURI().trimFileExtension().lastSegment();
    String _plus = (_lastSegment + ".txt");
    StringConcatenation _builder = new StringConcatenation();
    _builder.append("Entities : ");
    final Function1<Entity, String> _function = (Entity it) -> {
      return it.getName();
    };
    String _join = IteratorExtensions.join(IteratorExtensions.<Entity, String>map(Iterators.<Entity>filter(resource.getAllContents(), Entity.class), _function), ", ");
    _builder.append(_join);
    _builder.newLineIfNotEmpty();
    _builder.append("updated ");
    long _currentTimeMillis = System.currentTimeMillis();
    _builder.append(_currentTimeMillis);
    _builder.newLineIfNotEmpty();
    fsa.generateFile(_plus, _builder);
  }
}
