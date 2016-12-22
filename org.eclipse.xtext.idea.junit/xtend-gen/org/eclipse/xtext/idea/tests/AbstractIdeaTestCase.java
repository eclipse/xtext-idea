package org.eclipse.xtext.idea.tests;

import com.google.common.base.Objects;
import com.intellij.openapi.application.ApplicationManager;
import com.intellij.openapi.module.Module;
import com.intellij.openapi.projectRoots.Sdk;
import com.intellij.openapi.projectRoots.impl.JavaAwareProjectJdkTableImpl;
import com.intellij.openapi.projectRoots.impl.ProjectJdkImpl;
import com.intellij.openapi.roots.ContentEntry;
import com.intellij.openapi.roots.ModifiableRootModel;
import com.intellij.openapi.roots.ModuleRootManager;
import com.intellij.openapi.util.Computable;
import com.intellij.openapi.vfs.VfsUtil;
import com.intellij.openapi.vfs.VirtualFile;
import com.intellij.psi.PsiFile;
import com.intellij.testFramework.IdeaTestCase;
import com.intellij.testFramework.PlatformTestCase;
import java.util.List;
import junit.framework.TestCase;
import org.eclipse.xtext.diagnostics.Severity;
import org.eclipse.xtext.psi.impl.BaseXtextFile;
import org.eclipse.xtext.resource.XtextResource;
import org.eclipse.xtext.util.CancelIndicator;
import org.eclipse.xtext.validation.CheckMode;
import org.eclipse.xtext.validation.Issue;
import org.eclipse.xtext.xbase.lib.Exceptions;
import org.eclipse.xtext.xbase.lib.Functions.Function1;
import org.eclipse.xtext.xbase.lib.IterableExtensions;
import org.eclipse.xtext.xbase.lib.Pair;

@SuppressWarnings("all")
public abstract class AbstractIdeaTestCase extends IdeaTestCase {
  private VirtualFile srcFolder;
  
  public abstract void configureModule(final Module module, final ModifiableRootModel model, final ContentEntry entry);
  
  @Override
  protected boolean isRunInWriteAction() {
    return false;
  }
  
  protected <T extends Object> T write(final Computable<T> c) {
    return ApplicationManager.getApplication().<T>runWriteAction(c);
  }
  
  protected VirtualFile addFile(final Pair<String, String> file) {
    final Computable<VirtualFile> _function = () -> {
      try {
        final VirtualFile result = this.srcFolder.findOrCreateChildData(this, file.getKey());
        VfsUtil.saveText(result, file.getValue());
        this.assertNoCompileErrors(result);
        return result;
      } catch (Throwable _e) {
        throw Exceptions.sneakyThrow(_e);
      }
    };
    return this.<VirtualFile>write(_function);
  }
  
  @Override
  protected void setUp() throws Exception {
    super.setUp();
    final Computable<Object> _function = () -> {
      try {
        final ModuleRootManager mnr = ModuleRootManager.getInstance(this.getModule());
        final ModifiableRootModel model = mnr.getModifiableModel();
        final ContentEntry entry = model.addContentEntry(this.getProject().getBaseDir());
        this.srcFolder = VfsUtil.createDirectoryIfMissing(this.getProject().getBaseDir(), "src");
        entry.addSourceFolder(this.srcFolder, this.isTestSource(this.srcFolder));
        this.configureModule(this.getModule(), model, entry);
        model.commit();
        return null;
      } catch (Throwable _e) {
        throw Exceptions.sneakyThrow(_e);
      }
    };
    this.<Object>write(_function);
  }
  
  protected boolean isTestSource(final VirtualFile srcFolder) {
    return false;
  }
  
  protected void assertNoCompileErrors(final VirtualFile file) {
    final PsiFile psiFile = this.getPsiManager().findFile(file);
    if ((psiFile instanceof BaseXtextFile)) {
      final XtextResource resource = ((BaseXtextFile)psiFile).getResource();
      final List<Issue> issues = resource.getResourceServiceProvider().getResourceValidator().validate(resource, 
        CheckMode.NORMAL_AND_FAST, CancelIndicator.NullImpl);
      final Function1<Issue, Boolean> _function = (Issue it) -> {
        Severity _severity = it.getSeverity();
        return Boolean.valueOf(Objects.equal(_severity, Severity.ERROR));
      };
      TestCase.assertFalse(issues.toString(), IterableExtensions.<Issue>exists(issues, _function));
    }
  }
  
  @Override
  protected Sdk getTestProjectJdk() {
    try {
      Object _clone = JavaAwareProjectJdkTableImpl.getInstanceEx().getInternalJdk().clone();
      ProjectJdkImpl jdk = ((ProjectJdkImpl) _clone);
      jdk.setName("JDK");
      return jdk;
    } catch (final Throwable _t) {
      if (_t instanceof CloneNotSupportedException) {
        final CloneNotSupportedException e = (CloneNotSupportedException)_t;
        PlatformTestCase.LOG.error(e);
        return null;
      } else {
        throw Exceptions.sneakyThrow(_t);
      }
    }
  }
}
