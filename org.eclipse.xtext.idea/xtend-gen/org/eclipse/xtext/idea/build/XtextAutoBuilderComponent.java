/**
 * Copyright (c) 2015 itemis AG (http://www.itemis.eu) and others.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 */
package org.eclipse.xtext.idea.build;

import com.google.common.base.Objects;
import com.google.common.collect.Iterables;
import com.google.inject.Inject;
import com.google.inject.Provider;
import com.intellij.ProjectTopics;
import com.intellij.compiler.ModuleCompilerUtil;
import com.intellij.facet.Facet;
import com.intellij.facet.FacetType;
import com.intellij.facet.ProjectWideFacetAdapter;
import com.intellij.facet.ProjectWideFacetListenersRegistry;
import com.intellij.openapi.Disposable;
import com.intellij.openapi.application.Application;
import com.intellij.openapi.application.ApplicationManager;
import com.intellij.openapi.components.AbstractProjectComponent;
import com.intellij.openapi.components.PersistentStateComponent;
import com.intellij.openapi.components.State;
import com.intellij.openapi.components.Storage;
import com.intellij.openapi.components.StoragePathMacros;
import com.intellij.openapi.components.StorageScheme;
import com.intellij.openapi.editor.Document;
import com.intellij.openapi.editor.EditorFactory;
import com.intellij.openapi.editor.event.DocumentAdapter;
import com.intellij.openapi.editor.event.DocumentEvent;
import com.intellij.openapi.editor.event.EditorEventMulticaster;
import com.intellij.openapi.fileEditor.FileDocumentManager;
import com.intellij.openapi.module.Module;
import com.intellij.openapi.module.ModuleManager;
import com.intellij.openapi.progress.ProgressIndicator;
import com.intellij.openapi.progress.ProgressManager;
import com.intellij.openapi.progress.Task;
import com.intellij.openapi.project.DumbService;
import com.intellij.openapi.project.ModuleAdapter;
import com.intellij.openapi.project.ModuleListener;
import com.intellij.openapi.project.Project;
import com.intellij.openapi.roots.ContentEntry;
import com.intellij.openapi.roots.ModuleRootAdapter;
import com.intellij.openapi.roots.ModuleRootEvent;
import com.intellij.openapi.roots.ModuleRootListener;
import com.intellij.openapi.roots.ModuleRootManager;
import com.intellij.openapi.roots.ProjectFileIndex;
import com.intellij.openapi.util.Computable;
import com.intellij.openapi.util.Disposer;
import com.intellij.openapi.util.io.FileUtil;
import com.intellij.openapi.vfs.VfsUtilCore;
import com.intellij.openapi.vfs.VirtualFile;
import com.intellij.openapi.vfs.VirtualFileAdapter;
import com.intellij.openapi.vfs.VirtualFileEvent;
import com.intellij.openapi.vfs.VirtualFileManager;
import com.intellij.openapi.vfs.VirtualFileMoveEvent;
import com.intellij.openapi.vfs.VirtualFilePropertyEvent;
import com.intellij.openapi.vfs.VirtualFileVisitor;
import com.intellij.psi.PsiClass;
import com.intellij.psi.PsiFile;
import com.intellij.psi.PsiJavaFile;
import com.intellij.psi.PsiManager;
import com.intellij.psi.impl.PsiModificationTrackerImpl;
import com.intellij.psi.util.PsiModificationTracker;
import com.intellij.util.Alarm;
import com.intellij.util.Function;
import com.intellij.util.graph.Graph;
import com.intellij.util.messages.MessageBusConnection;
import java.util.ArrayList;
import java.util.Collection;
import java.util.HashSet;
import java.util.LinkedHashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.concurrent.LinkedBlockingQueue;
import java.util.function.Consumer;
import org.apache.log4j.Logger;
import org.eclipse.emf.common.util.URI;
import org.eclipse.emf.ecore.resource.ResourceSet;
import org.eclipse.xtend.lib.annotations.FinalFieldsConstructor;
import org.eclipse.xtext.build.BuildRequest;
import org.eclipse.xtext.build.IncrementalBuilder;
import org.eclipse.xtext.build.IndexState;
import org.eclipse.xtext.build.Source2GeneratedMapping;
import org.eclipse.xtext.common.types.descriptions.TypeResourceDescription;
import org.eclipse.xtext.idea.build.BuildEvent;
import org.eclipse.xtext.idea.build.BuildProgressReporter;
import org.eclipse.xtext.idea.build.XtextAutoBuilderComponentState;
import org.eclipse.xtext.idea.facet.AbstractFacetConfiguration;
import org.eclipse.xtext.idea.facet.AbstractFacetType;
import org.eclipse.xtext.idea.facet.FacetProvider;
import org.eclipse.xtext.idea.resource.IdeaResourceSetProvider;
import org.eclipse.xtext.idea.resource.VirtualFileURIUtil;
import org.eclipse.xtext.idea.shared.IdeaSharedInjectorProvider;
import org.eclipse.xtext.naming.IQualifiedNameConverter;
import org.eclipse.xtext.naming.QualifiedName;
import org.eclipse.xtext.resource.IResourceDescription;
import org.eclipse.xtext.resource.IResourceServiceProvider;
import org.eclipse.xtext.resource.XtextResourceSet;
import org.eclipse.xtext.resource.impl.ChunkedResourceDescriptions;
import org.eclipse.xtext.resource.impl.ResourceDescriptionsData;
import org.eclipse.xtext.service.OperationCanceledManager;
import org.eclipse.xtext.util.CancelIndicator;
import org.eclipse.xtext.util.internal.Log;
import org.eclipse.xtext.xbase.lib.CollectionExtensions;
import org.eclipse.xtext.xbase.lib.CollectionLiterals;
import org.eclipse.xtext.xbase.lib.Conversions;
import org.eclipse.xtext.xbase.lib.Exceptions;
import org.eclipse.xtext.xbase.lib.Functions.Function1;
import org.eclipse.xtext.xbase.lib.IterableExtensions;
import org.eclipse.xtext.xbase.lib.ListExtensions;
import org.eclipse.xtext.xbase.lib.ObjectExtensions;
import org.eclipse.xtext.xbase.lib.Procedures.Procedure0;
import org.eclipse.xtext.xbase.lib.Procedures.Procedure1;

/**
 * @author Jan Koehnlein - Initial contribution and API
 */
@State(name = "XtextAutoBuilderState", storages = { @Storage(id = "other", file = StoragePathMacros.WORKSPACE_FILE), @Storage(id = "dir", file = (StoragePathMacros.PROJECT_CONFIG_DIR + "/xtextAutoBuilderState.xml"), scheme = StorageScheme.DIRECTORY_BASED) })
@Log
@SuppressWarnings("all")
public class XtextAutoBuilderComponent extends AbstractProjectComponent implements Disposable, PersistentStateComponent<XtextAutoBuilderComponentState> {
  @FinalFieldsConstructor
  public static class MutableCancelIndicator implements CancelIndicator {
    private final ProgressIndicator indicator;
    
    private volatile boolean canceled;
    
    @Override
    public boolean isCanceled() {
      boolean _or = false;
      if (this.canceled) {
        _or = true;
      } else {
        boolean _isCanceled = false;
        if (this.indicator!=null) {
          _isCanceled=this.indicator.isCanceled();
        }
        _or = _isCanceled;
      }
      return _or;
    }
    
    public boolean setCanceled(final boolean canceled) {
      return this.canceled = canceled;
    }
    
    public MutableCancelIndicator(final ProgressIndicator indicator) {
      super();
      this.indicator = indicator;
    }
  }
  
  private volatile boolean disposed;
  
  private final LinkedBlockingQueue<BuildEvent> queue = new LinkedBlockingQueue<BuildEvent>();
  
  private final LinkedBlockingQueue<XtextAutoBuilderComponent.MutableCancelIndicator> cancelIndicators = new LinkedBlockingQueue<XtextAutoBuilderComponent.MutableCancelIndicator>();
  
  private Alarm alarm;
  
  private Project project;
  
  @Inject
  private Provider<IncrementalBuilder> builderProvider;
  
  @Inject
  private Provider<BuildProgressReporter> buildProgressReporterProvider;
  
  @Inject
  private IdeaResourceSetProvider resourceSetProvider;
  
  @Inject
  private IResourceServiceProvider.Registry resourceServiceProviderRegistry;
  
  @Inject
  private IQualifiedNameConverter qualifiedNameConverter;
  
  @Inject
  private Provider<ChunkedResourceDescriptions> chunkedResourceDescriptionsProvider;
  
  @Inject
  private OperationCanceledManager operationCanceledManager;
  
  private ChunkedResourceDescriptions chunkedResourceDescriptions;
  
  private Map<String, Source2GeneratedMapping> moduleName2GeneratedMapping = CollectionLiterals.<String, Source2GeneratedMapping>newHashMap();
  
  public Iterable<URI> getGeneratedSources(final URI source) {
    final Function1<Source2GeneratedMapping, List<URI>> _function = (Source2GeneratedMapping it) -> {
      return it.getGenerated(source);
    };
    return IterableExtensions.<URI>toList(Iterables.<URI>concat(IterableExtensions.<Source2GeneratedMapping, List<URI>>map(this.moduleName2GeneratedMapping.values(), _function)));
  }
  
  public Iterable<URI> getSource4GeneratedSource(final URI generated) {
    final Function1<Source2GeneratedMapping, List<URI>> _function = (Source2GeneratedMapping it) -> {
      return it.getSource(generated);
    };
    return IterableExtensions.<URI>toList(Iterables.<URI>concat(IterableExtensions.<Source2GeneratedMapping, List<URI>>map(this.moduleName2GeneratedMapping.values(), _function)));
  }
  
  public XtextAutoBuilderComponent(final Project project) {
    super(project);
    XtextAutoBuilderComponent.TEST_MODE = ApplicationManager.getApplication().isUnitTestMode();
    IdeaSharedInjectorProvider.getInjector().injectMembers(this);
    this.chunkedResourceDescriptions = this.chunkedResourceDescriptionsProvider.get();
    this.project = project;
    Alarm _alarm = new Alarm(Alarm.ThreadToUse.SWING_THREAD, this);
    this.alarm = _alarm;
    this.disposed = false;
    Disposer.register(project, this);
    EditorEventMulticaster _eventMulticaster = EditorFactory.getInstance().getEventMulticaster();
    _eventMulticaster.addDocumentListener(new DocumentAdapter() {
      @Override
      public void documentChanged(final DocumentEvent event) {
        VirtualFile file = FileDocumentManager.getInstance().getFile(event.getDocument());
        if ((file != null)) {
          XtextAutoBuilderComponent.this.fileModified(file);
        } else {
          Document _document = event.getDocument();
          String _plus = ("No virtual file for document. Contents was " + _document);
          XtextAutoBuilderComponent.LOG.info(_plus);
        }
      }
    }, project);
    VirtualFileManager _instance = VirtualFileManager.getInstance();
    _instance.addVirtualFileListener(new VirtualFileAdapter() {
      @Override
      public void beforePropertyChange(final VirtualFilePropertyEvent event) {
        String _propertyName = event.getPropertyName();
        boolean _equals = Objects.equal(_propertyName, VirtualFile.PROP_NAME);
        if (_equals) {
          XtextAutoBuilderComponent.this.scheduleDeletion(event.getFile());
        }
      }
      
      @Override
      public void propertyChanged(final VirtualFilePropertyEvent event) {
        String _propertyName = event.getPropertyName();
        boolean _equals = Objects.equal(_propertyName, VirtualFile.PROP_NAME);
        if (_equals) {
          XtextAutoBuilderComponent.this.commitScheduledDeletion();
          XtextAutoBuilderComponent.this.fileAdded(event.getFile());
        }
      }
      
      @Override
      public void contentsChanged(final VirtualFileEvent event) {
        XtextAutoBuilderComponent.this.fileModified(event.getFile());
      }
      
      @Override
      public void fileCreated(final VirtualFileEvent event) {
        XtextAutoBuilderComponent.this.fileAdded(event.getFile());
      }
      
      @Override
      public void fileDeleted(final VirtualFileEvent event) {
        XtextAutoBuilderComponent.this.commitScheduledDeletion();
      }
      
      @Override
      public void beforeFileDeletion(final VirtualFileEvent event) {
        XtextAutoBuilderComponent.this.scheduleDeletion(event.getFile());
      }
      
      @Override
      public void beforeFileMovement(final VirtualFileMoveEvent event) {
        XtextAutoBuilderComponent.this.scheduleDeletion(event.getFile());
      }
      
      @Override
      public void fileMoved(final VirtualFileMoveEvent event) {
        XtextAutoBuilderComponent.this.commitScheduledDeletion();
        XtextAutoBuilderComponent.this.fileAdded(event.getFile());
      }
    }, project);
    final MessageBusConnection connection = project.getMessageBus().connect(project);
    connection.<ModuleRootListener>subscribe(ProjectTopics.PROJECT_ROOTS, new ModuleRootAdapter() {
      @Override
      public void rootsChanged(final ModuleRootEvent event) {
        XtextAutoBuilderComponent.this.doCleanBuild();
      }
    });
    connection.<ModuleListener>subscribe(ProjectTopics.MODULES, new ModuleAdapter() {
      @Override
      public void moduleAdded(final Project project, final Module module) {
        boolean _isInitialized = project.isInitialized();
        if (_isInitialized) {
          XtextAutoBuilderComponent.this.doCleanBuild(module);
        }
      }
      
      @Override
      public void moduleRemoved(final Project project, final Module module) {
        XtextAutoBuilderComponent.this.chunkedResourceDescriptions.removeContainer(module.getName());
        XtextAutoBuilderComponent.this.moduleName2GeneratedMapping.remove(module.getName());
      }
      
      @Override
      public void modulesRenamed(final Project project, final List<Module> modules, final Function<Module, String> oldNameProvider) {
        for (final Module module : modules) {
          {
            XtextAutoBuilderComponent.this.chunkedResourceDescriptions.removeContainer(oldNameProvider.fun(module));
            XtextAutoBuilderComponent.this.moduleName2GeneratedMapping.remove(module.getName());
            XtextAutoBuilderComponent.this.doCleanBuild(module);
          }
        }
      }
    });
    ProjectWideFacetListenersRegistry _instance_1 = ProjectWideFacetListenersRegistry.getInstance(project);
    _instance_1.registerListener(new ProjectWideFacetAdapter<Facet>() {
      @Override
      public void facetAdded(final Facet facet) {
        if (((!XtextAutoBuilderComponent.this.isXtextFacet(facet)) || (!project.isInitialized()))) {
          return;
        }
        XtextAutoBuilderComponent.this.doCleanBuild(facet.getModule());
      }
      
      @Override
      public void facetRemoved(final Facet facet) {
        boolean _isXtextFacet = XtextAutoBuilderComponent.this.isXtextFacet(facet);
        boolean _not = (!_isXtextFacet);
        if (_not) {
          return;
        }
        XtextAutoBuilderComponent.this.doCleanBuild(facet.getModule());
      }
      
      @Override
      public void facetConfigurationChanged(final Facet facet) {
        boolean _isXtextFacet = XtextAutoBuilderComponent.this.isXtextFacet(facet);
        boolean _not = (!_isXtextFacet);
        if (_not) {
          return;
        }
        XtextAutoBuilderComponent.this.doCleanBuild(facet.getModule());
      }
    }, this);
  }
  
  protected boolean isXtextFacet(final Facet<?> facet) {
    final FacetType facetType = facet.getType();
    return (facetType instanceof AbstractFacetType<?>);
  }
  
  @Override
  public void dispose() {
    this.disposed = true;
    this.alarm.cancelAllRequests();
    this.queue.clear();
    this.chunkedResourceDescriptions = this.chunkedResourceDescriptionsProvider.get();
  }
  
  protected Project getProject() {
    return this.myProject;
  }
  
  public void fileModified(final VirtualFile file) {
    this.enqueue(BuildEvent.Type.MODIFIED, file);
  }
  
  public void scheduleDeletion(final VirtualFile root) {
    Module _findModule = this.findModule(root, ProjectFileIndex.SERVICE.getInstance(this.project));
    boolean _tripleEquals = (_findModule == null);
    if (_tripleEquals) {
      return;
    }
    final ArrayList<VirtualFile> files = CollectionLiterals.<VirtualFile>newArrayList();
    VfsUtilCore.visitChildrenRecursively(root, new VirtualFileVisitor() {
      @Override
      public boolean visitFile(final VirtualFile file) {
        boolean _xblockexpression = false;
        {
          boolean _isDirectory = file.isDirectory();
          boolean _not = (!_isDirectory);
          if (_not) {
            files.add(file);
          }
          _xblockexpression = true;
        }
        return _xblockexpression;
      }
    });
    boolean _isEmpty = files.isEmpty();
    boolean _not = (!_isEmpty);
    if (_not) {
      BuildEvent _buildEvent = new BuildEvent(BuildEvent.Type.DELETED, ((VirtualFile[])Conversions.unwrapArray(files, VirtualFile.class)));
      CollectionExtensions.<BuildEvent>addAll(this.scheduledDeletions, _buildEvent);
    }
  }
  
  private final LinkedBlockingQueue<BuildEvent> scheduledDeletions = new LinkedBlockingQueue<BuildEvent>();
  
  public void commitScheduledDeletion() {
    final ArrayList<BuildEvent> scheduledEvents = CollectionLiterals.<BuildEvent>newArrayList();
    this.scheduledDeletions.drainTo(scheduledEvents);
    for (final BuildEvent event : scheduledEvents) {
      this.enqueue(event);
    }
  }
  
  public void fileAdded(final VirtualFile file) {
    if (((!file.isDirectory()) && (file.getLength() > 0))) {
      this.enqueue(BuildEvent.Type.ADDED, file);
    } else {
      boolean _isInfoEnabled = XtextAutoBuilderComponent.LOG.isInfoEnabled();
      if (_isInfoEnabled) {
        String _path = file.getPath();
        String _plus = ("Ignoring new empty file " + _path);
        String _plus_1 = (_plus + ". Waiting for content.");
        XtextAutoBuilderComponent.LOG.info(_plus_1);
      }
    }
  }
  
  /**
   * For testing purposes! When set to <code>true</code>, the builds are not running asynchronously and delayed, but directly when the event comes in
   */
  public static boolean TEST_MODE = false;
  
  protected void enqueue(final BuildEvent.Type type, final VirtualFile... files) {
    final Function1<VirtualFile, Boolean> _function = (VirtualFile it) -> {
      boolean _isExcluded = this.isExcluded(it);
      return Boolean.valueOf((!_isExcluded));
    };
    final Iterable<VirtualFile> filteredFiles = IterableExtensions.<VirtualFile>filter(((Iterable<VirtualFile>)Conversions.doWrapArray(files)), _function);
    boolean _isEmpty = IterableExtensions.isEmpty(filteredFiles);
    if (_isEmpty) {
      return;
    }
    BuildEvent _buildEvent = new BuildEvent(type, ((VirtualFile[])Conversions.unwrapArray(filteredFiles, VirtualFile.class)));
    this.enqueue(_buildEvent);
  }
  
  protected void enqueue(final BuildEvent buildEvent) {
    try {
      if (((!this.disposed) && (!this.isLoaded()))) {
        this.queueAllResources();
      }
      boolean _isInfoEnabled = XtextAutoBuilderComponent.LOG.isInfoEnabled();
      if (_isInfoEnabled) {
        Set<URI> _keySet = buildEvent.getFilesByURI().keySet();
        for (final URI uri : _keySet) {
          BuildEvent.Type _type = buildEvent.getType();
          String _plus = ("Queuing " + _type);
          String _plus_1 = (_plus + " - ");
          String _plus_2 = (_plus_1 + uri);
          String _plus_3 = (_plus_2 + ".");
          XtextAutoBuilderComponent.LOG.info(_plus_3);
        }
      }
      if ((!this.disposed)) {
        this.queue.put(buildEvent);
        this.doRunBuild();
      }
    } catch (Throwable _e) {
      throw Exceptions.sneakyThrow(_e);
    }
  }
  
  protected void doCleanBuild() {
    if (this.ignoreIncomingEvents) {
      return;
    }
    this.alarm.cancelAllRequests();
    this.chunkedResourceDescriptions = this.chunkedResourceDescriptionsProvider.get();
    final Function1<Source2GeneratedMapping, List<URI>> _function = (Source2GeneratedMapping it) -> {
      return it.getAllGenerated();
    };
    this.safeDeleteUris(IterableExtensions.<URI>toList(Iterables.<URI>concat(IterableExtensions.<Source2GeneratedMapping, List<URI>>map(this.moduleName2GeneratedMapping.values(), _function))));
    this.moduleName2GeneratedMapping.clear();
    this.queueAllResources();
    this.doRunBuild();
  }
  
  protected void doCleanBuild(final Module module) {
    if (this.ignoreIncomingEvents) {
      return;
    }
    this.alarm.cancelAllRequests();
    this.chunkedResourceDescriptions.removeContainer(module.getName());
    final Source2GeneratedMapping before = this.moduleName2GeneratedMapping.remove(module.getName());
    if ((before != null)) {
      this.safeDeleteUris(before.getAllGenerated());
    }
    this.queueAllResources(module);
    this.doRunBuild();
  }
  
  protected void safeDeleteUris(final List<URI> uris) {
    boolean _isEmpty = uris.isEmpty();
    boolean _not = (!_isEmpty);
    if (_not) {
      final Application app = ApplicationManager.getApplication();
      final Runnable _function = () -> {
        try {
          try {
            this.ignoreIncomingEvents = true;
            for (final URI uri : uris) {
              {
                final VirtualFile file = VirtualFileURIUtil.getVirtualFile(uri);
                if (((file != null) && file.exists())) {
                  file.delete(this);
                }
              }
            }
          } finally {
            this.ignoreIncomingEvents = false;
          }
        } catch (Throwable _e) {
          throw Exceptions.sneakyThrow(_e);
        }
      };
      final Runnable runnable = _function;
      boolean _isDispatchThread = app.isDispatchThread();
      if (_isDispatchThread) {
        app.runWriteAction(runnable);
      } else {
        final Runnable _function_1 = () -> {
          app.runWriteAction(runnable);
        };
        app.invokeLater(_function_1, app.getDefaultModalityState());
      }
    }
  }
  
  private boolean avoidBuildInTestMode = false;
  
  /**
   * <p>
   * Should be used in the test mode only.
   * </p>
   * <p>
   * Collects all events produced by the operation and builds them at once.
   * </p>
   */
  public void runOperation(final Procedure0 operation) {
    if ((!XtextAutoBuilderComponent.TEST_MODE)) {
      throw new IllegalStateException("Should be used only for testing");
    }
    this.avoidBuildInTestMode = true;
    try {
      operation.apply();
    } finally {
      this.avoidBuildInTestMode = false;
      this.doRunBuild();
    }
  }
  
  protected void doRunBuild() {
    if (XtextAutoBuilderComponent.TEST_MODE) {
      if ((!this.avoidBuildInTestMode)) {
        PsiModificationTracker _modificationTracker = PsiManager.getInstance(this.getProject()).getModificationTracker();
        ((PsiModificationTrackerImpl) _modificationTracker).incCounter();
        this.build();
      }
    } else {
      this.alarm.cancelAllRequests();
      final Consumer<XtextAutoBuilderComponent.MutableCancelIndicator> _function = (XtextAutoBuilderComponent.MutableCancelIndicator it) -> {
        it.canceled = true;
      };
      this.cancelIndicators.forEach(_function);
      final Runnable _function_1 = () -> {
        this.build();
      };
      this.alarm.addRequest(_function_1, 200, ApplicationManager.getApplication().getDefaultModalityState());
    }
  }
  
  protected boolean isExcluded(final VirtualFile file) {
    if (this.ignoreIncomingEvents) {
      boolean _isDebugEnabled = XtextAutoBuilderComponent.LOG.isDebugEnabled();
      if (_isDebugEnabled) {
        String _path = file.getPath();
        String _plus = ("Ignoring transitive file change " + _path);
        XtextAutoBuilderComponent.LOG.debug(_plus);
      }
      return true;
    }
    return ((file == null) || file.isDirectory());
  }
  
  protected boolean isLoaded() {
    return ((!this.chunkedResourceDescriptions.isEmpty()) || (!this.queue.isEmpty()));
  }
  
  protected void queueAllResources(final Module module) {
    final ContentEntry[] entries = ModuleRootManager.getInstance(module).getContentEntries();
    final Function1<ContentEntry, Set<VirtualFile>> _function = (ContentEntry it) -> {
      return IterableExtensions.<VirtualFile>toSet(((Iterable<VirtualFile>)Conversions.doWrapArray(it.getSourceFolderFiles())));
    };
    Iterable<VirtualFile> _flatten = Iterables.<VirtualFile>concat(ListExtensions.<ContentEntry, Set<VirtualFile>>map(((List<ContentEntry>)Conversions.doWrapArray(entries)), _function));
    for (final VirtualFile root : _flatten) {
      final Procedure1<VirtualFile> _function_1 = (VirtualFile file) -> {
        try {
          if (((!file.isDirectory()) && file.exists())) {
            BuildEvent _buildEvent = new BuildEvent(BuildEvent.Type.ADDED, file);
            this.queue.put(_buildEvent);
          }
        } catch (Throwable _e) {
          throw Exceptions.sneakyThrow(_e);
        }
      };
      this.visitFileTree(root, _function_1);
    }
  }
  
  protected void queueAllResources() {
    final ModuleManager moduleManager = ModuleManager.getInstance(this.project);
    final Computable<Module[]> _function = () -> {
      return moduleManager.getModules();
    };
    Module[] _runReadAction = ApplicationManager.getApplication().<Module[]>runReadAction(_function);
    for (final Module module : _runReadAction) {
      this.queueAllResources(module);
    }
  }
  
  public void visitFileTree(final VirtualFile file, final Procedure1<? super VirtualFile> handler) {
    boolean _isDirectory = file.isDirectory();
    if (_isDirectory) {
      VirtualFile[] _children = file.getChildren();
      for (final VirtualFile child : _children) {
        this.visitFileTree(child, handler);
      }
    }
    handler.apply(file);
  }
  
  private volatile boolean ignoreIncomingEvents = false;
  
  private final static Object BUILD_MONITOR = new Object();
  
  protected void build() {
    if (this.disposed) {
      return;
    }
    boolean _isReadyToBeBuilt = this.isReadyToBeBuilt();
    boolean _not = (!_isReadyToBeBuilt);
    if (_not) {
      XtextAutoBuilderComponent.LOG.info("Project not yet initialized, wait some more");
      final Runnable _function = () -> {
        this.build();
      };
      this.alarm.addRequest(_function, 500);
    } else {
      if (XtextAutoBuilderComponent.TEST_MODE) {
        this.internalBuild(null);
      } else {
        ProgressManager _instance = ProgressManager.getInstance();
        _instance.run(new Task.Backgroundable(this.project, "Code Generation...") {
          @Override
          public void run(final ProgressIndicator indicator) {
            indicator.setIndeterminate(true);
            synchronized (XtextAutoBuilderComponent.BUILD_MONITOR) {
              XtextAutoBuilderComponent.this.internalBuild(indicator);
            }
          }
        });
      }
    }
  }
  
  private boolean isReadyToBeBuilt() {
    return (XtextAutoBuilderComponent.TEST_MODE || (this.project.isInitialized() && (!DumbService.getInstance(this.project).isDumb())));
  }
  
  private List<BuildEvent> unProcessedEvents = CollectionLiterals.<BuildEvent>newArrayList();
  
  protected void internalBuild(final ProgressIndicator indicator) {
    this.queue.drainTo(this.unProcessedEvents);
    boolean _isEmpty = this.unProcessedEvents.isEmpty();
    if (_isEmpty) {
      return;
    }
    final Application app = ApplicationManager.getApplication();
    final XtextAutoBuilderComponent.MutableCancelIndicator cancelIndicator = new XtextAutoBuilderComponent.MutableCancelIndicator(indicator);
    this.cancelIndicators.add(cancelIndicator);
    final ModuleManager moduleManager = ModuleManager.getInstance(this.getProject());
    final BuildProgressReporter buildProgressReporter = this.buildProgressReporterProvider.get();
    buildProgressReporter.setProject(this.project);
    ArrayList<BuildEvent> _arrayList = new ArrayList<BuildEvent>(this.unProcessedEvents);
    buildProgressReporter.setEvents(_arrayList);
    try {
      final Computable<Graph<Module>> _function = () -> {
        return moduleManager.moduleGraph();
      };
      final Graph<Module> moduleGraph = app.<Graph<Module>>runReadAction(_function);
      final ArrayList<IResourceDescription.Delta> deltas = CollectionLiterals.<IResourceDescription.Delta>newArrayList();
      Collection<Module> _nodes = moduleGraph.getNodes();
      final ArrayList<Module> sortedModules = new ArrayList<Module>(_nodes);
      ModuleCompilerUtil.sortModules(this.project, sortedModules);
      for (final Module module : sortedModules) {
        {
          Source2GeneratedMapping _elvis = null;
          Source2GeneratedMapping _get = this.moduleName2GeneratedMapping.get(module.getName());
          if (_get != null) {
            _elvis = _get;
          } else {
            Source2GeneratedMapping _source2GeneratedMapping = new Source2GeneratedMapping();
            _elvis = _source2GeneratedMapping;
          }
          final Source2GeneratedMapping fileMappings = _elvis;
          ResourceDescriptionsData _elvis_1 = null;
          ResourceDescriptionsData _container = this.chunkedResourceDescriptions.getContainer(module.getName());
          if (_container != null) {
            _elvis_1 = _container;
          } else {
            List<IResourceDescription> _emptyList = CollectionLiterals.<IResourceDescription>emptyList();
            ResourceDescriptionsData _resourceDescriptionsData = new ResourceDescriptionsData(_emptyList);
            _elvis_1 = _resourceDescriptionsData;
          }
          final ResourceDescriptionsData moduleDescriptions = _elvis_1;
          final HashSet<URI> changedUris = CollectionLiterals.<URI>newHashSet();
          final HashSet<URI> deletedUris = CollectionLiterals.<URI>newHashSet();
          final VirtualFile[] contentRoots = ModuleRootManager.getInstance(module).getContentRoots();
          final LinkedHashSet<BuildEvent> events = this.getEventsForModule(this.unProcessedEvents, module);
          if ((((List<VirtualFile>)Conversions.doWrapArray(contentRoots)).isEmpty() || (events.isEmpty() && deltas.isEmpty()))) {
            String _name = module.getName();
            String _plus = ("Skipping module \'" + _name);
            String _plus_1 = (_plus + "\'. Nothing to do here.");
            XtextAutoBuilderComponent.LOG.info(_plus_1);
          } else {
            this.collectChanges(events, module, changedUris, deletedUris, deltas);
            final ResourceDescriptionsData newIndex = moduleDescriptions.copy();
            BuildRequest _buildRequest = new BuildRequest();
            final Procedure1<BuildRequest> _function_1 = (BuildRequest it) -> {
              it.setResourceSet(this.createResourceSet(module, newIndex));
              List<URI> _dirtyFiles = it.getDirtyFiles();
              Iterables.<URI>addAll(_dirtyFiles, changedUris);
              List<URI> _deletedFiles = it.getDeletedFiles();
              Iterables.<URI>addAll(_deletedFiles, deletedUris);
              List<IResourceDescription.Delta> _externalDeltas = it.getExternalDeltas();
              Iterables.<IResourceDescription.Delta>addAll(_externalDeltas, deltas);
              it.setBaseDir(VirtualFileURIUtil.getURI(IterableExtensions.<VirtualFile>head(((Iterable<VirtualFile>)Conversions.doWrapArray(contentRoots)))));
              Source2GeneratedMapping _copy = fileMappings.copy();
              IndexState _indexState = new IndexState(newIndex, _copy);
              it.setState(_indexState);
              it.setAfterValidate(buildProgressReporter);
              final Procedure1<URI> _function_2 = (URI it_1) -> {
                buildProgressReporter.markAsAffected(it_1);
              };
              it.setAfterDeleteFile(_function_2);
              it.setCancelIndicator(cancelIndicator);
            };
            final BuildRequest request = ObjectExtensions.<BuildRequest>operator_doubleArrow(_buildRequest, _function_1);
            final IncrementalBuilder.Result result = this.builderProvider.get().build(request, this.getServiceProviderProvider(module));
            final Runnable _function_2 = () -> {
              try {
                this.ignoreIncomingEvents = true;
                final IdeaResourceSetProvider.VirtualFileBasedUriHandler handler = IdeaResourceSetProvider.VirtualFileBasedUriHandler.find(request.getResourceSet());
                handler.flushToDisk();
              } finally {
                this.ignoreIncomingEvents = false;
                buildProgressReporter.rehighlight();
              }
            };
            app.invokeAndWait(_function_2, app.getDefaultModalityState());
            this.chunkedResourceDescriptions.setContainer(module.getName(), result.getIndexState().getResourceDescriptions());
            this.moduleName2GeneratedMapping.put(module.getName(), result.getIndexState().getFileMappings());
            deltas.addAll(result.getAffectedResources());
            Iterables.removeAll(this.unProcessedEvents, events);
          }
        }
      }
      this.unProcessedEvents.clear();
    } catch (final Throwable _t) {
      if (_t instanceof Throwable) {
        final Throwable exc = (Throwable)_t;
        boolean _isOperationCanceledException = this.operationCanceledManager.isOperationCanceledException(exc);
        if (_isOperationCanceledException) {
          boolean _isInfoEnabled = XtextAutoBuilderComponent.LOG.isInfoEnabled();
          if (_isInfoEnabled) {
            XtextAutoBuilderComponent.LOG.info("Build canceled.");
          }
        } else {
          XtextAutoBuilderComponent.LOG.error("Error during auto build.", exc);
        }
      } else {
        throw Exceptions.sneakyThrow(_t);
      }
    } finally {
      buildProgressReporter.clearProgress();
      this.cancelIndicators.remove(cancelIndicator);
    }
  }
  
  protected LinkedHashSet<BuildEvent> getEventsForModule(final List<BuildEvent> events, final Module module) {
    final ModuleRootManager moduleRootManager = ModuleRootManager.getInstance(module);
    final String[] excludeRootUrls = moduleRootManager.getExcludeRootUrls();
    final String[] sourceRootUrls = moduleRootManager.getSourceRootUrls();
    final LinkedHashSet<BuildEvent> result = new LinkedHashSet<BuildEvent>();
    for (final BuildEvent event : events) {
      boolean _isEmpty = event.getURIs().isEmpty();
      boolean _not = (!_isEmpty);
      if (_not) {
        final String url = IterableExtensions.<URI>head(event.getURIs()).toString();
        if ((IterableExtensions.<String>forall(((Iterable<String>)Conversions.doWrapArray(excludeRootUrls)), ((Function1<String, Boolean>) (String it) -> {
          boolean _isUrlUnderRoot = this.isUrlUnderRoot(url, it);
          return Boolean.valueOf((!_isUrlUnderRoot));
        })) && IterableExtensions.<String>exists(((Iterable<String>)Conversions.doWrapArray(sourceRootUrls)), ((Function1<String, Boolean>) (String it) -> {
          return Boolean.valueOf(this.isUrlUnderRoot(url, it));
        })))) {
          result.add(event);
        }
      }
    }
    return result;
  }
  
  private final static char SEGMENT_SEPARATOR = '/';
  
  protected boolean isUrlUnderRoot(final String url, final String rootUrl) {
    return (((url.length() > rootUrl.length()) && (url.charAt(rootUrl.length()) == XtextAutoBuilderComponent.SEGMENT_SEPARATOR)) && 
      FileUtil.startsWith(url, rootUrl));
  }
  
  public Function1<URI, IResourceServiceProvider> getServiceProviderProvider(final Module module) {
    final Function1<URI, IResourceServiceProvider> _function = (URI it) -> {
      final IResourceServiceProvider serviceProvider = this.resourceServiceProviderRegistry.getResourceServiceProvider(it);
      if ((serviceProvider != null)) {
        final FacetProvider facetProvider = serviceProvider.<FacetProvider>get(FacetProvider.class);
        if ((facetProvider != null)) {
          final Facet<? extends AbstractFacetConfiguration> facet = facetProvider.getFacet(module);
          if ((facet != null)) {
            return serviceProvider;
          }
        }
      }
      return null;
    };
    return _function;
  }
  
  public XtextResourceSet createResourceSet(final Module module, final ResourceDescriptionsData newData) {
    final XtextResourceSet result = this.resourceSetProvider.get(module);
    final ChunkedResourceDescriptions fullIndex = ChunkedResourceDescriptions.findInEmfObject(result);
    fullIndex.setContainer(module.getName(), newData);
    return result;
  }
  
  public String getContainerHandle(final Module module) {
    return module.getName();
  }
  
  protected void collectChanges(final Set<BuildEvent> events, final Module module, final HashSet<URI> changedUris, final HashSet<URI> deletedUris, final ArrayList<IResourceDescription.Delta> deltas) {
    final Application app = ApplicationManager.getApplication();
    final Source2GeneratedMapping fileMappings = this.moduleName2GeneratedMapping.get(module.getName());
    for (final BuildEvent event : events) {
      BuildEvent.Type _type = event.getType();
      if (_type != null) {
        switch (_type) {
          case MODIFIED:
          case ADDED:
            Set<URI> _uRIs = event.getURIs();
            for (final URI uri : _uRIs) {
              {
                List<URI> _source = null;
                if (fileMappings!=null) {
                  _source=fileMappings.getSource(uri);
                }
                final List<URI> sourceUris = _source;
                if (((sourceUris != null) && (!sourceUris.isEmpty()))) {
                  for (final URI sourceUri : sourceUris) {
                    this.consistentAdd(sourceUri, changedUris, deletedUris);
                  }
                } else {
                  boolean _isJavaFile = this.isJavaFile(uri);
                  if (_isJavaFile) {
                    final Computable<Set<IResourceDescription.Delta>> _function = () -> {
                      return this.getJavaDeltas(event.getFile(uri), module);
                    };
                    Set<IResourceDescription.Delta> _runReadAction = app.<Set<IResourceDescription.Delta>>runReadAction(_function);
                    Iterables.<IResourceDescription.Delta>addAll(deltas, _runReadAction);
                  } else {
                    this.consistentAdd(uri, changedUris, deletedUris);
                  }
                }
              }
            }
            break;
          case DELETED:
            Set<URI> _uRIs_1 = event.getURIs();
            for (final URI uri_1 : _uRIs_1) {
              {
                List<URI> _source = null;
                if (fileMappings!=null) {
                  _source=fileMappings.getSource(uri_1);
                }
                final List<URI> sourceUris = _source;
                if (((sourceUris != null) && (!sourceUris.isEmpty()))) {
                  for (final URI sourceUri : sourceUris) {
                    boolean _contains = deletedUris.contains(sourceUri);
                    boolean _not = (!_contains);
                    if (_not) {
                      changedUris.add(sourceUri);
                    }
                  }
                } else {
                  boolean _isJavaFile = this.isJavaFile(uri_1);
                  if (_isJavaFile) {
                    final Computable<Set<IResourceDescription.Delta>> _function = () -> {
                      return this.getJavaDeltas(event.getFile(uri_1), module);
                    };
                    Set<IResourceDescription.Delta> _runReadAction = app.<Set<IResourceDescription.Delta>>runReadAction(_function);
                    Iterables.<IResourceDescription.Delta>addAll(deltas, _runReadAction);
                  } else {
                    this.consistentAdd(uri_1, deletedUris, changedUris);
                  }
                }
              }
            }
            break;
          default:
            break;
        }
      }
    }
  }
  
  protected void consistentAdd(final URI uri, final Set<URI> toBeAdded, final Set<URI> toBeRemoved) {
    toBeAdded.add(uri);
    toBeRemoved.remove(uri);
  }
  
  public boolean isJavaFile(final URI file) {
    String _fileExtension = file.fileExtension();
    return Objects.equal(_fileExtension, "java");
  }
  
  public Set<IResourceDescription.Delta> getJavaDeltas(final VirtualFile file, final Module module) {
    boolean _isValid = file.isValid();
    boolean _not = (!_isValid);
    if (_not) {
      return CollectionLiterals.<IResourceDescription.Delta>emptySet();
    }
    final PsiFile psiFile = PsiManager.getInstance(module.getProject()).findFile(file);
    final LinkedHashSet<IResourceDescription.Delta> result = CollectionLiterals.<IResourceDescription.Delta>newLinkedHashSet();
    if ((psiFile instanceof PsiJavaFile)) {
      PsiClass[] _classes = ((PsiJavaFile)psiFile).getClasses();
      for (final PsiClass clazz : _classes) {
        QualifiedName _qualifiedName = this.qualifiedNameConverter.toQualifiedName(clazz.getQualifiedName());
        TypeResourceDescription.ChangedDelta _changedDelta = new TypeResourceDescription.ChangedDelta(_qualifiedName);
        result.add(_changedDelta);
      }
    }
    return result;
  }
  
  public ChunkedResourceDescriptions installCopyOfResourceDescriptions(final ResourceSet resourceSet) {
    return this.chunkedResourceDescriptions.createShallowCopyWith(resourceSet);
  }
  
  protected Module findModule(final BuildEvent it, final ProjectFileIndex fileIndex) {
    Module _xblockexpression = null;
    {
      final VirtualFile file = IterableExtensions.<VirtualFile>head(it.getFilesByURI().values());
      Module _xifexpression = null;
      BuildEvent.Type _type = it.getType();
      boolean _equals = Objects.equal(_type, BuildEvent.Type.DELETED);
      if (_equals) {
        _xifexpression = this.findModule(file, fileIndex);
      } else {
        _xifexpression = fileIndex.getModuleForFile(file, true);
      }
      _xblockexpression = _xifexpression;
    }
    return _xblockexpression;
  }
  
  protected Module findModule(final VirtualFile file, final ProjectFileIndex fileIndex) {
    if ((file == null)) {
      return null;
    }
    final Module module = fileIndex.getModuleForFile(file, true);
    if ((module != null)) {
      return module;
    }
    return this.findModule(file.getParent(), fileIndex);
  }
  
  @Override
  public String getComponentName() {
    return "Xtext Compiler Component";
  }
  
  public ChunkedResourceDescriptions getIndexState() {
    return this.chunkedResourceDescriptions;
  }
  
  @Inject
  private XtextAutoBuilderComponentState.Codec codec;
  
  @Override
  public XtextAutoBuilderComponentState getState() {
    return this.codec.encode(this.resourceServiceProviderRegistry, this.chunkedResourceDescriptions, this.moduleName2GeneratedMapping);
  }
  
  @Override
  public void loadState(final XtextAutoBuilderComponentState state) {
    try {
      final Set<String> installedNow = this.resourceServiceProviderRegistry.getExtensionToFactoryMap().keySet();
      final Set<String> installedLastTime = this.codec.decodeInstalledLanguages(state);
      boolean _equals = installedNow.equals(installedLastTime);
      boolean _not = (!_equals);
      if (_not) {
        XtextAutoBuilderComponent.LOG.info("Different Xtext plugins than last time. Reindexing project.");
        this.chunkedResourceDescriptions = this.chunkedResourceDescriptionsProvider.get();
        this.moduleName2GeneratedMapping.clear();
        this.doCleanBuild();
      } else {
        boolean _isDebugEnabled = XtextAutoBuilderComponent.LOG.isDebugEnabled();
        if (_isDebugEnabled) {
          XtextAutoBuilderComponent.LOG.debug("Loading persisted index state.");
        }
        this.chunkedResourceDescriptions = this.codec.decodeIndex(state);
        this.moduleName2GeneratedMapping = this.codec.decodeModuleToGenerated(state);
      }
    } catch (final Throwable _t) {
      if (_t instanceof Exception) {
        final Exception exc = (Exception)_t;
        XtextAutoBuilderComponent.LOG.error("Error loading XtextAutoBuildComponentState ", exc);
        this.chunkedResourceDescriptions = this.chunkedResourceDescriptionsProvider.get();
        this.moduleName2GeneratedMapping.clear();
        this.doCleanBuild();
      } else {
        throw Exceptions.sneakyThrow(_t);
      }
    }
  }
  
  private final static Logger LOG = Logger.getLogger(XtextAutoBuilderComponent.class);
}
