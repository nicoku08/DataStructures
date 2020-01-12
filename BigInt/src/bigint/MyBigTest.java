
package bigint;

import java.io.IOException;
import java.util.Scanner;

public class MyBigTest {
	
	static Scanner sc;
	
	public static void parse() throws IOException {
		int min = -1000000, max = 1000000;
		int numCorrect = 0;
		BigInteger parsedBigInt;
		String integerString, parsedBigIntString;
		int signOfParsedBigInt, signOfIntegerString, lengthOfInteger;
		boolean isCorrect;
		String errorMessage = "";
		for (int i = min; i <= max; i++) {
			isCorrect = true;
			integerString = Integer.valueOf(i).toString();
			parsedBigInt = BigInteger.parse(integerString);
			parsedBigIntString = parsedBigInt.toString();
			if (!integerString.equals(parsedBigIntString)) {
				errorMessage += "\tparse(" + integerString + ") gave a wrong parse of " + parsedBigInt + "\n";
				isCorrect = false;
			}
			signOfParsedBigInt = parsedBigInt.front == null? 0:(parsedBigInt.negative? -1:1);
			signOfIntegerString = Integer.signum(i);
			if (signOfIntegerString != signOfParsedBigInt) {
				errorMessage += "\tparse(" + integerString + ") gave wrong sign " + signOfParsedBigInt + " instead of " + signOfIntegerString + "\n";
				isCorrect = false;
			}
			lengthOfInteger = i == 0? 0:(int) (Math.log10(Math.abs(i)) + 1);
			if (lengthOfInteger != parsedBigInt.numDigits) {
				errorMessage
				+= "\tparse(" + integerString + ") gave number of digits " + parsedBigInt.numDigits + " instead of " + lengthOfInteger + "\n";
				isCorrect = false;
			}
			
			if (isCorrect)
				numCorrect++;
			else {
				System.out.println(errorMessage);
				System.out.println("--------------------------------------------------------------------------------");
			}
			
		}
		System.out.println("\tend of parse method tests (numbers " + min + " to " + max + ")");
		int totalTests = max - min + 1;
		System.out.println("\tpassed " + numCorrect + "/" + totalTests + " (" + ((numCorrect / ((double) totalTests)) * 100) + "%)");
	}
	
	public static void add() throws IOException {
		int min = -100, max = 100;
		int numCorrect = 0;
		String firstIntegerString, secondIntegerString, integerSumString;
		BigInteger firstBigInteger, secondBigInteger, bigIntSum;
		int signOfBigIntSum, signOfIntegerString, lengthOfInteger;
		boolean isCorrect;
		String errorMessage = "";
		for (int i = min; i <= max; i++) {
			firstIntegerString = Integer.valueOf(i).toString();
			firstBigInteger = BigInteger.parse(firstIntegerString);
			for (int j = min; j <= max; j++) {
				isCorrect = true;
				secondIntegerString = Integer.valueOf(j).toString();
				secondBigInteger = BigInteger.parse(secondIntegerString);
				
				integerSumString = Integer.valueOf(i + j).toString();
				bigIntSum = BigInteger.add(firstBigInteger, secondBigInteger);
				
				if (!integerSumString.equals(bigIntSum.toString())) {
					errorMessage += "\t\t- gave " + bigIntSum + " instead of " + integerSumString + "\n";
					isCorrect = false;
				}
				
				signOfBigIntSum = bigIntSum.front == null? 0:(bigIntSum.negative? -1:1);
				signOfIntegerString = Integer.signum(i + j);
				if (signOfIntegerString != signOfBigIntSum) {
					errorMessage += "\t\t- gave sign " + signOfBigIntSum + " instead of " + signOfIntegerString + "\n";
					isCorrect = false;
				}
				lengthOfInteger = i + j == 0? 0:(int) (Math.log10(Math.abs(i + j)) + 1);
				if (lengthOfInteger != bigIntSum.numDigits) {
					errorMessage += "\t\t- gave number of digits " + bigIntSum.numDigits + " instead of " + lengthOfInteger + "\n";
					isCorrect = false;
				}
				
				if (isCorrect)
					numCorrect++;
				else {
					System.out.println("\tadd(" + i + ",  " + j + ")\n" + errorMessage);
					System.out.println("--------------------------------------------------------------------------------------------");
					errorMessage = "";
				}
			}
		}
		System.out.println("\tend of add method tests (of numbers " + min + " to " + max + ")");
		int totalTests = (max - min + 1) * (max - min + 1);
		System.out.println("\tpassed " + numCorrect + "/" + totalTests + " (" + ((numCorrect / ((double) totalTests)) * 100) + "%)");
	}
	
	public static void multiply() throws IOException {
		int min = -100, max = 100;
		int numCorrect = 0;
		String firstIntegerString, secondIntegerString, integerProductString;
		BigInteger firstBigInteger, secondBigInteger, bigIntProduct;
		int signOfBigIntSum, signOfIntegerString, lengthOfInteger;
		boolean isCorrect;
		String errorMessage = "";
		for (int i = min; i <= max; i++) {
			firstIntegerString = Integer.valueOf(i).toString();
			firstBigInteger = BigInteger.parse(firstIntegerString);
			for (int j = min; j <= max; j++) {
				isCorrect = true;
				secondIntegerString = Integer.valueOf(j).toString();
				secondBigInteger = BigInteger.parse(secondIntegerString);
				
				integerProductString = Integer.valueOf(i * j).toString();
				bigIntProduct = BigInteger.multiply(firstBigInteger, secondBigInteger);
				
				if (!integerProductString.equals(bigIntProduct.toString())) {
					errorMessage += "\t\t- gave " + bigIntProduct + " instead of " + integerProductString + "\n";
					isCorrect = false;
				}
				
				signOfBigIntSum = bigIntProduct.front == null? 0:(bigIntProduct.negative? -1:1);
				signOfIntegerString = Integer.signum(i * j);
				if (signOfIntegerString != signOfBigIntSum) {
					errorMessage += "\t\t- gave sign " + signOfBigIntSum + " instead of " + signOfIntegerString + "\n";
					isCorrect = false;
				}
				lengthOfInteger = i * j == 0? 0:(int) (Math.log10(Math.abs(i * j)) + 1);
				if (lengthOfInteger != bigIntProduct.numDigits) {
					errorMessage += "\t\t- gave number of digits " + bigIntProduct.numDigits + " instead of " + lengthOfInteger + "\n";
					isCorrect = false;
				}
				
				if (isCorrect)
					numCorrect++;
				else {
					System.out.println("\tmultiply(" + i + ",  " + j + ")\n" + errorMessage);
					System.out.println("--------------------------------------------------------------------------------------------");
					errorMessage = "";
				}
			}
		}
		System.out.println("\tend of multiply method tests (of numbers " + min + " to " + max + ")");
		int totalTests = (max - min + 1) * (max - min + 1);
		System.out.println("\tpassed " + numCorrect + "/" + totalTests + " (" + ((numCorrect / ((double) totalTests)) * 100) + "%)");
		
	}
	
	public static void main(String[] args) throws IOException {
		sc = new Scanner(System.in);
		
		char choice;
		while ((choice = getChoice()) != 'q') {
			switch (choice) {
				case 'p':
					parse();
					break;
				case 'a':
					add();
					break;
				case 'm':
					multiply();
					break;
				default:
					System.out.println("Incorrect choice");
			}
		}
	}
	
	private static char getChoice() {
		System.out.println("Select the method you wish to test");
		System.out.print("(p)arse, (a)dd, (m)ultiply, or (q)uit? => ");
		String in = sc.nextLine();
		char choice;
		if (in == null || in.length() == 0) {
			choice = ' ';
		}
		else {
			choice = in.toLowerCase().charAt(0);
		}
		return choice;
	}
	
}