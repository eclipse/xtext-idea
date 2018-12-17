/*******************************************************************************
 * Copyright (c) 2015 itemis AG (http://www.itemis.eu) and others.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 *******************************************************************************/
package org.eclipse.xtext.idea.linking.lazy;

import com.google.inject.Inject;
import com.google.inject.Provider;
import org.eclipse.emf.ecore.EObject;
import org.eclipse.emf.ecore.EReference;
import org.eclipse.emf.ecore.util.InternalEList;
import org.eclipse.xtext.resource.IEObjectDescription;
import org.eclipse.xtext.resource.ILocationInFileProvider;
import org.eclipse.xtext.scoping.IScope;
import org.eclipse.xtext.scoping.IScopeProvider;
import org.eclipse.xtext.service.OperationCanceledError;
import org.eclipse.xtext.util.ITextRegion;

import java.util.Collections;
import java.util.Iterator;

public class CrossReferenceDescription implements ICrossReferenceDescription {
	
	@Inject
	private IScopeProvider scopeProvider;
	
	@Inject
	private ILocationInFileProvider locationInFileProvider;
	
	private Integer index;
    private EObject context;
	private EReference reference;

	@Override
	public ITextRegion getTextRegion() {
    	try {
			if (index == null) {
				return locationInFileProvider.getSignificantTextRegion(context, reference, -1);	
			}
			return locationInFileProvider.getSignificantTextRegion(context, reference, index);
    	} catch (OperationCanceledError e) {
    		throw e.getWrapped();
    	}
	}

    @Override
	@SuppressWarnings("unchecked")
	public EObject resolve() {
    	try {
    		Object value = context.eGet(reference);
    		if (reference.isMany()) {
    			InternalEList<EObject> internalEList = (InternalEList<EObject>) value;
    			if (internalEList.size() > index) {
    				value = internalEList.get(index);
    			}
    		}
    		if (value instanceof EObject) {
    			return (EObject) value;
    		} else {
    			return null;
    		}
    	} catch (OperationCanceledError e) {
    		throw e.getWrapped();
    	}
    }

    @Override
	public Iterable<IEObjectDescription> getVariants() {
    	final Iterable<IEObjectDescription> iterable = getAllElements();
		return new Iterable<IEObjectDescription>() {

			@Override
			public Iterator<IEObjectDescription> iterator() {
				final Iterator<IEObjectDescription> iterator = iterable.iterator();
				return new Iterator<IEObjectDescription>() {

					@Override
					public boolean hasNext() {
						try {
							return iterator.hasNext();
						} catch (OperationCanceledError e) {
				    		throw e.getWrapped();
				    	} catch (UnsupportedOperationException e) {
				    		// FIXME specialize XtextPsiReference for Xtend instead of catching UnsupportedOperationException here 
				    		return false;
				    	} 
					}

					@Override
					public IEObjectDescription next() {
						try {
							return iterator.next();
						} catch (OperationCanceledError e) {
				    		throw e.getWrapped();
				    	}
					}

					@Override
					public void remove() {
						try {
							iterator.remove();
						} catch (OperationCanceledError e) {
				    		throw e.getWrapped();
				    	}
					}
					
				};
			}
    		
    	};
    }

	protected Iterable<IEObjectDescription> getAllElements() {
		try {
			IScope scope = scopeProvider.getScope(context, reference);
			return scope.getAllElements();
		} catch (OperationCanceledError e) {
    		throw e.getWrapped();
    	} catch (UnsupportedOperationException e) {
    		// FIXME specialize XtextPsiReference for Xtend instead of catching UnsupportedOperationException here 
    		return Collections.emptyList();
    	}
	}
    
    public static class CrossReferenceDescriptionProvider {
    	
    	@Inject
    	private Provider<CrossReferenceDescription> delegate;
    	
    	public ICrossReferenceDescription get(EObject context, EReference reference, Integer index) {
    		CrossReferenceDescription crossReferenceDescription = delegate.get();
    		crossReferenceDescription.index = index;
    		crossReferenceDescription.context = context;
    		crossReferenceDescription.reference = reference;
    		return crossReferenceDescription;
    	}
    	
    }

}