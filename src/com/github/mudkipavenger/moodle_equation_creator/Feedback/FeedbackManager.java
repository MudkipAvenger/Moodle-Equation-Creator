/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.github.mudkipavenger.moodle_equation_creator.Feedback;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.Iterator;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;

/**
 *
 * @author levi
 */
public class FeedbackManager {
    
    private static ArrayList<Feedback> expressions = new ArrayList();
    private static int step;
    
    public static void addFeedback(Feedback f, JTable feedbackTable)
    {
        f.setStep(++step);  //assign new feedback a step
        addFeedbackToList(f);
        addFeedbackToTable(f, feedbackTable);
    }
    
    private static void addFeedbackToList(Feedback f)
    {
        expressions.add(f);
    }
    
    private static void addFeedbackToTable(Feedback f, JTable table)
    {
        DefaultTableModel model = (DefaultTableModel) table.getModel();
        model.addRow(new Object[] {f.getStep(), f.getExpression()});
    }
    
    public static Feedback getFeedback(int index)
    {
        return expressions.get(index);
    }
    
    public static void removeFeedback(int index)
    {
        expressions.remove(index);
    }
    
    public static void removeFeedback(Feedback f)
    {
        expressions.remove(f);
    }
    
    public static Iterator getIterator()
    {
        return expressions.iterator();
    }
    
    private static void sortArrayList()
    {
        expressions.sort(new Comparator() {
            @Override
            public int compare(Object f1, Object f2)
            {
                return ((Feedback) f1).getStep() < ((Feedback) f2).getStep() ? -1 : ((Feedback) f1).getStep() == ((Feedback) f2).getStep() ? 0 : 1;
            }
        });
    }
    
    public static String print()
    {
        sortArrayList();
        String out = "";
        for(int i = 0; i < expressions.size(); i++)
        {
            out += "\\( \\" + expressions.get(i).getSize().name() + " " +  expressions.get(i).getExpression() + " \\)";
            out += "\n";
        }
        return out;
    }
    
}
