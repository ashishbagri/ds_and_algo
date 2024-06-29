package mathematics;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Comparator;
import java.util.stream.IntStream;

import static java.lang.Math.floor;
import static java.lang.Math.log10;

public class MathsClass {

    //Find the number of digits in a number\
    //a number could be
    // -ve - in this case we have to make it positive and then count
    // 0 or single digit
    public static int numberOfDigits(int n) {
        int count = 0;
        n = Math.abs(n);
        while( n != 0) {
            count++;
            n/=10;
        }
        return count;
    }

    //log based solution for count of digits
    //we know that log 10 of something, upperbound of it will give us numebr of digits
    public static int numberOfDigitsLogs(int n) {
        return (int) (floor(log10(n) + 1));
    }

    //find the number of digits in factorial of n
    public static int numberOfDigitsInFactorial(int n ) {
        double sum = IntStream.rangeClosed(1, n)
                .mapToDouble(Math::log10)
                .sum();
        return (int)Math.floor(sum) + 1;
    }

    public static void main(String[] args) {
        //System.out.println(numberOfDigits(1001236998));
        //System.out.println(numberOfDigitsLogs(1001236998));
        //System.out.println(numberOfDigitsInFactorial(100));
        //System.out.println(gp(84,87, 3));
        //System.out.println(quadraticRoots(83 ,720,621));
        //System.out.println(exactly3Divisors(10));
        System.out.println(sumUnderModulo(9223372036854775807L,9223372036854775807L));
    }

    public static double gp(int a, int b, int n) {
        return a * Math.pow((double) b /a,(n-1));
    }

    public static boolean isPrime(int N) {
        return IntStream.rangeClosed(2, (int) Math.sqrt(N))
                .noneMatch(i -> N % i == 0);
    }

    // number which will have only 3 factors are perfect square of prime numbers
    //now the logic to find is find a prime number and its sq is one answer
    public static int exactly3Divisors(int N) {
        int count = 0;
        for(int i = 2;i*i <= N; i++) {
            if(isPrime(i))
                count++;
        }
        return count;
    }

    public static ArrayList<Integer> quadraticRoots(int a, int b, int c) {
        ArrayList<Integer> result = new ArrayList<>(2);
        double d = Math.pow(b,2) - (4*a*c);
        if(d < 0) {
            result.add(-1);
            return result;
        }
        double sqd = Math.sqrt(d);
        double root1 = (-1*b + sqd)/(2*a);
        double root2 = (-1*b - sqd)/(2*a);
        result.add((int)Math.floor(root1));
        result.add((int)Math.floor(root2));
        result.sort(Comparator.reverseOrder());
        return result;
    }

    public int median(int A[],int N)
    {

        Arrays.sort(A);

        int l= A.length;
        if(l%2 == 0) {
            return (int)Math.floor((A[l/2] + A[l/2+1])/2);
        }else {
            return A[l/2];
        }
        //Your code here
        //If median is fraction then conver it to integer and return
    }
    //Function to find median of the array elements.
    public int mean(int A[],int N)
    {
        return Arrays.stream(A)
                .sum()/N;
    }

    public static long sumUnderModulo(long a, long b){
        int mod = 1000000007;
        return (a%mod + b%mod)%mod;
    }
}
