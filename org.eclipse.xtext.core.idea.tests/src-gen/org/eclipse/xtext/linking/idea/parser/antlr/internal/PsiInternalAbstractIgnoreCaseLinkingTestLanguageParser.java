package org.eclipse.xtext.linking.idea.parser.antlr.internal;

import com.intellij.lang.PsiBuilder;
import org.antlr.runtime.*;
import org.eclipse.xtext.idea.parser.AbstractPsiAntlrParser;
import org.eclipse.xtext.linking.idea.lang.AbstractIgnoreCaseLinkingTestLanguageElementTypeProvider;
import org.eclipse.xtext.linking.services.AbstractIgnoreCaseLinkingTestLanguageGrammarAccess;

@SuppressWarnings("all")
public class PsiInternalAbstractIgnoreCaseLinkingTestLanguageParser extends AbstractPsiAntlrParser {
    public static final String[] tokenNames = new String[] {
        "<invalid>", "<EOR>", "<DOWN>", "<UP>", "RULE_ID", "RULE_INT", "RULE_STRING", "RULE_ML_COMMENT", "RULE_SL_COMMENT", "RULE_WS", "RULE_ANY_OTHER", "'{'", "'}'"
    };
    public static final int RULE_ID=4;
    public static final int RULE_STRING=6;
    public static final int T__12=12;
    public static final int T__11=11;
    public static final int RULE_ANY_OTHER=10;
    public static final int RULE_INT=5;
    public static final int RULE_WS=9;
    public static final int RULE_SL_COMMENT=8;
    public static final int EOF=-1;
    public static final int RULE_ML_COMMENT=7;

    // delegates
    // delegators


        public PsiInternalAbstractIgnoreCaseLinkingTestLanguageParser(TokenStream input) {
            this(input, new RecognizerSharedState());
        }
        public PsiInternalAbstractIgnoreCaseLinkingTestLanguageParser(TokenStream input, RecognizerSharedState state) {
            super(input, state);
             
        }
        

    public String[] getTokenNames() { return PsiInternalAbstractIgnoreCaseLinkingTestLanguageParser.tokenNames; }
    public String getGrammarFileName() { return "PsiInternalAbstractIgnoreCaseLinkingTestLanguage.g"; }



    	protected AbstractIgnoreCaseLinkingTestLanguageGrammarAccess grammarAccess;

    	protected AbstractIgnoreCaseLinkingTestLanguageElementTypeProvider elementTypeProvider;

    	public PsiInternalAbstractIgnoreCaseLinkingTestLanguageParser(PsiBuilder builder, TokenStream input, AbstractIgnoreCaseLinkingTestLanguageElementTypeProvider elementTypeProvider, AbstractIgnoreCaseLinkingTestLanguageGrammarAccess grammarAccess) {
    		this(input);
    		setPsiBuilder(builder);
        	this.grammarAccess = grammarAccess;
    		this.elementTypeProvider = elementTypeProvider;
    	}

    	@Override
    	protected String getFirstRuleName() {
    		return "Model";
    	}




    // $ANTLR start "entryRuleModel"
    // PsiInternalAbstractIgnoreCaseLinkingTestLanguage.g:52:1: entryRuleModel returns [Boolean current=false] : iv_ruleModel= ruleModel EOF ;
    public final Boolean entryRuleModel() throws RecognitionException {
        Boolean current = false;

        Boolean iv_ruleModel = null;


        try {
            // PsiInternalAbstractIgnoreCaseLinkingTestLanguage.g:52:47: (iv_ruleModel= ruleModel EOF )
            // PsiInternalAbstractIgnoreCaseLinkingTestLanguage.g:53:2: iv_ruleModel= ruleModel EOF
            {
             markComposite(elementTypeProvider.getModelElementType()); 
            pushFollow(FollowSets000.FOLLOW_1);
            iv_ruleModel=ruleModel();

            state._fsp--;

             current =iv_ruleModel; 
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
    // $ANTLR end "entryRuleModel"


    // $ANTLR start "ruleModel"
    // PsiInternalAbstractIgnoreCaseLinkingTestLanguage.g:59:1: ruleModel returns [Boolean current=false] : ( (lv_elements_0_0= ruleElement ) )+ ;
    public final Boolean ruleModel() throws RecognitionException {
        Boolean current = false;

        Boolean lv_elements_0_0 = null;


        try {
            // PsiInternalAbstractIgnoreCaseLinkingTestLanguage.g:60:1: ( ( (lv_elements_0_0= ruleElement ) )+ )
            // PsiInternalAbstractIgnoreCaseLinkingTestLanguage.g:61:2: ( (lv_elements_0_0= ruleElement ) )+
            {
            // PsiInternalAbstractIgnoreCaseLinkingTestLanguage.g:61:2: ( (lv_elements_0_0= ruleElement ) )+
            int cnt1=0;
            loop1:
            do {
                int alt1=2;
                int LA1_0 = input.LA(1);

                if ( (LA1_0==RULE_ID) ) {
                    alt1=1;
                }


                switch (alt1) {
            	case 1 :
            	    // PsiInternalAbstractIgnoreCaseLinkingTestLanguage.g:62:3: (lv_elements_0_0= ruleElement )
            	    {
            	    // PsiInternalAbstractIgnoreCaseLinkingTestLanguage.g:62:3: (lv_elements_0_0= ruleElement )
            	    // PsiInternalAbstractIgnoreCaseLinkingTestLanguage.g:63:4: lv_elements_0_0= ruleElement
            	    {

            	    				markComposite(elementTypeProvider.getModel_ElementsElementParserRuleCall_0ElementType());
            	    			
            	    pushFollow(FollowSets000.FOLLOW_3);
            	    lv_elements_0_0=ruleElement();

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
            	    if ( cnt1 >= 1 ) break loop1;
                        EarlyExitException eee =
                            new EarlyExitException(1, input);
                        throw eee;
                }
                cnt1++;
            } while (true);


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
    // $ANTLR end "ruleModel"


    // $ANTLR start "entryRuleElement"
    // PsiInternalAbstractIgnoreCaseLinkingTestLanguage.g:79:1: entryRuleElement returns [Boolean current=false] : iv_ruleElement= ruleElement EOF ;
    public final Boolean entryRuleElement() throws RecognitionException {
        Boolean current = false;

        Boolean iv_ruleElement = null;


        try {
            // PsiInternalAbstractIgnoreCaseLinkingTestLanguage.g:79:49: (iv_ruleElement= ruleElement EOF )
            // PsiInternalAbstractIgnoreCaseLinkingTestLanguage.g:80:2: iv_ruleElement= ruleElement EOF
            {
             markComposite(elementTypeProvider.getElementElementType()); 
            pushFollow(FollowSets000.FOLLOW_1);
            iv_ruleElement=ruleElement();

            state._fsp--;

             current =iv_ruleElement; 
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
    // $ANTLR end "entryRuleElement"


    // $ANTLR start "ruleElement"
    // PsiInternalAbstractIgnoreCaseLinkingTestLanguage.g:86:1: ruleElement returns [Boolean current=false] : ( ( (lv_name_0_0= RULE_ID ) ) ( (otherlv_1= RULE_ID ) )? otherlv_2= '{' ( (lv_elements_3_0= ruleElement ) )* otherlv_4= '}' ) ;
    public final Boolean ruleElement() throws RecognitionException {
        Boolean current = false;

        Token lv_name_0_0=null;
        Token otherlv_1=null;
        Token otherlv_2=null;
        Token otherlv_4=null;
        Boolean lv_elements_3_0 = null;


        try {
            // PsiInternalAbstractIgnoreCaseLinkingTestLanguage.g:87:1: ( ( ( (lv_name_0_0= RULE_ID ) ) ( (otherlv_1= RULE_ID ) )? otherlv_2= '{' ( (lv_elements_3_0= ruleElement ) )* otherlv_4= '}' ) )
            // PsiInternalAbstractIgnoreCaseLinkingTestLanguage.g:88:2: ( ( (lv_name_0_0= RULE_ID ) ) ( (otherlv_1= RULE_ID ) )? otherlv_2= '{' ( (lv_elements_3_0= ruleElement ) )* otherlv_4= '}' )
            {
            // PsiInternalAbstractIgnoreCaseLinkingTestLanguage.g:88:2: ( ( (lv_name_0_0= RULE_ID ) ) ( (otherlv_1= RULE_ID ) )? otherlv_2= '{' ( (lv_elements_3_0= ruleElement ) )* otherlv_4= '}' )
            // PsiInternalAbstractIgnoreCaseLinkingTestLanguage.g:89:3: ( (lv_name_0_0= RULE_ID ) ) ( (otherlv_1= RULE_ID ) )? otherlv_2= '{' ( (lv_elements_3_0= ruleElement ) )* otherlv_4= '}'
            {
            // PsiInternalAbstractIgnoreCaseLinkingTestLanguage.g:89:3: ( (lv_name_0_0= RULE_ID ) )
            // PsiInternalAbstractIgnoreCaseLinkingTestLanguage.g:90:4: (lv_name_0_0= RULE_ID )
            {
            // PsiInternalAbstractIgnoreCaseLinkingTestLanguage.g:90:4: (lv_name_0_0= RULE_ID )
            // PsiInternalAbstractIgnoreCaseLinkingTestLanguage.g:91:5: lv_name_0_0= RULE_ID
            {

            					markLeaf(elementTypeProvider.getElement_NameIDTerminalRuleCall_0_0ElementType());
            				
            lv_name_0_0=(Token)match(input,RULE_ID,FollowSets000.FOLLOW_4); 

            					if(!current) {
            						associateWithSemanticElement();
            						current = true;
            					}
            				

            					doneLeaf(lv_name_0_0);
            				

            }


            }

            // PsiInternalAbstractIgnoreCaseLinkingTestLanguage.g:106:3: ( (otherlv_1= RULE_ID ) )?
            int alt2=2;
            int LA2_0 = input.LA(1);

            if ( (LA2_0==RULE_ID) ) {
                alt2=1;
            }
            switch (alt2) {
                case 1 :
                    // PsiInternalAbstractIgnoreCaseLinkingTestLanguage.g:107:4: (otherlv_1= RULE_ID )
                    {
                    // PsiInternalAbstractIgnoreCaseLinkingTestLanguage.g:107:4: (otherlv_1= RULE_ID )
                    // PsiInternalAbstractIgnoreCaseLinkingTestLanguage.g:108:5: otherlv_1= RULE_ID
                    {

                    					if (!current) {
                    						associateWithSemanticElement();
                    						current = true;
                    					}
                    				

                    					markLeaf(elementTypeProvider.getElement_ReferenceElementCrossReference_1_0ElementType());
                    				
                    otherlv_1=(Token)match(input,RULE_ID,FollowSets000.FOLLOW_5); 

                    					doneLeaf(otherlv_1);
                    				

                    }


                    }
                    break;

            }


            			markLeaf(elementTypeProvider.getElement_LeftCurlyBracketKeyword_2ElementType());
            		
            otherlv_2=(Token)match(input,11,FollowSets000.FOLLOW_6); 

            			doneLeaf(otherlv_2);
            		
            // PsiInternalAbstractIgnoreCaseLinkingTestLanguage.g:130:3: ( (lv_elements_3_0= ruleElement ) )*
            loop3:
            do {
                int alt3=2;
                int LA3_0 = input.LA(1);

                if ( (LA3_0==RULE_ID) ) {
                    alt3=1;
                }


                switch (alt3) {
            	case 1 :
            	    // PsiInternalAbstractIgnoreCaseLinkingTestLanguage.g:131:4: (lv_elements_3_0= ruleElement )
            	    {
            	    // PsiInternalAbstractIgnoreCaseLinkingTestLanguage.g:131:4: (lv_elements_3_0= ruleElement )
            	    // PsiInternalAbstractIgnoreCaseLinkingTestLanguage.g:132:5: lv_elements_3_0= ruleElement
            	    {

            	    					markComposite(elementTypeProvider.getElement_ElementsElementParserRuleCall_3_0ElementType());
            	    				
            	    pushFollow(FollowSets000.FOLLOW_6);
            	    lv_elements_3_0=ruleElement();

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
            	    break loop3;
                }
            } while (true);


            			markLeaf(elementTypeProvider.getElement_RightCurlyBracketKeyword_4ElementType());
            		
            otherlv_4=(Token)match(input,12,FollowSets000.FOLLOW_2); 

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
    // $ANTLR end "ruleElement"

    // Delegated rules


 

    
    private static class FollowSets000 {
        public static final BitSet FOLLOW_1 = new BitSet(new long[]{0x0000000000000000L});
        public static final BitSet FOLLOW_2 = new BitSet(new long[]{0x0000000000000002L});
        public static final BitSet FOLLOW_3 = new BitSet(new long[]{0x0000000000000012L});
        public static final BitSet FOLLOW_4 = new BitSet(new long[]{0x0000000000000810L});
        public static final BitSet FOLLOW_5 = new BitSet(new long[]{0x0000000000000800L});
        public static final BitSet FOLLOW_6 = new BitSet(new long[]{0x0000000000001010L});
    }


}