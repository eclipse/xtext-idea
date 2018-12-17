/*******************************************************************************
 * Copyright (c) 2015 itemis AG (http://www.itemis.eu) and others.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 *******************************************************************************/
package org.eclipse.xtext.psi;

import com.google.inject.Singleton;
import com.intellij.openapi.module.Module;
import com.intellij.openapi.vfs.VirtualFile;
import com.intellij.openapi.vfs.VirtualFileManager;
import com.intellij.psi.PsiElement;
import com.intellij.psi.PsiFile;
import com.intellij.psi.PsiManager;
import com.intellij.psi.impl.source.tree.CompositeElement;
import org.eclipse.emf.common.notify.impl.AdapterImpl;
import org.eclipse.emf.common.util.URI;
import org.eclipse.emf.ecore.EObject;
import org.eclipse.emf.ecore.resource.Resource;
import org.eclipse.emf.ecore.resource.ResourceSet;
import org.eclipse.emf.ecore.util.EcoreUtil;
import org.eclipse.xtext.idea.resource.ModuleProvider;
import org.eclipse.xtext.psi.impl.BaseXtextFile;
import org.eclipse.xtext.resource.IEObjectDescription;
import org.eclipse.xtext.service.OperationCanceledError;

@Singleton
public class PsiModelAssociations implements IPsiModelAssociations, IPsiModelAssociator {
	
	public static class PsiAdapter extends AdapterImpl {
		
		private PsiElement psiElement;
		
		private final CompositeElement composite;

		private final PsiElementProvider psiElementProvider;

		public PsiAdapter(final PsiElement psiElement) {
			this(new PsiElementProvider() {
				
				@Override
				public PsiElement get() {
					return psiElement;
				}
				
			});
		}

		public PsiAdapter(PsiElementProvider psiElementProvider) {
			this.composite = null;
			this.psiElementProvider = psiElementProvider;
		}

		public PsiAdapter(CompositeElement composite) {
			this.composite = composite;
			this.psiElementProvider = null;
		}
		
		@Override
		public boolean isAdapterForType(Object type) {
			return getClass() == type;
		}
		
		public CompositeElement getComposite() {
			return composite;
		}
		
		public PsiElement getPsi() {
			if (composite != null) {
				return composite.getPsi();
			}
			if (psiElement == null && psiElementProvider != null) {
				synchronized(psiElementProvider) {
					if (psiElement == null) {
						psiElement = psiElementProvider.get();
					}
				}
			}
			return psiElement;
		}
		
		public static CompositeElement getComposite(EObject object) {
			PsiAdapter psiAdapter = get(object);
			if (psiAdapter != null) {
				return psiAdapter.getComposite();
			}
			return null;
		}
		
		public static PsiElement getPsi(EObject object) {
			PsiAdapter psiAdapter = get(object);
			if (psiAdapter != null) {
				return psiAdapter.getPsi();
			}
			return null;
		}
		
		public static PsiAdapter get(EObject object) {
			if (object == null) {
				return null;
			}
			return (PsiAdapter) EcoreUtil.getAdapter(object.eAdapters(), PsiAdapter.class);
		}
		
	}
	
    @Override
	public EObject getEObject(PsiElement element) {
    	if (element == null) {
            return null;
        }
    	if (element instanceof PsiEObject) {
    		PsiEObject psiEObject = (PsiEObject) element;
			return psiEObject.getEObject();	
    	}
    	return null;
    }

    @Override
	public PsiElement getPsiElement(EObject object) {
    	try {
	    	if (object == null || object.eIsProxy()) {
	    		return null;
	    	}
	    	PsiElement psi = PsiAdapter.getPsi(object);
	    	if (psi != null) {
	    		return psi;
	    	}
	    	
	    	URI uri = EcoreUtil.getURI(object);
	    	Resource eResource = object.eResource();
	    	if (eResource == null) {
	    		return null;
	    	}
			BaseXtextFile xtextFile = getBaseXtextFile(eResource.getResourceSet(), uri);
	    	if (xtextFile == null) {
	    		return null;
	    	}
	    	EObject resolvedObject = xtextFile.getEObject(uri);
			return PsiAdapter.getPsi(resolvedObject);
    	} catch (OperationCanceledError e) {
    		throw e.getWrapped();
    	}
    }

	protected BaseXtextFile getBaseXtextFile(ResourceSet resourceSet, URI uri) {
		VirtualFile file = VirtualFileManager.getInstance().findFileByUrl(uri.trimFragment().toString());
    	if (file == null) {
    		return null;
    	}
    	Module module = ModuleProvider.findModule(resourceSet);
    	if (module == null) {
    		throw new IllegalStateException("Module was null");
    	}
    	PsiFile psiFile = PsiManager.getInstance(module.getProject()).findFile(file);
    	if (psiFile == null || !(psiFile instanceof BaseXtextFile)) {
    		return null;
    	}
    	return (BaseXtextFile) psiFile;
	}
    
	@Override
	public PsiElement getPsiElement(IEObjectDescription objectDescription, Resource context) {
		if (objectDescription == null) {
			return null;
		}
		// TODO maybe use EcoreUtil.resolve on objectDescription.getEObjectOrProxy
		EObject object = context.getResourceSet().getEObject(objectDescription.getEObjectURI(), true);
		return getPsiElement(object);
	}

	@Override
	public boolean associate(EObject eObject, PsiElementProvider psiElementProvider) {
		if (eObject == null || psiElementProvider == null) {
			return false;
		}
		if (PsiAdapter.get(eObject) != null) {
			return false;
		}
		return eObject.eAdapters().add(new PsiAdapter(psiElementProvider));
	}

}
