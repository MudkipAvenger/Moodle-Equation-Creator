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
    
    @Override
    public void traverse()
    {
        System.out.print("(");
        left.traverse();
        System.out.print(") " + operator + " (");
        right.traverse();
        System.out.print(")");
    }
    
}
