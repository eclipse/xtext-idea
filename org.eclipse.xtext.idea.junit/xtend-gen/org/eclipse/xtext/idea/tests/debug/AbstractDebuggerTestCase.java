package org.eclipse.xtext.idea.tests.debug;

import com.google.common.base.Objects;
import com.intellij.debugger.DebuggerManagerEx;
import com.intellij.debugger.DebuggerTestCase;
import com.intellij.debugger.DefaultDebugEnvironment;
import com.intellij.debugger.SourcePosition;
import com.intellij.debugger.engine.DebugProcess;
import com.intellij.debugger.engine.DebugProcessImpl;
import com.intellij.debugger.engine.JavaDebugProcess;
import com.intellij.debugger.engine.SuspendContextImpl;
import com.intellij.debugger.engine.SuspendManager;
import com.intellij.debugger.impl.DebuggerManagerImpl;
import com.intellij.debugger.impl.DebuggerSession;
import com.intellij.debugger.impl.GenericDebuggerRunnerSettings;
import com.intellij.debugger.impl.OutputChecker;
import com.intellij.debugger.settings.DebuggerSettings;
import com.intellij.debugger.ui.breakpoints.BreakpointManager;
import com.intellij.debugger.ui.breakpoints.LineBreakpoint;
import com.intellij.execution.ExecutionException;
import com.intellij.execution.Executor;
import com.intellij.execution.configurations.CommandLineBuilder;
import com.intellij.execution.configurations.GeneralCommandLine;
import com.intellij.execution.configurations.JavaCommandLineState;
import com.intellij.execution.configurations.JavaParameters;
import com.intellij.execution.configurations.RemoteConnection;
import com.intellij.execution.configurations.RunProfileState;
import com.intellij.execution.executors.DefaultDebugExecutor;
import com.intellij.execution.process.ProcessAdapter;
import com.intellij.execution.process.ProcessEvent;
import com.intellij.execution.process.ProcessHandler;
import com.intellij.execution.runners.ExecutionEnvironment;
import com.intellij.execution.runners.ExecutionEnvironmentBuilder;
import com.intellij.openapi.Disposable;
import com.intellij.openapi.application.PathManager;
import com.intellij.openapi.editor.Document;
import com.intellij.openapi.util.Key;
import com.intellij.openapi.vfs.VirtualFile;
import com.intellij.psi.PsiDocumentManager;
import com.intellij.psi.PsiFile;
import com.intellij.psi.PsiManager;
import com.intellij.util.ArrayUtil;
import com.intellij.util.ui.UIUtil;
import com.intellij.xdebugger.XDebugProcess;
import com.intellij.xdebugger.XDebugProcessStarter;
import com.intellij.xdebugger.XDebugSession;
import com.intellij.xdebugger.XDebuggerManager;
import com.sun.tools.javac.Main;
import java.io.File;
import java.lang.reflect.InvocationTargetException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import junit.framework.AssertionFailedError;
import junit.framework.TestCase;
import org.eclipse.xtend.lib.annotations.Accessors;
import org.eclipse.xtend.lib.macro.Active;
import org.eclipse.xtend2.lib.StringConcatenation;
import org.eclipse.xtext.idea.tests.AbstractIdeaTestCase;
import org.eclipse.xtext.xbase.lib.CollectionLiterals;
import org.eclipse.xtext.xbase.lib.Conversions;
import org.eclipse.xtext.xbase.lib.Exceptions;
import org.eclipse.xtext.xbase.lib.ExclusiveRange;
import org.eclipse.xtext.xbase.lib.Functions.Function1;
import org.eclipse.xtext.xbase.lib.InputOutput;
import org.eclipse.xtext.xbase.lib.IterableExtensions;
import org.eclipse.xtext.xbase.lib.ObjectExtensions;
import org.eclipse.xtext.xbase.lib.Procedures.Procedure1;
import org.jetbrains.annotations.NotNull;

@SuppressWarnings("all")
public abstract class AbstractDebuggerTestCase extends AbstractIdeaTestCase {
  private DebuggerSession myDebuggerSession;
  
  private DebugProcessImpl myDebugProcess;
  
  private final static int timeout = 10_000;
  
  @Override
  protected void tearDown() throws Exception {
    try {
      if ((this.myDebugProcess != null)) {
        this.myDebugProcess.stop(true);
        this.myDebugProcess.waitFor();
      }
    } finally {
      super.tearDown();
    }
  }
  
  protected void assertCurrentLine(final VirtualFile file, final String fragment) {
    final SourcePosition sp = this.myDebuggerSession.getContextManager().getContext().getSourcePosition();
    TestCase.assertEquals(file, sp.getFile().getVirtualFile());
    final Document doc = PsiDocumentManager.getInstance(this.getProject()).getDocument(sp.getFile());
    final int index = doc.getText().indexOf(fragment);
    if ((index == (-1))) {
      String _name = sp.getFile().getVirtualFile().getName();
      String _plus = ((("couldn\'t find \'" + fragment) + "\' in file ") + _name);
      TestCase.fail(_plus);
    }
    TestCase.assertEquals(doc.getLineNumber(index), sp.getLine());
  }
  
  protected void assertCurrentLine(final VirtualFile file, final int line) {
    final SourcePosition sp = this.myDebuggerSession.getContextManager().getContext().getSourcePosition();
    TestCase.assertEquals(file, sp.getFile().getVirtualFile());
    TestCase.assertEquals(line, sp.getLine());
  }
  
  protected void assertProcessTerminated() {
    TestCase.assertTrue(this.myDebugProcess.getProcessHandler().isProcessTerminated());
  }
  
  protected LineBreakpoint<?> addLineBreakpoint(final VirtualFile file, final int line) {
    final BreakpointManager breakpointManager = DebuggerManagerImpl.getInstanceEx(this.myProject).getBreakpointManager();
    final PsiFile psiFile = PsiManager.getInstance(this.myProject).findFile(file);
    final Document document = PsiDocumentManager.getInstance(this.myProject).getDocument(psiFile);
    return breakpointManager.addLineBreakpoint(document, line);
  }
  
  protected void stepOver(final int times) {
    ExclusiveRange _doubleDotLessThan = new ExclusiveRange(0, times, true);
    for (final Integer i : _doubleDotLessThan) {
      try {
        this.stepOver();
      } catch (final Throwable _t) {
        if (_t instanceof AssertionFailedError) {
          final AssertionFailedError e = (AssertionFailedError)_t;
          String _message = e.getMessage();
          String _plus = ((("Failed on step " + i) + " : ") + _message);
          TestCase.fail(_plus);
        } else {
          throw Exceptions.sneakyThrow(_t);
        }
      }
    }
  }
  
  protected SuspendContextImpl stepOver() {
    final Runnable _function = () -> {
      this.myDebuggerSession.stepOver(false);
    };
    return this.waitForContextChange(_function);
  }
  
  protected SuspendContextImpl stepInto() {
    final Runnable _function = () -> {
      this.myDebuggerSession.stepInto(false, null);
    };
    return this.waitForContextChange(_function);
  }
  
  protected SuspendContextImpl stepOut() {
    final Runnable _function = () -> {
      this.myDebuggerSession.stepOut();
    };
    return this.waitForContextChange(_function);
  }
  
  protected SuspendContextImpl resume() {
    final Runnable _function = () -> {
      this.myDebuggerSession.resume();
    };
    return this.waitForContextChange(_function);
  }
  
  private SuspendContextImpl waitForContextChange(final Runnable command) {
    try {
      TestCase.assertFalse(this.myDebugProcess.getProcessHandler().isProcessTerminated());
      int i = 0;
      final SuspendManager suspendManager = this.myDebugProcess.getSuspendManager();
      final SourcePosition oldSourcePosition = this.myDebugProcess.getSession().getContextManager().getContext().getSourcePosition();
      command.run();
      while (((i++ < (AbstractDebuggerTestCase.timeout / 10)) && (Objects.equal(oldSourcePosition, this.myDebugProcess.getSession().getContextManager().getContext().getSourcePosition()) || 
        (this.myDebugProcess.getSession().getContextManager().getContext().getSourcePosition() == null)))) {
        {
          Thread.sleep(10);
          UIUtil.dispatchAllInvocationEvents();
        }
      }
      SourcePosition _sourcePosition = this.myDebugProcess.getSession().getContextManager().getContext().getSourcePosition();
      boolean _equals = Objects.equal(oldSourcePosition, _sourcePosition);
      if (_equals) {
        StringConcatenation _builder = new StringConcatenation();
        _builder.append("too long process, terminated=");
        boolean _isProcessTerminated = this.myDebugProcess.getProcessHandler().isProcessTerminated();
        _builder.append(_isProcessTerminated);
        TestCase.fail(_builder.toString());
      }
      return suspendManager.getPausedContext();
    } catch (Throwable _e) {
      throw Exceptions.sneakyThrow(_e);
    }
  }
  
  protected void compile() {
    final List<String> args = new ArrayList<String>();
    args.add("-g");
    args.add("-d");
    final String modulePath = this.getModule().getModuleFile().getParent().getPath();
    final File classesDir = new File(modulePath, "classes");
    classesDir.mkdir();
    args.add(classesDir.getPath());
    final Class<TestCase> testCaseClass = TestCase.class;
    StringConcatenation _builder = new StringConcatenation();
    _builder.append("/");
    String _replace = testCaseClass.getName().replace(Character.valueOf('.').charValue(), Character.valueOf('/').charValue());
    _builder.append(_replace);
    _builder.append(".class");
    final String junitLibRoot = PathManager.getResourceRoot(testCaseClass, _builder.toString());
    if ((junitLibRoot != null)) {
      args.add("-cp");
      String _jarPathForClass = PathManager.getJarPathForClass(Objects.class);
      String _jarPathForClass_1 = PathManager.getJarPathForClass(IterableExtensions.class);
      String _jarPathForClass_2 = PathManager.getJarPathForClass(Accessors.class);
      String _jarPathForClass_3 = PathManager.getJarPathForClass(Active.class);
      args.add(
        IterableExtensions.join(Collections.<String>unmodifiableList(CollectionLiterals.<String>newArrayList(junitLibRoot, _jarPathForClass, _jarPathForClass_1, _jarPathForClass_2, _jarPathForClass_3)), File.pathSeparator));
    }
    final int before = args.size();
    final Function1<File, Boolean> _function = (File it) -> {
      return Boolean.valueOf(it.getName().endsWith(".java"));
    };
    Iterable<File> _filter = IterableExtensions.<File>filter(((Iterable<File>)Conversions.doWrapArray(new File(modulePath, "src").listFiles())), _function);
    for (final File file : _filter) {
      args.add(file.getPath());
    }
    final Function1<File, Boolean> _function_1 = (File it) -> {
      return Boolean.valueOf(it.getName().endsWith(".java"));
    };
    Iterable<File> _filter_1 = IterableExtensions.<File>filter(((Iterable<File>)Conversions.doWrapArray(new File(modulePath, "xtend-gen").listFiles())), _function_1);
    for (final File file_1 : _filter_1) {
      args.add(file_1.getPath());
    }
    int _size = args.size();
    boolean _lessThan = (before < _size);
    TestCase.assertTrue("No Java files!", _lessThan);
    Main.compile(ArrayUtil.toStringArray(args));
  }
  
  protected void startDebugProcess(final String className) throws ExecutionException, InterruptedException, InvocationTargetException {
    TestCase.assertTrue((this.myDebugProcess == null));
    JavaParameters _javaParameters = new JavaParameters();
    final Procedure1<JavaParameters> _function = (JavaParameters it) -> {
      try {
        it.setMainClass(className);
        it.configureByModule(this.getModule(), JavaParameters.JDK_AND_CLASSES, this.getTestProjectJdk());
        final String modulePath = this.getModule().getModuleFile().getParent().getPath();
        final File classesDir = new File(modulePath, "classes");
        it.getClassPath().add(classesDir);
      } catch (Throwable _e) {
        throw Exceptions.sneakyThrow(_e);
      }
    };
    JavaParameters _doubleArrow = ObjectExtensions.<JavaParameters>operator_doubleArrow(_javaParameters, _function);
    this.myDebuggerSession = this.createLocalProcess(DebuggerSettings.SOCKET_TRANSPORT, _doubleArrow);
    this.myDebugProcess = this.myDebuggerSession.getProcess();
    final Disposable _function_1 = () -> {
      this.myDebugProcess.dispose();
    };
    this.<Disposable>disposeOnTearDown(_function_1);
    final Runnable _function_2 = () -> {
      try {
        this.myDebuggerSession.getProcess().getProcessHandler().startNotify();
        while ((!this.myDebuggerSession.isAttached())) {
          {
            UIUtil.dispatchAllInvocationEvents();
            Thread.sleep(10);
          }
        }
      } catch (Throwable _e) {
        throw Exceptions.sneakyThrow(_e);
      }
    };
    this.waitForContextChange(_function_2);
  }
  
  private DebuggerSession createLocalProcess(final int transport, final JavaParameters myJavaParameters) throws ExecutionException, InterruptedException, InvocationTargetException {
    abstract class __AbstractDebuggerTestCase_1 extends DebuggerTestCase {
      public abstract DebuggerTestCase.MockConfiguration createMockConf();
    }
    
    final DebuggerSession[] debuggerSession = { null };
    DebuggerSettings _instance = DebuggerSettings.getInstance();
    _instance.DEBUGGER_TRANSPORT = transport;
    GenericDebuggerRunnerSettings debuggerRunnerSettings = new GenericDebuggerRunnerSettings();
    debuggerRunnerSettings.LOCAL = true;
    debuggerRunnerSettings.setDebugPort("3456");
    final DebuggerTestCase.MockConfiguration profile = new __AbstractDebuggerTestCase_1() {
      @Override
      protected String getTestAppPath() {
        return null;
      }
      
      @Override
      protected OutputChecker initOutputChecker() {
        return null;
      }
      
      public DebuggerTestCase.MockConfiguration createMockConf() {
        return new DebuggerTestCase.MockConfiguration();
      }
    }.createMockConf();
    Executor _debugExecutorInstance = DefaultDebugExecutor.getDebugExecutorInstance();
    ExecutionEnvironment environment = new ExecutionEnvironmentBuilder(this.myProject, _debugExecutorInstance).runnerSettings(debuggerRunnerSettings).runProfile(profile).build();
    final JavaCommandLineState javaCommandLineState = new JavaCommandLineState(environment) {
      @Override
      protected JavaParameters createJavaParameters() {
        return myJavaParameters;
      }
      
      @Override
      protected GeneralCommandLine createCommandLine() throws ExecutionException {
        return CommandLineBuilder.createFromJavaParameters(this.getJavaParameters());
      }
    };
    final RemoteConnection debugParameters = DebuggerManagerImpl.createDebugParameters(
      javaCommandLineState.getJavaParameters(), debuggerRunnerSettings, true);
    final Runnable _function = () -> {
      try {
        debuggerSession[0] = 
          this.attachVirtualMachine(javaCommandLineState, javaCommandLineState.getEnvironment(), debugParameters, 
            false);
      } catch (final Throwable _t) {
        if (_t instanceof ExecutionException) {
          final ExecutionException e = (ExecutionException)_t;
          TestCase.fail(e.getMessage());
        } else {
          throw Exceptions.sneakyThrow(_t);
        }
      }
    };
    this.invokeAndWaitIfNeeded(_function);
    final ProcessHandler processHandler = debuggerSession[0].getProcess().getProcessHandler();
    DebugProcessImpl _process = debuggerSession[0].getProcess();
    _process.addProcessListener(new ProcessAdapter() {
      @Override
      public void onTextAvailable(final ProcessEvent event, final Key outputType) {
        String _text = event.getText();
        String _plus = (_text + " - ");
        String _plus_1 = (_plus + outputType);
        InputOutput.<String>println(_plus_1);
      }
    });
    DebugProcess _debugProcess = DebuggerManagerEx.getInstanceEx(this.myProject).getDebugProcess(processHandler);
    DebugProcessImpl process = ((DebugProcessImpl) _debugProcess);
    TestCase.assertNotNull(process);
    return debuggerSession[0];
  }
  
  private void invokeAndWaitIfNeeded(final Runnable run) {
    UIUtil.invokeAndWaitIfNeeded(run);
  }
  
  private DebuggerSession attachVirtualMachine(final RunProfileState state, final ExecutionEnvironment environment, final RemoteConnection remoteConnection, final boolean pollConnection) throws ExecutionException {
    DebuggerManagerEx _instanceEx = DebuggerManagerEx.getInstanceEx(this.myProject);
    DefaultDebugEnvironment _defaultDebugEnvironment = new DefaultDebugEnvironment(environment, state, remoteConnection, pollConnection);
    final DebuggerSession debuggerSession = _instanceEx.attachVirtualMachine(_defaultDebugEnvironment);
    XDebuggerManager _instance = XDebuggerManager.getInstance(this.myProject);
    _instance.startSession(environment, new XDebugProcessStarter() {
      @NotNull
      @Override
      public XDebugProcess start(@NotNull final XDebugSession session) {
        return JavaDebugProcess.create(session, debuggerSession);
      }
    });
    return debuggerSession;
  }
}
