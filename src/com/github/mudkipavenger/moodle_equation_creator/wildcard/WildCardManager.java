/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.github.mudkipavenger.moodle_equation_creator.wildcard;

import java.util.HashMap;
import java.util.Objects;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 *
 * @author levi
 */
public class WildCardManager {
    
    private static final HashMap<String, WildCard> wildcards = new HashMap();
    private static WildCard wildcardBeingEdited = null;  //at most 2 wildcards can be edited at a time
    
    
    public static HashMap getWildCards()
    {
        return wildcards;
    }
    
    
    public static WildCard getWildCard(String key)
    {
        return wildcards.get(key);
    }
    
    public static void addWildCard(WildCard wildcard)
    {
        //DefaultTableModel model = (DefaultTableModel) table.getModel();
        
        /*if(Objects.equals(wildcard.getMin(), "") || Objects.equals(wildcard.getMax(), "") || Objects.equals(wildcard.getInterval(), ""))
        {
            model.addRow(new Object[] {wildcard.getName(), wildcard.getValue(), "", ""});
            addWildCardToHashMap(wildcard);
            return;
        }*/
        
        if(wildCardExists(wildcard))
            return;
        
        float min = (Float.valueOf(wildcard.getMin()));
        float max = (Float.valueOf(wildcard.getMax())); 
        float interval = (Float.valueOf(wildcard.getInterval()));
        
        if(interval == 1)
        {
            wildcard.setIsOutput(true);
            //model.addRow(new Object[] {wildcard.getName(), wildcard.getValue(), wildcard.getMin(), wildcard.getMax()});
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
            
            
            //model.addRow(new Object[] {w1.getName(), w1.getValue(), w1.getMin(), w1.getMax()});
            //model.addRow(new Object[] {w2.getName(), w2.getValue(), w2.getMin(), w2.getMax()});
            
            wildcards.put(w1.getName(), w1);
            wildcards.put(w2.getName(), w2);
        }
    }
    
    public static String insertWildCardsIntoExpression(String expression)
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
    
    public static String insertWildCardsIntoQuestion(String question)
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
    
    
    
    public static boolean wildCardExists(WildCard w)
    {
        return wildcards.containsKey(w.getName());
    }
    
    public static boolean wildCardExists(String name)
    {
        return wildcards.containsKey(name);
    }
    
    public static void removeWildCard(WildCard w)
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
    
    public static void addWildCardToEdit(WildCard w)
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
    
    public static void pushChangesToEditingWildCards(WildCard newWildCard)
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
    
    public static void clearEditingWildCards()
    {
        wildcardBeingEdited = null;
    }
    
    public static WildCard getWildCardBeingEdited()
    {
        return wildcardBeingEdited;
    }
    
}
