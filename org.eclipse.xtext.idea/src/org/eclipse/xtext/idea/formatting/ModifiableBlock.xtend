/*******************************************************************************
 * Copyright (c) 2015 itemis AG (http://www.itemis.eu) and others.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 *******************************************************************************/
package org.eclipse.xtext.idea.formatting

import com.intellij.formatting.Block
import com.intellij.formatting.Indent
import com.intellij.formatting.SpacingBuilder

/**
 * @author kosyakov - Initial contribution and API
 */
interface ModifiableBlock extends Block {
	def void setIndent(Indent indent)
	
	def void setIncomplete(Boolean incomplete)

	def SpacingBuilder getSpacingBuilder()

	def void setSpacingBuilder(SpacingBuilder spacingBuilder)

	def Block getParentBlock()

	def void setParentBlock(Block parentBlock)
}