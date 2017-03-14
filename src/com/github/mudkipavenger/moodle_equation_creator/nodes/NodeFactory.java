/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.github.mudkipavenger.moodle_equation_creator.nodes;

import com.github.mudkipavenger.moodle_equation_creator.itof;

/**
 *
 * @author levi
 */
public class NodeFactory {
    
    public static Node getNode(String s)
    {
        if(s.length() == 1 && itof.isOperator(s.charAt(0)))
        {
            switch(s.charAt(0))
            {
                case '+':
                case '-':
                case '*':
                case '/':
                    return new OperatorNode(s.charAt(0));
                case '^':
                    return new PowerOperatorNode();
                default:
                    return null;
            }
        }
        else if(itof.isFunctionOperator(s))
        {
            return FunctionNodeFactory.getFunctionNode(s);
        }
        else
        {
            return new OperandNode(s);
        }
    }
}
