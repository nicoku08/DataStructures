package app;

import java.io.*;
import java.util.*;
import java.util.regex.*;

import structures.Stack;

public class Expression {

	public static String delims = " \t*+-/()[]";
			
    /**
     * Populates the vars list with simple variables, and arrays lists with arrays
     * in the expression. For every variable (simple or array), a SINGLE instance is created 
     * and stored, even if it appears more than once in the expression.
     * At this time, values for all variables and all array items are set to
     * zero - they will be loaded from a file in the loadVariableValues method.
     * 
     * @param expr The expression
     * @param vars The variables array list - already created by the caller
     * @param arrays The arrays array list - already created by the caller
     */
    public static void 
    makeVariableLists(String expr, ArrayList<Variable> vars, ArrayList<Array> arrays) {
    	/** COMPLETE THIS METHOD **/
    	/** DO NOT create new vars and arrays - they are already created before being sent in
    	 ** to this method - you just need to fill them in.
    	 **/
    	
    	String hold="";
    	String spaci = "\\t*+-/()]";
    	int i = 0;
    	while(i<expr.length()) {
    		if(i == expr.length()-1 && Character.toString(expr.charAt(i))=="]") {
    			i++;
    			break;
    		}
			if(!Character.isDigit(expr.charAt(i))  &&!delims.contains(Character.toString(expr.charAt(i)))) { //if char is variable
				
				hold+= expr.charAt(i);
			//	System.out.println("hold multiple answer" + hold);
				if(i == expr.length()-1) {   //if char is equal to last char hold the value and add
					
					Variable temp = new Variable(hold);
					if(hold !="" && vars.contains(temp) != true) {
						vars.add(temp);
					}
				}
				
				i++;
				//System.out.println("first i is " +i);
			}else if (spaci.contains(Character.toString(expr.charAt(i)))){ // if the char is delim without [
				//System.out.println("second i is " +i);
				Variable temp = new Variable(hold);
			//	System.out.println("hold multiple tempr" + temp);
				if(hold !=""&& vars.contains(temp) != true) {
					vars.add(temp);
				}
				hold="";
				i++;
			}else {  // if the char is [
				
				Array temp = new Array (hold);
				//System.out.println("array temp is " + temp);
				if(hold !=""&& arrays.contains(temp) != true) {
					arrays.add(temp);
				}
				hold="";
				i++;
			}
    	}
    //System.out.println(vars);
    //	System.out.println(arrays);
    }
    
    /**
     * Loads values for variables and arrays in the expression
     * 
     * @param sc Scanner for values input
     * @throws IOException If there is a problem with the input 
     * @param vars The variables array list, previously populated by makeVariableLists
     * @param arrays The arrays array list - previously populated by makeVariableLists
     */
    public static void 
    loadVariableValues(Scanner sc, ArrayList<Variable> vars, ArrayList<Array> arrays) 
    throws IOException {
        while (sc.hasNextLine()) {
            StringTokenizer st = new StringTokenizer(sc.nextLine().trim());
            int numTokens = st.countTokens();
            String tok = st.nextToken();
            Variable var = new Variable(tok);
            Array arr = new Array(tok);
            int vari = vars.indexOf(var);
            int arri = arrays.indexOf(arr);
            if (vari == -1 && arri == -1) {
            	continue;
            }
            int num = Integer.parseInt(st.nextToken());
            if (numTokens == 2) { // scalar symbol
                vars.get(vari).value = num;
            } else { // array symbol
            	arr = arrays.get(arri);
            	arr.values = new int[num];
                // following are (index,val) pairs
                while (st.hasMoreTokens()) {
                    tok = st.nextToken();
                    StringTokenizer stt = new StringTokenizer(tok," (,)");
                    int index = Integer.parseInt(stt.nextToken());
                    int val = Integer.parseInt(stt.nextToken());
                    arr.values[index] = val;              
                }
            }
        }
       
        
    }
    
    /**
     * Evaluates the expression.
     * 
     * @param vars The variables array list, with values for all variables in the expression
     * @param arrays The arrays array list, with values for all array items
     * @return Result of evaluation
     */
    public static int 
    evaluate(String expr, ArrayList<Variable> vars, ArrayList<Array> arrays) {
    	
    	/** COMPLETE THIS METHOD **/
    	// following line just a placeholder for compilation
    	expr = expr.replaceAll("\\s+", "");
    	char[] sep = expr.toCharArray();
    	
    	Stack<Integer> varib= new Stack<>();
    	Stack<Character> signs = new Stack<>();
    	Stack<String> stackArr = new Stack<>();
    	
    	for (int i = 0; i < sep.length; i++) 
    	{
    		
    		if (sep[i] >= '0' && sep[i] <= '9') 
    		{
    			String togeth = "";
    			int j = i;
    			
    			while (j < sep.length && sep[j] >= '0' && sep[j] <= '9') 
    			{
    				togeth += sep[j];
    				j++;
    			}
    			i = j - 1;
    			varib.push((int)Integer.parseInt(togeth));
    		}
    		
    		
    		else if ((sep[i] >= 'a' && sep[i] <= 'z') || (sep[i] >= 'A' && sep[i] <= 'Z')) 
    		{
    			String varName = "";
    			int c = i;
    			
    			while (c < sep.length && ((sep[c] >= 'a' && sep[c] <= 'z') || (sep[c] >= 'A' && sep[c] <= 'Z'))) 
    			{
    				varName += sep[c];
    				c++;
    			}
    			i = c - 1;
    			
    			
    			if (i == sep.length - 1) 
    			{
    				for (int k = 0; k < vars.size(); k++) 
        			{
        				if (varName.equals(vars.get(k).name)) 
    					{
        					varib.push((int)vars.get(k).value);
        					break;
        				}
        			}
    			}else if (i <= sep.length - 1 && sep[i + 1] == '[') {
    				int d = 0;
    				while ( d < arrays.size()) {
    					if (varName.equals(arrays.get(d).name)) 
    					{
    						stackArr.push(arrays.get(d).name);
    						
    					}
    					d++;
    				}
    				
    			}else if (i <= sep.length - 1 && sep[i + 1] != '['){
    				int g =0 ;
    				while(g<vars.size()) {
    					if (varName.equals(vars.get(g).name)){
        					varib.push((int)vars.get(g).value);
        					break;
        				}
    					g++;
    				}
    				
    			}
    		
    			
    			
    		}else if (sep[i] == '(') {
    			signs.push(sep[i]);
    		}else if (sep[i] == ')') {
    			while (signs.peek() != '(') 
    			{
    				varib.push(calculate(varib.pop(), varib.pop(),signs.pop()));
    			}
    			signs.pop();
    		}else if (sep[i] == ']') {
    			while (signs.peek() != '['){
    				varib.push(calculate(varib.pop(), varib.pop(),signs.pop()));
    			}
    			int temp = varib.pop();
				for (int k = 0; k < arrays.size(); k++) {
					if (stackArr.peek().equals(arrays.get(k).name)) 
					{
						varib.push((int)arrays.get(k).values[(int) temp]);
						stackArr.pop();
						break;
					}
				}
				signs.pop();
    		}else if (sep[i] == '[') {
    			signs.push(sep[i]);
    		}else if (sep[i] == '+' || sep[i] == '-' || sep[i] == '*' || sep[i] == '/') 
    		{
    			while (!signs.isEmpty() && checkDeli(sep[i], signs.peek())) 
    			{
    				varib.push(calculate(varib.pop(), varib.pop(),signs.pop()));
    			}
    			signs.push(sep[i]);
    		}
    	}while (!signs.isEmpty())
    	{
    		varib.push(calculate( varib.pop(), varib.pop(),signs.pop()));
    	}
    	
    	return varib.pop();
    }
    
    private static int calculate( int num2, int num1, char act)    {
    	int ans = 0;
    	switch(act) {
    	case '+':
    		ans = num1 + num2;
    		return ans;
    	case '-': 
    		ans = num1 - num2;
    		return ans;
    	case '*':
    		ans = num1 * num2;
    		return ans;
    	case '/':
    		if (num2 == 0) 
    		{
    			throw new NoSuchElementException("Can't divide by zero");
    		}
    		ans =  num1 / num2;
    		return ans;
    	}
    	return 0;
    }
    private static boolean checkDeli(char delNow, char delStack)  {
    	
    	
    	String delS = "[]()";
    	if (delS.contains(Character.toString(delStack))){
    		return false;
    	}
    
    	
    	if ((delNow == '*' || delNow == '/') && (delStack == '+' || delStack == '-')) {
    		return false;
    	}
    	else {
    		return true;
    	}
    }
}
    




    
