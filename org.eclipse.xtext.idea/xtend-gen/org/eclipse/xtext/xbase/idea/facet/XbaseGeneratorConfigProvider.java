/**
 * Copyright (c) 2015 itemis AG (http://www.itemis.eu) and others.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 */
package org.eclipse.xtext.xbase.idea.facet;

import com.google.common.base.Objects;
import com.google.inject.Inject;
import com.intellij.facet.Facet;
import com.intellij.openapi.application.ApplicationManager;
import com.intellij.openapi.module.EffectiveLanguageLevelUtil;
import com.intellij.openapi.module.Module;
import com.intellij.openapi.util.Computable;
import com.intellij.pom.java.LanguageLevel;
import org.apache.log4j.Logger;
import org.eclipse.emf.ecore.EObject;
import org.eclipse.xtext.LanguageInfo;
import org.eclipse.xtext.idea.facet.AbstractFacetConfiguration;
import org.eclipse.xtext.idea.facet.FacetProvider;
import org.eclipse.xtext.idea.resource.ModuleProvider;
import org.eclipse.xtext.util.JavaVersion;
import org.eclipse.xtext.util.internal.Log;
import org.eclipse.xtext.xbase.compiler.GeneratorConfig;
import org.eclipse.xtext.xbase.compiler.IGeneratorConfigProvider;
import org.eclipse.xtext.xbase.idea.facet.XbaseFacetConfiguration;
import org.eclipse.xtext.xbase.idea.facet.XbaseGeneratorConfigurationState;
import org.eclipse.xtext.xbase.lib.Conversions;
import org.eclipse.xtext.xbase.lib.Functions.Function1;
import org.eclipse.xtext.xbase.lib.IterableExtensions;
import org.eclipse.xtext.xbase.lib.ObjectExtensions;
import org.eclipse.xtext.xbase.lib.Procedures.Procedure1;

/**
 * @author Jan Koehnlein - Initial contribution and API
 */
@Log
@SuppressWarnings("all")
public class XbaseGeneratorConfigProvider implements IGeneratorConfigProvider {
  @Inject
  private FacetProvider facetProvider;
  
  @Inject
  private LanguageInfo languageInfo;
  
  @Override
  public GeneratorConfig get(final EObject context) {
    final Module module = ModuleProvider.findModule(context.eResource().getResourceSet());
    if ((module != null)) {
      final Facet<? extends AbstractFacetConfiguration> facet = this.facetProvider.getFacet(module);
      AbstractFacetConfiguration _configuration = null;
      if (facet!=null) {
        _configuration=facet.getConfiguration();
      }
      final AbstractFacetConfiguration facetConfiguration = _configuration;
      if ((facetConfiguration instanceof XbaseFacetConfiguration)) {
        final XbaseGeneratorConfigurationState state = ((XbaseFacetConfiguration)facetConfiguration).getState();
        GeneratorConfig _generatorConfig = new GeneratorConfig();
        final Procedure1<GeneratorConfig> _function = (GeneratorConfig it) -> {
          it.setGeneratedAnnotationComment(state.getGeneratedAnnotationComment());
          it.setGenerateGeneratedAnnotation(state.isGenerateGeneratedAnnotation());
          it.setGenerateSyntheticSuppressWarnings(state.isGenerateSuppressWarnings());
          it.setIncludeDateInGeneratedAnnotation(state.isIncludeDateInGenerated());
          it.setJavaSourceVersion(this.getTargetJavaVersion(state, module));
        };
        return ObjectExtensions.<GeneratorConfig>operator_doubleArrow(_generatorConfig, _function);
      }
    }
    return new GeneratorConfig();
  }
  
  protected JavaVersion getTargetJavaVersion(final XbaseGeneratorConfigurationState state, final Module module) {
    JavaVersion _xblockexpression = null;
    {
      final String version = state.getTargetJavaVersion();
      LanguageLevel _xifexpression = null;
      if (((version == null) || version.equals("Module default"))) {
        LanguageLevel _xblockexpression_1 = null;
        {
          final Computable<LanguageLevel> _function = () -> {
            return EffectiveLanguageLevelUtil.getEffectiveLanguageLevel(module);
          };
          final Computable<LanguageLevel> action = _function;
          _xblockexpression_1 = ApplicationManager.getApplication().<LanguageLevel>runReadAction(action);
        }
        _xifexpression = _xblockexpression_1;
      } else {
        final Function1<LanguageLevel, Boolean> _function = (LanguageLevel it) -> {
          String _presentableText = it.getPresentableText();
          return Boolean.valueOf(Objects.equal(_presentableText, version));
        };
        _xifexpression = IterableExtensions.<LanguageLevel>findFirst(((Iterable<LanguageLevel>)Conversions.doWrapArray(LanguageLevel.values())), _function);
      }
      final LanguageLevel languageLevel = _xifexpression;
      JavaVersion _switchResult = null;
      if (languageLevel != null) {
        switch (languageLevel) {
          case JDK_1_3:
          case JDK_1_4:
            JavaVersion _xblockexpression_2 = null;
            {
              String _shortName = this.languageInfo.getShortName();
              String _plus = (_shortName + " requires Java language level 1.5 or higher. Using Java 1.5.");
              XbaseGeneratorConfigProvider.LOG.warn(_plus);
              _xblockexpression_2 = JavaVersion.JAVA5;
            }
            _switchResult = _xblockexpression_2;
            break;
          case JDK_1_5:
            _switchResult = JavaVersion.JAVA5;
            break;
          case JDK_1_6:
            _switchResult = JavaVersion.JAVA6;
            break;
          case JDK_1_7:
            _switchResult = JavaVersion.JAVA7;
            break;
          default:
            _switchResult = JavaVersion.JAVA8;
            break;
        }
      } else {
        _switchResult = JavaVersion.JAVA8;
      }
      _xblockexpression = _switchResult;
    }
    return _xblockexpression;
  }
  
  private final static Logger LOG = Logger.getLogger(XbaseGeneratorConfigProvider.class);
}
