/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.github.mudkipavenger.moodle_equation_creator.SaveStructure;

import com.github.mudkipavenger.moodle_equation_creator.Feedback.FeedbackManager;
import com.github.mudkipavenger.moodle_equation_creator.wildcard.WildCardManager;
import java.io.Serializable;

/**
 *
 * @author levi
 */
public class SaveStructure implements Serializable {
    
    /*
    * lots of strings from gui components
    */
    private String ExpressionsPanel_inputTextArea_text;
    private String ExpressionsPanel_outputTextArea_text;
    private String QuestionPanel_questionTextArea_text;
    private String FeedbackPanel_expressionTextArea_text;
    private String FeedbackPanel_newWildcardNameTextField_text;
    private String FeedbackPanel_newWildcardExpressionTextArea_text;
    
    private FeedbackManager feedbackManager;
    private WildCardManager wildcardManager;
    
    public void setFeedbackManager(FeedbackManager f)
    {
        feedbackManager = f;
    }
    
    public FeedbackManager getFeedbackManager()
    {
        return feedbackManager;
    }
    
    public WildCardManager getWildCardManager()
    {
        return wildcardManager;
    }
    
    public void setWildCardManager(WildCardManager w)
    {
        wildcardManager = w;
    }
    
    public void setExpressionsPanel_inputTextArea_text(String s)
    {
        ExpressionsPanel_inputTextArea_text = s;
    }
    
    public String getExpressionsPanel_inputTextArea_text()
    {
        return ExpressionsPanel_inputTextArea_text;
    }
    
    public void setExpressionsPanel_outputTextArea_text(String s)
    {
        ExpressionsPanel_outputTextArea_text = s;
    }
    
    public String getExpressionsPanel_outputTextArea_text()
    {
        return ExpressionsPanel_outputTextArea_text;
    }
    
    public void setQuestionPanel_questionTextArea_text(String s)
    {
        QuestionPanel_questionTextArea_text = s;
    }
    
    public String getQuestionPanel_questionTextArea_text()
    {
        return QuestionPanel_questionTextArea_text;
    }
    
    public void setFeedbackPanel_expressionTextArea_text(String s)
    {
        FeedbackPanel_expressionTextArea_text = s;
    }
    
    public String getFeedbackPanel_expressionTextArea_text()
    {
        return FeedbackPanel_expressionTextArea_text;
    }
    
    public void setFeedbackPanel_newWildcardNameTextField_text(String s)
    {
        FeedbackPanel_newWildcardNameTextField_text = s;
    }
    
    public String getFeedbackPanel_newWildcardNameTextField_text()
    {
        return FeedbackPanel_newWildcardNameTextField_text;
    }
    
    public void setFeedbackPanel_newWildcardExpressionTextArea_text(String s)
    {
        FeedbackPanel_newWildcardExpressionTextArea_text = s;
    }
    
    public String getFeedbackPanel_newWildcardExpressionTextArea_text()
    {
        return FeedbackPanel_newWildcardExpressionTextArea_text;
    }
    
}
