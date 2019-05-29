package interview.leetcode;

/**
 * The count-and-say sequence is the sequence of integers beginning as follows:
 * 1, 11, 21, 1211, 111221, ...
 * 
 * 1 is read off as "one 1" or 11.
 * 
 * 11 is read off as "two 1s" or 21.
 * 
 * 21 is read off as "one 2, then one 1" or 1211.
 * 
 * Given an integer n, generate the nth sequence.
 * 
 * Note: The sequence of integers will be represented as a string.
 * 
 */
public class Count_and_Say {

	public static void main(String[] args) {
		for (int i = 1; i <= 7; i++) {
			System.out.println(countAndSay(i) + "\n\n");
		}

		System.out.println(countAndSay2(20));
	}

	/**
	 * Generate current sequence by the previous sequence, (n)th by (n-1)th, n-1
	 * by n-2, etc...
	 * 
	 * Time: O(n * L), L is the average length of all intermediate Strings.
	 */
	public static String countAndSay(int n) {
		String s = "1";
		StringBuffer sb = new StringBuffer();
		while (--n > 0) {
			int size = s.length();
			int cnt = 1;
			for (int i = 0; i < size; i++) {
				char c = s.charAt(i);
				if (i + 1 < size && c == s.charAt(i + 1)) {
					cnt++; // count
				} else { // say
					sb.append(cnt).append(c);
					cnt = 1;
				}
			}
			s = sb.toString();
			sb.setLength(0);
		}
		return s;
	}

}
