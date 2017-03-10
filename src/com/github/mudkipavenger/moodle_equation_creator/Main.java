/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.github.mudkipavenger.moodle_equation_creator;

import com.github.mudkipavenger.moodle_equation_creator.nodes.*;

/**
 *
 * @author levi
 */
public class Main {
    
    public static void main(String [] args)
    {
        Node root;
        OperatorNode operator1 = new OperatorNode();
        operator1.setOperator('*');
        OperandNode operand1 = new OperandNode(5);
        OperandNode operand2 = new OperandNode(6);
        root = operator1;
        operator1.setLeft(operand1);
        operator1.setRight(operand2);
        root.traverse();
        
        System.out.println();
        
        Node tempRoot;
        OperatorNode tempOperator = new OperatorNode();
        tempOperator.setOperator('/');
        OperandNode tempOperand = new OperandNode(120);
        tempOperator.setLeft(tempOperand);
        tempOperator.setRight(root);
        tempRoot = tempOperator;
        root = tempRoot;
        root.traverse();
        System.out.println();
        
        itof i = new itof();
        
        String s = "(5 + 55) * (log(5)) + 33^3";
        String s2 = "\frac{{a}}{2}";
        
        int b = s2.indexOf("\frac");
        
        while(b != -1)
        {
            
            int index = b;
            StringBuilder str = new StringBuilder(s2);
            str.replace(b, b + 4, "");
            str.setCharAt(index, '(');
            for(int j = index+1, braceCnt = 0; j < str.length(); j++)
            {
                if(str.charAt(j) == '{')
                    braceCnt++;
                if(str.charAt(j) == '}')
                {
                    if(braceCnt == 0)
                    {
                        str.setCharAt(j, ')');
                        index = j + 1;
                        break;
                    }
                    else
                    {
                        braceCnt--;
                    }
                }
            }
            for(int j = index; j < str.length(); j++)
            {
                if(str.charAt(j) == '{')
                {
                    str.insert(j, " / ");
                    str.setCharAt(j + 3, '(');
                    index = j + 1;
                    break;
                }
            }
            for(int j = index, braceCnt = 0; j < str.length(); j++)
            {
                if(str.charAt(j) == '{')
                    braceCnt++;
                if(str.charAt(j) == '}')
                {
                    if(braceCnt == 0)
                    {
                        str.setCharAt(j, ')');
                        index = j + 1;
                        break;
                    }
                    else
                    {
                        braceCnt--;
                    }
                }
            }
            
            s2 = str.toString();
            b= str.indexOf("\frac");
        }
        
        //s2 = s2.replace("\frac{", "(");
        System.out.println(s2);
        
        System.out.println(i.convertToPostfix(s));
        
        //double d = 5;
        //System.out.println(d);
    }
}
