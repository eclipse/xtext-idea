/**
 * Copyright (c) 2015 itemis AG (http://www.itemis.eu) and others.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 */
package org.eclipse.xtext.idea.sdomain.idea.tests.scoping;

import com.google.common.collect.Iterators;
import com.intellij.lang.Language;
import com.intellij.psi.PsiElement;
import com.intellij.psi.PsiFile;
import java.util.Set;
import junit.framework.TestCase;
import org.eclipse.emf.ecore.EObject;
import org.eclipse.xtend2.lib.StringConcatenation;
import org.eclipse.xtext.idea.lang.IXtextLanguage;
import org.eclipse.xtext.idea.linking.lazy.CrossReferenceDescription;
import org.eclipse.xtext.idea.linking.lazy.ICrossReferenceDescription;
import org.eclipse.xtext.idea.sdomain.idea.tests.containers.LightSdomainTestCase;
import org.eclipse.xtext.idea.sdomain.sDomain.Entity;
import org.eclipse.xtext.idea.sdomain.sDomain.SDomainPackage;
import org.eclipse.xtext.naming.IQualifiedNameConverter;
import org.eclipse.xtext.naming.QualifiedName;
import org.eclipse.xtext.psi.PsiEObject;
import org.eclipse.xtext.resource.IEObjectDescription;
import org.eclipse.xtext.xbase.lib.Functions.Function1;
import org.eclipse.xtext.xbase.lib.IterableExtensions;
import org.eclipse.xtext.xbase.lib.IteratorExtensions;

@SuppressWarnings("all")
public class ScopeProviderTest extends LightSdomainTestCase {
  public void testImports() {
    final PsiFile file = this.myFixture.configureByText("import.sdomain", "import foo.bar.*");
    StringConcatenation _builder = new StringConcatenation();
    _builder.append("foo.bar {");
    _builder.newLine();
    _builder.append("\t");
    _builder.append("entity Person {");
    _builder.newLine();
    _builder.append("\t\t");
    _builder.append("String name");
    _builder.newLine();
    _builder.append("\t");
    _builder.append("}");
    _builder.newLine();
    _builder.append("\t");
    _builder.append("datatype String");
    _builder.newLine();
    _builder.append("}");
    _builder.newLine();
    this.myFixture.configureByText("foo.sdomain", _builder.toString());
    Language _language = file.getLanguage();
    final IXtextLanguage language = ((IXtextLanguage) _language);
    final IQualifiedNameConverter nameConverter = language.<IQualifiedNameConverter>getInstance(IQualifiedNameConverter.class);
    final CrossReferenceDescription.CrossReferenceDescriptionProvider crossReferenceDescriptionProvider = language.<CrossReferenceDescription.CrossReferenceDescriptionProvider>getInstance(CrossReferenceDescription.CrossReferenceDescriptionProvider.class);
    PsiElement _firstChild = file.getFirstChild();
    final EObject fileRoot = IterableExtensions.<EObject>head(((PsiEObject) _firstChild).getResource().getContents());
    final ICrossReferenceDescription crossReferenceDescription = crossReferenceDescriptionProvider.get(fileRoot, SDomainPackage.Literals.FILE__ELEMENTS, null);
    final Function1<IEObjectDescription, QualifiedName> _function = (IEObjectDescription it) -> {
      return it.getName();
    };
    final Set<QualifiedName> names = IterableExtensions.<QualifiedName>toSet(IterableExtensions.<IEObjectDescription, QualifiedName>map(crossReferenceDescription.getVariants(), _function));
    TestCase.assertEquals(names.toString(), 5, names.size());
    TestCase.assertTrue(names.contains(nameConverter.toQualifiedName("Person")));
    TestCase.assertTrue(names.contains(nameConverter.toQualifiedName("String")));
    TestCase.assertTrue(names.contains(nameConverter.toQualifiedName("foo.bar")));
    TestCase.assertTrue(names.contains(nameConverter.toQualifiedName("foo.bar.Person")));
    TestCase.assertTrue(names.contains(nameConverter.toQualifiedName("foo.bar.String")));
  }
  
  public void testRelativeContext() {
    StringConcatenation _builder = new StringConcatenation();
    _builder.append("stuff {");
    _builder.newLine();
    _builder.append("\t");
    _builder.append("baz {");
    _builder.newLine();
    _builder.append("\t\t");
    _builder.append("datatype String");
    _builder.newLine();
    _builder.append("\t");
    _builder.append("}");
    _builder.newLine();
    _builder.append("\t");
    _builder.append("entity Person {}");
    _builder.newLine();
    _builder.append("}");
    _builder.newLine();
    final PsiFile file = this.myFixture.configureByText("relative.sdomain", _builder.toString());
    Language _language = file.getLanguage();
    final IXtextLanguage language = ((IXtextLanguage) _language);
    final IQualifiedNameConverter nameConverter = language.<IQualifiedNameConverter>getInstance(IQualifiedNameConverter.class);
    final CrossReferenceDescription.CrossReferenceDescriptionProvider crossReferenceDescriptionProvider = language.<CrossReferenceDescription.CrossReferenceDescriptionProvider>getInstance(CrossReferenceDescription.CrossReferenceDescriptionProvider.class);
    PsiElement _firstChild = file.getFirstChild();
    final Entity entity = IteratorExtensions.<Entity>head(Iterators.<Entity>filter(((PsiEObject) _firstChild).getResource().getAllContents(), Entity.class));
    final ICrossReferenceDescription crossReferenceDescription = crossReferenceDescriptionProvider.get(entity, SDomainPackage.Literals.PROPERTY__TYPE, null);
    final Function1<IEObjectDescription, QualifiedName> _function = (IEObjectDescription it) -> {
      return it.getName();
    };
    final Set<QualifiedName> names = IterableExtensions.<QualifiedName>toSet(IterableExtensions.<IEObjectDescription, QualifiedName>map(crossReferenceDescription.getVariants(), _function));
    TestCase.assertEquals(names.toString(), 4, names.size());
    TestCase.assertTrue(names.contains(nameConverter.toQualifiedName("Person")));
    TestCase.assertTrue(names.contains(nameConverter.toQualifiedName("stuff.Person")));
    TestCase.assertTrue(names.contains(nameConverter.toQualifiedName("baz.String")));
    TestCase.assertTrue(names.contains(nameConverter.toQualifiedName("stuff.baz.String")));
  }
  
  public void testRelativePath() {
    StringConcatenation _builder = new StringConcatenation();
    _builder.append("stuff {");
    _builder.newLine();
    _builder.append("\t");
    _builder.append("import baz.*");
    _builder.newLine();
    _builder.append("\t");
    _builder.append("baz {");
    _builder.newLine();
    _builder.append("\t\t");
    _builder.append("datatype String");
    _builder.newLine();
    _builder.append("\t");
    _builder.append("}");
    _builder.newLine();
    _builder.append("\t");
    _builder.append("entity Person {}");
    _builder.newLine();
    _builder.append("}");
    _builder.newLine();
    final PsiFile file = this.myFixture.configureByText("relative.sdomain", _builder.toString());
    Language _language = file.getLanguage();
    final IXtextLanguage language = ((IXtextLanguage) _language);
    final IQualifiedNameConverter nameConverter = language.<IQualifiedNameConverter>getInstance(IQualifiedNameConverter.class);
    final CrossReferenceDescription.CrossReferenceDescriptionProvider crossReferenceDescriptionProvider = language.<CrossReferenceDescription.CrossReferenceDescriptionProvider>getInstance(CrossReferenceDescription.CrossReferenceDescriptionProvider.class);
    PsiElement _firstChild = file.getFirstChild();
    final Entity entity = IteratorExtensions.<Entity>head(Iterators.<Entity>filter(((PsiEObject) _firstChild).getResource().getAllContents(), Entity.class));
    final ICrossReferenceDescription crossReferenceDescription = crossReferenceDescriptionProvider.get(entity, SDomainPackage.Literals.PROPERTY__TYPE, null);
    final Function1<IEObjectDescription, QualifiedName> _function = (IEObjectDescription it) -> {
      return it.getName();
    };
    final Set<QualifiedName> names = IterableExtensions.<QualifiedName>toSet(IterableExtensions.<IEObjectDescription, QualifiedName>map(crossReferenceDescription.getVariants(), _function));
    TestCase.assertEquals(names.toString(), 5, names.size());
    TestCase.assertTrue(names.contains(nameConverter.toQualifiedName("Person")));
    TestCase.assertTrue(names.contains(nameConverter.toQualifiedName("stuff.Person")));
    TestCase.assertTrue(names.contains(nameConverter.toQualifiedName("String")));
    TestCase.assertTrue(names.contains(nameConverter.toQualifiedName("baz.String")));
    TestCase.assertTrue(names.contains(nameConverter.toQualifiedName("stuff.baz.String")));
  }
}
