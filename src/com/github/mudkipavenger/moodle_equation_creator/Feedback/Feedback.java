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
    
    
    public static enum Size {normalsize, large, Large, LARGE, huge, Huge};
    
    private String expression;
    private Size size;
    private int step;
    
    public Feedback()
    {
        this("", Size.normalsize);
    }
    
    public Feedback(String e)
    {
        this(e, Size.normalsize);
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
    
    public void setStep(int step)
    {
        this.step = step;
    }
    
    public int getStep()
    {
        return step;
    }
    
}
