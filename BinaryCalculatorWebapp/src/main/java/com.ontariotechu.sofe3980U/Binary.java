package com.ontariotechu.sofe3980U;

/**
 * Unsigned integer Binary variable
 *
 */
public class Binary {
	private String number = "0"; // string containing the binary value '0' or '1'

	/**
	 * A constructor that generates a binary object.
	 *
	 * @param number a String of the binary values. It should contain only zeros or
	 *               ones with any length and order. otherwise, the value of "0"
	 *               will be stored. Trailing zeros will be excluded and empty
	 *               string will be considered as zero.
	 */
	public Binary(String number) {
		if (number == null || number.isEmpty()) {
			this.number = "0"; // Default to "0" for null or empty input
			return;
		}

		// Validate the binary string (only '0' or '1' allowed)
		for (int i = 0; i < number.length(); i++) {
			char ch = number.charAt(i);
			if (ch != '0' && ch != '1') {
				this.number = "0"; // Default to "0" for invalid input
				return;
			}
		}

		// Remove leading zeros
		int beg;
		for (beg = 0; beg < number.length(); beg++) {
			if (number.charAt(beg) != '0') {
				break;
			}
		}

		// If all digits are '0', ensure number is "0"
		this.number = (beg == number.length()) ? "0" : number.substring(beg);

		// Ensure empty strings are replaced with "0"
		if (this.number.isEmpty()) {
			this.number = "0";
		}
	}

	/**
	 * Return the binary value of the variable
	 *
	 * @return the binary value in a string format.
	 */
	public String getValue() {
		return this.number;
	}

	/**
	 * Adding two binary variables. For more information, visit
	 * <a href="https://www.wikihow.com/Add-Binary-Numbers"> Add-Binary-Numbers
	 * </a>.
	 *
	 * @param num1 The first addend object
	 * @param num2 The second addend object
	 * @return A binary variable with a value of <i>num1+num2</i>.
	 */
	public static Binary add(Binary num1, Binary num2) {
		// the index of the first digit of each number
		int ind1 = num1.number.length() - 1;
		int ind2 = num2.number.length() - 1;
		// initial variable
		int carry = 0;
		String num3 = ""; // the binary value of the sum
		while (ind1 >= 0 || ind2 >= 0 || carry != 0) // loop until all digits are processed
		{
			int sum = carry; // previous carry
			if (ind1 >= 0) { // if num1 has a digit to add
				sum += (num1.number.charAt(ind1) == '1') ? 1 : 0; // convert the digit to int and add it to sum
				ind1--; // update ind1
			}
			if (ind2 >= 0) { // if num2 has a digit to add
				sum += (num2.number.charAt(ind2) == '1') ? 1 : 0; // convert the digit to int and add it to sum
				ind2--; // update ind2
			}
			carry = sum / 2; // the new carry
			sum = sum % 2; // the resultant digit
			num3 = ((sum == 0) ? "0" : "1") + num3; // convert sum to string and append it to num3
		}
		Binary result = new Binary(num3); // create a binary object with the calculated value.
		return result;

	}

	public static Binary orOperation(Binary num1, Binary num2) {
		// Create a StringBuilder to construct the resulting binary number
		StringBuilder output = new StringBuilder();

		// Initialize indices for traversing the binary numbers from right to left
		int index1 = num1.number.length() - 1;
		int index2 = num2.number.length() - 1;

		// Loop until both indices are less than 0, meaning we have processed all bits
		while (index1 >= 0 || index2 >= 0) {
			// Default the bits to '0' in case one of the indices is out of bounds
			char bitA = '0';
			char bitB = '0';

			// Get the bit from the first binary number if the index is valid
			if (index1 >= 0) {
				bitA = num1.number.charAt(index1);
			}

			// Get the bit from the second binary number if the index is valid
			if (index2 >= 0) {
				bitB = num2.number.charAt(index2);
			}

			// Perform the logical OR operation and append the result to the output
			if (bitA == '1' || bitB == '1') {
				output.append('1'); // Append '1' if either bit is '1'
			} else {
				output.append('0'); // Append '0' if both bits are '0'
			}

			// Decrement the indices to move to the next bits on the left
			index1--;
			index2--;
		}

		// Reverse the constructed binary string and return a new Binary object
		return new Binary(output.reverse().toString());
	}

	/**
	 * Performs the AND operation between two binary numbers.
	 *
	 * @param num1 The first binary number.
	 * @param num2 The second binary number.
	 * @return A Binary object representing the result of AND operation.
	 */
	public static Binary andOperation(Binary num1, Binary num2) {
		StringBuilder output = new StringBuilder();
		int index1 = num1.number.length() - 1;
		int index2 = num2.number.length() - 1;

		while (index1 >= 0 || index2 >= 0) {
			char bitA;
			char bitB;

			if (index1 >= 0) {
				bitA = num1.number.charAt(index1);
			} else {
				bitA = '0';
			}

			if (index2 >= 0) {
				bitB = num2.number.charAt(index2);
			} else {
				bitB = '0';
			}

			if (bitA == '1' && bitB == '1') {
				output.append('1');
			} else {
				output.append('0');
			}

			index1--;
			index2--;
		}

		return new Binary(output.reverse().toString());
	}

	/**
	 * Performs the AND operation between two binary numbers.
	 *
	 * @param num1 The first binary number.
	 * @param num2 The second binary number.
	 * @return A Binary object representing the result of AND operation.
	 */
	public static Binary multiplyBinary(Binary first, Binary second) {
		Binary result = new Binary("0");
		Binary temp = new Binary(first.number);
	
		for (int i = second.number.length() - 1; i >= 0; i--) {
			if (second.number.charAt(i) == '1') {
				// Inline addition logic
				StringBuilder sum = new StringBuilder();
				int carry = 0, x = result.number.length() - 1, y = temp.number.length() - 1;
	
				while (x >= 0 || y >= 0 || carry > 0) {
					int bit1 = x >= 0 ? result.number.charAt(x) - '0' : 0;
					int bit2 = y >= 0 ? temp.number.charAt(y) - '0' : 0;
					int total = bit1 + bit2 + carry;
					sum.append(total % 2);
					carry = total / 2;
					x--;
					y--;
				}
	
				result = new Binary(sum.reverse().toString());
			}
	
			// Inline shift logic
			if (!"0".equals(temp.getValue())) {
				temp = new Binary(temp.number + "0");
			}
		}
	
		return result;
	}

}
