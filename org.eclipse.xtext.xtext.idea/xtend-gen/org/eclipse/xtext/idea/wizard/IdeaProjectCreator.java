package org.eclipse.xtext.idea.wizard;

import com.google.common.io.Resources;
import com.google.inject.Inject;
import com.intellij.ide.highlighter.ModuleFileType;
import com.intellij.openapi.module.ModifiableModuleModel;
import com.intellij.openapi.module.Module;
import com.intellij.openapi.module.StdModuleTypes;
import com.intellij.openapi.roots.ContentEntry;
import com.intellij.openapi.roots.ModifiableRootModel;
import com.intellij.openapi.roots.ModuleRootManager;
import com.intellij.openapi.vfs.LocalFileSystem;
import com.intellij.openapi.vfs.VfsUtil;
import com.intellij.openapi.vfs.VirtualFile;
import java.io.File;
import java.util.List;
import java.util.function.Consumer;
import org.eclipse.xtext.idea.config.XtextProjectConfigurator;
import org.eclipse.xtext.idea.facet.AbstractFacetConfiguration;
import org.eclipse.xtext.idea.facet.GeneratorConfigurationState;
import org.eclipse.xtext.idea.lang.XtextLanguage;
import org.eclipse.xtext.xbase.lib.Conversions;
import org.eclipse.xtext.xbase.lib.Exceptions;
import org.eclipse.xtext.xbase.lib.Functions.Function1;
import org.eclipse.xtext.xbase.lib.ListExtensions;
import org.eclipse.xtext.xtext.wizard.AbstractFile;
import org.eclipse.xtext.xtext.wizard.BinaryFile;
import org.eclipse.xtext.xtext.wizard.Outlet;
import org.eclipse.xtext.xtext.wizard.ParentProjectDescriptor;
import org.eclipse.xtext.xtext.wizard.ProjectDescriptor;
import org.eclipse.xtext.xtext.wizard.ProjectsCreator;
import org.eclipse.xtext.xtext.wizard.TextFile;
import org.eclipse.xtext.xtext.wizard.WizardConfiguration;
import org.jetbrains.jps.model.java.JavaSourceRootProperties;
import org.jetbrains.jps.model.java.JavaSourceRootType;
import org.jetbrains.jps.model.java.JpsJavaExtensionService;

@SuppressWarnings("all")
public class IdeaProjectCreator implements ProjectsCreator {
  public static class Factory {
    public IdeaProjectCreator create(final ModifiableModuleModel model) {
      final IdeaProjectCreator pc = new IdeaProjectCreator(model);
      XtextLanguage.INSTANCE.injectMembers(pc);
      return pc;
    }
  }
  
  @Inject
  private XtextProjectConfigurator projectConfigrator;
  
  private ModifiableModuleModel model;
  
  public IdeaProjectCreator(final ModifiableModuleModel model) {
    this.model = model;
  }
  
  @Override
  public void createProjects(final WizardConfiguration config) {
    final Consumer<ProjectDescriptor> _function = (ProjectDescriptor it) -> {
      this.createProject(it);
    };
    config.getEnabledProjects().forEach(_function);
  }
  
  public Module createProject(final ProjectDescriptor project) {
    try {
      final VirtualFile projectRoot = VfsUtil.createDirectories(project.getLocation());
      final LocalFileSystem fileSystem = LocalFileSystem.getInstance();
      final Consumer<AbstractFile> _function = (AbstractFile it) -> {
        try {
          String _pathFor = project.getConfig().getSourceLayout().getPathFor(it.getOutlet());
          String _plus = (_pathFor + "/");
          String _relativePath = it.getRelativePath();
          final String projectRelativePath = (_plus + _relativePath);
          String _path = projectRoot.getPath();
          File _file = new File(_path);
          final File ioFile = new File(_file, projectRelativePath);
          ioFile.getParentFile().mkdirs();
          ioFile.createNewFile();
          boolean _isExecutable = it.isExecutable();
          if (_isExecutable) {
            ioFile.setExecutable(true);
          }
          final VirtualFile virtualFile = fileSystem.refreshAndFindFileByIoFile(ioFile);
          boolean _matched = false;
          if (it instanceof TextFile) {
            _matched=true;
            VfsUtil.saveText(virtualFile, ((TextFile)it).getContent());
          }
          if (!_matched) {
            if (it instanceof BinaryFile) {
              _matched=true;
              virtualFile.setBinaryContent(Resources.toByteArray(((BinaryFile)it).getContent()));
            }
          }
        } catch (Throwable _e) {
          throw Exceptions.sneakyThrow(_e);
        }
      };
      project.getFiles().forEach(_function);
      final Module module = this.model.newModule(this.moduleFilePath(project), StdModuleTypes.JAVA.getId());
      final ModifiableRootModel rootModel = ModuleRootManager.getInstance(module).getModifiableModel();
      rootModel.inheritSdk();
      final VirtualFile modelContentRootDir = fileSystem.refreshAndFindFileByPath(project.getLocation());
      final ContentEntry contentEntry = rootModel.addContentEntry(modelContentRootDir);
      final Function1<Outlet, String> _function_1 = (Outlet it) -> {
        return project.sourceFolder(it);
      };
      final List<String> genFolders = ListExtensions.<Outlet, String>map(((List<Outlet>)Conversions.doWrapArray(Outlet.generateOutlets())), _function_1);
      final Consumer<String> _function_2 = (String it) -> {
        try {
          final VirtualFile sourceRoot = VfsUtil.createDirectoryIfMissing(modelContentRootDir, it);
          JavaSourceRootType rootType = JavaSourceRootType.SOURCE;
          final boolean isGen = genFolders.contains(it);
          final JavaSourceRootProperties properties = JpsJavaExtensionService.getInstance().createSourceRootProperties("", isGen);
          contentEntry.<JavaSourceRootProperties>addSourceFolder(sourceRoot, rootType, properties);
        } catch (Throwable _e) {
          throw Exceptions.sneakyThrow(_e);
        }
      };
      project.getSourceFolders().forEach(_function_2);
      if ((!(project instanceof ParentProjectDescriptor))) {
        final AbstractFacetConfiguration conf = this.projectConfigrator.createOrGetFacetConf(module, "org.eclipse.xtend.core.Xtend");
        if ((conf != null)) {
          GeneratorConfigurationState _state = conf.getState();
          _state.setOutputDirectory(this.absoluteSourceFolder(project, Outlet.MAIN_XTEND_GEN));
          GeneratorConfigurationState _state_1 = conf.getState();
          _state_1.setTestOutputDirectory(this.absoluteSourceFolder(project, Outlet.TEST_XTEND_GEN));
        }
      }
      rootModel.commit();
      return module;
    } catch (Throwable _e) {
      throw Exceptions.sneakyThrow(_e);
    }
  }
  
  public String absoluteSourceFolder(final ProjectDescriptor project, final Outlet outlet) {
    String _location = project.getLocation();
    String _plus = (_location + "/");
    String _sourceFolder = project.sourceFolder(outlet);
    return (_plus + _sourceFolder);
  }
  
  public String moduleFilePath(final ProjectDescriptor project) {
    String _location = project.getLocation();
    String _plus = (_location + File.separator);
    String _name = project.getName();
    String _plus_1 = (_plus + _name);
    return (_plus_1 + ModuleFileType.DOT_DEFAULT_EXTENSION);
  }
}
