/*******************************************************************************
 * Copyright (c) 2015 itemis AG (http://www.itemis.eu) and others.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 *******************************************************************************/
package org.eclipse.xtext.idea.build

import com.intellij.codeInsight.daemon.DaemonCodeAnalyzer
import com.intellij.compiler.CompilerMessageImpl
import com.intellij.compiler.ProblemsView
import com.intellij.openapi.application.ApplicationManager
import com.intellij.openapi.compiler.CompileScope
import com.intellij.openapi.compiler.CompilerMessage
import com.intellij.openapi.compiler.CompilerMessageCategory
import com.intellij.openapi.fileEditor.OpenFileDescriptor
import com.intellij.openapi.fileTypes.FileType
import com.intellij.openapi.project.Project
import com.intellij.openapi.util.Key
import com.intellij.problems.WolfTheProblemSolver
import java.util.List
import java.util.UUID
import org.eclipse.emf.common.util.URI
import org.eclipse.xtend.lib.annotations.Accessors
import org.eclipse.xtext.build.BuildRequest
import org.eclipse.xtext.validation.Issue

import static extension org.eclipse.xtext.idea.resource.VirtualFileURIUtil.*

/**
 * @author kosyakov - Initial contribution and API
 */
class BuildProgressReporter implements BuildRequest.IPostValidationCallback {

	val sessionId = UUID.randomUUID

	val affectedScope = new AffectedScope

	@Accessors(PUBLIC_SETTER)
	Project project

	@Accessors(PUBLIC_SETTER)
	List<BuildEvent> events

	protected def getProblemsView() {
		ProblemsView.SERVICE.getInstance(project)
	}

	def void clearProgress() {
		if (unitTestMode || project.isDisposed)
			return;

		if(problemsView !== null) {
			problemsView.clearProgress
			problemsView.clearOldMessages(affectedScope, sessionId)
		}
	}
	
	def void rehighlight() {
		val filesToRehighlight = affectedScope.affectedFiles.filter[shouldRehighlight]
		if (filesToRehighlight.empty)
			return;
			
		val wolfTheProblemSolver = WolfTheProblemSolver.getInstance(project)
		for (fileToRehighlight : filesToRehighlight) {
			wolfTheProblemSolver.queue(fileToRehighlight.virtualFile)
		}
		DaemonCodeAnalyzer.getInstance(project).restart
	}

	protected def shouldRehighlight(URI fileURI) {
		events.filter [
			type == BuildEvent.Type.MODIFIED || type == BuildEvent.Type.DELETED
		].exists[URIs.contains(fileURI)] == false
	}

	def void markAsAffected(URI uri) {
		affectedScope.affectedFiles += uri
	}

	override afterValidate(URI validated, Iterable<Issue> issues) {
		markAsAffected(validated)
		for (issue : issues.filterNull) {
			reportIssue(validated, issue)
		}
		return true
	}

	protected def reportIssue(URI validated, Issue issue) {
		if (unitTestMode || project.isDisposed)
			return;
		val compilerMessage = getCompilerMessage(validated, issue)
		if(problemsView !== null)
			problemsView.addMessage(compilerMessage, sessionId)
	}

	protected def isUnitTestMode() {
		val application = ApplicationManager.application
		application === null || application.unitTestMode
	}

	protected def CompilerMessage getCompilerMessage(URI validated, Issue issue) {
		val file = validated.virtualFile
		return new CompilerMessageImpl(
			project,
			issue.category,
			issue.message,
			file,
			issue.lineNumber,
			issue.column,
			new OpenFileDescriptor(project, file, issue.offset)
		)
	}

	protected def getCategory(Issue issue) {
		switch issue.severity {
			case ERROR:
				CompilerMessageCategory.ERROR
			case WARNING:
				CompilerMessageCategory.WARNING
			case INFO:
				CompilerMessageCategory.INFORMATION
			default:
				CompilerMessageCategory.INFORMATION
		}
	}

}

class AffectedScope implements CompileScope {

	@Accessors
	val affectedFiles = <URI>newHashSet

	override belongs(String url) {
		val uri = URI.createURI(url)
		affectedFiles.contains(uri)
	}

	override getAffectedModules() {
		throw new UnsupportedOperationException("TODO: auto-generated method stub")
	}

	override getFiles(FileType fileType, boolean inSourceOnly) {
		throw new UnsupportedOperationException("TODO: auto-generated method stub")
	}

	override exportUserData() {
		throw new UnsupportedOperationException("TODO: auto-generated method stub")
	}

	override <T> getUserData(Key<T> key) {
		throw new UnsupportedOperationException("TODO: auto-generated method stub")
	}

	override <T> putUserData(Key<T> key, T value) {
		throw new UnsupportedOperationException("TODO: auto-generated method stub")
	}

}
