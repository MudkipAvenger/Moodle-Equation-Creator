/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.github.mudkipavenger.moodle_equation_creator;

import com.github.mudkipavenger.moodle_equation_creator.nodes.*;
import java.util.Scanner;
import java.util.Stack;

/**
 *
 * @author levi
 */
public class Main {
    
    public static void main(String [] args)
    {
        
        itof i = new itof();
        
        Scanner scanner = new Scanner(System.in);
        
        System.out.println("Enter a formula in LaTex form:");
        String formula = scanner.nextLine();
        formula = formula.replace("\\", "\\\\");
        //System.out.println(formula);
        
        //String s = "(5 + 55) * (log(5)) + 33^3";
        //String s2 = "(\frac{{a}}{2.5}) \times (\frac{(45 + 5)^{{b}}} {32})";
        //
        
        //s2 = s2.replace("\\\\f", "\\\\\\\\f");
        //System.out.println(s2);
        
        int index = formula.indexOf("\\\\frac");
        
        while(index != -1)
        {
            
            StringBuilder str = new StringBuilder(formula);
            str.replace(index, index + 6, "");
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
                        break;
                    }
                    else
                    {
                        braceCnt--;
                    }
                }
            }
            index = str.indexOf("\\\\frac");
            formula = str.toString();
        }
        
        
        formula = formula.replace("\\\\times", "*");
        formula = formula.replace("\\\\ast", "*");
        formula = formula.replace("\\\\div", "/");
        
        formula = formula.replace("\\\\;", "");
        formula = formula.replace("\\\\left(", "(");
        formula = formula.replace("\\\\right)", ")");
        
        index = formula.indexOf("^");
        int indexAfterExponent = 0;
        
        while(index != -1)
        {
            StringBuilder str = new StringBuilder(formula);
            if(str.charAt(index + 1) == '{')
            {
                indexAfterExponent = index + 1;
                str.setCharAt(index + 1, '(');
                for(int j = index+1, braceCnt = 0; j < str.length(); j++)
                {
                    if(str.charAt(j) == '{')
                        braceCnt++;
                    if(str.charAt(j) == '}')
                    {
                        if(braceCnt == 0)
                        {
                            str.setCharAt(j, ')');
                            break;
                        }
                        else
                        {
                            braceCnt--;
                        }
                    }
                }
            }
            index = str.indexOf("^", indexAfterExponent);
            formula = str.toString();
        }
        
        index = formula.indexOf("\\\\sqrt");
        
        while(index != -1)
        {
            StringBuilder str = new StringBuilder(formula);
            str.replace(index, index + 6, "");
            String rootNumber = "";
            int startIndex = index;
            
            for(int j = index + 1, braceCnt = 0; j < str.length(); j++)
            {
                if(str.charAt(j) == '[')
                    braceCnt++;
                else if(str.charAt(j) == ']')
                {
                    if(braceCnt == 0)
                    {
                        index = j + 1;
                        break;
                        
                    }
                    else
                        braceCnt--;
                }
                else
                {
                    rootNumber += str.charAt(j);
                }
            }
            str.replace(startIndex, index, "");
            index = index - (index - startIndex);
            //System.out.println(rootNumber);
            
            for(int j = index; j < str.length(); j++)
            {
                if(str.charAt(j) == '{')
                {
                    str.setCharAt(j, '(');
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
                        str.insert(index, "^((1) / (" + rootNumber + "))");
                        break;
                    }
                    else
                    {
                        braceCnt--;
                    }
                }
            }
            
            
            formula = str.toString();
            index = -1;
        }
        
        //System.out.println(formula);
        String formula_out = i.convertToPostfix(formula);
        
        System.out.println(formula_out);
        
        String [] tokens = formula_out.split("\\s+");
        //System.out.println(tokens.length);
        
        Stack<Node> stack = new Stack();
        
        for(int j = 1; j < tokens.length; j++)
        {
            String temp = tokens[j];
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
        
        Node root = stack.pop();
        root.traverse();
        System.out.println();
        
    }
}
