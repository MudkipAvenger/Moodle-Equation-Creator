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
public class OperatorNode extends Node {
    
    private char operator;  //+ - / *
    private Node left;
    private Node right;
    
    public OperatorNode()
    {
        operator = '+';
        left = null;
        right = null;
    }
    
    public OperatorNode(char c)
    {
        operator = c;
    }
    
    public void setRight(Node r)
    {
        right = r;
    }
    
    public void setOperator(char c)
    {
        operator = c;
    }
    
    public void setLeft(Node l)
    {
        left = l;
    }
    
    public char getOperator()
    {
        return operator;
    }
    
    public Node getLeft()
    {
        return left;
    }
    
    public Node getRight()
    {
        return right;
    }
    
    @Override
    public String traverse()
    {
        String output = "";
        output += "(";
        output += left.traverse();
        output += ") " + getOperator() + " (";
        output += right.traverse();
        output += ")";
        return output;
    }
    
}
