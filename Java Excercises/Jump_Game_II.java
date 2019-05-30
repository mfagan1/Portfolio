package interview.leetcode;

import java.util.Arrays;

/**
 * Given an array of non-negative integers, you are initially positioned at the
 * first index of the array.
 * 
 * Each element in the array represents your maximum jump length at that
 * position.
 * 
 * Your goal is to reach the last index in the minimum number of jumps.
 * 
 * For example: Given array A = [2,3,1,1,4]
 * 
 * The minimum number of jumps to reach the last index is 2. (Jump 1 step from
 * index 0 to 1, then 3 steps to the last index.)
 * 
 * 
 */
public class Jump_Game_II {

	public static void main(String[] args) {
		System.out.print(jump(new int[] { 1, 1 }) + "\t");
		System.out.print(jump(new int[] { 1, 2, 1, 1, 1 }) + "\t");
		System.out.print(jump(new int[] { 2, 3, 0, 1, 4 }) + "\t");
		System.out.print(jump(new int[] { 7, 0, 9, 6, 9, 6, 1, 7, 9, 0, 1, 2,
				9, 0, 3 })
				+ "\t");


	}

	public static int jump(int[] A) {
		if (A.length < 2)
			return 0;
		int maxJump = A[0], restSteps = A[0], jumps = 0;
		for (int i = 1; i < A.length; i++) {
			if (i > maxJump)
				return -1;
			if (i + A[i] > maxJump) // update max position it can reach
				maxJump = i + A[i];
			restSteps--;
			if (restSteps == 0 || i == A.length - 1) { // have to jump
				restSteps = maxJump - i;
				jumps++;
			}
		}
		return jumps;
	}



}
