/**
 * Copyright (c) 2015 itemis AG (http://www.itemis.eu) and others.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 */
package org.eclipse.xtext.idea.tests.parsing;

import com.google.common.base.Objects;
import com.google.inject.Inject;
import com.intellij.lang.ASTNode;
import com.intellij.psi.PsiErrorElement;
import java.util.List;
import org.eclipse.emf.ecore.EAttribute;
import org.eclipse.emf.ecore.EObject;
import org.eclipse.emf.ecore.EStructuralFeature;
import org.eclipse.xtend.lib.annotations.AccessorType;
import org.eclipse.xtend.lib.annotations.Accessors;
import org.eclipse.xtend2.lib.StringConcatenation;
import org.eclipse.xtext.EcoreUtil2;
import org.eclipse.xtext.idea.nodemodel.ASTNodeExtension;
import org.eclipse.xtext.idea.nodemodel.IASTNodeAwareNodeModelBuilder;
import org.eclipse.xtext.idea.resource.PsiToEcoreAdapter;
import org.eclipse.xtext.idea.tests.parsing.NodeModelPrinter;
import org.eclipse.xtext.nodemodel.ICompositeNode;
import org.eclipse.xtext.nodemodel.INode;
import org.eclipse.xtext.nodemodel.impl.InvariantChecker;
import org.eclipse.xtext.nodemodel.util.NodeModelUtils;
import org.eclipse.xtext.resource.XtextResource;
import org.eclipse.xtext.util.EmfFormatter;
import org.eclipse.xtext.xbase.lib.CollectionLiterals;
import org.eclipse.xtext.xbase.lib.Conversions;
import org.eclipse.xtext.xbase.lib.Extension;
import org.eclipse.xtext.xbase.lib.Functions.Function1;
import org.eclipse.xtext.xbase.lib.IterableExtensions;
import org.eclipse.xtext.xbase.lib.ListExtensions;
import org.eclipse.xtext.xbase.lib.Pure;
import org.junit.Assert;

@SuppressWarnings("all")
public class XtextResourceAsserts extends Assert {
  @Inject
  @Accessors(AccessorType.PUBLIC_GETTER)
  @Extension
  private NodeModelPrinter nodeModelPrinter;
  
  @Inject
  @Extension
  private InvariantChecker invariantChecker;
  
  @Inject
  @Extension
  private ASTNodeExtension astNodeExtension;
  
  public void assertResource(final XtextResource expectedResource, final XtextResource actualResource) {
    this.assertResource(expectedResource, actualResource, true);
  }
  
  public void assertResource(final XtextResource expectedResource, final XtextResource actualResource, final boolean resolve) {
    final ICompositeNode expectedRootNode = expectedResource.getParseResult().getRootNode();
    final ICompositeNode actualRootNode = actualResource.getParseResult().getRootNode();
    Assert.assertEquals(this.nodeModelPrinter.print(expectedRootNode), this.nodeModelPrinter.print(actualRootNode));
    if (resolve) {
      EcoreUtil2.resolveLazyCrossReferences(expectedResource, null);
      EcoreUtil2.resolveLazyCrossReferences(actualResource, null);
    }
    final EObject expectedRootASTElement = expectedResource.getParseResult().getRootASTElement();
    final EObject actualRootASTElement = actualResource.getParseResult().getRootASTElement();
    Assert.assertEquals(EmfFormatter.objToStr(expectedRootASTElement), EmfFormatter.objToStr(actualRootASTElement));
    this.invariantChecker.checkInvariant(actualRootNode);
    final PsiToEcoreAdapter psiToEcoreAdapter = PsiToEcoreAdapter.findInEmfObject(actualResource);
    final ASTNode rootASTNode = psiToEcoreAdapter.getXtextFile().getFirstChild().getNode();
    this.assertASTNode(rootASTNode, actualRootNode, psiToEcoreAdapter);
    Assert.assertEquals(
      this.printAST(rootASTNode), 
      this.printAST(psiToEcoreAdapter.getINode(rootASTNode), psiToEcoreAdapter));
  }
  
  protected void assertASTNode(final ASTNode astNode, final ICompositeNode rootNode, final PsiToEcoreAdapter psiToEcoreAdapter) {
    if ((!(astNode instanceof PsiErrorElement))) {
      final INode node = psiToEcoreAdapter.getINode(astNode);
      StringConcatenation _builder = new StringConcatenation();
      _builder.append("The node is null the ast node: ");
      _builder.append(astNode);
      Assert.assertNotNull(_builder.toString(), node);
      StringConcatenation _builder_1 = new StringConcatenation();
      _builder_1.append("The node ");
      _builder_1.append(node);
      _builder_1.append(" is not a part of the tree for the ast node: ");
      _builder_1.append(astNode);
      Assert.assertTrue(_builder_1.toString(), 
        this.belongsTo(node, rootNode));
      Assert.assertEquals(astNode, psiToEcoreAdapter.getASTNode(node));
      final EObject semanticElement = node.getSemanticElement();
      if ((semanticElement != null)) {
        final EStructuralFeature feature = semanticElement.eClass().getEStructuralFeature("name");
        if ((((feature instanceof EAttribute) && (!feature.isMany())) && String.class.isAssignableFrom(feature.getEType().getInstanceClass()))) {
          final List<INode> nodes = NodeModelUtils.findNodesForFeature(node.getSemanticElement(), feature);
          final List<ASTNode> astNodes = this.astNodeExtension.findNodesForFeature(astNode, feature);
          Assert.assertEquals(nodes.size(), astNodes.size());
          for (int i = 0; (i < nodes.size()); i++) {
            Assert.assertEquals(
              astNodes.get(i), 
              psiToEcoreAdapter.getASTNode(nodes.get(i)));
          }
        }
      }
    }
    ASTNode[] _children = astNode.getChildren(null);
    for (final ASTNode child : _children) {
      this.assertASTNode(child, rootNode, psiToEcoreAdapter);
    }
  }
  
  protected String printAST(final ASTNode astNode) {
    String _xblockexpression = null;
    {
      if ((astNode instanceof PsiErrorElement)) {
        ASTNode _firstChildNode = astNode.getFirstChildNode();
        String _printAST = null;
        if (_firstChildNode!=null) {
          _printAST=this.printAST(_firstChildNode);
        }
        return _printAST;
      }
      StringConcatenation _builder = new StringConcatenation();
      _builder.append(astNode);
      _builder.append(" {");
      _builder.newLineIfNotEmpty();
      _builder.append("\t");
      _builder.append("hasSemanticElement: ");
      Boolean _elvis = null;
      Boolean _userData = astNode.<Boolean>getUserData(IASTNodeAwareNodeModelBuilder.HAS_SEMANTIC_ELEMENT_KEY);
      if (_userData != null) {
        _elvis = _userData;
      } else {
        _elvis = Boolean.valueOf(false);
      }
      _builder.append(_elvis, "\t");
      _builder.newLineIfNotEmpty();
      {
        final Function1<ASTNode, String> _function = (ASTNode it) -> {
          return this.printAST(it);
        };
        Iterable<String> _filterNull = IterableExtensions.<String>filterNull(ListExtensions.<ASTNode, String>map(((List<ASTNode>)Conversions.doWrapArray(astNode.getChildren(null))), _function));
        for(final String child : _filterNull) {
          _builder.append("\t");
          _builder.append(child, "\t");
          _builder.newLineIfNotEmpty();
        }
      }
      _builder.append("}");
      _builder.newLine();
      _xblockexpression = _builder.toString();
    }
    return _xblockexpression;
  }
  
  protected String printAST(final INode node, final PsiToEcoreAdapter psiToEcoreAdapter) {
    String _xblockexpression = null;
    {
      final ASTNode astNode = psiToEcoreAdapter.getASTNode(node);
      Iterable<INode> _xifexpression = null;
      if ((node instanceof ICompositeNode)) {
        _xifexpression = ((ICompositeNode)node).getChildren();
      } else {
        _xifexpression = CollectionLiterals.<INode>emptyList();
      }
      final Iterable<INode> children = _xifexpression;
      StringConcatenation _builder = new StringConcatenation();
      _builder.append(astNode);
      _builder.append(" {");
      _builder.newLineIfNotEmpty();
      _builder.append("\t");
      _builder.append("hasSemanticElement: ");
      boolean _hasDirectSemanticElement = node.hasDirectSemanticElement();
      _builder.append(_hasDirectSemanticElement, "\t");
      _builder.newLineIfNotEmpty();
      {
        for(final INode child : children) {
          _builder.append("\t");
          String _printAST = this.printAST(child, psiToEcoreAdapter);
          _builder.append(_printAST, "\t");
          _builder.newLineIfNotEmpty();
        }
      }
      _builder.append("}");
      _builder.newLine();
      _xblockexpression = _builder.toString();
    }
    return _xblockexpression;
  }
  
  protected boolean belongsTo(final INode node, final ICompositeNode rootNode) {
    final Function1<INode, Boolean> _function = (INode it) -> {
      return Boolean.valueOf(Objects.equal(it, node));
    };
    return IterableExtensions.<INode>exists(rootNode.getAsTreeIterable(), _function);
  }
  
  @Pure
  public NodeModelPrinter getNodeModelPrinter() {
    return this.nodeModelPrinter;
  }
}
