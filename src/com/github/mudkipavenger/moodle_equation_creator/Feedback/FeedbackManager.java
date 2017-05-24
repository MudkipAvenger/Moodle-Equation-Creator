/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.github.mudkipavenger.moodle_equation_creator.Feedback;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.Iterator;

/**
 *
 * @author levi
 */
public class FeedbackManager implements Serializable {
    
    private ArrayList<Feedback> expressions;
    private int step;
    
    public FeedbackManager()
    {
        expressions = new ArrayList();
        step = 0;
    }
    
    public void addFeedback(Feedback f)
    {
        f.setStep(++step);  //assign new feedback a step
        expressions.add(f);
    }
    
    
    public Feedback getFeedback(int index)
    {
        return expressions.get(index);
    }
    
    public void removeFeedback(int index)
    {
        expressions.remove(index);
    }
    
    public void removeFeedback(Feedback f)
    {
        expressions.remove(f);
    }
    
    public Iterator getIterator()
    {
        return expressions.iterator();
    }
    
    private void sortArrayList()
    {
        expressions.sort(new Comparator() {
            @Override
            public int compare(Object f1, Object f2)
            {
                return ((Feedback) f1).getStep() < ((Feedback) f2).getStep() ? -1 : ((Feedback) f1).getStep() == ((Feedback) f2).getStep() ? 0 : 1;
            }
        });
    }
    
    public String print()
    {
        sortArrayList();
        String out = "";
        for(int i = 0; i < expressions.size(); i++)
        {
            out += "\\( \\" + expressions.get(i).getSize().name() + " " +  expressions.get(i).getExpression() + " \\)";
            out += "\n\n";
        }
        return out;
    }
    
}
