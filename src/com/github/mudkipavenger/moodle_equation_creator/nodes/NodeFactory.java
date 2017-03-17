/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.github.mudkipavenger.moodle_equation_creator.nodes;

import com.github.mudkipavenger.moodle_equation_creator.InfixToPostfix;

/**
 *
 * @author levi
 */
public class NodeFactory {
    
    public static Node getNode(String s)
    {
        if(InfixToPostfix.isFunctionOperator(s))
        {
            return FunctionNodeFactory.getFunctionNode(s);
        }
        else if(s.length() == 1 && InfixToPostfix.isOperator(s.charAt(0)))
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
        else
        {
            return new OperandNode(s);
        }
    }
}
