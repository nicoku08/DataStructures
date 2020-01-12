
package bigint;

import java.io.IOException;
import java.util.Scanner;

public class ParseTest {
	
	static Scanner sc;
	
	public static void main(String[] args) throws IOException {
		String[] testStrings = {"+0", "-0", "+123", "1023", "0012", "0", "-123", "-001", "+000"};
		String[] expectedStrings = {"0", "0", "123", "1023", "12", "0", "-123", "-1", "0"};
		
		System.out.println("Starting parse method testing");
		int numCorrect = 0;
		String parsedString = "";
		for (int i = 0; i < testStrings.length; i++) {
			parsedString = BigInteger.parse(testStrings[i]).toString();
			if (!parsedString.equals(expectedStrings[i]))
				System.out.println("The parsed string \"" + parsedString + "\" did not match the expected string \"" + expectedStrings[i] + "\"");
			else
				numCorrect++;
		}
		int totalTests = testStrings.length;
		System.out.println("Passed " + numCorrect + "/" + totalTests + " (" + ((numCorrect / ((double) totalTests)) * 100) + "%)");
		System.out.println("End of parse method testing");
	}
}