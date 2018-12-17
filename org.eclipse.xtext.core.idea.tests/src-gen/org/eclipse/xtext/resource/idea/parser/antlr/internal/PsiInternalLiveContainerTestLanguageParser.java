package org.eclipse.xtext.resource.idea.parser.antlr.internal;

import com.intellij.lang.PsiBuilder;
import org.antlr.runtime.*;
import org.eclipse.xtext.idea.parser.AbstractPsiAntlrParser;
import org.eclipse.xtext.resource.idea.lang.LiveContainerTestLanguageElementTypeProvider;
import org.eclipse.xtext.resource.services.LiveContainerTestLanguageGrammarAccess;

@SuppressWarnings("all")
public class PsiInternalLiveContainerTestLanguageParser extends AbstractPsiAntlrParser {
    public static final String[] tokenNames = new String[] {
        "<invalid>", "<EOR>", "<DOWN>", "<UP>", "RULE_ID", "RULE_INT", "RULE_STRING", "RULE_ML_COMMENT", "RULE_SL_COMMENT", "RULE_WS", "RULE_ANY_OTHER"
    };
    public static final int RULE_ID=4;
    public static final int RULE_STRING=6;
    public static final int RULE_ANY_OTHER=10;
    public static final int RULE_INT=5;
    public static final int RULE_WS=9;
    public static final int RULE_SL_COMMENT=8;
    public static final int EOF=-1;
    public static final int RULE_ML_COMMENT=7;

    // delegates
    // delegators


        public PsiInternalLiveContainerTestLanguageParser(TokenStream input) {
            this(input, new RecognizerSharedState());
        }
        public PsiInternalLiveContainerTestLanguageParser(TokenStream input, RecognizerSharedState state) {
            super(input, state);
             
        }
        

    public String[] getTokenNames() { return PsiInternalLiveContainerTestLanguageParser.tokenNames; }
    public String getGrammarFileName() { return "PsiInternalLiveContainerTestLanguage.g"; }



    	protected LiveContainerTestLanguageGrammarAccess grammarAccess;

    	protected LiveContainerTestLanguageElementTypeProvider elementTypeProvider;

    	public PsiInternalLiveContainerTestLanguageParser(PsiBuilder builder, TokenStream input, LiveContainerTestLanguageElementTypeProvider elementTypeProvider, LiveContainerTestLanguageGrammarAccess grammarAccess) {
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
    // PsiInternalLiveContainerTestLanguage.g:52:1: entryRuleModel returns [Boolean current=false] : iv_ruleModel= ruleModel EOF ;
    public final Boolean entryRuleModel() throws RecognitionException {
        Boolean current = false;

        Boolean iv_ruleModel = null;


        try {
            // PsiInternalLiveContainerTestLanguage.g:52:47: (iv_ruleModel= ruleModel EOF )
            // PsiInternalLiveContainerTestLanguage.g:53:2: iv_ruleModel= ruleModel EOF
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
    // PsiInternalLiveContainerTestLanguage.g:59:1: ruleModel returns [Boolean current=false] : ( (lv_name_0_0= RULE_ID ) ) ;
    public final Boolean ruleModel() throws RecognitionException {
        Boolean current = false;

        Token lv_name_0_0=null;

        try {
            // PsiInternalLiveContainerTestLanguage.g:60:1: ( ( (lv_name_0_0= RULE_ID ) ) )
            // PsiInternalLiveContainerTestLanguage.g:61:2: ( (lv_name_0_0= RULE_ID ) )
            {
            // PsiInternalLiveContainerTestLanguage.g:61:2: ( (lv_name_0_0= RULE_ID ) )
            // PsiInternalLiveContainerTestLanguage.g:62:3: (lv_name_0_0= RULE_ID )
            {
            // PsiInternalLiveContainerTestLanguage.g:62:3: (lv_name_0_0= RULE_ID )
            // PsiInternalLiveContainerTestLanguage.g:63:4: lv_name_0_0= RULE_ID
            {

            				markLeaf(elementTypeProvider.getModel_NameIDTerminalRuleCall_0ElementType());
            			
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
    // $ANTLR end "ruleModel"

    // Delegated rules


 

    
    private static class FollowSets000 {
        public static final BitSet FOLLOW_1 = new BitSet(new long[]{0x0000000000000000L});
        public static final BitSet FOLLOW_2 = new BitSet(new long[]{0x0000000000000002L});
    }


}