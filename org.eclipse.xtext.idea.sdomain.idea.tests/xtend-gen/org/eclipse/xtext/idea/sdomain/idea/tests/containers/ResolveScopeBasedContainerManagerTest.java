/**
 * Copyright (c) 2015 itemis AG (http://www.itemis.eu) and others.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 */
package org.eclipse.xtext.idea.sdomain.idea.tests.containers;

import com.google.common.collect.Iterables;
import com.google.inject.Inject;
import com.intellij.openapi.module.Module;
import com.intellij.openapi.roots.ModuleRootModificationUtil;
import com.intellij.openapi.vfs.VirtualFile;
import com.intellij.psi.PsiFile;
import com.intellij.testFramework.PlatformTestCase;
import com.intellij.testFramework.PsiTestUtil;
import com.intellij.testFramework.UsefulTestCase;
import java.io.File;
import java.util.Collections;
import java.util.List;
import junit.framework.TestCase;
import org.eclipse.emf.common.util.URI;
import org.eclipse.xtend2.lib.StringConcatenation;
import org.eclipse.xtext.idea.sdomain.idea.lang.SDomainFileType;
import org.eclipse.xtext.idea.sdomain.idea.lang.SDomainLanguage;
import org.eclipse.xtext.idea.sdomain.idea.tests.containers.URIBasedTestResourceDescription;
import org.eclipse.xtext.idea.tests.LightToolingTest;
import org.eclipse.xtext.psi.impl.BaseXtextFile;
import org.eclipse.xtext.resource.IContainer;
import org.eclipse.xtext.resource.IResourceDescriptions;
import org.eclipse.xtext.resource.impl.ResourceDescriptionsProvider;
import org.eclipse.xtext.xbase.lib.CollectionLiterals;
import org.eclipse.xtext.xbase.lib.Functions.Function1;
import org.eclipse.xtext.xbase.lib.IterableExtensions;
import org.eclipse.xtext.xbase.lib.ListExtensions;

@SuppressWarnings("all")
public class ResolveScopeBasedContainerManagerTest extends PlatformTestCase {
  @Inject
  private IContainer.Manager containerManager;
  
  @Inject
  private ResourceDescriptionsProvider resourceDescriptionsProvider;
  
  private List<BaseXtextFile> files;
  
  protected String getTestDataPath() {
    StringConcatenation _builder = new StringConcatenation();
    _builder.append("./testData/containers");
    return _builder.toString();
  }
  
  @Override
  protected void setUp() throws Exception {
    super.setUp();
    final Module module = this.createModule("module");
    StringConcatenation _builder = new StringConcatenation();
    String _testDataPath = this.getTestDataPath();
    _builder.append(_testDataPath);
    _builder.append("/module");
    File _file = new File(_builder.toString());
    PsiTestUtil.addSourceRoot(module, UsefulTestCase.refreshAndFindFile(_file));
    final Module module2 = this.createModule("module2");
    StringConcatenation _builder_1 = new StringConcatenation();
    String _testDataPath_1 = this.getTestDataPath();
    _builder_1.append(_testDataPath_1);
    _builder_1.append("/module2");
    File _file_1 = new File(_builder_1.toString());
    PsiTestUtil.addSourceRoot(module2, UsefulTestCase.refreshAndFindFile(_file_1));
    LightToolingTest.addFacetToModule(module, SDomainFileType.INSTANCE.getLanguage().getID());
    LightToolingTest.addFacetToModule(module2, SDomainFileType.INSTANCE.getLanguage().getID());
    ModuleRootModificationUtil.addDependency(module, module2);
    final Function1<String, PsiFile> _function = (String path) -> {
      PsiFile _xblockexpression = null;
      {
        StringConcatenation _builder_2 = new StringConcatenation();
        String _testDataPath_2 = this.getTestDataPath();
        _builder_2.append(_testDataPath_2);
        _builder_2.append("/");
        _builder_2.append(path);
        final File file = new File(_builder_2.toString());
        final VirtualFile virtualFile = UsefulTestCase.refreshAndFindFile(file);
        _xblockexpression = this.getPsiManager().findFile(virtualFile);
      }
      return _xblockexpression;
    };
    this.files = IterableExtensions.<BaseXtextFile>toList(Iterables.<BaseXtextFile>filter(ListExtensions.<String, PsiFile>map(Collections.<String>unmodifiableList(CollectionLiterals.<String>newArrayList("/module/file1.sdomain", "/module/file2.sdomain", "/module2/file3.sdomain")), _function), BaseXtextFile.class));
    SDomainLanguage.INSTANCE.injectMembers(this);
  }
  
  @Override
  protected void tearDown() throws Exception {
    this.files = null;
    super.tearDown();
  }
  
  public void testGetContainer_01() {
    URI _uRI = IterableExtensions.<BaseXtextFile>head(this.files).getURI();
    final URIBasedTestResourceDescription description = new URIBasedTestResourceDescription(_uRI);
    final IResourceDescriptions resourceDescriptions = this.resourceDescriptionsProvider.getResourceDescriptions(IterableExtensions.<BaseXtextFile>head(this.files).getResource());
    final IContainer container = this.containerManager.getContainer(description, resourceDescriptions);
    TestCase.assertEquals(2, IterableExtensions.size(container.getResourceDescriptions()));
    TestCase.assertNotNull(container.getResourceDescription(IterableExtensions.<BaseXtextFile>head(this.files).getURI()));
    TestCase.assertNotNull(container.getResourceDescription(this.files.get(1).getURI()));
    TestCase.assertNull(container.getResourceDescription(IterableExtensions.<BaseXtextFile>last(this.files).getURI()));
  }
  
  public void testGetContainer_02() {
    URI _uRI = IterableExtensions.<BaseXtextFile>last(this.files).getURI();
    final URIBasedTestResourceDescription description = new URIBasedTestResourceDescription(_uRI);
    final IResourceDescriptions resourceDescriptions = this.resourceDescriptionsProvider.getResourceDescriptions(IterableExtensions.<BaseXtextFile>last(this.files).getResource());
    final IContainer container = this.containerManager.getContainer(description, resourceDescriptions);
    TestCase.assertEquals(1, IterableExtensions.size(container.getResourceDescriptions()));
    TestCase.assertNull(container.getResourceDescription(IterableExtensions.<BaseXtextFile>head(this.files).getURI()));
    TestCase.assertNull(container.getResourceDescription(this.files.get(1).getURI()));
    TestCase.assertNotNull(container.getResourceDescription(IterableExtensions.<BaseXtextFile>last(this.files).getURI()));
  }
  
  public void testGetVisibleContainers_01() {
    URI _uRI = IterableExtensions.<BaseXtextFile>head(this.files).getURI();
    final URIBasedTestResourceDescription description = new URIBasedTestResourceDescription(_uRI);
    final IResourceDescriptions resourceDescriptions = this.resourceDescriptionsProvider.getResourceDescriptions(IterableExtensions.<BaseXtextFile>head(this.files).getResource());
    final List<IContainer> visibleContainers = this.containerManager.getVisibleContainers(description, resourceDescriptions);
    TestCase.assertEquals(2, visibleContainers.size());
    TestCase.assertEquals(2, IterableExtensions.size(IterableExtensions.<IContainer>head(visibleContainers).getResourceDescriptions()));
    TestCase.assertEquals(1, IterableExtensions.size(visibleContainers.get(1).getResourceDescriptions()));
    TestCase.assertNotNull(IterableExtensions.<IContainer>head(visibleContainers).getResourceDescription(IterableExtensions.<BaseXtextFile>head(this.files).getURI()));
    TestCase.assertNotNull(IterableExtensions.<IContainer>head(visibleContainers).getResourceDescription(this.files.get(1).getURI()));
    TestCase.assertNotNull(visibleContainers.get(1).getResourceDescription(IterableExtensions.<BaseXtextFile>last(this.files).getURI()));
  }
  
  public void testGetVisibleContainers_02() {
    URI _uRI = IterableExtensions.<BaseXtextFile>last(this.files).getURI();
    final URIBasedTestResourceDescription description = new URIBasedTestResourceDescription(_uRI);
    final IResourceDescriptions resourceDescriptions = this.resourceDescriptionsProvider.getResourceDescriptions(IterableExtensions.<BaseXtextFile>last(this.files).getResource());
    final List<IContainer> visibleContainers = this.containerManager.getVisibleContainers(description, resourceDescriptions);
    TestCase.assertEquals(1, visibleContainers.size());
    TestCase.assertEquals(1, IterableExtensions.size(IterableExtensions.<IContainer>head(visibleContainers).getResourceDescriptions()));
    TestCase.assertNotNull(IterableExtensions.<IContainer>head(visibleContainers).getResourceDescription(IterableExtensions.<BaseXtextFile>last(this.files).getURI()));
  }
}
