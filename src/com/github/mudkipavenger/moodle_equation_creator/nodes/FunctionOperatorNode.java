/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.github.mudkipavenger.moodle_equation_creator.nodes;

import java.util.ArrayList;

/**
 *
 * @author levi
 */
public abstract class FunctionOperatorNode extends Node {
    
    private int numberOfArguments;
    private String functionName;
    private ArrayList<Node> args;
    
    public FunctionOperatorNode()
    {
        args = new ArrayList();
    }
    
    public void setNumberOfArguments(int i)
    {
        numberOfArguments = i;
    }
    
    public int getNumberOfArguments()
    {
        return numberOfArguments;
    }
    
    public void setFunctionName(String name)
    {
        functionName = name;
    }
    
    public String getFunctionName()
    {
        return functionName;
    }
    
    public void setArgs(ArrayList<Node> a)
    {
        args = a;
    }
    
    public ArrayList<Node> getArgs()
    {
        return args;
    }
    
    public void addArgument(Node arg)
    {
        if(getArgs().size() < getNumberOfArguments())
            getArgs().add(arg);
    }
    
}
