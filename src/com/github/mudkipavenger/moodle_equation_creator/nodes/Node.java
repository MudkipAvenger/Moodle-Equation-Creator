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
public abstract class Node {
    
    private int value;
    
    public int getValue()
    {
        return value;
    }
    
    public void setValue(int v)
    {
        value = v;
    }
    
    public abstract void traverse();
}
