package org.eclipse.xtext.parser.antlr;

import com.google.common.io.CharStreams;
import com.google.inject.Injector;
import java.io.InputStream;
import java.io.InputStreamReader;
import org.eclipse.emf.common.util.URI;
import org.eclipse.xtend.lib.annotations.FinalFieldsConstructor;
import org.eclipse.xtext.ISetup;
import org.eclipse.xtext.idea.tests.TestDecorator;
import org.eclipse.xtext.idea.tests.parsing.AbstractLanguageParsingTestCase;
import org.eclipse.xtext.idea.tests.parsing.ModelChecker;
import org.eclipse.xtext.idea.tests.parsing.NodeModelPrinter;
import org.eclipse.xtext.parser.antlr.Bug378967Test;
import org.eclipse.xtext.parser.antlr.idea.Bug378967TestLanguageStandaloneSetupIdea;
import org.eclipse.xtext.parser.antlr.idea.lang.Bug378967TestLanguageFileType;
import org.eclipse.xtext.parser.antlr.idea.lang.Bug378967TestLanguageLanguage;
import org.eclipse.xtext.resource.XtextResource;

@TestDecorator
@SuppressWarnings("all")
public class IdeaBug378967Test extends AbstractLanguageParsingTestCase {
  @FinalFieldsConstructor
  private static class Delegate extends Bug378967Test {
    private final ModelChecker modelChecker;
    
    @Override
    public void setUp() throws Exception {
      super.setUp();
      this.setInjector(Bug378967TestLanguageLanguage.INSTANCE.<Injector>getInstance(Injector.class));
    }
    
    @Override
    protected XtextResource doGetResource(final InputStream in, final URI uri) throws Exception {
      InputStreamReader _inputStreamReader = new InputStreamReader(in);
      return this.modelChecker.checkResource(CharStreams.toString(_inputStreamReader), false);
    }
    
    @Override
    protected boolean shouldTestSerializer(final XtextResource resource) {
      return false;
    }
    
    public Delegate(final ModelChecker modelChecker) {
      super();
      this.modelChecker = modelChecker;
    }
  }
  
  private IdeaBug378967Test.Delegate delegate;
  
  public IdeaBug378967Test() {
    super(Bug378967TestLanguageFileType.INSTANCE);
    IdeaBug378967Test.Delegate _delegate = new IdeaBug378967Test.Delegate(this);
    this.delegate = _delegate;
  }
  
  @Override
  protected ISetup getSetup() {
    return new Bug378967TestLanguageStandaloneSetupIdea();
  }
  
  @Override
  protected String getTestDataPath() {
    return "./testData/parsing/antlr/bug378967";
  }
  
  @Override
  protected void setUp() throws Exception {
    super.setUp();
    NodeModelPrinter _nodeModelPrinter = this.getXtextResourceAsserts().getNodeModelPrinter();
    _nodeModelPrinter.setIgnoreSyntaxErrors(true);
    this.delegate.setUp();
  }
  
  public void test1() throws Exception {
    delegate.test1();
  }
  
  public void test2() throws Exception {
    delegate.test2();
  }
  
  public void test3() throws Exception {
    delegate.test3();
  }
  
  public void test4() throws Exception {
    delegate.test4();
  }
}
