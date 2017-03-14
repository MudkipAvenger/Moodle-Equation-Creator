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
public class FunctionNodeFactory {
    
    public static FunctionOperatorNode getFunctionNode(String function)
    {
        switch(function)
        {
            case "log":
            case "ln":
                return new LogFunctionNode();
            default:
                return null;
        }
    }
}
