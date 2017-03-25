/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.github.mudkipavenger.moodle_equation_creator.wildcard;

import java.util.HashMap;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;

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
    
    public WildCard()
    {
        setName("");
        setValue("");
        setMin("");
        setMax("");
        setDecimalPlaces(0);
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
    
}
