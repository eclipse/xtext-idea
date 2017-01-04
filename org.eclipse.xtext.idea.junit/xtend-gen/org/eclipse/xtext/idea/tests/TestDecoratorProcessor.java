/**
 * Copyright (c) 2015 itemis AG (http://www.itemis.eu) and others.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 */
package org.eclipse.xtext.idea.tests;

import java.util.function.Consumer;
import org.eclipse.xtend.lib.macro.AbstractClassProcessor;
import org.eclipse.xtend.lib.macro.TransformationContext;
import org.eclipse.xtend.lib.macro.declaration.MethodDeclaration;
import org.eclipse.xtend.lib.macro.declaration.MutableClassDeclaration;
import org.eclipse.xtend.lib.macro.declaration.MutableFieldDeclaration;
import org.eclipse.xtend.lib.macro.declaration.MutableMethodDeclaration;
import org.eclipse.xtend.lib.macro.declaration.ResolvedMethod;
import org.eclipse.xtend.lib.macro.declaration.Type;
import org.eclipse.xtend.lib.macro.declaration.TypeReference;
import org.eclipse.xtend2.lib.StringConcatenationClient;
import org.eclipse.xtext.xbase.lib.Conversions;
import org.eclipse.xtext.xbase.lib.Extension;
import org.eclipse.xtext.xbase.lib.Functions.Function1;
import org.eclipse.xtext.xbase.lib.IterableExtensions;
import org.eclipse.xtext.xbase.lib.Procedures.Procedure1;
import org.junit.Ignore;
import org.junit.Test;

@SuppressWarnings("all")
public class TestDecoratorProcessor extends AbstractClassProcessor {
  @Override
  public void doTransform(final MutableClassDeclaration cls, @Extension final TransformationContext context) {
    final MutableFieldDeclaration delegate = cls.findDeclaredField("delegate");
    if ((delegate == null)) {
      context.addWarning(cls, "Delegate is not declared");
      return;
    }
    final Type atTest = context.findTypeGlobally(Test.class);
    final Type atIgnore = context.findTypeGlobally(Ignore.class);
    delegate.markAsRead();
    final Function1<ResolvedMethod, MethodDeclaration> _function = (ResolvedMethod it) -> {
      return it.getDeclaration();
    };
    final Function1<MethodDeclaration, Boolean> _function_1 = (MethodDeclaration it) -> {
      return Boolean.valueOf(((it.findAnnotation(atTest) != null) && (it.findAnnotation(atIgnore) == null)));
    };
    final Function1<MethodDeclaration, Boolean> _function_2 = (MethodDeclaration it) -> {
      MutableMethodDeclaration _findDeclaredMethod = cls.findDeclaredMethod(it.getSimpleName());
      return Boolean.valueOf((_findDeclaredMethod == null));
    };
    final Function1<MethodDeclaration, String> _function_3 = (MethodDeclaration it) -> {
      return it.getSimpleName();
    };
    final Consumer<MethodDeclaration> _function_4 = (MethodDeclaration declaredMethod) -> {
      final Procedure1<MutableMethodDeclaration> _function_5 = (MutableMethodDeclaration it) -> {
        StringConcatenationClient _client = new StringConcatenationClient() {
          @Override
          protected void appendTo(StringConcatenationClient.TargetStringConcatenation _builder) {
            _builder.append("delegate.");
            String _simpleName = declaredMethod.getSimpleName();
            _builder.append(_simpleName);
            _builder.append("();");
          }
        };
        it.setBody(_client);
        it.setExceptions(((TypeReference[])Conversions.unwrapArray(declaredMethod.getExceptions(), TypeReference.class)));
      };
      cls.addMethod(declaredMethod.getSimpleName(), _function_5);
    };
    IterableExtensions.<MethodDeclaration, String>sortBy(IterableExtensions.<MethodDeclaration>filter(IterableExtensions.<MethodDeclaration>filter(IterableExtensions.map(delegate.getType().getAllResolvedMethods(), _function), _function_1), _function_2), _function_3).forEach(_function_4);
  }
}
