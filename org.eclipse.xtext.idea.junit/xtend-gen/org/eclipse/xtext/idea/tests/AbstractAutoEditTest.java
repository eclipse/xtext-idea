package org.eclipse.xtext.idea.tests;

import com.intellij.ide.ClipboardSynchronizer;
import com.intellij.openapi.actionSystem.IdeActions;
import com.intellij.openapi.editor.Document;
import com.intellij.openapi.fileTypes.LanguageFileType;
import com.intellij.openapi.util.TextRange;
import com.intellij.psi.PsiFile;
import java.awt.datatransfer.StringSelection;
import junit.framework.TestCase;
import org.eclipse.xtext.idea.tests.LightToolingTest;
import org.eclipse.xtext.idea.tests.LineDelimiters;

@SuppressWarnings("all")
public abstract class AbstractAutoEditTest extends LightToolingTest {
  public AbstractAutoEditTest(final LanguageFileType fileType) {
    super(fileType);
  }
  
  protected void assertAutoIndentOnNewLine(final CharSequence model, final CharSequence expectedModel) {
    this.configureByText(model.toString());
    this.newLine();
    this.assertState(expectedModel.toString());
  }
  
  protected void selectText(final int relativeToCurrentOffset, final int length) {
    final int offset = this.myFixture.getEditor().getCaretModel().getOffset();
    final int startOffset = (offset + relativeToCurrentOffset);
    final int endOffset = (startOffset + length);
    this.myFixture.getEditor().getSelectionModel().setSelection(startOffset, endOffset);
  }
  
  protected void pasteText(final String text) {
    final StringSelection content = new StringSelection(text);
    ClipboardSynchronizer.getInstance().setContent(content, content);
    this.myFixture.performEditorAction(IdeActions.ACTION_EDITOR_PASTE);
  }
  
  @Override
  protected PsiFile configureByText(final String code) {
    return super.configureByText(code.replace("|", "<caret>"));
  }
  
  protected void assertState(final String editorState) {
    final String expectedState = LineDelimiters.toUnix(editorState);
    String _xblockexpression = null;
    {
      final int caretOffset = this.myFixture.getEditor().getCaretModel().getPrimaryCaret().getOffset();
      final Document document = this.myFixture.getEditor().getDocument();
      Document _document = this.myFixture.getEditor().getDocument();
      TextRange _textRange = new TextRange(0, caretOffset);
      final String beforeCaret = _document.getText(_textRange);
      Document _document_1 = this.myFixture.getEditor().getDocument();
      int _textLength = document.getTextLength();
      TextRange _textRange_1 = new TextRange(caretOffset, _textLength);
      final String afterCaret = _document_1.getText(_textRange_1);
      _xblockexpression = ((beforeCaret + "|") + afterCaret);
    }
    final String actualState = _xblockexpression;
    TestCase.assertEquals(this.dumpFormattingModel(), expectedState, actualState);
  }
  
  protected void newLine() {
    this.myFixture.type("\n");
  }
  
  protected void backspace() {
    this.myFixture.type("\b");
  }
}
