/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.github.mudkipavenger.moodle_equation_creator.wildcard;

import java.io.Serializable;
import java.util.HashMap;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 *
 * @author levi
 */
public class WildCardManager implements Serializable {
    
    private HashMap<String, WildCard> wildcards;
    private WildCard wildcardBeingEdited;  //only one wildcard can be edited at a time
    
    public WildCardManager()
    {
        wildcards = new HashMap<String, WildCard>();
        wildcardBeingEdited = null;
    }
    
    public HashMap getWildCards()
    {
        return wildcards;
    }
    
    
    public WildCard getWildCard(String key)
    {
        return wildcards.get(key);
    }
    
    public void addWildCard(WildCard wildcard)
    {
        //DefaultTableModel model = (DefaultTableModel) table.getModel();
        
        if(wildCardExists(wildcard))    //dont add wildcards already added
            return;
        
        //catch expression wildcards
        if(wildcard.isExpresion())
        {
            wildcard.setIsOutput(false);
            wildcard.setIsMaster(true);
            wildcard.setIsTwoPartWildCard(false);
            wildcards.put(wildcard.getName(), wildcard);
            return;
        }
        
        float min = (Float.valueOf(wildcard.getMin()));
        float max = (Float.valueOf(wildcard.getMax())); 
        float interval = (Float.valueOf(wildcard.getInterval()));
        
        if(interval == 1)
        {
            wildcard.setIsOutput(true);
            wildcard.setIsTwoPartWildCard(false);
            wildcard.setIsMaster(true);
            wildcard.setIsExpression(false);
            wildcards.put(wildcard.getName(), wildcard);
        }
        else
        {
            WildCard w1;
            WildCard w2;
            w1 = new WildCard();
            w1.setName(wildcard.getName());
            
            w2 = new WildCard();
            w2.setName(w1.getName() + "_");
            
            w1.setValue("(" + wildcard.getMin() + ") + ((" + w2.getName() + ") * (" + wildcard.getInterval() + "))");
            w2.setValue("{" + w2.getName() + "}");
            
            w1.setMin(wildcard.getMin());
            w1.setMax(wildcard.getMax());
            w2.setMin("0");
            w2.setMax("" + ((max - min) / (interval)));
            
            w1.setInterval(wildcard.getInterval());
            w2.setInterval("1");
            
            w1.setIsTwoPartWildCard(true);
            w2.setIsTwoPartWildCard(true);
            
            w1.setIsMaster(true);
            w2.setIsMaster(false);
            
            w1.setSecondRef(w2);
            w2.setSecondRef(w1);
            
            w1.setIsOutput(false);
            w2.setIsOutput(true);
            
            wildcards.put(w1.getName(), w1);
            wildcards.put(w2.getName(), w2);
        }
    }
    
    public String insertWildCardsIntoExpression(String expression)
    {
        StringBuilder output = new StringBuilder(expression);
        for ( String key : wildcards.keySet() ) 
        {
            Pattern pattern = Pattern.compile("((\\b|^|\\()(?!\\{)" + key + "(?!\\})(\\)|\\b|$))");
            Matcher matcher = pattern.matcher(output);
            while(matcher.find())
            {
                output.replace(matcher.start(), matcher.end(), insertWildCardsIntoExpression("(" + wildcards.get(key).getValue() + ")"));
                matcher = pattern.matcher(output);
            }
        }
        return output.toString();
    }
    
    public String insertWildCardsIntoQuestion(String question)
    {
        StringBuilder output = new StringBuilder(question);
        for ( String key : wildcards.keySet() ) 
        {
            //Pattern pattern = Pattern.compile("((\\b|^|\\()(?!\\{)" + key + "(?!\\})(\\)|\\b|$))");
            Pattern pattern = Pattern.compile("((\\b|^)(?!\\{)" + key + "(?!\\})(\\b|$))");
            Matcher matcher = pattern.matcher(output);
            while(matcher.find())
            {
                output.replace(matcher.start(), matcher.end(), "{=" + insertWildCardsIntoExpression("(" + wildcards.get(key).getValue() + ")") + "}");
                matcher = pattern.matcher(output);
            } 
        }
        return output.toString();
    }
    
    
    
    public boolean wildCardExists(WildCard w)
    {
        return wildcards.containsKey(w.getName());
    }
    
    public boolean wildCardExists(String name)
    {
        return wildcards.containsKey(name);
    }
    
    public void removeWildCard(WildCard w)
    {
        if(w == null)
            return;
        if(w.isTwoPartWildCard())   //delete both parts
        {
            wildcards.remove(w.getSecondRef().getName());
            wildcards.remove(w.getName());
        }
        else
        {
            wildcards.remove(w.getName());
        }
    }
    
    public void addWildCardToEdit(WildCard w)
    {
        if(wildcardBeingEdited != null)
        {
            //error
            return;
        }
        else
        {
            wildcardBeingEdited = w;
        }
    }
    
    public void pushChangesToEditingWildCards(WildCard newWildCard)
    {
        if(wildcardBeingEdited == null)
        {
            return;
        }
        
        if(wildcardBeingEdited.isTwoPartWildCard())
        {
            removeWildCard(wildcardBeingEdited.getSecondRef());
            removeWildCard(wildcardBeingEdited);
        }
        else
        {
            removeWildCard(wildcardBeingEdited);
        }
        addWildCard(newWildCard);
        clearEditingWildCards();
    }
    
    public void pushChangesToEditingExpressionWildCards(WildCard newWildCard, String originalLaTex)
    {
        if(!wildcardBeingEdited.isExpresion())
        {
            pushChangesToEditingWildCards(newWildCard);
            return;
        }
        newWildCard.setOriginalLaTexExpression(originalLaTex);
        newWildCard.setIsExpression(true);
        pushChangesToEditingWildCards(newWildCard);
    }
    
    public void clearEditingWildCards()
    {
        wildcardBeingEdited = null;
    }
    
    public WildCard getWildCardBeingEdited()
    {
        return wildcardBeingEdited;
    }
    
}
