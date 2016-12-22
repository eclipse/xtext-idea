/**
 * Copyright (c) 2015 itemis AG (http://www.itemis.eu) and others.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 */
package org.eclipse.xtext.idea.formatting;

import com.intellij.formatting.IndentInfo;
import com.intellij.openapi.project.Project;
import com.intellij.psi.codeStyle.CodeStyleSettingsManager;
import com.intellij.psi.codeStyle.CommonCodeStyleSettings;
import org.eclipse.xtext.formatting.IIndentationInformation;
import org.eclipse.xtext.idea.actionSystem.DataContextExtensions;

/**
 * @author kosyakov - Initial contribution and API
 */
@SuppressWarnings("all")
public class CodeStyleSettingsIndentationInformation implements IIndentationInformation {
  @Override
  public String getIndentString() {
    String _xblockexpression = null;
    {
      final Project project = DataContextExtensions.getProject(DataContextExtensions.getDataContext());
      final CommonCodeStyleSettings.IndentOptions indentOptions = CodeStyleSettingsManager.getSettings(project).getIndentOptions();
      final IndentInfo indentInfo = new IndentInfo(0, 4, 0);
      _xblockexpression = indentInfo.generateNewWhiteSpace(indentOptions);
    }
    return _xblockexpression;
  }
}
