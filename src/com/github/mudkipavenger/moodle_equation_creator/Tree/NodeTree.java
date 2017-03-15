/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.github.mudkipavenger.moodle_equation_creator.Tree;

import com.github.mudkipavenger.moodle_equation_creator.nodes.Node;

/**
 *
 * @author levi
 */
public class NodeTree {
    
    private Node root;
    
    public NodeTree()
    {
        setRoot(null);
    }
    
    public NodeTree(Node n)
    {
        setRoot(n);
    }
    
    public void setRoot(Node n)
    {
        root = n;
    }
    
    public Node getRoot()
    {
        return root;
    }
    
    public void printExpression()
    {
        root.traverse();
    }
}
