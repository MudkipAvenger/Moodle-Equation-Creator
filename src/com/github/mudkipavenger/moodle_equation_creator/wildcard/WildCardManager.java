/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.github.mudkipavenger.moodle_equation_creator.wildcard;

import java.util.HashMap;
import java.util.Objects;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;

/**
 *
 * @author levi
 */
public class WildCardManager {
    
    private static HashMap<String, String> wildcards = new HashMap();
    
    public static HashMap getWildCards()
    {
        return wildcards;
    }
    
    public static void addWildCard(WildCard w, JTable table)
    {
        if(!wildCardExists(w))
        {
            addWildCardToTable(w, table);
        }
    }
    
    private static void addWildCardToHashMap(WildCard w)
    {
            wildcards.put(w.getName(), w.getValue());

    }
    
    public static String insertWildcardsIntoExpression(String expression)
    {
        StringBuilder output = new StringBuilder(expression);
        boolean replaced = false;
        do
        {
            replaced = false;
            for ( String key : wildcards.keySet() ) {
                int index = output.indexOf("(" + key + ")");
                while(index != -1)
                {
                    replaced = true;
                    output.replace(index + 1, index + 1 + key.length(), wildcards.get(key));
                    index = output.indexOf("(" + key + ")", index + 1);
                }
            }
        }while(replaced);
        
        return output.toString();
    }
    
    private static void addWildCardToTable(WildCard wildcard, JTable table)
    {
        DefaultTableModel model = (DefaultTableModel) table.getModel();
        
        if(Objects.equals(wildcard.getMin(), "") || Objects.equals(wildcard.getMax(), "") || Objects.equals(wildcard.getInterval(), ""))
        {
            model.addRow(new Object[] {wildcard.getName(), wildcard.getValue(), "", ""});
            addWildCardToHashMap(wildcard);
            return;
        }
        
        float min = (Float.valueOf(wildcard.getMin()));
        float max = (Float.valueOf(wildcard.getMax())); 
        float interval = (Float.valueOf(wildcard.getInterval()));
        
        if(interval == 1)
        {
            model.addRow(new Object[] {wildcard.getName(), wildcard.getValue(), wildcard.getMin(), wildcard.getMax()});
            addWildCardToHashMap(wildcard);
        }
        else
        {
            WildCard w1;
            WildCard w2;
            w1 = new WildCard();
            w1.setName(wildcard.getName());
            
            w2 = new WildCard();
            w2.setName("#" + w1.getName());
            
            w1.setValue("((" + wildcard.getMin() + ") + ((" + w2.getName() + ") * (" + wildcard.getInterval() + ")))");
            w2.setValue("{" + w2.getName() + "}");
            
            w1.setMin("");
            w1.setMax("");
            w2.setMin("0");
            w2.setMax("" + ((max - min) / (interval)));
            
            model.addRow(new Object[] {w1.getName(), w1.getValue(), w1.getMin(), w1.getMax()});
            model.addRow(new Object[] {w2.getName(), w2.getValue(), w2.getMin(), w2.getMax()});
            
            addWildCardToHashMap(w1);
            addWildCardToHashMap(w2);
        }
    }
    
    public static boolean wildCardExists(WildCard w)
    {
        return wildcards.containsKey(w.getName());
    }
    
}
