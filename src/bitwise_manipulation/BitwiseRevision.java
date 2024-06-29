package bitwise_manipulation;

import java.util.Arrays;
import java.util.List;
import java.util.Map;
import java.util.stream.Collector;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

public class BitwiseRevision {
    public static void main(String[] args) {
        //System.out.println(isOdd(100));
        //System.out.println(setBitAt(8, 3));
        //System.out.println(unsetBit(9, 1));
        //System.out.println(toggleBit(9,3));
        //System.out.println(isBitSet(9,1));
        //System.out.println(8 >> 1);
        //System.out.println(-8 >>> 1);
        //System.out.println(logBase2(9));
        //System.out.println(mostSignificantBit(18));
        //System.out.println(powerOf2(16));
        //System.out.println(maxAnd(new int[] {4, 8, 16, 2} ));
        //System.out.println(rightMostBit(12));
        //System.out.println(~8+1);
        //print2oddNumbers(new int[]{4, 4, 100, 5000, 4, 4, 4, 4, 100, 100});
        //printPowerSet("ABC");
        //printLongestSubSeq(220);
        //System.out.println(rightMostDiffBitPosition(11, 9));
        //System.out.println(rightMostDiffBitPosition(52, 4));
        System.out.println(countSetBits(4));
    }

    private static boolean isOdd(int n) {
        return (n & 1) != 0;
    }

    private static int setBitAt(int n, int position) {
        int nth = 1 << (position - 1);
        return n | nth;
    }

    // the idea is to shift the number then flip the 1s to make
    // the nth position as 0 and then do an AND operation
    private static  int unsetBit(int n, int position) {
        int nth = 1 << (position - 1);
        return n & (~nth);
    }

    private static int toggleBit(int n, int position) {
        int nth = 1 << (position - 1);
        return  n ^ nth;
    }

    private static boolean isBitSet(int n, int position) {
        int nth = 1 << (position-1);
        return (n & nth) != 0;
    }

    private static int logBase2(int n) {
        int count = 0;
        while(n > 1) {
            n >>=1;
            count++;
        }
        return count;
    }

    private static boolean powerOf2(int n) {
        return (n & (n -1)) == 0;
    }

    private static int mostSignificantBit(int n) {
        int count = 0;
        while(n > 1) {
            n>>=1;
            count++;
        }
        return 1 << count;
    }

    private static int findXor(int n) {
        //X ^ X = 0
        // 0 ^ X = X
        // 1 ^ 2 = 3
        // 1 ^ 2 ^ 3 = 3 ^ 3 = 0;
        // 0 ^ 4 = 4;
        //logic is modwith 4 and based on rem
        int rem = n % 4;
        if(rem == 0) {
            return n;
        }
        if(rem == 1) {
            return 1;
        }
        if(rem == 2) {
            return n + 1;
        }
        return n;
    }

    //max end means that we have to have the right most common bit
    //you starte with taking the right most bit and then check which
    //all numbes have the bit set, if most than 2 then keep the and of the number
    //return finally;
    private static int maxAnd(int[] arr) {
        //see in jave the int is 4 bytes
        // 32 bits
        return IntStream.iterate(30, i -> i > 0, i -> i-=1)
                .map(i -> 1 << i)
                .reduce(0,  (r, i) -> {
                    if(Arrays.stream(arr)
                            .filter(j -> ((r | i) & j) == (r| i))
                            .count() > 1)
                        return r | i;
                    return r;
                });
    }

    private static int countNumberOfSetBit(int n) {
        //ok so two solutions
        //1 is you go thorugh evey bit and then check if its set
        /*return (int)IntStream.rangeClosed(0, 30)
                .map(i -> 1 << i)
                .filter(i -> (n & i) != 0)
                .peek(System.out::println)
                .count();
        */
        //other solution is to use Brian Karnigham
        //the idea is to unset the left most bit (you unset it by n & (n-1)
        //keep doing this until the number is 0
        int count = 0;
        while(n > 0) {
            count++;
            n&=(n-1);
            System.out.println(n);
        }
        return count;
    }

    //Given an array find 2 odd numbers
    //the idea is we xor all
    //and then with that xor we iterate over array and do the xor again
    //if the number is present in the array then return both;
    private static void print2oddNumbers(int[] arr) {
        int xor = Arrays.stream(arr).reduce(0, (r, i) -> r ^ i);
        int rightMostBit = xor & (~xor + 1);
        Map<Boolean, List<Integer>> partition = Arrays.stream(arr)
                .boxed()
                .collect(Collectors.partitioningBy(i -> (i & rightMostBit) > 0));
        System.out.println(partition.get(Boolean.TRUE).stream().reduce(0, (r,i) -> r ^ i));
        System.out.println(partition.get(Boolean.FALSE).stream().reduce(0, (r,i) -> r ^ i));

    }

    //find rightmost setBit
    private static int rightMostBit(int n) {
        return n & (~n+1);
    }

    private static void printPowerSet(String str) {
        IntStream.rangeClosed(0, 1 << str.length())
                .mapToObj(i ->
                        IntStream.range(0, str.length())
                        .filter(j -> ((1<<j) & i) > 0)
                                .mapToObj(str::charAt)
                                .collect(Collector.of(StringBuilder::new, StringBuilder::append, StringBuilder::append, StringBuilder::toString)))
                .forEach(System.out::println);
    }

    private static void printLongestSubSeq(int n) {
/*
        int rn = mostSignificantBit(n);
        int count = 0;
        int max = 0;
        int counter = 1;
        while(counter <= rn) {
            if((n & counter) == 0) {
                max = Math.max(max, count);
                count = 0;
            }else
                count++;
            counter<<=1;
        }
*/

//        System.out.println(max);
        /*
        IntStream.rangeClosed(1, rn)
                .boxed()
                .collect(Collectors.groupingBy(i -> ((1<<i) & n)*/
        //the idea is to left shift the number until 0
        int count = 0;
        while(n > 0) {
            n&=(n<<1);
            count++;
        }
        System.out.println(count);
    }

    private static int positionOfRightMostBit(int n) {
        //find the righmost bit
        int rn = n & -n;
        return (int)(Math.log(rn)/Math.log(2)) + 1;
    }

    /**
     * Given two numbers M and N. The task is to find the position of the rightmost
     * different bit in the binary representation of numbers.
     * If both M and N are the same then return -1 in this case.
     */
    private static int rightMostDiffBitPosition(int n, int m) {
        int xor = n ^ m;
        return positionOfRightMostBit(xor);
    }

    private static int countSetBits(final int n) {
        //so the idea is to find the pattern
        int pattern = 1;
        int patternMultiplier = pattern*2;
        //n++;
        int count = 0;
        while(pattern < n) {
            count+=(n/patternMultiplier)*pattern;
            int rem = n%patternMultiplier;
            if(rem > pattern) {
                count+=rem-pattern;
            }
            pattern<<=1;
            patternMultiplier = pattern*2;
        }
        //return count;

        return IntStream.iterate(1, i -> i <= n, i ->i<<=1)
                .map(i -> (((n+1)/(2*i))*i) + ((n+1)%(2*i) > i ? ((n+1)%(2*i) - i ): 0))
                .peek(System.out::println)
                .sum();
    }
}