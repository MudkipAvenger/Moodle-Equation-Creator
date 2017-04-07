/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.github.mudkipavenger.moodle_equation_creator.Feedback;

/**
 *
 * @author levi
 */
public class Feedback {
    
    private String expression;
    private int size = 1;
    
    public Feedback()
    {
        this("");
    }
    
    public Feedback(String e)
    {
        setExpression(e);
    }
    
    public String getExpression()
    {
        return expression;
    }
    
    public void setExpression(String e)
    {
        expression = e;
    }
    
    public int getSize()
    {
        return size;
    }
    
    public void setSize(int s)
    {
        size = s;
    }
    
}
