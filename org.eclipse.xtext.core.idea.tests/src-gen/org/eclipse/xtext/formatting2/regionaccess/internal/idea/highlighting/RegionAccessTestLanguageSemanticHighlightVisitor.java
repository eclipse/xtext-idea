package org.eclipse.xtext.formatting2.regionaccess.internal.idea.highlighting;

import org.eclipse.xtext.formatting2.regionaccess.internal.idea.lang.RegionAccessTestLanguageLanguage;
import org.eclipse.xtext.idea.highlighting.SemanticHighlightVisitor;

public class RegionAccessTestLanguageSemanticHighlightVisitor extends SemanticHighlightVisitor {
	public RegionAccessTestLanguageSemanticHighlightVisitor() {
		RegionAccessTestLanguageLanguage.INSTANCE.injectMembers(this);
	}
}
