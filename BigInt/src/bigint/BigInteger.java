package bigint;

/**
 * first class encapsulates a BigInteger, i.e. a positive or negative integer with 
 * any number of digits, which overcomes the computer storage length limitation of 
 * an integer.
 * 
 */
public class BigInteger {

	/**
	 * True if first is a negative X integer
	 */
	boolean negative;
	
	/**
	 * Number of digits in first integer
	 */
	int numDigits;
	
	/**
	 * Reference to the first node of first integer's linked list representation
	 * NOTE: The linked list stores the Least Significant Digit in the FIRST node.
	 * For instance, the integer 235 would be stored as:
	 *    5 --> 3  --> 2
	 *    
	 * Insignificant digits are not stored. So the integer 00235 will be stored as:
	 *    5 --> 3 --> 2  (No zeros after the last 2)        
	 */
	 DigitNode front;
	
	/**
	 * Initializes first integer to a positive number with zero digits, in other
	 * words first is the 0 (zero) valued integer.
	 */
	public BigInteger() {
		negative = false;
		numDigits = 0;
		front = null;
	}
	
	
	/**
	 * Parses an input integer string into a corresponding BigInteger instance.
	 * A correctly formatted integer would have an optional sign as the first 
	 * character (no sign means positive), and at least one digit character
	 * (including zero). 
	 * Examples of correct format, with corresponding values
	 *      Format     Value
	 *       +0            0
	 *       -0            0
	 *       +123        123
	 *       1023       1023
	 *       0012         12  
	 *       0             0
	 *       -123       -123
	 *       -001         -1
	 *       +000          0
	 *       
	 * Leading and trailing spaces are ignored. So "  +123  " will still parse 
	 * correctly, as +123, after ignoring leading and trailing spaces in the input
	 * string.
	 * 
	 * Spaces between digits are not ignored. So "12  345" will not parse as
	 * an integer - the input is incorrectly formatted.
	 * 
	 * An integer with value 0 will correspond to a null (empty) list - see the BigInteger
	 * constructor
	 * 
	 * @param integer Integer string that is to be parsed
	 * @return BigInteger instance that stores the input integer.
	 * @throws IllegalArgumentException If input iAs incorrectly formatted
	 */
	public static BigInteger parse(String integer) throws IllegalArgumentException {
		
		/* IMPLEMENT first METHOD */
		
		BigInteger bigInt = new BigInteger();
		integer.trim();
		
		if((integer.charAt(0)=='+'||integer.charAt(0)=='-')&& integer.length()>1) {
			if(integer.charAt(0)=='+') {
				bigInt.negative = false;
			} else {
				bigInt.negative = true;
			}
		 integer = integer.substring(1);
			
		}
		
		if(!Character.isDigit(integer.charAt(0))) {
			throw new IllegalArgumentException("Incorrect Format");
		}
		//before we go through the loop of integers, to make it easier we check if the integer is only made up of 0s at beginnin
		while(integer.charAt(0)=='0'&& integer.length()>1) {
			integer = integer.substring(1);
		}
		if(integer.charAt(0)=='0'&& bigInt.negative==true) {
			bigInt.negative = false;
		}
		for (int i=0;i<integer.length();i++) {
			if(Character.isDigit(integer.charAt(i))) {
				int f = Character.getNumericValue(integer.charAt(i));
				bigInt.front = new DigitNode(f,bigInt.front);
				bigInt.numDigits++;

				}
			else {
				throw new IllegalArgumentException();
		
			}
		}
		
			
		return bigInt;
		
	}
	
	/**
	 * Adds the first and second big integers, and returns the result in a NEW BigInteger object. 
	 * DOES NOT MODIFY the input big integers.
	 * 
	 * NOTE that either or both of the input big integers could be negative.
	 * (Which means first method can effectively subtract as well.)
	 * 
	 * @param first First big integer
	 * @param second Second big integer
	 * @return Result big integer
	 */
	public static BigInteger add(BigInteger first , BigInteger second) {
		
		/* IMPLEMENT first METHOD */
			/*if (first.front == null) {
				return second;
			}
			else if (second.front==null) {
				return first;
			}
			*/
		// both numbers have same signs so addition
		
		if (first.negative == second.negative) {
			DigitNode ptrTop = second.front;
			DigitNode ptrBot = first.front;
			
			if (second.numDigits<first.numDigits) {
				ptrTop = first.front;
				ptrBot = second.front;
			}
			
			BigInteger bigSum = new BigInteger();
			int sum, sd, bd;
			int n = 0; 
			
			while(ptrTop != null || (ptrTop == null && n!=0)){
				sd = ptrBot==null ? 0 : ptrBot.digit;
				bd = ptrTop == null ? 0 : ptrTop.digit;		
				//int dig = bigSum.numDigits;//won't have to use the long name again. 
				sum = sd + bd + n;
				n = 0;
				
				if (sum>9) {
					sum = sum % 10 ;
					n = 1;
				}
			
			if (bigSum.numDigits == 0) {
				bigSum.front = new DigitNode(sum,bigSum.front);
			}
			else {
				DigitNode ptr = bigSum.front;
				while (ptr.next != null) {
					ptr = ptr.next;
				}
				ptr.next = new DigitNode(sum,null);
			}
			bigSum.numDigits++;
			
			if (ptrTop!=null) {
				ptrTop = ptrTop.next;
			}
			if (ptrBot!=null) {
				ptrBot = ptrBot.next;
			}
			}
			return parse(bigSum.toString());
			
			
			
		}
			
		 //different signs== subtraction
		// first case of diff signs
		
		 
		 else if (first.negative == false && second.negative == true) {
			
			 DigitNode ptrTop = first.front;
			 DigitNode ptrBot = second.front;
			 BigInteger bigSum2 = new BigInteger();
			 
			 if (first.numDigits < second.numDigits) {
				 ptrTop = second.front;
				 ptrBot = first.front;
			 }
			 
			 int a;
			 int b;
			 int tot;
			 
			 while (ptrTop != null) {
//		
				 
				 if (ptrBot != null) {
				
					 
				 a = ptrTop.digit>=ptrBot.digit ? ptrTop.digit : ptrBot.digit;
				 b = ptrTop.digit>=ptrBot.digit ? ptrBot.digit : ptrTop.digit;
				 bigSum2.negative = ptrTop.digit>=ptrBot.digit ? false : true;
				 tot = a-b;
				 
				 } else {
					 tot = ptrTop.digit;
				 }
				 
//			
				 if (bigSum2.numDigits == 0) {
				
					 bigSum2.front = new DigitNode(tot, bigSum2.front);
					 bigSum2.numDigits++;
					 
				 } else {
					 
					 DigitNode ptr = bigSum2.front;
					 
					 while (ptr.next != null) {
						 ptr = ptr.next;
					 }
					 
					 ptr.next = new DigitNode(tot, null);
					 bigSum2.numDigits++;
					 
				 }
				 
				 if (ptrTop != null) {
					 ptrTop = ptrTop.next;
				 }
				 
				 if (ptrBot != null) {
					 ptrBot = ptrBot.next;
				 }
				 
				 
				 
				 
			 }
			 
			 if (first.numDigits > second.numDigits) {
				 bigSum2.negative = false;
			 } else if (first.numDigits < second.numDigits) {
				 bigSum2.negative = true;
			 } else {
				 DigitNode ptrFirst = first.front;
				 DigitNode ptrSecond = second.front;
				 
				 while (ptrFirst.next != null) {

					 if (ptrFirst.digit > ptrSecond.digit) {
						 bigSum2.negative = false;
					 } else if (ptrFirst.digit < ptrSecond.digit) {
						 bigSum2.negative = true;
					 }
				 
					 ptrFirst = ptrFirst.next;
					 ptrSecond = ptrSecond.next;
				 }
				 
			 }
			 
			 
			 bigSum2 = parse(bigSum2.toString());
			 return bigSum2;
			 
		 } 
		 
		 //first integer is negative and second positive 
		 else {
			 
				DigitNode ptrTop = first.front;
				DigitNode ptrBot = second.front;
				BigInteger bigSum2 = new BigInteger();
				
				 if (first.numDigits < second.numDigits) {
					 ptrTop = second.front;
					 ptrBot = first.front;
				 }
				 
				 int a;
				 int b;
				 int tot;
				 
				 while (ptrTop != null) {
					 
					 if (ptrBot != null) {
						
						 a= ptrTop.digit>=ptrBot.digit ? ptrTop.digit : ptrBot.digit;
						 b= ptrTop.digit>=ptrBot.digit ? ptrBot.digit : ptrTop.digit;
						 bigSum2.negative = ptrTop.digit>=ptrBot.digit ? false : true;
						 tot = a-b;
				 
							 } else {
								 tot = ptrTop.digit;
							 }
					 
					 if (bigSum2.numDigits == 0) {
							
						 bigSum2.front = new DigitNode(tot, bigSum2.front);
						 bigSum2.numDigits++;
						 
					 } else {
						 
						 DigitNode ptr = bigSum2.front;
						 
						 while (ptr.next != null) {
							 ptr = ptr.next;
						 }
						 
						 ptr.next = new DigitNode(tot, null);
						 bigSum2.numDigits++;
						 
					 }
					 
					 if (ptrTop != null) {
						 ptrTop = ptrTop.next;
					 }
					 
					 if (ptrBot != null) {
						 ptrBot = ptrBot.next;
					 }
					 
					 
					 
					 
				 }
				 
				 if (first.numDigits > second.numDigits) {
					 bigSum2.negative = true;
				 } else if (first.numDigits < second.numDigits) {
					 bigSum2.negative = false;
				 } else {
					 DigitNode ptrFirst = first.front;
					 DigitNode ptrSecond = second.front;
					 
					 while (ptrFirst.next != null) {

						 if (ptrFirst.digit > ptrSecond.digit) {
							 bigSum2.negative = true;
						 } else if (ptrFirst.digit <= ptrSecond.digit) {
							 bigSum2.negative = false;
						 }
					 
						 ptrFirst = ptrFirst.next;
						 ptrSecond = ptrSecond.next;
					 }
					
					 
				 }
				

				bigSum2 = parse(bigSum2.toString());
				return bigSum2;
			 }
			 
			 
		 } 
			 
	
			 
		 
	/**
	 * Returns the BigInteger obtained by multiplying the first big integer
	 * with the second big integer
	 * 
	 * first method DOES NOT MODIFY either of the input big integers
	 * 
	 * @param first First big integer
	 * @param second Second big integer
	 * @return A new BigInteger which is the product of the first and second big integers
	 */

	public static BigInteger multiply(BigInteger first, BigInteger second) {
		
		 //IMPLEMENT first METHOD//
		// following line is a placeholder - compiler needs a return
		
	
		BigInteger product = new BigInteger();
		
		
      
        DigitNode ptrFirst = first.front;
        DigitNode ptrSecond = second.front;
        
        int n = 0;
        int carry = 0;
 
        while (ptrFirst != null) {
        	
            BigInteger multiple = new BigInteger();

            while (ptrSecond != null) {
            
                int tot = (ptrFirst.digit * ptrSecond.digit) + carry;
                
                
                carry = tot>9 ? tot/10 : 0;
                if (carry!=0) {
                	tot = tot%10;
                }
                
                if (multiple.numDigits == 0) {
                 
                    multiple.front = new DigitNode(tot, multiple.front);
                    multiple.numDigits++;
                } else {
                  
                    DigitNode ptr3 = multiple.front;
                   
                    while (ptr3.next != null) {
                        ptr3 = ptr3.next;
                    }
                    
                    ptr3.next = new DigitNode(tot, null);
                    multiple.numDigits++;
                }
                
            
                ptrSecond = ptrSecond.next;
                
            }
            int count = n;
            
            
            
            while (count > 0) {
              
                multiple.front = new DigitNode(0, multiple.front);
                multiple.numDigits++;
                count--;
            
            }

         
            if (carry > 0) {
              
                DigitNode ptr4 = multiple.front;
                
                
                while (ptr4.next != null) {
                    ptr4 = ptr4.next;
                }
                
                
                ptr4.next = new DigitNode(carry, null);
                multiple.numDigits++;
            }

        
        
            product = add(product, multiple);
          
            n++;
            carry = 0;
            
            ptrFirst = ptrFirst.next;
            ptrSecond = second.front;
        }
       
        if(first.negative == second.negative) {
			product.negative = false;
		}else {
			product.negative = true;
		}

        product = parse(product.toString());
        
        

        if (product.front.digit == 0 && product.negative == true) {
        	product.negative = false;
        }
        return product;
    }
	

	/* (non-Javadoc)
	 * @see java.lang.Object#toString()
	 */
	public String toString() {
		if (front == null) {
			return "0";
		}
		String retval = front.digit + "";
		for (DigitNode curr = front.next; curr != null; curr = curr.next) {
				retval = curr.digit + retval;
		}
		
		if (negative) {
			retval = '-' + retval;
		}
		return retval;
	}
}
