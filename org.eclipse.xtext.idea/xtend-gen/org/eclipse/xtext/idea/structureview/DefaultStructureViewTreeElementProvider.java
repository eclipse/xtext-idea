/**
 * Copyright (c) 2015 itemis AG (http://www.itemis.eu) and others.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 */
package org.eclipse.xtext.idea.structureview;

import com.google.common.collect.Iterables;
import com.google.inject.Inject;
import com.google.inject.Provider;
import com.google.inject.Singleton;
import com.intellij.ide.projectView.PresentationData;
import com.intellij.ide.structureView.StructureViewTreeElement;
import com.intellij.navigation.ItemPresentation;
import java.util.Arrays;
import java.util.List;
import org.eclipse.emf.ecore.EObject;
import org.eclipse.emf.ecore.EReference;
import org.eclipse.emf.ecore.EStructuralFeature;
import org.eclipse.xtext.idea.presentation.ItemPresentationProvider;
import org.eclipse.xtext.idea.structureview.EObjectTreeElement;
import org.eclipse.xtext.idea.structureview.EStructuralFeatureTreeElement;
import org.eclipse.xtext.idea.structureview.IStructureViewTreeElementProvider;
import org.eclipse.xtext.idea.structureview.XtextFileTreeElement;
import org.eclipse.xtext.psi.impl.BaseXtextFile;
import org.eclipse.xtext.xbase.lib.Extension;
import org.eclipse.xtext.xbase.lib.Functions.Function1;
import org.eclipse.xtext.xbase.lib.IterableExtensions;
import org.eclipse.xtext.xbase.lib.ListExtensions;
import org.eclipse.xtext.xbase.lib.ObjectExtensions;
import org.eclipse.xtext.xbase.lib.Procedures.Procedure1;

/**
 * @author kosyakov - Initial contribution and API
 */
@Singleton
@SuppressWarnings("all")
public class DefaultStructureViewTreeElementProvider implements IStructureViewTreeElementProvider {
  @Inject
  private Provider<EObjectTreeElement> objectTreeElementProvider;
  
  @Inject
  @Extension
  protected ItemPresentationProvider itemPresentationProvider;
  
  @Inject
  private Provider<EStructuralFeatureTreeElement> structuralFeatureTreeElementProvider;
  
  protected void _buildChildren(final StructureViewTreeElement structureViewTreeElement) {
  }
  
  protected void _buildChildren(final XtextFileTreeElement it) {
    final EObject modelElement = IterableExtensions.<EObject>head(it.getElement().getResource().getContents());
    if ((modelElement == null)) {
      return;
    }
    ItemPresentation _elvis = null;
    ItemPresentation _itemPresentation = this.itemPresentationProvider.getItemPresentation(modelElement);
    if (_itemPresentation != null) {
      _elvis = _itemPresentation;
    } else {
      PresentationData _presentationData = new PresentationData();
      _elvis = _presentationData;
    }
    final ItemPresentation itemPresentation = _elvis;
    if ((itemPresentation instanceof PresentationData)) {
      String _presentableText = ((PresentationData)itemPresentation).getPresentableText();
      boolean _tripleEquals = (_presentableText == null);
      if (_tripleEquals) {
        ((PresentationData)itemPresentation).setPresentableText(modelElement.eResource().getURI().trimFileExtension().lastSegment());
      }
    }
    it.addChild(
      this.createEObjectTreeElement(modelElement, 
        it.getElement(), 
        this.isLeaf(modelElement), itemPresentation));
  }
  
  protected void _buildChildren(final EObjectTreeElement it) {
    final Function1<EObject, StructureViewTreeElement> _function = (EObject child) -> {
      return this.createEObjectTreeElement(child, it.xtextFile);
    };
    it.addChildren(ListExtensions.<EObject, StructureViewTreeElement>map(it.getObject().eContents(), _function));
  }
  
  protected void _buildChildren(final EStructuralFeatureTreeElement it) {
    final Object values = it.getOwner().eGet(it.getFeature());
    if ((values instanceof List<?>)) {
      boolean _isMany = it.getFeature().isMany();
      if (_isMany) {
        final Function1<EObject, StructureViewTreeElement> _function = (EObject value) -> {
          return this.createEObjectTreeElement(value, it.xtextFile);
        };
        it.addChildren(IterableExtensions.<EObject, StructureViewTreeElement>map(Iterables.<EObject>filter(((Iterable<?>)values), EObject.class), _function));
      }
    } else {
      if ((values instanceof EObject)) {
        it.addChild(this.createEObjectTreeElement(((EObject)values), it.xtextFile));
      }
    }
  }
  
  protected EObjectTreeElement createEObjectTreeElement(final EObject modelElement, final BaseXtextFile xtextFile) {
    return this.createEObjectTreeElement(modelElement, xtextFile, 
      this.isLeaf(modelElement), 
      this.itemPresentationProvider.getItemPresentation(modelElement));
  }
  
  protected EObjectTreeElement createEObjectTreeElement(final EObject modelElement, final BaseXtextFile xtextFile, final boolean leaf, final ItemPresentation itemPresentation) {
    EObjectTreeElement _xblockexpression = null;
    {
      boolean _and = false;
      String _presentableText = null;
      if (itemPresentation!=null) {
        _presentableText=itemPresentation.getPresentableText();
      }
      boolean _tripleEquals = (_presentableText == null);
      if (!_tripleEquals) {
        _and = false;
      } else {
        _and = leaf;
      }
      if (_and) {
        return null;
      }
      EObjectTreeElement _get = this.objectTreeElementProvider.get();
      final Procedure1<EObjectTreeElement> _function = (EObjectTreeElement objectTreeElement) -> {
        objectTreeElement.setObject(modelElement);
        objectTreeElement.xtextFile = xtextFile;
        objectTreeElement.setLeaf(leaf);
        objectTreeElement.setItemPresentation(itemPresentation);
        objectTreeElement.setStructureViewTreeElementProvider(this);
      };
      _xblockexpression = ObjectExtensions.<EObjectTreeElement>operator_doubleArrow(_get, _function);
    }
    return _xblockexpression;
  }
  
  protected EStructuralFeatureTreeElement createEStructuralFeatureTreeElement(final EObject modelElement, final EStructuralFeature feature, final BaseXtextFile xtextFile, final boolean leaf, final ItemPresentation itemPresentation) {
    EStructuralFeatureTreeElement _get = this.structuralFeatureTreeElementProvider.get();
    final Procedure1<EStructuralFeatureTreeElement> _function = (EStructuralFeatureTreeElement structuralFeatureTreeElement) -> {
      structuralFeatureTreeElement.setOwner(modelElement);
      structuralFeatureTreeElement.setFeature(feature);
      structuralFeatureTreeElement.xtextFile = xtextFile;
      structuralFeatureTreeElement.setLeaf(leaf);
      structuralFeatureTreeElement.setItemPresentation(itemPresentation);
      structuralFeatureTreeElement.setStructureViewTreeElementProvider(this);
    };
    return ObjectExtensions.<EStructuralFeatureTreeElement>operator_doubleArrow(_get, _function);
  }
  
  protected boolean isLeaf(final EObject modelElement) {
    final Function1<EReference, Boolean> _function = (EReference containmentRef) -> {
      return Boolean.valueOf(modelElement.eIsSet(containmentRef));
    };
    boolean _exists = IterableExtensions.<EReference>exists(modelElement.eClass().getEAllContainments(), _function);
    return (!_exists);
  }
  
  public void buildChildren(final StructureViewTreeElement it) {
    if (it instanceof EObjectTreeElement) {
      _buildChildren((EObjectTreeElement)it);
      return;
    } else if (it instanceof EStructuralFeatureTreeElement) {
      _buildChildren((EStructuralFeatureTreeElement)it);
      return;
    } else if (it instanceof XtextFileTreeElement) {
      _buildChildren((XtextFileTreeElement)it);
      return;
    } else if (it != null) {
      _buildChildren(it);
      return;
    } else {
      throw new IllegalArgumentException("Unhandled parameter types: " +
        Arrays.<Object>asList(it).toString());
    }
  }
}
