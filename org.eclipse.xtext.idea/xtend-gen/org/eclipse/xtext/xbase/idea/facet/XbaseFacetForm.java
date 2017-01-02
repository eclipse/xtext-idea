/**
 * Copyright (c) 2015 itemis AG (http://www.itemis.eu) and others.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 */
package org.eclipse.xtext.xbase.idea.facet;

import com.google.common.base.Objects;
import com.intellij.facet.ui.FacetValidatorsManager;
import com.intellij.openapi.module.Module;
import com.intellij.openapi.roots.LanguageLevelModuleExtension;
import com.intellij.openapi.roots.ModuleRootManager;
import com.intellij.openapi.roots.impl.LanguageLevelProjectExtensionImpl;
import com.intellij.openapi.roots.ui.configuration.LanguageLevelCombo;
import com.intellij.pom.java.LanguageLevel;
import com.intellij.ui.components.JBTextField;
import java.awt.GridBagConstraints;
import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;
import javax.swing.JCheckBox;
import javax.swing.JComponent;
import javax.swing.JPanel;
import org.eclipse.xtext.idea.facet.GeneratorConfigurationState;
import org.eclipse.xtext.idea.facet.GeneratorFacetForm;
import org.eclipse.xtext.idea.util.IdeaWidgetFactory;
import org.eclipse.xtext.xbase.idea.facet.XbaseGeneratorConfigurationState;
import org.eclipse.xtext.xbase.lib.Conversions;
import org.eclipse.xtext.xbase.lib.Extension;
import org.eclipse.xtext.xbase.lib.Functions.Function1;
import org.eclipse.xtext.xbase.lib.IterableExtensions;

/**
 * @author dhuebner - Initial contribution and API
 */
@SuppressWarnings("all")
public class XbaseFacetForm extends GeneratorFacetForm {
  @Extension
  private IdeaWidgetFactory _ideaWidgetFactory = new IdeaWidgetFactory();
  
  private LanguageLevelCombo targetJavaVersion;
  
  private JCheckBox generateSuppressWarnings;
  
  private JCheckBox generateGeneratedAnnotation;
  
  private JCheckBox includeDateInGenerated;
  
  private JBTextField generatedAnnotationComment;
  
  private JCheckBox installDslAsPrimarySource;
  
  private JCheckBox hideLocalSyntheticVariables;
  
  public XbaseFacetForm(final Module module) {
    super(module);
  }
  
  public XbaseFacetForm(final Module module, final FacetValidatorsManager validatorsManager) {
    super(module, validatorsManager);
  }
  
  @Override
  protected JComponent createComponent() {
    final JComponent comp = super.createComponent();
    this.includeDateInGenerated.setEnabled(false);
    this.generatedAnnotationComment.setEnabled(false);
    this.hideLocalSyntheticVariables.setEnabled(false);
    final ItemListener _function = (ItemEvent it) -> {
      this.includeDateInGenerated.setEnabled(this.generateGeneratedAnnotation.isSelected());
      this.generatedAnnotationComment.setEnabled(this.generateGeneratedAnnotation.isSelected());
    };
    this.generateGeneratedAnnotation.addItemListener(_function);
    final ItemListener _function_1 = (ItemEvent it) -> {
      this.hideLocalSyntheticVariables.setEnabled(this.installDslAsPrimarySource.isSelected());
    };
    this.installDslAsPrimarySource.addItemListener(_function_1);
    return comp;
  }
  
  @Override
  public IdeaWidgetFactory.TwoColumnPanel createGeneralSection(@Extension final IdeaWidgetFactory.TwoColumnPanel it) {
    IdeaWidgetFactory.TwoColumnPanel _xblockexpression = null;
    {
      super.createGeneralSection(it);
      final Function1<GridBagConstraints, JComponent> _function = (GridBagConstraints it_1) -> {
        final Function1<GridBagConstraints, JComponent> _function_1 = (GridBagConstraints it_2) -> {
          return this._ideaWidgetFactory.label("Language level:");
        };
        final Function1<GridBagConstraints, JComponent> _function_2 = (GridBagConstraints it_2) -> {
          LanguageLevelCombo _xblockexpression_1 = null;
          {
            this._ideaWidgetFactory.expand(it_2, GridBagConstraints.HORIZONTAL);
            _xblockexpression_1 = this.targetJavaVersion = this.createLanguageLevelCombo();
          }
          return _xblockexpression_1;
        };
        return this._ideaWidgetFactory.container(_function_1, _function_2);
      };
      it.row(it, _function);
      final Function1<GridBagConstraints, JComponent> _function_1 = (GridBagConstraints it_1) -> {
        return this.generateSuppressWarnings = this._ideaWidgetFactory.checkBox("Generate @SuppressWarnings annotations");
      };
      it.row(it, _function_1);
      final Function1<GridBagConstraints, JComponent> _function_2 = (GridBagConstraints it_1) -> {
        return this.generateGeneratedAnnotation = this._ideaWidgetFactory.checkBox("Generate @Generated annotations");
      };
      it.row(it, _function_2);
      final Function1<GridBagConstraints, JComponent> _function_3 = (GridBagConstraints it_1) -> {
        JCheckBox _xblockexpression_1 = null;
        {
          this._ideaWidgetFactory.indent(it_1);
          _xblockexpression_1 = this.includeDateInGenerated = this._ideaWidgetFactory.checkBox("Include current time information");
        }
        return _xblockexpression_1;
      };
      it.row(it, _function_3);
      final Function1<GridBagConstraints, JComponent> _function_4 = (GridBagConstraints it_1) -> {
        JPanel _xblockexpression_1 = null;
        {
          this._ideaWidgetFactory.indent(it_1);
          final Function1<GridBagConstraints, JComponent> _function_5 = (GridBagConstraints it_2) -> {
            return this._ideaWidgetFactory.label("Comment:");
          };
          final Function1<GridBagConstraints, JComponent> _function_6 = (GridBagConstraints it_2) -> {
            JBTextField _xblockexpression_2 = null;
            {
              this._ideaWidgetFactory.expand(it_2, GridBagConstraints.HORIZONTAL);
              it_2.anchor = GridBagConstraints.WEST;
              _xblockexpression_2 = this.generatedAnnotationComment = this._ideaWidgetFactory.textField();
            }
            return _xblockexpression_2;
          };
          _xblockexpression_1 = this._ideaWidgetFactory.container(_function_5, _function_6);
        }
        return _xblockexpression_1;
      };
      _xblockexpression = it.row(it, _function_4);
    }
    return _xblockexpression;
  }
  
  @Override
  public void createOutputSection(@Extension final IdeaWidgetFactory.TwoColumnPanel it) {
    super.createOutputSection(it);
    final Function1<GridBagConstraints, JComponent> _function = (GridBagConstraints it_1) -> {
      return this.installDslAsPrimarySource = this._ideaWidgetFactory.checkBox("Ignore generated Java source when debugging (Use for Android)");
    };
    it.row(it, _function);
    final Function1<GridBagConstraints, JComponent> _function_1 = (GridBagConstraints it_1) -> {
      JCheckBox _xblockexpression = null;
      {
        this._ideaWidgetFactory.indent(it_1);
        _xblockexpression = this.hideLocalSyntheticVariables = this._ideaWidgetFactory.checkBox("Hide synthetic local variables in the debugger");
      }
      return _xblockexpression;
    };
    it.row(it, _function_1);
  }
  
  @Override
  public void postCreateComponent() {
    super.postCreateComponent();
    this.installDslAsPrimarySource.setVisible(false);
    this.hideLocalSyntheticVariables.setVisible(false);
  }
  
  @Override
  public void setData(final GeneratorConfigurationState data) {
    super.setData(data);
    if ((data instanceof XbaseGeneratorConfigurationState)) {
      final Function1<LanguageLevel, Boolean> _function = (LanguageLevel it) -> {
        String _name = it.name();
        String _targetJavaVersion = ((XbaseGeneratorConfigurationState)data).getTargetJavaVersion();
        return Boolean.valueOf((_name == _targetJavaVersion));
      };
      LanguageLevel _findFirst = IterableExtensions.<LanguageLevel>findFirst(((Iterable<LanguageLevel>)Conversions.doWrapArray(LanguageLevel.values())), _function);
      boolean _notEquals = (!Objects.equal(_findFirst, null));
      if (_notEquals) {
        this.targetJavaVersion.setSelectedItem(LanguageLevel.valueOf(((XbaseGeneratorConfigurationState)data).getTargetJavaVersion()));
      } else {
        this.targetJavaVersion.setSelectedItem(null);
      }
      this.generateSuppressWarnings.setSelected(((XbaseGeneratorConfigurationState)data).isGenerateSuppressWarnings());
      this.generateGeneratedAnnotation.setSelected(((XbaseGeneratorConfigurationState)data).isGenerateGeneratedAnnotation());
      this.includeDateInGenerated.setSelected(((XbaseGeneratorConfigurationState)data).isIncludeDateInGenerated());
      this.generatedAnnotationComment.setText(((XbaseGeneratorConfigurationState)data).getGeneratedAnnotationComment());
      this.installDslAsPrimarySource.setSelected(((XbaseGeneratorConfigurationState)data).isInstallDslAsPrimarySource());
      this.hideLocalSyntheticVariables.setSelected(((XbaseGeneratorConfigurationState)data).isHideLocalSyntheticVariables());
    }
  }
  
  @Override
  public void getData(final GeneratorConfigurationState data) {
    super.getData(data);
    if ((data instanceof XbaseGeneratorConfigurationState)) {
      Object _selectedItem = this.targetJavaVersion.getSelectedItem();
      String _string = null;
      if (_selectedItem!=null) {
        _string=_selectedItem.toString();
      }
      ((XbaseGeneratorConfigurationState)data).setTargetJavaVersion(_string);
      ((XbaseGeneratorConfigurationState)data).setGenerateSuppressWarnings(this.generateSuppressWarnings.isSelected());
      ((XbaseGeneratorConfigurationState)data).setGenerateGeneratedAnnotation(this.generateGeneratedAnnotation.isSelected());
      ((XbaseGeneratorConfigurationState)data).setIncludeDateInGenerated(this.includeDateInGenerated.isSelected());
      ((XbaseGeneratorConfigurationState)data).setGeneratedAnnotationComment(this.generatedAnnotationComment.getText());
      ((XbaseGeneratorConfigurationState)data).setInstallDslAsPrimarySource(this.installDslAsPrimarySource.isSelected());
      ((XbaseGeneratorConfigurationState)data).setHideLocalSyntheticVariables(this.hideLocalSyntheticVariables.isSelected());
    }
  }
  
  @Override
  public boolean isModified(final GeneratorConfigurationState data) {
    boolean _isModified = super.isModified(data);
    boolean _not = (!_isModified);
    if (_not) {
      if ((data instanceof XbaseGeneratorConfigurationState)) {
        boolean _xifexpression = false;
        Object _selectedItem = this.targetJavaVersion.getSelectedItem();
        boolean _tripleNotEquals = (_selectedItem != null);
        if (_tripleNotEquals) {
          boolean _equals = this.targetJavaVersion.getSelectedItem().toString().equals(((XbaseGeneratorConfigurationState)data).getTargetJavaVersion());
          _xifexpression = (!_equals);
        } else {
          String _targetJavaVersion = ((XbaseGeneratorConfigurationState)data).getTargetJavaVersion();
          _xifexpression = (_targetJavaVersion != null);
        }
        if (_xifexpression) {
          return true;
        }
        boolean _isSelected = this.generateSuppressWarnings.isSelected();
        boolean _isGenerateSuppressWarnings = ((XbaseGeneratorConfigurationState)data).isGenerateSuppressWarnings();
        boolean _tripleNotEquals_1 = (Boolean.valueOf(_isSelected) != Boolean.valueOf(_isGenerateSuppressWarnings));
        if (_tripleNotEquals_1) {
          return true;
        }
        boolean _isSelected_1 = this.generateGeneratedAnnotation.isSelected();
        boolean _isGenerateGeneratedAnnotation = ((XbaseGeneratorConfigurationState)data).isGenerateGeneratedAnnotation();
        boolean _tripleNotEquals_2 = (Boolean.valueOf(_isSelected_1) != Boolean.valueOf(_isGenerateGeneratedAnnotation));
        if (_tripleNotEquals_2) {
          return true;
        }
        boolean _isSelected_2 = this.includeDateInGenerated.isSelected();
        boolean _isIncludeDateInGenerated = ((XbaseGeneratorConfigurationState)data).isIncludeDateInGenerated();
        boolean _tripleNotEquals_3 = (Boolean.valueOf(_isSelected_2) != Boolean.valueOf(_isIncludeDateInGenerated));
        if (_tripleNotEquals_3) {
          return true;
        }
        boolean _xifexpression_1 = false;
        String _text = this.generatedAnnotationComment.getText();
        boolean _tripleNotEquals_4 = (_text != null);
        if (_tripleNotEquals_4) {
          boolean _equals_1 = this.generatedAnnotationComment.getText().equals(((XbaseGeneratorConfigurationState)data).getGeneratedAnnotationComment());
          _xifexpression_1 = (!_equals_1);
        } else {
          String _generatedAnnotationComment = ((XbaseGeneratorConfigurationState)data).getGeneratedAnnotationComment();
          _xifexpression_1 = (_generatedAnnotationComment != null);
        }
        if (_xifexpression_1) {
          return true;
        }
        boolean _isSelected_3 = this.installDslAsPrimarySource.isSelected();
        boolean _isInstallDslAsPrimarySource = ((XbaseGeneratorConfigurationState)data).isInstallDslAsPrimarySource();
        boolean _tripleNotEquals_5 = (Boolean.valueOf(_isSelected_3) != Boolean.valueOf(_isInstallDslAsPrimarySource));
        if (_tripleNotEquals_5) {
          return true;
        }
        boolean _isSelected_4 = this.hideLocalSyntheticVariables.isSelected();
        boolean _isHideLocalSyntheticVariables = ((XbaseGeneratorConfigurationState)data).isHideLocalSyntheticVariables();
        boolean _tripleNotEquals_6 = (Boolean.valueOf(_isSelected_4) != Boolean.valueOf(_isHideLocalSyntheticVariables));
        if (_tripleNotEquals_6) {
          return true;
        }
      }
      return false;
    }
    return true;
  }
  
  public LanguageLevelCombo createLanguageLevelCombo() {
    abstract class __XbaseFacetForm_1 extends LanguageLevelCombo {
      __XbaseFacetForm_1(final String defaultItem) {
        super(defaultItem);
      }
      
      LanguageLevelModuleExtension llExt;
    }
    
    final String defItem = "Module default";
    final __XbaseFacetForm_1 langLavelCombo = new __XbaseFacetForm_1(defItem) {
      {
        llExt = ModuleRootManager.getInstance(XbaseFacetForm.this.getModule()).<LanguageLevelModuleExtension>getModuleExtension(LanguageLevelModuleExtension.class);
      }
      @Override
      protected LanguageLevel getDefaultLevel() {
        LanguageLevel langLevel = this.llExt.getLanguageLevel();
        boolean _equals = Objects.equal(langLevel, null);
        if (_equals) {
          langLevel = LanguageLevelProjectExtensionImpl.getInstanceImpl(XbaseFacetForm.this.getModule().getProject()).getCurrentLevel();
        }
        return langLevel;
      }
    };
    langLavelCombo.insertItemAt(defItem, 0);
    return langLavelCombo;
  }
}
