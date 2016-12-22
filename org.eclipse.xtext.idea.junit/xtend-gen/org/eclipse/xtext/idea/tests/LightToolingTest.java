/**
 * Copyright (c) 2015 itemis AG (http://www.itemis.eu) and others.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 */
package org.eclipse.xtext.idea.tests;

import com.google.common.base.Objects;
import com.google.inject.Inject;
import com.intellij.codeInsight.CodeInsightSettings;
import com.intellij.codeInsight.lookup.LookupElement;
import com.intellij.facet.Facet;
import com.intellij.facet.FacetConfiguration;
import com.intellij.facet.FacetManager;
import com.intellij.facet.FacetType;
import com.intellij.facet.FacetTypeRegistry;
import com.intellij.formatting.Block;
import com.intellij.formatting.FormattingModelBuilder;
import com.intellij.formatting.FormattingModelDumper;
import com.intellij.ide.highlighter.HighlighterFactory;
import com.intellij.ide.structureView.newStructureView.StructureViewComponent;
import com.intellij.lang.Language;
import com.intellij.lang.LanguageFormatting;
import com.intellij.openapi.application.ApplicationManager;
import com.intellij.openapi.editor.highlighter.EditorHighlighter;
import com.intellij.openapi.editor.highlighter.HighlighterIterator;
import com.intellij.openapi.fileTypes.LanguageFileType;
import com.intellij.openapi.module.Module;
import com.intellij.openapi.module.ModuleType;
import com.intellij.openapi.module.StdModuleTypes;
import com.intellij.openapi.projectRoots.Sdk;
import com.intellij.openapi.projectRoots.impl.JavaAwareProjectJdkTableImpl;
import com.intellij.openapi.roots.ContentEntry;
import com.intellij.openapi.roots.LanguageLevelModuleExtension;
import com.intellij.openapi.roots.ModifiableRootModel;
import com.intellij.openapi.vfs.VirtualFile;
import com.intellij.pom.java.LanguageLevel;
import com.intellij.psi.PsiFile;
import com.intellij.psi.codeStyle.CodeStyleSettings;
import com.intellij.psi.codeStyle.CodeStyleSettingsManager;
import com.intellij.psi.codeStyle.CommonCodeStyleSettings;
import com.intellij.psi.tree.IElementType;
import com.intellij.testFramework.LightProjectDescriptor;
import com.intellij.testFramework.PlatformTestUtil;
import com.intellij.testFramework.fixtures.LightCodeInsightFixtureTestCase;
import com.intellij.util.Consumer;
import com.intellij.util.ui.tree.TreeUtil;
import junit.framework.TestCase;
import org.eclipse.emf.common.util.URI;
import org.eclipse.xtend.lib.annotations.Accessors;
import org.eclipse.xtend2.lib.StringConcatenation;
import org.eclipse.xtext.ide.editor.syntaxcoloring.AbstractAntlrTokenToAttributeIdMapper;
import org.eclipse.xtext.ide.editor.syntaxcoloring.HighlightingStyles;
import org.eclipse.xtext.idea.build.XtextAutoBuilderComponent;
import org.eclipse.xtext.idea.lang.IXtextLanguage;
import org.eclipse.xtext.idea.parser.TokenTypeProvider;
import org.eclipse.xtext.idea.resource.VirtualFileURIUtil;
import org.eclipse.xtext.idea.tests.LineDelimiters;
import org.eclipse.xtext.psi.impl.BaseXtextFile;
import org.eclipse.xtext.resource.XtextResourceSet;
import org.eclipse.xtext.resource.impl.ChunkedResourceDescriptions;
import org.eclipse.xtext.xbase.lib.Conversions;
import org.eclipse.xtext.xbase.lib.Extension;
import org.eclipse.xtext.xbase.lib.Functions.Function1;
import org.eclipse.xtext.xbase.lib.IterableExtensions;
import org.eclipse.xtext.xbase.lib.ObjectExtensions;
import org.eclipse.xtext.xbase.lib.Procedures.Procedure1;
import org.eclipse.xtext.xbase.lib.Pure;

@SuppressWarnings("all")
public class LightToolingTest extends LightCodeInsightFixtureTestCase {
  @Inject
  @Extension
  private TokenTypeProvider tokenTypeProvider;
  
  @Inject
  @Extension
  private AbstractAntlrTokenToAttributeIdMapper tokenToAttributeIdMapper;
  
  @Accessors
  private final LanguageFileType fileType;
  
  public LightToolingTest(final LanguageFileType fileType) {
    XtextAutoBuilderComponent.TEST_MODE = true;
    this.fileType = fileType;
  }
  
  @Override
  protected void setUp() throws Exception {
    super.setUp();
    this.getXtextLanguage().injectMembers(this);
    CodeInsightSettings _instance = CodeInsightSettings.getInstance();
    _instance.AUTOCOMPLETE_ON_CODE_COMPLETION = false;
    CommonCodeStyleSettings.IndentOptions _indentOptions = this.getCodeStyleSettings().getIndentOptions();
    _indentOptions.USE_TAB_CHARACTER = true;
  }
  
  @Override
  protected void tearDown() throws Exception {
    CodeInsightSettings _instance = CodeInsightSettings.getInstance();
    _instance.AUTOCOMPLETE_ON_CODE_COMPLETION = true;
    super.tearDown();
  }
  
  protected final IXtextLanguage getXtextLanguage() {
    Language _language = this.fileType.getLanguage();
    return ((IXtextLanguage) _language);
  }
  
  @Override
  protected LightProjectDescriptor getProjectDescriptor() {
    return new LightProjectDescriptor() {
      @Override
      public void configureModule(final Module module, final ModifiableRootModel model, final ContentEntry contentEntry) {
        final LanguageLevelModuleExtension languageLevelModuleExtension = model.<LanguageLevelModuleExtension>getModuleExtension(LanguageLevelModuleExtension.class);
        boolean _notEquals = (!Objects.equal(languageLevelModuleExtension, null));
        if (_notEquals) {
          languageLevelModuleExtension.setLanguageLevel(LightToolingTest.this.getLanguageLevel());
        }
        LightToolingTest.addFacetToModule(module, LightToolingTest.this.fileType.getLanguage().getID());
        LightToolingTest.this.configureModule(module, model, contentEntry);
      }
      
      @Override
      public ModuleType<?> getModuleType() {
        return StdModuleTypes.JAVA;
      }
      
      @Override
      public Sdk getSdk() {
        return LightToolingTest.this.getSdk();
      }
    };
  }
  
  public static void addFacetToModule(final Module module, final String languageId) {
    final FacetManager mnr = FacetManager.getInstance(module);
    final Function1<FacetType, Boolean> _function = (FacetType it) -> {
      String _stringId = it.getStringId();
      return Boolean.valueOf(Objects.equal(_stringId, languageId));
    };
    final FacetType facetType = IterableExtensions.<FacetType>findFirst(((Iterable<FacetType>)Conversions.doWrapArray(FacetTypeRegistry.getInstance().getFacetTypes())), _function);
    final Runnable _function_1 = () -> {
      mnr.<Facet, FacetConfiguration>addFacet(facetType, facetType.getDefaultFacetName(), null);
      return;
    };
    ApplicationManager.getApplication().runWriteAction(_function_1);
  }
  
  protected Sdk getSdk() {
    return JavaAwareProjectJdkTableImpl.getInstanceEx().getInternalJdk();
  }
  
  protected LanguageLevel getLanguageLevel() {
    return LanguageLevel.JDK_1_6;
  }
  
  protected void configureModule(final Module module, final ModifiableRootModel model, final ContentEntry contentEntry) {
  }
  
  protected PsiFile configureByText(final String code) {
    return this.myFixture.configureByText(this.fileType, LineDelimiters.toUnix(code));
  }
  
  protected void configureByText(final String path, final String code) {
    String _defaultExtension = this.fileType.getDefaultExtension();
    String _plus = ((path + ".") + _defaultExtension);
    final PsiFile psiFile = this.myFixture.addFileToProject(_plus, LineDelimiters.toUnix(code));
    this.myFixture.configureFromExistingVirtualFile(psiFile.getVirtualFile());
  }
  
  protected LookupElement[] complete(final String text) {
    LookupElement[] _xblockexpression = null;
    {
      this.configureByText(text);
      _xblockexpression = this.myFixture.completeBasic();
    }
    return _xblockexpression;
  }
  
  protected void assertLookupStrings(final String... items) {
    TestCase.assertEquals(IterableExtensions.<String>toList(((Iterable<String>)Conversions.doWrapArray(items))), this.myFixture.getLookupElementStrings());
  }
  
  protected void assertHighlights(final String lineDelimitedHighlights) {
    final String expectedHighlights = LineDelimiters.toUnix(lineDelimitedHighlights);
    EditorHighlighter _createHighlighter = HighlighterFactory.createHighlighter(this.getProject(), this.myFixture.getFile().getVirtualFile());
    final Procedure1<EditorHighlighter> _function = (EditorHighlighter it) -> {
      it.setText(this.myFixture.getEditor().getDocument().getText());
      it.setColorScheme(this.myFixture.getEditor().getColorsScheme());
    };
    final EditorHighlighter highlighter = ObjectExtensions.<EditorHighlighter>operator_doubleArrow(_createHighlighter, _function);
    final HighlighterIterator highlights = highlighter.createIterator(0);
    final String actualHighlights = this.compactHighlights(highlights);
    TestCase.assertEquals(expectedHighlights, actualHighlights);
  }
  
  protected String compactHighlights(final HighlighterIterator highlights) {
    String _xblockexpression = null;
    {
      final StringBuilder compactHighlights = new StringBuilder();
      while ((!highlights.atEnd())) {
        {
          final int start = highlights.getStart();
          final IElementType tokenType = highlights.getTokenType();
          int end = highlights.getEnd();
          while (((!highlights.atEnd()) && Objects.equal(highlights.getTokenType(), tokenType))) {
            {
              end = highlights.getEnd();
              highlights.advance();
            }
          }
          String _xtextStyle = this.getXtextStyle(tokenType);
          boolean _notEquals = (!Objects.equal(_xtextStyle, HighlightingStyles.DEFAULT_ID));
          if (_notEquals) {
            StringConcatenation _builder = new StringConcatenation();
            _builder.append(start);
            _builder.append("-");
            _builder.append(end);
            _builder.append(":");
            String _xtextStyle_1 = this.getXtextStyle(tokenType);
            _builder.append(_xtextStyle_1);
            compactHighlights.append(_builder);
            compactHighlights.append("\n");
          }
        }
      }
      _xblockexpression = compactHighlights.toString();
    }
    return _xblockexpression;
  }
  
  protected String getXtextStyle(final IElementType tokenType) {
    return this.tokenToAttributeIdMapper.getId(this.tokenTypeProvider.getAntlrType(tokenType));
  }
  
  protected BaseXtextFile getXtextFile() {
    PsiFile _file = this.myFixture.getFile();
    return ((BaseXtextFile) _file);
  }
  
  protected void testStructureView(final String model, final String expected) {
    final Consumer<StructureViewComponent> _function = (StructureViewComponent component) -> {
      this.assertTreeStructure(component, expected);
    };
    this.testStructureView(model, _function);
  }
  
  protected void assertTreeStructure(final StructureViewComponent component, final String expected) {
    TreeUtil.expandAll(component.getTree());
    PlatformTestUtil.assertTreeStructureEquals(component.getTreeStructure(), expected);
  }
  
  protected void testStructureView(final String model, final Consumer<StructureViewComponent> consumer) {
    this.myFixture.configureByText(this.fileType, model);
    this.testStructureView(consumer);
  }
  
  protected void testStructureView(final Consumer<StructureViewComponent> consumer) {
    this.myFixture.testStructureView(consumer);
  }
  
  protected CodeStyleSettings getCodeStyleSettings() {
    return CodeStyleSettingsManager.getSettings(this.getProject());
  }
  
  protected String dumpFormattingModel() {
    String _xblockexpression = null;
    {
      final FormattingModelBuilder formattingModelBuilder = LanguageFormatting.INSTANCE.forContext(this.getFile());
      final Block block = formattingModelBuilder.createModel(this.getFile(), this.getCodeStyleSettings()).getRootBlock();
      final StringBuilder builder = new StringBuilder();
      FormattingModelDumper.dumpFormattingModel(block, 0, builder);
      _xblockexpression = builder.toString();
    }
    return _xblockexpression;
  }
  
  protected Iterable<PsiFile> getGeneratedSources(final PsiFile sourceFile, final Function1<? super VirtualFile, ? extends Boolean> filter) {
    final Function1<VirtualFile, PsiFile> _function = (VirtualFile it) -> {
      return this.getPsiManager().findFile(it);
    };
    return IterableExtensions.<PsiFile>filterNull(IterableExtensions.<VirtualFile, PsiFile>map(this.getGeneratedSources(sourceFile.getVirtualFile(), filter), _function));
  }
  
  protected Iterable<VirtualFile> getGeneratedSources(final VirtualFile sourceFile, final Function1<? super VirtualFile, ? extends Boolean> filter) {
    final Function1<VirtualFile, Boolean> _function = (VirtualFile it) -> {
      return filter.apply(it);
    };
    return IterableExtensions.<VirtualFile>filter(this.getGeneratedSources(sourceFile), _function);
  }
  
  protected Iterable<VirtualFile> getGeneratedSources(final VirtualFile sourceFile) {
    final Function1<URI, VirtualFile> _function = (URI it) -> {
      return VirtualFileURIUtil.getVirtualFile(it);
    };
    return IterableExtensions.<VirtualFile>filterNull(IterableExtensions.<URI, VirtualFile>map(this.getBuilder().getGeneratedSources(VirtualFileURIUtil.getURI(sourceFile)), _function));
  }
  
  protected ChunkedResourceDescriptions getIndex() {
    final XtextResourceSet rs = new XtextResourceSet();
    this.getBuilder().installCopyOfResourceDescriptions(rs);
    final ChunkedResourceDescriptions index = ChunkedResourceDescriptions.findInEmfObject(rs);
    return index;
  }
  
  protected XtextAutoBuilderComponent getBuilder() {
    return this.getProject().<XtextAutoBuilderComponent>getComponent(XtextAutoBuilderComponent.class);
  }
  
  @Pure
  public LanguageFileType getFileType() {
    return this.fileType;
  }
}
