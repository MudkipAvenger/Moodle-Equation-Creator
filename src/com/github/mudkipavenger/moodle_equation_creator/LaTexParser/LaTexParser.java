/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.github.mudkipavenger.moodle_equation_creator.LaTexParser;

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
        output = replaceLaTexParenthesis(output);
        output = LaTexParser.replaceBracketsWithParenthesis(output);
        output = LaTexParser.replaceCurlyBracesWithParenthesis(output);
        output = LaTexParser.replaceBarsWithAbsoluteValue(output);
        output = LaTexParser.replaceLnWithLog(output);
        output = LaTexParser.replaceFracWithDivision(output);
        output = LaTexParser.replaceExponentWithPow(output);
        output = LaTexParser.replaceSqrtWithPow(output);
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
    
    private static String replaceExponentWithPow(String in)
    {
        StringBuilder str = new StringBuilder(in);
        int index = str.indexOf("^");
        int indexAfterExponent = 0;
        
        while(index != -1)
        {
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
}
