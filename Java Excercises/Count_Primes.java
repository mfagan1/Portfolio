package interview.leetcode;

import static org.junit.Assert.assertTrue;
import interview.AutoTestUtils;

import java.util.Arrays;

import org.junit.Test;

/**
 * Description:
 * 
 * Count the number of prime numbers less than a non-negative number, n
 * 
 * References:
 * 
 * How Many Primes Are There?
 * 
 * Sieve of Eratosthenes
 * 
 */
public class Count_Primes {

	public static void main(String[] args) {
		AutoTestUtils.runTestClassAndPrint(Count_Primes.class);
	}


	public int countPrimes(int n) {
		boolean[] primes = new boolean[n];
		Arrays.fill(primes, true);
		int cnt = 0;
		for (int i = 2; i < n; i++) {
			if (primes[i]) {
				cnt++;
				for (int j = 2; j * i < n; j++) {
					primes[i * j] = false;
				}
			}
		}
		return cnt;
	}

	public int countPrimes_Improved(int n) {
		boolean[] primes = new boolean[n];
		Arrays.fill(primes, true);
		for (int i = 2; i * i < n; i++) {
			if (primes[i]) {
				// suffices to consider multiples i, i+1, ..., N/i
				for (int j = 2; j * i < n; j++) {
					primes[i * j] = false;
				}
			}
		}
		int cnt = 0;
		for (int i = 2; i < n; i++)
			cnt = primes[i] ? cnt + 1 : cnt;
		return cnt;
	}

	@Test
	public void test1() {
		int n = 2;
		int res = countPrimes_Improved(n);
		int ans = 0;
		assertTrue("Wrong: " + res, res == ans);
	}

	@Test
	public void test2() {
		int n = 3;
		int res = countPrimes_Improved(n);
		int ans = 1;
		assertTrue("Wrong: " + res, res == ans);
	}

	@Test
	public void test3() {
		int n = 5;
		int res = countPrimes_Improved(n); // 2 3
		int ans = 2;
		assertTrue("Wrong: " + res, res == ans);
	}

	@Test
	public void test4() {
		int n = 9;
		int res = countPrimes_Improved(n); // 2 3 5 7
		int ans = 4;
		assertTrue("Wrong: " + res, res == ans);
	}
}
