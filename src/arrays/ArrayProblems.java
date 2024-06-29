package arrays;

import java.util.Arrays;
import java.util.stream.IntStream;

import static arrays.ArrayMain.*;
import static utils.IOUtil.getInts;
import static utils.IOUtil.readInt;

/**
 * P0 : Rotate an array k times
 * P01 : Reverse an Array
 * P02 : Maximum sum of consecutive numbers
 * (Sliding window)
 * P03 : Leader in an array :
 * A leader is when no greater element on right side
 * P04 : Trapping Rain water problem
 * P05 : Stock buy and sell, buy at high and sell at low for max profit
 * P1 : Range of sum with queries
 * p2 : Equilibirium Index
 * p3 : Largest Sum Array
 * p4 : Merge Two sorted Arrays
 */
public class ArrayProblems {

    public static void main(String[] args) {
        ProblemOne();
        //System.out.println(ProblemTwo());
        //ProblemFour();
        //findLeader();
        //trappingRainWaterProblem();
        System.out.println(minStartValue(getInts()));
    }

    /**
     * Problem #1 : Range Sum Queries using Prefix Sum
     * We are given an Array of n integers,
     * We are given q queries having indices l and r .
     * We have to find out sum between the given range of indices.
     * [4, 5, 3, 2, 5]
     3
     0 3
     2 4
     1 3
     */
    private static void ProblemOne() {
        int[] prefixSum = prefixSum(getInts());
        int q = readInt();
        while(q-- > 0) {
            int[] indices = getInts();
            int l = indices[0];
            int r = indices[1];
            System.out.println(prefixSum[r] - (l ==0 ? 0 : prefixSum[l-1]));
        }

    }

    /**
     * Problem #2 : Equilibrium index of an array
     * Equilibrium index of an array is an index such
     * that the sum of elements at lower indexes is equal
     * to the sum of elements at higher indexes.
     * We are given an Array of integers,
     * We have to find out the first index i from left such that -
     A[0] + A[1] + ... A[i-1] = A[i+1] + A[i+2] ... A[n-1]
     *
     * [1, 2, 3 , 4, 5]
     * [1, 3, 6, 10, 15]
     * [-7, 1, 5, 2, -4, 3, 0]
     * answer : 3
     */
    private static int ProblemTwo() {

        int[] in = getInts();
        int eqIndex = getEqIndexPrefixSum(in);
        int rqIndex1 = getEqIndexPointer(in);
        System.out.println(eqIndex);
        System.out.println(rqIndex1);
        return eqIndex;
    }

    private static int getEqIndexPointer(int[] in) {
        int sum = Arrays.stream(in).sum();
        int leftSum = 0;
        for(int i = 0; i<in.length; i++) {
            sum-=in[i];
            if(sum == leftSum)
                return i;
            leftSum+=in[i];
        }
        return -1;
    }

    public static int minStartValue(int[] nums) {
        int preSum = 1 - nums[nums.length -1 ];
        for(int i = nums.length - 2;i>=0;i--) {
            if(preSum <= 0)
                preSum = 1;
            preSum = preSum - nums[i];
        }
        return preSum;
    }

    private static int getEqIndexPrefixSum(int[] in) {
        int[] prefixSum = prefixSum(in);
        return IntStream.range(1, in.length)
                .filter( i -> prefixSum[i -1] == prefixSum[in.length - 1] - prefixSum[i])
                .findAny().orElse(-1);
    }

    /**
     * Largest Sum Subarray
     * We are given an array of positive and negative integers. We have to find the subarray having maximum sum.
     * Input
     * [-3, 4, -1, -2, 1, 5]
     * [4, 7, 3, 4, 6, 5]
     * Output
     * 7
     * (4+(-1)+(-2)+1+5)
     */
    private static void ProblemThree(){
        System.out.println(Arrays.stream(prefixSumReverse(getInts())).max().getAsInt());
    }

    /**
     * Merge two sorted Arrays
     * We are given two sorted arrays arr1[ ] and arr2[ ]
     * of size m and n respectively.
     * We have to merge these arrays and store the numbers in
     * arr3[ ] of size m+n.
     * Input
     * 1 3 4 6
     * 2 5 7 8
     * Output
     * 1 2 3 4 5 6 7 8
     */
    private static void ProblemFour() {
        int[] first = getInts();
        int[] second = getInts();
        int[] result = new int[first.length + second.length];
        int i = 0;
        int j = 0;
        int k = 0;

        while ( j < first.length && k < second.length) {
            if(first[j]  > second[k])
                result[i] = second[k++];
            else
                result[i] = first[j++];

            i++;
        }

        while (j < first.length)
            result[i++] = first[j++];
        while (k < second.length)
            result[i++]= second[k++];

        printArray(result);
    }

    /**
     * so leader is basically num which has no greater element
     * on right side
     *
     * [5 3 20 15 8 3]
     * O/p : 20 25 8 3
     */
    private static void  findLeader() {
        int[] arr = getInts();
        int max = arr[arr.length - 1];
        for(int i = arr.length - 2 ; i>=0; i--) {
            int n = arr[i];
            if(n > max) {
                System.out.println(n);
                max = n;
            }
        }
    }

    /**
     * Take max on right and max on left
     * take min and minus current index value
     * 3 0 1 2 5
     */
    private static void trappingRainWaterProblem() {
        int[] in = getInts();
        int[] leftMax = getLeftMax(in);
        int[] rightMax = getRightMax(in);
        int maxWater = 0;
        for(int i =0;i< in.length;i++) {
            maxWater+=Math.min(leftMax[i], rightMax[i]) - in[i];
        }
        System.out.println(maxWater);
    }

    /**
     * 3 0 1 2 5
     */
    private static void trappingRainWaterProblemWithStack() {
        int[] in = getInts();
        int[] leftMax = getLeftMax(in);
        int[] rightMax = getRightMax(in);
        int maxWater = 0;
        for(int i =0;i< in.length;i++) {
            maxWater+=Math.min(leftMax[i], rightMax[i]) - in[i];
        }
        System.out.println(maxWater);
    }


    private static int[] getRightMax(int[] in) {
        int[] rightMax = new int[in.length];
        rightMax[in.length -1]= in[in.length -1];
        for(int i = in.length - 2; i>=0;i--) {
            rightMax[i] = Math.max(rightMax[i+1], in[i]);//max > rightMax[i + 1] ? max : rightMax[i + 1];
        }
        return rightMax;
    }

    private static int[] getLeftMax(int[] in) {
        int[] leftMax = new int[in.length];
        leftMax[0]= in[0];
        for(int i = 1; i < in.length;i++) {
            leftMax[i] = Math.max(leftMax[ i - 1], in[i]);
        }
        return leftMax;
    }

    private static void buyAndSellProblem() {
        //1 5 3 8 12
        //4+9
        //seems the idea is to find peak
        //a peak is greater from right and left
        int[] in = getInts();

    }
}