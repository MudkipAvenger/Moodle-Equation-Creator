/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.github.mudkipavenger.moodle_equation_creator.LaTexParser;

import com.github.mudkipavenger.moodle_equation_creator.InfixToPostfix;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 *
 * @author levi
 */
public class LaTexParser {
    
    public static String latexToInfix(String LaTex)
    {
        String output = LaTex;
        output = removeEscapeCharacters(output);
        output = removeSpaces(output);
        output = replaceMult(output);
        output = replaceDiv(output);
        output = removeDisplayStyle(output);
        output = replaceLaTexParenthesis(output);
        output = LaTexParser.replaceBracketsWithParenthesis(output);
        output = LaTexParser.replaceCurlyBracesWithParenthesis(output);
        output = LaTexParser.replaceBarsWithAbsoluteValue(output);
        output = LaTexParser.replaceLnWithLog(output);
        output = LaTexParser.replaceFracWithDivision(output);
        output = LaTexParser.replaceExponentWithPow(output);
        output = LaTexParser.replaceSqrtWithPow(output);
        output = replaceParenthesisWithMult(output);
        output = replaceNegatives(output);
        System.out.println(output);
        //System.out.println(output);
        return output;
    }
    
    private static String removeEscapeCharacters(String in) //repalces all \ with \\ so \frac would become \\frac
    {
        return in.replace("\\", "\\\\");    
    }
    
    private static String removeSpaces(String in)
    {
        return in.replace("\\\\;", "").replace("\\\\:", "");
    }
    
    private static String replaceMult(String in)
    {
        return in.replace("\\\\times", "*").replace("\\\\ast", "*");
    }
    
    private static String replaceDiv(String in)
    {
        return in.replace("\\\\div", "/");
    }
    
    private static String replaceLaTexParenthesis(String in)
    {
        return in.replace("\\\\left(", "(").replace("\\\\right)", ")");
    }
    
    private static String replaceBracketsWithParenthesis(String in)
    {
        return in.replace("\\\\left[", "(").replace("\\\\right]", ")").replace("\\\\lbrack", "(").replace("\\\\rbrack", ")");
    }
    
    private static String replaceCurlyBracesWithParenthesis(String in)
    {
        return in.replace("\\\\left\\\\{", "(").replace("\\\\right\\\\}", ")");
    }
    
    private static String replaceBarsWithAbsoluteValue(String in)
    {
        return in.replace("\\\\left|", "abs(").replace("\\\\right|", ")");
    }
    
    private static String replaceLnWithLog(String in)
    {
        return in.replace("\\\\ln", "log").replace("\\\\log", "log");
    }
    
    private static String replaceFracWithDivision(String in)
    {
        StringBuilder str = new StringBuilder(in);
        int index = str.indexOf("\\\\frac");
        
        while(index != -1)
        {
            //str = new StringBuilder(in);
            str.replace(index, index + 6, "");
            
            //int tempIndex = index;
            
            for(int i = index; i < str.length(); i++)   //find next char, if it is not {, then enclose next char in {}
            {
                if(!InfixToPostfix.isSpace(str.charAt(i)) && str.charAt(i) != '{')  //not space or {
                {
                    str.insert(i, '{');         //enclose next char in {}
                    str.insert(i + 2, '}');
                    index = i;
                    break;
                }
                else if(str.charAt(i) == '{')
                    index = i;
                    break;
            }
            
             
            //index = 0;
            
            str.setCharAt(index, '(');
            for(int i = index+1, braceCnt = 0; i < str.length(); i++)
            {
                if(str.charAt(i) == '{')
                    braceCnt++;
                if(str.charAt(i) == '}')
                {
                    if(braceCnt == 0)
                    {
                        str.setCharAt(i, ')');
                        index = i + 1;
                        break;
                    }
                    else
                    {
                        braceCnt--;
                    }
                }
            }
            
            for(int i = index; i < str.length(); i++)   //find next char, if it is not {, then enclose next char in {}
            {
                if(!InfixToPostfix.isSpace(str.charAt(i)) && str.charAt(i) != '{')  //not space or {
                {
                    str.insert(i, '{');         //enclose next char in {}
                    str.insert(i + 2, '}');
                    index = i;
                    break;
                }
                else if(str.charAt(i) == '{')
                    index = i;
                    break;
            }
            
            for(int i = index; i < str.length(); i++)
            {
                if(str.charAt(i) == '{')
                {
                    str.insert(i, " / ");
                    str.setCharAt(i + 3, '(');
                    index = i + 1;
                    break;
                }
            }
            for(int i = index, braceCnt = 0; i < str.length(); i++)
            {
                if(str.charAt(i) == '{')
                    braceCnt++;
                if(str.charAt(i) == '}')
                {
                    if(braceCnt == 0)
                    {
                        str.setCharAt(i, ')');
                        break;
                    }
                    else
                    {
                        braceCnt--;
                    }
                }
            }
            index = str.indexOf("\\\\frac");
        }
        return str.toString();
    }
    
    public static String EnsureFracIsEnclosedInBraces(String in)
    {
        StringBuilder str = new StringBuilder(in);
        int index = str.indexOf("\\frac"); 
        
        while(index != -1)
        {
            int startIndex = index;
            for(int i = index + 5; i < str.length(); i++)   //find next char, if it is not {, then enclose next char in {}
            {
                if(!InfixToPostfix.isSpace(str.charAt(i)) && str.charAt(i) != '{')  //not space or {
                {
                    str.insert(i, "{ ");         //enclose next char in {}
                    str.insert(i + 3, " }");
                    index = i;
                    break;
                }
                else if(str.charAt(i) == '{')
                    index = i;
                    break;
            }
            
            for(int i = index+1, braceCnt = 0; i < str.length(); i++)
            {
                if(str.charAt(i) == '{')
                    braceCnt++;
                if(str.charAt(i) == '}')
                {
                    if(braceCnt == 0)
                    {
                        index = i + 1;
                        break;
                    }
                    else
                    {
                        braceCnt--;
                    }
                }
            }
            
            for(int i = index; i < str.length(); i++)   //find next char, if it is not {, then enclose next char in {}
            {
                if(!InfixToPostfix.isSpace(str.charAt(i)) && str.charAt(i) != '{')  //not space or {
                {
                    str.insert(i, "{ ");         //enclose next char in {}
                    str.insert(i + 3, " }");
                    break;
                }
                else if(str.charAt(i) == '{')
                    break;
            }
            
            index = str.indexOf("\\frac", startIndex + 6);
        }
        
        return str.toString();
    }
    
    public static String EnsureExponentIsEnclosedInBraces(String in)
    {
        StringBuilder str = new StringBuilder(in);
        int index = str.indexOf("^");
        
        while(index != -1)
        {
            
            for(int i = index + 1; i < str.length(); i++)   //find next char, if it is not {, then enclose next char in {}
            {
                if(!InfixToPostfix.isSpace(str.charAt(i)) && str.charAt(i) != '{')  //not space or {
                {
                    str.insert(i, "{ ");         //enclose next char in {}
                    str.insert(i + 3, " }");
                    break;
                }
                else if(str.charAt(i) == '{')
                {
                    str.replace(i, i + 1, "{ ");
                    for(int j = i + 2, braceCnt = 0; j < str.length(); j++)
                    {
                        if(str.charAt(j) == '{')
                            braceCnt++;
                        if(str.charAt(j) == '}')
                        {
                            if(braceCnt == 0)
                            {
                                str.replace(j, j + 1, " }");
                                break;
                            }
                            else
                            {
                                braceCnt--;
                            }
                        }
                    }
                }
                break;
            }
            index = str.indexOf("^", index + 1);
        }
        return str.toString();
    }
    
    private static String replaceExponentWithPow(String in)
    {
        StringBuilder str = new StringBuilder(in);
        int index = str.indexOf("^");
        int indexAfterExponent = 0;
        
        while(index != -1)
        {
            
            for(int i = index + 1; i < str.length(); i++)   //find next char, if it is not {, then enclose next char in {}
            {
                if(!InfixToPostfix.isSpace(str.charAt(i)) && str.charAt(i) != '{')  //not space or {
                {
                    str.insert(i, '{');         //enclose next char in {}
                    str.insert(i + 2, '}');
                    break;
                }
                else if(str.charAt(i) == '{')
                    break;
            }
            
            if(str.charAt(index + 1) == '{')
            {
                indexAfterExponent = index + 1;
                str.setCharAt(index + 1, '(');
                for(int i = index+1, braceCnt = 0; i < str.length(); i++)
                {
                    if(str.charAt(i) == '{')
                        braceCnt++;
                    if(str.charAt(i) == '}')
                    {
                        if(braceCnt == 0)
                        {
                            str.setCharAt(i, ')');
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
        }
        
        return str.toString();
    }
    
    private static String replaceSqrtWithPow(String in)
    {
        StringBuilder str = new StringBuilder(in);
        int index = str.indexOf("\\\\sqrt");
        
        while(index != -1)
        {
            str.replace(index, index + 6, "");
            String rootNumber = "";
            int startIndex = index;
            
            for(int i = index; i < str.length(); i++)   //find next char, if it is not {, then enclose next char in {}
            {
                if(!InfixToPostfix.isSpace(str.charAt(i)) && str.charAt(i) != '[')  //not space or {
                {
                    str.insert(i, "[2]");         //enclose next char in []
                    break;
                }
                else if(str.charAt(i) == '[')
                    break;
            }
            
            
            for(int i = index + 1, braceCnt = 0; i < str.length(); i++)
            {
                if(str.charAt(i) == '[')
                    braceCnt++;
                else if(str.charAt(i) == ']')
                {
                    if(braceCnt == 0)
                    {
                        index = i + 1;
                        break;
                        
                    }
                    else
                        braceCnt--;
                }
                else
                {
                    rootNumber += str.charAt(i);
                }
            }
            str.replace(startIndex, index, "");
            index = index - (index - startIndex);
            
            for(int i = index; i < str.length(); i++)   //find next char, if it is not {, then enclose next char in {}
            {
                if(!InfixToPostfix.isSpace(str.charAt(i)) && str.charAt(i) != '{')  //not space or {
                {
                    str.insert(i, '{');         //enclose next char in {}
                    str.insert(i + 2, '}');
                    break;
                }
                else if(str.charAt(i) == '{')
                    break;
            }
            
            for(int i = index; i < str.length(); i++)
            {
                if(str.charAt(i) == '{')
                {
                    str.setCharAt(i, '(');
                    index = i + 1;
                    break;
                }
            }
            
            for(int i = index, braceCnt = 0; i < str.length(); i++)
            {
                if(str.charAt(i) == '{')
                    braceCnt++;
                if(str.charAt(i) == '}')
                {
                    if(braceCnt == 0)
                    {
                        str.setCharAt(i, ')');
                        index = i + 1;
                        str.insert(index, "^((1) / (" + rootNumber + "))");
                        break;
                    }
                    else
                    {
                        braceCnt--;
                    }
                }
            }
            index = str.indexOf("\\\\sqrt");
        }
        return str.toString();
    }
    
    private static String replaceParenthesisWithMult(String in)
    {
        StringBuilder str = new StringBuilder(in);
        
        int index = str.indexOf(")", 0);
        
        while(index != -1)
        {
            for(int i = index + 1; i < str.length(); i++)
            {
                if(InfixToPostfix.isSpace(str.charAt(i)))
                    continue;
                else if(!InfixToPostfix.isOperator(str.charAt(i)))
                {
                    index = i;
                    break;
                }
                else if(InfixToPostfix.isOperator(str.charAt(i)) && str.charAt(i) != '(')
                {
                    //index = i;
                    break;
                }
                else if(str.charAt(i) == '(')
                {
                    str.insert(i, " * ");
                    index = i;
                    break;
                }
            }
            index += 1;
            index = str.indexOf(")", index);
        }
        
        index = str.indexOf("(", 0);
        
        while(index != -1)
        {
            String operand = "";
            for(int i = index - 1; i >= 0; i--)
            {
                if(str.charAt(i) == ' ')
                    continue;
                else if(InfixToPostfix.isOperator(str.charAt(i)))
                {
                    break;
                }
                else
                {
                    while(i >= 0 && str.charAt(i) != ' ' && !InfixToPostfix.isOperator(str.charAt(i)))
                    {
                        operand = str.charAt(i) + operand;
                        i--;
                    }
                    if(InfixToPostfix.isFunctionOperator(operand))
                    {
                        break;
                    }
                    else
                    {
                        str.insert(index, " * ");
                        break;
                    }
                }
            }
            index = str.indexOf("(", index + 1);
        }
        
        return str.toString();
    }
    
    private static String replaceNegatives(String in)
    {
        StringBuilder output = new StringBuilder(in);
        final String regex = "((?<=[+\\-*\\/\\(])|^)\\s*-\\s*((\\d+(\\.\\d+)?)|[\\(])";
        
        Pattern pattern = Pattern.compile(regex);
        Matcher matcher = pattern.matcher(output);
        while(matcher.find())
        {
            String match = matcher.group();
            int index = match.indexOf("-");
            boolean paren = false;
            
            for(int i = index+1; i < match.length(); i++)
            {
                if(match.charAt(i) == '(')
                {
                    paren = true;
                    index = i;
                    break;
                }
                else if(!InfixToPostfix.isSpace(match.charAt(i)))
                {
                    index = i;
                    break;
                }
            }
            
            if(paren)
            {
                index = index + matcher.start();
                int startIndex = index;
                int endIndex = output.length() - 1;
                for(int i = index + 1, braceCnt = 0; i < output.length(); i++)
                {
                    if(output.charAt(i) == '(')
                        braceCnt++;
                    else if(output.charAt(index) == ')')
                    {
                        if(braceCnt == 0)
                        {
                            endIndex = i;
                        }
                        else
                        {
                            braceCnt--;
                        }
                    }
                }
                String sub = output.substring(startIndex, endIndex);
                String temp = "((0) - " + sub + ")";
                output.replace(matcher.start(), endIndex, temp);
            }
            else
            {
                String sub = match.substring(index);
                String temp = "((0) - (" + sub + "))";
                output.replace(matcher.start(), matcher.end(), temp);
            }
            matcher = pattern.matcher(output);
            
        }
        return output.toString();
 
    }
    
    private static String removeDisplayStyle(String in)
    {
        StringBuilder str = new StringBuilder(in);
        int index = str.indexOf("\\\\displaystyle");
        
        while(index != -1)
        {
            str = str.replace(index, index + 14, "");
            index = str.indexOf("\\\\displaystyle");
        }
        return str.toString();
    }
}
