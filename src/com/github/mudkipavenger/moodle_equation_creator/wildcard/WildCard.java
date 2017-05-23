/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.github.mudkipavenger.moodle_equation_creator.wildcard;


/**
 *
 * @author levi
 */
public class WildCard {

    private String name;
    private String value;
    private String min;
    private String max;
    private String interval;
    private int decimalPlaces;
    
    private boolean isTwoPartWildCard;  //flag for if this wildcard has two parts
    private boolean isMaster;           //flag for if this wildcard is the main part of the two (it contains the secondary wildcard)
    private WildCard secondRef;         //reference to the second wildcard if this wildcard is a two part wildcard
    private boolean isOutput;           //flag for if this wildcard needs to be displayed in the output pane (The user has to enter the wildcard info on moodle when making the question)
    private boolean isExpression;       //flag for if this wildcard was made from an expression
    
    private String originalLaTexExpression;
    
    public WildCard()
    {
        setName("");
        setValue("");
        setMin("");
        setMax("");
        setInterval("1");
        setDecimalPlaces(0);
        setIsTwoPartWildCard(false);
        setIsMaster(true);
        setIsOutput(true);
        setSecondRef(null);
        setIsExpression(false);
        setOriginalLaTexExpression("");
    }
    
    
    public String getName()
    {
        return name;
    }
    
    public void setName(String n)
    {
        name = n;
    }
    
    public String getValue()
    {
        return value;
    }
    
    public void setValue(String v)
    {
        value = v;
    }
    
    public String getMin()
    {
        return min;
    }
    
    public void setMin(String m)
    {
        min = m;
    }
    
    public String getMax()
    {
        return max;
    }
    
    public void setMax(String m)
    {
        max = m;
    }
    
    public void setInterval(String i)
    {
        interval = i;
    }
    
    public String getInterval()
    {
        return interval;
    }
    
    public int getDecimalPlaces()
    {
        return decimalPlaces;
    }
    
    public void setDecimalPlaces(int n)
    {
        decimalPlaces = n;
    }
    
    public void setIsTwoPartWildCard(boolean b)
    {
        isTwoPartWildCard = b;
    }
    
    public boolean isTwoPartWildCard()
    {
        return isTwoPartWildCard;
    }
    
    public void setIsMaster(boolean b)
    {
        isMaster = b;
    }
    
    public boolean isMaster()
    {
        return isMaster;
    }
    
    public void setIsOutput(boolean b)
    {
        isOutput = b;
    }
    
    public boolean isOutput()
    {
        return isOutput;
    }
    
    public void setSecondRef(WildCard w)
    {
        secondRef = w;
    }
    
    public WildCard getSecondRef()
    {
        return secondRef;
    }
    
    public boolean isExpresion()
    {
        return isExpression;
    }
    
    public void setIsExpression(boolean b)
    {
        isExpression = b;
    }
    
    public void setOriginalLaTexExpression(String expression)
    {
        originalLaTexExpression = expression;
    }
    
    public String getOriginalLaTexExpression()
    {
        return originalLaTexExpression;
    }
}
