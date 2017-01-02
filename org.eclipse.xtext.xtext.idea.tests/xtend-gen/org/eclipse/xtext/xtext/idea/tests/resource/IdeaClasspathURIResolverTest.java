package org.eclipse.xtext.xtext.idea.tests.resource;

import com.intellij.psi.PsiClass;
import com.intellij.psi.PsiFile;
import com.intellij.psi.search.GlobalSearchScope;
import junit.framework.TestCase;
import org.eclipse.emf.common.util.URI;
import org.eclipse.xtext.idea.lang.XtextFileType;
import org.eclipse.xtext.idea.resource.IdeaClasspathURIResolver;
import org.eclipse.xtext.idea.resource.VirtualFileURIUtil;
import org.eclipse.xtext.idea.tests.LightToolingTest;
import org.eclipse.xtext.resource.ClasspathUriUtil;
import org.junit.Test;

@SuppressWarnings("all")
public class IdeaClasspathURIResolverTest extends LightToolingTest {
  public IdeaClasspathURIResolverTest() {
    super(XtextFileType.INSTANCE);
  }
  
  private final IdeaClasspathURIResolver resolver = new IdeaClasspathURIResolver();
  
  @Test
  public void testResolveFromSourceFolder() {
    final PsiFile file = this.myFixture.addFileToProject("foo/MyFile.text", "My Contents");
    final URI result = this.resolver.resolve(this.myFixture.getModule(), URI.createURI((ClasspathUriUtil.CLASSPATH_SCHEME + "://foo/MyFile.text")));
    TestCase.assertEquals(VirtualFileURIUtil.getURI(file.getVirtualFile()), result);
  }
  
  @Test
  public void testResolveFromLibrary() {
    final PsiClass type = this.myFixture.getJavaFacade().findClass("java.lang.String", GlobalSearchScope.moduleWithLibrariesScope(this.myFixture.getModule()));
    final URI result = this.resolver.resolve(this.myFixture.getModule(), URI.createURI((ClasspathUriUtil.CLASSPATH_SCHEME + "://java/lang/String.class")));
    TestCase.assertEquals(VirtualFileURIUtil.getURI(type.getContainingFile().getVirtualFile()), result);
  }
}
