/**
 * Copyright (c) 2015 itemis AG (http://www.itemis.eu) and others.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 */
package org.eclipse.xtext.xbase.idea.imports;

import com.google.common.base.Objects;
import com.google.inject.Inject;
import com.google.inject.name.Named;
import com.intellij.lang.ImportOptimizer;
import com.intellij.openapi.editor.Document;
import com.intellij.openapi.util.EmptyRunnable;
import com.intellij.psi.PsiDocumentManager;
import com.intellij.psi.PsiFile;
import java.util.List;
import org.eclipse.xtext.Constants;
import org.eclipse.xtext.psi.impl.BaseXtextFile;
import org.eclipse.xtext.util.ReplaceRegion;
import org.eclipse.xtext.xbase.imports.ImportOrganizer;
import org.eclipse.xtext.xbase.lib.Functions.Function1;
import org.eclipse.xtext.xbase.lib.IterableExtensions;

/**
 * @author Sven Efftinge - Initial contribution and API
 */
@SuppressWarnings("all")
public class XImportSectionOptimizer implements ImportOptimizer {
  @Inject
  @Named(Constants.LANGUAGE_NAME)
  private String languageId;
  
  @Inject
  private ImportOrganizer importOrganizer;
  
  @Override
  public Runnable processFile(final PsiFile file) {
    if ((file instanceof BaseXtextFile)) {
      final Function1<ReplaceRegion, Integer> _function = (ReplaceRegion it) -> {
        int _offset = it.getOffset();
        return Integer.valueOf((_offset * (-1)));
      };
      final List<ReplaceRegion> changes = IterableExtensions.<ReplaceRegion, Integer>sortBy(this.importOrganizer.getOrganizedImportChanges(((BaseXtextFile)file).getResource()), _function);
      final Runnable _function_1 = () -> {
        final Document document = PsiDocumentManager.getInstance(((BaseXtextFile)file).getProject()).getDocument(file);
        for (final ReplaceRegion change : changes) {
          document.replaceString(change.getOffset(), change.getEndOffset(), change.getText());
        }
      };
      return _function_1;
    } else {
      return EmptyRunnable.INSTANCE;
    }
  }
  
  @Override
  public boolean supports(final PsiFile file) {
    String _iD = file.getLanguage().getID();
    return Objects.equal(this.languageId, _iD);
  }
}
