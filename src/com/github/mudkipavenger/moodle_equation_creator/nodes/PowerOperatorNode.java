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
public class PowerOperatorNode extends OperatorNode {
    
    public PowerOperatorNode()
    {
        super('^');
    }
    
    @Override
    public String traverse()
    {
        String output = "";
        output += "pow((";
        output += getLeft().traverse();
        output += "), (";
        output += getRight().traverse();
        output +="))";
        return output;
    }
    
}
