/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.github.mudkipavenger.moodle_equation_creator.Feedback;

import java.util.ArrayList;
import java.util.Iterator;

/**
 *
 * @author levi
 */
public class FeedbackManager {
    
    private static ArrayList<Feedback> expressions = new ArrayList();
    
    public static void addExpression(Feedback f)
    {
        expressions.add(f);
    }
    
    public static void addExpression(String s)
    {
        expressions.add(new Feedback(s));
    }
    
    public static Feedback getExpression(int index)
    {
        return expressions.get(index);
    }
    
    public static void removeExpression(int index)
    {
        expressions.remove(index);
    }
    
    public static void removeExpression(Feedback f)
    {
        expressions.remove(f);
    }
    
    public static Iterator getIterator()
    {
        return expressions.iterator();
    }
    
    public static void print()
    {
        for(int i = 0; i < expressions.size(); i++)
        {
            System.out.println("\\( " + expressions.get(i).getExpression() + " \\)");
        }
    }
    
}
