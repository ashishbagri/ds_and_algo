package bitwise_manipulation;

import utils.IOUtil;

import java.time.LocalTime;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Comparator;
import java.util.List;

public class BitwisePractice {

    record Subject(int diff, int id){}

    public static void main(String[] args) {
        int[] num = IOUtil.getInts();
        int n = num[0];
        int q = num[1];
        List<String> events = new ArrayList<>(n);
        while(n-- > 0) {
            String s = IOUtil.readLine();
            events.add(s);
        }
        System.out.println(getSlot(events, q));
    }

    private static String getSlot(List<String> events, int k) {
        //Add first and last padding
        //First
        events.add("00:00 00:00");
        events.add("23:59 23:59");
        Schedule freeSchedule =
                events.stream()
                .map(BitwisePractice::getSchedule)
                .map(s -> new Schedule(s.start, s.end.plusMinutes(k).isBefore(LocalTime.parse("23:59")) ? s.end.plusMinutes(k) : s.end))
                .sorted(Comparator.comparing(Schedule::end))
                        .min((s1, s2) -> s1.end.isAfter(s2.start) ? 1 : -1)
                .orElseThrow();
        return freeSchedule.end.minusMinutes(k).toString();
    }

    private static Schedule getSchedule(String event) {
        String[] str = event.split(" ");
        return new Schedule(LocalTime.parse(str[0]), LocalTime.parse(str[1]));
    }

    record Schedule(LocalTime start, LocalTime end){}
    //check if a number is even or od
    private static boolean isEven(int n) {
        return n%2 == 0;
    }

    //check if number is odd even by bitwise
    //we know that last bit of number if odd is always 1
    //so if you AND a number by 1 and its 1 then it odd
    private static boolean isEvenBit(int n) {
        return (n & 1) == 0;
    }

    //set a bit at nth position
    private static int setNthBit(int num, int n) {
        int nth = 1 << (n-1);
        return (num | nth);
    }

    //clear or unset a bit at nth position
    private static int unSetBitAt(int num, int n) {
        int nth = 1 << (n-1);
        return (num & (~nth));
    }

    //toggle a bit at nth position
    private static int toggle(int num, int n) {
        int nth =  1 << (n-1);
        return num ^ nth;
    }

    //Checking if bit at nth position is set or unset:
    //the property of xor - if diff then 1 if same then 0
    //1 ^ 1 = 0
    //0 ^ 1 = 1
    private static boolean isSetAtNth(int num, int n) {
        int nth = 1<< (n-1);
        return (num & nth) == 1;
    }

    // Find log base 2 of a 32 bit integer:
    private static int logBase2(int n) {
        int count = 0;
        while(n > 1) {
            n>>=1;
            count++;
        }
        return count;

        // 1000 -> 0100 -> 0010 -> 0001 -> 0000
    }

    //Flipping the bits of a number
    //1010 -> 0101
    private static int filpBits(int n) {
        int nth = (1<<n) - 1;
        return n^nth;
    }

    //Swapping Two Numbers
    private static void swap(int a, int b) {
        a^=b;
        b^=a;
        a^=b;
        //read the properties fo XOR
        // a = (a ^ b)
        // b = b ^ a;
        // a = a ^ //b( which  will be b);
    }

    //Given a number N, the task is to check whether the given number is a power of 2 or not.
    private static boolean isPower2(int n) {
        return (n&(n-1)) == 0;
    }

    // Given a number N, find the most significant set bit in the given number.
    private static int mostSignificantbit(int n) {
        // right shift until 0
        int msb = 0;
        while(n != 1) {
            n>>=1;
            msb++;
        }
        return 1 << msb;
    }

    //Given a number N, the task is to find the XOR of all numbers from 1 to N.
    //basics :
    // so  1 ^ 2 = 3
    // X XOR X = 0;
    // so till 1 ^ 2 ^ 3 = 0
    // now we know 0  ^ X = X;
    //so we know 1 ^ 2 ^ 3 ^ 4 = 4
    //now just mod with 4 and as per reminder
    //
    private static int modulusOfAllTillN(int N) {
        N%=4;
        if(N == 0) {
            return N;
        }
        if(N == 1) {
            return 1;
        }
        if(N == 2) {
            return N+1;
        }
        if(N == 3) {
            return 0;
        }
        return N;
    }


    //Given an array arr[] of N positive elements.
    // The task is to find the Maximum AND Value
    // generated by any pair of the element from the array.
    private static void maxAnd(int[] N) {

    }

    /*Given an integer N. The task is to return the position of first set bit
    found from the right side in the binary representation of the number.
    Note: If there is no set bit in the integer N, then return 0 from the function.
     */
    //Function to find position of first set bit in the given number.
    public static int getFirstSetBit(int n) {
        //11001
        //11000
        //00111
        //11000

        //100010
        //100001
        //100000

        //10010
        //10001
        //10000
        //00010
        if(n==0) return 0;
        //logic n & (n-1) - will remove the right most significant bit
        //right most significant bit if XOR with number then only the left most bit will be left
        int rightmostbit = n & (n-1);
        n^= rightmostbit;
        int count = 0;
        while(n != 0) {
            n>>=1;
            count++;
        }
        return count;
    }

    //Function to find the first position with different bits.
    public static int posOfRightMostDiffBit(int m, int n) {

        if( m == n)
            return -1;

        //1011 //11
        //1001 //9
        //0010

        //12  1100
        //9   1001
        //    0101
        return getFirstSetBit(m^n);
    }

    /**
     * Input:
     * N = 4
     * arr[] = {4, 8, 12, 16}
     * Output: 8
     * Explanation:
     * Pair (8,12) has the Maximum AND Value 8.
     * @param arr
     * @param N
     * @return
     */
    // Function for finding maximum AND value.
    public static int maxAND (int arr[], int N) {
        //maximum AND will only be done if the right most bits are common
        //find the rightmost bit of each number
        //if 2 numb mactches then next right most bit
        //like wise
        //or you can start with bitgger number
        //10^5 mean 2^31 ?

        int res = 0;
        for(int i =16;i>0;i--) {
            int count = countBit(arr, res | (1<<i));
            if(count >= 2) {
                res|=(1<<i);
            }
        }
        return res;
    }

    private static int countBit(int arr[], int bitset) {
        return (int) Arrays.stream(arr)
                .filter(i -> (i & bitset) != 0)
                .count();
    }
    /*
    Given an unsigned integer N. The task is to swap all odd bits with even bits. For example,
    if the given number is 23 (00010111), it should be converted to 43(00101011).
    Here, every even position bit is swapped with an adjacent bit on the right side
    (even position bits are highlighted in the binary representation of 23),
    and every odd position bit is swapped with an adjacent on the left side.
     */
    public static int swapBits(int n) {
        //if we right shift and do And what will happen
        //get all the even bits
        //get all the odd bits
        //then right shift even
        //left shift odd

        int ev=n & 0xAAAAAAAA;
        //0x55555555 means 01010101010101010101010101010101 in binary.
        //we get all odd bits of n.
        int od=n & 0x55555555;

        //right Shifting the even bits obtained previously.
        ev>>=1;
        //left Shifting the even bits obtained previously.
        od<<=1;

        //doing bitwise OR of even and odd bits obtained and
        //returning the final result.
        return (ev | od);
    }
    //You are given a number N. Find the total count of set bits for all numbers from 1 to N(both inclusive).
    public static int countSetBits(int n){
        return -1;
    }
}