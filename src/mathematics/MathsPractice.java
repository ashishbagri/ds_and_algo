package mathematics;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Comparator;
import java.util.stream.IntStream;

import static java.lang.Math.floor;
import static java.lang.Math.log10;

public class MathsPractice {


    private int numberOfDigits(int n) {
        int count = 0;
        while(n > 0) {
            n/=10;
            count++;
        }
        return count;
    }

    private int numberOfDigitLogBasedSolution(int n) {
        return (int)Math.floor(Math.log10(n)) + 1;
    }

    //given an Integer N , Find the number of digits that appear in its factorial.
    private int numberOfDigitsInFactorial(int n) {
        //n! = n * (n-1) * (n-2) ......1
        //so if we use log base solution then
        //numberOfDigts = Math.floor(Math.log10(n!)) + 1
        //we know property of log is the multiplication is plus
        //so log10(n*n(-1)...1) = log(n) + log(n-1)....log(1)
        //hence it can be written as follows :
        // numberOfDigits = Math.floor(SUm(Math.log10(n)+ ..) )+1;
        //lets code
        double sum = IntStream.rangeClosed(1, n)
                .mapToDouble(Math::log10)
                .sum();
        return (int)Math.floor(sum) + 1;

    }

    //check if a number is prime
    private static boolean isPrime(long n) {
        return IntStream.rangeClosed(2, (int)Math.sqrt(n))
                .noneMatch(i -> n%i == 0);
    }

    //Divisors equal to 3
    //see we need to find which all elements are less or equal to N
    //and have 3 factors
    //analysis is that a prime number has exactly 2 divisors
    //so if we square prime number, that will have exactly 3 factors
    //so the idea is to go through the number and check if prime
    //is yes then add the count
    private static int numberOf3divisors(long N) {
        return (int)IntStream.rangeClosed(2, (int)Math.sqrt(N))
                .filter(MathsPractice::isPrime)
                .count();
    }

    static ArrayList<Integer> threeDivisors(ArrayList<Long> query, int q){
        return query.stream()
                .mapToInt(MathsPractice::numberOf3divisors)
                .collect(ArrayList::new, ArrayList::add, ArrayList::addAll);
    }

    //find number of trailing zeros in mod of a given N
    //consider prime factorials of N
    // 5 * 2 produces 10 (1 zero)
    //5 -> 5 4 3 2 1 - 120
    // we see that number of 2 are always equal or more than 5
    //so just find no of 5 and that should be all
    //how can we find how many fives
    //ok whatever number is given , you can divide by 5 to count
    //number of 5 in multiplication, just floor to get int
    //so 7/5 = 1 which is correct
    // what about 25 ? 25/5 = 5 but the count is 6
    // prime factors of 25 - 5 * 5
    // prime factors of 24 = 2*2*2*3
    //20 -> 2*2*5
    //15  =  5*3
    //10 - 5*2
    //5 - 5*1
    //so for every square we add an exta one , better to divide by all squares of 5
    private static int countNumberOfTrailingZerosInFactorialOf(int n) {
        return IntStream.iterate(5, i -> i <= n, i -> i * 5)
                .map(i -> n/i)
                .sum();
    }

    public static void main(String[] args) {
        System.out.println(countNumberOfTrailingZerosInFactorialOf(100));
    }
}