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
    
    private String value;
    
    public OperandNode()
    {
        setValue("");
    }
    
    public OperandNode(String d)
    {
        setValue(d);
    }
    
    public void setValue(String d)
    {
        value = d;
    }
    
    public String getValue()
    {
        return value;
    }
    
    @Override
    public String traverse()
    {
        return value;
    }
}
