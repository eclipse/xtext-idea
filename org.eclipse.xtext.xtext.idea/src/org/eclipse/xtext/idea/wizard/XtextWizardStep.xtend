package org.eclipse.xtext.idea.wizard

import com.intellij.ide.util.projectWizard.ModuleWizardStep
import com.intellij.ide.util.projectWizard.WizardContext
import com.intellij.openapi.diagnostic.Logger
import com.intellij.openapi.options.ConfigurationException
import com.intellij.openapi.ui.ComboBox
import javax.swing.JCheckBox
import javax.swing.JPanel
import javax.swing.JTextField
import org.eclipse.xtext.idea.util.IdeaWidgetFactory
import org.eclipse.xtext.idea.util.PlatformUtil
import org.eclipse.xtext.xtext.wizard.BuildSystem
import org.eclipse.xtext.xtext.wizard.LanguageDescriptor.FileExtensions
import org.eclipse.xtext.xtext.wizard.ProjectLayout
import org.eclipse.xtext.xtext.wizard.SourceLayout
import org.eclipse.xtext.xtext.wizard.TestedProjectDescriptor

import static java.awt.GridBagConstraints.*
import static org.eclipse.xtext.xtext.wizard.BuildSystem.*

class XtextWizardStep extends ModuleWizardStep {
	static final Logger LOG = Logger.getInstance(XtextWizardStep.name)
	extension IdeaWidgetFactory = new IdeaWidgetFactory
	extension PlatformUtil = new PlatformUtil

	JPanel mainPanel

	JTextField nameField
	JTextField extensionField

	// JCheckBox eclipse
	JCheckBox web
	JCheckBox test

	ComboBox<Object> buildSystem
	ComboBox<Object> layout

	WizardContext context

	new(WizardContext context) {
		this.context = context
	}

	override getComponent() {
		try {
			if (mainPanel === null) {
				mainPanel = createMainPanel()
			}
			return mainPanel
		} catch (Exception exception) {
			LOG.error("Initialisation failed.", exception)
			return new JPanel
		}
	}

	def JPanel createMainPanel() {
		twoColumnPanel [ extension it |

			row [separator("Language")]
			row([label("Name:")], [expand(HORIZONTAL) nameField = textField('org.xtext.example.mydsl.MyDsl')])
			row([label("Extension:")], [extensionField = textField('mydsl')])
			row [label(" ")]

			row [separator("Facets")]
			// row [eclipse = checkBox("Eclipse Plugin")]
			row [web = checkBox("Web Integration")]
			row [test = checkBox("Testing Support")]
			row [label(" ")]

			row [separator("Project Settings")]
			row([label("Build System:")], [
				indentRight(400)
				buildSystem = if(isMavenInstalled()) comboBox(GRADLE, MAVEN) else comboBox(GRADLE)
			])
			row([label("Source Layout:")], [indentRight(400) layout = comboBox(SourceLayout.MAVEN, SourceLayout.PLAIN)])

			row [expand(VERTICAL) label("")]
		]
	}

	override updateDataModel() {
		val xtextBuilder = context.projectBuilder as XtextModuleBuilder
		val config = xtextBuilder.getWizardConfiguration()

		config.language.name = nameField.text
		config.language.fileExtensions = FileExtensions.fromString(extensionField.text)

		config.runtimeProject.enabled = true
		config.ideProject.enabled = web.selected
		// config.uiProject.enabled = eclipse.selected
		config.webProject.enabled = web.selected
		config.enabledProjects.filter(TestedProjectDescriptor).forEach[testProject.enabled = test.selected]

		config.preferredBuildSystem = buildSystem.selectedItem as BuildSystem
		config.sourceLayout = layout.selectedItem as SourceLayout
		config.projectLayout = ProjectLayout.HIERARCHICAL

	}

	override validate() throws ConfigurationException {
		val superCall = super.validate()
		// if(eclipse.isSelected && layout.selectedItem == SourceLayout.MAVEN) {
		// throw new ConfigurationException('''For "Eclipse Plugin" please select Source Layout: �SourceLayout.PLAIN�.''')
		// }
		return superCall
	}
}
