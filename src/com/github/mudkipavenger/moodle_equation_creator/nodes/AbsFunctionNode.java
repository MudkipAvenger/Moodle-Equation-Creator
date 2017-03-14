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
public class AbsFunctionNode extends FunctionOperatorNode {
    
    public AbsFunctionNode()
    {
        setFunctionName("abs");
        setNumberOfArguments(1);
        setArgs(new ArrayList<Node>());
    }
    
    @Override
    public void traverse()
    {
        System.out.print(getFunctionName() + "(");
        for(int i = 0; i < getArgs().size(); i++)
        {
            if(i >= 1)
            {
                System.out.print(", ");
            }
            getArgs().get(i).traverse();
        }
        System.out.print(")");
    }
    
}
