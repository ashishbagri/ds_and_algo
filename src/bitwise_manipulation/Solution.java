package bitwise_manipulation;

import java.util.Arrays;
import java.util.function.Predicate;

public class Solution {

    private static final Solution solution = new Solution();
    public static void main(String[] args) {
        System.out.println(solution.checkIfKthBitIsSet(5,1));
        System.out.println(solution.isPowerOfTwo(1024));
        System.out.println(solution.countBits(101,0));
        System.out.println(solution.countBitsOptimized(101));
        System.out.println(solution.findOddOccurringElementInGivenArray(new int[]{3,3,3,4,5,4,5}));
        solution.printTwoOddNumbers(new int[]{3,3,3,6,4,5,4,5});
        solution.printPowerSet("ABC");
    }

    /**
     * Check if Kth Bit is set
     * @param n
     * @param k
     * @return
     */
    private static boolean checkIfKthBitIsSet(int n, int k) {
        return (n&(1<<k))==1;
    }

    /**
     * Check  if a number if power of 2
     * @param n
     * @return
     */
    private static boolean isPowerOfTwo(int n) {
        return (n&(n-1)) == 0;
    }

    /**
     * Count the number Of set bits
     * @param n
     * @return
     */
    private static int countNumberOfSetBits(int n){
        int count = 0;
        while (n >0){
            count+=n&1;
            n>>=1;
        }
        return count;
    }

    /**
     * Count the number of Bits
     * @param n
     * @param count
     * @return
     */
    private int countBits(int n, int count){
        if(n == 0)
            return count;
        int rem = n%2;
        if(rem == 1)
            count++;
        return countBits(n/2, count);
    }

    /**
     * Count the number of Bits
     * @param n
     * @return
     */
    private int countBitsOptimized(int n){
        int count = 0;
        while(n > 0){
            count++;
            n&=n-1;
        }
        return count;
    }

    /**
     * Given in an array containing one element with odd occurrence.
     * @param arr
     * @return
     */
    private int findOddOccurringElementInGivenArray(int[] arr){
        return Arrays.stream(arr)
                .reduce(0, (subtotal, element) -> subtotal ^ element);
    }

    /**
     * Given in an array containing two elements with odd occurrence.
     * @param arr
     */
    private void printTwoOddNumbers(int[] arr){
        int xor = findOddOccurringElementInGivenArray(arr);
        int first_set_bit = xor & ~(xor -1);
        System.out.println("first number is : "+ getNum(arr, i -> (i & first_set_bit) == 0));
        System.out.println("second number is : "+getNum(arr, i -> (i & first_set_bit) != 0));
    }

    private int getNum(int[] arr, Predicate<Integer> predicate){
        return Arrays.stream(arr)
                .filter(i -> predicate.test(i))
                .reduce(0 , (s, i) -> s ^ i);
    }

    /**
     * Generate Power Set of a Given String
     * @param str
     */
    private void printPowerSet(String str){
        int length = str.length();
        int twoOfLength = 1 << length;

        for( int i =0 ; i <twoOfLength; i++) {
            for (int j = 0; j < length; j++)
                if ((i & (1 << j)) > 0)
                    System.out.print(str.charAt(j));
            System.out.println();
        }
    }

    /**
     * Longest subsequence of 1 in binary rep of a number
     */
    private void longestSubsetOf1inBinaryRepOfaNumber(int n){

        /**
         * The idea is based on the concept that the AND of bit sequence
         * with a left shifted by 1 version of itself effectively removes
         * the trailing 1 from every sequence of consecutive 1s.
         *
         * So the operation N = (N & (N << 1)) reduces length of every sequence
         * of 1s by one in binary representation of N. If we keep doing this
         * operation in a loop, we end up with N = 0. The number of iterations
         * required to reach 0 is actually length of the longest consecutive sequence of 1s.
         */

        int count = 0;
        while(n > 0) {
            n&=(n<<1);
            count++;
        }
        System.out.println(count);
    }


    /**
     * check if OddOrNot
     * @param n
     * @return
     */
    private boolean isEven(int n) {
        //1. XOR : we know if we XOR a number with 1 it reduce by 1 if odd and increment by 1 if even
        return (n^1) == (n+1);

        //2. AND : when you do & with 1, it will become 1 if odd and 0 is even
        //return (n & 1) == 0;

        //3. OR : when you do OR with 1 , the number will remain same if even
        //return (n | 1) != n;

        //4. >> << - when you do a number left shift it will loose one and then do right shift it will gain a 0
        //if a numbe remains  same then even otherise odd
        //int y  = n >> 1;
        //return n == (y<<1);
    }

    /**
     * Find log 2 base of 32 bit Integer
     * @param n
     */
    private void find2BaseOf32BitInteger(int n){
        //keep shifting right until it becomes 0;
        int count = 0;
        while(n != 0){
            n>>=1;
            count++;
        }

        System.out.println(count);
    }

    private void flipBits(int n ){
        System.out.println(n^((1<<n)-1));
    }

    /**
     * Given a number N, find the most significant set bit in a number
     */
    private void findMSB(int n) {
        
    }
}
