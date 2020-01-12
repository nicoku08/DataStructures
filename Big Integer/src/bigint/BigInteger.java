package bigint;

/**
 * This class encapsulates a BigInteger, i.e. a positive or negative integer with 
 * any number of digits, which overcomes the computer storage length limitation of 
 * an integer.
 * 
 */
public class BigInteger {

	/**
	 * True if this is a negative X integer
	 */
	boolean negative;
	
	/**
	 * Number of digits in this integer
	 */
	int numDigits;
	
	/**
	 * Reference to the first node of this integer's linked list representation
	 * NOTE: The linked list stores the Least Significant Digit in the FIRST node.
	 * For instance, the integer 235 would be stored as:
	 *    5 --> 3  --> 2
	 *    
	 * Insignificant digits are not stored. So the integer 00235 will be stored as:
	 *    5 --> 3 --> 2  (No zeros after the last 2)        
	 */
	DigitNode front;
	
	/**
	 * Initializes this integer to a positive number with zero digits, in other
	 * words this is the 0 (zero) valued integer.
	 */
	public BigInteger() {
		negative = false;
		numDigits = 0;
		front = null;
	}
	
	private void addToFront(int num) {
		front = new DigitNode(num,front);
		numDigits++;
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
		
		/* IMPLEMENT THIS METHOD */
		
		BigInteger LL = new BigInteger();
		integer.trim();
		//if(LL.front == null) {
			//throw new IllegalArgumentException("Input is null");
			//LL.addToFront(8);
		//}
		if(integer.charAt(0)=='+'||integer.charAt(0)=='-') {
			if(integer.charAt(0)=='+') {
				LL.negative = false;
			} else {
				LL.negative = true;
			}
		 integer = integer.substring(1);
			
		}
		
		else if(!Character.isDigit(integer.charAt(0))) {
			throw new IllegalArgumentException("Incorrect Format");
		}
		//before we go through the loop of integers, to make it easier we check if the integer is only a 0
		if(integer.charAt(0)=='0'&& integer.length()>1) {
			integer = integer.substring(1);
		}
		for (int i=0;i<integer.length();i++) {
			if(Character.isDigit(integer.charAt(i))) {
				//if(integer.charAt(i)=='0') {
				//	integer = integer.substring(i);
				char h = integer.charAt(i);
				int n = Character.getNumericValue(h);
				int f = Integer.parseInt(String.valueOf(h)+ "");
				LL.addToFront(f);
				LL.numDigits++;

				}
			else {
				throw new IllegalArgumentException();
		
			}
		}
		
		//LL.addToFront(1);
		
		//System.out.println("We have added 1");
		//System.out.println(LL.front.digit);
			
		return LL;
		
	}
	
	/**
	 * Adds the first and second big integers, and returns the result in a NEW BigInteger object. 
	 * DOES NOT MODIFY the input big integers.
	 * 
	 * NOTE that either or both of the input big integers could be negative.
	 * (Which means this method can effectively subtract as well.)
	 * 
	 * @param first First big integer
	 * @param second Second big integer
	 * @return Result big integer
	 */
	//public static BigInteger add(BigInteger first, BigInteger second) {
		
		/* IMPLEMENT THIS METHOD */
		
		// following line is a placeholder for compilation
		//return null;
	//}
	
	/**
	 * Returns the BigInteger obtained by multiplying the first big integer
	 * with the second big integer
	 * 
	 * This method DOES NOT MODIFY either of the input big integers
	 * 
	 * @param first First big integer
	 * @param second Second big integer
	 * @return A new BigInteger which is the product of the first and second big integers
	 */
	/** public static BigInteger multiply(BigInteger first, BigInteger second) {
		
		 IMPLEMENT THIS METHOD 
		BigInteger multLL = new BigInteger();
		multLL.addToFront(0);
		BigInteger tempor = new BigInteger();
		BigInteger temp = this;
		BigInteger temp2 = other;
		int c = 0;
		if (this.numDigits < other.numDigits) {
			temp = other;
			temp2 = this;
		}
		int numDig = temp.numDigits;
		int numDig2 = temp2.numDigits;
		DigitNode ptr = temp.front;
		if (this.negative == false && other.negative == false) {
			multLL.negative = false;
		}
		else if (this.negative == true && other.negative == true) {
			multLL.negative = false;
		}
		else {
			multLL.negative = true;
			tempor.negative = true;
		}
		for (int m = 0; m < numDig2; m++) {
			for (int y = 0; y < numDig; y++) {
				tempor.addToBack((ptr.digit * temp2.front.digit + c) % 10);
				c = (ptr.digit * temp2.front.digit + c) / 10;
				ptr = ptr.next;
			}
			if (c != 0) {
				tempor.addToBack(c);
			}
			ptr = temp.front;
			temp2.deleteFromFront();
			multLL = multLL.add(tempor);
			tempor = new BigInteger();
			if ((this.negative == false && other.negative == true) || (this.negative == true && other.negative == false)) {
				tempor.negative = true;
			}
			for (int r = 0; r < m + 1; r++) {
				tempor.addToBack(0);
			}
			c = 0;
		}
		return multLL;
	}
	*/

		// following line is a placeholder for compilation
	
	
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
