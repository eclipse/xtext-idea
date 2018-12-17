package org.eclipse.xtext.idea.parser.antlr.internal;

import com.intellij.lang.PsiBuilder;
import org.antlr.runtime.*;
import org.eclipse.xtext.idea.lang.XtextElementTypeProvider;
import org.eclipse.xtext.idea.parser.AbstractPsiAntlrParser;
import org.eclipse.xtext.services.XtextGrammarAccess;

@SuppressWarnings("all")
public class PsiInternalXtextParser extends AbstractPsiAntlrParser {
    public static final String[] tokenNames = new String[] {
        "<invalid>", "<EOR>", "<DOWN>", "<UP>", "RULE_STRING", "RULE_ID", "RULE_INT", "RULE_ML_COMMENT", "RULE_SL_COMMENT", "RULE_WS", "RULE_ANY_OTHER", "'grammar'", "'with'", "','", "'hidden'", "'('", "')'", "'.'", "'generate'", "'as'", "'import'", "'@'", "'fragment'", "'*'", "'returns'", "':'", "';'", "'<'", "'>'", "'::'", "'|'", "'&'", "'?'", "'+'", "'{'", "'='", "'+='", "'current'", "'}'", "'true'", "'false'", "'!'", "'=>'", "'->'", "'?='", "'['", "']'", "'terminal'", "'EOF'", "'..'", "'enum'"
    };
    public static final int T__50=50;
    public static final int T__19=19;
    public static final int T__15=15;
    public static final int T__16=16;
    public static final int T__17=17;
    public static final int T__18=18;
    public static final int T__11=11;
    public static final int T__12=12;
    public static final int T__13=13;
    public static final int T__14=14;
    public static final int RULE_ID=5;
    public static final int T__26=26;
    public static final int T__27=27;
    public static final int T__28=28;
    public static final int RULE_INT=6;
    public static final int T__29=29;
    public static final int T__22=22;
    public static final int RULE_ML_COMMENT=7;
    public static final int T__23=23;
    public static final int T__24=24;
    public static final int T__25=25;
    public static final int T__20=20;
    public static final int T__21=21;
    public static final int RULE_STRING=4;
    public static final int RULE_SL_COMMENT=8;
    public static final int T__37=37;
    public static final int T__38=38;
    public static final int T__39=39;
    public static final int T__33=33;
    public static final int T__34=34;
    public static final int T__35=35;
    public static final int T__36=36;
    public static final int EOF=-1;
    public static final int T__30=30;
    public static final int T__31=31;
    public static final int T__32=32;
    public static final int RULE_WS=9;
    public static final int RULE_ANY_OTHER=10;
    public static final int T__48=48;
    public static final int T__49=49;
    public static final int T__44=44;
    public static final int T__45=45;
    public static final int T__46=46;
    public static final int T__47=47;
    public static final int T__40=40;
    public static final int T__41=41;
    public static final int T__42=42;
    public static final int T__43=43;

    // delegates
    // delegators


        public PsiInternalXtextParser(TokenStream input) {
            this(input, new RecognizerSharedState());
        }
        public PsiInternalXtextParser(TokenStream input, RecognizerSharedState state) {
            super(input, state);
             
        }
        

    public String[] getTokenNames() { return PsiInternalXtextParser.tokenNames; }
    public String getGrammarFileName() { return "PsiInternalXtext.g"; }



    	protected XtextGrammarAccess grammarAccess;

    	protected XtextElementTypeProvider elementTypeProvider;

    	public PsiInternalXtextParser(PsiBuilder builder, TokenStream input, XtextElementTypeProvider elementTypeProvider, XtextGrammarAccess grammarAccess) {
    		this(input);
    		setPsiBuilder(builder);
    		this.grammarAccess = grammarAccess;
    		this.elementTypeProvider = elementTypeProvider;
    	}

    	@Override
    	protected String getFirstRuleName() {
    		return "Grammar";
    	}




    // $ANTLR start "entryRuleGrammar"
    // PsiInternalXtext.g:52:1: entryRuleGrammar returns [Boolean current=false] : iv_ruleGrammar= ruleGrammar EOF ;
    public final Boolean entryRuleGrammar() throws RecognitionException {
        Boolean current = false;

        Boolean iv_ruleGrammar = null;


        try {
            // PsiInternalXtext.g:52:49: (iv_ruleGrammar= ruleGrammar EOF )
            // PsiInternalXtext.g:53:2: iv_ruleGrammar= ruleGrammar EOF
            {
             markComposite(elementTypeProvider.getGrammarElementType()); 
            pushFollow(FollowSets000.FOLLOW_1);
            iv_ruleGrammar=ruleGrammar();

            state._fsp--;

             current =iv_ruleGrammar; 
            match(input,EOF,FollowSets000.FOLLOW_2); 

            }

        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
        }
        finally {
        }
        return current;
    }
    // $ANTLR end "entryRuleGrammar"


    // $ANTLR start "ruleGrammar"
    // PsiInternalXtext.g:59:1: ruleGrammar returns [Boolean current=false] : (otherlv_0= 'grammar' ( (lv_name_1_0= ruleGrammarID ) ) (otherlv_2= 'with' ( ( ruleGrammarID ) ) (otherlv_4= ',' ( ( ruleGrammarID ) ) )* )? ( ( (lv_definesHiddenTokens_6_0= 'hidden' ) ) otherlv_7= '(' ( ( ( ruleRuleID ) ) (otherlv_9= ',' ( ( ruleRuleID ) ) )* )? otherlv_11= ')' )? ( (lv_metamodelDeclarations_12_0= ruleAbstractMetamodelDeclaration ) )* ( (lv_rules_13_0= ruleAbstractRule ) )+ ) ;
    public final Boolean ruleGrammar() throws RecognitionException {
        Boolean current = false;

        Token otherlv_0=null;
        Token otherlv_2=null;
        Token otherlv_4=null;
        Token lv_definesHiddenTokens_6_0=null;
        Token otherlv_7=null;
        Token otherlv_9=null;
        Token otherlv_11=null;
        Boolean lv_name_1_0 = null;

        Boolean lv_metamodelDeclarations_12_0 = null;

        Boolean lv_rules_13_0 = null;


        try {
            // PsiInternalXtext.g:60:1: ( (otherlv_0= 'grammar' ( (lv_name_1_0= ruleGrammarID ) ) (otherlv_2= 'with' ( ( ruleGrammarID ) ) (otherlv_4= ',' ( ( ruleGrammarID ) ) )* )? ( ( (lv_definesHiddenTokens_6_0= 'hidden' ) ) otherlv_7= '(' ( ( ( ruleRuleID ) ) (otherlv_9= ',' ( ( ruleRuleID ) ) )* )? otherlv_11= ')' )? ( (lv_metamodelDeclarations_12_0= ruleAbstractMetamodelDeclaration ) )* ( (lv_rules_13_0= ruleAbstractRule ) )+ ) )
            // PsiInternalXtext.g:61:2: (otherlv_0= 'grammar' ( (lv_name_1_0= ruleGrammarID ) ) (otherlv_2= 'with' ( ( ruleGrammarID ) ) (otherlv_4= ',' ( ( ruleGrammarID ) ) )* )? ( ( (lv_definesHiddenTokens_6_0= 'hidden' ) ) otherlv_7= '(' ( ( ( ruleRuleID ) ) (otherlv_9= ',' ( ( ruleRuleID ) ) )* )? otherlv_11= ')' )? ( (lv_metamodelDeclarations_12_0= ruleAbstractMetamodelDeclaration ) )* ( (lv_rules_13_0= ruleAbstractRule ) )+ )
            {
            // PsiInternalXtext.g:61:2: (otherlv_0= 'grammar' ( (lv_name_1_0= ruleGrammarID ) ) (otherlv_2= 'with' ( ( ruleGrammarID ) ) (otherlv_4= ',' ( ( ruleGrammarID ) ) )* )? ( ( (lv_definesHiddenTokens_6_0= 'hidden' ) ) otherlv_7= '(' ( ( ( ruleRuleID ) ) (otherlv_9= ',' ( ( ruleRuleID ) ) )* )? otherlv_11= ')' )? ( (lv_metamodelDeclarations_12_0= ruleAbstractMetamodelDeclaration ) )* ( (lv_rules_13_0= ruleAbstractRule ) )+ )
            // PsiInternalXtext.g:62:3: otherlv_0= 'grammar' ( (lv_name_1_0= ruleGrammarID ) ) (otherlv_2= 'with' ( ( ruleGrammarID ) ) (otherlv_4= ',' ( ( ruleGrammarID ) ) )* )? ( ( (lv_definesHiddenTokens_6_0= 'hidden' ) ) otherlv_7= '(' ( ( ( ruleRuleID ) ) (otherlv_9= ',' ( ( ruleRuleID ) ) )* )? otherlv_11= ')' )? ( (lv_metamodelDeclarations_12_0= ruleAbstractMetamodelDeclaration ) )* ( (lv_rules_13_0= ruleAbstractRule ) )+
            {

            			markLeaf(elementTypeProvider.getGrammar_GrammarKeyword_0ElementType());
            		
            otherlv_0=(Token)match(input,11,FollowSets000.FOLLOW_3); 

            			doneLeaf(otherlv_0);
            		
            // PsiInternalXtext.g:69:3: ( (lv_name_1_0= ruleGrammarID ) )
            // PsiInternalXtext.g:70:4: (lv_name_1_0= ruleGrammarID )
            {
            // PsiInternalXtext.g:70:4: (lv_name_1_0= ruleGrammarID )
            // PsiInternalXtext.g:71:5: lv_name_1_0= ruleGrammarID
            {

            					markComposite(elementTypeProvider.getGrammar_NameGrammarIDParserRuleCall_1_0ElementType());
            				
            pushFollow(FollowSets000.FOLLOW_4);
            lv_name_1_0=ruleGrammarID();

            state._fsp--;


            					doneComposite();
            					if(!current) {
            						associateWithSemanticElement();
            						current = true;
            					}
            				

            }


            }

            // PsiInternalXtext.g:84:3: (otherlv_2= 'with' ( ( ruleGrammarID ) ) (otherlv_4= ',' ( ( ruleGrammarID ) ) )* )?
            int alt2=2;
            int LA2_0 = input.LA(1);

            if ( (LA2_0==12) ) {
                alt2=1;
            }
            switch (alt2) {
                case 1 :
                    // PsiInternalXtext.g:85:4: otherlv_2= 'with' ( ( ruleGrammarID ) ) (otherlv_4= ',' ( ( ruleGrammarID ) ) )*
                    {

                    				markLeaf(elementTypeProvider.getGrammar_WithKeyword_2_0ElementType());
                    			
                    otherlv_2=(Token)match(input,12,FollowSets000.FOLLOW_3); 

                    				doneLeaf(otherlv_2);
                    			
                    // PsiInternalXtext.g:92:4: ( ( ruleGrammarID ) )
                    // PsiInternalXtext.g:93:5: ( ruleGrammarID )
                    {
                    // PsiInternalXtext.g:93:5: ( ruleGrammarID )
                    // PsiInternalXtext.g:94:6: ruleGrammarID
                    {

                    						if (!current) {
                    							associateWithSemanticElement();
                    							current = true;
                    						}
                    					

                    						markComposite(elementTypeProvider.getGrammar_UsedGrammarsGrammarCrossReference_2_1_0ElementType());
                    					
                    pushFollow(FollowSets000.FOLLOW_5);
                    ruleGrammarID();

                    state._fsp--;


                    						doneComposite();
                    					

                    }


                    }

                    // PsiInternalXtext.g:109:4: (otherlv_4= ',' ( ( ruleGrammarID ) ) )*
                    loop1:
                    do {
                        int alt1=2;
                        int LA1_0 = input.LA(1);

                        if ( (LA1_0==13) ) {
                            alt1=1;
                        }


                        switch (alt1) {
                    	case 1 :
                    	    // PsiInternalXtext.g:110:5: otherlv_4= ',' ( ( ruleGrammarID ) )
                    	    {

                    	    					markLeaf(elementTypeProvider.getGrammar_CommaKeyword_2_2_0ElementType());
                    	    				
                    	    otherlv_4=(Token)match(input,13,FollowSets000.FOLLOW_3); 

                    	    					doneLeaf(otherlv_4);
                    	    				
                    	    // PsiInternalXtext.g:117:5: ( ( ruleGrammarID ) )
                    	    // PsiInternalXtext.g:118:6: ( ruleGrammarID )
                    	    {
                    	    // PsiInternalXtext.g:118:6: ( ruleGrammarID )
                    	    // PsiInternalXtext.g:119:7: ruleGrammarID
                    	    {

                    	    							if (!current) {
                    	    								associateWithSemanticElement();
                    	    								current = true;
                    	    							}
                    	    						

                    	    							markComposite(elementTypeProvider.getGrammar_UsedGrammarsGrammarCrossReference_2_2_1_0ElementType());
                    	    						
                    	    pushFollow(FollowSets000.FOLLOW_5);
                    	    ruleGrammarID();

                    	    state._fsp--;


                    	    							doneComposite();
                    	    						

                    	    }


                    	    }


                    	    }
                    	    break;

                    	default :
                    	    break loop1;
                        }
                    } while (true);


                    }
                    break;

            }

            // PsiInternalXtext.g:136:3: ( ( (lv_definesHiddenTokens_6_0= 'hidden' ) ) otherlv_7= '(' ( ( ( ruleRuleID ) ) (otherlv_9= ',' ( ( ruleRuleID ) ) )* )? otherlv_11= ')' )?
            int alt5=2;
            int LA5_0 = input.LA(1);

            if ( (LA5_0==14) ) {
                alt5=1;
            }
            switch (alt5) {
                case 1 :
                    // PsiInternalXtext.g:137:4: ( (lv_definesHiddenTokens_6_0= 'hidden' ) ) otherlv_7= '(' ( ( ( ruleRuleID ) ) (otherlv_9= ',' ( ( ruleRuleID ) ) )* )? otherlv_11= ')'
                    {
                    // PsiInternalXtext.g:137:4: ( (lv_definesHiddenTokens_6_0= 'hidden' ) )
                    // PsiInternalXtext.g:138:5: (lv_definesHiddenTokens_6_0= 'hidden' )
                    {
                    // PsiInternalXtext.g:138:5: (lv_definesHiddenTokens_6_0= 'hidden' )
                    // PsiInternalXtext.g:139:6: lv_definesHiddenTokens_6_0= 'hidden'
                    {

                    						markLeaf(elementTypeProvider.getGrammar_DefinesHiddenTokensHiddenKeyword_3_0_0ElementType());
                    					
                    lv_definesHiddenTokens_6_0=(Token)match(input,14,FollowSets000.FOLLOW_6); 

                    						doneLeaf(lv_definesHiddenTokens_6_0);
                    					

                    						if (!current) {
                    							associateWithSemanticElement();
                    							current = true;
                    						}
                    					

                    }


                    }


                    				markLeaf(elementTypeProvider.getGrammar_LeftParenthesisKeyword_3_1ElementType());
                    			
                    otherlv_7=(Token)match(input,15,FollowSets000.FOLLOW_7); 

                    				doneLeaf(otherlv_7);
                    			
                    // PsiInternalXtext.g:161:4: ( ( ( ruleRuleID ) ) (otherlv_9= ',' ( ( ruleRuleID ) ) )* )?
                    int alt4=2;
                    int LA4_0 = input.LA(1);

                    if ( (LA4_0==RULE_ID||(LA4_0>=39 && LA4_0<=40)) ) {
                        alt4=1;
                    }
                    switch (alt4) {
                        case 1 :
                            // PsiInternalXtext.g:162:5: ( ( ruleRuleID ) ) (otherlv_9= ',' ( ( ruleRuleID ) ) )*
                            {
                            // PsiInternalXtext.g:162:5: ( ( ruleRuleID ) )
                            // PsiInternalXtext.g:163:6: ( ruleRuleID )
                            {
                            // PsiInternalXtext.g:163:6: ( ruleRuleID )
                            // PsiInternalXtext.g:164:7: ruleRuleID
                            {

                            							if (!current) {
                            								associateWithSemanticElement();
                            								current = true;
                            							}
                            						

                            							markComposite(elementTypeProvider.getGrammar_HiddenTokensAbstractRuleCrossReference_3_2_0_0ElementType());
                            						
                            pushFollow(FollowSets000.FOLLOW_8);
                            ruleRuleID();

                            state._fsp--;


                            							doneComposite();
                            						

                            }


                            }

                            // PsiInternalXtext.g:179:5: (otherlv_9= ',' ( ( ruleRuleID ) ) )*
                            loop3:
                            do {
                                int alt3=2;
                                int LA3_0 = input.LA(1);

                                if ( (LA3_0==13) ) {
                                    alt3=1;
                                }


                                switch (alt3) {
                            	case 1 :
                            	    // PsiInternalXtext.g:180:6: otherlv_9= ',' ( ( ruleRuleID ) )
                            	    {

                            	    						markLeaf(elementTypeProvider.getGrammar_CommaKeyword_3_2_1_0ElementType());
                            	    					
                            	    otherlv_9=(Token)match(input,13,FollowSets000.FOLLOW_3); 

                            	    						doneLeaf(otherlv_9);
                            	    					
                            	    // PsiInternalXtext.g:187:6: ( ( ruleRuleID ) )
                            	    // PsiInternalXtext.g:188:7: ( ruleRuleID )
                            	    {
                            	    // PsiInternalXtext.g:188:7: ( ruleRuleID )
                            	    // PsiInternalXtext.g:189:8: ruleRuleID
                            	    {

                            	    								if (!current) {
                            	    									associateWithSemanticElement();
                            	    									current = true;
                            	    								}
                            	    							

                            	    								markComposite(elementTypeProvider.getGrammar_HiddenTokensAbstractRuleCrossReference_3_2_1_1_0ElementType());
                            	    							
                            	    pushFollow(FollowSets000.FOLLOW_8);
                            	    ruleRuleID();

                            	    state._fsp--;


                            	    								doneComposite();
                            	    							

                            	    }


                            	    }


                            	    }
                            	    break;

                            	default :
                            	    break loop3;
                                }
                            } while (true);


                            }
                            break;

                    }


                    				markLeaf(elementTypeProvider.getGrammar_RightParenthesisKeyword_3_3ElementType());
                    			
                    otherlv_11=(Token)match(input,16,FollowSets000.FOLLOW_4); 

                    				doneLeaf(otherlv_11);
                    			

                    }
                    break;

            }

            // PsiInternalXtext.g:214:3: ( (lv_metamodelDeclarations_12_0= ruleAbstractMetamodelDeclaration ) )*
            loop6:
            do {
                int alt6=2;
                int LA6_0 = input.LA(1);

                if ( (LA6_0==18||LA6_0==20) ) {
                    alt6=1;
                }


                switch (alt6) {
            	case 1 :
            	    // PsiInternalXtext.g:215:4: (lv_metamodelDeclarations_12_0= ruleAbstractMetamodelDeclaration )
            	    {
            	    // PsiInternalXtext.g:215:4: (lv_metamodelDeclarations_12_0= ruleAbstractMetamodelDeclaration )
            	    // PsiInternalXtext.g:216:5: lv_metamodelDeclarations_12_0= ruleAbstractMetamodelDeclaration
            	    {

            	    					markComposite(elementTypeProvider.getGrammar_MetamodelDeclarationsAbstractMetamodelDeclarationParserRuleCall_4_0ElementType());
            	    				
            	    pushFollow(FollowSets000.FOLLOW_4);
            	    lv_metamodelDeclarations_12_0=ruleAbstractMetamodelDeclaration();

            	    state._fsp--;


            	    					doneComposite();
            	    					if(!current) {
            	    						associateWithSemanticElement();
            	    						current = true;
            	    					}
            	    				

            	    }


            	    }
            	    break;

            	default :
            	    break loop6;
                }
            } while (true);

            // PsiInternalXtext.g:229:3: ( (lv_rules_13_0= ruleAbstractRule ) )+
            int cnt7=0;
            loop7:
            do {
                int alt7=2;
                int LA7_0 = input.LA(1);

                if ( (LA7_0==RULE_ID||(LA7_0>=21 && LA7_0<=22)||(LA7_0>=39 && LA7_0<=40)||LA7_0==47||LA7_0==50) ) {
                    alt7=1;
                }


                switch (alt7) {
            	case 1 :
            	    // PsiInternalXtext.g:230:4: (lv_rules_13_0= ruleAbstractRule )
            	    {
            	    // PsiInternalXtext.g:230:4: (lv_rules_13_0= ruleAbstractRule )
            	    // PsiInternalXtext.g:231:5: lv_rules_13_0= ruleAbstractRule
            	    {

            	    					markComposite(elementTypeProvider.getGrammar_RulesAbstractRuleParserRuleCall_5_0ElementType());
            	    				
            	    pushFollow(FollowSets000.FOLLOW_9);
            	    lv_rules_13_0=ruleAbstractRule();

            	    state._fsp--;


            	    					doneComposite();
            	    					if(!current) {
            	    						associateWithSemanticElement();
            	    						current = true;
            	    					}
            	    				

            	    }


            	    }
            	    break;

            	default :
            	    if ( cnt7 >= 1 ) break loop7;
                        EarlyExitException eee =
                            new EarlyExitException(7, input);
                        throw eee;
                }
                cnt7++;
            } while (true);


            }


            }

        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
        }
        finally {
        }
        return current;
    }
    // $ANTLR end "ruleGrammar"


    // $ANTLR start "entryRuleGrammarID"
    // PsiInternalXtext.g:248:1: entryRuleGrammarID returns [Boolean current=false] : iv_ruleGrammarID= ruleGrammarID EOF ;
    public final Boolean entryRuleGrammarID() throws RecognitionException {
        Boolean current = false;

        Boolean iv_ruleGrammarID = null;


        try {
            // PsiInternalXtext.g:248:51: (iv_ruleGrammarID= ruleGrammarID EOF )
            // PsiInternalXtext.g:249:2: iv_ruleGrammarID= ruleGrammarID EOF
            {
             markComposite(elementTypeProvider.getGrammarIDElementType()); 
            pushFollow(FollowSets000.FOLLOW_1);
            iv_ruleGrammarID=ruleGrammarID();

            state._fsp--;

             current =iv_ruleGrammarID; 
            match(input,EOF,FollowSets000.FOLLOW_2); 

            }

        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
        }
        finally {
        }
        return current;
    }
    // $ANTLR end "entryRuleGrammarID"


    // $ANTLR start "ruleGrammarID"
    // PsiInternalXtext.g:255:1: ruleGrammarID returns [Boolean current=false] : ( ruleValidID (kw= '.' ruleValidID )* ) ;
    public final Boolean ruleGrammarID() throws RecognitionException {
        Boolean current = false;

        Token kw=null;

        try {
            // PsiInternalXtext.g:256:1: ( ( ruleValidID (kw= '.' ruleValidID )* ) )
            // PsiInternalXtext.g:257:2: ( ruleValidID (kw= '.' ruleValidID )* )
            {
            // PsiInternalXtext.g:257:2: ( ruleValidID (kw= '.' ruleValidID )* )
            // PsiInternalXtext.g:258:3: ruleValidID (kw= '.' ruleValidID )*
            {

            			markComposite(elementTypeProvider.getGrammarID_ValidIDParserRuleCall_0ElementType());
            		
            pushFollow(FollowSets000.FOLLOW_10);
            ruleValidID();

            state._fsp--;


            			doneComposite();
            		
            // PsiInternalXtext.g:265:3: (kw= '.' ruleValidID )*
            loop8:
            do {
                int alt8=2;
                int LA8_0 = input.LA(1);

                if ( (LA8_0==17) ) {
                    alt8=1;
                }


                switch (alt8) {
            	case 1 :
            	    // PsiInternalXtext.g:266:4: kw= '.' ruleValidID
            	    {

            	    				markLeaf(elementTypeProvider.getGrammarID_FullStopKeyword_1_0ElementType());
            	    			
            	    kw=(Token)match(input,17,FollowSets000.FOLLOW_3); 

            	    				doneLeaf(kw);
            	    			

            	    				markComposite(elementTypeProvider.getGrammarID_ValidIDParserRuleCall_1_1ElementType());
            	    			
            	    pushFollow(FollowSets000.FOLLOW_10);
            	    ruleValidID();

            	    state._fsp--;


            	    				doneComposite();
            	    			

            	    }
            	    break;

            	default :
            	    break loop8;
                }
            } while (true);


            }


            }

        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
        }
        finally {
        }
        return current;
    }
    // $ANTLR end "ruleGrammarID"


    // $ANTLR start "entryRuleAbstractRule"
    // PsiInternalXtext.g:285:1: entryRuleAbstractRule returns [Boolean current=false] : iv_ruleAbstractRule= ruleAbstractRule EOF ;
    public final Boolean entryRuleAbstractRule() throws RecognitionException {
        Boolean current = false;

        Boolean iv_ruleAbstractRule = null;


        try {
            // PsiInternalXtext.g:285:54: (iv_ruleAbstractRule= ruleAbstractRule EOF )
            // PsiInternalXtext.g:286:2: iv_ruleAbstractRule= ruleAbstractRule EOF
            {
             markComposite(elementTypeProvider.getAbstractRuleElementType()); 
            pushFollow(FollowSets000.FOLLOW_1);
            iv_ruleAbstractRule=ruleAbstractRule();

            state._fsp--;

             current =iv_ruleAbstractRule; 
            match(input,EOF,FollowSets000.FOLLOW_2); 

            }

        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
        }
        finally {
        }
        return current;
    }
    // $ANTLR end "entryRuleAbstractRule"


    // $ANTLR start "ruleAbstractRule"
    // PsiInternalXtext.g:292:1: ruleAbstractRule returns [Boolean current=false] : (this_ParserRule_0= ruleParserRule | this_TerminalRule_1= ruleTerminalRule | this_EnumRule_2= ruleEnumRule ) ;
    public final Boolean ruleAbstractRule() throws RecognitionException {
        Boolean current = false;

        Boolean this_ParserRule_0 = null;

        Boolean this_TerminalRule_1 = null;

        Boolean this_EnumRule_2 = null;


        try {
            // PsiInternalXtext.g:293:1: ( (this_ParserRule_0= ruleParserRule | this_TerminalRule_1= ruleTerminalRule | this_EnumRule_2= ruleEnumRule ) )
            // PsiInternalXtext.g:294:2: (this_ParserRule_0= ruleParserRule | this_TerminalRule_1= ruleTerminalRule | this_EnumRule_2= ruleEnumRule )
            {
            // PsiInternalXtext.g:294:2: (this_ParserRule_0= ruleParserRule | this_TerminalRule_1= ruleTerminalRule | this_EnumRule_2= ruleEnumRule )
            int alt9=3;
            alt9 = dfa9.predict(input);
            switch (alt9) {
                case 1 :
                    // PsiInternalXtext.g:295:3: this_ParserRule_0= ruleParserRule
                    {

                    			markComposite(elementTypeProvider.getAbstractRule_ParserRuleParserRuleCall_0ElementType());
                    		
                    pushFollow(FollowSets000.FOLLOW_2);
                    this_ParserRule_0=ruleParserRule();

                    state._fsp--;


                    			current = this_ParserRule_0;
                    			doneComposite();
                    		

                    }
                    break;
                case 2 :
                    // PsiInternalXtext.g:304:3: this_TerminalRule_1= ruleTerminalRule
                    {

                    			markComposite(elementTypeProvider.getAbstractRule_TerminalRuleParserRuleCall_1ElementType());
                    		
                    pushFollow(FollowSets000.FOLLOW_2);
                    this_TerminalRule_1=ruleTerminalRule();

                    state._fsp--;


                    			current = this_TerminalRule_1;
                    			doneComposite();
                    		

                    }
                    break;
                case 3 :
                    // PsiInternalXtext.g:313:3: this_EnumRule_2= ruleEnumRule
                    {

                    			markComposite(elementTypeProvider.getAbstractRule_EnumRuleParserRuleCall_2ElementType());
                    		
                    pushFollow(FollowSets000.FOLLOW_2);
                    this_EnumRule_2=ruleEnumRule();

                    state._fsp--;


                    			current = this_EnumRule_2;
                    			doneComposite();
                    		

                    }
                    break;

            }


            }

        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
        }
        finally {
        }
        return current;
    }
    // $ANTLR end "ruleAbstractRule"


    // $ANTLR start "entryRuleAbstractMetamodelDeclaration"
    // PsiInternalXtext.g:325:1: entryRuleAbstractMetamodelDeclaration returns [Boolean current=false] : iv_ruleAbstractMetamodelDeclaration= ruleAbstractMetamodelDeclaration EOF ;
    public final Boolean entryRuleAbstractMetamodelDeclaration() throws RecognitionException {
        Boolean current = false;

        Boolean iv_ruleAbstractMetamodelDeclaration = null;


        try {
            // PsiInternalXtext.g:325:70: (iv_ruleAbstractMetamodelDeclaration= ruleAbstractMetamodelDeclaration EOF )
            // PsiInternalXtext.g:326:2: iv_ruleAbstractMetamodelDeclaration= ruleAbstractMetamodelDeclaration EOF
            {
             markComposite(elementTypeProvider.getAbstractMetamodelDeclarationElementType()); 
            pushFollow(FollowSets000.FOLLOW_1);
            iv_ruleAbstractMetamodelDeclaration=ruleAbstractMetamodelDeclaration();

            state._fsp--;

             current =iv_ruleAbstractMetamodelDeclaration; 
            match(input,EOF,FollowSets000.FOLLOW_2); 

            }

        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
        }
        finally {
        }
        return current;
    }
    // $ANTLR end "entryRuleAbstractMetamodelDeclaration"


    // $ANTLR start "ruleAbstractMetamodelDeclaration"
    // PsiInternalXtext.g:332:1: ruleAbstractMetamodelDeclaration returns [Boolean current=false] : (this_GeneratedMetamodel_0= ruleGeneratedMetamodel | this_ReferencedMetamodel_1= ruleReferencedMetamodel ) ;
    public final Boolean ruleAbstractMetamodelDeclaration() throws RecognitionException {
        Boolean current = false;

        Boolean this_GeneratedMetamodel_0 = null;

        Boolean this_ReferencedMetamodel_1 = null;


        try {
            // PsiInternalXtext.g:333:1: ( (this_GeneratedMetamodel_0= ruleGeneratedMetamodel | this_ReferencedMetamodel_1= ruleReferencedMetamodel ) )
            // PsiInternalXtext.g:334:2: (this_GeneratedMetamodel_0= ruleGeneratedMetamodel | this_ReferencedMetamodel_1= ruleReferencedMetamodel )
            {
            // PsiInternalXtext.g:334:2: (this_GeneratedMetamodel_0= ruleGeneratedMetamodel | this_ReferencedMetamodel_1= ruleReferencedMetamodel )
            int alt10=2;
            int LA10_0 = input.LA(1);

            if ( (LA10_0==18) ) {
                alt10=1;
            }
            else if ( (LA10_0==20) ) {
                alt10=2;
            }
            else {
                NoViableAltException nvae =
                    new NoViableAltException("", 10, 0, input);

                throw nvae;
            }
            switch (alt10) {
                case 1 :
                    // PsiInternalXtext.g:335:3: this_GeneratedMetamodel_0= ruleGeneratedMetamodel
                    {

                    			markComposite(elementTypeProvider.getAbstractMetamodelDeclaration_GeneratedMetamodelParserRuleCall_0ElementType());
                    		
                    pushFollow(FollowSets000.FOLLOW_2);
                    this_GeneratedMetamodel_0=ruleGeneratedMetamodel();

                    state._fsp--;


                    			current = this_GeneratedMetamodel_0;
                    			doneComposite();
                    		

                    }
                    break;
                case 2 :
                    // PsiInternalXtext.g:344:3: this_ReferencedMetamodel_1= ruleReferencedMetamodel
                    {

                    			markComposite(elementTypeProvider.getAbstractMetamodelDeclaration_ReferencedMetamodelParserRuleCall_1ElementType());
                    		
                    pushFollow(FollowSets000.FOLLOW_2);
                    this_ReferencedMetamodel_1=ruleReferencedMetamodel();

                    state._fsp--;


                    			current = this_ReferencedMetamodel_1;
                    			doneComposite();
                    		

                    }
                    break;

            }


            }

        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
        }
        finally {
        }
        return current;
    }
    // $ANTLR end "ruleAbstractMetamodelDeclaration"


    // $ANTLR start "entryRuleGeneratedMetamodel"
    // PsiInternalXtext.g:356:1: entryRuleGeneratedMetamodel returns [Boolean current=false] : iv_ruleGeneratedMetamodel= ruleGeneratedMetamodel EOF ;
    public final Boolean entryRuleGeneratedMetamodel() throws RecognitionException {
        Boolean current = false;

        Boolean iv_ruleGeneratedMetamodel = null;


        try {
            // PsiInternalXtext.g:356:60: (iv_ruleGeneratedMetamodel= ruleGeneratedMetamodel EOF )
            // PsiInternalXtext.g:357:2: iv_ruleGeneratedMetamodel= ruleGeneratedMetamodel EOF
            {
             markComposite(elementTypeProvider.getGeneratedMetamodelElementType()); 
            pushFollow(FollowSets000.FOLLOW_1);
            iv_ruleGeneratedMetamodel=ruleGeneratedMetamodel();

            state._fsp--;

             current =iv_ruleGeneratedMetamodel; 
            match(input,EOF,FollowSets000.FOLLOW_2); 

            }

        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
        }
        finally {
        }
        return current;
    }
    // $ANTLR end "entryRuleGeneratedMetamodel"


    // $ANTLR start "ruleGeneratedMetamodel"
    // PsiInternalXtext.g:363:1: ruleGeneratedMetamodel returns [Boolean current=false] : (otherlv_0= 'generate' ( (lv_name_1_0= ruleValidID ) ) ( (otherlv_2= RULE_STRING ) ) (otherlv_3= 'as' ( (lv_alias_4_0= ruleValidID ) ) )? ) ;
    public final Boolean ruleGeneratedMetamodel() throws RecognitionException {
        Boolean current = false;

        Token otherlv_0=null;
        Token otherlv_2=null;
        Token otherlv_3=null;
        Boolean lv_name_1_0 = null;

        Boolean lv_alias_4_0 = null;


        try {
            // PsiInternalXtext.g:364:1: ( (otherlv_0= 'generate' ( (lv_name_1_0= ruleValidID ) ) ( (otherlv_2= RULE_STRING ) ) (otherlv_3= 'as' ( (lv_alias_4_0= ruleValidID ) ) )? ) )
            // PsiInternalXtext.g:365:2: (otherlv_0= 'generate' ( (lv_name_1_0= ruleValidID ) ) ( (otherlv_2= RULE_STRING ) ) (otherlv_3= 'as' ( (lv_alias_4_0= ruleValidID ) ) )? )
            {
            // PsiInternalXtext.g:365:2: (otherlv_0= 'generate' ( (lv_name_1_0= ruleValidID ) ) ( (otherlv_2= RULE_STRING ) ) (otherlv_3= 'as' ( (lv_alias_4_0= ruleValidID ) ) )? )
            // PsiInternalXtext.g:366:3: otherlv_0= 'generate' ( (lv_name_1_0= ruleValidID ) ) ( (otherlv_2= RULE_STRING ) ) (otherlv_3= 'as' ( (lv_alias_4_0= ruleValidID ) ) )?
            {

            			markLeaf(elementTypeProvider.getGeneratedMetamodel_GenerateKeyword_0ElementType());
            		
            otherlv_0=(Token)match(input,18,FollowSets000.FOLLOW_3); 

            			doneLeaf(otherlv_0);
            		
            // PsiInternalXtext.g:373:3: ( (lv_name_1_0= ruleValidID ) )
            // PsiInternalXtext.g:374:4: (lv_name_1_0= ruleValidID )
            {
            // PsiInternalXtext.g:374:4: (lv_name_1_0= ruleValidID )
            // PsiInternalXtext.g:375:5: lv_name_1_0= ruleValidID
            {

            					markComposite(elementTypeProvider.getGeneratedMetamodel_NameValidIDParserRuleCall_1_0ElementType());
            				
            pushFollow(FollowSets000.FOLLOW_11);
            lv_name_1_0=ruleValidID();

            state._fsp--;


            					doneComposite();
            					if(!current) {
            						associateWithSemanticElement();
            						current = true;
            					}
            				

            }


            }

            // PsiInternalXtext.g:388:3: ( (otherlv_2= RULE_STRING ) )
            // PsiInternalXtext.g:389:4: (otherlv_2= RULE_STRING )
            {
            // PsiInternalXtext.g:389:4: (otherlv_2= RULE_STRING )
            // PsiInternalXtext.g:390:5: otherlv_2= RULE_STRING
            {

            					if (!current) {
            						associateWithSemanticElement();
            						current = true;
            					}
            				

            					markLeaf(elementTypeProvider.getGeneratedMetamodel_EPackageEPackageCrossReference_2_0ElementType());
            				
            otherlv_2=(Token)match(input,RULE_STRING,FollowSets000.FOLLOW_12); 

            					doneLeaf(otherlv_2);
            				

            }


            }

            // PsiInternalXtext.g:405:3: (otherlv_3= 'as' ( (lv_alias_4_0= ruleValidID ) ) )?
            int alt11=2;
            int LA11_0 = input.LA(1);

            if ( (LA11_0==19) ) {
                alt11=1;
            }
            switch (alt11) {
                case 1 :
                    // PsiInternalXtext.g:406:4: otherlv_3= 'as' ( (lv_alias_4_0= ruleValidID ) )
                    {

                    				markLeaf(elementTypeProvider.getGeneratedMetamodel_AsKeyword_3_0ElementType());
                    			
                    otherlv_3=(Token)match(input,19,FollowSets000.FOLLOW_3); 

                    				doneLeaf(otherlv_3);
                    			
                    // PsiInternalXtext.g:413:4: ( (lv_alias_4_0= ruleValidID ) )
                    // PsiInternalXtext.g:414:5: (lv_alias_4_0= ruleValidID )
                    {
                    // PsiInternalXtext.g:414:5: (lv_alias_4_0= ruleValidID )
                    // PsiInternalXtext.g:415:6: lv_alias_4_0= ruleValidID
                    {

                    						markComposite(elementTypeProvider.getGeneratedMetamodel_AliasValidIDParserRuleCall_3_1_0ElementType());
                    					
                    pushFollow(FollowSets000.FOLLOW_2);
                    lv_alias_4_0=ruleValidID();

                    state._fsp--;


                    						doneComposite();
                    						if(!current) {
                    							associateWithSemanticElement();
                    							current = true;
                    						}
                    					

                    }


                    }


                    }
                    break;

            }


            }


            }

        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
        }
        finally {
        }
        return current;
    }
    // $ANTLR end "ruleGeneratedMetamodel"


    // $ANTLR start "entryRuleReferencedMetamodel"
    // PsiInternalXtext.g:433:1: entryRuleReferencedMetamodel returns [Boolean current=false] : iv_ruleReferencedMetamodel= ruleReferencedMetamodel EOF ;
    public final Boolean entryRuleReferencedMetamodel() throws RecognitionException {
        Boolean current = false;

        Boolean iv_ruleReferencedMetamodel = null;


        try {
            // PsiInternalXtext.g:433:61: (iv_ruleReferencedMetamodel= ruleReferencedMetamodel EOF )
            // PsiInternalXtext.g:434:2: iv_ruleReferencedMetamodel= ruleReferencedMetamodel EOF
            {
             markComposite(elementTypeProvider.getReferencedMetamodelElementType()); 
            pushFollow(FollowSets000.FOLLOW_1);
            iv_ruleReferencedMetamodel=ruleReferencedMetamodel();

            state._fsp--;

             current =iv_ruleReferencedMetamodel; 
            match(input,EOF,FollowSets000.FOLLOW_2); 

            }

        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
        }
        finally {
        }
        return current;
    }
    // $ANTLR end "entryRuleReferencedMetamodel"


    // $ANTLR start "ruleReferencedMetamodel"
    // PsiInternalXtext.g:440:1: ruleReferencedMetamodel returns [Boolean current=false] : (otherlv_0= 'import' ( (otherlv_1= RULE_STRING ) ) (otherlv_2= 'as' ( (lv_alias_3_0= ruleValidID ) ) )? ) ;
    public final Boolean ruleReferencedMetamodel() throws RecognitionException {
        Boolean current = false;

        Token otherlv_0=null;
        Token otherlv_1=null;
        Token otherlv_2=null;
        Boolean lv_alias_3_0 = null;


        try {
            // PsiInternalXtext.g:441:1: ( (otherlv_0= 'import' ( (otherlv_1= RULE_STRING ) ) (otherlv_2= 'as' ( (lv_alias_3_0= ruleValidID ) ) )? ) )
            // PsiInternalXtext.g:442:2: (otherlv_0= 'import' ( (otherlv_1= RULE_STRING ) ) (otherlv_2= 'as' ( (lv_alias_3_0= ruleValidID ) ) )? )
            {
            // PsiInternalXtext.g:442:2: (otherlv_0= 'import' ( (otherlv_1= RULE_STRING ) ) (otherlv_2= 'as' ( (lv_alias_3_0= ruleValidID ) ) )? )
            // PsiInternalXtext.g:443:3: otherlv_0= 'import' ( (otherlv_1= RULE_STRING ) ) (otherlv_2= 'as' ( (lv_alias_3_0= ruleValidID ) ) )?
            {

            			markLeaf(elementTypeProvider.getReferencedMetamodel_ImportKeyword_0ElementType());
            		
            otherlv_0=(Token)match(input,20,FollowSets000.FOLLOW_11); 

            			doneLeaf(otherlv_0);
            		
            // PsiInternalXtext.g:450:3: ( (otherlv_1= RULE_STRING ) )
            // PsiInternalXtext.g:451:4: (otherlv_1= RULE_STRING )
            {
            // PsiInternalXtext.g:451:4: (otherlv_1= RULE_STRING )
            // PsiInternalXtext.g:452:5: otherlv_1= RULE_STRING
            {

            					if (!current) {
            						associateWithSemanticElement();
            						current = true;
            					}
            				

            					markLeaf(elementTypeProvider.getReferencedMetamodel_EPackageEPackageCrossReference_1_0ElementType());
            				
            otherlv_1=(Token)match(input,RULE_STRING,FollowSets000.FOLLOW_12); 

            					doneLeaf(otherlv_1);
            				

            }


            }

            // PsiInternalXtext.g:467:3: (otherlv_2= 'as' ( (lv_alias_3_0= ruleValidID ) ) )?
            int alt12=2;
            int LA12_0 = input.LA(1);

            if ( (LA12_0==19) ) {
                alt12=1;
            }
            switch (alt12) {
                case 1 :
                    // PsiInternalXtext.g:468:4: otherlv_2= 'as' ( (lv_alias_3_0= ruleValidID ) )
                    {

                    				markLeaf(elementTypeProvider.getReferencedMetamodel_AsKeyword_2_0ElementType());
                    			
                    otherlv_2=(Token)match(input,19,FollowSets000.FOLLOW_3); 

                    				doneLeaf(otherlv_2);
                    			
                    // PsiInternalXtext.g:475:4: ( (lv_alias_3_0= ruleValidID ) )
                    // PsiInternalXtext.g:476:5: (lv_alias_3_0= ruleValidID )
                    {
                    // PsiInternalXtext.g:476:5: (lv_alias_3_0= ruleValidID )
                    // PsiInternalXtext.g:477:6: lv_alias_3_0= ruleValidID
                    {

                    						markComposite(elementTypeProvider.getReferencedMetamodel_AliasValidIDParserRuleCall_2_1_0ElementType());
                    					
                    pushFollow(FollowSets000.FOLLOW_2);
                    lv_alias_3_0=ruleValidID();

                    state._fsp--;


                    						doneComposite();
                    						if(!current) {
                    							associateWithSemanticElement();
                    							current = true;
                    						}
                    					

                    }


                    }


                    }
                    break;

            }


            }


            }

        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
        }
        finally {
        }
        return current;
    }
    // $ANTLR end "ruleReferencedMetamodel"


    // $ANTLR start "entryRuleAnnotation"
    // PsiInternalXtext.g:495:1: entryRuleAnnotation returns [Boolean current=false] : iv_ruleAnnotation= ruleAnnotation EOF ;
    public final Boolean entryRuleAnnotation() throws RecognitionException {
        Boolean current = false;

        Boolean iv_ruleAnnotation = null;


        try {
            // PsiInternalXtext.g:495:52: (iv_ruleAnnotation= ruleAnnotation EOF )
            // PsiInternalXtext.g:496:2: iv_ruleAnnotation= ruleAnnotation EOF
            {
             markComposite(elementTypeProvider.getAnnotationElementType()); 
            pushFollow(FollowSets000.FOLLOW_1);
            iv_ruleAnnotation=ruleAnnotation();

            state._fsp--;

             current =iv_ruleAnnotation; 
            match(input,EOF,FollowSets000.FOLLOW_2); 

            }

        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
        }
        finally {
        }
        return current;
    }
    // $ANTLR end "entryRuleAnnotation"


    // $ANTLR start "ruleAnnotation"
    // PsiInternalXtext.g:502:1: ruleAnnotation returns [Boolean current=false] : (otherlv_0= '@' ( (lv_name_1_0= RULE_ID ) ) ) ;
    public final Boolean ruleAnnotation() throws RecognitionException {
        Boolean current = false;

        Token otherlv_0=null;
        Token lv_name_1_0=null;

        try {
            // PsiInternalXtext.g:503:1: ( (otherlv_0= '@' ( (lv_name_1_0= RULE_ID ) ) ) )
            // PsiInternalXtext.g:504:2: (otherlv_0= '@' ( (lv_name_1_0= RULE_ID ) ) )
            {
            // PsiInternalXtext.g:504:2: (otherlv_0= '@' ( (lv_name_1_0= RULE_ID ) ) )
            // PsiInternalXtext.g:505:3: otherlv_0= '@' ( (lv_name_1_0= RULE_ID ) )
            {

            			markLeaf(elementTypeProvider.getAnnotation_CommercialAtKeyword_0ElementType());
            		
            otherlv_0=(Token)match(input,21,FollowSets000.FOLLOW_13); 

            			doneLeaf(otherlv_0);
            		
            // PsiInternalXtext.g:512:3: ( (lv_name_1_0= RULE_ID ) )
            // PsiInternalXtext.g:513:4: (lv_name_1_0= RULE_ID )
            {
            // PsiInternalXtext.g:513:4: (lv_name_1_0= RULE_ID )
            // PsiInternalXtext.g:514:5: lv_name_1_0= RULE_ID
            {

            					markLeaf(elementTypeProvider.getAnnotation_NameIDTerminalRuleCall_1_0ElementType());
            				
            lv_name_1_0=(Token)match(input,RULE_ID,FollowSets000.FOLLOW_2); 

            					if(!current) {
            						associateWithSemanticElement();
            						current = true;
            					}
            				

            					doneLeaf(lv_name_1_0);
            				

            }


            }


            }


            }

        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
        }
        finally {
        }
        return current;
    }
    // $ANTLR end "ruleAnnotation"


    // $ANTLR start "entryRuleParserRule"
    // PsiInternalXtext.g:533:1: entryRuleParserRule returns [Boolean current=false] : iv_ruleParserRule= ruleParserRule EOF ;
    public final Boolean entryRuleParserRule() throws RecognitionException {
        Boolean current = false;

        Boolean iv_ruleParserRule = null;


        try {
            // PsiInternalXtext.g:533:52: (iv_ruleParserRule= ruleParserRule EOF )
            // PsiInternalXtext.g:534:2: iv_ruleParserRule= ruleParserRule EOF
            {
             markComposite(elementTypeProvider.getParserRuleElementType()); 
            pushFollow(FollowSets000.FOLLOW_1);
            iv_ruleParserRule=ruleParserRule();

            state._fsp--;

             current =iv_ruleParserRule; 
            match(input,EOF,FollowSets000.FOLLOW_2); 

            }

        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
        }
        finally {
        }
        return current;
    }
    // $ANTLR end "entryRuleParserRule"


    // $ANTLR start "ruleParserRule"
    // PsiInternalXtext.g:540:1: ruleParserRule returns [Boolean current=false] : ( ( (lv_annotations_0_0= ruleAnnotation ) )* ( ( ( (lv_fragment_1_0= 'fragment' ) ) this_RuleNameAndParams_2= ruleRuleNameAndParams[$current] ( ( (lv_wildcard_3_0= '*' ) ) | (otherlv_4= 'returns' ( (lv_type_5_0= ruleTypeRef ) ) )? ) ) | (this_RuleNameAndParams_6= ruleRuleNameAndParams[$current] (otherlv_7= 'returns' ( (lv_type_8_0= ruleTypeRef ) ) )? ) ) ( ( (lv_definesHiddenTokens_9_0= 'hidden' ) ) otherlv_10= '(' ( ( ( ruleRuleID ) ) (otherlv_12= ',' ( ( ruleRuleID ) ) )* )? otherlv_14= ')' )? otherlv_15= ':' ( (lv_alternatives_16_0= ruleAlternatives ) ) otherlv_17= ';' ) ;
    public final Boolean ruleParserRule() throws RecognitionException {
        Boolean current = false;

        Token lv_fragment_1_0=null;
        Token lv_wildcard_3_0=null;
        Token otherlv_4=null;
        Token otherlv_7=null;
        Token lv_definesHiddenTokens_9_0=null;
        Token otherlv_10=null;
        Token otherlv_12=null;
        Token otherlv_14=null;
        Token otherlv_15=null;
        Token otherlv_17=null;
        Boolean lv_annotations_0_0 = null;

        Boolean this_RuleNameAndParams_2 = null;

        Boolean lv_type_5_0 = null;

        Boolean this_RuleNameAndParams_6 = null;

        Boolean lv_type_8_0 = null;

        Boolean lv_alternatives_16_0 = null;


        try {
            // PsiInternalXtext.g:541:1: ( ( ( (lv_annotations_0_0= ruleAnnotation ) )* ( ( ( (lv_fragment_1_0= 'fragment' ) ) this_RuleNameAndParams_2= ruleRuleNameAndParams[$current] ( ( (lv_wildcard_3_0= '*' ) ) | (otherlv_4= 'returns' ( (lv_type_5_0= ruleTypeRef ) ) )? ) ) | (this_RuleNameAndParams_6= ruleRuleNameAndParams[$current] (otherlv_7= 'returns' ( (lv_type_8_0= ruleTypeRef ) ) )? ) ) ( ( (lv_definesHiddenTokens_9_0= 'hidden' ) ) otherlv_10= '(' ( ( ( ruleRuleID ) ) (otherlv_12= ',' ( ( ruleRuleID ) ) )* )? otherlv_14= ')' )? otherlv_15= ':' ( (lv_alternatives_16_0= ruleAlternatives ) ) otherlv_17= ';' ) )
            // PsiInternalXtext.g:542:2: ( ( (lv_annotations_0_0= ruleAnnotation ) )* ( ( ( (lv_fragment_1_0= 'fragment' ) ) this_RuleNameAndParams_2= ruleRuleNameAndParams[$current] ( ( (lv_wildcard_3_0= '*' ) ) | (otherlv_4= 'returns' ( (lv_type_5_0= ruleTypeRef ) ) )? ) ) | (this_RuleNameAndParams_6= ruleRuleNameAndParams[$current] (otherlv_7= 'returns' ( (lv_type_8_0= ruleTypeRef ) ) )? ) ) ( ( (lv_definesHiddenTokens_9_0= 'hidden' ) ) otherlv_10= '(' ( ( ( ruleRuleID ) ) (otherlv_12= ',' ( ( ruleRuleID ) ) )* )? otherlv_14= ')' )? otherlv_15= ':' ( (lv_alternatives_16_0= ruleAlternatives ) ) otherlv_17= ';' )
            {
            // PsiInternalXtext.g:542:2: ( ( (lv_annotations_0_0= ruleAnnotation ) )* ( ( ( (lv_fragment_1_0= 'fragment' ) ) this_RuleNameAndParams_2= ruleRuleNameAndParams[$current] ( ( (lv_wildcard_3_0= '*' ) ) | (otherlv_4= 'returns' ( (lv_type_5_0= ruleTypeRef ) ) )? ) ) | (this_RuleNameAndParams_6= ruleRuleNameAndParams[$current] (otherlv_7= 'returns' ( (lv_type_8_0= ruleTypeRef ) ) )? ) ) ( ( (lv_definesHiddenTokens_9_0= 'hidden' ) ) otherlv_10= '(' ( ( ( ruleRuleID ) ) (otherlv_12= ',' ( ( ruleRuleID ) ) )* )? otherlv_14= ')' )? otherlv_15= ':' ( (lv_alternatives_16_0= ruleAlternatives ) ) otherlv_17= ';' )
            // PsiInternalXtext.g:543:3: ( (lv_annotations_0_0= ruleAnnotation ) )* ( ( ( (lv_fragment_1_0= 'fragment' ) ) this_RuleNameAndParams_2= ruleRuleNameAndParams[$current] ( ( (lv_wildcard_3_0= '*' ) ) | (otherlv_4= 'returns' ( (lv_type_5_0= ruleTypeRef ) ) )? ) ) | (this_RuleNameAndParams_6= ruleRuleNameAndParams[$current] (otherlv_7= 'returns' ( (lv_type_8_0= ruleTypeRef ) ) )? ) ) ( ( (lv_definesHiddenTokens_9_0= 'hidden' ) ) otherlv_10= '(' ( ( ( ruleRuleID ) ) (otherlv_12= ',' ( ( ruleRuleID ) ) )* )? otherlv_14= ')' )? otherlv_15= ':' ( (lv_alternatives_16_0= ruleAlternatives ) ) otherlv_17= ';'
            {
            // PsiInternalXtext.g:543:3: ( (lv_annotations_0_0= ruleAnnotation ) )*
            loop13:
            do {
                int alt13=2;
                int LA13_0 = input.LA(1);

                if ( (LA13_0==21) ) {
                    alt13=1;
                }


                switch (alt13) {
            	case 1 :
            	    // PsiInternalXtext.g:544:4: (lv_annotations_0_0= ruleAnnotation )
            	    {
            	    // PsiInternalXtext.g:544:4: (lv_annotations_0_0= ruleAnnotation )
            	    // PsiInternalXtext.g:545:5: lv_annotations_0_0= ruleAnnotation
            	    {

            	    					markComposite(elementTypeProvider.getParserRule_AnnotationsAnnotationParserRuleCall_0_0ElementType());
            	    				
            	    pushFollow(FollowSets000.FOLLOW_14);
            	    lv_annotations_0_0=ruleAnnotation();

            	    state._fsp--;


            	    					doneComposite();
            	    					if(!current) {
            	    						associateWithSemanticElement();
            	    						current = true;
            	    					}
            	    				

            	    }


            	    }
            	    break;

            	default :
            	    break loop13;
                }
            } while (true);

            // PsiInternalXtext.g:558:3: ( ( ( (lv_fragment_1_0= 'fragment' ) ) this_RuleNameAndParams_2= ruleRuleNameAndParams[$current] ( ( (lv_wildcard_3_0= '*' ) ) | (otherlv_4= 'returns' ( (lv_type_5_0= ruleTypeRef ) ) )? ) ) | (this_RuleNameAndParams_6= ruleRuleNameAndParams[$current] (otherlv_7= 'returns' ( (lv_type_8_0= ruleTypeRef ) ) )? ) )
            int alt17=2;
            int LA17_0 = input.LA(1);

            if ( (LA17_0==22) ) {
                alt17=1;
            }
            else if ( (LA17_0==RULE_ID||(LA17_0>=39 && LA17_0<=40)) ) {
                alt17=2;
            }
            else {
                NoViableAltException nvae =
                    new NoViableAltException("", 17, 0, input);

                throw nvae;
            }
            switch (alt17) {
                case 1 :
                    // PsiInternalXtext.g:559:4: ( ( (lv_fragment_1_0= 'fragment' ) ) this_RuleNameAndParams_2= ruleRuleNameAndParams[$current] ( ( (lv_wildcard_3_0= '*' ) ) | (otherlv_4= 'returns' ( (lv_type_5_0= ruleTypeRef ) ) )? ) )
                    {
                    // PsiInternalXtext.g:559:4: ( ( (lv_fragment_1_0= 'fragment' ) ) this_RuleNameAndParams_2= ruleRuleNameAndParams[$current] ( ( (lv_wildcard_3_0= '*' ) ) | (otherlv_4= 'returns' ( (lv_type_5_0= ruleTypeRef ) ) )? ) )
                    // PsiInternalXtext.g:560:5: ( (lv_fragment_1_0= 'fragment' ) ) this_RuleNameAndParams_2= ruleRuleNameAndParams[$current] ( ( (lv_wildcard_3_0= '*' ) ) | (otherlv_4= 'returns' ( (lv_type_5_0= ruleTypeRef ) ) )? )
                    {
                    // PsiInternalXtext.g:560:5: ( (lv_fragment_1_0= 'fragment' ) )
                    // PsiInternalXtext.g:561:6: (lv_fragment_1_0= 'fragment' )
                    {
                    // PsiInternalXtext.g:561:6: (lv_fragment_1_0= 'fragment' )
                    // PsiInternalXtext.g:562:7: lv_fragment_1_0= 'fragment'
                    {

                    							markLeaf(elementTypeProvider.getParserRule_FragmentFragmentKeyword_1_0_0_0ElementType());
                    						
                    lv_fragment_1_0=(Token)match(input,22,FollowSets000.FOLLOW_14); 

                    							doneLeaf(lv_fragment_1_0);
                    						

                    							if (!current) {
                    								associateWithSemanticElement();
                    								current = true;
                    							}
                    						

                    }


                    }


                    					if (!current) {
                    						associateWithSemanticElement();
                    						current = true;
                    					}
                    					markComposite(elementTypeProvider.getParserRule_RuleNameAndParamsParserRuleCall_1_0_1ElementType());
                    				
                    pushFollow(FollowSets000.FOLLOW_15);
                    this_RuleNameAndParams_2=ruleRuleNameAndParams(current);

                    state._fsp--;


                    					current = this_RuleNameAndParams_2;
                    					doneComposite();
                    				
                    // PsiInternalXtext.g:589:5: ( ( (lv_wildcard_3_0= '*' ) ) | (otherlv_4= 'returns' ( (lv_type_5_0= ruleTypeRef ) ) )? )
                    int alt15=2;
                    int LA15_0 = input.LA(1);

                    if ( (LA15_0==23) ) {
                        alt15=1;
                    }
                    else if ( (LA15_0==14||(LA15_0>=24 && LA15_0<=25)) ) {
                        alt15=2;
                    }
                    else {
                        NoViableAltException nvae =
                            new NoViableAltException("", 15, 0, input);

                        throw nvae;
                    }
                    switch (alt15) {
                        case 1 :
                            // PsiInternalXtext.g:590:6: ( (lv_wildcard_3_0= '*' ) )
                            {
                            // PsiInternalXtext.g:590:6: ( (lv_wildcard_3_0= '*' ) )
                            // PsiInternalXtext.g:591:7: (lv_wildcard_3_0= '*' )
                            {
                            // PsiInternalXtext.g:591:7: (lv_wildcard_3_0= '*' )
                            // PsiInternalXtext.g:592:8: lv_wildcard_3_0= '*'
                            {

                            								markLeaf(elementTypeProvider.getParserRule_WildcardAsteriskKeyword_1_0_2_0_0ElementType());
                            							
                            lv_wildcard_3_0=(Token)match(input,23,FollowSets000.FOLLOW_16); 

                            								doneLeaf(lv_wildcard_3_0);
                            							

                            								if (!current) {
                            									associateWithSemanticElement();
                            									current = true;
                            								}
                            							

                            }


                            }


                            }
                            break;
                        case 2 :
                            // PsiInternalXtext.g:608:6: (otherlv_4= 'returns' ( (lv_type_5_0= ruleTypeRef ) ) )?
                            {
                            // PsiInternalXtext.g:608:6: (otherlv_4= 'returns' ( (lv_type_5_0= ruleTypeRef ) ) )?
                            int alt14=2;
                            int LA14_0 = input.LA(1);

                            if ( (LA14_0==24) ) {
                                alt14=1;
                            }
                            switch (alt14) {
                                case 1 :
                                    // PsiInternalXtext.g:609:7: otherlv_4= 'returns' ( (lv_type_5_0= ruleTypeRef ) )
                                    {

                                    							markLeaf(elementTypeProvider.getParserRule_ReturnsKeyword_1_0_2_1_0ElementType());
                                    						
                                    otherlv_4=(Token)match(input,24,FollowSets000.FOLLOW_13); 

                                    							doneLeaf(otherlv_4);
                                    						
                                    // PsiInternalXtext.g:616:7: ( (lv_type_5_0= ruleTypeRef ) )
                                    // PsiInternalXtext.g:617:8: (lv_type_5_0= ruleTypeRef )
                                    {
                                    // PsiInternalXtext.g:617:8: (lv_type_5_0= ruleTypeRef )
                                    // PsiInternalXtext.g:618:9: lv_type_5_0= ruleTypeRef
                                    {

                                    									markComposite(elementTypeProvider.getParserRule_TypeTypeRefParserRuleCall_1_0_2_1_1_0ElementType());
                                    								
                                    pushFollow(FollowSets000.FOLLOW_16);
                                    lv_type_5_0=ruleTypeRef();

                                    state._fsp--;


                                    									doneComposite();
                                    									if(!current) {
                                    										associateWithSemanticElement();
                                    										current = true;
                                    									}
                                    								

                                    }


                                    }


                                    }
                                    break;

                            }


                            }
                            break;

                    }


                    }


                    }
                    break;
                case 2 :
                    // PsiInternalXtext.g:635:4: (this_RuleNameAndParams_6= ruleRuleNameAndParams[$current] (otherlv_7= 'returns' ( (lv_type_8_0= ruleTypeRef ) ) )? )
                    {
                    // PsiInternalXtext.g:635:4: (this_RuleNameAndParams_6= ruleRuleNameAndParams[$current] (otherlv_7= 'returns' ( (lv_type_8_0= ruleTypeRef ) ) )? )
                    // PsiInternalXtext.g:636:5: this_RuleNameAndParams_6= ruleRuleNameAndParams[$current] (otherlv_7= 'returns' ( (lv_type_8_0= ruleTypeRef ) ) )?
                    {

                    					if (!current) {
                    						associateWithSemanticElement();
                    						current = true;
                    					}
                    					markComposite(elementTypeProvider.getParserRule_RuleNameAndParamsParserRuleCall_1_1_0ElementType());
                    				
                    pushFollow(FollowSets000.FOLLOW_17);
                    this_RuleNameAndParams_6=ruleRuleNameAndParams(current);

                    state._fsp--;


                    					current = this_RuleNameAndParams_6;
                    					doneComposite();
                    				
                    // PsiInternalXtext.g:648:5: (otherlv_7= 'returns' ( (lv_type_8_0= ruleTypeRef ) ) )?
                    int alt16=2;
                    int LA16_0 = input.LA(1);

                    if ( (LA16_0==24) ) {
                        alt16=1;
                    }
                    switch (alt16) {
                        case 1 :
                            // PsiInternalXtext.g:649:6: otherlv_7= 'returns' ( (lv_type_8_0= ruleTypeRef ) )
                            {

                            						markLeaf(elementTypeProvider.getParserRule_ReturnsKeyword_1_1_1_0ElementType());
                            					
                            otherlv_7=(Token)match(input,24,FollowSets000.FOLLOW_13); 

                            						doneLeaf(otherlv_7);
                            					
                            // PsiInternalXtext.g:656:6: ( (lv_type_8_0= ruleTypeRef ) )
                            // PsiInternalXtext.g:657:7: (lv_type_8_0= ruleTypeRef )
                            {
                            // PsiInternalXtext.g:657:7: (lv_type_8_0= ruleTypeRef )
                            // PsiInternalXtext.g:658:8: lv_type_8_0= ruleTypeRef
                            {

                            								markComposite(elementTypeProvider.getParserRule_TypeTypeRefParserRuleCall_1_1_1_1_0ElementType());
                            							
                            pushFollow(FollowSets000.FOLLOW_16);
                            lv_type_8_0=ruleTypeRef();

                            state._fsp--;


                            								doneComposite();
                            								if(!current) {
                            									associateWithSemanticElement();
                            									current = true;
                            								}
                            							

                            }


                            }


                            }
                            break;

                    }


                    }


                    }
                    break;

            }

            // PsiInternalXtext.g:674:3: ( ( (lv_definesHiddenTokens_9_0= 'hidden' ) ) otherlv_10= '(' ( ( ( ruleRuleID ) ) (otherlv_12= ',' ( ( ruleRuleID ) ) )* )? otherlv_14= ')' )?
            int alt20=2;
            int LA20_0 = input.LA(1);

            if ( (LA20_0==14) ) {
                alt20=1;
            }
            switch (alt20) {
                case 1 :
                    // PsiInternalXtext.g:675:4: ( (lv_definesHiddenTokens_9_0= 'hidden' ) ) otherlv_10= '(' ( ( ( ruleRuleID ) ) (otherlv_12= ',' ( ( ruleRuleID ) ) )* )? otherlv_14= ')'
                    {
                    // PsiInternalXtext.g:675:4: ( (lv_definesHiddenTokens_9_0= 'hidden' ) )
                    // PsiInternalXtext.g:676:5: (lv_definesHiddenTokens_9_0= 'hidden' )
                    {
                    // PsiInternalXtext.g:676:5: (lv_definesHiddenTokens_9_0= 'hidden' )
                    // PsiInternalXtext.g:677:6: lv_definesHiddenTokens_9_0= 'hidden'
                    {

                    						markLeaf(elementTypeProvider.getParserRule_DefinesHiddenTokensHiddenKeyword_2_0_0ElementType());
                    					
                    lv_definesHiddenTokens_9_0=(Token)match(input,14,FollowSets000.FOLLOW_6); 

                    						doneLeaf(lv_definesHiddenTokens_9_0);
                    					

                    						if (!current) {
                    							associateWithSemanticElement();
                    							current = true;
                    						}
                    					

                    }


                    }


                    				markLeaf(elementTypeProvider.getParserRule_LeftParenthesisKeyword_2_1ElementType());
                    			
                    otherlv_10=(Token)match(input,15,FollowSets000.FOLLOW_7); 

                    				doneLeaf(otherlv_10);
                    			
                    // PsiInternalXtext.g:699:4: ( ( ( ruleRuleID ) ) (otherlv_12= ',' ( ( ruleRuleID ) ) )* )?
                    int alt19=2;
                    int LA19_0 = input.LA(1);

                    if ( (LA19_0==RULE_ID||(LA19_0>=39 && LA19_0<=40)) ) {
                        alt19=1;
                    }
                    switch (alt19) {
                        case 1 :
                            // PsiInternalXtext.g:700:5: ( ( ruleRuleID ) ) (otherlv_12= ',' ( ( ruleRuleID ) ) )*
                            {
                            // PsiInternalXtext.g:700:5: ( ( ruleRuleID ) )
                            // PsiInternalXtext.g:701:6: ( ruleRuleID )
                            {
                            // PsiInternalXtext.g:701:6: ( ruleRuleID )
                            // PsiInternalXtext.g:702:7: ruleRuleID
                            {

                            							if (!current) {
                            								associateWithSemanticElement();
                            								current = true;
                            							}
                            						

                            							markComposite(elementTypeProvider.getParserRule_HiddenTokensAbstractRuleCrossReference_2_2_0_0ElementType());
                            						
                            pushFollow(FollowSets000.FOLLOW_8);
                            ruleRuleID();

                            state._fsp--;


                            							doneComposite();
                            						

                            }


                            }

                            // PsiInternalXtext.g:717:5: (otherlv_12= ',' ( ( ruleRuleID ) ) )*
                            loop18:
                            do {
                                int alt18=2;
                                int LA18_0 = input.LA(1);

                                if ( (LA18_0==13) ) {
                                    alt18=1;
                                }


                                switch (alt18) {
                            	case 1 :
                            	    // PsiInternalXtext.g:718:6: otherlv_12= ',' ( ( ruleRuleID ) )
                            	    {

                            	    						markLeaf(elementTypeProvider.getParserRule_CommaKeyword_2_2_1_0ElementType());
                            	    					
                            	    otherlv_12=(Token)match(input,13,FollowSets000.FOLLOW_3); 

                            	    						doneLeaf(otherlv_12);
                            	    					
                            	    // PsiInternalXtext.g:725:6: ( ( ruleRuleID ) )
                            	    // PsiInternalXtext.g:726:7: ( ruleRuleID )
                            	    {
                            	    // PsiInternalXtext.g:726:7: ( ruleRuleID )
                            	    // PsiInternalXtext.g:727:8: ruleRuleID
                            	    {

                            	    								if (!current) {
                            	    									associateWithSemanticElement();
                            	    									current = true;
                            	    								}
                            	    							

                            	    								markComposite(elementTypeProvider.getParserRule_HiddenTokensAbstractRuleCrossReference_2_2_1_1_0ElementType());
                            	    							
                            	    pushFollow(FollowSets000.FOLLOW_8);
                            	    ruleRuleID();

                            	    state._fsp--;


                            	    								doneComposite();
                            	    							

                            	    }


                            	    }


                            	    }
                            	    break;

                            	default :
                            	    break loop18;
                                }
                            } while (true);


                            }
                            break;

                    }


                    				markLeaf(elementTypeProvider.getParserRule_RightParenthesisKeyword_2_3ElementType());
                    			
                    otherlv_14=(Token)match(input,16,FollowSets000.FOLLOW_18); 

                    				doneLeaf(otherlv_14);
                    			

                    }
                    break;

            }


            			markLeaf(elementTypeProvider.getParserRule_ColonKeyword_3ElementType());
            		
            otherlv_15=(Token)match(input,25,FollowSets000.FOLLOW_19); 

            			doneLeaf(otherlv_15);
            		
            // PsiInternalXtext.g:759:3: ( (lv_alternatives_16_0= ruleAlternatives ) )
            // PsiInternalXtext.g:760:4: (lv_alternatives_16_0= ruleAlternatives )
            {
            // PsiInternalXtext.g:760:4: (lv_alternatives_16_0= ruleAlternatives )
            // PsiInternalXtext.g:761:5: lv_alternatives_16_0= ruleAlternatives
            {

            					markComposite(elementTypeProvider.getParserRule_AlternativesAlternativesParserRuleCall_4_0ElementType());
            				
            pushFollow(FollowSets000.FOLLOW_20);
            lv_alternatives_16_0=ruleAlternatives();

            state._fsp--;


            					doneComposite();
            					if(!current) {
            						associateWithSemanticElement();
            						current = true;
            					}
            				

            }


            }


            			markLeaf(elementTypeProvider.getParserRule_SemicolonKeyword_5ElementType());
            		
            otherlv_17=(Token)match(input,26,FollowSets000.FOLLOW_2); 

            			doneLeaf(otherlv_17);
            		

            }


            }

        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
        }
        finally {
        }
        return current;
    }
    // $ANTLR end "ruleParserRule"


    // $ANTLR start "ruleRuleNameAndParams"
    // PsiInternalXtext.g:786:1: ruleRuleNameAndParams[Boolean in_current] returns [Boolean current=in_current] : ( ( (lv_name_0_0= ruleValidID ) ) (otherlv_1= '<' ( ( (lv_parameters_2_0= ruleParameter ) ) (otherlv_3= ',' ( (lv_parameters_4_0= ruleParameter ) ) )* )? otherlv_5= '>' )? ) ;
    public final Boolean ruleRuleNameAndParams(Boolean in_current) throws RecognitionException {
        Boolean current = in_current;

        Token otherlv_1=null;
        Token otherlv_3=null;
        Token otherlv_5=null;
        Boolean lv_name_0_0 = null;

        Boolean lv_parameters_2_0 = null;

        Boolean lv_parameters_4_0 = null;


        try {
            // PsiInternalXtext.g:787:1: ( ( ( (lv_name_0_0= ruleValidID ) ) (otherlv_1= '<' ( ( (lv_parameters_2_0= ruleParameter ) ) (otherlv_3= ',' ( (lv_parameters_4_0= ruleParameter ) ) )* )? otherlv_5= '>' )? ) )
            // PsiInternalXtext.g:788:2: ( ( (lv_name_0_0= ruleValidID ) ) (otherlv_1= '<' ( ( (lv_parameters_2_0= ruleParameter ) ) (otherlv_3= ',' ( (lv_parameters_4_0= ruleParameter ) ) )* )? otherlv_5= '>' )? )
            {
            // PsiInternalXtext.g:788:2: ( ( (lv_name_0_0= ruleValidID ) ) (otherlv_1= '<' ( ( (lv_parameters_2_0= ruleParameter ) ) (otherlv_3= ',' ( (lv_parameters_4_0= ruleParameter ) ) )* )? otherlv_5= '>' )? )
            // PsiInternalXtext.g:789:3: ( (lv_name_0_0= ruleValidID ) ) (otherlv_1= '<' ( ( (lv_parameters_2_0= ruleParameter ) ) (otherlv_3= ',' ( (lv_parameters_4_0= ruleParameter ) ) )* )? otherlv_5= '>' )?
            {
            // PsiInternalXtext.g:789:3: ( (lv_name_0_0= ruleValidID ) )
            // PsiInternalXtext.g:790:4: (lv_name_0_0= ruleValidID )
            {
            // PsiInternalXtext.g:790:4: (lv_name_0_0= ruleValidID )
            // PsiInternalXtext.g:791:5: lv_name_0_0= ruleValidID
            {

            					markComposite(elementTypeProvider.getRuleNameAndParams_NameValidIDParserRuleCall_0_0ElementType());
            				
            pushFollow(FollowSets000.FOLLOW_21);
            lv_name_0_0=ruleValidID();

            state._fsp--;


            					doneComposite();
            					if(!current) {
            						associateWithSemanticElement();
            						current = true;
            					}
            				

            }


            }

            // PsiInternalXtext.g:804:3: (otherlv_1= '<' ( ( (lv_parameters_2_0= ruleParameter ) ) (otherlv_3= ',' ( (lv_parameters_4_0= ruleParameter ) ) )* )? otherlv_5= '>' )?
            int alt23=2;
            int LA23_0 = input.LA(1);

            if ( (LA23_0==27) ) {
                alt23=1;
            }
            switch (alt23) {
                case 1 :
                    // PsiInternalXtext.g:805:4: otherlv_1= '<' ( ( (lv_parameters_2_0= ruleParameter ) ) (otherlv_3= ',' ( (lv_parameters_4_0= ruleParameter ) ) )* )? otherlv_5= '>'
                    {

                    				markLeaf(elementTypeProvider.getRuleNameAndParams_LessThanSignKeyword_1_0ElementType());
                    			
                    otherlv_1=(Token)match(input,27,FollowSets000.FOLLOW_22); 

                    				doneLeaf(otherlv_1);
                    			
                    // PsiInternalXtext.g:812:4: ( ( (lv_parameters_2_0= ruleParameter ) ) (otherlv_3= ',' ( (lv_parameters_4_0= ruleParameter ) ) )* )?
                    int alt22=2;
                    int LA22_0 = input.LA(1);

                    if ( (LA22_0==RULE_ID) ) {
                        alt22=1;
                    }
                    switch (alt22) {
                        case 1 :
                            // PsiInternalXtext.g:813:5: ( (lv_parameters_2_0= ruleParameter ) ) (otherlv_3= ',' ( (lv_parameters_4_0= ruleParameter ) ) )*
                            {
                            // PsiInternalXtext.g:813:5: ( (lv_parameters_2_0= ruleParameter ) )
                            // PsiInternalXtext.g:814:6: (lv_parameters_2_0= ruleParameter )
                            {
                            // PsiInternalXtext.g:814:6: (lv_parameters_2_0= ruleParameter )
                            // PsiInternalXtext.g:815:7: lv_parameters_2_0= ruleParameter
                            {

                            							markComposite(elementTypeProvider.getRuleNameAndParams_ParametersParameterParserRuleCall_1_1_0_0ElementType());
                            						
                            pushFollow(FollowSets000.FOLLOW_23);
                            lv_parameters_2_0=ruleParameter();

                            state._fsp--;


                            							doneComposite();
                            							if(!current) {
                            								associateWithSemanticElement();
                            								current = true;
                            							}
                            						

                            }


                            }

                            // PsiInternalXtext.g:828:5: (otherlv_3= ',' ( (lv_parameters_4_0= ruleParameter ) ) )*
                            loop21:
                            do {
                                int alt21=2;
                                int LA21_0 = input.LA(1);

                                if ( (LA21_0==13) ) {
                                    alt21=1;
                                }


                                switch (alt21) {
                            	case 1 :
                            	    // PsiInternalXtext.g:829:6: otherlv_3= ',' ( (lv_parameters_4_0= ruleParameter ) )
                            	    {

                            	    						markLeaf(elementTypeProvider.getRuleNameAndParams_CommaKeyword_1_1_1_0ElementType());
                            	    					
                            	    otherlv_3=(Token)match(input,13,FollowSets000.FOLLOW_13); 

                            	    						doneLeaf(otherlv_3);
                            	    					
                            	    // PsiInternalXtext.g:836:6: ( (lv_parameters_4_0= ruleParameter ) )
                            	    // PsiInternalXtext.g:837:7: (lv_parameters_4_0= ruleParameter )
                            	    {
                            	    // PsiInternalXtext.g:837:7: (lv_parameters_4_0= ruleParameter )
                            	    // PsiInternalXtext.g:838:8: lv_parameters_4_0= ruleParameter
                            	    {

                            	    								markComposite(elementTypeProvider.getRuleNameAndParams_ParametersParameterParserRuleCall_1_1_1_1_0ElementType());
                            	    							
                            	    pushFollow(FollowSets000.FOLLOW_23);
                            	    lv_parameters_4_0=ruleParameter();

                            	    state._fsp--;


                            	    								doneComposite();
                            	    								if(!current) {
                            	    									associateWithSemanticElement();
                            	    									current = true;
                            	    								}
                            	    							

                            	    }


                            	    }


                            	    }
                            	    break;

                            	default :
                            	    break loop21;
                                }
                            } while (true);


                            }
                            break;

                    }


                    				markLeaf(elementTypeProvider.getRuleNameAndParams_GreaterThanSignKeyword_1_2ElementType());
                    			
                    otherlv_5=(Token)match(input,28,FollowSets000.FOLLOW_2); 

                    				doneLeaf(otherlv_5);
                    			

                    }
                    break;

            }


            }


            }

        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
        }
        finally {
        }
        return current;
    }
    // $ANTLR end "ruleRuleNameAndParams"


    // $ANTLR start "entryRuleParameter"
    // PsiInternalXtext.g:865:1: entryRuleParameter returns [Boolean current=false] : iv_ruleParameter= ruleParameter EOF ;
    public final Boolean entryRuleParameter() throws RecognitionException {
        Boolean current = false;

        Boolean iv_ruleParameter = null;


        try {
            // PsiInternalXtext.g:865:51: (iv_ruleParameter= ruleParameter EOF )
            // PsiInternalXtext.g:866:2: iv_ruleParameter= ruleParameter EOF
            {
             markComposite(elementTypeProvider.getParameterElementType()); 
            pushFollow(FollowSets000.FOLLOW_1);
            iv_ruleParameter=ruleParameter();

            state._fsp--;

             current =iv_ruleParameter; 
            match(input,EOF,FollowSets000.FOLLOW_2); 

            }

        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
        }
        finally {
        }
        return current;
    }
    // $ANTLR end "entryRuleParameter"


    // $ANTLR start "ruleParameter"
    // PsiInternalXtext.g:872:1: ruleParameter returns [Boolean current=false] : ( (lv_name_0_0= RULE_ID ) ) ;
    public final Boolean ruleParameter() throws RecognitionException {
        Boolean current = false;

        Token lv_name_0_0=null;

        try {
            // PsiInternalXtext.g:873:1: ( ( (lv_name_0_0= RULE_ID ) ) )
            // PsiInternalXtext.g:874:2: ( (lv_name_0_0= RULE_ID ) )
            {
            // PsiInternalXtext.g:874:2: ( (lv_name_0_0= RULE_ID ) )
            // PsiInternalXtext.g:875:3: (lv_name_0_0= RULE_ID )
            {
            // PsiInternalXtext.g:875:3: (lv_name_0_0= RULE_ID )
            // PsiInternalXtext.g:876:4: lv_name_0_0= RULE_ID
            {

            				markLeaf(elementTypeProvider.getParameter_NameIDTerminalRuleCall_0ElementType());
            			
            lv_name_0_0=(Token)match(input,RULE_ID,FollowSets000.FOLLOW_2); 

            				if(!current) {
            					associateWithSemanticElement();
            					current = true;
            				}
            			

            				doneLeaf(lv_name_0_0);
            			

            }


            }


            }

        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
        }
        finally {
        }
        return current;
    }
    // $ANTLR end "ruleParameter"


    // $ANTLR start "entryRuleTypeRef"
    // PsiInternalXtext.g:894:1: entryRuleTypeRef returns [Boolean current=false] : iv_ruleTypeRef= ruleTypeRef EOF ;
    public final Boolean entryRuleTypeRef() throws RecognitionException {
        Boolean current = false;

        Boolean iv_ruleTypeRef = null;


        try {
            // PsiInternalXtext.g:894:49: (iv_ruleTypeRef= ruleTypeRef EOF )
            // PsiInternalXtext.g:895:2: iv_ruleTypeRef= ruleTypeRef EOF
            {
             markComposite(elementTypeProvider.getTypeRefElementType()); 
            pushFollow(FollowSets000.FOLLOW_1);
            iv_ruleTypeRef=ruleTypeRef();

            state._fsp--;

             current =iv_ruleTypeRef; 
            match(input,EOF,FollowSets000.FOLLOW_2); 

            }

        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
        }
        finally {
        }
        return current;
    }
    // $ANTLR end "entryRuleTypeRef"


    // $ANTLR start "ruleTypeRef"
    // PsiInternalXtext.g:901:1: ruleTypeRef returns [Boolean current=false] : ( ( ( (otherlv_0= RULE_ID ) ) otherlv_1= '::' )? ( (otherlv_2= RULE_ID ) ) ) ;
    public final Boolean ruleTypeRef() throws RecognitionException {
        Boolean current = false;

        Token otherlv_0=null;
        Token otherlv_1=null;
        Token otherlv_2=null;

        try {
            // PsiInternalXtext.g:902:1: ( ( ( ( (otherlv_0= RULE_ID ) ) otherlv_1= '::' )? ( (otherlv_2= RULE_ID ) ) ) )
            // PsiInternalXtext.g:903:2: ( ( ( (otherlv_0= RULE_ID ) ) otherlv_1= '::' )? ( (otherlv_2= RULE_ID ) ) )
            {
            // PsiInternalXtext.g:903:2: ( ( ( (otherlv_0= RULE_ID ) ) otherlv_1= '::' )? ( (otherlv_2= RULE_ID ) ) )
            // PsiInternalXtext.g:904:3: ( ( (otherlv_0= RULE_ID ) ) otherlv_1= '::' )? ( (otherlv_2= RULE_ID ) )
            {
            // PsiInternalXtext.g:904:3: ( ( (otherlv_0= RULE_ID ) ) otherlv_1= '::' )?
            int alt24=2;
            int LA24_0 = input.LA(1);

            if ( (LA24_0==RULE_ID) ) {
                int LA24_1 = input.LA(2);

                if ( (LA24_1==29) ) {
                    alt24=1;
                }
            }
            switch (alt24) {
                case 1 :
                    // PsiInternalXtext.g:905:4: ( (otherlv_0= RULE_ID ) ) otherlv_1= '::'
                    {
                    // PsiInternalXtext.g:905:4: ( (otherlv_0= RULE_ID ) )
                    // PsiInternalXtext.g:906:5: (otherlv_0= RULE_ID )
                    {
                    // PsiInternalXtext.g:906:5: (otherlv_0= RULE_ID )
                    // PsiInternalXtext.g:907:6: otherlv_0= RULE_ID
                    {

                    						if (!current) {
                    							associateWithSemanticElement();
                    							current = true;
                    						}
                    					

                    						markLeaf(elementTypeProvider.getTypeRef_MetamodelAbstractMetamodelDeclarationCrossReference_0_0_0ElementType());
                    					
                    otherlv_0=(Token)match(input,RULE_ID,FollowSets000.FOLLOW_24); 

                    						doneLeaf(otherlv_0);
                    					

                    }


                    }


                    				markLeaf(elementTypeProvider.getTypeRef_ColonColonKeyword_0_1ElementType());
                    			
                    otherlv_1=(Token)match(input,29,FollowSets000.FOLLOW_13); 

                    				doneLeaf(otherlv_1);
                    			

                    }
                    break;

            }

            // PsiInternalXtext.g:930:3: ( (otherlv_2= RULE_ID ) )
            // PsiInternalXtext.g:931:4: (otherlv_2= RULE_ID )
            {
            // PsiInternalXtext.g:931:4: (otherlv_2= RULE_ID )
            // PsiInternalXtext.g:932:5: otherlv_2= RULE_ID
            {

            					if (!current) {
            						associateWithSemanticElement();
            						current = true;
            					}
            				

            					markLeaf(elementTypeProvider.getTypeRef_ClassifierEClassifierCrossReference_1_0ElementType());
            				
            otherlv_2=(Token)match(input,RULE_ID,FollowSets000.FOLLOW_2); 

            					doneLeaf(otherlv_2);
            				

            }


            }


            }


            }

        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
        }
        finally {
        }
        return current;
    }
    // $ANTLR end "ruleTypeRef"


    // $ANTLR start "entryRuleAlternatives"
    // PsiInternalXtext.g:951:1: entryRuleAlternatives returns [Boolean current=false] : iv_ruleAlternatives= ruleAlternatives EOF ;
    public final Boolean entryRuleAlternatives() throws RecognitionException {
        Boolean current = false;

        Boolean iv_ruleAlternatives = null;


        try {
            // PsiInternalXtext.g:951:54: (iv_ruleAlternatives= ruleAlternatives EOF )
            // PsiInternalXtext.g:952:2: iv_ruleAlternatives= ruleAlternatives EOF
            {
             markComposite(elementTypeProvider.getAlternativesElementType()); 
            pushFollow(FollowSets000.FOLLOW_1);
            iv_ruleAlternatives=ruleAlternatives();

            state._fsp--;

             current =iv_ruleAlternatives; 
            match(input,EOF,FollowSets000.FOLLOW_2); 

            }

        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
        }
        finally {
        }
        return current;
    }
    // $ANTLR end "entryRuleAlternatives"


    // $ANTLR start "ruleAlternatives"
    // PsiInternalXtext.g:958:1: ruleAlternatives returns [Boolean current=false] : (this_ConditionalBranch_0= ruleConditionalBranch ( () (otherlv_2= '|' ( (lv_elements_3_0= ruleConditionalBranch ) ) )+ )? ) ;
    public final Boolean ruleAlternatives() throws RecognitionException {
        Boolean current = false;

        Token otherlv_2=null;
        Boolean this_ConditionalBranch_0 = null;

        Boolean lv_elements_3_0 = null;


        try {
            // PsiInternalXtext.g:959:1: ( (this_ConditionalBranch_0= ruleConditionalBranch ( () (otherlv_2= '|' ( (lv_elements_3_0= ruleConditionalBranch ) ) )+ )? ) )
            // PsiInternalXtext.g:960:2: (this_ConditionalBranch_0= ruleConditionalBranch ( () (otherlv_2= '|' ( (lv_elements_3_0= ruleConditionalBranch ) ) )+ )? )
            {
            // PsiInternalXtext.g:960:2: (this_ConditionalBranch_0= ruleConditionalBranch ( () (otherlv_2= '|' ( (lv_elements_3_0= ruleConditionalBranch ) ) )+ )? )
            // PsiInternalXtext.g:961:3: this_ConditionalBranch_0= ruleConditionalBranch ( () (otherlv_2= '|' ( (lv_elements_3_0= ruleConditionalBranch ) ) )+ )?
            {

            			markComposite(elementTypeProvider.getAlternatives_ConditionalBranchParserRuleCall_0ElementType());
            		
            pushFollow(FollowSets000.FOLLOW_25);
            this_ConditionalBranch_0=ruleConditionalBranch();

            state._fsp--;


            			current = this_ConditionalBranch_0;
            			doneComposite();
            		
            // PsiInternalXtext.g:969:3: ( () (otherlv_2= '|' ( (lv_elements_3_0= ruleConditionalBranch ) ) )+ )?
            int alt26=2;
            int LA26_0 = input.LA(1);

            if ( (LA26_0==30) ) {
                alt26=1;
            }
            switch (alt26) {
                case 1 :
                    // PsiInternalXtext.g:970:4: () (otherlv_2= '|' ( (lv_elements_3_0= ruleConditionalBranch ) ) )+
                    {
                    // PsiInternalXtext.g:970:4: ()
                    // PsiInternalXtext.g:971:5: 
                    {

                    					precedeComposite(elementTypeProvider.getAlternatives_AlternativesElementsAction_1_0ElementType());
                    					doneComposite();
                    					associateWithSemanticElement();
                    				

                    }

                    // PsiInternalXtext.g:977:4: (otherlv_2= '|' ( (lv_elements_3_0= ruleConditionalBranch ) ) )+
                    int cnt25=0;
                    loop25:
                    do {
                        int alt25=2;
                        int LA25_0 = input.LA(1);

                        if ( (LA25_0==30) ) {
                            alt25=1;
                        }


                        switch (alt25) {
                    	case 1 :
                    	    // PsiInternalXtext.g:978:5: otherlv_2= '|' ( (lv_elements_3_0= ruleConditionalBranch ) )
                    	    {

                    	    					markLeaf(elementTypeProvider.getAlternatives_VerticalLineKeyword_1_1_0ElementType());
                    	    				
                    	    otherlv_2=(Token)match(input,30,FollowSets000.FOLLOW_19); 

                    	    					doneLeaf(otherlv_2);
                    	    				
                    	    // PsiInternalXtext.g:985:5: ( (lv_elements_3_0= ruleConditionalBranch ) )
                    	    // PsiInternalXtext.g:986:6: (lv_elements_3_0= ruleConditionalBranch )
                    	    {
                    	    // PsiInternalXtext.g:986:6: (lv_elements_3_0= ruleConditionalBranch )
                    	    // PsiInternalXtext.g:987:7: lv_elements_3_0= ruleConditionalBranch
                    	    {

                    	    							markComposite(elementTypeProvider.getAlternatives_ElementsConditionalBranchParserRuleCall_1_1_1_0ElementType());
                    	    						
                    	    pushFollow(FollowSets000.FOLLOW_25);
                    	    lv_elements_3_0=ruleConditionalBranch();

                    	    state._fsp--;


                    	    							doneComposite();
                    	    							if(!current) {
                    	    								associateWithSemanticElement();
                    	    								current = true;
                    	    							}
                    	    						

                    	    }


                    	    }


                    	    }
                    	    break;

                    	default :
                    	    if ( cnt25 >= 1 ) break loop25;
                                EarlyExitException eee =
                                    new EarlyExitException(25, input);
                                throw eee;
                        }
                        cnt25++;
                    } while (true);


                    }
                    break;

            }


            }


            }

        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
        }
        finally {
        }
        return current;
    }
    // $ANTLR end "ruleAlternatives"


    // $ANTLR start "entryRuleConditionalBranch"
    // PsiInternalXtext.g:1006:1: entryRuleConditionalBranch returns [Boolean current=false] : iv_ruleConditionalBranch= ruleConditionalBranch EOF ;
    public final Boolean entryRuleConditionalBranch() throws RecognitionException {
        Boolean current = false;

        Boolean iv_ruleConditionalBranch = null;


        try {
            // PsiInternalXtext.g:1006:59: (iv_ruleConditionalBranch= ruleConditionalBranch EOF )
            // PsiInternalXtext.g:1007:2: iv_ruleConditionalBranch= ruleConditionalBranch EOF
            {
             markComposite(elementTypeProvider.getConditionalBranchElementType()); 
            pushFollow(FollowSets000.FOLLOW_1);
            iv_ruleConditionalBranch=ruleConditionalBranch();

            state._fsp--;

             current =iv_ruleConditionalBranch; 
            match(input,EOF,FollowSets000.FOLLOW_2); 

            }

        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
        }
        finally {
        }
        return current;
    }
    // $ANTLR end "entryRuleConditionalBranch"


    // $ANTLR start "ruleConditionalBranch"
    // PsiInternalXtext.g:1013:1: ruleConditionalBranch returns [Boolean current=false] : (this_UnorderedGroup_0= ruleUnorderedGroup | ( () otherlv_2= '<' ( (lv_guardCondition_3_0= ruleDisjunction ) ) otherlv_4= '>' ( (lv_elements_5_0= ruleAbstractToken ) )+ ) ) ;
    public final Boolean ruleConditionalBranch() throws RecognitionException {
        Boolean current = false;

        Token otherlv_2=null;
        Token otherlv_4=null;
        Boolean this_UnorderedGroup_0 = null;

        Boolean lv_guardCondition_3_0 = null;

        Boolean lv_elements_5_0 = null;


        try {
            // PsiInternalXtext.g:1014:1: ( (this_UnorderedGroup_0= ruleUnorderedGroup | ( () otherlv_2= '<' ( (lv_guardCondition_3_0= ruleDisjunction ) ) otherlv_4= '>' ( (lv_elements_5_0= ruleAbstractToken ) )+ ) ) )
            // PsiInternalXtext.g:1015:2: (this_UnorderedGroup_0= ruleUnorderedGroup | ( () otherlv_2= '<' ( (lv_guardCondition_3_0= ruleDisjunction ) ) otherlv_4= '>' ( (lv_elements_5_0= ruleAbstractToken ) )+ ) )
            {
            // PsiInternalXtext.g:1015:2: (this_UnorderedGroup_0= ruleUnorderedGroup | ( () otherlv_2= '<' ( (lv_guardCondition_3_0= ruleDisjunction ) ) otherlv_4= '>' ( (lv_elements_5_0= ruleAbstractToken ) )+ ) )
            int alt28=2;
            int LA28_0 = input.LA(1);

            if ( ((LA28_0>=RULE_STRING && LA28_0<=RULE_ID)||LA28_0==15||LA28_0==34||(LA28_0>=39 && LA28_0<=40)||(LA28_0>=42 && LA28_0<=43)) ) {
                alt28=1;
            }
            else if ( (LA28_0==27) ) {
                alt28=2;
            }
            else {
                NoViableAltException nvae =
                    new NoViableAltException("", 28, 0, input);

                throw nvae;
            }
            switch (alt28) {
                case 1 :
                    // PsiInternalXtext.g:1016:3: this_UnorderedGroup_0= ruleUnorderedGroup
                    {

                    			markComposite(elementTypeProvider.getConditionalBranch_UnorderedGroupParserRuleCall_0ElementType());
                    		
                    pushFollow(FollowSets000.FOLLOW_2);
                    this_UnorderedGroup_0=ruleUnorderedGroup();

                    state._fsp--;


                    			current = this_UnorderedGroup_0;
                    			doneComposite();
                    		

                    }
                    break;
                case 2 :
                    // PsiInternalXtext.g:1025:3: ( () otherlv_2= '<' ( (lv_guardCondition_3_0= ruleDisjunction ) ) otherlv_4= '>' ( (lv_elements_5_0= ruleAbstractToken ) )+ )
                    {
                    // PsiInternalXtext.g:1025:3: ( () otherlv_2= '<' ( (lv_guardCondition_3_0= ruleDisjunction ) ) otherlv_4= '>' ( (lv_elements_5_0= ruleAbstractToken ) )+ )
                    // PsiInternalXtext.g:1026:4: () otherlv_2= '<' ( (lv_guardCondition_3_0= ruleDisjunction ) ) otherlv_4= '>' ( (lv_elements_5_0= ruleAbstractToken ) )+
                    {
                    // PsiInternalXtext.g:1026:4: ()
                    // PsiInternalXtext.g:1027:5: 
                    {

                    					precedeComposite(elementTypeProvider.getConditionalBranch_GroupAction_1_0ElementType());
                    					doneComposite();
                    					associateWithSemanticElement();
                    				

                    }


                    				markLeaf(elementTypeProvider.getConditionalBranch_LessThanSignKeyword_1_1ElementType());
                    			
                    otherlv_2=(Token)match(input,27,FollowSets000.FOLLOW_26); 

                    				doneLeaf(otherlv_2);
                    			
                    // PsiInternalXtext.g:1040:4: ( (lv_guardCondition_3_0= ruleDisjunction ) )
                    // PsiInternalXtext.g:1041:5: (lv_guardCondition_3_0= ruleDisjunction )
                    {
                    // PsiInternalXtext.g:1041:5: (lv_guardCondition_3_0= ruleDisjunction )
                    // PsiInternalXtext.g:1042:6: lv_guardCondition_3_0= ruleDisjunction
                    {

                    						markComposite(elementTypeProvider.getConditionalBranch_GuardConditionDisjunctionParserRuleCall_1_2_0ElementType());
                    					
                    pushFollow(FollowSets000.FOLLOW_27);
                    lv_guardCondition_3_0=ruleDisjunction();

                    state._fsp--;


                    						doneComposite();
                    						if(!current) {
                    							associateWithSemanticElement();
                    							current = true;
                    						}
                    					

                    }


                    }


                    				markLeaf(elementTypeProvider.getConditionalBranch_GreaterThanSignKeyword_1_3ElementType());
                    			
                    otherlv_4=(Token)match(input,28,FollowSets000.FOLLOW_28); 

                    				doneLeaf(otherlv_4);
                    			
                    // PsiInternalXtext.g:1062:4: ( (lv_elements_5_0= ruleAbstractToken ) )+
                    int cnt27=0;
                    loop27:
                    do {
                        int alt27=2;
                        int LA27_0 = input.LA(1);

                        if ( ((LA27_0>=RULE_STRING && LA27_0<=RULE_ID)||LA27_0==15||LA27_0==34||(LA27_0>=39 && LA27_0<=40)||(LA27_0>=42 && LA27_0<=43)) ) {
                            alt27=1;
                        }


                        switch (alt27) {
                    	case 1 :
                    	    // PsiInternalXtext.g:1063:5: (lv_elements_5_0= ruleAbstractToken )
                    	    {
                    	    // PsiInternalXtext.g:1063:5: (lv_elements_5_0= ruleAbstractToken )
                    	    // PsiInternalXtext.g:1064:6: lv_elements_5_0= ruleAbstractToken
                    	    {

                    	    						markComposite(elementTypeProvider.getConditionalBranch_ElementsAbstractTokenParserRuleCall_1_4_0ElementType());
                    	    					
                    	    pushFollow(FollowSets000.FOLLOW_29);
                    	    lv_elements_5_0=ruleAbstractToken();

                    	    state._fsp--;


                    	    						doneComposite();
                    	    						if(!current) {
                    	    							associateWithSemanticElement();
                    	    							current = true;
                    	    						}
                    	    					

                    	    }


                    	    }
                    	    break;

                    	default :
                    	    if ( cnt27 >= 1 ) break loop27;
                                EarlyExitException eee =
                                    new EarlyExitException(27, input);
                                throw eee;
                        }
                        cnt27++;
                    } while (true);


                    }


                    }
                    break;

            }


            }

        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
        }
        finally {
        }
        return current;
    }
    // $ANTLR end "ruleConditionalBranch"


    // $ANTLR start "entryRuleUnorderedGroup"
    // PsiInternalXtext.g:1082:1: entryRuleUnorderedGroup returns [Boolean current=false] : iv_ruleUnorderedGroup= ruleUnorderedGroup EOF ;
    public final Boolean entryRuleUnorderedGroup() throws RecognitionException {
        Boolean current = false;

        Boolean iv_ruleUnorderedGroup = null;


        try {
            // PsiInternalXtext.g:1082:56: (iv_ruleUnorderedGroup= ruleUnorderedGroup EOF )
            // PsiInternalXtext.g:1083:2: iv_ruleUnorderedGroup= ruleUnorderedGroup EOF
            {
             markComposite(elementTypeProvider.getUnorderedGroupElementType()); 
            pushFollow(FollowSets000.FOLLOW_1);
            iv_ruleUnorderedGroup=ruleUnorderedGroup();

            state._fsp--;

             current =iv_ruleUnorderedGroup; 
            match(input,EOF,FollowSets000.FOLLOW_2); 

            }

        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
        }
        finally {
        }
        return current;
    }
    // $ANTLR end "entryRuleUnorderedGroup"


    // $ANTLR start "ruleUnorderedGroup"
    // PsiInternalXtext.g:1089:1: ruleUnorderedGroup returns [Boolean current=false] : (this_Group_0= ruleGroup ( () (otherlv_2= '&' ( (lv_elements_3_0= ruleGroup ) ) )+ )? ) ;
    public final Boolean ruleUnorderedGroup() throws RecognitionException {
        Boolean current = false;

        Token otherlv_2=null;
        Boolean this_Group_0 = null;

        Boolean lv_elements_3_0 = null;


        try {
            // PsiInternalXtext.g:1090:1: ( (this_Group_0= ruleGroup ( () (otherlv_2= '&' ( (lv_elements_3_0= ruleGroup ) ) )+ )? ) )
            // PsiInternalXtext.g:1091:2: (this_Group_0= ruleGroup ( () (otherlv_2= '&' ( (lv_elements_3_0= ruleGroup ) ) )+ )? )
            {
            // PsiInternalXtext.g:1091:2: (this_Group_0= ruleGroup ( () (otherlv_2= '&' ( (lv_elements_3_0= ruleGroup ) ) )+ )? )
            // PsiInternalXtext.g:1092:3: this_Group_0= ruleGroup ( () (otherlv_2= '&' ( (lv_elements_3_0= ruleGroup ) ) )+ )?
            {

            			markComposite(elementTypeProvider.getUnorderedGroup_GroupParserRuleCall_0ElementType());
            		
            pushFollow(FollowSets000.FOLLOW_30);
            this_Group_0=ruleGroup();

            state._fsp--;


            			current = this_Group_0;
            			doneComposite();
            		
            // PsiInternalXtext.g:1100:3: ( () (otherlv_2= '&' ( (lv_elements_3_0= ruleGroup ) ) )+ )?
            int alt30=2;
            int LA30_0 = input.LA(1);

            if ( (LA30_0==31) ) {
                alt30=1;
            }
            switch (alt30) {
                case 1 :
                    // PsiInternalXtext.g:1101:4: () (otherlv_2= '&' ( (lv_elements_3_0= ruleGroup ) ) )+
                    {
                    // PsiInternalXtext.g:1101:4: ()
                    // PsiInternalXtext.g:1102:5: 
                    {

                    					precedeComposite(elementTypeProvider.getUnorderedGroup_UnorderedGroupElementsAction_1_0ElementType());
                    					doneComposite();
                    					associateWithSemanticElement();
                    				

                    }

                    // PsiInternalXtext.g:1108:4: (otherlv_2= '&' ( (lv_elements_3_0= ruleGroup ) ) )+
                    int cnt29=0;
                    loop29:
                    do {
                        int alt29=2;
                        int LA29_0 = input.LA(1);

                        if ( (LA29_0==31) ) {
                            alt29=1;
                        }


                        switch (alt29) {
                    	case 1 :
                    	    // PsiInternalXtext.g:1109:5: otherlv_2= '&' ( (lv_elements_3_0= ruleGroup ) )
                    	    {

                    	    					markLeaf(elementTypeProvider.getUnorderedGroup_AmpersandKeyword_1_1_0ElementType());
                    	    				
                    	    otherlv_2=(Token)match(input,31,FollowSets000.FOLLOW_28); 

                    	    					doneLeaf(otherlv_2);
                    	    				
                    	    // PsiInternalXtext.g:1116:5: ( (lv_elements_3_0= ruleGroup ) )
                    	    // PsiInternalXtext.g:1117:6: (lv_elements_3_0= ruleGroup )
                    	    {
                    	    // PsiInternalXtext.g:1117:6: (lv_elements_3_0= ruleGroup )
                    	    // PsiInternalXtext.g:1118:7: lv_elements_3_0= ruleGroup
                    	    {

                    	    							markComposite(elementTypeProvider.getUnorderedGroup_ElementsGroupParserRuleCall_1_1_1_0ElementType());
                    	    						
                    	    pushFollow(FollowSets000.FOLLOW_30);
                    	    lv_elements_3_0=ruleGroup();

                    	    state._fsp--;


                    	    							doneComposite();
                    	    							if(!current) {
                    	    								associateWithSemanticElement();
                    	    								current = true;
                    	    							}
                    	    						

                    	    }


                    	    }


                    	    }
                    	    break;

                    	default :
                    	    if ( cnt29 >= 1 ) break loop29;
                                EarlyExitException eee =
                                    new EarlyExitException(29, input);
                                throw eee;
                        }
                        cnt29++;
                    } while (true);


                    }
                    break;

            }


            }


            }

        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
        }
        finally {
        }
        return current;
    }
    // $ANTLR end "ruleUnorderedGroup"


    // $ANTLR start "entryRuleGroup"
    // PsiInternalXtext.g:1137:1: entryRuleGroup returns [Boolean current=false] : iv_ruleGroup= ruleGroup EOF ;
    public final Boolean entryRuleGroup() throws RecognitionException {
        Boolean current = false;

        Boolean iv_ruleGroup = null;


        try {
            // PsiInternalXtext.g:1137:47: (iv_ruleGroup= ruleGroup EOF )
            // PsiInternalXtext.g:1138:2: iv_ruleGroup= ruleGroup EOF
            {
             markComposite(elementTypeProvider.getGroupElementType()); 
            pushFollow(FollowSets000.FOLLOW_1);
            iv_ruleGroup=ruleGroup();

            state._fsp--;

             current =iv_ruleGroup; 
            match(input,EOF,FollowSets000.FOLLOW_2); 

            }

        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
        }
        finally {
        }
        return current;
    }
    // $ANTLR end "entryRuleGroup"


    // $ANTLR start "ruleGroup"
    // PsiInternalXtext.g:1144:1: ruleGroup returns [Boolean current=false] : (this_AbstractToken_0= ruleAbstractToken ( () ( (lv_elements_2_0= ruleAbstractToken ) )+ )? ) ;
    public final Boolean ruleGroup() throws RecognitionException {
        Boolean current = false;

        Boolean this_AbstractToken_0 = null;

        Boolean lv_elements_2_0 = null;


        try {
            // PsiInternalXtext.g:1145:1: ( (this_AbstractToken_0= ruleAbstractToken ( () ( (lv_elements_2_0= ruleAbstractToken ) )+ )? ) )
            // PsiInternalXtext.g:1146:2: (this_AbstractToken_0= ruleAbstractToken ( () ( (lv_elements_2_0= ruleAbstractToken ) )+ )? )
            {
            // PsiInternalXtext.g:1146:2: (this_AbstractToken_0= ruleAbstractToken ( () ( (lv_elements_2_0= ruleAbstractToken ) )+ )? )
            // PsiInternalXtext.g:1147:3: this_AbstractToken_0= ruleAbstractToken ( () ( (lv_elements_2_0= ruleAbstractToken ) )+ )?
            {

            			markComposite(elementTypeProvider.getGroup_AbstractTokenParserRuleCall_0ElementType());
            		
            pushFollow(FollowSets000.FOLLOW_29);
            this_AbstractToken_0=ruleAbstractToken();

            state._fsp--;


            			current = this_AbstractToken_0;
            			doneComposite();
            		
            // PsiInternalXtext.g:1155:3: ( () ( (lv_elements_2_0= ruleAbstractToken ) )+ )?
            int alt32=2;
            int LA32_0 = input.LA(1);

            if ( ((LA32_0>=RULE_STRING && LA32_0<=RULE_ID)||LA32_0==15||LA32_0==34||(LA32_0>=39 && LA32_0<=40)||(LA32_0>=42 && LA32_0<=43)) ) {
                alt32=1;
            }
            switch (alt32) {
                case 1 :
                    // PsiInternalXtext.g:1156:4: () ( (lv_elements_2_0= ruleAbstractToken ) )+
                    {
                    // PsiInternalXtext.g:1156:4: ()
                    // PsiInternalXtext.g:1157:5: 
                    {

                    					precedeComposite(elementTypeProvider.getGroup_GroupElementsAction_1_0ElementType());
                    					doneComposite();
                    					associateWithSemanticElement();
                    				

                    }

                    // PsiInternalXtext.g:1163:4: ( (lv_elements_2_0= ruleAbstractToken ) )+
                    int cnt31=0;
                    loop31:
                    do {
                        int alt31=2;
                        int LA31_0 = input.LA(1);

                        if ( ((LA31_0>=RULE_STRING && LA31_0<=RULE_ID)||LA31_0==15||LA31_0==34||(LA31_0>=39 && LA31_0<=40)||(LA31_0>=42 && LA31_0<=43)) ) {
                            alt31=1;
                        }


                        switch (alt31) {
                    	case 1 :
                    	    // PsiInternalXtext.g:1164:5: (lv_elements_2_0= ruleAbstractToken )
                    	    {
                    	    // PsiInternalXtext.g:1164:5: (lv_elements_2_0= ruleAbstractToken )
                    	    // PsiInternalXtext.g:1165:6: lv_elements_2_0= ruleAbstractToken
                    	    {

                    	    						markComposite(elementTypeProvider.getGroup_ElementsAbstractTokenParserRuleCall_1_1_0ElementType());
                    	    					
                    	    pushFollow(FollowSets000.FOLLOW_29);
                    	    lv_elements_2_0=ruleAbstractToken();

                    	    state._fsp--;


                    	    						doneComposite();
                    	    						if(!current) {
                    	    							associateWithSemanticElement();
                    	    							current = true;
                    	    						}
                    	    					

                    	    }


                    	    }
                    	    break;

                    	default :
                    	    if ( cnt31 >= 1 ) break loop31;
                                EarlyExitException eee =
                                    new EarlyExitException(31, input);
                                throw eee;
                        }
                        cnt31++;
                    } while (true);


                    }
                    break;

            }


            }


            }

        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
        }
        finally {
        }
        return current;
    }
    // $ANTLR end "ruleGroup"


    // $ANTLR start "entryRuleAbstractToken"
    // PsiInternalXtext.g:1183:1: entryRuleAbstractToken returns [Boolean current=false] : iv_ruleAbstractToken= ruleAbstractToken EOF ;
    public final Boolean entryRuleAbstractToken() throws RecognitionException {
        Boolean current = false;

        Boolean iv_ruleAbstractToken = null;


        try {
            // PsiInternalXtext.g:1183:55: (iv_ruleAbstractToken= ruleAbstractToken EOF )
            // PsiInternalXtext.g:1184:2: iv_ruleAbstractToken= ruleAbstractToken EOF
            {
             markComposite(elementTypeProvider.getAbstractTokenElementType()); 
            pushFollow(FollowSets000.FOLLOW_1);
            iv_ruleAbstractToken=ruleAbstractToken();

            state._fsp--;

             current =iv_ruleAbstractToken; 
            match(input,EOF,FollowSets000.FOLLOW_2); 

            }

        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
        }
        finally {
        }
        return current;
    }
    // $ANTLR end "entryRuleAbstractToken"


    // $ANTLR start "ruleAbstractToken"
    // PsiInternalXtext.g:1190:1: ruleAbstractToken returns [Boolean current=false] : (this_AbstractTokenWithCardinality_0= ruleAbstractTokenWithCardinality | this_Action_1= ruleAction ) ;
    public final Boolean ruleAbstractToken() throws RecognitionException {
        Boolean current = false;

        Boolean this_AbstractTokenWithCardinality_0 = null;

        Boolean this_Action_1 = null;


        try {
            // PsiInternalXtext.g:1191:1: ( (this_AbstractTokenWithCardinality_0= ruleAbstractTokenWithCardinality | this_Action_1= ruleAction ) )
            // PsiInternalXtext.g:1192:2: (this_AbstractTokenWithCardinality_0= ruleAbstractTokenWithCardinality | this_Action_1= ruleAction )
            {
            // PsiInternalXtext.g:1192:2: (this_AbstractTokenWithCardinality_0= ruleAbstractTokenWithCardinality | this_Action_1= ruleAction )
            int alt33=2;
            int LA33_0 = input.LA(1);

            if ( ((LA33_0>=RULE_STRING && LA33_0<=RULE_ID)||LA33_0==15||(LA33_0>=39 && LA33_0<=40)||(LA33_0>=42 && LA33_0<=43)) ) {
                alt33=1;
            }
            else if ( (LA33_0==34) ) {
                alt33=2;
            }
            else {
                NoViableAltException nvae =
                    new NoViableAltException("", 33, 0, input);

                throw nvae;
            }
            switch (alt33) {
                case 1 :
                    // PsiInternalXtext.g:1193:3: this_AbstractTokenWithCardinality_0= ruleAbstractTokenWithCardinality
                    {

                    			markComposite(elementTypeProvider.getAbstractToken_AbstractTokenWithCardinalityParserRuleCall_0ElementType());
                    		
                    pushFollow(FollowSets000.FOLLOW_2);
                    this_AbstractTokenWithCardinality_0=ruleAbstractTokenWithCardinality();

                    state._fsp--;


                    			current = this_AbstractTokenWithCardinality_0;
                    			doneComposite();
                    		

                    }
                    break;
                case 2 :
                    // PsiInternalXtext.g:1202:3: this_Action_1= ruleAction
                    {

                    			markComposite(elementTypeProvider.getAbstractToken_ActionParserRuleCall_1ElementType());
                    		
                    pushFollow(FollowSets000.FOLLOW_2);
                    this_Action_1=ruleAction();

                    state._fsp--;


                    			current = this_Action_1;
                    			doneComposite();
                    		

                    }
                    break;

            }


            }

        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
        }
        finally {
        }
        return current;
    }
    // $ANTLR end "ruleAbstractToken"


    // $ANTLR start "entryRuleAbstractTokenWithCardinality"
    // PsiInternalXtext.g:1214:1: entryRuleAbstractTokenWithCardinality returns [Boolean current=false] : iv_ruleAbstractTokenWithCardinality= ruleAbstractTokenWithCardinality EOF ;
    public final Boolean entryRuleAbstractTokenWithCardinality() throws RecognitionException {
        Boolean current = false;

        Boolean iv_ruleAbstractTokenWithCardinality = null;


        try {
            // PsiInternalXtext.g:1214:70: (iv_ruleAbstractTokenWithCardinality= ruleAbstractTokenWithCardinality EOF )
            // PsiInternalXtext.g:1215:2: iv_ruleAbstractTokenWithCardinality= ruleAbstractTokenWithCardinality EOF
            {
             markComposite(elementTypeProvider.getAbstractTokenWithCardinalityElementType()); 
            pushFollow(FollowSets000.FOLLOW_1);
            iv_ruleAbstractTokenWithCardinality=ruleAbstractTokenWithCardinality();

            state._fsp--;

             current =iv_ruleAbstractTokenWithCardinality; 
            match(input,EOF,FollowSets000.FOLLOW_2); 

            }

        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
        }
        finally {
        }
        return current;
    }
    // $ANTLR end "entryRuleAbstractTokenWithCardinality"


    // $ANTLR start "ruleAbstractTokenWithCardinality"
    // PsiInternalXtext.g:1221:1: ruleAbstractTokenWithCardinality returns [Boolean current=false] : ( (this_Assignment_0= ruleAssignment | this_AbstractTerminal_1= ruleAbstractTerminal ) ( ( (lv_cardinality_2_1= '?' | lv_cardinality_2_2= '*' | lv_cardinality_2_3= '+' ) ) )? ) ;
    public final Boolean ruleAbstractTokenWithCardinality() throws RecognitionException {
        Boolean current = false;

        Token lv_cardinality_2_1=null;
        Token lv_cardinality_2_2=null;
        Token lv_cardinality_2_3=null;
        Boolean this_Assignment_0 = null;

        Boolean this_AbstractTerminal_1 = null;


        try {
            // PsiInternalXtext.g:1222:1: ( ( (this_Assignment_0= ruleAssignment | this_AbstractTerminal_1= ruleAbstractTerminal ) ( ( (lv_cardinality_2_1= '?' | lv_cardinality_2_2= '*' | lv_cardinality_2_3= '+' ) ) )? ) )
            // PsiInternalXtext.g:1223:2: ( (this_Assignment_0= ruleAssignment | this_AbstractTerminal_1= ruleAbstractTerminal ) ( ( (lv_cardinality_2_1= '?' | lv_cardinality_2_2= '*' | lv_cardinality_2_3= '+' ) ) )? )
            {
            // PsiInternalXtext.g:1223:2: ( (this_Assignment_0= ruleAssignment | this_AbstractTerminal_1= ruleAbstractTerminal ) ( ( (lv_cardinality_2_1= '?' | lv_cardinality_2_2= '*' | lv_cardinality_2_3= '+' ) ) )? )
            // PsiInternalXtext.g:1224:3: (this_Assignment_0= ruleAssignment | this_AbstractTerminal_1= ruleAbstractTerminal ) ( ( (lv_cardinality_2_1= '?' | lv_cardinality_2_2= '*' | lv_cardinality_2_3= '+' ) ) )?
            {
            // PsiInternalXtext.g:1224:3: (this_Assignment_0= ruleAssignment | this_AbstractTerminal_1= ruleAbstractTerminal )
            int alt34=2;
            alt34 = dfa34.predict(input);
            switch (alt34) {
                case 1 :
                    // PsiInternalXtext.g:1225:4: this_Assignment_0= ruleAssignment
                    {

                    				markComposite(elementTypeProvider.getAbstractTokenWithCardinality_AssignmentParserRuleCall_0_0ElementType());
                    			
                    pushFollow(FollowSets000.FOLLOW_31);
                    this_Assignment_0=ruleAssignment();

                    state._fsp--;


                    				current = this_Assignment_0;
                    				doneComposite();
                    			

                    }
                    break;
                case 2 :
                    // PsiInternalXtext.g:1234:4: this_AbstractTerminal_1= ruleAbstractTerminal
                    {

                    				markComposite(elementTypeProvider.getAbstractTokenWithCardinality_AbstractTerminalParserRuleCall_0_1ElementType());
                    			
                    pushFollow(FollowSets000.FOLLOW_31);
                    this_AbstractTerminal_1=ruleAbstractTerminal();

                    state._fsp--;


                    				current = this_AbstractTerminal_1;
                    				doneComposite();
                    			

                    }
                    break;

            }

            // PsiInternalXtext.g:1243:3: ( ( (lv_cardinality_2_1= '?' | lv_cardinality_2_2= '*' | lv_cardinality_2_3= '+' ) ) )?
            int alt36=2;
            int LA36_0 = input.LA(1);

            if ( (LA36_0==23||(LA36_0>=32 && LA36_0<=33)) ) {
                alt36=1;
            }
            switch (alt36) {
                case 1 :
                    // PsiInternalXtext.g:1244:4: ( (lv_cardinality_2_1= '?' | lv_cardinality_2_2= '*' | lv_cardinality_2_3= '+' ) )
                    {
                    // PsiInternalXtext.g:1244:4: ( (lv_cardinality_2_1= '?' | lv_cardinality_2_2= '*' | lv_cardinality_2_3= '+' ) )
                    // PsiInternalXtext.g:1245:5: (lv_cardinality_2_1= '?' | lv_cardinality_2_2= '*' | lv_cardinality_2_3= '+' )
                    {
                    // PsiInternalXtext.g:1245:5: (lv_cardinality_2_1= '?' | lv_cardinality_2_2= '*' | lv_cardinality_2_3= '+' )
                    int alt35=3;
                    switch ( input.LA(1) ) {
                    case 32:
                        {
                        alt35=1;
                        }
                        break;
                    case 23:
                        {
                        alt35=2;
                        }
                        break;
                    case 33:
                        {
                        alt35=3;
                        }
                        break;
                    default:
                        NoViableAltException nvae =
                            new NoViableAltException("", 35, 0, input);

                        throw nvae;
                    }

                    switch (alt35) {
                        case 1 :
                            // PsiInternalXtext.g:1246:6: lv_cardinality_2_1= '?'
                            {

                            						markLeaf(elementTypeProvider.getAbstractTokenWithCardinality_CardinalityQuestionMarkKeyword_1_0_0ElementType());
                            					
                            lv_cardinality_2_1=(Token)match(input,32,FollowSets000.FOLLOW_2); 

                            						doneLeaf(lv_cardinality_2_1);
                            					

                            						if (!current) {
                            							associateWithSemanticElement();
                            							current = true;
                            						}
                            					

                            }
                            break;
                        case 2 :
                            // PsiInternalXtext.g:1260:6: lv_cardinality_2_2= '*'
                            {

                            						markLeaf(elementTypeProvider.getAbstractTokenWithCardinality_CardinalityAsteriskKeyword_1_0_1ElementType());
                            					
                            lv_cardinality_2_2=(Token)match(input,23,FollowSets000.FOLLOW_2); 

                            						doneLeaf(lv_cardinality_2_2);
                            					

                            						if (!current) {
                            							associateWithSemanticElement();
                            							current = true;
                            						}
                            					

                            }
                            break;
                        case 3 :
                            // PsiInternalXtext.g:1274:6: lv_cardinality_2_3= '+'
                            {

                            						markLeaf(elementTypeProvider.getAbstractTokenWithCardinality_CardinalityPlusSignKeyword_1_0_2ElementType());
                            					
                            lv_cardinality_2_3=(Token)match(input,33,FollowSets000.FOLLOW_2); 

                            						doneLeaf(lv_cardinality_2_3);
                            					

                            						if (!current) {
                            							associateWithSemanticElement();
                            							current = true;
                            						}
                            					

                            }
                            break;

                    }


                    }


                    }
                    break;

            }


            }


            }

        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
        }
        finally {
        }
        return current;
    }
    // $ANTLR end "ruleAbstractTokenWithCardinality"


    // $ANTLR start "entryRuleAction"
    // PsiInternalXtext.g:1294:1: entryRuleAction returns [Boolean current=false] : iv_ruleAction= ruleAction EOF ;
    public final Boolean entryRuleAction() throws RecognitionException {
        Boolean current = false;

        Boolean iv_ruleAction = null;


        try {
            // PsiInternalXtext.g:1294:48: (iv_ruleAction= ruleAction EOF )
            // PsiInternalXtext.g:1295:2: iv_ruleAction= ruleAction EOF
            {
             markComposite(elementTypeProvider.getActionElementType()); 
            pushFollow(FollowSets000.FOLLOW_1);
            iv_ruleAction=ruleAction();

            state._fsp--;

             current =iv_ruleAction; 
            match(input,EOF,FollowSets000.FOLLOW_2); 

            }

        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
        }
        finally {
        }
        return current;
    }
    // $ANTLR end "entryRuleAction"


    // $ANTLR start "ruleAction"
    // PsiInternalXtext.g:1301:1: ruleAction returns [Boolean current=false] : (otherlv_0= '{' ( (lv_type_1_0= ruleTypeRef ) ) (otherlv_2= '.' ( (lv_feature_3_0= ruleValidID ) ) ( ( (lv_operator_4_1= '=' | lv_operator_4_2= '+=' ) ) ) otherlv_5= 'current' )? otherlv_6= '}' ) ;
    public final Boolean ruleAction() throws RecognitionException {
        Boolean current = false;

        Token otherlv_0=null;
        Token otherlv_2=null;
        Token lv_operator_4_1=null;
        Token lv_operator_4_2=null;
        Token otherlv_5=null;
        Token otherlv_6=null;
        Boolean lv_type_1_0 = null;

        Boolean lv_feature_3_0 = null;


        try {
            // PsiInternalXtext.g:1302:1: ( (otherlv_0= '{' ( (lv_type_1_0= ruleTypeRef ) ) (otherlv_2= '.' ( (lv_feature_3_0= ruleValidID ) ) ( ( (lv_operator_4_1= '=' | lv_operator_4_2= '+=' ) ) ) otherlv_5= 'current' )? otherlv_6= '}' ) )
            // PsiInternalXtext.g:1303:2: (otherlv_0= '{' ( (lv_type_1_0= ruleTypeRef ) ) (otherlv_2= '.' ( (lv_feature_3_0= ruleValidID ) ) ( ( (lv_operator_4_1= '=' | lv_operator_4_2= '+=' ) ) ) otherlv_5= 'current' )? otherlv_6= '}' )
            {
            // PsiInternalXtext.g:1303:2: (otherlv_0= '{' ( (lv_type_1_0= ruleTypeRef ) ) (otherlv_2= '.' ( (lv_feature_3_0= ruleValidID ) ) ( ( (lv_operator_4_1= '=' | lv_operator_4_2= '+=' ) ) ) otherlv_5= 'current' )? otherlv_6= '}' )
            // PsiInternalXtext.g:1304:3: otherlv_0= '{' ( (lv_type_1_0= ruleTypeRef ) ) (otherlv_2= '.' ( (lv_feature_3_0= ruleValidID ) ) ( ( (lv_operator_4_1= '=' | lv_operator_4_2= '+=' ) ) ) otherlv_5= 'current' )? otherlv_6= '}'
            {

            			markLeaf(elementTypeProvider.getAction_LeftCurlyBracketKeyword_0ElementType());
            		
            otherlv_0=(Token)match(input,34,FollowSets000.FOLLOW_13); 

            			doneLeaf(otherlv_0);
            		
            // PsiInternalXtext.g:1311:3: ( (lv_type_1_0= ruleTypeRef ) )
            // PsiInternalXtext.g:1312:4: (lv_type_1_0= ruleTypeRef )
            {
            // PsiInternalXtext.g:1312:4: (lv_type_1_0= ruleTypeRef )
            // PsiInternalXtext.g:1313:5: lv_type_1_0= ruleTypeRef
            {

            					markComposite(elementTypeProvider.getAction_TypeTypeRefParserRuleCall_1_0ElementType());
            				
            pushFollow(FollowSets000.FOLLOW_32);
            lv_type_1_0=ruleTypeRef();

            state._fsp--;


            					doneComposite();
            					if(!current) {
            						associateWithSemanticElement();
            						current = true;
            					}
            				

            }


            }

            // PsiInternalXtext.g:1326:3: (otherlv_2= '.' ( (lv_feature_3_0= ruleValidID ) ) ( ( (lv_operator_4_1= '=' | lv_operator_4_2= '+=' ) ) ) otherlv_5= 'current' )?
            int alt38=2;
            int LA38_0 = input.LA(1);

            if ( (LA38_0==17) ) {
                alt38=1;
            }
            switch (alt38) {
                case 1 :
                    // PsiInternalXtext.g:1327:4: otherlv_2= '.' ( (lv_feature_3_0= ruleValidID ) ) ( ( (lv_operator_4_1= '=' | lv_operator_4_2= '+=' ) ) ) otherlv_5= 'current'
                    {

                    				markLeaf(elementTypeProvider.getAction_FullStopKeyword_2_0ElementType());
                    			
                    otherlv_2=(Token)match(input,17,FollowSets000.FOLLOW_3); 

                    				doneLeaf(otherlv_2);
                    			
                    // PsiInternalXtext.g:1334:4: ( (lv_feature_3_0= ruleValidID ) )
                    // PsiInternalXtext.g:1335:5: (lv_feature_3_0= ruleValidID )
                    {
                    // PsiInternalXtext.g:1335:5: (lv_feature_3_0= ruleValidID )
                    // PsiInternalXtext.g:1336:6: lv_feature_3_0= ruleValidID
                    {

                    						markComposite(elementTypeProvider.getAction_FeatureValidIDParserRuleCall_2_1_0ElementType());
                    					
                    pushFollow(FollowSets000.FOLLOW_33);
                    lv_feature_3_0=ruleValidID();

                    state._fsp--;


                    						doneComposite();
                    						if(!current) {
                    							associateWithSemanticElement();
                    							current = true;
                    						}
                    					

                    }


                    }

                    // PsiInternalXtext.g:1349:4: ( ( (lv_operator_4_1= '=' | lv_operator_4_2= '+=' ) ) )
                    // PsiInternalXtext.g:1350:5: ( (lv_operator_4_1= '=' | lv_operator_4_2= '+=' ) )
                    {
                    // PsiInternalXtext.g:1350:5: ( (lv_operator_4_1= '=' | lv_operator_4_2= '+=' ) )
                    // PsiInternalXtext.g:1351:6: (lv_operator_4_1= '=' | lv_operator_4_2= '+=' )
                    {
                    // PsiInternalXtext.g:1351:6: (lv_operator_4_1= '=' | lv_operator_4_2= '+=' )
                    int alt37=2;
                    int LA37_0 = input.LA(1);

                    if ( (LA37_0==35) ) {
                        alt37=1;
                    }
                    else if ( (LA37_0==36) ) {
                        alt37=2;
                    }
                    else {
                        NoViableAltException nvae =
                            new NoViableAltException("", 37, 0, input);

                        throw nvae;
                    }
                    switch (alt37) {
                        case 1 :
                            // PsiInternalXtext.g:1352:7: lv_operator_4_1= '='
                            {

                            							markLeaf(elementTypeProvider.getAction_OperatorEqualsSignKeyword_2_2_0_0ElementType());
                            						
                            lv_operator_4_1=(Token)match(input,35,FollowSets000.FOLLOW_34); 

                            							doneLeaf(lv_operator_4_1);
                            						

                            							if (!current) {
                            								associateWithSemanticElement();
                            								current = true;
                            							}
                            						

                            }
                            break;
                        case 2 :
                            // PsiInternalXtext.g:1366:7: lv_operator_4_2= '+='
                            {

                            							markLeaf(elementTypeProvider.getAction_OperatorPlusSignEqualsSignKeyword_2_2_0_1ElementType());
                            						
                            lv_operator_4_2=(Token)match(input,36,FollowSets000.FOLLOW_34); 

                            							doneLeaf(lv_operator_4_2);
                            						

                            							if (!current) {
                            								associateWithSemanticElement();
                            								current = true;
                            							}
                            						

                            }
                            break;

                    }


                    }


                    }


                    				markLeaf(elementTypeProvider.getAction_CurrentKeyword_2_3ElementType());
                    			
                    otherlv_5=(Token)match(input,37,FollowSets000.FOLLOW_35); 

                    				doneLeaf(otherlv_5);
                    			

                    }
                    break;

            }


            			markLeaf(elementTypeProvider.getAction_RightCurlyBracketKeyword_3ElementType());
            		
            otherlv_6=(Token)match(input,38,FollowSets000.FOLLOW_2); 

            			doneLeaf(otherlv_6);
            		

            }


            }

        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
        }
        finally {
        }
        return current;
    }
    // $ANTLR end "ruleAction"


    // $ANTLR start "entryRuleAbstractTerminal"
    // PsiInternalXtext.g:1401:1: entryRuleAbstractTerminal returns [Boolean current=false] : iv_ruleAbstractTerminal= ruleAbstractTerminal EOF ;
    public final Boolean entryRuleAbstractTerminal() throws RecognitionException {
        Boolean current = false;

        Boolean iv_ruleAbstractTerminal = null;


        try {
            // PsiInternalXtext.g:1401:58: (iv_ruleAbstractTerminal= ruleAbstractTerminal EOF )
            // PsiInternalXtext.g:1402:2: iv_ruleAbstractTerminal= ruleAbstractTerminal EOF
            {
             markComposite(elementTypeProvider.getAbstractTerminalElementType()); 
            pushFollow(FollowSets000.FOLLOW_1);
            iv_ruleAbstractTerminal=ruleAbstractTerminal();

            state._fsp--;

             current =iv_ruleAbstractTerminal; 
            match(input,EOF,FollowSets000.FOLLOW_2); 

            }

        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
        }
        finally {
        }
        return current;
    }
    // $ANTLR end "entryRuleAbstractTerminal"


    // $ANTLR start "ruleAbstractTerminal"
    // PsiInternalXtext.g:1408:1: ruleAbstractTerminal returns [Boolean current=false] : (this_Keyword_0= ruleKeyword | this_RuleCall_1= ruleRuleCall | this_ParenthesizedElement_2= ruleParenthesizedElement | this_PredicatedKeyword_3= rulePredicatedKeyword | this_PredicatedRuleCall_4= rulePredicatedRuleCall | this_PredicatedGroup_5= rulePredicatedGroup ) ;
    public final Boolean ruleAbstractTerminal() throws RecognitionException {
        Boolean current = false;

        Boolean this_Keyword_0 = null;

        Boolean this_RuleCall_1 = null;

        Boolean this_ParenthesizedElement_2 = null;

        Boolean this_PredicatedKeyword_3 = null;

        Boolean this_PredicatedRuleCall_4 = null;

        Boolean this_PredicatedGroup_5 = null;


        try {
            // PsiInternalXtext.g:1409:1: ( (this_Keyword_0= ruleKeyword | this_RuleCall_1= ruleRuleCall | this_ParenthesizedElement_2= ruleParenthesizedElement | this_PredicatedKeyword_3= rulePredicatedKeyword | this_PredicatedRuleCall_4= rulePredicatedRuleCall | this_PredicatedGroup_5= rulePredicatedGroup ) )
            // PsiInternalXtext.g:1410:2: (this_Keyword_0= ruleKeyword | this_RuleCall_1= ruleRuleCall | this_ParenthesizedElement_2= ruleParenthesizedElement | this_PredicatedKeyword_3= rulePredicatedKeyword | this_PredicatedRuleCall_4= rulePredicatedRuleCall | this_PredicatedGroup_5= rulePredicatedGroup )
            {
            // PsiInternalXtext.g:1410:2: (this_Keyword_0= ruleKeyword | this_RuleCall_1= ruleRuleCall | this_ParenthesizedElement_2= ruleParenthesizedElement | this_PredicatedKeyword_3= rulePredicatedKeyword | this_PredicatedRuleCall_4= rulePredicatedRuleCall | this_PredicatedGroup_5= rulePredicatedGroup )
            int alt39=6;
            switch ( input.LA(1) ) {
            case RULE_STRING:
                {
                alt39=1;
                }
                break;
            case RULE_ID:
            case 39:
            case 40:
                {
                alt39=2;
                }
                break;
            case 15:
                {
                alt39=3;
                }
                break;
            case 42:
                {
                switch ( input.LA(2) ) {
                case RULE_STRING:
                    {
                    alt39=4;
                    }
                    break;
                case RULE_ID:
                case 39:
                case 40:
                    {
                    alt39=5;
                    }
                    break;
                case 15:
                    {
                    alt39=6;
                    }
                    break;
                default:
                    NoViableAltException nvae =
                        new NoViableAltException("", 39, 4, input);

                    throw nvae;
                }

                }
                break;
            case 43:
                {
                switch ( input.LA(2) ) {
                case RULE_STRING:
                    {
                    alt39=4;
                    }
                    break;
                case RULE_ID:
                case 39:
                case 40:
                    {
                    alt39=5;
                    }
                    break;
                case 15:
                    {
                    alt39=6;
                    }
                    break;
                default:
                    NoViableAltException nvae =
                        new NoViableAltException("", 39, 5, input);

                    throw nvae;
                }

                }
                break;
            default:
                NoViableAltException nvae =
                    new NoViableAltException("", 39, 0, input);

                throw nvae;
            }

            switch (alt39) {
                case 1 :
                    // PsiInternalXtext.g:1411:3: this_Keyword_0= ruleKeyword
                    {

                    			markComposite(elementTypeProvider.getAbstractTerminal_KeywordParserRuleCall_0ElementType());
                    		
                    pushFollow(FollowSets000.FOLLOW_2);
                    this_Keyword_0=ruleKeyword();

                    state._fsp--;


                    			current = this_Keyword_0;
                    			doneComposite();
                    		

                    }
                    break;
                case 2 :
                    // PsiInternalXtext.g:1420:3: this_RuleCall_1= ruleRuleCall
                    {

                    			markComposite(elementTypeProvider.getAbstractTerminal_RuleCallParserRuleCall_1ElementType());
                    		
                    pushFollow(FollowSets000.FOLLOW_2);
                    this_RuleCall_1=ruleRuleCall();

                    state._fsp--;


                    			current = this_RuleCall_1;
                    			doneComposite();
                    		

                    }
                    break;
                case 3 :
                    // PsiInternalXtext.g:1429:3: this_ParenthesizedElement_2= ruleParenthesizedElement
                    {

                    			markComposite(elementTypeProvider.getAbstractTerminal_ParenthesizedElementParserRuleCall_2ElementType());
                    		
                    pushFollow(FollowSets000.FOLLOW_2);
                    this_ParenthesizedElement_2=ruleParenthesizedElement();

                    state._fsp--;


                    			current = this_ParenthesizedElement_2;
                    			doneComposite();
                    		

                    }
                    break;
                case 4 :
                    // PsiInternalXtext.g:1438:3: this_PredicatedKeyword_3= rulePredicatedKeyword
                    {

                    			markComposite(elementTypeProvider.getAbstractTerminal_PredicatedKeywordParserRuleCall_3ElementType());
                    		
                    pushFollow(FollowSets000.FOLLOW_2);
                    this_PredicatedKeyword_3=rulePredicatedKeyword();

                    state._fsp--;


                    			current = this_PredicatedKeyword_3;
                    			doneComposite();
                    		

                    }
                    break;
                case 5 :
                    // PsiInternalXtext.g:1447:3: this_PredicatedRuleCall_4= rulePredicatedRuleCall
                    {

                    			markComposite(elementTypeProvider.getAbstractTerminal_PredicatedRuleCallParserRuleCall_4ElementType());
                    		
                    pushFollow(FollowSets000.FOLLOW_2);
                    this_PredicatedRuleCall_4=rulePredicatedRuleCall();

                    state._fsp--;


                    			current = this_PredicatedRuleCall_4;
                    			doneComposite();
                    		

                    }
                    break;
                case 6 :
                    // PsiInternalXtext.g:1456:3: this_PredicatedGroup_5= rulePredicatedGroup
                    {

                    			markComposite(elementTypeProvider.getAbstractTerminal_PredicatedGroupParserRuleCall_5ElementType());
                    		
                    pushFollow(FollowSets000.FOLLOW_2);
                    this_PredicatedGroup_5=rulePredicatedGroup();

                    state._fsp--;


                    			current = this_PredicatedGroup_5;
                    			doneComposite();
                    		

                    }
                    break;

            }


            }

        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
        }
        finally {
        }
        return current;
    }
    // $ANTLR end "ruleAbstractTerminal"


    // $ANTLR start "entryRuleKeyword"
    // PsiInternalXtext.g:1468:1: entryRuleKeyword returns [Boolean current=false] : iv_ruleKeyword= ruleKeyword EOF ;
    public final Boolean entryRuleKeyword() throws RecognitionException {
        Boolean current = false;

        Boolean iv_ruleKeyword = null;


        try {
            // PsiInternalXtext.g:1468:49: (iv_ruleKeyword= ruleKeyword EOF )
            // PsiInternalXtext.g:1469:2: iv_ruleKeyword= ruleKeyword EOF
            {
             markComposite(elementTypeProvider.getKeywordElementType()); 
            pushFollow(FollowSets000.FOLLOW_1);
            iv_ruleKeyword=ruleKeyword();

            state._fsp--;

             current =iv_ruleKeyword; 
            match(input,EOF,FollowSets000.FOLLOW_2); 

            }

        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
        }
        finally {
        }
        return current;
    }
    // $ANTLR end "entryRuleKeyword"


    // $ANTLR start "ruleKeyword"
    // PsiInternalXtext.g:1475:1: ruleKeyword returns [Boolean current=false] : ( (lv_value_0_0= RULE_STRING ) ) ;
    public final Boolean ruleKeyword() throws RecognitionException {
        Boolean current = false;

        Token lv_value_0_0=null;

        try {
            // PsiInternalXtext.g:1476:1: ( ( (lv_value_0_0= RULE_STRING ) ) )
            // PsiInternalXtext.g:1477:2: ( (lv_value_0_0= RULE_STRING ) )
            {
            // PsiInternalXtext.g:1477:2: ( (lv_value_0_0= RULE_STRING ) )
            // PsiInternalXtext.g:1478:3: (lv_value_0_0= RULE_STRING )
            {
            // PsiInternalXtext.g:1478:3: (lv_value_0_0= RULE_STRING )
            // PsiInternalXtext.g:1479:4: lv_value_0_0= RULE_STRING
            {

            				markLeaf(elementTypeProvider.getKeyword_ValueSTRINGTerminalRuleCall_0ElementType());
            			
            lv_value_0_0=(Token)match(input,RULE_STRING,FollowSets000.FOLLOW_2); 

            				if(!current) {
            					associateWithSemanticElement();
            					current = true;
            				}
            			

            				doneLeaf(lv_value_0_0);
            			

            }


            }


            }

        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
        }
        finally {
        }
        return current;
    }
    // $ANTLR end "ruleKeyword"


    // $ANTLR start "entryRuleRuleCall"
    // PsiInternalXtext.g:1497:1: entryRuleRuleCall returns [Boolean current=false] : iv_ruleRuleCall= ruleRuleCall EOF ;
    public final Boolean entryRuleRuleCall() throws RecognitionException {
        Boolean current = false;

        Boolean iv_ruleRuleCall = null;


        try {
            // PsiInternalXtext.g:1497:50: (iv_ruleRuleCall= ruleRuleCall EOF )
            // PsiInternalXtext.g:1498:2: iv_ruleRuleCall= ruleRuleCall EOF
            {
             markComposite(elementTypeProvider.getRuleCallElementType()); 
            pushFollow(FollowSets000.FOLLOW_1);
            iv_ruleRuleCall=ruleRuleCall();

            state._fsp--;

             current =iv_ruleRuleCall; 
            match(input,EOF,FollowSets000.FOLLOW_2); 

            }

        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
        }
        finally {
        }
        return current;
    }
    // $ANTLR end "entryRuleRuleCall"


    // $ANTLR start "ruleRuleCall"
    // PsiInternalXtext.g:1504:1: ruleRuleCall returns [Boolean current=false] : ( ( ( ruleRuleID ) ) (otherlv_1= '<' ( (lv_arguments_2_0= ruleNamedArgument ) ) (otherlv_3= ',' ( (lv_arguments_4_0= ruleNamedArgument ) ) )* otherlv_5= '>' )? ) ;
    public final Boolean ruleRuleCall() throws RecognitionException {
        Boolean current = false;

        Token otherlv_1=null;
        Token otherlv_3=null;
        Token otherlv_5=null;
        Boolean lv_arguments_2_0 = null;

        Boolean lv_arguments_4_0 = null;


        try {
            // PsiInternalXtext.g:1505:1: ( ( ( ( ruleRuleID ) ) (otherlv_1= '<' ( (lv_arguments_2_0= ruleNamedArgument ) ) (otherlv_3= ',' ( (lv_arguments_4_0= ruleNamedArgument ) ) )* otherlv_5= '>' )? ) )
            // PsiInternalXtext.g:1506:2: ( ( ( ruleRuleID ) ) (otherlv_1= '<' ( (lv_arguments_2_0= ruleNamedArgument ) ) (otherlv_3= ',' ( (lv_arguments_4_0= ruleNamedArgument ) ) )* otherlv_5= '>' )? )
            {
            // PsiInternalXtext.g:1506:2: ( ( ( ruleRuleID ) ) (otherlv_1= '<' ( (lv_arguments_2_0= ruleNamedArgument ) ) (otherlv_3= ',' ( (lv_arguments_4_0= ruleNamedArgument ) ) )* otherlv_5= '>' )? )
            // PsiInternalXtext.g:1507:3: ( ( ruleRuleID ) ) (otherlv_1= '<' ( (lv_arguments_2_0= ruleNamedArgument ) ) (otherlv_3= ',' ( (lv_arguments_4_0= ruleNamedArgument ) ) )* otherlv_5= '>' )?
            {
            // PsiInternalXtext.g:1507:3: ( ( ruleRuleID ) )
            // PsiInternalXtext.g:1508:4: ( ruleRuleID )
            {
            // PsiInternalXtext.g:1508:4: ( ruleRuleID )
            // PsiInternalXtext.g:1509:5: ruleRuleID
            {

            					if (!current) {
            						associateWithSemanticElement();
            						current = true;
            					}
            				

            					markComposite(elementTypeProvider.getRuleCall_RuleAbstractRuleCrossReference_0_0ElementType());
            				
            pushFollow(FollowSets000.FOLLOW_21);
            ruleRuleID();

            state._fsp--;


            					doneComposite();
            				

            }


            }

            // PsiInternalXtext.g:1524:3: (otherlv_1= '<' ( (lv_arguments_2_0= ruleNamedArgument ) ) (otherlv_3= ',' ( (lv_arguments_4_0= ruleNamedArgument ) ) )* otherlv_5= '>' )?
            int alt41=2;
            int LA41_0 = input.LA(1);

            if ( (LA41_0==27) ) {
                alt41=1;
            }
            switch (alt41) {
                case 1 :
                    // PsiInternalXtext.g:1525:4: otherlv_1= '<' ( (lv_arguments_2_0= ruleNamedArgument ) ) (otherlv_3= ',' ( (lv_arguments_4_0= ruleNamedArgument ) ) )* otherlv_5= '>'
                    {

                    				markLeaf(elementTypeProvider.getRuleCall_LessThanSignKeyword_1_0ElementType());
                    			
                    otherlv_1=(Token)match(input,27,FollowSets000.FOLLOW_26); 

                    				doneLeaf(otherlv_1);
                    			
                    // PsiInternalXtext.g:1532:4: ( (lv_arguments_2_0= ruleNamedArgument ) )
                    // PsiInternalXtext.g:1533:5: (lv_arguments_2_0= ruleNamedArgument )
                    {
                    // PsiInternalXtext.g:1533:5: (lv_arguments_2_0= ruleNamedArgument )
                    // PsiInternalXtext.g:1534:6: lv_arguments_2_0= ruleNamedArgument
                    {

                    						markComposite(elementTypeProvider.getRuleCall_ArgumentsNamedArgumentParserRuleCall_1_1_0ElementType());
                    					
                    pushFollow(FollowSets000.FOLLOW_23);
                    lv_arguments_2_0=ruleNamedArgument();

                    state._fsp--;


                    						doneComposite();
                    						if(!current) {
                    							associateWithSemanticElement();
                    							current = true;
                    						}
                    					

                    }


                    }

                    // PsiInternalXtext.g:1547:4: (otherlv_3= ',' ( (lv_arguments_4_0= ruleNamedArgument ) ) )*
                    loop40:
                    do {
                        int alt40=2;
                        int LA40_0 = input.LA(1);

                        if ( (LA40_0==13) ) {
                            alt40=1;
                        }


                        switch (alt40) {
                    	case 1 :
                    	    // PsiInternalXtext.g:1548:5: otherlv_3= ',' ( (lv_arguments_4_0= ruleNamedArgument ) )
                    	    {

                    	    					markLeaf(elementTypeProvider.getRuleCall_CommaKeyword_1_2_0ElementType());
                    	    				
                    	    otherlv_3=(Token)match(input,13,FollowSets000.FOLLOW_26); 

                    	    					doneLeaf(otherlv_3);
                    	    				
                    	    // PsiInternalXtext.g:1555:5: ( (lv_arguments_4_0= ruleNamedArgument ) )
                    	    // PsiInternalXtext.g:1556:6: (lv_arguments_4_0= ruleNamedArgument )
                    	    {
                    	    // PsiInternalXtext.g:1556:6: (lv_arguments_4_0= ruleNamedArgument )
                    	    // PsiInternalXtext.g:1557:7: lv_arguments_4_0= ruleNamedArgument
                    	    {

                    	    							markComposite(elementTypeProvider.getRuleCall_ArgumentsNamedArgumentParserRuleCall_1_2_1_0ElementType());
                    	    						
                    	    pushFollow(FollowSets000.FOLLOW_23);
                    	    lv_arguments_4_0=ruleNamedArgument();

                    	    state._fsp--;


                    	    							doneComposite();
                    	    							if(!current) {
                    	    								associateWithSemanticElement();
                    	    								current = true;
                    	    							}
                    	    						

                    	    }


                    	    }


                    	    }
                    	    break;

                    	default :
                    	    break loop40;
                        }
                    } while (true);


                    				markLeaf(elementTypeProvider.getRuleCall_GreaterThanSignKeyword_1_3ElementType());
                    			
                    otherlv_5=(Token)match(input,28,FollowSets000.FOLLOW_2); 

                    				doneLeaf(otherlv_5);
                    			

                    }
                    break;

            }


            }


            }

        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
        }
        finally {
        }
        return current;
    }
    // $ANTLR end "ruleRuleCall"


    // $ANTLR start "entryRuleNamedArgument"
    // PsiInternalXtext.g:1583:1: entryRuleNamedArgument returns [Boolean current=false] : iv_ruleNamedArgument= ruleNamedArgument EOF ;
    public final Boolean entryRuleNamedArgument() throws RecognitionException {
        Boolean current = false;

        Boolean iv_ruleNamedArgument = null;


        try {
            // PsiInternalXtext.g:1583:55: (iv_ruleNamedArgument= ruleNamedArgument EOF )
            // PsiInternalXtext.g:1584:2: iv_ruleNamedArgument= ruleNamedArgument EOF
            {
             markComposite(elementTypeProvider.getNamedArgumentElementType()); 
            pushFollow(FollowSets000.FOLLOW_1);
            iv_ruleNamedArgument=ruleNamedArgument();

            state._fsp--;

             current =iv_ruleNamedArgument; 
            match(input,EOF,FollowSets000.FOLLOW_2); 

            }

        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
        }
        finally {
        }
        return current;
    }
    // $ANTLR end "entryRuleNamedArgument"


    // $ANTLR start "ruleNamedArgument"
    // PsiInternalXtext.g:1590:1: ruleNamedArgument returns [Boolean current=false] : ( ( ( (otherlv_0= RULE_ID ) ) ( (lv_calledByName_1_0= '=' ) ) )? ( (lv_value_2_0= ruleDisjunction ) ) ) ;
    public final Boolean ruleNamedArgument() throws RecognitionException {
        Boolean current = false;

        Token otherlv_0=null;
        Token lv_calledByName_1_0=null;
        Boolean lv_value_2_0 = null;


        try {
            // PsiInternalXtext.g:1591:1: ( ( ( ( (otherlv_0= RULE_ID ) ) ( (lv_calledByName_1_0= '=' ) ) )? ( (lv_value_2_0= ruleDisjunction ) ) ) )
            // PsiInternalXtext.g:1592:2: ( ( ( (otherlv_0= RULE_ID ) ) ( (lv_calledByName_1_0= '=' ) ) )? ( (lv_value_2_0= ruleDisjunction ) ) )
            {
            // PsiInternalXtext.g:1592:2: ( ( ( (otherlv_0= RULE_ID ) ) ( (lv_calledByName_1_0= '=' ) ) )? ( (lv_value_2_0= ruleDisjunction ) ) )
            // PsiInternalXtext.g:1593:3: ( ( (otherlv_0= RULE_ID ) ) ( (lv_calledByName_1_0= '=' ) ) )? ( (lv_value_2_0= ruleDisjunction ) )
            {
            // PsiInternalXtext.g:1593:3: ( ( (otherlv_0= RULE_ID ) ) ( (lv_calledByName_1_0= '=' ) ) )?
            int alt42=2;
            int LA42_0 = input.LA(1);

            if ( (LA42_0==RULE_ID) ) {
                int LA42_1 = input.LA(2);

                if ( (LA42_1==35) ) {
                    alt42=1;
                }
            }
            switch (alt42) {
                case 1 :
                    // PsiInternalXtext.g:1594:4: ( (otherlv_0= RULE_ID ) ) ( (lv_calledByName_1_0= '=' ) )
                    {
                    // PsiInternalXtext.g:1594:4: ( (otherlv_0= RULE_ID ) )
                    // PsiInternalXtext.g:1595:5: (otherlv_0= RULE_ID )
                    {
                    // PsiInternalXtext.g:1595:5: (otherlv_0= RULE_ID )
                    // PsiInternalXtext.g:1596:6: otherlv_0= RULE_ID
                    {

                    						if (!current) {
                    							associateWithSemanticElement();
                    							current = true;
                    						}
                    					

                    						markLeaf(elementTypeProvider.getNamedArgument_ParameterParameterCrossReference_0_0_0ElementType());
                    					
                    otherlv_0=(Token)match(input,RULE_ID,FollowSets000.FOLLOW_36); 

                    						doneLeaf(otherlv_0);
                    					

                    }


                    }

                    // PsiInternalXtext.g:1611:4: ( (lv_calledByName_1_0= '=' ) )
                    // PsiInternalXtext.g:1612:5: (lv_calledByName_1_0= '=' )
                    {
                    // PsiInternalXtext.g:1612:5: (lv_calledByName_1_0= '=' )
                    // PsiInternalXtext.g:1613:6: lv_calledByName_1_0= '='
                    {

                    						markLeaf(elementTypeProvider.getNamedArgument_CalledByNameEqualsSignKeyword_0_1_0ElementType());
                    					
                    lv_calledByName_1_0=(Token)match(input,35,FollowSets000.FOLLOW_26); 

                    						doneLeaf(lv_calledByName_1_0);
                    					

                    						if (!current) {
                    							associateWithSemanticElement();
                    							current = true;
                    						}
                    					

                    }


                    }


                    }
                    break;

            }

            // PsiInternalXtext.g:1629:3: ( (lv_value_2_0= ruleDisjunction ) )
            // PsiInternalXtext.g:1630:4: (lv_value_2_0= ruleDisjunction )
            {
            // PsiInternalXtext.g:1630:4: (lv_value_2_0= ruleDisjunction )
            // PsiInternalXtext.g:1631:5: lv_value_2_0= ruleDisjunction
            {

            					markComposite(elementTypeProvider.getNamedArgument_ValueDisjunctionParserRuleCall_1_0ElementType());
            				
            pushFollow(FollowSets000.FOLLOW_2);
            lv_value_2_0=ruleDisjunction();

            state._fsp--;


            					doneComposite();
            					if(!current) {
            						associateWithSemanticElement();
            						current = true;
            					}
            				

            }


            }


            }


            }

        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
        }
        finally {
        }
        return current;
    }
    // $ANTLR end "ruleNamedArgument"


    // $ANTLR start "entryRuleLiteralCondition"
    // PsiInternalXtext.g:1648:1: entryRuleLiteralCondition returns [Boolean current=false] : iv_ruleLiteralCondition= ruleLiteralCondition EOF ;
    public final Boolean entryRuleLiteralCondition() throws RecognitionException {
        Boolean current = false;

        Boolean iv_ruleLiteralCondition = null;


        try {
            // PsiInternalXtext.g:1648:58: (iv_ruleLiteralCondition= ruleLiteralCondition EOF )
            // PsiInternalXtext.g:1649:2: iv_ruleLiteralCondition= ruleLiteralCondition EOF
            {
             markComposite(elementTypeProvider.getLiteralConditionElementType()); 
            pushFollow(FollowSets000.FOLLOW_1);
            iv_ruleLiteralCondition=ruleLiteralCondition();

            state._fsp--;

             current =iv_ruleLiteralCondition; 
            match(input,EOF,FollowSets000.FOLLOW_2); 

            }

        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
        }
        finally {
        }
        return current;
    }
    // $ANTLR end "entryRuleLiteralCondition"


    // $ANTLR start "ruleLiteralCondition"
    // PsiInternalXtext.g:1655:1: ruleLiteralCondition returns [Boolean current=false] : ( () ( ( (lv_true_1_0= 'true' ) ) | otherlv_2= 'false' ) ) ;
    public final Boolean ruleLiteralCondition() throws RecognitionException {
        Boolean current = false;

        Token lv_true_1_0=null;
        Token otherlv_2=null;

        try {
            // PsiInternalXtext.g:1656:1: ( ( () ( ( (lv_true_1_0= 'true' ) ) | otherlv_2= 'false' ) ) )
            // PsiInternalXtext.g:1657:2: ( () ( ( (lv_true_1_0= 'true' ) ) | otherlv_2= 'false' ) )
            {
            // PsiInternalXtext.g:1657:2: ( () ( ( (lv_true_1_0= 'true' ) ) | otherlv_2= 'false' ) )
            // PsiInternalXtext.g:1658:3: () ( ( (lv_true_1_0= 'true' ) ) | otherlv_2= 'false' )
            {
            // PsiInternalXtext.g:1658:3: ()
            // PsiInternalXtext.g:1659:4: 
            {

            				precedeComposite(elementTypeProvider.getLiteralCondition_LiteralConditionAction_0ElementType());
            				doneComposite();
            				associateWithSemanticElement();
            			

            }

            // PsiInternalXtext.g:1665:3: ( ( (lv_true_1_0= 'true' ) ) | otherlv_2= 'false' )
            int alt43=2;
            int LA43_0 = input.LA(1);

            if ( (LA43_0==39) ) {
                alt43=1;
            }
            else if ( (LA43_0==40) ) {
                alt43=2;
            }
            else {
                NoViableAltException nvae =
                    new NoViableAltException("", 43, 0, input);

                throw nvae;
            }
            switch (alt43) {
                case 1 :
                    // PsiInternalXtext.g:1666:4: ( (lv_true_1_0= 'true' ) )
                    {
                    // PsiInternalXtext.g:1666:4: ( (lv_true_1_0= 'true' ) )
                    // PsiInternalXtext.g:1667:5: (lv_true_1_0= 'true' )
                    {
                    // PsiInternalXtext.g:1667:5: (lv_true_1_0= 'true' )
                    // PsiInternalXtext.g:1668:6: lv_true_1_0= 'true'
                    {

                    						markLeaf(elementTypeProvider.getLiteralCondition_TrueTrueKeyword_1_0_0ElementType());
                    					
                    lv_true_1_0=(Token)match(input,39,FollowSets000.FOLLOW_2); 

                    						doneLeaf(lv_true_1_0);
                    					

                    						if (!current) {
                    							associateWithSemanticElement();
                    							current = true;
                    						}
                    					

                    }


                    }


                    }
                    break;
                case 2 :
                    // PsiInternalXtext.g:1684:4: otherlv_2= 'false'
                    {

                    				markLeaf(elementTypeProvider.getLiteralCondition_FalseKeyword_1_1ElementType());
                    			
                    otherlv_2=(Token)match(input,40,FollowSets000.FOLLOW_2); 

                    				doneLeaf(otherlv_2);
                    			

                    }
                    break;

            }


            }


            }

        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
        }
        finally {
        }
        return current;
    }
    // $ANTLR end "ruleLiteralCondition"


    // $ANTLR start "entryRuleDisjunction"
    // PsiInternalXtext.g:1696:1: entryRuleDisjunction returns [Boolean current=false] : iv_ruleDisjunction= ruleDisjunction EOF ;
    public final Boolean entryRuleDisjunction() throws RecognitionException {
        Boolean current = false;

        Boolean iv_ruleDisjunction = null;


        try {
            // PsiInternalXtext.g:1696:53: (iv_ruleDisjunction= ruleDisjunction EOF )
            // PsiInternalXtext.g:1697:2: iv_ruleDisjunction= ruleDisjunction EOF
            {
             markComposite(elementTypeProvider.getDisjunctionElementType()); 
            pushFollow(FollowSets000.FOLLOW_1);
            iv_ruleDisjunction=ruleDisjunction();

            state._fsp--;

             current =iv_ruleDisjunction; 
            match(input,EOF,FollowSets000.FOLLOW_2); 

            }

        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
        }
        finally {
        }
        return current;
    }
    // $ANTLR end "entryRuleDisjunction"


    // $ANTLR start "ruleDisjunction"
    // PsiInternalXtext.g:1703:1: ruleDisjunction returns [Boolean current=false] : (this_Conjunction_0= ruleConjunction ( () otherlv_2= '|' ( (lv_right_3_0= ruleConjunction ) ) )* ) ;
    public final Boolean ruleDisjunction() throws RecognitionException {
        Boolean current = false;

        Token otherlv_2=null;
        Boolean this_Conjunction_0 = null;

        Boolean lv_right_3_0 = null;


        try {
            // PsiInternalXtext.g:1704:1: ( (this_Conjunction_0= ruleConjunction ( () otherlv_2= '|' ( (lv_right_3_0= ruleConjunction ) ) )* ) )
            // PsiInternalXtext.g:1705:2: (this_Conjunction_0= ruleConjunction ( () otherlv_2= '|' ( (lv_right_3_0= ruleConjunction ) ) )* )
            {
            // PsiInternalXtext.g:1705:2: (this_Conjunction_0= ruleConjunction ( () otherlv_2= '|' ( (lv_right_3_0= ruleConjunction ) ) )* )
            // PsiInternalXtext.g:1706:3: this_Conjunction_0= ruleConjunction ( () otherlv_2= '|' ( (lv_right_3_0= ruleConjunction ) ) )*
            {

            			markComposite(elementTypeProvider.getDisjunction_ConjunctionParserRuleCall_0ElementType());
            		
            pushFollow(FollowSets000.FOLLOW_25);
            this_Conjunction_0=ruleConjunction();

            state._fsp--;


            			current = this_Conjunction_0;
            			doneComposite();
            		
            // PsiInternalXtext.g:1714:3: ( () otherlv_2= '|' ( (lv_right_3_0= ruleConjunction ) ) )*
            loop44:
            do {
                int alt44=2;
                int LA44_0 = input.LA(1);

                if ( (LA44_0==30) ) {
                    alt44=1;
                }


                switch (alt44) {
            	case 1 :
            	    // PsiInternalXtext.g:1715:4: () otherlv_2= '|' ( (lv_right_3_0= ruleConjunction ) )
            	    {
            	    // PsiInternalXtext.g:1715:4: ()
            	    // PsiInternalXtext.g:1716:5: 
            	    {

            	    					precedeComposite(elementTypeProvider.getDisjunction_DisjunctionLeftAction_1_0ElementType());
            	    					doneComposite();
            	    					associateWithSemanticElement();
            	    				

            	    }


            	    				markLeaf(elementTypeProvider.getDisjunction_VerticalLineKeyword_1_1ElementType());
            	    			
            	    otherlv_2=(Token)match(input,30,FollowSets000.FOLLOW_26); 

            	    				doneLeaf(otherlv_2);
            	    			
            	    // PsiInternalXtext.g:1729:4: ( (lv_right_3_0= ruleConjunction ) )
            	    // PsiInternalXtext.g:1730:5: (lv_right_3_0= ruleConjunction )
            	    {
            	    // PsiInternalXtext.g:1730:5: (lv_right_3_0= ruleConjunction )
            	    // PsiInternalXtext.g:1731:6: lv_right_3_0= ruleConjunction
            	    {

            	    						markComposite(elementTypeProvider.getDisjunction_RightConjunctionParserRuleCall_1_2_0ElementType());
            	    					
            	    pushFollow(FollowSets000.FOLLOW_25);
            	    lv_right_3_0=ruleConjunction();

            	    state._fsp--;


            	    						doneComposite();
            	    						if(!current) {
            	    							associateWithSemanticElement();
            	    							current = true;
            	    						}
            	    					

            	    }


            	    }


            	    }
            	    break;

            	default :
            	    break loop44;
                }
            } while (true);


            }


            }

        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
        }
        finally {
        }
        return current;
    }
    // $ANTLR end "ruleDisjunction"


    // $ANTLR start "entryRuleConjunction"
    // PsiInternalXtext.g:1749:1: entryRuleConjunction returns [Boolean current=false] : iv_ruleConjunction= ruleConjunction EOF ;
    public final Boolean entryRuleConjunction() throws RecognitionException {
        Boolean current = false;

        Boolean iv_ruleConjunction = null;


        try {
            // PsiInternalXtext.g:1749:53: (iv_ruleConjunction= ruleConjunction EOF )
            // PsiInternalXtext.g:1750:2: iv_ruleConjunction= ruleConjunction EOF
            {
             markComposite(elementTypeProvider.getConjunctionElementType()); 
            pushFollow(FollowSets000.FOLLOW_1);
            iv_ruleConjunction=ruleConjunction();

            state._fsp--;

             current =iv_ruleConjunction; 
            match(input,EOF,FollowSets000.FOLLOW_2); 

            }

        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
        }
        finally {
        }
        return current;
    }
    // $ANTLR end "entryRuleConjunction"


    // $ANTLR start "ruleConjunction"
    // PsiInternalXtext.g:1756:1: ruleConjunction returns [Boolean current=false] : (this_Negation_0= ruleNegation ( () otherlv_2= '&' ( (lv_right_3_0= ruleNegation ) ) )* ) ;
    public final Boolean ruleConjunction() throws RecognitionException {
        Boolean current = false;

        Token otherlv_2=null;
        Boolean this_Negation_0 = null;

        Boolean lv_right_3_0 = null;


        try {
            // PsiInternalXtext.g:1757:1: ( (this_Negation_0= ruleNegation ( () otherlv_2= '&' ( (lv_right_3_0= ruleNegation ) ) )* ) )
            // PsiInternalXtext.g:1758:2: (this_Negation_0= ruleNegation ( () otherlv_2= '&' ( (lv_right_3_0= ruleNegation ) ) )* )
            {
            // PsiInternalXtext.g:1758:2: (this_Negation_0= ruleNegation ( () otherlv_2= '&' ( (lv_right_3_0= ruleNegation ) ) )* )
            // PsiInternalXtext.g:1759:3: this_Negation_0= ruleNegation ( () otherlv_2= '&' ( (lv_right_3_0= ruleNegation ) ) )*
            {

            			markComposite(elementTypeProvider.getConjunction_NegationParserRuleCall_0ElementType());
            		
            pushFollow(FollowSets000.FOLLOW_30);
            this_Negation_0=ruleNegation();

            state._fsp--;


            			current = this_Negation_0;
            			doneComposite();
            		
            // PsiInternalXtext.g:1767:3: ( () otherlv_2= '&' ( (lv_right_3_0= ruleNegation ) ) )*
            loop45:
            do {
                int alt45=2;
                int LA45_0 = input.LA(1);

                if ( (LA45_0==31) ) {
                    alt45=1;
                }


                switch (alt45) {
            	case 1 :
            	    // PsiInternalXtext.g:1768:4: () otherlv_2= '&' ( (lv_right_3_0= ruleNegation ) )
            	    {
            	    // PsiInternalXtext.g:1768:4: ()
            	    // PsiInternalXtext.g:1769:5: 
            	    {

            	    					precedeComposite(elementTypeProvider.getConjunction_ConjunctionLeftAction_1_0ElementType());
            	    					doneComposite();
            	    					associateWithSemanticElement();
            	    				

            	    }


            	    				markLeaf(elementTypeProvider.getConjunction_AmpersandKeyword_1_1ElementType());
            	    			
            	    otherlv_2=(Token)match(input,31,FollowSets000.FOLLOW_26); 

            	    				doneLeaf(otherlv_2);
            	    			
            	    // PsiInternalXtext.g:1782:4: ( (lv_right_3_0= ruleNegation ) )
            	    // PsiInternalXtext.g:1783:5: (lv_right_3_0= ruleNegation )
            	    {
            	    // PsiInternalXtext.g:1783:5: (lv_right_3_0= ruleNegation )
            	    // PsiInternalXtext.g:1784:6: lv_right_3_0= ruleNegation
            	    {

            	    						markComposite(elementTypeProvider.getConjunction_RightNegationParserRuleCall_1_2_0ElementType());
            	    					
            	    pushFollow(FollowSets000.FOLLOW_30);
            	    lv_right_3_0=ruleNegation();

            	    state._fsp--;


            	    						doneComposite();
            	    						if(!current) {
            	    							associateWithSemanticElement();
            	    							current = true;
            	    						}
            	    					

            	    }


            	    }


            	    }
            	    break;

            	default :
            	    break loop45;
                }
            } while (true);


            }


            }

        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
        }
        finally {
        }
        return current;
    }
    // $ANTLR end "ruleConjunction"


    // $ANTLR start "entryRuleNegation"
    // PsiInternalXtext.g:1802:1: entryRuleNegation returns [Boolean current=false] : iv_ruleNegation= ruleNegation EOF ;
    public final Boolean entryRuleNegation() throws RecognitionException {
        Boolean current = false;

        Boolean iv_ruleNegation = null;


        try {
            // PsiInternalXtext.g:1802:50: (iv_ruleNegation= ruleNegation EOF )
            // PsiInternalXtext.g:1803:2: iv_ruleNegation= ruleNegation EOF
            {
             markComposite(elementTypeProvider.getNegationElementType()); 
            pushFollow(FollowSets000.FOLLOW_1);
            iv_ruleNegation=ruleNegation();

            state._fsp--;

             current =iv_ruleNegation; 
            match(input,EOF,FollowSets000.FOLLOW_2); 

            }

        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
        }
        finally {
        }
        return current;
    }
    // $ANTLR end "entryRuleNegation"


    // $ANTLR start "ruleNegation"
    // PsiInternalXtext.g:1809:1: ruleNegation returns [Boolean current=false] : (this_Atom_0= ruleAtom | ( () otherlv_2= '!' ( (lv_value_3_0= ruleNegation ) ) ) ) ;
    public final Boolean ruleNegation() throws RecognitionException {
        Boolean current = false;

        Token otherlv_2=null;
        Boolean this_Atom_0 = null;

        Boolean lv_value_3_0 = null;


        try {
            // PsiInternalXtext.g:1810:1: ( (this_Atom_0= ruleAtom | ( () otherlv_2= '!' ( (lv_value_3_0= ruleNegation ) ) ) ) )
            // PsiInternalXtext.g:1811:2: (this_Atom_0= ruleAtom | ( () otherlv_2= '!' ( (lv_value_3_0= ruleNegation ) ) ) )
            {
            // PsiInternalXtext.g:1811:2: (this_Atom_0= ruleAtom | ( () otherlv_2= '!' ( (lv_value_3_0= ruleNegation ) ) ) )
            int alt46=2;
            int LA46_0 = input.LA(1);

            if ( (LA46_0==RULE_ID||LA46_0==15||(LA46_0>=39 && LA46_0<=40)) ) {
                alt46=1;
            }
            else if ( (LA46_0==41) ) {
                alt46=2;
            }
            else {
                NoViableAltException nvae =
                    new NoViableAltException("", 46, 0, input);

                throw nvae;
            }
            switch (alt46) {
                case 1 :
                    // PsiInternalXtext.g:1812:3: this_Atom_0= ruleAtom
                    {

                    			markComposite(elementTypeProvider.getNegation_AtomParserRuleCall_0ElementType());
                    		
                    pushFollow(FollowSets000.FOLLOW_2);
                    this_Atom_0=ruleAtom();

                    state._fsp--;


                    			current = this_Atom_0;
                    			doneComposite();
                    		

                    }
                    break;
                case 2 :
                    // PsiInternalXtext.g:1821:3: ( () otherlv_2= '!' ( (lv_value_3_0= ruleNegation ) ) )
                    {
                    // PsiInternalXtext.g:1821:3: ( () otherlv_2= '!' ( (lv_value_3_0= ruleNegation ) ) )
                    // PsiInternalXtext.g:1822:4: () otherlv_2= '!' ( (lv_value_3_0= ruleNegation ) )
                    {
                    // PsiInternalXtext.g:1822:4: ()
                    // PsiInternalXtext.g:1823:5: 
                    {

                    					precedeComposite(elementTypeProvider.getNegation_NegationAction_1_0ElementType());
                    					doneComposite();
                    					associateWithSemanticElement();
                    				

                    }


                    				markLeaf(elementTypeProvider.getNegation_ExclamationMarkKeyword_1_1ElementType());
                    			
                    otherlv_2=(Token)match(input,41,FollowSets000.FOLLOW_26); 

                    				doneLeaf(otherlv_2);
                    			
                    // PsiInternalXtext.g:1836:4: ( (lv_value_3_0= ruleNegation ) )
                    // PsiInternalXtext.g:1837:5: (lv_value_3_0= ruleNegation )
                    {
                    // PsiInternalXtext.g:1837:5: (lv_value_3_0= ruleNegation )
                    // PsiInternalXtext.g:1838:6: lv_value_3_0= ruleNegation
                    {

                    						markComposite(elementTypeProvider.getNegation_ValueNegationParserRuleCall_1_2_0ElementType());
                    					
                    pushFollow(FollowSets000.FOLLOW_2);
                    lv_value_3_0=ruleNegation();

                    state._fsp--;


                    						doneComposite();
                    						if(!current) {
                    							associateWithSemanticElement();
                    							current = true;
                    						}
                    					

                    }


                    }


                    }


                    }
                    break;

            }


            }

        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
        }
        finally {
        }
        return current;
    }
    // $ANTLR end "ruleNegation"


    // $ANTLR start "entryRuleAtom"
    // PsiInternalXtext.g:1856:1: entryRuleAtom returns [Boolean current=false] : iv_ruleAtom= ruleAtom EOF ;
    public final Boolean entryRuleAtom() throws RecognitionException {
        Boolean current = false;

        Boolean iv_ruleAtom = null;


        try {
            // PsiInternalXtext.g:1856:46: (iv_ruleAtom= ruleAtom EOF )
            // PsiInternalXtext.g:1857:2: iv_ruleAtom= ruleAtom EOF
            {
             markComposite(elementTypeProvider.getAtomElementType()); 
            pushFollow(FollowSets000.FOLLOW_1);
            iv_ruleAtom=ruleAtom();

            state._fsp--;

             current =iv_ruleAtom; 
            match(input,EOF,FollowSets000.FOLLOW_2); 

            }

        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
        }
        finally {
        }
        return current;
    }
    // $ANTLR end "entryRuleAtom"


    // $ANTLR start "ruleAtom"
    // PsiInternalXtext.g:1863:1: ruleAtom returns [Boolean current=false] : (this_ParameterReference_0= ruleParameterReference | this_ParenthesizedCondition_1= ruleParenthesizedCondition | this_LiteralCondition_2= ruleLiteralCondition ) ;
    public final Boolean ruleAtom() throws RecognitionException {
        Boolean current = false;

        Boolean this_ParameterReference_0 = null;

        Boolean this_ParenthesizedCondition_1 = null;

        Boolean this_LiteralCondition_2 = null;


        try {
            // PsiInternalXtext.g:1864:1: ( (this_ParameterReference_0= ruleParameterReference | this_ParenthesizedCondition_1= ruleParenthesizedCondition | this_LiteralCondition_2= ruleLiteralCondition ) )
            // PsiInternalXtext.g:1865:2: (this_ParameterReference_0= ruleParameterReference | this_ParenthesizedCondition_1= ruleParenthesizedCondition | this_LiteralCondition_2= ruleLiteralCondition )
            {
            // PsiInternalXtext.g:1865:2: (this_ParameterReference_0= ruleParameterReference | this_ParenthesizedCondition_1= ruleParenthesizedCondition | this_LiteralCondition_2= ruleLiteralCondition )
            int alt47=3;
            switch ( input.LA(1) ) {
            case RULE_ID:
                {
                alt47=1;
                }
                break;
            case 15:
                {
                alt47=2;
                }
                break;
            case 39:
            case 40:
                {
                alt47=3;
                }
                break;
            default:
                NoViableAltException nvae =
                    new NoViableAltException("", 47, 0, input);

                throw nvae;
            }

            switch (alt47) {
                case 1 :
                    // PsiInternalXtext.g:1866:3: this_ParameterReference_0= ruleParameterReference
                    {

                    			markComposite(elementTypeProvider.getAtom_ParameterReferenceParserRuleCall_0ElementType());
                    		
                    pushFollow(FollowSets000.FOLLOW_2);
                    this_ParameterReference_0=ruleParameterReference();

                    state._fsp--;


                    			current = this_ParameterReference_0;
                    			doneComposite();
                    		

                    }
                    break;
                case 2 :
                    // PsiInternalXtext.g:1875:3: this_ParenthesizedCondition_1= ruleParenthesizedCondition
                    {

                    			markComposite(elementTypeProvider.getAtom_ParenthesizedConditionParserRuleCall_1ElementType());
                    		
                    pushFollow(FollowSets000.FOLLOW_2);
                    this_ParenthesizedCondition_1=ruleParenthesizedCondition();

                    state._fsp--;


                    			current = this_ParenthesizedCondition_1;
                    			doneComposite();
                    		

                    }
                    break;
                case 3 :
                    // PsiInternalXtext.g:1884:3: this_LiteralCondition_2= ruleLiteralCondition
                    {

                    			markComposite(elementTypeProvider.getAtom_LiteralConditionParserRuleCall_2ElementType());
                    		
                    pushFollow(FollowSets000.FOLLOW_2);
                    this_LiteralCondition_2=ruleLiteralCondition();

                    state._fsp--;


                    			current = this_LiteralCondition_2;
                    			doneComposite();
                    		

                    }
                    break;

            }


            }

        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
        }
        finally {
        }
        return current;
    }
    // $ANTLR end "ruleAtom"


    // $ANTLR start "entryRuleParenthesizedCondition"
    // PsiInternalXtext.g:1896:1: entryRuleParenthesizedCondition returns [Boolean current=false] : iv_ruleParenthesizedCondition= ruleParenthesizedCondition EOF ;
    public final Boolean entryRuleParenthesizedCondition() throws RecognitionException {
        Boolean current = false;

        Boolean iv_ruleParenthesizedCondition = null;


        try {
            // PsiInternalXtext.g:1896:64: (iv_ruleParenthesizedCondition= ruleParenthesizedCondition EOF )
            // PsiInternalXtext.g:1897:2: iv_ruleParenthesizedCondition= ruleParenthesizedCondition EOF
            {
             markComposite(elementTypeProvider.getParenthesizedConditionElementType()); 
            pushFollow(FollowSets000.FOLLOW_1);
            iv_ruleParenthesizedCondition=ruleParenthesizedCondition();

            state._fsp--;

             current =iv_ruleParenthesizedCondition; 
            match(input,EOF,FollowSets000.FOLLOW_2); 

            }

        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
        }
        finally {
        }
        return current;
    }
    // $ANTLR end "entryRuleParenthesizedCondition"


    // $ANTLR start "ruleParenthesizedCondition"
    // PsiInternalXtext.g:1903:1: ruleParenthesizedCondition returns [Boolean current=false] : (otherlv_0= '(' this_Disjunction_1= ruleDisjunction otherlv_2= ')' ) ;
    public final Boolean ruleParenthesizedCondition() throws RecognitionException {
        Boolean current = false;

        Token otherlv_0=null;
        Token otherlv_2=null;
        Boolean this_Disjunction_1 = null;


        try {
            // PsiInternalXtext.g:1904:1: ( (otherlv_0= '(' this_Disjunction_1= ruleDisjunction otherlv_2= ')' ) )
            // PsiInternalXtext.g:1905:2: (otherlv_0= '(' this_Disjunction_1= ruleDisjunction otherlv_2= ')' )
            {
            // PsiInternalXtext.g:1905:2: (otherlv_0= '(' this_Disjunction_1= ruleDisjunction otherlv_2= ')' )
            // PsiInternalXtext.g:1906:3: otherlv_0= '(' this_Disjunction_1= ruleDisjunction otherlv_2= ')'
            {

            			markLeaf(elementTypeProvider.getParenthesizedCondition_LeftParenthesisKeyword_0ElementType());
            		
            otherlv_0=(Token)match(input,15,FollowSets000.FOLLOW_26); 

            			doneLeaf(otherlv_0);
            		

            			markComposite(elementTypeProvider.getParenthesizedCondition_DisjunctionParserRuleCall_1ElementType());
            		
            pushFollow(FollowSets000.FOLLOW_37);
            this_Disjunction_1=ruleDisjunction();

            state._fsp--;


            			current = this_Disjunction_1;
            			doneComposite();
            		

            			markLeaf(elementTypeProvider.getParenthesizedCondition_RightParenthesisKeyword_2ElementType());
            		
            otherlv_2=(Token)match(input,16,FollowSets000.FOLLOW_2); 

            			doneLeaf(otherlv_2);
            		

            }


            }

        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
        }
        finally {
        }
        return current;
    }
    // $ANTLR end "ruleParenthesizedCondition"


    // $ANTLR start "entryRuleParameterReference"
    // PsiInternalXtext.g:1932:1: entryRuleParameterReference returns [Boolean current=false] : iv_ruleParameterReference= ruleParameterReference EOF ;
    public final Boolean entryRuleParameterReference() throws RecognitionException {
        Boolean current = false;

        Boolean iv_ruleParameterReference = null;


        try {
            // PsiInternalXtext.g:1932:60: (iv_ruleParameterReference= ruleParameterReference EOF )
            // PsiInternalXtext.g:1933:2: iv_ruleParameterReference= ruleParameterReference EOF
            {
             markComposite(elementTypeProvider.getParameterReferenceElementType()); 
            pushFollow(FollowSets000.FOLLOW_1);
            iv_ruleParameterReference=ruleParameterReference();

            state._fsp--;

             current =iv_ruleParameterReference; 
            match(input,EOF,FollowSets000.FOLLOW_2); 

            }

        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
        }
        finally {
        }
        return current;
    }
    // $ANTLR end "entryRuleParameterReference"


    // $ANTLR start "ruleParameterReference"
    // PsiInternalXtext.g:1939:1: ruleParameterReference returns [Boolean current=false] : ( (otherlv_0= RULE_ID ) ) ;
    public final Boolean ruleParameterReference() throws RecognitionException {
        Boolean current = false;

        Token otherlv_0=null;

        try {
            // PsiInternalXtext.g:1940:1: ( ( (otherlv_0= RULE_ID ) ) )
            // PsiInternalXtext.g:1941:2: ( (otherlv_0= RULE_ID ) )
            {
            // PsiInternalXtext.g:1941:2: ( (otherlv_0= RULE_ID ) )
            // PsiInternalXtext.g:1942:3: (otherlv_0= RULE_ID )
            {
            // PsiInternalXtext.g:1942:3: (otherlv_0= RULE_ID )
            // PsiInternalXtext.g:1943:4: otherlv_0= RULE_ID
            {

            				if (!current) {
            					associateWithSemanticElement();
            					current = true;
            				}
            			

            				markLeaf(elementTypeProvider.getParameterReference_ParameterParameterCrossReference_0ElementType());
            			
            otherlv_0=(Token)match(input,RULE_ID,FollowSets000.FOLLOW_2); 

            				doneLeaf(otherlv_0);
            			

            }


            }


            }

        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
        }
        finally {
        }
        return current;
    }
    // $ANTLR end "ruleParameterReference"


    // $ANTLR start "entryRuleTerminalRuleCall"
    // PsiInternalXtext.g:1961:1: entryRuleTerminalRuleCall returns [Boolean current=false] : iv_ruleTerminalRuleCall= ruleTerminalRuleCall EOF ;
    public final Boolean entryRuleTerminalRuleCall() throws RecognitionException {
        Boolean current = false;

        Boolean iv_ruleTerminalRuleCall = null;


        try {
            // PsiInternalXtext.g:1961:58: (iv_ruleTerminalRuleCall= ruleTerminalRuleCall EOF )
            // PsiInternalXtext.g:1962:2: iv_ruleTerminalRuleCall= ruleTerminalRuleCall EOF
            {
             markComposite(elementTypeProvider.getTerminalRuleCallElementType()); 
            pushFollow(FollowSets000.FOLLOW_1);
            iv_ruleTerminalRuleCall=ruleTerminalRuleCall();

            state._fsp--;

             current =iv_ruleTerminalRuleCall; 
            match(input,EOF,FollowSets000.FOLLOW_2); 

            }

        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
        }
        finally {
        }
        return current;
    }
    // $ANTLR end "entryRuleTerminalRuleCall"


    // $ANTLR start "ruleTerminalRuleCall"
    // PsiInternalXtext.g:1968:1: ruleTerminalRuleCall returns [Boolean current=false] : ( ( ruleRuleID ) ) ;
    public final Boolean ruleTerminalRuleCall() throws RecognitionException {
        Boolean current = false;

        try {
            // PsiInternalXtext.g:1969:1: ( ( ( ruleRuleID ) ) )
            // PsiInternalXtext.g:1970:2: ( ( ruleRuleID ) )
            {
            // PsiInternalXtext.g:1970:2: ( ( ruleRuleID ) )
            // PsiInternalXtext.g:1971:3: ( ruleRuleID )
            {
            // PsiInternalXtext.g:1971:3: ( ruleRuleID )
            // PsiInternalXtext.g:1972:4: ruleRuleID
            {

            				if (!current) {
            					associateWithSemanticElement();
            					current = true;
            				}
            			

            				markComposite(elementTypeProvider.getTerminalRuleCall_RuleAbstractRuleCrossReference_0ElementType());
            			
            pushFollow(FollowSets000.FOLLOW_2);
            ruleRuleID();

            state._fsp--;


            				doneComposite();
            			

            }


            }


            }

        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
        }
        finally {
        }
        return current;
    }
    // $ANTLR end "ruleTerminalRuleCall"


    // $ANTLR start "entryRuleRuleID"
    // PsiInternalXtext.g:1990:1: entryRuleRuleID returns [Boolean current=false] : iv_ruleRuleID= ruleRuleID EOF ;
    public final Boolean entryRuleRuleID() throws RecognitionException {
        Boolean current = false;

        Boolean iv_ruleRuleID = null;


        try {
            // PsiInternalXtext.g:1990:48: (iv_ruleRuleID= ruleRuleID EOF )
            // PsiInternalXtext.g:1991:2: iv_ruleRuleID= ruleRuleID EOF
            {
             markComposite(elementTypeProvider.getRuleIDElementType()); 
            pushFollow(FollowSets000.FOLLOW_1);
            iv_ruleRuleID=ruleRuleID();

            state._fsp--;

             current =iv_ruleRuleID; 
            match(input,EOF,FollowSets000.FOLLOW_2); 

            }

        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
        }
        finally {
        }
        return current;
    }
    // $ANTLR end "entryRuleRuleID"


    // $ANTLR start "ruleRuleID"
    // PsiInternalXtext.g:1997:1: ruleRuleID returns [Boolean current=false] : ( ruleValidID (kw= '::' ruleValidID )* ) ;
    public final Boolean ruleRuleID() throws RecognitionException {
        Boolean current = false;

        Token kw=null;

        try {
            // PsiInternalXtext.g:1998:1: ( ( ruleValidID (kw= '::' ruleValidID )* ) )
            // PsiInternalXtext.g:1999:2: ( ruleValidID (kw= '::' ruleValidID )* )
            {
            // PsiInternalXtext.g:1999:2: ( ruleValidID (kw= '::' ruleValidID )* )
            // PsiInternalXtext.g:2000:3: ruleValidID (kw= '::' ruleValidID )*
            {

            			markComposite(elementTypeProvider.getRuleID_ValidIDParserRuleCall_0ElementType());
            		
            pushFollow(FollowSets000.FOLLOW_38);
            ruleValidID();

            state._fsp--;


            			doneComposite();
            		
            // PsiInternalXtext.g:2007:3: (kw= '::' ruleValidID )*
            loop48:
            do {
                int alt48=2;
                int LA48_0 = input.LA(1);

                if ( (LA48_0==29) ) {
                    alt48=1;
                }


                switch (alt48) {
            	case 1 :
            	    // PsiInternalXtext.g:2008:4: kw= '::' ruleValidID
            	    {

            	    				markLeaf(elementTypeProvider.getRuleID_ColonColonKeyword_1_0ElementType());
            	    			
            	    kw=(Token)match(input,29,FollowSets000.FOLLOW_3); 

            	    				doneLeaf(kw);
            	    			

            	    				markComposite(elementTypeProvider.getRuleID_ValidIDParserRuleCall_1_1ElementType());
            	    			
            	    pushFollow(FollowSets000.FOLLOW_38);
            	    ruleValidID();

            	    state._fsp--;


            	    				doneComposite();
            	    			

            	    }
            	    break;

            	default :
            	    break loop48;
                }
            } while (true);


            }


            }

        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
        }
        finally {
        }
        return current;
    }
    // $ANTLR end "ruleRuleID"


    // $ANTLR start "entryRuleValidID"
    // PsiInternalXtext.g:2027:1: entryRuleValidID returns [Boolean current=false] : iv_ruleValidID= ruleValidID EOF ;
    public final Boolean entryRuleValidID() throws RecognitionException {
        Boolean current = false;

        Boolean iv_ruleValidID = null;


        try {
            // PsiInternalXtext.g:2027:49: (iv_ruleValidID= ruleValidID EOF )
            // PsiInternalXtext.g:2028:2: iv_ruleValidID= ruleValidID EOF
            {
             markComposite(elementTypeProvider.getValidIDElementType()); 
            pushFollow(FollowSets000.FOLLOW_1);
            iv_ruleValidID=ruleValidID();

            state._fsp--;

             current =iv_ruleValidID; 
            match(input,EOF,FollowSets000.FOLLOW_2); 

            }

        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
        }
        finally {
        }
        return current;
    }
    // $ANTLR end "entryRuleValidID"


    // $ANTLR start "ruleValidID"
    // PsiInternalXtext.g:2034:1: ruleValidID returns [Boolean current=false] : (this_ID_0= RULE_ID | kw= 'true' | kw= 'false' ) ;
    public final Boolean ruleValidID() throws RecognitionException {
        Boolean current = false;

        Token this_ID_0=null;
        Token kw=null;

        try {
            // PsiInternalXtext.g:2035:1: ( (this_ID_0= RULE_ID | kw= 'true' | kw= 'false' ) )
            // PsiInternalXtext.g:2036:2: (this_ID_0= RULE_ID | kw= 'true' | kw= 'false' )
            {
            // PsiInternalXtext.g:2036:2: (this_ID_0= RULE_ID | kw= 'true' | kw= 'false' )
            int alt49=3;
            switch ( input.LA(1) ) {
            case RULE_ID:
                {
                alt49=1;
                }
                break;
            case 39:
                {
                alt49=2;
                }
                break;
            case 40:
                {
                alt49=3;
                }
                break;
            default:
                NoViableAltException nvae =
                    new NoViableAltException("", 49, 0, input);

                throw nvae;
            }

            switch (alt49) {
                case 1 :
                    // PsiInternalXtext.g:2037:3: this_ID_0= RULE_ID
                    {

                    			markLeaf(elementTypeProvider.getValidID_IDTerminalRuleCall_0ElementType());
                    		
                    this_ID_0=(Token)match(input,RULE_ID,FollowSets000.FOLLOW_2); 

                    			doneLeaf(this_ID_0);
                    		

                    }
                    break;
                case 2 :
                    // PsiInternalXtext.g:2045:3: kw= 'true'
                    {

                    			markLeaf(elementTypeProvider.getValidID_TrueKeyword_1ElementType());
                    		
                    kw=(Token)match(input,39,FollowSets000.FOLLOW_2); 

                    			doneLeaf(kw);
                    		

                    }
                    break;
                case 3 :
                    // PsiInternalXtext.g:2053:3: kw= 'false'
                    {

                    			markLeaf(elementTypeProvider.getValidID_FalseKeyword_2ElementType());
                    		
                    kw=(Token)match(input,40,FollowSets000.FOLLOW_2); 

                    			doneLeaf(kw);
                    		

                    }
                    break;

            }


            }

        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
        }
        finally {
        }
        return current;
    }
    // $ANTLR end "ruleValidID"


    // $ANTLR start "entryRulePredicatedKeyword"
    // PsiInternalXtext.g:2064:1: entryRulePredicatedKeyword returns [Boolean current=false] : iv_rulePredicatedKeyword= rulePredicatedKeyword EOF ;
    public final Boolean entryRulePredicatedKeyword() throws RecognitionException {
        Boolean current = false;

        Boolean iv_rulePredicatedKeyword = null;


        try {
            // PsiInternalXtext.g:2064:59: (iv_rulePredicatedKeyword= rulePredicatedKeyword EOF )
            // PsiInternalXtext.g:2065:2: iv_rulePredicatedKeyword= rulePredicatedKeyword EOF
            {
             markComposite(elementTypeProvider.getPredicatedKeywordElementType()); 
            pushFollow(FollowSets000.FOLLOW_1);
            iv_rulePredicatedKeyword=rulePredicatedKeyword();

            state._fsp--;

             current =iv_rulePredicatedKeyword; 
            match(input,EOF,FollowSets000.FOLLOW_2); 

            }

        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
        }
        finally {
        }
        return current;
    }
    // $ANTLR end "entryRulePredicatedKeyword"


    // $ANTLR start "rulePredicatedKeyword"
    // PsiInternalXtext.g:2071:1: rulePredicatedKeyword returns [Boolean current=false] : ( ( ( (lv_predicated_0_0= '=>' ) ) | ( (lv_firstSetPredicated_1_0= '->' ) ) ) ( (lv_value_2_0= RULE_STRING ) ) ) ;
    public final Boolean rulePredicatedKeyword() throws RecognitionException {
        Boolean current = false;

        Token lv_predicated_0_0=null;
        Token lv_firstSetPredicated_1_0=null;
        Token lv_value_2_0=null;

        try {
            // PsiInternalXtext.g:2072:1: ( ( ( ( (lv_predicated_0_0= '=>' ) ) | ( (lv_firstSetPredicated_1_0= '->' ) ) ) ( (lv_value_2_0= RULE_STRING ) ) ) )
            // PsiInternalXtext.g:2073:2: ( ( ( (lv_predicated_0_0= '=>' ) ) | ( (lv_firstSetPredicated_1_0= '->' ) ) ) ( (lv_value_2_0= RULE_STRING ) ) )
            {
            // PsiInternalXtext.g:2073:2: ( ( ( (lv_predicated_0_0= '=>' ) ) | ( (lv_firstSetPredicated_1_0= '->' ) ) ) ( (lv_value_2_0= RULE_STRING ) ) )
            // PsiInternalXtext.g:2074:3: ( ( (lv_predicated_0_0= '=>' ) ) | ( (lv_firstSetPredicated_1_0= '->' ) ) ) ( (lv_value_2_0= RULE_STRING ) )
            {
            // PsiInternalXtext.g:2074:3: ( ( (lv_predicated_0_0= '=>' ) ) | ( (lv_firstSetPredicated_1_0= '->' ) ) )
            int alt50=2;
            int LA50_0 = input.LA(1);

            if ( (LA50_0==42) ) {
                alt50=1;
            }
            else if ( (LA50_0==43) ) {
                alt50=2;
            }
            else {
                NoViableAltException nvae =
                    new NoViableAltException("", 50, 0, input);

                throw nvae;
            }
            switch (alt50) {
                case 1 :
                    // PsiInternalXtext.g:2075:4: ( (lv_predicated_0_0= '=>' ) )
                    {
                    // PsiInternalXtext.g:2075:4: ( (lv_predicated_0_0= '=>' ) )
                    // PsiInternalXtext.g:2076:5: (lv_predicated_0_0= '=>' )
                    {
                    // PsiInternalXtext.g:2076:5: (lv_predicated_0_0= '=>' )
                    // PsiInternalXtext.g:2077:6: lv_predicated_0_0= '=>'
                    {

                    						markLeaf(elementTypeProvider.getPredicatedKeyword_PredicatedEqualsSignGreaterThanSignKeyword_0_0_0ElementType());
                    					
                    lv_predicated_0_0=(Token)match(input,42,FollowSets000.FOLLOW_11); 

                    						doneLeaf(lv_predicated_0_0);
                    					

                    						if (!current) {
                    							associateWithSemanticElement();
                    							current = true;
                    						}
                    					

                    }


                    }


                    }
                    break;
                case 2 :
                    // PsiInternalXtext.g:2093:4: ( (lv_firstSetPredicated_1_0= '->' ) )
                    {
                    // PsiInternalXtext.g:2093:4: ( (lv_firstSetPredicated_1_0= '->' ) )
                    // PsiInternalXtext.g:2094:5: (lv_firstSetPredicated_1_0= '->' )
                    {
                    // PsiInternalXtext.g:2094:5: (lv_firstSetPredicated_1_0= '->' )
                    // PsiInternalXtext.g:2095:6: lv_firstSetPredicated_1_0= '->'
                    {

                    						markLeaf(elementTypeProvider.getPredicatedKeyword_FirstSetPredicatedHyphenMinusGreaterThanSignKeyword_0_1_0ElementType());
                    					
                    lv_firstSetPredicated_1_0=(Token)match(input,43,FollowSets000.FOLLOW_11); 

                    						doneLeaf(lv_firstSetPredicated_1_0);
                    					

                    						if (!current) {
                    							associateWithSemanticElement();
                    							current = true;
                    						}
                    					

                    }


                    }


                    }
                    break;

            }

            // PsiInternalXtext.g:2111:3: ( (lv_value_2_0= RULE_STRING ) )
            // PsiInternalXtext.g:2112:4: (lv_value_2_0= RULE_STRING )
            {
            // PsiInternalXtext.g:2112:4: (lv_value_2_0= RULE_STRING )
            // PsiInternalXtext.g:2113:5: lv_value_2_0= RULE_STRING
            {

            					markLeaf(elementTypeProvider.getPredicatedKeyword_ValueSTRINGTerminalRuleCall_1_0ElementType());
            				
            lv_value_2_0=(Token)match(input,RULE_STRING,FollowSets000.FOLLOW_2); 

            					if(!current) {
            						associateWithSemanticElement();
            						current = true;
            					}
            				

            					doneLeaf(lv_value_2_0);
            				

            }


            }


            }


            }

        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
        }
        finally {
        }
        return current;
    }
    // $ANTLR end "rulePredicatedKeyword"


    // $ANTLR start "entryRulePredicatedRuleCall"
    // PsiInternalXtext.g:2132:1: entryRulePredicatedRuleCall returns [Boolean current=false] : iv_rulePredicatedRuleCall= rulePredicatedRuleCall EOF ;
    public final Boolean entryRulePredicatedRuleCall() throws RecognitionException {
        Boolean current = false;

        Boolean iv_rulePredicatedRuleCall = null;


        try {
            // PsiInternalXtext.g:2132:60: (iv_rulePredicatedRuleCall= rulePredicatedRuleCall EOF )
            // PsiInternalXtext.g:2133:2: iv_rulePredicatedRuleCall= rulePredicatedRuleCall EOF
            {
             markComposite(elementTypeProvider.getPredicatedRuleCallElementType()); 
            pushFollow(FollowSets000.FOLLOW_1);
            iv_rulePredicatedRuleCall=rulePredicatedRuleCall();

            state._fsp--;

             current =iv_rulePredicatedRuleCall; 
            match(input,EOF,FollowSets000.FOLLOW_2); 

            }

        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
        }
        finally {
        }
        return current;
    }
    // $ANTLR end "entryRulePredicatedRuleCall"


    // $ANTLR start "rulePredicatedRuleCall"
    // PsiInternalXtext.g:2139:1: rulePredicatedRuleCall returns [Boolean current=false] : ( ( ( (lv_predicated_0_0= '=>' ) ) | ( (lv_firstSetPredicated_1_0= '->' ) ) ) ( ( ruleRuleID ) ) (otherlv_3= '<' ( (lv_arguments_4_0= ruleNamedArgument ) ) (otherlv_5= ',' ( (lv_arguments_6_0= ruleNamedArgument ) ) )* otherlv_7= '>' )? ) ;
    public final Boolean rulePredicatedRuleCall() throws RecognitionException {
        Boolean current = false;

        Token lv_predicated_0_0=null;
        Token lv_firstSetPredicated_1_0=null;
        Token otherlv_3=null;
        Token otherlv_5=null;
        Token otherlv_7=null;
        Boolean lv_arguments_4_0 = null;

        Boolean lv_arguments_6_0 = null;


        try {
            // PsiInternalXtext.g:2140:1: ( ( ( ( (lv_predicated_0_0= '=>' ) ) | ( (lv_firstSetPredicated_1_0= '->' ) ) ) ( ( ruleRuleID ) ) (otherlv_3= '<' ( (lv_arguments_4_0= ruleNamedArgument ) ) (otherlv_5= ',' ( (lv_arguments_6_0= ruleNamedArgument ) ) )* otherlv_7= '>' )? ) )
            // PsiInternalXtext.g:2141:2: ( ( ( (lv_predicated_0_0= '=>' ) ) | ( (lv_firstSetPredicated_1_0= '->' ) ) ) ( ( ruleRuleID ) ) (otherlv_3= '<' ( (lv_arguments_4_0= ruleNamedArgument ) ) (otherlv_5= ',' ( (lv_arguments_6_0= ruleNamedArgument ) ) )* otherlv_7= '>' )? )
            {
            // PsiInternalXtext.g:2141:2: ( ( ( (lv_predicated_0_0= '=>' ) ) | ( (lv_firstSetPredicated_1_0= '->' ) ) ) ( ( ruleRuleID ) ) (otherlv_3= '<' ( (lv_arguments_4_0= ruleNamedArgument ) ) (otherlv_5= ',' ( (lv_arguments_6_0= ruleNamedArgument ) ) )* otherlv_7= '>' )? )
            // PsiInternalXtext.g:2142:3: ( ( (lv_predicated_0_0= '=>' ) ) | ( (lv_firstSetPredicated_1_0= '->' ) ) ) ( ( ruleRuleID ) ) (otherlv_3= '<' ( (lv_arguments_4_0= ruleNamedArgument ) ) (otherlv_5= ',' ( (lv_arguments_6_0= ruleNamedArgument ) ) )* otherlv_7= '>' )?
            {
            // PsiInternalXtext.g:2142:3: ( ( (lv_predicated_0_0= '=>' ) ) | ( (lv_firstSetPredicated_1_0= '->' ) ) )
            int alt51=2;
            int LA51_0 = input.LA(1);

            if ( (LA51_0==42) ) {
                alt51=1;
            }
            else if ( (LA51_0==43) ) {
                alt51=2;
            }
            else {
                NoViableAltException nvae =
                    new NoViableAltException("", 51, 0, input);

                throw nvae;
            }
            switch (alt51) {
                case 1 :
                    // PsiInternalXtext.g:2143:4: ( (lv_predicated_0_0= '=>' ) )
                    {
                    // PsiInternalXtext.g:2143:4: ( (lv_predicated_0_0= '=>' ) )
                    // PsiInternalXtext.g:2144:5: (lv_predicated_0_0= '=>' )
                    {
                    // PsiInternalXtext.g:2144:5: (lv_predicated_0_0= '=>' )
                    // PsiInternalXtext.g:2145:6: lv_predicated_0_0= '=>'
                    {

                    						markLeaf(elementTypeProvider.getPredicatedRuleCall_PredicatedEqualsSignGreaterThanSignKeyword_0_0_0ElementType());
                    					
                    lv_predicated_0_0=(Token)match(input,42,FollowSets000.FOLLOW_3); 

                    						doneLeaf(lv_predicated_0_0);
                    					

                    						if (!current) {
                    							associateWithSemanticElement();
                    							current = true;
                    						}
                    					

                    }


                    }


                    }
                    break;
                case 2 :
                    // PsiInternalXtext.g:2161:4: ( (lv_firstSetPredicated_1_0= '->' ) )
                    {
                    // PsiInternalXtext.g:2161:4: ( (lv_firstSetPredicated_1_0= '->' ) )
                    // PsiInternalXtext.g:2162:5: (lv_firstSetPredicated_1_0= '->' )
                    {
                    // PsiInternalXtext.g:2162:5: (lv_firstSetPredicated_1_0= '->' )
                    // PsiInternalXtext.g:2163:6: lv_firstSetPredicated_1_0= '->'
                    {

                    						markLeaf(elementTypeProvider.getPredicatedRuleCall_FirstSetPredicatedHyphenMinusGreaterThanSignKeyword_0_1_0ElementType());
                    					
                    lv_firstSetPredicated_1_0=(Token)match(input,43,FollowSets000.FOLLOW_3); 

                    						doneLeaf(lv_firstSetPredicated_1_0);
                    					

                    						if (!current) {
                    							associateWithSemanticElement();
                    							current = true;
                    						}
                    					

                    }


                    }


                    }
                    break;

            }

            // PsiInternalXtext.g:2179:3: ( ( ruleRuleID ) )
            // PsiInternalXtext.g:2180:4: ( ruleRuleID )
            {
            // PsiInternalXtext.g:2180:4: ( ruleRuleID )
            // PsiInternalXtext.g:2181:5: ruleRuleID
            {

            					if (!current) {
            						associateWithSemanticElement();
            						current = true;
            					}
            				

            					markComposite(elementTypeProvider.getPredicatedRuleCall_RuleAbstractRuleCrossReference_1_0ElementType());
            				
            pushFollow(FollowSets000.FOLLOW_21);
            ruleRuleID();

            state._fsp--;


            					doneComposite();
            				

            }


            }

            // PsiInternalXtext.g:2196:3: (otherlv_3= '<' ( (lv_arguments_4_0= ruleNamedArgument ) ) (otherlv_5= ',' ( (lv_arguments_6_0= ruleNamedArgument ) ) )* otherlv_7= '>' )?
            int alt53=2;
            int LA53_0 = input.LA(1);

            if ( (LA53_0==27) ) {
                alt53=1;
            }
            switch (alt53) {
                case 1 :
                    // PsiInternalXtext.g:2197:4: otherlv_3= '<' ( (lv_arguments_4_0= ruleNamedArgument ) ) (otherlv_5= ',' ( (lv_arguments_6_0= ruleNamedArgument ) ) )* otherlv_7= '>'
                    {

                    				markLeaf(elementTypeProvider.getPredicatedRuleCall_LessThanSignKeyword_2_0ElementType());
                    			
                    otherlv_3=(Token)match(input,27,FollowSets000.FOLLOW_26); 

                    				doneLeaf(otherlv_3);
                    			
                    // PsiInternalXtext.g:2204:4: ( (lv_arguments_4_0= ruleNamedArgument ) )
                    // PsiInternalXtext.g:2205:5: (lv_arguments_4_0= ruleNamedArgument )
                    {
                    // PsiInternalXtext.g:2205:5: (lv_arguments_4_0= ruleNamedArgument )
                    // PsiInternalXtext.g:2206:6: lv_arguments_4_0= ruleNamedArgument
                    {

                    						markComposite(elementTypeProvider.getPredicatedRuleCall_ArgumentsNamedArgumentParserRuleCall_2_1_0ElementType());
                    					
                    pushFollow(FollowSets000.FOLLOW_23);
                    lv_arguments_4_0=ruleNamedArgument();

                    state._fsp--;


                    						doneComposite();
                    						if(!current) {
                    							associateWithSemanticElement();
                    							current = true;
                    						}
                    					

                    }


                    }

                    // PsiInternalXtext.g:2219:4: (otherlv_5= ',' ( (lv_arguments_6_0= ruleNamedArgument ) ) )*
                    loop52:
                    do {
                        int alt52=2;
                        int LA52_0 = input.LA(1);

                        if ( (LA52_0==13) ) {
                            alt52=1;
                        }


                        switch (alt52) {
                    	case 1 :
                    	    // PsiInternalXtext.g:2220:5: otherlv_5= ',' ( (lv_arguments_6_0= ruleNamedArgument ) )
                    	    {

                    	    					markLeaf(elementTypeProvider.getPredicatedRuleCall_CommaKeyword_2_2_0ElementType());
                    	    				
                    	    otherlv_5=(Token)match(input,13,FollowSets000.FOLLOW_26); 

                    	    					doneLeaf(otherlv_5);
                    	    				
                    	    // PsiInternalXtext.g:2227:5: ( (lv_arguments_6_0= ruleNamedArgument ) )
                    	    // PsiInternalXtext.g:2228:6: (lv_arguments_6_0= ruleNamedArgument )
                    	    {
                    	    // PsiInternalXtext.g:2228:6: (lv_arguments_6_0= ruleNamedArgument )
                    	    // PsiInternalXtext.g:2229:7: lv_arguments_6_0= ruleNamedArgument
                    	    {

                    	    							markComposite(elementTypeProvider.getPredicatedRuleCall_ArgumentsNamedArgumentParserRuleCall_2_2_1_0ElementType());
                    	    						
                    	    pushFollow(FollowSets000.FOLLOW_23);
                    	    lv_arguments_6_0=ruleNamedArgument();

                    	    state._fsp--;


                    	    							doneComposite();
                    	    							if(!current) {
                    	    								associateWithSemanticElement();
                    	    								current = true;
                    	    							}
                    	    						

                    	    }


                    	    }


                    	    }
                    	    break;

                    	default :
                    	    break loop52;
                        }
                    } while (true);


                    				markLeaf(elementTypeProvider.getPredicatedRuleCall_GreaterThanSignKeyword_2_3ElementType());
                    			
                    otherlv_7=(Token)match(input,28,FollowSets000.FOLLOW_2); 

                    				doneLeaf(otherlv_7);
                    			

                    }
                    break;

            }


            }


            }

        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
        }
        finally {
        }
        return current;
    }
    // $ANTLR end "rulePredicatedRuleCall"


    // $ANTLR start "entryRuleAssignment"
    // PsiInternalXtext.g:2255:1: entryRuleAssignment returns [Boolean current=false] : iv_ruleAssignment= ruleAssignment EOF ;
    public final Boolean entryRuleAssignment() throws RecognitionException {
        Boolean current = false;

        Boolean iv_ruleAssignment = null;


        try {
            // PsiInternalXtext.g:2255:52: (iv_ruleAssignment= ruleAssignment EOF )
            // PsiInternalXtext.g:2256:2: iv_ruleAssignment= ruleAssignment EOF
            {
             markComposite(elementTypeProvider.getAssignmentElementType()); 
            pushFollow(FollowSets000.FOLLOW_1);
            iv_ruleAssignment=ruleAssignment();

            state._fsp--;

             current =iv_ruleAssignment; 
            match(input,EOF,FollowSets000.FOLLOW_2); 

            }

        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
        }
        finally {
        }
        return current;
    }
    // $ANTLR end "entryRuleAssignment"


    // $ANTLR start "ruleAssignment"
    // PsiInternalXtext.g:2262:1: ruleAssignment returns [Boolean current=false] : ( ( ( (lv_predicated_0_0= '=>' ) ) | ( (lv_firstSetPredicated_1_0= '->' ) ) )? ( (lv_feature_2_0= ruleValidID ) ) ( ( (lv_operator_3_1= '+=' | lv_operator_3_2= '=' | lv_operator_3_3= '?=' ) ) ) ( (lv_terminal_4_0= ruleAssignableTerminal ) ) ) ;
    public final Boolean ruleAssignment() throws RecognitionException {
        Boolean current = false;

        Token lv_predicated_0_0=null;
        Token lv_firstSetPredicated_1_0=null;
        Token lv_operator_3_1=null;
        Token lv_operator_3_2=null;
        Token lv_operator_3_3=null;
        Boolean lv_feature_2_0 = null;

        Boolean lv_terminal_4_0 = null;


        try {
            // PsiInternalXtext.g:2263:1: ( ( ( ( (lv_predicated_0_0= '=>' ) ) | ( (lv_firstSetPredicated_1_0= '->' ) ) )? ( (lv_feature_2_0= ruleValidID ) ) ( ( (lv_operator_3_1= '+=' | lv_operator_3_2= '=' | lv_operator_3_3= '?=' ) ) ) ( (lv_terminal_4_0= ruleAssignableTerminal ) ) ) )
            // PsiInternalXtext.g:2264:2: ( ( ( (lv_predicated_0_0= '=>' ) ) | ( (lv_firstSetPredicated_1_0= '->' ) ) )? ( (lv_feature_2_0= ruleValidID ) ) ( ( (lv_operator_3_1= '+=' | lv_operator_3_2= '=' | lv_operator_3_3= '?=' ) ) ) ( (lv_terminal_4_0= ruleAssignableTerminal ) ) )
            {
            // PsiInternalXtext.g:2264:2: ( ( ( (lv_predicated_0_0= '=>' ) ) | ( (lv_firstSetPredicated_1_0= '->' ) ) )? ( (lv_feature_2_0= ruleValidID ) ) ( ( (lv_operator_3_1= '+=' | lv_operator_3_2= '=' | lv_operator_3_3= '?=' ) ) ) ( (lv_terminal_4_0= ruleAssignableTerminal ) ) )
            // PsiInternalXtext.g:2265:3: ( ( (lv_predicated_0_0= '=>' ) ) | ( (lv_firstSetPredicated_1_0= '->' ) ) )? ( (lv_feature_2_0= ruleValidID ) ) ( ( (lv_operator_3_1= '+=' | lv_operator_3_2= '=' | lv_operator_3_3= '?=' ) ) ) ( (lv_terminal_4_0= ruleAssignableTerminal ) )
            {
            // PsiInternalXtext.g:2265:3: ( ( (lv_predicated_0_0= '=>' ) ) | ( (lv_firstSetPredicated_1_0= '->' ) ) )?
            int alt54=3;
            int LA54_0 = input.LA(1);

            if ( (LA54_0==42) ) {
                alt54=1;
            }
            else if ( (LA54_0==43) ) {
                alt54=2;
            }
            switch (alt54) {
                case 1 :
                    // PsiInternalXtext.g:2266:4: ( (lv_predicated_0_0= '=>' ) )
                    {
                    // PsiInternalXtext.g:2266:4: ( (lv_predicated_0_0= '=>' ) )
                    // PsiInternalXtext.g:2267:5: (lv_predicated_0_0= '=>' )
                    {
                    // PsiInternalXtext.g:2267:5: (lv_predicated_0_0= '=>' )
                    // PsiInternalXtext.g:2268:6: lv_predicated_0_0= '=>'
                    {

                    						markLeaf(elementTypeProvider.getAssignment_PredicatedEqualsSignGreaterThanSignKeyword_0_0_0ElementType());
                    					
                    lv_predicated_0_0=(Token)match(input,42,FollowSets000.FOLLOW_3); 

                    						doneLeaf(lv_predicated_0_0);
                    					

                    						if (!current) {
                    							associateWithSemanticElement();
                    							current = true;
                    						}
                    					

                    }


                    }


                    }
                    break;
                case 2 :
                    // PsiInternalXtext.g:2284:4: ( (lv_firstSetPredicated_1_0= '->' ) )
                    {
                    // PsiInternalXtext.g:2284:4: ( (lv_firstSetPredicated_1_0= '->' ) )
                    // PsiInternalXtext.g:2285:5: (lv_firstSetPredicated_1_0= '->' )
                    {
                    // PsiInternalXtext.g:2285:5: (lv_firstSetPredicated_1_0= '->' )
                    // PsiInternalXtext.g:2286:6: lv_firstSetPredicated_1_0= '->'
                    {

                    						markLeaf(elementTypeProvider.getAssignment_FirstSetPredicatedHyphenMinusGreaterThanSignKeyword_0_1_0ElementType());
                    					
                    lv_firstSetPredicated_1_0=(Token)match(input,43,FollowSets000.FOLLOW_3); 

                    						doneLeaf(lv_firstSetPredicated_1_0);
                    					

                    						if (!current) {
                    							associateWithSemanticElement();
                    							current = true;
                    						}
                    					

                    }


                    }


                    }
                    break;

            }

            // PsiInternalXtext.g:2302:3: ( (lv_feature_2_0= ruleValidID ) )
            // PsiInternalXtext.g:2303:4: (lv_feature_2_0= ruleValidID )
            {
            // PsiInternalXtext.g:2303:4: (lv_feature_2_0= ruleValidID )
            // PsiInternalXtext.g:2304:5: lv_feature_2_0= ruleValidID
            {

            					markComposite(elementTypeProvider.getAssignment_FeatureValidIDParserRuleCall_1_0ElementType());
            				
            pushFollow(FollowSets000.FOLLOW_39);
            lv_feature_2_0=ruleValidID();

            state._fsp--;


            					doneComposite();
            					if(!current) {
            						associateWithSemanticElement();
            						current = true;
            					}
            				

            }


            }

            // PsiInternalXtext.g:2317:3: ( ( (lv_operator_3_1= '+=' | lv_operator_3_2= '=' | lv_operator_3_3= '?=' ) ) )
            // PsiInternalXtext.g:2318:4: ( (lv_operator_3_1= '+=' | lv_operator_3_2= '=' | lv_operator_3_3= '?=' ) )
            {
            // PsiInternalXtext.g:2318:4: ( (lv_operator_3_1= '+=' | lv_operator_3_2= '=' | lv_operator_3_3= '?=' ) )
            // PsiInternalXtext.g:2319:5: (lv_operator_3_1= '+=' | lv_operator_3_2= '=' | lv_operator_3_3= '?=' )
            {
            // PsiInternalXtext.g:2319:5: (lv_operator_3_1= '+=' | lv_operator_3_2= '=' | lv_operator_3_3= '?=' )
            int alt55=3;
            switch ( input.LA(1) ) {
            case 36:
                {
                alt55=1;
                }
                break;
            case 35:
                {
                alt55=2;
                }
                break;
            case 44:
                {
                alt55=3;
                }
                break;
            default:
                NoViableAltException nvae =
                    new NoViableAltException("", 55, 0, input);

                throw nvae;
            }

            switch (alt55) {
                case 1 :
                    // PsiInternalXtext.g:2320:6: lv_operator_3_1= '+='
                    {

                    						markLeaf(elementTypeProvider.getAssignment_OperatorPlusSignEqualsSignKeyword_2_0_0ElementType());
                    					
                    lv_operator_3_1=(Token)match(input,36,FollowSets000.FOLLOW_40); 

                    						doneLeaf(lv_operator_3_1);
                    					

                    						if (!current) {
                    							associateWithSemanticElement();
                    							current = true;
                    						}
                    					

                    }
                    break;
                case 2 :
                    // PsiInternalXtext.g:2334:6: lv_operator_3_2= '='
                    {

                    						markLeaf(elementTypeProvider.getAssignment_OperatorEqualsSignKeyword_2_0_1ElementType());
                    					
                    lv_operator_3_2=(Token)match(input,35,FollowSets000.FOLLOW_40); 

                    						doneLeaf(lv_operator_3_2);
                    					

                    						if (!current) {
                    							associateWithSemanticElement();
                    							current = true;
                    						}
                    					

                    }
                    break;
                case 3 :
                    // PsiInternalXtext.g:2348:6: lv_operator_3_3= '?='
                    {

                    						markLeaf(elementTypeProvider.getAssignment_OperatorQuestionMarkEqualsSignKeyword_2_0_2ElementType());
                    					
                    lv_operator_3_3=(Token)match(input,44,FollowSets000.FOLLOW_40); 

                    						doneLeaf(lv_operator_3_3);
                    					

                    						if (!current) {
                    							associateWithSemanticElement();
                    							current = true;
                    						}
                    					

                    }
                    break;

            }


            }


            }

            // PsiInternalXtext.g:2364:3: ( (lv_terminal_4_0= ruleAssignableTerminal ) )
            // PsiInternalXtext.g:2365:4: (lv_terminal_4_0= ruleAssignableTerminal )
            {
            // PsiInternalXtext.g:2365:4: (lv_terminal_4_0= ruleAssignableTerminal )
            // PsiInternalXtext.g:2366:5: lv_terminal_4_0= ruleAssignableTerminal
            {

            					markComposite(elementTypeProvider.getAssignment_TerminalAssignableTerminalParserRuleCall_3_0ElementType());
            				
            pushFollow(FollowSets000.FOLLOW_2);
            lv_terminal_4_0=ruleAssignableTerminal();

            state._fsp--;


            					doneComposite();
            					if(!current) {
            						associateWithSemanticElement();
            						current = true;
            					}
            				

            }


            }


            }


            }

        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
        }
        finally {
        }
        return current;
    }
    // $ANTLR end "ruleAssignment"


    // $ANTLR start "entryRuleAssignableTerminal"
    // PsiInternalXtext.g:2383:1: entryRuleAssignableTerminal returns [Boolean current=false] : iv_ruleAssignableTerminal= ruleAssignableTerminal EOF ;
    public final Boolean entryRuleAssignableTerminal() throws RecognitionException {
        Boolean current = false;

        Boolean iv_ruleAssignableTerminal = null;


        try {
            // PsiInternalXtext.g:2383:60: (iv_ruleAssignableTerminal= ruleAssignableTerminal EOF )
            // PsiInternalXtext.g:2384:2: iv_ruleAssignableTerminal= ruleAssignableTerminal EOF
            {
             markComposite(elementTypeProvider.getAssignableTerminalElementType()); 
            pushFollow(FollowSets000.FOLLOW_1);
            iv_ruleAssignableTerminal=ruleAssignableTerminal();

            state._fsp--;

             current =iv_ruleAssignableTerminal; 
            match(input,EOF,FollowSets000.FOLLOW_2); 

            }

        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
        }
        finally {
        }
        return current;
    }
    // $ANTLR end "entryRuleAssignableTerminal"


    // $ANTLR start "ruleAssignableTerminal"
    // PsiInternalXtext.g:2390:1: ruleAssignableTerminal returns [Boolean current=false] : (this_Keyword_0= ruleKeyword | this_RuleCall_1= ruleRuleCall | this_ParenthesizedAssignableElement_2= ruleParenthesizedAssignableElement | this_CrossReference_3= ruleCrossReference ) ;
    public final Boolean ruleAssignableTerminal() throws RecognitionException {
        Boolean current = false;

        Boolean this_Keyword_0 = null;

        Boolean this_RuleCall_1 = null;

        Boolean this_ParenthesizedAssignableElement_2 = null;

        Boolean this_CrossReference_3 = null;


        try {
            // PsiInternalXtext.g:2391:1: ( (this_Keyword_0= ruleKeyword | this_RuleCall_1= ruleRuleCall | this_ParenthesizedAssignableElement_2= ruleParenthesizedAssignableElement | this_CrossReference_3= ruleCrossReference ) )
            // PsiInternalXtext.g:2392:2: (this_Keyword_0= ruleKeyword | this_RuleCall_1= ruleRuleCall | this_ParenthesizedAssignableElement_2= ruleParenthesizedAssignableElement | this_CrossReference_3= ruleCrossReference )
            {
            // PsiInternalXtext.g:2392:2: (this_Keyword_0= ruleKeyword | this_RuleCall_1= ruleRuleCall | this_ParenthesizedAssignableElement_2= ruleParenthesizedAssignableElement | this_CrossReference_3= ruleCrossReference )
            int alt56=4;
            switch ( input.LA(1) ) {
            case RULE_STRING:
                {
                alt56=1;
                }
                break;
            case RULE_ID:
            case 39:
            case 40:
                {
                alt56=2;
                }
                break;
            case 15:
                {
                alt56=3;
                }
                break;
            case 45:
                {
                alt56=4;
                }
                break;
            default:
                NoViableAltException nvae =
                    new NoViableAltException("", 56, 0, input);

                throw nvae;
            }

            switch (alt56) {
                case 1 :
                    // PsiInternalXtext.g:2393:3: this_Keyword_0= ruleKeyword
                    {

                    			markComposite(elementTypeProvider.getAssignableTerminal_KeywordParserRuleCall_0ElementType());
                    		
                    pushFollow(FollowSets000.FOLLOW_2);
                    this_Keyword_0=ruleKeyword();

                    state._fsp--;


                    			current = this_Keyword_0;
                    			doneComposite();
                    		

                    }
                    break;
                case 2 :
                    // PsiInternalXtext.g:2402:3: this_RuleCall_1= ruleRuleCall
                    {

                    			markComposite(elementTypeProvider.getAssignableTerminal_RuleCallParserRuleCall_1ElementType());
                    		
                    pushFollow(FollowSets000.FOLLOW_2);
                    this_RuleCall_1=ruleRuleCall();

                    state._fsp--;


                    			current = this_RuleCall_1;
                    			doneComposite();
                    		

                    }
                    break;
                case 3 :
                    // PsiInternalXtext.g:2411:3: this_ParenthesizedAssignableElement_2= ruleParenthesizedAssignableElement
                    {

                    			markComposite(elementTypeProvider.getAssignableTerminal_ParenthesizedAssignableElementParserRuleCall_2ElementType());
                    		
                    pushFollow(FollowSets000.FOLLOW_2);
                    this_ParenthesizedAssignableElement_2=ruleParenthesizedAssignableElement();

                    state._fsp--;


                    			current = this_ParenthesizedAssignableElement_2;
                    			doneComposite();
                    		

                    }
                    break;
                case 4 :
                    // PsiInternalXtext.g:2420:3: this_CrossReference_3= ruleCrossReference
                    {

                    			markComposite(elementTypeProvider.getAssignableTerminal_CrossReferenceParserRuleCall_3ElementType());
                    		
                    pushFollow(FollowSets000.FOLLOW_2);
                    this_CrossReference_3=ruleCrossReference();

                    state._fsp--;


                    			current = this_CrossReference_3;
                    			doneComposite();
                    		

                    }
                    break;

            }


            }

        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
        }
        finally {
        }
        return current;
    }
    // $ANTLR end "ruleAssignableTerminal"


    // $ANTLR start "entryRuleParenthesizedAssignableElement"
    // PsiInternalXtext.g:2432:1: entryRuleParenthesizedAssignableElement returns [Boolean current=false] : iv_ruleParenthesizedAssignableElement= ruleParenthesizedAssignableElement EOF ;
    public final Boolean entryRuleParenthesizedAssignableElement() throws RecognitionException {
        Boolean current = false;

        Boolean iv_ruleParenthesizedAssignableElement = null;


        try {
            // PsiInternalXtext.g:2432:72: (iv_ruleParenthesizedAssignableElement= ruleParenthesizedAssignableElement EOF )
            // PsiInternalXtext.g:2433:2: iv_ruleParenthesizedAssignableElement= ruleParenthesizedAssignableElement EOF
            {
             markComposite(elementTypeProvider.getParenthesizedAssignableElementElementType()); 
            pushFollow(FollowSets000.FOLLOW_1);
            iv_ruleParenthesizedAssignableElement=ruleParenthesizedAssignableElement();

            state._fsp--;

             current =iv_ruleParenthesizedAssignableElement; 
            match(input,EOF,FollowSets000.FOLLOW_2); 

            }

        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
        }
        finally {
        }
        return current;
    }
    // $ANTLR end "entryRuleParenthesizedAssignableElement"


    // $ANTLR start "ruleParenthesizedAssignableElement"
    // PsiInternalXtext.g:2439:1: ruleParenthesizedAssignableElement returns [Boolean current=false] : (otherlv_0= '(' this_AssignableAlternatives_1= ruleAssignableAlternatives otherlv_2= ')' ) ;
    public final Boolean ruleParenthesizedAssignableElement() throws RecognitionException {
        Boolean current = false;

        Token otherlv_0=null;
        Token otherlv_2=null;
        Boolean this_AssignableAlternatives_1 = null;


        try {
            // PsiInternalXtext.g:2440:1: ( (otherlv_0= '(' this_AssignableAlternatives_1= ruleAssignableAlternatives otherlv_2= ')' ) )
            // PsiInternalXtext.g:2441:2: (otherlv_0= '(' this_AssignableAlternatives_1= ruleAssignableAlternatives otherlv_2= ')' )
            {
            // PsiInternalXtext.g:2441:2: (otherlv_0= '(' this_AssignableAlternatives_1= ruleAssignableAlternatives otherlv_2= ')' )
            // PsiInternalXtext.g:2442:3: otherlv_0= '(' this_AssignableAlternatives_1= ruleAssignableAlternatives otherlv_2= ')'
            {

            			markLeaf(elementTypeProvider.getParenthesizedAssignableElement_LeftParenthesisKeyword_0ElementType());
            		
            otherlv_0=(Token)match(input,15,FollowSets000.FOLLOW_40); 

            			doneLeaf(otherlv_0);
            		

            			markComposite(elementTypeProvider.getParenthesizedAssignableElement_AssignableAlternativesParserRuleCall_1ElementType());
            		
            pushFollow(FollowSets000.FOLLOW_37);
            this_AssignableAlternatives_1=ruleAssignableAlternatives();

            state._fsp--;


            			current = this_AssignableAlternatives_1;
            			doneComposite();
            		

            			markLeaf(elementTypeProvider.getParenthesizedAssignableElement_RightParenthesisKeyword_2ElementType());
            		
            otherlv_2=(Token)match(input,16,FollowSets000.FOLLOW_2); 

            			doneLeaf(otherlv_2);
            		

            }


            }

        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
        }
        finally {
        }
        return current;
    }
    // $ANTLR end "ruleParenthesizedAssignableElement"


    // $ANTLR start "entryRuleAssignableAlternatives"
    // PsiInternalXtext.g:2468:1: entryRuleAssignableAlternatives returns [Boolean current=false] : iv_ruleAssignableAlternatives= ruleAssignableAlternatives EOF ;
    public final Boolean entryRuleAssignableAlternatives() throws RecognitionException {
        Boolean current = false;

        Boolean iv_ruleAssignableAlternatives = null;


        try {
            // PsiInternalXtext.g:2468:64: (iv_ruleAssignableAlternatives= ruleAssignableAlternatives EOF )
            // PsiInternalXtext.g:2469:2: iv_ruleAssignableAlternatives= ruleAssignableAlternatives EOF
            {
             markComposite(elementTypeProvider.getAssignableAlternativesElementType()); 
            pushFollow(FollowSets000.FOLLOW_1);
            iv_ruleAssignableAlternatives=ruleAssignableAlternatives();

            state._fsp--;

             current =iv_ruleAssignableAlternatives; 
            match(input,EOF,FollowSets000.FOLLOW_2); 

            }

        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
        }
        finally {
        }
        return current;
    }
    // $ANTLR end "entryRuleAssignableAlternatives"


    // $ANTLR start "ruleAssignableAlternatives"
    // PsiInternalXtext.g:2475:1: ruleAssignableAlternatives returns [Boolean current=false] : (this_AssignableTerminal_0= ruleAssignableTerminal ( () (otherlv_2= '|' ( (lv_elements_3_0= ruleAssignableTerminal ) ) )+ )? ) ;
    public final Boolean ruleAssignableAlternatives() throws RecognitionException {
        Boolean current = false;

        Token otherlv_2=null;
        Boolean this_AssignableTerminal_0 = null;

        Boolean lv_elements_3_0 = null;


        try {
            // PsiInternalXtext.g:2476:1: ( (this_AssignableTerminal_0= ruleAssignableTerminal ( () (otherlv_2= '|' ( (lv_elements_3_0= ruleAssignableTerminal ) ) )+ )? ) )
            // PsiInternalXtext.g:2477:2: (this_AssignableTerminal_0= ruleAssignableTerminal ( () (otherlv_2= '|' ( (lv_elements_3_0= ruleAssignableTerminal ) ) )+ )? )
            {
            // PsiInternalXtext.g:2477:2: (this_AssignableTerminal_0= ruleAssignableTerminal ( () (otherlv_2= '|' ( (lv_elements_3_0= ruleAssignableTerminal ) ) )+ )? )
            // PsiInternalXtext.g:2478:3: this_AssignableTerminal_0= ruleAssignableTerminal ( () (otherlv_2= '|' ( (lv_elements_3_0= ruleAssignableTerminal ) ) )+ )?
            {

            			markComposite(elementTypeProvider.getAssignableAlternatives_AssignableTerminalParserRuleCall_0ElementType());
            		
            pushFollow(FollowSets000.FOLLOW_25);
            this_AssignableTerminal_0=ruleAssignableTerminal();

            state._fsp--;


            			current = this_AssignableTerminal_0;
            			doneComposite();
            		
            // PsiInternalXtext.g:2486:3: ( () (otherlv_2= '|' ( (lv_elements_3_0= ruleAssignableTerminal ) ) )+ )?
            int alt58=2;
            int LA58_0 = input.LA(1);

            if ( (LA58_0==30) ) {
                alt58=1;
            }
            switch (alt58) {
                case 1 :
                    // PsiInternalXtext.g:2487:4: () (otherlv_2= '|' ( (lv_elements_3_0= ruleAssignableTerminal ) ) )+
                    {
                    // PsiInternalXtext.g:2487:4: ()
                    // PsiInternalXtext.g:2488:5: 
                    {

                    					precedeComposite(elementTypeProvider.getAssignableAlternatives_AlternativesElementsAction_1_0ElementType());
                    					doneComposite();
                    					associateWithSemanticElement();
                    				

                    }

                    // PsiInternalXtext.g:2494:4: (otherlv_2= '|' ( (lv_elements_3_0= ruleAssignableTerminal ) ) )+
                    int cnt57=0;
                    loop57:
                    do {
                        int alt57=2;
                        int LA57_0 = input.LA(1);

                        if ( (LA57_0==30) ) {
                            alt57=1;
                        }


                        switch (alt57) {
                    	case 1 :
                    	    // PsiInternalXtext.g:2495:5: otherlv_2= '|' ( (lv_elements_3_0= ruleAssignableTerminal ) )
                    	    {

                    	    					markLeaf(elementTypeProvider.getAssignableAlternatives_VerticalLineKeyword_1_1_0ElementType());
                    	    				
                    	    otherlv_2=(Token)match(input,30,FollowSets000.FOLLOW_40); 

                    	    					doneLeaf(otherlv_2);
                    	    				
                    	    // PsiInternalXtext.g:2502:5: ( (lv_elements_3_0= ruleAssignableTerminal ) )
                    	    // PsiInternalXtext.g:2503:6: (lv_elements_3_0= ruleAssignableTerminal )
                    	    {
                    	    // PsiInternalXtext.g:2503:6: (lv_elements_3_0= ruleAssignableTerminal )
                    	    // PsiInternalXtext.g:2504:7: lv_elements_3_0= ruleAssignableTerminal
                    	    {

                    	    							markComposite(elementTypeProvider.getAssignableAlternatives_ElementsAssignableTerminalParserRuleCall_1_1_1_0ElementType());
                    	    						
                    	    pushFollow(FollowSets000.FOLLOW_25);
                    	    lv_elements_3_0=ruleAssignableTerminal();

                    	    state._fsp--;


                    	    							doneComposite();
                    	    							if(!current) {
                    	    								associateWithSemanticElement();
                    	    								current = true;
                    	    							}
                    	    						

                    	    }


                    	    }


                    	    }
                    	    break;

                    	default :
                    	    if ( cnt57 >= 1 ) break loop57;
                                EarlyExitException eee =
                                    new EarlyExitException(57, input);
                                throw eee;
                        }
                        cnt57++;
                    } while (true);


                    }
                    break;

            }


            }


            }

        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
        }
        finally {
        }
        return current;
    }
    // $ANTLR end "ruleAssignableAlternatives"


    // $ANTLR start "entryRuleCrossReference"
    // PsiInternalXtext.g:2523:1: entryRuleCrossReference returns [Boolean current=false] : iv_ruleCrossReference= ruleCrossReference EOF ;
    public final Boolean entryRuleCrossReference() throws RecognitionException {
        Boolean current = false;

        Boolean iv_ruleCrossReference = null;


        try {
            // PsiInternalXtext.g:2523:56: (iv_ruleCrossReference= ruleCrossReference EOF )
            // PsiInternalXtext.g:2524:2: iv_ruleCrossReference= ruleCrossReference EOF
            {
             markComposite(elementTypeProvider.getCrossReferenceElementType()); 
            pushFollow(FollowSets000.FOLLOW_1);
            iv_ruleCrossReference=ruleCrossReference();

            state._fsp--;

             current =iv_ruleCrossReference; 
            match(input,EOF,FollowSets000.FOLLOW_2); 

            }

        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
        }
        finally {
        }
        return current;
    }
    // $ANTLR end "entryRuleCrossReference"


    // $ANTLR start "ruleCrossReference"
    // PsiInternalXtext.g:2530:1: ruleCrossReference returns [Boolean current=false] : (otherlv_0= '[' ( (lv_type_1_0= ruleTypeRef ) ) (otherlv_2= '|' ( (lv_terminal_3_0= ruleCrossReferenceableTerminal ) ) )? otherlv_4= ']' ) ;
    public final Boolean ruleCrossReference() throws RecognitionException {
        Boolean current = false;

        Token otherlv_0=null;
        Token otherlv_2=null;
        Token otherlv_4=null;
        Boolean lv_type_1_0 = null;

        Boolean lv_terminal_3_0 = null;


        try {
            // PsiInternalXtext.g:2531:1: ( (otherlv_0= '[' ( (lv_type_1_0= ruleTypeRef ) ) (otherlv_2= '|' ( (lv_terminal_3_0= ruleCrossReferenceableTerminal ) ) )? otherlv_4= ']' ) )
            // PsiInternalXtext.g:2532:2: (otherlv_0= '[' ( (lv_type_1_0= ruleTypeRef ) ) (otherlv_2= '|' ( (lv_terminal_3_0= ruleCrossReferenceableTerminal ) ) )? otherlv_4= ']' )
            {
            // PsiInternalXtext.g:2532:2: (otherlv_0= '[' ( (lv_type_1_0= ruleTypeRef ) ) (otherlv_2= '|' ( (lv_terminal_3_0= ruleCrossReferenceableTerminal ) ) )? otherlv_4= ']' )
            // PsiInternalXtext.g:2533:3: otherlv_0= '[' ( (lv_type_1_0= ruleTypeRef ) ) (otherlv_2= '|' ( (lv_terminal_3_0= ruleCrossReferenceableTerminal ) ) )? otherlv_4= ']'
            {

            			markLeaf(elementTypeProvider.getCrossReference_LeftSquareBracketKeyword_0ElementType());
            		
            otherlv_0=(Token)match(input,45,FollowSets000.FOLLOW_13); 

            			doneLeaf(otherlv_0);
            		
            // PsiInternalXtext.g:2540:3: ( (lv_type_1_0= ruleTypeRef ) )
            // PsiInternalXtext.g:2541:4: (lv_type_1_0= ruleTypeRef )
            {
            // PsiInternalXtext.g:2541:4: (lv_type_1_0= ruleTypeRef )
            // PsiInternalXtext.g:2542:5: lv_type_1_0= ruleTypeRef
            {

            					markComposite(elementTypeProvider.getCrossReference_TypeTypeRefParserRuleCall_1_0ElementType());
            				
            pushFollow(FollowSets000.FOLLOW_41);
            lv_type_1_0=ruleTypeRef();

            state._fsp--;


            					doneComposite();
            					if(!current) {
            						associateWithSemanticElement();
            						current = true;
            					}
            				

            }


            }

            // PsiInternalXtext.g:2555:3: (otherlv_2= '|' ( (lv_terminal_3_0= ruleCrossReferenceableTerminal ) ) )?
            int alt59=2;
            int LA59_0 = input.LA(1);

            if ( (LA59_0==30) ) {
                alt59=1;
            }
            switch (alt59) {
                case 1 :
                    // PsiInternalXtext.g:2556:4: otherlv_2= '|' ( (lv_terminal_3_0= ruleCrossReferenceableTerminal ) )
                    {

                    				markLeaf(elementTypeProvider.getCrossReference_VerticalLineKeyword_2_0ElementType());
                    			
                    otherlv_2=(Token)match(input,30,FollowSets000.FOLLOW_42); 

                    				doneLeaf(otherlv_2);
                    			
                    // PsiInternalXtext.g:2563:4: ( (lv_terminal_3_0= ruleCrossReferenceableTerminal ) )
                    // PsiInternalXtext.g:2564:5: (lv_terminal_3_0= ruleCrossReferenceableTerminal )
                    {
                    // PsiInternalXtext.g:2564:5: (lv_terminal_3_0= ruleCrossReferenceableTerminal )
                    // PsiInternalXtext.g:2565:6: lv_terminal_3_0= ruleCrossReferenceableTerminal
                    {

                    						markComposite(elementTypeProvider.getCrossReference_TerminalCrossReferenceableTerminalParserRuleCall_2_1_0ElementType());
                    					
                    pushFollow(FollowSets000.FOLLOW_43);
                    lv_terminal_3_0=ruleCrossReferenceableTerminal();

                    state._fsp--;


                    						doneComposite();
                    						if(!current) {
                    							associateWithSemanticElement();
                    							current = true;
                    						}
                    					

                    }


                    }


                    }
                    break;

            }


            			markLeaf(elementTypeProvider.getCrossReference_RightSquareBracketKeyword_3ElementType());
            		
            otherlv_4=(Token)match(input,46,FollowSets000.FOLLOW_2); 

            			doneLeaf(otherlv_4);
            		

            }


            }

        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
        }
        finally {
        }
        return current;
    }
    // $ANTLR end "ruleCrossReference"


    // $ANTLR start "entryRuleCrossReferenceableTerminal"
    // PsiInternalXtext.g:2590:1: entryRuleCrossReferenceableTerminal returns [Boolean current=false] : iv_ruleCrossReferenceableTerminal= ruleCrossReferenceableTerminal EOF ;
    public final Boolean entryRuleCrossReferenceableTerminal() throws RecognitionException {
        Boolean current = false;

        Boolean iv_ruleCrossReferenceableTerminal = null;


        try {
            // PsiInternalXtext.g:2590:68: (iv_ruleCrossReferenceableTerminal= ruleCrossReferenceableTerminal EOF )
            // PsiInternalXtext.g:2591:2: iv_ruleCrossReferenceableTerminal= ruleCrossReferenceableTerminal EOF
            {
             markComposite(elementTypeProvider.getCrossReferenceableTerminalElementType()); 
            pushFollow(FollowSets000.FOLLOW_1);
            iv_ruleCrossReferenceableTerminal=ruleCrossReferenceableTerminal();

            state._fsp--;

             current =iv_ruleCrossReferenceableTerminal; 
            match(input,EOF,FollowSets000.FOLLOW_2); 

            }

        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
        }
        finally {
        }
        return current;
    }
    // $ANTLR end "entryRuleCrossReferenceableTerminal"


    // $ANTLR start "ruleCrossReferenceableTerminal"
    // PsiInternalXtext.g:2597:1: ruleCrossReferenceableTerminal returns [Boolean current=false] : (this_Keyword_0= ruleKeyword | this_RuleCall_1= ruleRuleCall ) ;
    public final Boolean ruleCrossReferenceableTerminal() throws RecognitionException {
        Boolean current = false;

        Boolean this_Keyword_0 = null;

        Boolean this_RuleCall_1 = null;


        try {
            // PsiInternalXtext.g:2598:1: ( (this_Keyword_0= ruleKeyword | this_RuleCall_1= ruleRuleCall ) )
            // PsiInternalXtext.g:2599:2: (this_Keyword_0= ruleKeyword | this_RuleCall_1= ruleRuleCall )
            {
            // PsiInternalXtext.g:2599:2: (this_Keyword_0= ruleKeyword | this_RuleCall_1= ruleRuleCall )
            int alt60=2;
            int LA60_0 = input.LA(1);

            if ( (LA60_0==RULE_STRING) ) {
                alt60=1;
            }
            else if ( (LA60_0==RULE_ID||(LA60_0>=39 && LA60_0<=40)) ) {
                alt60=2;
            }
            else {
                NoViableAltException nvae =
                    new NoViableAltException("", 60, 0, input);

                throw nvae;
            }
            switch (alt60) {
                case 1 :
                    // PsiInternalXtext.g:2600:3: this_Keyword_0= ruleKeyword
                    {

                    			markComposite(elementTypeProvider.getCrossReferenceableTerminal_KeywordParserRuleCall_0ElementType());
                    		
                    pushFollow(FollowSets000.FOLLOW_2);
                    this_Keyword_0=ruleKeyword();

                    state._fsp--;


                    			current = this_Keyword_0;
                    			doneComposite();
                    		

                    }
                    break;
                case 2 :
                    // PsiInternalXtext.g:2609:3: this_RuleCall_1= ruleRuleCall
                    {

                    			markComposite(elementTypeProvider.getCrossReferenceableTerminal_RuleCallParserRuleCall_1ElementType());
                    		
                    pushFollow(FollowSets000.FOLLOW_2);
                    this_RuleCall_1=ruleRuleCall();

                    state._fsp--;


                    			current = this_RuleCall_1;
                    			doneComposite();
                    		

                    }
                    break;

            }


            }

        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
        }
        finally {
        }
        return current;
    }
    // $ANTLR end "ruleCrossReferenceableTerminal"


    // $ANTLR start "entryRuleParenthesizedElement"
    // PsiInternalXtext.g:2621:1: entryRuleParenthesizedElement returns [Boolean current=false] : iv_ruleParenthesizedElement= ruleParenthesizedElement EOF ;
    public final Boolean entryRuleParenthesizedElement() throws RecognitionException {
        Boolean current = false;

        Boolean iv_ruleParenthesizedElement = null;


        try {
            // PsiInternalXtext.g:2621:62: (iv_ruleParenthesizedElement= ruleParenthesizedElement EOF )
            // PsiInternalXtext.g:2622:2: iv_ruleParenthesizedElement= ruleParenthesizedElement EOF
            {
             markComposite(elementTypeProvider.getParenthesizedElementElementType()); 
            pushFollow(FollowSets000.FOLLOW_1);
            iv_ruleParenthesizedElement=ruleParenthesizedElement();

            state._fsp--;

             current =iv_ruleParenthesizedElement; 
            match(input,EOF,FollowSets000.FOLLOW_2); 

            }

        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
        }
        finally {
        }
        return current;
    }
    // $ANTLR end "entryRuleParenthesizedElement"


    // $ANTLR start "ruleParenthesizedElement"
    // PsiInternalXtext.g:2628:1: ruleParenthesizedElement returns [Boolean current=false] : (otherlv_0= '(' this_Alternatives_1= ruleAlternatives otherlv_2= ')' ) ;
    public final Boolean ruleParenthesizedElement() throws RecognitionException {
        Boolean current = false;

        Token otherlv_0=null;
        Token otherlv_2=null;
        Boolean this_Alternatives_1 = null;


        try {
            // PsiInternalXtext.g:2629:1: ( (otherlv_0= '(' this_Alternatives_1= ruleAlternatives otherlv_2= ')' ) )
            // PsiInternalXtext.g:2630:2: (otherlv_0= '(' this_Alternatives_1= ruleAlternatives otherlv_2= ')' )
            {
            // PsiInternalXtext.g:2630:2: (otherlv_0= '(' this_Alternatives_1= ruleAlternatives otherlv_2= ')' )
            // PsiInternalXtext.g:2631:3: otherlv_0= '(' this_Alternatives_1= ruleAlternatives otherlv_2= ')'
            {

            			markLeaf(elementTypeProvider.getParenthesizedElement_LeftParenthesisKeyword_0ElementType());
            		
            otherlv_0=(Token)match(input,15,FollowSets000.FOLLOW_19); 

            			doneLeaf(otherlv_0);
            		

            			markComposite(elementTypeProvider.getParenthesizedElement_AlternativesParserRuleCall_1ElementType());
            		
            pushFollow(FollowSets000.FOLLOW_37);
            this_Alternatives_1=ruleAlternatives();

            state._fsp--;


            			current = this_Alternatives_1;
            			doneComposite();
            		

            			markLeaf(elementTypeProvider.getParenthesizedElement_RightParenthesisKeyword_2ElementType());
            		
            otherlv_2=(Token)match(input,16,FollowSets000.FOLLOW_2); 

            			doneLeaf(otherlv_2);
            		

            }


            }

        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
        }
        finally {
        }
        return current;
    }
    // $ANTLR end "ruleParenthesizedElement"


    // $ANTLR start "entryRulePredicatedGroup"
    // PsiInternalXtext.g:2657:1: entryRulePredicatedGroup returns [Boolean current=false] : iv_rulePredicatedGroup= rulePredicatedGroup EOF ;
    public final Boolean entryRulePredicatedGroup() throws RecognitionException {
        Boolean current = false;

        Boolean iv_rulePredicatedGroup = null;


        try {
            // PsiInternalXtext.g:2657:57: (iv_rulePredicatedGroup= rulePredicatedGroup EOF )
            // PsiInternalXtext.g:2658:2: iv_rulePredicatedGroup= rulePredicatedGroup EOF
            {
             markComposite(elementTypeProvider.getPredicatedGroupElementType()); 
            pushFollow(FollowSets000.FOLLOW_1);
            iv_rulePredicatedGroup=rulePredicatedGroup();

            state._fsp--;

             current =iv_rulePredicatedGroup; 
            match(input,EOF,FollowSets000.FOLLOW_2); 

            }

        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
        }
        finally {
        }
        return current;
    }
    // $ANTLR end "entryRulePredicatedGroup"


    // $ANTLR start "rulePredicatedGroup"
    // PsiInternalXtext.g:2664:1: rulePredicatedGroup returns [Boolean current=false] : ( ( ( (lv_predicated_0_0= '=>' ) ) | ( (lv_firstSetPredicated_1_0= '->' ) ) ) otherlv_2= '(' ( (lv_elements_3_0= ruleAlternatives ) ) otherlv_4= ')' ) ;
    public final Boolean rulePredicatedGroup() throws RecognitionException {
        Boolean current = false;

        Token lv_predicated_0_0=null;
        Token lv_firstSetPredicated_1_0=null;
        Token otherlv_2=null;
        Token otherlv_4=null;
        Boolean lv_elements_3_0 = null;


        try {
            // PsiInternalXtext.g:2665:1: ( ( ( ( (lv_predicated_0_0= '=>' ) ) | ( (lv_firstSetPredicated_1_0= '->' ) ) ) otherlv_2= '(' ( (lv_elements_3_0= ruleAlternatives ) ) otherlv_4= ')' ) )
            // PsiInternalXtext.g:2666:2: ( ( ( (lv_predicated_0_0= '=>' ) ) | ( (lv_firstSetPredicated_1_0= '->' ) ) ) otherlv_2= '(' ( (lv_elements_3_0= ruleAlternatives ) ) otherlv_4= ')' )
            {
            // PsiInternalXtext.g:2666:2: ( ( ( (lv_predicated_0_0= '=>' ) ) | ( (lv_firstSetPredicated_1_0= '->' ) ) ) otherlv_2= '(' ( (lv_elements_3_0= ruleAlternatives ) ) otherlv_4= ')' )
            // PsiInternalXtext.g:2667:3: ( ( (lv_predicated_0_0= '=>' ) ) | ( (lv_firstSetPredicated_1_0= '->' ) ) ) otherlv_2= '(' ( (lv_elements_3_0= ruleAlternatives ) ) otherlv_4= ')'
            {
            // PsiInternalXtext.g:2667:3: ( ( (lv_predicated_0_0= '=>' ) ) | ( (lv_firstSetPredicated_1_0= '->' ) ) )
            int alt61=2;
            int LA61_0 = input.LA(1);

            if ( (LA61_0==42) ) {
                alt61=1;
            }
            else if ( (LA61_0==43) ) {
                alt61=2;
            }
            else {
                NoViableAltException nvae =
                    new NoViableAltException("", 61, 0, input);

                throw nvae;
            }
            switch (alt61) {
                case 1 :
                    // PsiInternalXtext.g:2668:4: ( (lv_predicated_0_0= '=>' ) )
                    {
                    // PsiInternalXtext.g:2668:4: ( (lv_predicated_0_0= '=>' ) )
                    // PsiInternalXtext.g:2669:5: (lv_predicated_0_0= '=>' )
                    {
                    // PsiInternalXtext.g:2669:5: (lv_predicated_0_0= '=>' )
                    // PsiInternalXtext.g:2670:6: lv_predicated_0_0= '=>'
                    {

                    						markLeaf(elementTypeProvider.getPredicatedGroup_PredicatedEqualsSignGreaterThanSignKeyword_0_0_0ElementType());
                    					
                    lv_predicated_0_0=(Token)match(input,42,FollowSets000.FOLLOW_6); 

                    						doneLeaf(lv_predicated_0_0);
                    					

                    						if (!current) {
                    							associateWithSemanticElement();
                    							current = true;
                    						}
                    					

                    }


                    }


                    }
                    break;
                case 2 :
                    // PsiInternalXtext.g:2686:4: ( (lv_firstSetPredicated_1_0= '->' ) )
                    {
                    // PsiInternalXtext.g:2686:4: ( (lv_firstSetPredicated_1_0= '->' ) )
                    // PsiInternalXtext.g:2687:5: (lv_firstSetPredicated_1_0= '->' )
                    {
                    // PsiInternalXtext.g:2687:5: (lv_firstSetPredicated_1_0= '->' )
                    // PsiInternalXtext.g:2688:6: lv_firstSetPredicated_1_0= '->'
                    {

                    						markLeaf(elementTypeProvider.getPredicatedGroup_FirstSetPredicatedHyphenMinusGreaterThanSignKeyword_0_1_0ElementType());
                    					
                    lv_firstSetPredicated_1_0=(Token)match(input,43,FollowSets000.FOLLOW_6); 

                    						doneLeaf(lv_firstSetPredicated_1_0);
                    					

                    						if (!current) {
                    							associateWithSemanticElement();
                    							current = true;
                    						}
                    					

                    }


                    }


                    }
                    break;

            }


            			markLeaf(elementTypeProvider.getPredicatedGroup_LeftParenthesisKeyword_1ElementType());
            		
            otherlv_2=(Token)match(input,15,FollowSets000.FOLLOW_19); 

            			doneLeaf(otherlv_2);
            		
            // PsiInternalXtext.g:2711:3: ( (lv_elements_3_0= ruleAlternatives ) )
            // PsiInternalXtext.g:2712:4: (lv_elements_3_0= ruleAlternatives )
            {
            // PsiInternalXtext.g:2712:4: (lv_elements_3_0= ruleAlternatives )
            // PsiInternalXtext.g:2713:5: lv_elements_3_0= ruleAlternatives
            {

            					markComposite(elementTypeProvider.getPredicatedGroup_ElementsAlternativesParserRuleCall_2_0ElementType());
            				
            pushFollow(FollowSets000.FOLLOW_37);
            lv_elements_3_0=ruleAlternatives();

            state._fsp--;


            					doneComposite();
            					if(!current) {
            						associateWithSemanticElement();
            						current = true;
            					}
            				

            }


            }


            			markLeaf(elementTypeProvider.getPredicatedGroup_RightParenthesisKeyword_3ElementType());
            		
            otherlv_4=(Token)match(input,16,FollowSets000.FOLLOW_2); 

            			doneLeaf(otherlv_4);
            		

            }


            }

        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
        }
        finally {
        }
        return current;
    }
    // $ANTLR end "rulePredicatedGroup"


    // $ANTLR start "entryRuleTerminalRule"
    // PsiInternalXtext.g:2737:1: entryRuleTerminalRule returns [Boolean current=false] : iv_ruleTerminalRule= ruleTerminalRule EOF ;
    public final Boolean entryRuleTerminalRule() throws RecognitionException {
        Boolean current = false;

        Boolean iv_ruleTerminalRule = null;


        try {
            // PsiInternalXtext.g:2737:54: (iv_ruleTerminalRule= ruleTerminalRule EOF )
            // PsiInternalXtext.g:2738:2: iv_ruleTerminalRule= ruleTerminalRule EOF
            {
             markComposite(elementTypeProvider.getTerminalRuleElementType()); 
            pushFollow(FollowSets000.FOLLOW_1);
            iv_ruleTerminalRule=ruleTerminalRule();

            state._fsp--;

             current =iv_ruleTerminalRule; 
            match(input,EOF,FollowSets000.FOLLOW_2); 

            }

        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
        }
        finally {
        }
        return current;
    }
    // $ANTLR end "entryRuleTerminalRule"


    // $ANTLR start "ruleTerminalRule"
    // PsiInternalXtext.g:2744:1: ruleTerminalRule returns [Boolean current=false] : ( ( (lv_annotations_0_0= ruleAnnotation ) )* otherlv_1= 'terminal' ( ( ( (lv_fragment_2_0= 'fragment' ) ) ( (lv_name_3_0= ruleValidID ) ) ) | ( ( (lv_name_4_0= ruleValidID ) ) (otherlv_5= 'returns' ( (lv_type_6_0= ruleTypeRef ) ) )? ) ) otherlv_7= ':' ( (lv_alternatives_8_0= ruleTerminalAlternatives ) ) otherlv_9= ';' ) ;
    public final Boolean ruleTerminalRule() throws RecognitionException {
        Boolean current = false;

        Token otherlv_1=null;
        Token lv_fragment_2_0=null;
        Token otherlv_5=null;
        Token otherlv_7=null;
        Token otherlv_9=null;
        Boolean lv_annotations_0_0 = null;

        Boolean lv_name_3_0 = null;

        Boolean lv_name_4_0 = null;

        Boolean lv_type_6_0 = null;

        Boolean lv_alternatives_8_0 = null;


        try {
            // PsiInternalXtext.g:2745:1: ( ( ( (lv_annotations_0_0= ruleAnnotation ) )* otherlv_1= 'terminal' ( ( ( (lv_fragment_2_0= 'fragment' ) ) ( (lv_name_3_0= ruleValidID ) ) ) | ( ( (lv_name_4_0= ruleValidID ) ) (otherlv_5= 'returns' ( (lv_type_6_0= ruleTypeRef ) ) )? ) ) otherlv_7= ':' ( (lv_alternatives_8_0= ruleTerminalAlternatives ) ) otherlv_9= ';' ) )
            // PsiInternalXtext.g:2746:2: ( ( (lv_annotations_0_0= ruleAnnotation ) )* otherlv_1= 'terminal' ( ( ( (lv_fragment_2_0= 'fragment' ) ) ( (lv_name_3_0= ruleValidID ) ) ) | ( ( (lv_name_4_0= ruleValidID ) ) (otherlv_5= 'returns' ( (lv_type_6_0= ruleTypeRef ) ) )? ) ) otherlv_7= ':' ( (lv_alternatives_8_0= ruleTerminalAlternatives ) ) otherlv_9= ';' )
            {
            // PsiInternalXtext.g:2746:2: ( ( (lv_annotations_0_0= ruleAnnotation ) )* otherlv_1= 'terminal' ( ( ( (lv_fragment_2_0= 'fragment' ) ) ( (lv_name_3_0= ruleValidID ) ) ) | ( ( (lv_name_4_0= ruleValidID ) ) (otherlv_5= 'returns' ( (lv_type_6_0= ruleTypeRef ) ) )? ) ) otherlv_7= ':' ( (lv_alternatives_8_0= ruleTerminalAlternatives ) ) otherlv_9= ';' )
            // PsiInternalXtext.g:2747:3: ( (lv_annotations_0_0= ruleAnnotation ) )* otherlv_1= 'terminal' ( ( ( (lv_fragment_2_0= 'fragment' ) ) ( (lv_name_3_0= ruleValidID ) ) ) | ( ( (lv_name_4_0= ruleValidID ) ) (otherlv_5= 'returns' ( (lv_type_6_0= ruleTypeRef ) ) )? ) ) otherlv_7= ':' ( (lv_alternatives_8_0= ruleTerminalAlternatives ) ) otherlv_9= ';'
            {
            // PsiInternalXtext.g:2747:3: ( (lv_annotations_0_0= ruleAnnotation ) )*
            loop62:
            do {
                int alt62=2;
                int LA62_0 = input.LA(1);

                if ( (LA62_0==21) ) {
                    alt62=1;
                }


                switch (alt62) {
            	case 1 :
            	    // PsiInternalXtext.g:2748:4: (lv_annotations_0_0= ruleAnnotation )
            	    {
            	    // PsiInternalXtext.g:2748:4: (lv_annotations_0_0= ruleAnnotation )
            	    // PsiInternalXtext.g:2749:5: lv_annotations_0_0= ruleAnnotation
            	    {

            	    					markComposite(elementTypeProvider.getTerminalRule_AnnotationsAnnotationParserRuleCall_0_0ElementType());
            	    				
            	    pushFollow(FollowSets000.FOLLOW_44);
            	    lv_annotations_0_0=ruleAnnotation();

            	    state._fsp--;


            	    					doneComposite();
            	    					if(!current) {
            	    						associateWithSemanticElement();
            	    						current = true;
            	    					}
            	    				

            	    }


            	    }
            	    break;

            	default :
            	    break loop62;
                }
            } while (true);


            			markLeaf(elementTypeProvider.getTerminalRule_TerminalKeyword_1ElementType());
            		
            otherlv_1=(Token)match(input,47,FollowSets000.FOLLOW_45); 

            			doneLeaf(otherlv_1);
            		
            // PsiInternalXtext.g:2769:3: ( ( ( (lv_fragment_2_0= 'fragment' ) ) ( (lv_name_3_0= ruleValidID ) ) ) | ( ( (lv_name_4_0= ruleValidID ) ) (otherlv_5= 'returns' ( (lv_type_6_0= ruleTypeRef ) ) )? ) )
            int alt64=2;
            int LA64_0 = input.LA(1);

            if ( (LA64_0==22) ) {
                alt64=1;
            }
            else if ( (LA64_0==RULE_ID||(LA64_0>=39 && LA64_0<=40)) ) {
                alt64=2;
            }
            else {
                NoViableAltException nvae =
                    new NoViableAltException("", 64, 0, input);

                throw nvae;
            }
            switch (alt64) {
                case 1 :
                    // PsiInternalXtext.g:2770:4: ( ( (lv_fragment_2_0= 'fragment' ) ) ( (lv_name_3_0= ruleValidID ) ) )
                    {
                    // PsiInternalXtext.g:2770:4: ( ( (lv_fragment_2_0= 'fragment' ) ) ( (lv_name_3_0= ruleValidID ) ) )
                    // PsiInternalXtext.g:2771:5: ( (lv_fragment_2_0= 'fragment' ) ) ( (lv_name_3_0= ruleValidID ) )
                    {
                    // PsiInternalXtext.g:2771:5: ( (lv_fragment_2_0= 'fragment' ) )
                    // PsiInternalXtext.g:2772:6: (lv_fragment_2_0= 'fragment' )
                    {
                    // PsiInternalXtext.g:2772:6: (lv_fragment_2_0= 'fragment' )
                    // PsiInternalXtext.g:2773:7: lv_fragment_2_0= 'fragment'
                    {

                    							markLeaf(elementTypeProvider.getTerminalRule_FragmentFragmentKeyword_2_0_0_0ElementType());
                    						
                    lv_fragment_2_0=(Token)match(input,22,FollowSets000.FOLLOW_3); 

                    							doneLeaf(lv_fragment_2_0);
                    						

                    							if (!current) {
                    								associateWithSemanticElement();
                    								current = true;
                    							}
                    						

                    }


                    }

                    // PsiInternalXtext.g:2788:5: ( (lv_name_3_0= ruleValidID ) )
                    // PsiInternalXtext.g:2789:6: (lv_name_3_0= ruleValidID )
                    {
                    // PsiInternalXtext.g:2789:6: (lv_name_3_0= ruleValidID )
                    // PsiInternalXtext.g:2790:7: lv_name_3_0= ruleValidID
                    {

                    							markComposite(elementTypeProvider.getTerminalRule_NameValidIDParserRuleCall_2_0_1_0ElementType());
                    						
                    pushFollow(FollowSets000.FOLLOW_18);
                    lv_name_3_0=ruleValidID();

                    state._fsp--;


                    							doneComposite();
                    							if(!current) {
                    								associateWithSemanticElement();
                    								current = true;
                    							}
                    						

                    }


                    }


                    }


                    }
                    break;
                case 2 :
                    // PsiInternalXtext.g:2805:4: ( ( (lv_name_4_0= ruleValidID ) ) (otherlv_5= 'returns' ( (lv_type_6_0= ruleTypeRef ) ) )? )
                    {
                    // PsiInternalXtext.g:2805:4: ( ( (lv_name_4_0= ruleValidID ) ) (otherlv_5= 'returns' ( (lv_type_6_0= ruleTypeRef ) ) )? )
                    // PsiInternalXtext.g:2806:5: ( (lv_name_4_0= ruleValidID ) ) (otherlv_5= 'returns' ( (lv_type_6_0= ruleTypeRef ) ) )?
                    {
                    // PsiInternalXtext.g:2806:5: ( (lv_name_4_0= ruleValidID ) )
                    // PsiInternalXtext.g:2807:6: (lv_name_4_0= ruleValidID )
                    {
                    // PsiInternalXtext.g:2807:6: (lv_name_4_0= ruleValidID )
                    // PsiInternalXtext.g:2808:7: lv_name_4_0= ruleValidID
                    {

                    							markComposite(elementTypeProvider.getTerminalRule_NameValidIDParserRuleCall_2_1_0_0ElementType());
                    						
                    pushFollow(FollowSets000.FOLLOW_46);
                    lv_name_4_0=ruleValidID();

                    state._fsp--;


                    							doneComposite();
                    							if(!current) {
                    								associateWithSemanticElement();
                    								current = true;
                    							}
                    						

                    }


                    }

                    // PsiInternalXtext.g:2821:5: (otherlv_5= 'returns' ( (lv_type_6_0= ruleTypeRef ) ) )?
                    int alt63=2;
                    int LA63_0 = input.LA(1);

                    if ( (LA63_0==24) ) {
                        alt63=1;
                    }
                    switch (alt63) {
                        case 1 :
                            // PsiInternalXtext.g:2822:6: otherlv_5= 'returns' ( (lv_type_6_0= ruleTypeRef ) )
                            {

                            						markLeaf(elementTypeProvider.getTerminalRule_ReturnsKeyword_2_1_1_0ElementType());
                            					
                            otherlv_5=(Token)match(input,24,FollowSets000.FOLLOW_13); 

                            						doneLeaf(otherlv_5);
                            					
                            // PsiInternalXtext.g:2829:6: ( (lv_type_6_0= ruleTypeRef ) )
                            // PsiInternalXtext.g:2830:7: (lv_type_6_0= ruleTypeRef )
                            {
                            // PsiInternalXtext.g:2830:7: (lv_type_6_0= ruleTypeRef )
                            // PsiInternalXtext.g:2831:8: lv_type_6_0= ruleTypeRef
                            {

                            								markComposite(elementTypeProvider.getTerminalRule_TypeTypeRefParserRuleCall_2_1_1_1_0ElementType());
                            							
                            pushFollow(FollowSets000.FOLLOW_18);
                            lv_type_6_0=ruleTypeRef();

                            state._fsp--;


                            								doneComposite();
                            								if(!current) {
                            									associateWithSemanticElement();
                            									current = true;
                            								}
                            							

                            }


                            }


                            }
                            break;

                    }


                    }


                    }
                    break;

            }


            			markLeaf(elementTypeProvider.getTerminalRule_ColonKeyword_3ElementType());
            		
            otherlv_7=(Token)match(input,25,FollowSets000.FOLLOW_47); 

            			doneLeaf(otherlv_7);
            		
            // PsiInternalXtext.g:2854:3: ( (lv_alternatives_8_0= ruleTerminalAlternatives ) )
            // PsiInternalXtext.g:2855:4: (lv_alternatives_8_0= ruleTerminalAlternatives )
            {
            // PsiInternalXtext.g:2855:4: (lv_alternatives_8_0= ruleTerminalAlternatives )
            // PsiInternalXtext.g:2856:5: lv_alternatives_8_0= ruleTerminalAlternatives
            {

            					markComposite(elementTypeProvider.getTerminalRule_AlternativesTerminalAlternativesParserRuleCall_4_0ElementType());
            				
            pushFollow(FollowSets000.FOLLOW_20);
            lv_alternatives_8_0=ruleTerminalAlternatives();

            state._fsp--;


            					doneComposite();
            					if(!current) {
            						associateWithSemanticElement();
            						current = true;
            					}
            				

            }


            }


            			markLeaf(elementTypeProvider.getTerminalRule_SemicolonKeyword_5ElementType());
            		
            otherlv_9=(Token)match(input,26,FollowSets000.FOLLOW_2); 

            			doneLeaf(otherlv_9);
            		

            }


            }

        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
        }
        finally {
        }
        return current;
    }
    // $ANTLR end "ruleTerminalRule"


    // $ANTLR start "entryRuleTerminalAlternatives"
    // PsiInternalXtext.g:2880:1: entryRuleTerminalAlternatives returns [Boolean current=false] : iv_ruleTerminalAlternatives= ruleTerminalAlternatives EOF ;
    public final Boolean entryRuleTerminalAlternatives() throws RecognitionException {
        Boolean current = false;

        Boolean iv_ruleTerminalAlternatives = null;


        try {
            // PsiInternalXtext.g:2880:62: (iv_ruleTerminalAlternatives= ruleTerminalAlternatives EOF )
            // PsiInternalXtext.g:2881:2: iv_ruleTerminalAlternatives= ruleTerminalAlternatives EOF
            {
             markComposite(elementTypeProvider.getTerminalAlternativesElementType()); 
            pushFollow(FollowSets000.FOLLOW_1);
            iv_ruleTerminalAlternatives=ruleTerminalAlternatives();

            state._fsp--;

             current =iv_ruleTerminalAlternatives; 
            match(input,EOF,FollowSets000.FOLLOW_2); 

            }

        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
        }
        finally {
        }
        return current;
    }
    // $ANTLR end "entryRuleTerminalAlternatives"


    // $ANTLR start "ruleTerminalAlternatives"
    // PsiInternalXtext.g:2887:1: ruleTerminalAlternatives returns [Boolean current=false] : (this_TerminalGroup_0= ruleTerminalGroup ( () (otherlv_2= '|' ( (lv_elements_3_0= ruleTerminalGroup ) ) )+ )? ) ;
    public final Boolean ruleTerminalAlternatives() throws RecognitionException {
        Boolean current = false;

        Token otherlv_2=null;
        Boolean this_TerminalGroup_0 = null;

        Boolean lv_elements_3_0 = null;


        try {
            // PsiInternalXtext.g:2888:1: ( (this_TerminalGroup_0= ruleTerminalGroup ( () (otherlv_2= '|' ( (lv_elements_3_0= ruleTerminalGroup ) ) )+ )? ) )
            // PsiInternalXtext.g:2889:2: (this_TerminalGroup_0= ruleTerminalGroup ( () (otherlv_2= '|' ( (lv_elements_3_0= ruleTerminalGroup ) ) )+ )? )
            {
            // PsiInternalXtext.g:2889:2: (this_TerminalGroup_0= ruleTerminalGroup ( () (otherlv_2= '|' ( (lv_elements_3_0= ruleTerminalGroup ) ) )+ )? )
            // PsiInternalXtext.g:2890:3: this_TerminalGroup_0= ruleTerminalGroup ( () (otherlv_2= '|' ( (lv_elements_3_0= ruleTerminalGroup ) ) )+ )?
            {

            			markComposite(elementTypeProvider.getTerminalAlternatives_TerminalGroupParserRuleCall_0ElementType());
            		
            pushFollow(FollowSets000.FOLLOW_25);
            this_TerminalGroup_0=ruleTerminalGroup();

            state._fsp--;


            			current = this_TerminalGroup_0;
            			doneComposite();
            		
            // PsiInternalXtext.g:2898:3: ( () (otherlv_2= '|' ( (lv_elements_3_0= ruleTerminalGroup ) ) )+ )?
            int alt66=2;
            int LA66_0 = input.LA(1);

            if ( (LA66_0==30) ) {
                alt66=1;
            }
            switch (alt66) {
                case 1 :
                    // PsiInternalXtext.g:2899:4: () (otherlv_2= '|' ( (lv_elements_3_0= ruleTerminalGroup ) ) )+
                    {
                    // PsiInternalXtext.g:2899:4: ()
                    // PsiInternalXtext.g:2900:5: 
                    {

                    					precedeComposite(elementTypeProvider.getTerminalAlternatives_AlternativesElementsAction_1_0ElementType());
                    					doneComposite();
                    					associateWithSemanticElement();
                    				

                    }

                    // PsiInternalXtext.g:2906:4: (otherlv_2= '|' ( (lv_elements_3_0= ruleTerminalGroup ) ) )+
                    int cnt65=0;
                    loop65:
                    do {
                        int alt65=2;
                        int LA65_0 = input.LA(1);

                        if ( (LA65_0==30) ) {
                            alt65=1;
                        }


                        switch (alt65) {
                    	case 1 :
                    	    // PsiInternalXtext.g:2907:5: otherlv_2= '|' ( (lv_elements_3_0= ruleTerminalGroup ) )
                    	    {

                    	    					markLeaf(elementTypeProvider.getTerminalAlternatives_VerticalLineKeyword_1_1_0ElementType());
                    	    				
                    	    otherlv_2=(Token)match(input,30,FollowSets000.FOLLOW_47); 

                    	    					doneLeaf(otherlv_2);
                    	    				
                    	    // PsiInternalXtext.g:2914:5: ( (lv_elements_3_0= ruleTerminalGroup ) )
                    	    // PsiInternalXtext.g:2915:6: (lv_elements_3_0= ruleTerminalGroup )
                    	    {
                    	    // PsiInternalXtext.g:2915:6: (lv_elements_3_0= ruleTerminalGroup )
                    	    // PsiInternalXtext.g:2916:7: lv_elements_3_0= ruleTerminalGroup
                    	    {

                    	    							markComposite(elementTypeProvider.getTerminalAlternatives_ElementsTerminalGroupParserRuleCall_1_1_1_0ElementType());
                    	    						
                    	    pushFollow(FollowSets000.FOLLOW_25);
                    	    lv_elements_3_0=ruleTerminalGroup();

                    	    state._fsp--;


                    	    							doneComposite();
                    	    							if(!current) {
                    	    								associateWithSemanticElement();
                    	    								current = true;
                    	    							}
                    	    						

                    	    }


                    	    }


                    	    }
                    	    break;

                    	default :
                    	    if ( cnt65 >= 1 ) break loop65;
                                EarlyExitException eee =
                                    new EarlyExitException(65, input);
                                throw eee;
                        }
                        cnt65++;
                    } while (true);


                    }
                    break;

            }


            }


            }

        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
        }
        finally {
        }
        return current;
    }
    // $ANTLR end "ruleTerminalAlternatives"


    // $ANTLR start "entryRuleTerminalGroup"
    // PsiInternalXtext.g:2935:1: entryRuleTerminalGroup returns [Boolean current=false] : iv_ruleTerminalGroup= ruleTerminalGroup EOF ;
    public final Boolean entryRuleTerminalGroup() throws RecognitionException {
        Boolean current = false;

        Boolean iv_ruleTerminalGroup = null;


        try {
            // PsiInternalXtext.g:2935:55: (iv_ruleTerminalGroup= ruleTerminalGroup EOF )
            // PsiInternalXtext.g:2936:2: iv_ruleTerminalGroup= ruleTerminalGroup EOF
            {
             markComposite(elementTypeProvider.getTerminalGroupElementType()); 
            pushFollow(FollowSets000.FOLLOW_1);
            iv_ruleTerminalGroup=ruleTerminalGroup();

            state._fsp--;

             current =iv_ruleTerminalGroup; 
            match(input,EOF,FollowSets000.FOLLOW_2); 

            }

        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
        }
        finally {
        }
        return current;
    }
    // $ANTLR end "entryRuleTerminalGroup"


    // $ANTLR start "ruleTerminalGroup"
    // PsiInternalXtext.g:2942:1: ruleTerminalGroup returns [Boolean current=false] : (this_TerminalToken_0= ruleTerminalToken ( () ( (lv_elements_2_0= ruleTerminalToken ) )+ )? ) ;
    public final Boolean ruleTerminalGroup() throws RecognitionException {
        Boolean current = false;

        Boolean this_TerminalToken_0 = null;

        Boolean lv_elements_2_0 = null;


        try {
            // PsiInternalXtext.g:2943:1: ( (this_TerminalToken_0= ruleTerminalToken ( () ( (lv_elements_2_0= ruleTerminalToken ) )+ )? ) )
            // PsiInternalXtext.g:2944:2: (this_TerminalToken_0= ruleTerminalToken ( () ( (lv_elements_2_0= ruleTerminalToken ) )+ )? )
            {
            // PsiInternalXtext.g:2944:2: (this_TerminalToken_0= ruleTerminalToken ( () ( (lv_elements_2_0= ruleTerminalToken ) )+ )? )
            // PsiInternalXtext.g:2945:3: this_TerminalToken_0= ruleTerminalToken ( () ( (lv_elements_2_0= ruleTerminalToken ) )+ )?
            {

            			markComposite(elementTypeProvider.getTerminalGroup_TerminalTokenParserRuleCall_0ElementType());
            		
            pushFollow(FollowSets000.FOLLOW_48);
            this_TerminalToken_0=ruleTerminalToken();

            state._fsp--;


            			current = this_TerminalToken_0;
            			doneComposite();
            		
            // PsiInternalXtext.g:2953:3: ( () ( (lv_elements_2_0= ruleTerminalToken ) )+ )?
            int alt68=2;
            int LA68_0 = input.LA(1);

            if ( ((LA68_0>=RULE_STRING && LA68_0<=RULE_ID)||LA68_0==15||LA68_0==17||(LA68_0>=39 && LA68_0<=41)||LA68_0==43||LA68_0==48) ) {
                alt68=1;
            }
            switch (alt68) {
                case 1 :
                    // PsiInternalXtext.g:2954:4: () ( (lv_elements_2_0= ruleTerminalToken ) )+
                    {
                    // PsiInternalXtext.g:2954:4: ()
                    // PsiInternalXtext.g:2955:5: 
                    {

                    					precedeComposite(elementTypeProvider.getTerminalGroup_GroupElementsAction_1_0ElementType());
                    					doneComposite();
                    					associateWithSemanticElement();
                    				

                    }

                    // PsiInternalXtext.g:2961:4: ( (lv_elements_2_0= ruleTerminalToken ) )+
                    int cnt67=0;
                    loop67:
                    do {
                        int alt67=2;
                        int LA67_0 = input.LA(1);

                        if ( ((LA67_0>=RULE_STRING && LA67_0<=RULE_ID)||LA67_0==15||LA67_0==17||(LA67_0>=39 && LA67_0<=41)||LA67_0==43||LA67_0==48) ) {
                            alt67=1;
                        }


                        switch (alt67) {
                    	case 1 :
                    	    // PsiInternalXtext.g:2962:5: (lv_elements_2_0= ruleTerminalToken )
                    	    {
                    	    // PsiInternalXtext.g:2962:5: (lv_elements_2_0= ruleTerminalToken )
                    	    // PsiInternalXtext.g:2963:6: lv_elements_2_0= ruleTerminalToken
                    	    {

                    	    						markComposite(elementTypeProvider.getTerminalGroup_ElementsTerminalTokenParserRuleCall_1_1_0ElementType());
                    	    					
                    	    pushFollow(FollowSets000.FOLLOW_48);
                    	    lv_elements_2_0=ruleTerminalToken();

                    	    state._fsp--;


                    	    						doneComposite();
                    	    						if(!current) {
                    	    							associateWithSemanticElement();
                    	    							current = true;
                    	    						}
                    	    					

                    	    }


                    	    }
                    	    break;

                    	default :
                    	    if ( cnt67 >= 1 ) break loop67;
                                EarlyExitException eee =
                                    new EarlyExitException(67, input);
                                throw eee;
                        }
                        cnt67++;
                    } while (true);


                    }
                    break;

            }


            }


            }

        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
        }
        finally {
        }
        return current;
    }
    // $ANTLR end "ruleTerminalGroup"


    // $ANTLR start "entryRuleTerminalToken"
    // PsiInternalXtext.g:2981:1: entryRuleTerminalToken returns [Boolean current=false] : iv_ruleTerminalToken= ruleTerminalToken EOF ;
    public final Boolean entryRuleTerminalToken() throws RecognitionException {
        Boolean current = false;

        Boolean iv_ruleTerminalToken = null;


        try {
            // PsiInternalXtext.g:2981:55: (iv_ruleTerminalToken= ruleTerminalToken EOF )
            // PsiInternalXtext.g:2982:2: iv_ruleTerminalToken= ruleTerminalToken EOF
            {
             markComposite(elementTypeProvider.getTerminalTokenElementType()); 
            pushFollow(FollowSets000.FOLLOW_1);
            iv_ruleTerminalToken=ruleTerminalToken();

            state._fsp--;

             current =iv_ruleTerminalToken; 
            match(input,EOF,FollowSets000.FOLLOW_2); 

            }

        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
        }
        finally {
        }
        return current;
    }
    // $ANTLR end "entryRuleTerminalToken"


    // $ANTLR start "ruleTerminalToken"
    // PsiInternalXtext.g:2988:1: ruleTerminalToken returns [Boolean current=false] : (this_TerminalTokenElement_0= ruleTerminalTokenElement ( ( (lv_cardinality_1_1= '?' | lv_cardinality_1_2= '*' | lv_cardinality_1_3= '+' ) ) )? ) ;
    public final Boolean ruleTerminalToken() throws RecognitionException {
        Boolean current = false;

        Token lv_cardinality_1_1=null;
        Token lv_cardinality_1_2=null;
        Token lv_cardinality_1_3=null;
        Boolean this_TerminalTokenElement_0 = null;


        try {
            // PsiInternalXtext.g:2989:1: ( (this_TerminalTokenElement_0= ruleTerminalTokenElement ( ( (lv_cardinality_1_1= '?' | lv_cardinality_1_2= '*' | lv_cardinality_1_3= '+' ) ) )? ) )
            // PsiInternalXtext.g:2990:2: (this_TerminalTokenElement_0= ruleTerminalTokenElement ( ( (lv_cardinality_1_1= '?' | lv_cardinality_1_2= '*' | lv_cardinality_1_3= '+' ) ) )? )
            {
            // PsiInternalXtext.g:2990:2: (this_TerminalTokenElement_0= ruleTerminalTokenElement ( ( (lv_cardinality_1_1= '?' | lv_cardinality_1_2= '*' | lv_cardinality_1_3= '+' ) ) )? )
            // PsiInternalXtext.g:2991:3: this_TerminalTokenElement_0= ruleTerminalTokenElement ( ( (lv_cardinality_1_1= '?' | lv_cardinality_1_2= '*' | lv_cardinality_1_3= '+' ) ) )?
            {

            			markComposite(elementTypeProvider.getTerminalToken_TerminalTokenElementParserRuleCall_0ElementType());
            		
            pushFollow(FollowSets000.FOLLOW_31);
            this_TerminalTokenElement_0=ruleTerminalTokenElement();

            state._fsp--;


            			current = this_TerminalTokenElement_0;
            			doneComposite();
            		
            // PsiInternalXtext.g:2999:3: ( ( (lv_cardinality_1_1= '?' | lv_cardinality_1_2= '*' | lv_cardinality_1_3= '+' ) ) )?
            int alt70=2;
            int LA70_0 = input.LA(1);

            if ( (LA70_0==23||(LA70_0>=32 && LA70_0<=33)) ) {
                alt70=1;
            }
            switch (alt70) {
                case 1 :
                    // PsiInternalXtext.g:3000:4: ( (lv_cardinality_1_1= '?' | lv_cardinality_1_2= '*' | lv_cardinality_1_3= '+' ) )
                    {
                    // PsiInternalXtext.g:3000:4: ( (lv_cardinality_1_1= '?' | lv_cardinality_1_2= '*' | lv_cardinality_1_3= '+' ) )
                    // PsiInternalXtext.g:3001:5: (lv_cardinality_1_1= '?' | lv_cardinality_1_2= '*' | lv_cardinality_1_3= '+' )
                    {
                    // PsiInternalXtext.g:3001:5: (lv_cardinality_1_1= '?' | lv_cardinality_1_2= '*' | lv_cardinality_1_3= '+' )
                    int alt69=3;
                    switch ( input.LA(1) ) {
                    case 32:
                        {
                        alt69=1;
                        }
                        break;
                    case 23:
                        {
                        alt69=2;
                        }
                        break;
                    case 33:
                        {
                        alt69=3;
                        }
                        break;
                    default:
                        NoViableAltException nvae =
                            new NoViableAltException("", 69, 0, input);

                        throw nvae;
                    }

                    switch (alt69) {
                        case 1 :
                            // PsiInternalXtext.g:3002:6: lv_cardinality_1_1= '?'
                            {

                            						markLeaf(elementTypeProvider.getTerminalToken_CardinalityQuestionMarkKeyword_1_0_0ElementType());
                            					
                            lv_cardinality_1_1=(Token)match(input,32,FollowSets000.FOLLOW_2); 

                            						doneLeaf(lv_cardinality_1_1);
                            					

                            						if (!current) {
                            							associateWithSemanticElement();
                            							current = true;
                            						}
                            					

                            }
                            break;
                        case 2 :
                            // PsiInternalXtext.g:3016:6: lv_cardinality_1_2= '*'
                            {

                            						markLeaf(elementTypeProvider.getTerminalToken_CardinalityAsteriskKeyword_1_0_1ElementType());
                            					
                            lv_cardinality_1_2=(Token)match(input,23,FollowSets000.FOLLOW_2); 

                            						doneLeaf(lv_cardinality_1_2);
                            					

                            						if (!current) {
                            							associateWithSemanticElement();
                            							current = true;
                            						}
                            					

                            }
                            break;
                        case 3 :
                            // PsiInternalXtext.g:3030:6: lv_cardinality_1_3= '+'
                            {

                            						markLeaf(elementTypeProvider.getTerminalToken_CardinalityPlusSignKeyword_1_0_2ElementType());
                            					
                            lv_cardinality_1_3=(Token)match(input,33,FollowSets000.FOLLOW_2); 

                            						doneLeaf(lv_cardinality_1_3);
                            					

                            						if (!current) {
                            							associateWithSemanticElement();
                            							current = true;
                            						}
                            					

                            }
                            break;

                    }


                    }


                    }
                    break;

            }


            }


            }

        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
        }
        finally {
        }
        return current;
    }
    // $ANTLR end "ruleTerminalToken"


    // $ANTLR start "entryRuleTerminalTokenElement"
    // PsiInternalXtext.g:3050:1: entryRuleTerminalTokenElement returns [Boolean current=false] : iv_ruleTerminalTokenElement= ruleTerminalTokenElement EOF ;
    public final Boolean entryRuleTerminalTokenElement() throws RecognitionException {
        Boolean current = false;

        Boolean iv_ruleTerminalTokenElement = null;


        try {
            // PsiInternalXtext.g:3050:62: (iv_ruleTerminalTokenElement= ruleTerminalTokenElement EOF )
            // PsiInternalXtext.g:3051:2: iv_ruleTerminalTokenElement= ruleTerminalTokenElement EOF
            {
             markComposite(elementTypeProvider.getTerminalTokenElementElementType()); 
            pushFollow(FollowSets000.FOLLOW_1);
            iv_ruleTerminalTokenElement=ruleTerminalTokenElement();

            state._fsp--;

             current =iv_ruleTerminalTokenElement; 
            match(input,EOF,FollowSets000.FOLLOW_2); 

            }

        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
        }
        finally {
        }
        return current;
    }
    // $ANTLR end "entryRuleTerminalTokenElement"


    // $ANTLR start "ruleTerminalTokenElement"
    // PsiInternalXtext.g:3057:1: ruleTerminalTokenElement returns [Boolean current=false] : (this_CharacterRange_0= ruleCharacterRange | this_TerminalRuleCall_1= ruleTerminalRuleCall | this_ParenthesizedTerminalElement_2= ruleParenthesizedTerminalElement | this_AbstractNegatedToken_3= ruleAbstractNegatedToken | this_Wildcard_4= ruleWildcard | this_EOF_5= ruleEOF ) ;
    public final Boolean ruleTerminalTokenElement() throws RecognitionException {
        Boolean current = false;

        Boolean this_CharacterRange_0 = null;

        Boolean this_TerminalRuleCall_1 = null;

        Boolean this_ParenthesizedTerminalElement_2 = null;

        Boolean this_AbstractNegatedToken_3 = null;

        Boolean this_Wildcard_4 = null;

        Boolean this_EOF_5 = null;


        try {
            // PsiInternalXtext.g:3058:1: ( (this_CharacterRange_0= ruleCharacterRange | this_TerminalRuleCall_1= ruleTerminalRuleCall | this_ParenthesizedTerminalElement_2= ruleParenthesizedTerminalElement | this_AbstractNegatedToken_3= ruleAbstractNegatedToken | this_Wildcard_4= ruleWildcard | this_EOF_5= ruleEOF ) )
            // PsiInternalXtext.g:3059:2: (this_CharacterRange_0= ruleCharacterRange | this_TerminalRuleCall_1= ruleTerminalRuleCall | this_ParenthesizedTerminalElement_2= ruleParenthesizedTerminalElement | this_AbstractNegatedToken_3= ruleAbstractNegatedToken | this_Wildcard_4= ruleWildcard | this_EOF_5= ruleEOF )
            {
            // PsiInternalXtext.g:3059:2: (this_CharacterRange_0= ruleCharacterRange | this_TerminalRuleCall_1= ruleTerminalRuleCall | this_ParenthesizedTerminalElement_2= ruleParenthesizedTerminalElement | this_AbstractNegatedToken_3= ruleAbstractNegatedToken | this_Wildcard_4= ruleWildcard | this_EOF_5= ruleEOF )
            int alt71=6;
            switch ( input.LA(1) ) {
            case RULE_STRING:
                {
                alt71=1;
                }
                break;
            case RULE_ID:
            case 39:
            case 40:
                {
                alt71=2;
                }
                break;
            case 15:
                {
                alt71=3;
                }
                break;
            case 41:
            case 43:
                {
                alt71=4;
                }
                break;
            case 17:
                {
                alt71=5;
                }
                break;
            case 48:
                {
                alt71=6;
                }
                break;
            default:
                NoViableAltException nvae =
                    new NoViableAltException("", 71, 0, input);

                throw nvae;
            }

            switch (alt71) {
                case 1 :
                    // PsiInternalXtext.g:3060:3: this_CharacterRange_0= ruleCharacterRange
                    {

                    			markComposite(elementTypeProvider.getTerminalTokenElement_CharacterRangeParserRuleCall_0ElementType());
                    		
                    pushFollow(FollowSets000.FOLLOW_2);
                    this_CharacterRange_0=ruleCharacterRange();

                    state._fsp--;


                    			current = this_CharacterRange_0;
                    			doneComposite();
                    		

                    }
                    break;
                case 2 :
                    // PsiInternalXtext.g:3069:3: this_TerminalRuleCall_1= ruleTerminalRuleCall
                    {

                    			markComposite(elementTypeProvider.getTerminalTokenElement_TerminalRuleCallParserRuleCall_1ElementType());
                    		
                    pushFollow(FollowSets000.FOLLOW_2);
                    this_TerminalRuleCall_1=ruleTerminalRuleCall();

                    state._fsp--;


                    			current = this_TerminalRuleCall_1;
                    			doneComposite();
                    		

                    }
                    break;
                case 3 :
                    // PsiInternalXtext.g:3078:3: this_ParenthesizedTerminalElement_2= ruleParenthesizedTerminalElement
                    {

                    			markComposite(elementTypeProvider.getTerminalTokenElement_ParenthesizedTerminalElementParserRuleCall_2ElementType());
                    		
                    pushFollow(FollowSets000.FOLLOW_2);
                    this_ParenthesizedTerminalElement_2=ruleParenthesizedTerminalElement();

                    state._fsp--;


                    			current = this_ParenthesizedTerminalElement_2;
                    			doneComposite();
                    		

                    }
                    break;
                case 4 :
                    // PsiInternalXtext.g:3087:3: this_AbstractNegatedToken_3= ruleAbstractNegatedToken
                    {

                    			markComposite(elementTypeProvider.getTerminalTokenElement_AbstractNegatedTokenParserRuleCall_3ElementType());
                    		
                    pushFollow(FollowSets000.FOLLOW_2);
                    this_AbstractNegatedToken_3=ruleAbstractNegatedToken();

                    state._fsp--;


                    			current = this_AbstractNegatedToken_3;
                    			doneComposite();
                    		

                    }
                    break;
                case 5 :
                    // PsiInternalXtext.g:3096:3: this_Wildcard_4= ruleWildcard
                    {

                    			markComposite(elementTypeProvider.getTerminalTokenElement_WildcardParserRuleCall_4ElementType());
                    		
                    pushFollow(FollowSets000.FOLLOW_2);
                    this_Wildcard_4=ruleWildcard();

                    state._fsp--;


                    			current = this_Wildcard_4;
                    			doneComposite();
                    		

                    }
                    break;
                case 6 :
                    // PsiInternalXtext.g:3105:3: this_EOF_5= ruleEOF
                    {

                    			markComposite(elementTypeProvider.getTerminalTokenElement_EOFParserRuleCall_5ElementType());
                    		
                    pushFollow(FollowSets000.FOLLOW_2);
                    this_EOF_5=ruleEOF();

                    state._fsp--;


                    			current = this_EOF_5;
                    			doneComposite();
                    		

                    }
                    break;

            }


            }

        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
        }
        finally {
        }
        return current;
    }
    // $ANTLR end "ruleTerminalTokenElement"


    // $ANTLR start "entryRuleParenthesizedTerminalElement"
    // PsiInternalXtext.g:3117:1: entryRuleParenthesizedTerminalElement returns [Boolean current=false] : iv_ruleParenthesizedTerminalElement= ruleParenthesizedTerminalElement EOF ;
    public final Boolean entryRuleParenthesizedTerminalElement() throws RecognitionException {
        Boolean current = false;

        Boolean iv_ruleParenthesizedTerminalElement = null;


        try {
            // PsiInternalXtext.g:3117:70: (iv_ruleParenthesizedTerminalElement= ruleParenthesizedTerminalElement EOF )
            // PsiInternalXtext.g:3118:2: iv_ruleParenthesizedTerminalElement= ruleParenthesizedTerminalElement EOF
            {
             markComposite(elementTypeProvider.getParenthesizedTerminalElementElementType()); 
            pushFollow(FollowSets000.FOLLOW_1);
            iv_ruleParenthesizedTerminalElement=ruleParenthesizedTerminalElement();

            state._fsp--;

             current =iv_ruleParenthesizedTerminalElement; 
            match(input,EOF,FollowSets000.FOLLOW_2); 

            }

        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
        }
        finally {
        }
        return current;
    }
    // $ANTLR end "entryRuleParenthesizedTerminalElement"


    // $ANTLR start "ruleParenthesizedTerminalElement"
    // PsiInternalXtext.g:3124:1: ruleParenthesizedTerminalElement returns [Boolean current=false] : (otherlv_0= '(' this_TerminalAlternatives_1= ruleTerminalAlternatives otherlv_2= ')' ) ;
    public final Boolean ruleParenthesizedTerminalElement() throws RecognitionException {
        Boolean current = false;

        Token otherlv_0=null;
        Token otherlv_2=null;
        Boolean this_TerminalAlternatives_1 = null;


        try {
            // PsiInternalXtext.g:3125:1: ( (otherlv_0= '(' this_TerminalAlternatives_1= ruleTerminalAlternatives otherlv_2= ')' ) )
            // PsiInternalXtext.g:3126:2: (otherlv_0= '(' this_TerminalAlternatives_1= ruleTerminalAlternatives otherlv_2= ')' )
            {
            // PsiInternalXtext.g:3126:2: (otherlv_0= '(' this_TerminalAlternatives_1= ruleTerminalAlternatives otherlv_2= ')' )
            // PsiInternalXtext.g:3127:3: otherlv_0= '(' this_TerminalAlternatives_1= ruleTerminalAlternatives otherlv_2= ')'
            {

            			markLeaf(elementTypeProvider.getParenthesizedTerminalElement_LeftParenthesisKeyword_0ElementType());
            		
            otherlv_0=(Token)match(input,15,FollowSets000.FOLLOW_47); 

            			doneLeaf(otherlv_0);
            		

            			markComposite(elementTypeProvider.getParenthesizedTerminalElement_TerminalAlternativesParserRuleCall_1ElementType());
            		
            pushFollow(FollowSets000.FOLLOW_37);
            this_TerminalAlternatives_1=ruleTerminalAlternatives();

            state._fsp--;


            			current = this_TerminalAlternatives_1;
            			doneComposite();
            		

            			markLeaf(elementTypeProvider.getParenthesizedTerminalElement_RightParenthesisKeyword_2ElementType());
            		
            otherlv_2=(Token)match(input,16,FollowSets000.FOLLOW_2); 

            			doneLeaf(otherlv_2);
            		

            }


            }

        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
        }
        finally {
        }
        return current;
    }
    // $ANTLR end "ruleParenthesizedTerminalElement"


    // $ANTLR start "entryRuleAbstractNegatedToken"
    // PsiInternalXtext.g:3153:1: entryRuleAbstractNegatedToken returns [Boolean current=false] : iv_ruleAbstractNegatedToken= ruleAbstractNegatedToken EOF ;
    public final Boolean entryRuleAbstractNegatedToken() throws RecognitionException {
        Boolean current = false;

        Boolean iv_ruleAbstractNegatedToken = null;


        try {
            // PsiInternalXtext.g:3153:62: (iv_ruleAbstractNegatedToken= ruleAbstractNegatedToken EOF )
            // PsiInternalXtext.g:3154:2: iv_ruleAbstractNegatedToken= ruleAbstractNegatedToken EOF
            {
             markComposite(elementTypeProvider.getAbstractNegatedTokenElementType()); 
            pushFollow(FollowSets000.FOLLOW_1);
            iv_ruleAbstractNegatedToken=ruleAbstractNegatedToken();

            state._fsp--;

             current =iv_ruleAbstractNegatedToken; 
            match(input,EOF,FollowSets000.FOLLOW_2); 

            }

        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
        }
        finally {
        }
        return current;
    }
    // $ANTLR end "entryRuleAbstractNegatedToken"


    // $ANTLR start "ruleAbstractNegatedToken"
    // PsiInternalXtext.g:3160:1: ruleAbstractNegatedToken returns [Boolean current=false] : (this_NegatedToken_0= ruleNegatedToken | this_UntilToken_1= ruleUntilToken ) ;
    public final Boolean ruleAbstractNegatedToken() throws RecognitionException {
        Boolean current = false;

        Boolean this_NegatedToken_0 = null;

        Boolean this_UntilToken_1 = null;


        try {
            // PsiInternalXtext.g:3161:1: ( (this_NegatedToken_0= ruleNegatedToken | this_UntilToken_1= ruleUntilToken ) )
            // PsiInternalXtext.g:3162:2: (this_NegatedToken_0= ruleNegatedToken | this_UntilToken_1= ruleUntilToken )
            {
            // PsiInternalXtext.g:3162:2: (this_NegatedToken_0= ruleNegatedToken | this_UntilToken_1= ruleUntilToken )
            int alt72=2;
            int LA72_0 = input.LA(1);

            if ( (LA72_0==41) ) {
                alt72=1;
            }
            else if ( (LA72_0==43) ) {
                alt72=2;
            }
            else {
                NoViableAltException nvae =
                    new NoViableAltException("", 72, 0, input);

                throw nvae;
            }
            switch (alt72) {
                case 1 :
                    // PsiInternalXtext.g:3163:3: this_NegatedToken_0= ruleNegatedToken
                    {

                    			markComposite(elementTypeProvider.getAbstractNegatedToken_NegatedTokenParserRuleCall_0ElementType());
                    		
                    pushFollow(FollowSets000.FOLLOW_2);
                    this_NegatedToken_0=ruleNegatedToken();

                    state._fsp--;


                    			current = this_NegatedToken_0;
                    			doneComposite();
                    		

                    }
                    break;
                case 2 :
                    // PsiInternalXtext.g:3172:3: this_UntilToken_1= ruleUntilToken
                    {

                    			markComposite(elementTypeProvider.getAbstractNegatedToken_UntilTokenParserRuleCall_1ElementType());
                    		
                    pushFollow(FollowSets000.FOLLOW_2);
                    this_UntilToken_1=ruleUntilToken();

                    state._fsp--;


                    			current = this_UntilToken_1;
                    			doneComposite();
                    		

                    }
                    break;

            }


            }

        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
        }
        finally {
        }
        return current;
    }
    // $ANTLR end "ruleAbstractNegatedToken"


    // $ANTLR start "entryRuleNegatedToken"
    // PsiInternalXtext.g:3184:1: entryRuleNegatedToken returns [Boolean current=false] : iv_ruleNegatedToken= ruleNegatedToken EOF ;
    public final Boolean entryRuleNegatedToken() throws RecognitionException {
        Boolean current = false;

        Boolean iv_ruleNegatedToken = null;


        try {
            // PsiInternalXtext.g:3184:54: (iv_ruleNegatedToken= ruleNegatedToken EOF )
            // PsiInternalXtext.g:3185:2: iv_ruleNegatedToken= ruleNegatedToken EOF
            {
             markComposite(elementTypeProvider.getNegatedTokenElementType()); 
            pushFollow(FollowSets000.FOLLOW_1);
            iv_ruleNegatedToken=ruleNegatedToken();

            state._fsp--;

             current =iv_ruleNegatedToken; 
            match(input,EOF,FollowSets000.FOLLOW_2); 

            }

        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
        }
        finally {
        }
        return current;
    }
    // $ANTLR end "entryRuleNegatedToken"


    // $ANTLR start "ruleNegatedToken"
    // PsiInternalXtext.g:3191:1: ruleNegatedToken returns [Boolean current=false] : (otherlv_0= '!' ( (lv_terminal_1_0= ruleTerminalTokenElement ) ) ) ;
    public final Boolean ruleNegatedToken() throws RecognitionException {
        Boolean current = false;

        Token otherlv_0=null;
        Boolean lv_terminal_1_0 = null;


        try {
            // PsiInternalXtext.g:3192:1: ( (otherlv_0= '!' ( (lv_terminal_1_0= ruleTerminalTokenElement ) ) ) )
            // PsiInternalXtext.g:3193:2: (otherlv_0= '!' ( (lv_terminal_1_0= ruleTerminalTokenElement ) ) )
            {
            // PsiInternalXtext.g:3193:2: (otherlv_0= '!' ( (lv_terminal_1_0= ruleTerminalTokenElement ) ) )
            // PsiInternalXtext.g:3194:3: otherlv_0= '!' ( (lv_terminal_1_0= ruleTerminalTokenElement ) )
            {

            			markLeaf(elementTypeProvider.getNegatedToken_ExclamationMarkKeyword_0ElementType());
            		
            otherlv_0=(Token)match(input,41,FollowSets000.FOLLOW_47); 

            			doneLeaf(otherlv_0);
            		
            // PsiInternalXtext.g:3201:3: ( (lv_terminal_1_0= ruleTerminalTokenElement ) )
            // PsiInternalXtext.g:3202:4: (lv_terminal_1_0= ruleTerminalTokenElement )
            {
            // PsiInternalXtext.g:3202:4: (lv_terminal_1_0= ruleTerminalTokenElement )
            // PsiInternalXtext.g:3203:5: lv_terminal_1_0= ruleTerminalTokenElement
            {

            					markComposite(elementTypeProvider.getNegatedToken_TerminalTerminalTokenElementParserRuleCall_1_0ElementType());
            				
            pushFollow(FollowSets000.FOLLOW_2);
            lv_terminal_1_0=ruleTerminalTokenElement();

            state._fsp--;


            					doneComposite();
            					if(!current) {
            						associateWithSemanticElement();
            						current = true;
            					}
            				

            }


            }


            }


            }

        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
        }
        finally {
        }
        return current;
    }
    // $ANTLR end "ruleNegatedToken"


    // $ANTLR start "entryRuleUntilToken"
    // PsiInternalXtext.g:3220:1: entryRuleUntilToken returns [Boolean current=false] : iv_ruleUntilToken= ruleUntilToken EOF ;
    public final Boolean entryRuleUntilToken() throws RecognitionException {
        Boolean current = false;

        Boolean iv_ruleUntilToken = null;


        try {
            // PsiInternalXtext.g:3220:52: (iv_ruleUntilToken= ruleUntilToken EOF )
            // PsiInternalXtext.g:3221:2: iv_ruleUntilToken= ruleUntilToken EOF
            {
             markComposite(elementTypeProvider.getUntilTokenElementType()); 
            pushFollow(FollowSets000.FOLLOW_1);
            iv_ruleUntilToken=ruleUntilToken();

            state._fsp--;

             current =iv_ruleUntilToken; 
            match(input,EOF,FollowSets000.FOLLOW_2); 

            }

        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
        }
        finally {
        }
        return current;
    }
    // $ANTLR end "entryRuleUntilToken"


    // $ANTLR start "ruleUntilToken"
    // PsiInternalXtext.g:3227:1: ruleUntilToken returns [Boolean current=false] : (otherlv_0= '->' ( (lv_terminal_1_0= ruleTerminalTokenElement ) ) ) ;
    public final Boolean ruleUntilToken() throws RecognitionException {
        Boolean current = false;

        Token otherlv_0=null;
        Boolean lv_terminal_1_0 = null;


        try {
            // PsiInternalXtext.g:3228:1: ( (otherlv_0= '->' ( (lv_terminal_1_0= ruleTerminalTokenElement ) ) ) )
            // PsiInternalXtext.g:3229:2: (otherlv_0= '->' ( (lv_terminal_1_0= ruleTerminalTokenElement ) ) )
            {
            // PsiInternalXtext.g:3229:2: (otherlv_0= '->' ( (lv_terminal_1_0= ruleTerminalTokenElement ) ) )
            // PsiInternalXtext.g:3230:3: otherlv_0= '->' ( (lv_terminal_1_0= ruleTerminalTokenElement ) )
            {

            			markLeaf(elementTypeProvider.getUntilToken_HyphenMinusGreaterThanSignKeyword_0ElementType());
            		
            otherlv_0=(Token)match(input,43,FollowSets000.FOLLOW_47); 

            			doneLeaf(otherlv_0);
            		
            // PsiInternalXtext.g:3237:3: ( (lv_terminal_1_0= ruleTerminalTokenElement ) )
            // PsiInternalXtext.g:3238:4: (lv_terminal_1_0= ruleTerminalTokenElement )
            {
            // PsiInternalXtext.g:3238:4: (lv_terminal_1_0= ruleTerminalTokenElement )
            // PsiInternalXtext.g:3239:5: lv_terminal_1_0= ruleTerminalTokenElement
            {

            					markComposite(elementTypeProvider.getUntilToken_TerminalTerminalTokenElementParserRuleCall_1_0ElementType());
            				
            pushFollow(FollowSets000.FOLLOW_2);
            lv_terminal_1_0=ruleTerminalTokenElement();

            state._fsp--;


            					doneComposite();
            					if(!current) {
            						associateWithSemanticElement();
            						current = true;
            					}
            				

            }


            }


            }


            }

        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
        }
        finally {
        }
        return current;
    }
    // $ANTLR end "ruleUntilToken"


    // $ANTLR start "entryRuleWildcard"
    // PsiInternalXtext.g:3256:1: entryRuleWildcard returns [Boolean current=false] : iv_ruleWildcard= ruleWildcard EOF ;
    public final Boolean entryRuleWildcard() throws RecognitionException {
        Boolean current = false;

        Boolean iv_ruleWildcard = null;


        try {
            // PsiInternalXtext.g:3256:50: (iv_ruleWildcard= ruleWildcard EOF )
            // PsiInternalXtext.g:3257:2: iv_ruleWildcard= ruleWildcard EOF
            {
             markComposite(elementTypeProvider.getWildcardElementType()); 
            pushFollow(FollowSets000.FOLLOW_1);
            iv_ruleWildcard=ruleWildcard();

            state._fsp--;

             current =iv_ruleWildcard; 
            match(input,EOF,FollowSets000.FOLLOW_2); 

            }

        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
        }
        finally {
        }
        return current;
    }
    // $ANTLR end "entryRuleWildcard"


    // $ANTLR start "ruleWildcard"
    // PsiInternalXtext.g:3263:1: ruleWildcard returns [Boolean current=false] : ( () otherlv_1= '.' ) ;
    public final Boolean ruleWildcard() throws RecognitionException {
        Boolean current = false;

        Token otherlv_1=null;

        try {
            // PsiInternalXtext.g:3264:1: ( ( () otherlv_1= '.' ) )
            // PsiInternalXtext.g:3265:2: ( () otherlv_1= '.' )
            {
            // PsiInternalXtext.g:3265:2: ( () otherlv_1= '.' )
            // PsiInternalXtext.g:3266:3: () otherlv_1= '.'
            {
            // PsiInternalXtext.g:3266:3: ()
            // PsiInternalXtext.g:3267:4: 
            {

            				precedeComposite(elementTypeProvider.getWildcard_WildcardAction_0ElementType());
            				doneComposite();
            				associateWithSemanticElement();
            			

            }


            			markLeaf(elementTypeProvider.getWildcard_FullStopKeyword_1ElementType());
            		
            otherlv_1=(Token)match(input,17,FollowSets000.FOLLOW_2); 

            			doneLeaf(otherlv_1);
            		

            }


            }

        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
        }
        finally {
        }
        return current;
    }
    // $ANTLR end "ruleWildcard"


    // $ANTLR start "entryRuleEOF"
    // PsiInternalXtext.g:3284:1: entryRuleEOF returns [Boolean current=false] : iv_ruleEOF= ruleEOF EOF ;
    public final Boolean entryRuleEOF() throws RecognitionException {
        Boolean current = false;

        Boolean iv_ruleEOF = null;


        try {
            // PsiInternalXtext.g:3284:45: (iv_ruleEOF= ruleEOF EOF )
            // PsiInternalXtext.g:3285:2: iv_ruleEOF= ruleEOF EOF
            {
             markComposite(elementTypeProvider.getEOFElementType()); 
            pushFollow(FollowSets000.FOLLOW_1);
            iv_ruleEOF=ruleEOF();

            state._fsp--;

             current =iv_ruleEOF; 
            match(input,EOF,FollowSets000.FOLLOW_2); 

            }

        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
        }
        finally {
        }
        return current;
    }
    // $ANTLR end "entryRuleEOF"


    // $ANTLR start "ruleEOF"
    // PsiInternalXtext.g:3291:1: ruleEOF returns [Boolean current=false] : ( () otherlv_1= 'EOF' ) ;
    public final Boolean ruleEOF() throws RecognitionException {
        Boolean current = false;

        Token otherlv_1=null;

        try {
            // PsiInternalXtext.g:3292:1: ( ( () otherlv_1= 'EOF' ) )
            // PsiInternalXtext.g:3293:2: ( () otherlv_1= 'EOF' )
            {
            // PsiInternalXtext.g:3293:2: ( () otherlv_1= 'EOF' )
            // PsiInternalXtext.g:3294:3: () otherlv_1= 'EOF'
            {
            // PsiInternalXtext.g:3294:3: ()
            // PsiInternalXtext.g:3295:4: 
            {

            				precedeComposite(elementTypeProvider.getEOF_EOFAction_0ElementType());
            				doneComposite();
            				associateWithSemanticElement();
            			

            }


            			markLeaf(elementTypeProvider.getEOF_EOFKeyword_1ElementType());
            		
            otherlv_1=(Token)match(input,48,FollowSets000.FOLLOW_2); 

            			doneLeaf(otherlv_1);
            		

            }


            }

        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
        }
        finally {
        }
        return current;
    }
    // $ANTLR end "ruleEOF"


    // $ANTLR start "entryRuleCharacterRange"
    // PsiInternalXtext.g:3312:1: entryRuleCharacterRange returns [Boolean current=false] : iv_ruleCharacterRange= ruleCharacterRange EOF ;
    public final Boolean entryRuleCharacterRange() throws RecognitionException {
        Boolean current = false;

        Boolean iv_ruleCharacterRange = null;


        try {
            // PsiInternalXtext.g:3312:56: (iv_ruleCharacterRange= ruleCharacterRange EOF )
            // PsiInternalXtext.g:3313:2: iv_ruleCharacterRange= ruleCharacterRange EOF
            {
             markComposite(elementTypeProvider.getCharacterRangeElementType()); 
            pushFollow(FollowSets000.FOLLOW_1);
            iv_ruleCharacterRange=ruleCharacterRange();

            state._fsp--;

             current =iv_ruleCharacterRange; 
            match(input,EOF,FollowSets000.FOLLOW_2); 

            }

        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
        }
        finally {
        }
        return current;
    }
    // $ANTLR end "entryRuleCharacterRange"


    // $ANTLR start "ruleCharacterRange"
    // PsiInternalXtext.g:3319:1: ruleCharacterRange returns [Boolean current=false] : (this_Keyword_0= ruleKeyword ( () otherlv_2= '..' ( (lv_right_3_0= ruleKeyword ) ) )? ) ;
    public final Boolean ruleCharacterRange() throws RecognitionException {
        Boolean current = false;

        Token otherlv_2=null;
        Boolean this_Keyword_0 = null;

        Boolean lv_right_3_0 = null;


        try {
            // PsiInternalXtext.g:3320:1: ( (this_Keyword_0= ruleKeyword ( () otherlv_2= '..' ( (lv_right_3_0= ruleKeyword ) ) )? ) )
            // PsiInternalXtext.g:3321:2: (this_Keyword_0= ruleKeyword ( () otherlv_2= '..' ( (lv_right_3_0= ruleKeyword ) ) )? )
            {
            // PsiInternalXtext.g:3321:2: (this_Keyword_0= ruleKeyword ( () otherlv_2= '..' ( (lv_right_3_0= ruleKeyword ) ) )? )
            // PsiInternalXtext.g:3322:3: this_Keyword_0= ruleKeyword ( () otherlv_2= '..' ( (lv_right_3_0= ruleKeyword ) ) )?
            {

            			markComposite(elementTypeProvider.getCharacterRange_KeywordParserRuleCall_0ElementType());
            		
            pushFollow(FollowSets000.FOLLOW_49);
            this_Keyword_0=ruleKeyword();

            state._fsp--;


            			current = this_Keyword_0;
            			doneComposite();
            		
            // PsiInternalXtext.g:3330:3: ( () otherlv_2= '..' ( (lv_right_3_0= ruleKeyword ) ) )?
            int alt73=2;
            int LA73_0 = input.LA(1);

            if ( (LA73_0==49) ) {
                alt73=1;
            }
            switch (alt73) {
                case 1 :
                    // PsiInternalXtext.g:3331:4: () otherlv_2= '..' ( (lv_right_3_0= ruleKeyword ) )
                    {
                    // PsiInternalXtext.g:3331:4: ()
                    // PsiInternalXtext.g:3332:5: 
                    {

                    					precedeComposite(elementTypeProvider.getCharacterRange_CharacterRangeLeftAction_1_0ElementType());
                    					doneComposite();
                    					associateWithSemanticElement();
                    				

                    }


                    				markLeaf(elementTypeProvider.getCharacterRange_FullStopFullStopKeyword_1_1ElementType());
                    			
                    otherlv_2=(Token)match(input,49,FollowSets000.FOLLOW_11); 

                    				doneLeaf(otherlv_2);
                    			
                    // PsiInternalXtext.g:3345:4: ( (lv_right_3_0= ruleKeyword ) )
                    // PsiInternalXtext.g:3346:5: (lv_right_3_0= ruleKeyword )
                    {
                    // PsiInternalXtext.g:3346:5: (lv_right_3_0= ruleKeyword )
                    // PsiInternalXtext.g:3347:6: lv_right_3_0= ruleKeyword
                    {

                    						markComposite(elementTypeProvider.getCharacterRange_RightKeywordParserRuleCall_1_2_0ElementType());
                    					
                    pushFollow(FollowSets000.FOLLOW_2);
                    lv_right_3_0=ruleKeyword();

                    state._fsp--;


                    						doneComposite();
                    						if(!current) {
                    							associateWithSemanticElement();
                    							current = true;
                    						}
                    					

                    }


                    }


                    }
                    break;

            }


            }


            }

        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
        }
        finally {
        }
        return current;
    }
    // $ANTLR end "ruleCharacterRange"


    // $ANTLR start "entryRuleEnumRule"
    // PsiInternalXtext.g:3365:1: entryRuleEnumRule returns [Boolean current=false] : iv_ruleEnumRule= ruleEnumRule EOF ;
    public final Boolean entryRuleEnumRule() throws RecognitionException {
        Boolean current = false;

        Boolean iv_ruleEnumRule = null;


        try {
            // PsiInternalXtext.g:3365:50: (iv_ruleEnumRule= ruleEnumRule EOF )
            // PsiInternalXtext.g:3366:2: iv_ruleEnumRule= ruleEnumRule EOF
            {
             markComposite(elementTypeProvider.getEnumRuleElementType()); 
            pushFollow(FollowSets000.FOLLOW_1);
            iv_ruleEnumRule=ruleEnumRule();

            state._fsp--;

             current =iv_ruleEnumRule; 
            match(input,EOF,FollowSets000.FOLLOW_2); 

            }

        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
        }
        finally {
        }
        return current;
    }
    // $ANTLR end "entryRuleEnumRule"


    // $ANTLR start "ruleEnumRule"
    // PsiInternalXtext.g:3372:1: ruleEnumRule returns [Boolean current=false] : ( ( (lv_annotations_0_0= ruleAnnotation ) )* otherlv_1= 'enum' ( (lv_name_2_0= ruleValidID ) ) (otherlv_3= 'returns' ( (lv_type_4_0= ruleTypeRef ) ) )? otherlv_5= ':' ( (lv_alternatives_6_0= ruleEnumLiterals ) ) otherlv_7= ';' ) ;
    public final Boolean ruleEnumRule() throws RecognitionException {
        Boolean current = false;

        Token otherlv_1=null;
        Token otherlv_3=null;
        Token otherlv_5=null;
        Token otherlv_7=null;
        Boolean lv_annotations_0_0 = null;

        Boolean lv_name_2_0 = null;

        Boolean lv_type_4_0 = null;

        Boolean lv_alternatives_6_0 = null;


        try {
            // PsiInternalXtext.g:3373:1: ( ( ( (lv_annotations_0_0= ruleAnnotation ) )* otherlv_1= 'enum' ( (lv_name_2_0= ruleValidID ) ) (otherlv_3= 'returns' ( (lv_type_4_0= ruleTypeRef ) ) )? otherlv_5= ':' ( (lv_alternatives_6_0= ruleEnumLiterals ) ) otherlv_7= ';' ) )
            // PsiInternalXtext.g:3374:2: ( ( (lv_annotations_0_0= ruleAnnotation ) )* otherlv_1= 'enum' ( (lv_name_2_0= ruleValidID ) ) (otherlv_3= 'returns' ( (lv_type_4_0= ruleTypeRef ) ) )? otherlv_5= ':' ( (lv_alternatives_6_0= ruleEnumLiterals ) ) otherlv_7= ';' )
            {
            // PsiInternalXtext.g:3374:2: ( ( (lv_annotations_0_0= ruleAnnotation ) )* otherlv_1= 'enum' ( (lv_name_2_0= ruleValidID ) ) (otherlv_3= 'returns' ( (lv_type_4_0= ruleTypeRef ) ) )? otherlv_5= ':' ( (lv_alternatives_6_0= ruleEnumLiterals ) ) otherlv_7= ';' )
            // PsiInternalXtext.g:3375:3: ( (lv_annotations_0_0= ruleAnnotation ) )* otherlv_1= 'enum' ( (lv_name_2_0= ruleValidID ) ) (otherlv_3= 'returns' ( (lv_type_4_0= ruleTypeRef ) ) )? otherlv_5= ':' ( (lv_alternatives_6_0= ruleEnumLiterals ) ) otherlv_7= ';'
            {
            // PsiInternalXtext.g:3375:3: ( (lv_annotations_0_0= ruleAnnotation ) )*
            loop74:
            do {
                int alt74=2;
                int LA74_0 = input.LA(1);

                if ( (LA74_0==21) ) {
                    alt74=1;
                }


                switch (alt74) {
            	case 1 :
            	    // PsiInternalXtext.g:3376:4: (lv_annotations_0_0= ruleAnnotation )
            	    {
            	    // PsiInternalXtext.g:3376:4: (lv_annotations_0_0= ruleAnnotation )
            	    // PsiInternalXtext.g:3377:5: lv_annotations_0_0= ruleAnnotation
            	    {

            	    					markComposite(elementTypeProvider.getEnumRule_AnnotationsAnnotationParserRuleCall_0_0ElementType());
            	    				
            	    pushFollow(FollowSets000.FOLLOW_50);
            	    lv_annotations_0_0=ruleAnnotation();

            	    state._fsp--;


            	    					doneComposite();
            	    					if(!current) {
            	    						associateWithSemanticElement();
            	    						current = true;
            	    					}
            	    				

            	    }


            	    }
            	    break;

            	default :
            	    break loop74;
                }
            } while (true);


            			markLeaf(elementTypeProvider.getEnumRule_EnumKeyword_1ElementType());
            		
            otherlv_1=(Token)match(input,50,FollowSets000.FOLLOW_3); 

            			doneLeaf(otherlv_1);
            		
            // PsiInternalXtext.g:3397:3: ( (lv_name_2_0= ruleValidID ) )
            // PsiInternalXtext.g:3398:4: (lv_name_2_0= ruleValidID )
            {
            // PsiInternalXtext.g:3398:4: (lv_name_2_0= ruleValidID )
            // PsiInternalXtext.g:3399:5: lv_name_2_0= ruleValidID
            {

            					markComposite(elementTypeProvider.getEnumRule_NameValidIDParserRuleCall_2_0ElementType());
            				
            pushFollow(FollowSets000.FOLLOW_46);
            lv_name_2_0=ruleValidID();

            state._fsp--;


            					doneComposite();
            					if(!current) {
            						associateWithSemanticElement();
            						current = true;
            					}
            				

            }


            }

            // PsiInternalXtext.g:3412:3: (otherlv_3= 'returns' ( (lv_type_4_0= ruleTypeRef ) ) )?
            int alt75=2;
            int LA75_0 = input.LA(1);

            if ( (LA75_0==24) ) {
                alt75=1;
            }
            switch (alt75) {
                case 1 :
                    // PsiInternalXtext.g:3413:4: otherlv_3= 'returns' ( (lv_type_4_0= ruleTypeRef ) )
                    {

                    				markLeaf(elementTypeProvider.getEnumRule_ReturnsKeyword_3_0ElementType());
                    			
                    otherlv_3=(Token)match(input,24,FollowSets000.FOLLOW_13); 

                    				doneLeaf(otherlv_3);
                    			
                    // PsiInternalXtext.g:3420:4: ( (lv_type_4_0= ruleTypeRef ) )
                    // PsiInternalXtext.g:3421:5: (lv_type_4_0= ruleTypeRef )
                    {
                    // PsiInternalXtext.g:3421:5: (lv_type_4_0= ruleTypeRef )
                    // PsiInternalXtext.g:3422:6: lv_type_4_0= ruleTypeRef
                    {

                    						markComposite(elementTypeProvider.getEnumRule_TypeTypeRefParserRuleCall_3_1_0ElementType());
                    					
                    pushFollow(FollowSets000.FOLLOW_18);
                    lv_type_4_0=ruleTypeRef();

                    state._fsp--;


                    						doneComposite();
                    						if(!current) {
                    							associateWithSemanticElement();
                    							current = true;
                    						}
                    					

                    }


                    }


                    }
                    break;

            }


            			markLeaf(elementTypeProvider.getEnumRule_ColonKeyword_4ElementType());
            		
            otherlv_5=(Token)match(input,25,FollowSets000.FOLLOW_13); 

            			doneLeaf(otherlv_5);
            		
            // PsiInternalXtext.g:3443:3: ( (lv_alternatives_6_0= ruleEnumLiterals ) )
            // PsiInternalXtext.g:3444:4: (lv_alternatives_6_0= ruleEnumLiterals )
            {
            // PsiInternalXtext.g:3444:4: (lv_alternatives_6_0= ruleEnumLiterals )
            // PsiInternalXtext.g:3445:5: lv_alternatives_6_0= ruleEnumLiterals
            {

            					markComposite(elementTypeProvider.getEnumRule_AlternativesEnumLiteralsParserRuleCall_5_0ElementType());
            				
            pushFollow(FollowSets000.FOLLOW_20);
            lv_alternatives_6_0=ruleEnumLiterals();

            state._fsp--;


            					doneComposite();
            					if(!current) {
            						associateWithSemanticElement();
            						current = true;
            					}
            				

            }


            }


            			markLeaf(elementTypeProvider.getEnumRule_SemicolonKeyword_6ElementType());
            		
            otherlv_7=(Token)match(input,26,FollowSets000.FOLLOW_2); 

            			doneLeaf(otherlv_7);
            		

            }


            }

        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
        }
        finally {
        }
        return current;
    }
    // $ANTLR end "ruleEnumRule"


    // $ANTLR start "entryRuleEnumLiterals"
    // PsiInternalXtext.g:3469:1: entryRuleEnumLiterals returns [Boolean current=false] : iv_ruleEnumLiterals= ruleEnumLiterals EOF ;
    public final Boolean entryRuleEnumLiterals() throws RecognitionException {
        Boolean current = false;

        Boolean iv_ruleEnumLiterals = null;


        try {
            // PsiInternalXtext.g:3469:54: (iv_ruleEnumLiterals= ruleEnumLiterals EOF )
            // PsiInternalXtext.g:3470:2: iv_ruleEnumLiterals= ruleEnumLiterals EOF
            {
             markComposite(elementTypeProvider.getEnumLiteralsElementType()); 
            pushFollow(FollowSets000.FOLLOW_1);
            iv_ruleEnumLiterals=ruleEnumLiterals();

            state._fsp--;

             current =iv_ruleEnumLiterals; 
            match(input,EOF,FollowSets000.FOLLOW_2); 

            }

        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
        }
        finally {
        }
        return current;
    }
    // $ANTLR end "entryRuleEnumLiterals"


    // $ANTLR start "ruleEnumLiterals"
    // PsiInternalXtext.g:3476:1: ruleEnumLiterals returns [Boolean current=false] : (this_EnumLiteralDeclaration_0= ruleEnumLiteralDeclaration ( () (otherlv_2= '|' ( (lv_elements_3_0= ruleEnumLiteralDeclaration ) ) )+ )? ) ;
    public final Boolean ruleEnumLiterals() throws RecognitionException {
        Boolean current = false;

        Token otherlv_2=null;
        Boolean this_EnumLiteralDeclaration_0 = null;

        Boolean lv_elements_3_0 = null;


        try {
            // PsiInternalXtext.g:3477:1: ( (this_EnumLiteralDeclaration_0= ruleEnumLiteralDeclaration ( () (otherlv_2= '|' ( (lv_elements_3_0= ruleEnumLiteralDeclaration ) ) )+ )? ) )
            // PsiInternalXtext.g:3478:2: (this_EnumLiteralDeclaration_0= ruleEnumLiteralDeclaration ( () (otherlv_2= '|' ( (lv_elements_3_0= ruleEnumLiteralDeclaration ) ) )+ )? )
            {
            // PsiInternalXtext.g:3478:2: (this_EnumLiteralDeclaration_0= ruleEnumLiteralDeclaration ( () (otherlv_2= '|' ( (lv_elements_3_0= ruleEnumLiteralDeclaration ) ) )+ )? )
            // PsiInternalXtext.g:3479:3: this_EnumLiteralDeclaration_0= ruleEnumLiteralDeclaration ( () (otherlv_2= '|' ( (lv_elements_3_0= ruleEnumLiteralDeclaration ) ) )+ )?
            {

            			markComposite(elementTypeProvider.getEnumLiterals_EnumLiteralDeclarationParserRuleCall_0ElementType());
            		
            pushFollow(FollowSets000.FOLLOW_25);
            this_EnumLiteralDeclaration_0=ruleEnumLiteralDeclaration();

            state._fsp--;


            			current = this_EnumLiteralDeclaration_0;
            			doneComposite();
            		
            // PsiInternalXtext.g:3487:3: ( () (otherlv_2= '|' ( (lv_elements_3_0= ruleEnumLiteralDeclaration ) ) )+ )?
            int alt77=2;
            int LA77_0 = input.LA(1);

            if ( (LA77_0==30) ) {
                alt77=1;
            }
            switch (alt77) {
                case 1 :
                    // PsiInternalXtext.g:3488:4: () (otherlv_2= '|' ( (lv_elements_3_0= ruleEnumLiteralDeclaration ) ) )+
                    {
                    // PsiInternalXtext.g:3488:4: ()
                    // PsiInternalXtext.g:3489:5: 
                    {

                    					precedeComposite(elementTypeProvider.getEnumLiterals_AlternativesElementsAction_1_0ElementType());
                    					doneComposite();
                    					associateWithSemanticElement();
                    				

                    }

                    // PsiInternalXtext.g:3495:4: (otherlv_2= '|' ( (lv_elements_3_0= ruleEnumLiteralDeclaration ) ) )+
                    int cnt76=0;
                    loop76:
                    do {
                        int alt76=2;
                        int LA76_0 = input.LA(1);

                        if ( (LA76_0==30) ) {
                            alt76=1;
                        }


                        switch (alt76) {
                    	case 1 :
                    	    // PsiInternalXtext.g:3496:5: otherlv_2= '|' ( (lv_elements_3_0= ruleEnumLiteralDeclaration ) )
                    	    {

                    	    					markLeaf(elementTypeProvider.getEnumLiterals_VerticalLineKeyword_1_1_0ElementType());
                    	    				
                    	    otherlv_2=(Token)match(input,30,FollowSets000.FOLLOW_13); 

                    	    					doneLeaf(otherlv_2);
                    	    				
                    	    // PsiInternalXtext.g:3503:5: ( (lv_elements_3_0= ruleEnumLiteralDeclaration ) )
                    	    // PsiInternalXtext.g:3504:6: (lv_elements_3_0= ruleEnumLiteralDeclaration )
                    	    {
                    	    // PsiInternalXtext.g:3504:6: (lv_elements_3_0= ruleEnumLiteralDeclaration )
                    	    // PsiInternalXtext.g:3505:7: lv_elements_3_0= ruleEnumLiteralDeclaration
                    	    {

                    	    							markComposite(elementTypeProvider.getEnumLiterals_ElementsEnumLiteralDeclarationParserRuleCall_1_1_1_0ElementType());
                    	    						
                    	    pushFollow(FollowSets000.FOLLOW_25);
                    	    lv_elements_3_0=ruleEnumLiteralDeclaration();

                    	    state._fsp--;


                    	    							doneComposite();
                    	    							if(!current) {
                    	    								associateWithSemanticElement();
                    	    								current = true;
                    	    							}
                    	    						

                    	    }


                    	    }


                    	    }
                    	    break;

                    	default :
                    	    if ( cnt76 >= 1 ) break loop76;
                                EarlyExitException eee =
                                    new EarlyExitException(76, input);
                                throw eee;
                        }
                        cnt76++;
                    } while (true);


                    }
                    break;

            }


            }


            }

        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
        }
        finally {
        }
        return current;
    }
    // $ANTLR end "ruleEnumLiterals"


    // $ANTLR start "entryRuleEnumLiteralDeclaration"
    // PsiInternalXtext.g:3524:1: entryRuleEnumLiteralDeclaration returns [Boolean current=false] : iv_ruleEnumLiteralDeclaration= ruleEnumLiteralDeclaration EOF ;
    public final Boolean entryRuleEnumLiteralDeclaration() throws RecognitionException {
        Boolean current = false;

        Boolean iv_ruleEnumLiteralDeclaration = null;


        try {
            // PsiInternalXtext.g:3524:64: (iv_ruleEnumLiteralDeclaration= ruleEnumLiteralDeclaration EOF )
            // PsiInternalXtext.g:3525:2: iv_ruleEnumLiteralDeclaration= ruleEnumLiteralDeclaration EOF
            {
             markComposite(elementTypeProvider.getEnumLiteralDeclarationElementType()); 
            pushFollow(FollowSets000.FOLLOW_1);
            iv_ruleEnumLiteralDeclaration=ruleEnumLiteralDeclaration();

            state._fsp--;

             current =iv_ruleEnumLiteralDeclaration; 
            match(input,EOF,FollowSets000.FOLLOW_2); 

            }

        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
        }
        finally {
        }
        return current;
    }
    // $ANTLR end "entryRuleEnumLiteralDeclaration"


    // $ANTLR start "ruleEnumLiteralDeclaration"
    // PsiInternalXtext.g:3531:1: ruleEnumLiteralDeclaration returns [Boolean current=false] : ( ( (otherlv_0= RULE_ID ) ) (otherlv_1= '=' ( (lv_literal_2_0= ruleKeyword ) ) )? ) ;
    public final Boolean ruleEnumLiteralDeclaration() throws RecognitionException {
        Boolean current = false;

        Token otherlv_0=null;
        Token otherlv_1=null;
        Boolean lv_literal_2_0 = null;


        try {
            // PsiInternalXtext.g:3532:1: ( ( ( (otherlv_0= RULE_ID ) ) (otherlv_1= '=' ( (lv_literal_2_0= ruleKeyword ) ) )? ) )
            // PsiInternalXtext.g:3533:2: ( ( (otherlv_0= RULE_ID ) ) (otherlv_1= '=' ( (lv_literal_2_0= ruleKeyword ) ) )? )
            {
            // PsiInternalXtext.g:3533:2: ( ( (otherlv_0= RULE_ID ) ) (otherlv_1= '=' ( (lv_literal_2_0= ruleKeyword ) ) )? )
            // PsiInternalXtext.g:3534:3: ( (otherlv_0= RULE_ID ) ) (otherlv_1= '=' ( (lv_literal_2_0= ruleKeyword ) ) )?
            {
            // PsiInternalXtext.g:3534:3: ( (otherlv_0= RULE_ID ) )
            // PsiInternalXtext.g:3535:4: (otherlv_0= RULE_ID )
            {
            // PsiInternalXtext.g:3535:4: (otherlv_0= RULE_ID )
            // PsiInternalXtext.g:3536:5: otherlv_0= RULE_ID
            {

            					if (!current) {
            						associateWithSemanticElement();
            						current = true;
            					}
            				

            					markLeaf(elementTypeProvider.getEnumLiteralDeclaration_EnumLiteralEEnumLiteralCrossReference_0_0ElementType());
            				
            otherlv_0=(Token)match(input,RULE_ID,FollowSets000.FOLLOW_51); 

            					doneLeaf(otherlv_0);
            				

            }


            }

            // PsiInternalXtext.g:3551:3: (otherlv_1= '=' ( (lv_literal_2_0= ruleKeyword ) ) )?
            int alt78=2;
            int LA78_0 = input.LA(1);

            if ( (LA78_0==35) ) {
                alt78=1;
            }
            switch (alt78) {
                case 1 :
                    // PsiInternalXtext.g:3552:4: otherlv_1= '=' ( (lv_literal_2_0= ruleKeyword ) )
                    {

                    				markLeaf(elementTypeProvider.getEnumLiteralDeclaration_EqualsSignKeyword_1_0ElementType());
                    			
                    otherlv_1=(Token)match(input,35,FollowSets000.FOLLOW_11); 

                    				doneLeaf(otherlv_1);
                    			
                    // PsiInternalXtext.g:3559:4: ( (lv_literal_2_0= ruleKeyword ) )
                    // PsiInternalXtext.g:3560:5: (lv_literal_2_0= ruleKeyword )
                    {
                    // PsiInternalXtext.g:3560:5: (lv_literal_2_0= ruleKeyword )
                    // PsiInternalXtext.g:3561:6: lv_literal_2_0= ruleKeyword
                    {

                    						markComposite(elementTypeProvider.getEnumLiteralDeclaration_LiteralKeywordParserRuleCall_1_1_0ElementType());
                    					
                    pushFollow(FollowSets000.FOLLOW_2);
                    lv_literal_2_0=ruleKeyword();

                    state._fsp--;


                    						doneComposite();
                    						if(!current) {
                    							associateWithSemanticElement();
                    							current = true;
                    						}
                    					

                    }


                    }


                    }
                    break;

            }


            }


            }

        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
        }
        finally {
        }
        return current;
    }
    // $ANTLR end "ruleEnumLiteralDeclaration"

    // Delegated rules


    protected DFA9 dfa9 = new DFA9(this);
    protected DFA34 dfa34 = new DFA34(this);
    static final String dfa_1s = "\6\uffff";
    static final String dfa_2s = "\2\5\3\uffff\1\5";
    static final String dfa_3s = "\1\62\1\5\3\uffff\1\62";
    static final String dfa_4s = "\2\uffff\1\1\1\2\1\3\1\uffff";
    static final String dfa_5s = "\6\uffff}>";
    static final String[] dfa_6s = {
            "\1\2\17\uffff\1\1\1\2\20\uffff\2\2\6\uffff\1\3\2\uffff\1\4",
            "\1\5",
            "",
            "",
            "",
            "\1\2\17\uffff\1\1\1\2\20\uffff\2\2\6\uffff\1\3\2\uffff\1\4"
    };

    static final short[] dfa_1 = DFA.unpackEncodedString(dfa_1s);
    static final char[] dfa_2 = DFA.unpackEncodedStringToUnsignedChars(dfa_2s);
    static final char[] dfa_3 = DFA.unpackEncodedStringToUnsignedChars(dfa_3s);
    static final short[] dfa_4 = DFA.unpackEncodedString(dfa_4s);
    static final short[] dfa_5 = DFA.unpackEncodedString(dfa_5s);
    static final short[][] dfa_6 = unpackEncodedStringArray(dfa_6s);

    class DFA9 extends DFA {

        public DFA9(BaseRecognizer recognizer) {
            this.recognizer = recognizer;
            this.decisionNumber = 9;
            this.eot = dfa_1;
            this.eof = dfa_1;
            this.min = dfa_2;
            this.max = dfa_3;
            this.accept = dfa_4;
            this.special = dfa_5;
            this.transition = dfa_6;
        }
        public String getDescription() {
            return "294:2: (this_ParserRule_0= ruleParserRule | this_TerminalRule_1= ruleTerminalRule | this_EnumRule_2= ruleEnumRule )";
        }
    }
    static final String dfa_7s = "\13\uffff";
    static final String dfa_8s = "\3\uffff\3\6\1\uffff\3\6\1\uffff";
    static final String dfa_9s = "\6\4\1\uffff\3\4\1\uffff";
    static final String dfa_10s = "\1\53\2\50\3\54\1\uffff\3\54\1\uffff";
    static final String dfa_11s = "\6\uffff\1\2\3\uffff\1\1";
    static final String dfa_12s = "\13\uffff}>";
    static final String[] dfa_13s = {
            "\1\6\1\3\11\uffff\1\6\27\uffff\1\4\1\5\1\uffff\1\1\1\2",
            "\1\6\1\7\11\uffff\1\6\27\uffff\1\10\1\11",
            "\1\6\1\7\11\uffff\1\6\27\uffff\1\10\1\11",
            "\2\6\11\uffff\2\6\6\uffff\1\6\2\uffff\2\6\1\uffff\6\6\2\12\2\uffff\2\6\1\uffff\2\6\1\12",
            "\2\6\11\uffff\2\6\6\uffff\1\6\2\uffff\2\6\1\uffff\6\6\2\12\2\uffff\2\6\1\uffff\2\6\1\12",
            "\2\6\11\uffff\2\6\6\uffff\1\6\2\uffff\2\6\1\uffff\6\6\2\12\2\uffff\2\6\1\uffff\2\6\1\12",
            "",
            "\2\6\11\uffff\2\6\6\uffff\1\6\2\uffff\2\6\1\uffff\6\6\2\12\2\uffff\2\6\1\uffff\2\6\1\12",
            "\2\6\11\uffff\2\6\6\uffff\1\6\2\uffff\2\6\1\uffff\6\6\2\12\2\uffff\2\6\1\uffff\2\6\1\12",
            "\2\6\11\uffff\2\6\6\uffff\1\6\2\uffff\2\6\1\uffff\6\6\2\12\2\uffff\2\6\1\uffff\2\6\1\12",
            ""
    };

    static final short[] dfa_7 = DFA.unpackEncodedString(dfa_7s);
    static final short[] dfa_8 = DFA.unpackEncodedString(dfa_8s);
    static final char[] dfa_9 = DFA.unpackEncodedStringToUnsignedChars(dfa_9s);
    static final char[] dfa_10 = DFA.unpackEncodedStringToUnsignedChars(dfa_10s);
    static final short[] dfa_11 = DFA.unpackEncodedString(dfa_11s);
    static final short[] dfa_12 = DFA.unpackEncodedString(dfa_12s);
    static final short[][] dfa_13 = unpackEncodedStringArray(dfa_13s);

    class DFA34 extends DFA {

        public DFA34(BaseRecognizer recognizer) {
            this.recognizer = recognizer;
            this.decisionNumber = 34;
            this.eot = dfa_7;
            this.eof = dfa_8;
            this.min = dfa_9;
            this.max = dfa_10;
            this.accept = dfa_11;
            this.special = dfa_12;
            this.transition = dfa_13;
        }
        public String getDescription() {
            return "1224:3: (this_Assignment_0= ruleAssignment | this_AbstractTerminal_1= ruleAbstractTerminal )";
        }
    }
 

    
    private static class FollowSets000 {
        public static final BitSet FOLLOW_1 = new BitSet(new long[]{0x0000000000000000L});
        public static final BitSet FOLLOW_2 = new BitSet(new long[]{0x0000000000000002L});
        public static final BitSet FOLLOW_3 = new BitSet(new long[]{0x0000018000000020L});
        public static final BitSet FOLLOW_4 = new BitSet(new long[]{0x0004818000745020L});
        public static final BitSet FOLLOW_5 = new BitSet(new long[]{0x0004818000747020L});
        public static final BitSet FOLLOW_6 = new BitSet(new long[]{0x0000000000008000L});
        public static final BitSet FOLLOW_7 = new BitSet(new long[]{0x0000018000010020L});
        public static final BitSet FOLLOW_8 = new BitSet(new long[]{0x0000000000012000L});
        public static final BitSet FOLLOW_9 = new BitSet(new long[]{0x0004818000745022L});
        public static final BitSet FOLLOW_10 = new BitSet(new long[]{0x0000000000020002L});
        public static final BitSet FOLLOW_11 = new BitSet(new long[]{0x0000000000000010L});
        public static final BitSet FOLLOW_12 = new BitSet(new long[]{0x0000000000080002L});
        public static final BitSet FOLLOW_13 = new BitSet(new long[]{0x0000000000000020L});
        public static final BitSet FOLLOW_14 = new BitSet(new long[]{0x0000018000600020L});
        public static final BitSet FOLLOW_15 = new BitSet(new long[]{0x0000000003804000L});
        public static final BitSet FOLLOW_16 = new BitSet(new long[]{0x0000000002004000L});
        public static final BitSet FOLLOW_17 = new BitSet(new long[]{0x0000000003004000L});
        public static final BitSet FOLLOW_18 = new BitSet(new long[]{0x0000000002000000L});
        public static final BitSet FOLLOW_19 = new BitSet(new long[]{0x00000D8408008030L});
        public static final BitSet FOLLOW_20 = new BitSet(new long[]{0x0000000004000000L});
        public static final BitSet FOLLOW_21 = new BitSet(new long[]{0x0000000008000002L});
        public static final BitSet FOLLOW_22 = new BitSet(new long[]{0x0000000010000020L});
        public static final BitSet FOLLOW_23 = new BitSet(new long[]{0x0000000010002000L});
        public static final BitSet FOLLOW_24 = new BitSet(new long[]{0x0000000020000000L});
        public static final BitSet FOLLOW_25 = new BitSet(new long[]{0x0000000040000002L});
        public static final BitSet FOLLOW_26 = new BitSet(new long[]{0x0000038000008020L});
        public static final BitSet FOLLOW_27 = new BitSet(new long[]{0x0000000010000000L});
        public static final BitSet FOLLOW_28 = new BitSet(new long[]{0x00000D8400008030L});
        public static final BitSet FOLLOW_29 = new BitSet(new long[]{0x00000D8400008032L});
        public static final BitSet FOLLOW_30 = new BitSet(new long[]{0x0000000080000002L});
        public static final BitSet FOLLOW_31 = new BitSet(new long[]{0x0000000300800002L});
        public static final BitSet FOLLOW_32 = new BitSet(new long[]{0x0000004000020000L});
        public static final BitSet FOLLOW_33 = new BitSet(new long[]{0x0000001800000000L});
        public static final BitSet FOLLOW_34 = new BitSet(new long[]{0x0000002000000000L});
        public static final BitSet FOLLOW_35 = new BitSet(new long[]{0x0000004000000000L});
        public static final BitSet FOLLOW_36 = new BitSet(new long[]{0x0000000800000000L});
        public static final BitSet FOLLOW_37 = new BitSet(new long[]{0x0000000000010000L});
        public static final BitSet FOLLOW_38 = new BitSet(new long[]{0x0000000020000002L});
        public static final BitSet FOLLOW_39 = new BitSet(new long[]{0x0000101800000000L});
        public static final BitSet FOLLOW_40 = new BitSet(new long[]{0x0000218000008030L});
        public static final BitSet FOLLOW_41 = new BitSet(new long[]{0x0000400040000000L});
        public static final BitSet FOLLOW_42 = new BitSet(new long[]{0x0000018000000030L});
        public static final BitSet FOLLOW_43 = new BitSet(new long[]{0x0000400000000000L});
        public static final BitSet FOLLOW_44 = new BitSet(new long[]{0x0000800000200000L});
        public static final BitSet FOLLOW_45 = new BitSet(new long[]{0x0000018000400020L});
        public static final BitSet FOLLOW_46 = new BitSet(new long[]{0x0000000003000000L});
        public static final BitSet FOLLOW_47 = new BitSet(new long[]{0x00010B8000028030L});
        public static final BitSet FOLLOW_48 = new BitSet(new long[]{0x00010B8000028032L});
        public static final BitSet FOLLOW_49 = new BitSet(new long[]{0x0002000000000002L});
        public static final BitSet FOLLOW_50 = new BitSet(new long[]{0x0004000000200000L});
        public static final BitSet FOLLOW_51 = new BitSet(new long[]{0x0000000800000002L});
    }


}