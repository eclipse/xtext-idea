package org.eclipse.xtext.xtext.idea.tests.wizard;

import com.intellij.openapi.module.Module;
import com.intellij.openapi.module.ModuleManager;
import com.intellij.openapi.project.Project;
import com.intellij.testFramework.PsiTestCase;
import java.io.IOException;
import java.util.List;
import junit.framework.TestCase;
import org.eclipse.xtend2.lib.StringConcatenation;
import org.eclipse.xtext.idea.wizard.XtextModuleBuilder;
import org.eclipse.xtext.xbase.lib.Conversions;
import org.eclipse.xtext.xtext.wizard.BuildSystem;
import org.eclipse.xtext.xtext.wizard.LanguageDescriptor;
import org.eclipse.xtext.xtext.wizard.ProjectLayout;
import org.eclipse.xtext.xtext.wizard.WizardConfiguration;
import org.junit.Test;

@SuppressWarnings("all")
public class IdeaProjectCreatorTest extends PsiTestCase {
  private XtextModuleBuilder builder;
  
  @Override
  protected void setUp() throws Exception {
    super.setUp();
    XtextModuleBuilder _xtextModuleBuilder = new XtextModuleBuilder();
    this.builder = _xtextModuleBuilder;
    this.builder.setName("mydsl");
    WizardConfiguration _wizardConfiguration = this.builder.getWizardConfiguration();
    LanguageDescriptor _language = _wizardConfiguration.getLanguage();
    _language.setName("org.xtext.MyDsl");
  }
  
  @Override
  protected Module createMainModule() throws IOException {
    return null;
  }
  
  @Test
  public void testCreateProject() {
    final List<Module> modules = this.executeModuleBuilder();
    TestCase.assertEquals(1, modules.size());
    TestCase.assertEquals("mydsl", modules.get(0).getName());
    TestCase.assertTrue(modules.get(0).getModuleFilePath().endsWith("/mydsl/mydsl.iml"));
  }
  
  public List<Module> executeModuleBuilder() {
    Project _project = this.getProject();
    final List<Module> modules = this.builder.commit(_project);
    return modules;
  }
  
  @Test
  public void testCreateGradleProject() {
    WizardConfiguration _wizardConfiguration = this.builder.getWizardConfiguration();
    _wizardConfiguration.setPreferredBuildSystem(BuildSystem.GRADLE);
    final List<Module> modules = this.executeModuleBuilder();
    TestCase.assertEquals(2, modules.size());
    TestCase.assertEquals("mydsl.parent", modules.get(0).getName());
    TestCase.assertTrue(modules.get(0).getModuleFilePath().endsWith("/mydsl.parent/mydsl.parent.iml"));
    TestCase.assertEquals("mydsl", modules.get(1).getName());
    TestCase.assertTrue(modules.get(1).getModuleFilePath().endsWith("/mydsl/mydsl.iml"));
  }
  
  @Test
  public void testCreateGradleHirachicalProject() {
    WizardConfiguration _wizardConfiguration = this.builder.getWizardConfiguration();
    _wizardConfiguration.setPreferredBuildSystem(BuildSystem.GRADLE);
    WizardConfiguration _wizardConfiguration_1 = this.builder.getWizardConfiguration();
    _wizardConfiguration_1.setProjectLayout(ProjectLayout.HIERARCHICAL);
    final List<Module> modules = this.executeModuleBuilder();
    TestCase.assertEquals(2, modules.size());
    TestCase.assertEquals("mydsl.parent", modules.get(0).getName());
    TestCase.assertTrue(modules.get(0).getModuleFilePath().endsWith("/mydsl.parent/mydsl.parent.iml"));
    TestCase.assertEquals("mydsl", modules.get(1).getName());
    TestCase.assertTrue(modules.get(1).getModuleFilePath().endsWith("/mydsl.parent/mydsl/mydsl.iml"));
    Project _project = this.getProject();
    ModuleManager _instance = ModuleManager.getInstance(_project);
    final Module[] allModules = _instance.getModules();
    TestCase.assertEquals(2, ((List<Module>)Conversions.doWrapArray(allModules)).size());
  }
  
  @Test
  public void testCreateTwoLanguagesProject() {
    Project _project = this.getProject();
    ModuleManager _instance = ModuleManager.getInstance(_project);
    final Module[] allModules = _instance.getModules();
    TestCase.assertEquals(0, ((List<Module>)Conversions.doWrapArray(allModules)).size());
    WizardConfiguration _wizardConfiguration = this.builder.getWizardConfiguration();
    _wizardConfiguration.setPreferredBuildSystem(BuildSystem.MAVEN);
    WizardConfiguration _wizardConfiguration_1 = this.builder.getWizardConfiguration();
    _wizardConfiguration_1.setProjectLayout(ProjectLayout.HIERARCHICAL);
    this.builder.setName("mydsl");
    final List<Module> modules = this.executeModuleBuilder();
    TestCase.assertEquals(2, modules.size());
    TestCase.assertEquals("mydsl.parent", modules.get(0).getName());
    TestCase.assertTrue(modules.get(0).getModuleFilePath().endsWith("/mydsl.parent/mydsl.parent.iml"));
    TestCase.assertEquals("mydsl", modules.get(1).getName());
    TestCase.assertTrue(modules.get(1).getModuleFilePath().endsWith("/mydsl.parent/mydsl/mydsl.iml"));
    this.builder.setName("mydsl2");
    Project _project_1 = this.getProject();
    final Module rootModule = this.builder.commitModule(_project_1, null);
    TestCase.assertEquals("mydsl2.parent", rootModule.getName());
    StringConcatenation _builder = new StringConcatenation();
    _builder.append("Wrong .iml path  ");
    String _moduleFilePath = rootModule.getModuleFilePath();
    _builder.append(_moduleFilePath);
    Project _project_2 = this.getProject();
    String _basePath = _project_2.getBasePath();
    String _plus = (_basePath + "/mydsl2.parent/mydsl2.parent.iml");
    TestCase.assertEquals(_builder.toString(), _plus, rootModule.getModuleFilePath());
  }
}
