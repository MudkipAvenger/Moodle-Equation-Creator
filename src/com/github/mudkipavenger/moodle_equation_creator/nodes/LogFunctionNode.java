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
public class LogFunctionNode extends FunctionOperatorNode {
    
    
    public LogFunctionNode()
    {
        setFunctionName("log");
        setNumberOfArguments(1);
        setArgs(new ArrayList<Node>());
    }
    
    @Override
    public String traverse()
    {
        String output = "";
        output += getFunctionName() + "(";
        for(int i = 0; i < getArgs().size(); i++)
        {
            if(i >= 1)
            {
                output += ", ";
            }
            output += getArgs().get(i).traverse();
        }
        output +=")";
        return output;
    }
}
