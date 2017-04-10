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
    
    
    public static enum Size {normalSize, large, Large, LARGE, huge, HUGE};
    
    private String expression;
    private Size size;
    
    public Feedback()
    {
        this("", Size.normalSize);
    }
    
    public Feedback(String e)
    {
        setExpression(e);
        setSize(Size.normalSize);
    }
    
    public Feedback(String e, Size s)
    {
        setExpression(e);
        setSize(s);
    }
    
    public String getExpression()
    {
        return expression;
    }
    
    public void setExpression(String e)
    {
        expression = e;
    }
    
    public Size getSize()
    {
        return size;
    }
    
    public void setSize(Size s)
    {
        size = s;
    }
    
}
