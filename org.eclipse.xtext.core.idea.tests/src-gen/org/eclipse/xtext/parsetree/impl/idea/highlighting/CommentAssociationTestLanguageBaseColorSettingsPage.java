package org.eclipse.xtext.parsetree.impl.idea.highlighting;

import org.eclipse.xtext.idea.highlighting.AbstractColorSettingsPage;
import org.eclipse.xtext.parsetree.impl.idea.lang.CommentAssociationTestLanguageLanguage;


public class CommentAssociationTestLanguageBaseColorSettingsPage extends AbstractColorSettingsPage {
	
	public CommentAssociationTestLanguageBaseColorSettingsPage() {
		CommentAssociationTestLanguageLanguage.INSTANCE.injectMembers(this);
	}

	@Override
	public String getDisplayName() {
		return CommentAssociationTestLanguageLanguage.INSTANCE.getDisplayName();
	}
}
