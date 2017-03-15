/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.github.mudkipavenger.moodle_equation_creator.Tree;

import com.github.mudkipavenger.moodle_equation_creator.InfixToPostfix;
import com.github.mudkipavenger.moodle_equation_creator.nodes.FunctionOperatorNode;
import com.github.mudkipavenger.moodle_equation_creator.nodes.Node;
import com.github.mudkipavenger.moodle_equation_creator.nodes.NodeFactory;
import com.github.mudkipavenger.moodle_equation_creator.nodes.OperandNode;
import com.github.mudkipavenger.moodle_equation_creator.nodes.OperatorNode;
import com.github.mudkipavenger.moodle_equation_creator.LaTexParser.LaTexParser;
import java.util.Stack;

/**
 *
 * @author levi
 */
public class NodeTreebuilder {
    
    public static NodeTree buildTreeFromPostfix(String postfix)
    {
        String [] tokens = postfix.split("\\s+");   
        Stack<Node> stack = new Stack();
        
        for(int i = 1; i < tokens.length; i++)
        {
            String temp = tokens[i];
            Node node = NodeFactory.getNode(temp);
            
            if(node instanceof OperatorNode)
            {
                ((OperatorNode) node).setRight(stack.pop());
                ((OperatorNode) node).setLeft(stack.pop());
                stack.push(node);
            }
            else if(node instanceof OperandNode)
            {
                stack.push(node);
            }
            else if(node instanceof FunctionOperatorNode)
            {
                for(int k = 0; k < ((FunctionOperatorNode) node).getNumberOfArguments(); k++)
                    ((FunctionOperatorNode) node).addArgument(stack.pop());
                stack.push(node);
            }
        }
        
        return new NodeTree(stack.pop());
    }
    
    public static NodeTree buildTreeFromInfix(String infix)
    {
        return buildTreeFromPostfix(InfixToPostfix.convertToPostfix(infix));
    }
    
    public static NodeTree buildTreeFromLaTex(String latex)
    {
        return buildTreeFromInfix(LaTexParser.latexToInfix(latex));
    }
    
}
