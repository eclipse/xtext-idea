package org.eclipse.xtext.idea.common.types.parser.antlr.internal;

import org.eclipse.xtext.*;
import org.eclipse.xtext.parser.*;
import org.eclipse.xtext.parser.impl.*;
import org.eclipse.emf.ecore.util.EcoreUtil;
import org.eclipse.emf.ecore.EObject;
import org.eclipse.xtext.parser.antlr.AbstractInternalAntlrParser;
import org.eclipse.xtext.parser.antlr.XtextTokenStream;
import org.eclipse.xtext.parser.antlr.XtextTokenStream.HiddenTokens;
import org.eclipse.xtext.parser.antlr.AntlrDatatypeRuleToken;
import org.eclipse.xtext.idea.common.types.services.RefactoringTestLanguageGrammarAccess;



import org.antlr.runtime.*;
import java.util.Stack;
import java.util.List;
import java.util.ArrayList;

/*******************************************************************************
 * Copyright (c) 2015 itemis AG (http://www.itemis.eu) and others.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 *******************************************************************************/
@SuppressWarnings("all")
public class InternalRefactoringTestLanguageParser extends AbstractInternalAntlrParser {
    public static final String[] tokenNames = new String[] {
        "<invalid>", "<EOR>", "<DOWN>", "<UP>", "RULE_ID", "RULE_INT", "RULE_STRING", "RULE_ML_COMMENT", "RULE_SL_COMMENT", "RULE_WS", "RULE_ANY_OTHER", "'.'", "'$'"
    };
    public static final int RULE_ID=4;
    public static final int RULE_WS=9;
    public static final int RULE_STRING=6;
    public static final int RULE_ANY_OTHER=10;
    public static final int RULE_SL_COMMENT=8;
    public static final int RULE_INT=5;
    public static final int T__11=11;
    public static final int RULE_ML_COMMENT=7;
    public static final int T__12=12;
    public static final int EOF=-1;

    // delegates
    // delegators


        public InternalRefactoringTestLanguageParser(TokenStream input) {
            this(input, new RecognizerSharedState());
        }
        public InternalRefactoringTestLanguageParser(TokenStream input, RecognizerSharedState state) {
            super(input, state);
             
        }
        

    public String[] getTokenNames() { return InternalRefactoringTestLanguageParser.tokenNames; }
    public String getGrammarFileName() { return "InternalRefactoringTestLanguage.g"; }



     	private RefactoringTestLanguageGrammarAccess grammarAccess;

        public InternalRefactoringTestLanguageParser(TokenStream input, RefactoringTestLanguageGrammarAccess grammarAccess) {
            this(input);
            this.grammarAccess = grammarAccess;
            registerRules(grammarAccess.getGrammar());
        }

        @Override
        protected String getFirstRuleName() {
        	return "Model";
       	}

       	@Override
       	protected RefactoringTestLanguageGrammarAccess getGrammarAccess() {
       		return grammarAccess;
       	}




    // $ANTLR start "entryRuleModel"
    // InternalRefactoringTestLanguage.g:68:1: entryRuleModel returns [EObject current=null] : iv_ruleModel= ruleModel EOF ;
    public final EObject entryRuleModel() throws RecognitionException {
        EObject current = null;

        EObject iv_ruleModel = null;


        try {
            // InternalRefactoringTestLanguage.g:68:46: (iv_ruleModel= ruleModel EOF )
            // InternalRefactoringTestLanguage.g:69:2: iv_ruleModel= ruleModel EOF
            {
             newCompositeNode(grammarAccess.getModelRule()); 
            pushFollow(FollowSets000.FOLLOW_1);
            iv_ruleModel=ruleModel();

            state._fsp--;

             current =iv_ruleModel; 
            match(input,EOF,FollowSets000.FOLLOW_2); 

            }

        }

            catch (RecognitionException re) {
                recover(input,re);
                appendSkippedTokens();
            }
        finally {
        }
        return current;
    }
    // $ANTLR end "entryRuleModel"


    // $ANTLR start "ruleModel"
    // InternalRefactoringTestLanguage.g:75:1: ruleModel returns [EObject current=null] : ( (lv_referenceHolder_0_0= ruleReferenceHolder ) )* ;
    public final EObject ruleModel() throws RecognitionException {
        EObject current = null;

        EObject lv_referenceHolder_0_0 = null;



        	enterRule();

        try {
            // InternalRefactoringTestLanguage.g:81:2: ( ( (lv_referenceHolder_0_0= ruleReferenceHolder ) )* )
            // InternalRefactoringTestLanguage.g:82:2: ( (lv_referenceHolder_0_0= ruleReferenceHolder ) )*
            {
            // InternalRefactoringTestLanguage.g:82:2: ( (lv_referenceHolder_0_0= ruleReferenceHolder ) )*
            loop1:
            do {
                int alt1=2;
                int LA1_0 = input.LA(1);

                if ( (LA1_0==RULE_ID) ) {
                    alt1=1;
                }


                switch (alt1) {
            	case 1 :
            	    // InternalRefactoringTestLanguage.g:83:3: (lv_referenceHolder_0_0= ruleReferenceHolder )
            	    {
            	    // InternalRefactoringTestLanguage.g:83:3: (lv_referenceHolder_0_0= ruleReferenceHolder )
            	    // InternalRefactoringTestLanguage.g:84:4: lv_referenceHolder_0_0= ruleReferenceHolder
            	    {

            	    				newCompositeNode(grammarAccess.getModelAccess().getReferenceHolderReferenceHolderParserRuleCall_0());
            	    			
            	    pushFollow(FollowSets000.FOLLOW_3);
            	    lv_referenceHolder_0_0=ruleReferenceHolder();

            	    state._fsp--;


            	    				if (current==null) {
            	    					current = createModelElementForParent(grammarAccess.getModelRule());
            	    				}
            	    				add(
            	    					current,
            	    					"referenceHolder",
            	    					lv_referenceHolder_0_0,
            	    					"org.eclipse.xtext.idea.common.types.RefactoringTestLanguage.ReferenceHolder");
            	    				afterParserOrEnumRuleCall();
            	    			

            	    }


            	    }
            	    break;

            	default :
            	    break loop1;
                }
            } while (true);


            }


            	leaveRule();

        }

            catch (RecognitionException re) {
                recover(input,re);
                appendSkippedTokens();
            }
        finally {
        }
        return current;
    }
    // $ANTLR end "ruleModel"


    // $ANTLR start "entryRuleReferenceHolder"
    // InternalRefactoringTestLanguage.g:104:1: entryRuleReferenceHolder returns [EObject current=null] : iv_ruleReferenceHolder= ruleReferenceHolder EOF ;
    public final EObject entryRuleReferenceHolder() throws RecognitionException {
        EObject current = null;

        EObject iv_ruleReferenceHolder = null;


        try {
            // InternalRefactoringTestLanguage.g:104:56: (iv_ruleReferenceHolder= ruleReferenceHolder EOF )
            // InternalRefactoringTestLanguage.g:105:2: iv_ruleReferenceHolder= ruleReferenceHolder EOF
            {
             newCompositeNode(grammarAccess.getReferenceHolderRule()); 
            pushFollow(FollowSets000.FOLLOW_1);
            iv_ruleReferenceHolder=ruleReferenceHolder();

            state._fsp--;

             current =iv_ruleReferenceHolder; 
            match(input,EOF,FollowSets000.FOLLOW_2); 

            }

        }

            catch (RecognitionException re) {
                recover(input,re);
                appendSkippedTokens();
            }
        finally {
        }
        return current;
    }
    // $ANTLR end "entryRuleReferenceHolder"


    // $ANTLR start "ruleReferenceHolder"
    // InternalRefactoringTestLanguage.g:111:1: ruleReferenceHolder returns [EObject current=null] : ( ( (lv_name_0_0= RULE_ID ) ) ( ( ruleFQN ) ) ) ;
    public final EObject ruleReferenceHolder() throws RecognitionException {
        EObject current = null;

        Token lv_name_0_0=null;


        	enterRule();

        try {
            // InternalRefactoringTestLanguage.g:117:2: ( ( ( (lv_name_0_0= RULE_ID ) ) ( ( ruleFQN ) ) ) )
            // InternalRefactoringTestLanguage.g:118:2: ( ( (lv_name_0_0= RULE_ID ) ) ( ( ruleFQN ) ) )
            {
            // InternalRefactoringTestLanguage.g:118:2: ( ( (lv_name_0_0= RULE_ID ) ) ( ( ruleFQN ) ) )
            // InternalRefactoringTestLanguage.g:119:3: ( (lv_name_0_0= RULE_ID ) ) ( ( ruleFQN ) )
            {
            // InternalRefactoringTestLanguage.g:119:3: ( (lv_name_0_0= RULE_ID ) )
            // InternalRefactoringTestLanguage.g:120:4: (lv_name_0_0= RULE_ID )
            {
            // InternalRefactoringTestLanguage.g:120:4: (lv_name_0_0= RULE_ID )
            // InternalRefactoringTestLanguage.g:121:5: lv_name_0_0= RULE_ID
            {
            lv_name_0_0=(Token)match(input,RULE_ID,FollowSets000.FOLLOW_4); 

            					newLeafNode(lv_name_0_0, grammarAccess.getReferenceHolderAccess().getNameIDTerminalRuleCall_0_0());
            				

            					if (current==null) {
            						current = createModelElement(grammarAccess.getReferenceHolderRule());
            					}
            					setWithLastConsumed(
            						current,
            						"name",
            						lv_name_0_0,
            						"org.eclipse.xtext.common.Terminals.ID");
            				

            }


            }

            // InternalRefactoringTestLanguage.g:137:3: ( ( ruleFQN ) )
            // InternalRefactoringTestLanguage.g:138:4: ( ruleFQN )
            {
            // InternalRefactoringTestLanguage.g:138:4: ( ruleFQN )
            // InternalRefactoringTestLanguage.g:139:5: ruleFQN
            {

            					if (current==null) {
            						current = createModelElement(grammarAccess.getReferenceHolderRule());
            					}
            				

            					newCompositeNode(grammarAccess.getReferenceHolderAccess().getDefaultReferenceJvmTypeCrossReference_1_0());
            				
            pushFollow(FollowSets000.FOLLOW_2);
            ruleFQN();

            state._fsp--;


            					afterParserOrEnumRuleCall();
            				

            }


            }


            }


            }


            	leaveRule();

        }

            catch (RecognitionException re) {
                recover(input,re);
                appendSkippedTokens();
            }
        finally {
        }
        return current;
    }
    // $ANTLR end "ruleReferenceHolder"


    // $ANTLR start "entryRuleFQN"
    // InternalRefactoringTestLanguage.g:157:1: entryRuleFQN returns [String current=null] : iv_ruleFQN= ruleFQN EOF ;
    public final String entryRuleFQN() throws RecognitionException {
        String current = null;

        AntlrDatatypeRuleToken iv_ruleFQN = null;


        try {
            // InternalRefactoringTestLanguage.g:157:43: (iv_ruleFQN= ruleFQN EOF )
            // InternalRefactoringTestLanguage.g:158:2: iv_ruleFQN= ruleFQN EOF
            {
             newCompositeNode(grammarAccess.getFQNRule()); 
            pushFollow(FollowSets000.FOLLOW_1);
            iv_ruleFQN=ruleFQN();

            state._fsp--;

             current =iv_ruleFQN.getText(); 
            match(input,EOF,FollowSets000.FOLLOW_2); 

            }

        }

            catch (RecognitionException re) {
                recover(input,re);
                appendSkippedTokens();
            }
        finally {
        }
        return current;
    }
    // $ANTLR end "entryRuleFQN"


    // $ANTLR start "ruleFQN"
    // InternalRefactoringTestLanguage.g:164:1: ruleFQN returns [AntlrDatatypeRuleToken current=new AntlrDatatypeRuleToken()] : (this_ID_0= RULE_ID (kw= '.' this_ID_2= RULE_ID )* (kw= '$' this_ID_4= RULE_ID )* ) ;
    public final AntlrDatatypeRuleToken ruleFQN() throws RecognitionException {
        AntlrDatatypeRuleToken current = new AntlrDatatypeRuleToken();

        Token this_ID_0=null;
        Token kw=null;
        Token this_ID_2=null;
        Token this_ID_4=null;


        	enterRule();

        try {
            // InternalRefactoringTestLanguage.g:170:2: ( (this_ID_0= RULE_ID (kw= '.' this_ID_2= RULE_ID )* (kw= '$' this_ID_4= RULE_ID )* ) )
            // InternalRefactoringTestLanguage.g:171:2: (this_ID_0= RULE_ID (kw= '.' this_ID_2= RULE_ID )* (kw= '$' this_ID_4= RULE_ID )* )
            {
            // InternalRefactoringTestLanguage.g:171:2: (this_ID_0= RULE_ID (kw= '.' this_ID_2= RULE_ID )* (kw= '$' this_ID_4= RULE_ID )* )
            // InternalRefactoringTestLanguage.g:172:3: this_ID_0= RULE_ID (kw= '.' this_ID_2= RULE_ID )* (kw= '$' this_ID_4= RULE_ID )*
            {
            this_ID_0=(Token)match(input,RULE_ID,FollowSets000.FOLLOW_5); 

            			current.merge(this_ID_0);
            		

            			newLeafNode(this_ID_0, grammarAccess.getFQNAccess().getIDTerminalRuleCall_0());
            		
            // InternalRefactoringTestLanguage.g:179:3: (kw= '.' this_ID_2= RULE_ID )*
            loop2:
            do {
                int alt2=2;
                int LA2_0 = input.LA(1);

                if ( (LA2_0==11) ) {
                    alt2=1;
                }


                switch (alt2) {
            	case 1 :
            	    // InternalRefactoringTestLanguage.g:180:4: kw= '.' this_ID_2= RULE_ID
            	    {
            	    kw=(Token)match(input,11,FollowSets000.FOLLOW_4); 

            	    				current.merge(kw);
            	    				newLeafNode(kw, grammarAccess.getFQNAccess().getFullStopKeyword_1_0());
            	    			
            	    this_ID_2=(Token)match(input,RULE_ID,FollowSets000.FOLLOW_5); 

            	    				current.merge(this_ID_2);
            	    			

            	    				newLeafNode(this_ID_2, grammarAccess.getFQNAccess().getIDTerminalRuleCall_1_1());
            	    			

            	    }
            	    break;

            	default :
            	    break loop2;
                }
            } while (true);

            // InternalRefactoringTestLanguage.g:193:3: (kw= '$' this_ID_4= RULE_ID )*
            loop3:
            do {
                int alt3=2;
                int LA3_0 = input.LA(1);

                if ( (LA3_0==12) ) {
                    alt3=1;
                }


                switch (alt3) {
            	case 1 :
            	    // InternalRefactoringTestLanguage.g:194:4: kw= '$' this_ID_4= RULE_ID
            	    {
            	    kw=(Token)match(input,12,FollowSets000.FOLLOW_4); 

            	    				current.merge(kw);
            	    				newLeafNode(kw, grammarAccess.getFQNAccess().getDollarSignKeyword_2_0());
            	    			
            	    this_ID_4=(Token)match(input,RULE_ID,FollowSets000.FOLLOW_6); 

            	    				current.merge(this_ID_4);
            	    			

            	    				newLeafNode(this_ID_4, grammarAccess.getFQNAccess().getIDTerminalRuleCall_2_1());
            	    			

            	    }
            	    break;

            	default :
            	    break loop3;
                }
            } while (true);


            }


            }


            	leaveRule();

        }

            catch (RecognitionException re) {
                recover(input,re);
                appendSkippedTokens();
            }
        finally {
        }
        return current;
    }
    // $ANTLR end "ruleFQN"

    // Delegated rules


 

    
    private static class FollowSets000 {
        public static final BitSet FOLLOW_1 = new BitSet(new long[]{0x0000000000000000L});
        public static final BitSet FOLLOW_2 = new BitSet(new long[]{0x0000000000000002L});
        public static final BitSet FOLLOW_3 = new BitSet(new long[]{0x0000000000000012L});
        public static final BitSet FOLLOW_4 = new BitSet(new long[]{0x0000000000000010L});
        public static final BitSet FOLLOW_5 = new BitSet(new long[]{0x0000000000001802L});
        public static final BitSet FOLLOW_6 = new BitSet(new long[]{0x0000000000001002L});
    }


}