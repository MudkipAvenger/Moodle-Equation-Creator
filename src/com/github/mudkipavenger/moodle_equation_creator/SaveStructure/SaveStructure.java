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
    
}
