/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.github.mudkipavenger.moodle_equation_creator.nodes;

/**
 *
 * @author levi
 */
public class OperandNode extends Node {
    
    private double value;
    
    public OperandNode()
    {
        setValue(0);
    }
    
    public OperandNode(double d)
    {
        setValue(d);
    }
    
    public void setValue(double d)
    {
        value = d;
    }
    
    public double getValue()
    {
        return value;
    }
    
    @Override
    public void traverse()
    {
        
    }
}
