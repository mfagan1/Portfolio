package interview.leetcode;

import static org.junit.Assert.assertTrue;
import interview.AutoTestUtils;

import java.util.HashSet;
import java.util.Set;

import org.junit.Test;

/**
 * Write an algorithm to determine if a number is "happy".
 * 
 * A happy number is a number defined by the following process: Starting with
 * any positive integer, replace the number by the sum of the squares of its
 * digits, and repeat the process until the number equals 1 (where it will
 * stay), or it loops endlessly in a cycle which does not include 1. Those
 * numbers for which this process ends in 1 are happy numbers.
 * 
 * Example: 19 is a happy number
 * 
 * 1^2 + 9^2 = 82
 * 8^2 + 2^2 = 68
 * 6^2 + 8^2 = 100
 * 1^2 + 0^2 + 0^2 = 1
 * 
 */
public class Happy_Number {

	public static void main(String[] args) {
		AutoTestUtils.runTestClassAndPrint(Happy_Number.class);
	}

	public boolean isHappy(int n) {
		int slow = n;
		int fast = sumSquare(n);
		while (slow != fast) {
			if (slow == 1 || fast == 1)
				return true;
			System.out.println(slow + ", " + fast);
			slow = sumSquare(slow);
			fast = sumSquare(sumSquare(fast));
		}
		return slow == 1;
	}

	private int sumSquare(int n) {
		int res = 0;
		while (n > 0) {
			int digit = n % 10;
			n /= 10;
			res += digit * digit;
		}
		return res;
	}

	@Test
	public void test1() {
		assertTrue(!isHappy(3));
	}
}
