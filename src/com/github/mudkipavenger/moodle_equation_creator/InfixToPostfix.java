/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.github.mudkipavenger.moodle_equation_creator;

/**
 *
 * @author levi
 */
import java.util.*;

public class InfixToPostfix{


   public static boolean isOperator(char c) { // Tell whether c is an operator.

     return c == '+'  ||  c == '-'  ||  c == '*'  ||  c == '/'  ||  c == '^'
           || c=='(' || c==')';
   
   }//end isOperator

 

   private static boolean isSpace(char c) {  // Tell whether c is a space.

     return (c == ' ');
   
   }//end isSpace
   
   public static boolean isFunctionOperator(String op)
   {
       return Objects.equals(op, "ln") || Objects.equals(op, "log") || Objects.equals(op, "abs");
   }
   
   private static int getPrecedence(String op)
   {
       if(op.length() == 1)
       {
           switch(op.charAt(0))
           {
               case '+':
               case '-':
                   return 1;
               case '*':
               case '/':
                   return 2;
               case '^':
                   return 3;
               case '(':
               case ')':
                   return 5;
               default:
                   return 0;
           }
       }
       else if(isFunctionOperator(op))
       {
           return 4;
       }
       else
           return 0;   
   }


   private static boolean lowerPrecedence(String o1, String o2) {
      // Tell whether op1 has lower precedence than op2, where op1 is an
      // operator on the left and op2 is an operator on the right.
      // op1 and op2 are assumed to be operator characters (+,-,*,/,^).
      
      if(o1.length() == 1 && o1.charAt(0) == '(')
          return true;
      
      return (getPrecedence(o1) < getPrecedence(o2));
 
   } // end lowerPrecedence


// Method to convert infix to postfix:

   public static String convertToPostfix(String infix) {
          Stack operatorStack = new Stack();  
     char c;       
     StringTokenizer parser = new StringTokenizer(infix,"+-*/^() ",true);
     StringBuffer postfix = new StringBuffer(infix.length());
        while (parser.hasMoreTokens()) {     
           String token = parser.nextToken();
           c = token.charAt(0);         
           if(isFunctionOperator(token))
           {
               operatorStack.push(token);
           }
           else if ( (token.length() == 1) && isOperator(c)) 
           {    
              while (!operatorStack.empty() &&
                  !lowerPrecedence(((String)operatorStack.peek()), token))
              {
                postfix.append(" ").append((String)operatorStack.pop());
              }
              if (c==')') 
              {
                    String operator = (String)operatorStack.pop();
                    while (operator.charAt(0)!='(') 
                    {
                       postfix.append(" ").append(operator);
                       operator = (String)operatorStack.pop();  
                    }
              }
              else
                 operatorStack.push(token);
           }
           else if ( (token.length() == 1) && isSpace(c) ) {   
               }
           else { 
             postfix.append(" ").append(token);  
           }
         }
        while (!operatorStack.empty())
           postfix.append(" ").append((String)operatorStack.pop());
     
        return postfix.toString();
   }

}//end class InfixToPostfix
