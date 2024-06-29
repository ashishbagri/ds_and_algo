package mathematics;

import java.util.stream.IntStream;

public class MathsRevision {

    public static void main(String[] args) {
        //System.out.println(numberOfDigits(12345));
        //System.out.println(numberOfDigitInFactortia(12));
        //System.out.println(isPrime(43));
        //System.out.println(isPrime(877));
        System.out.println(countOf5(20));
    }

    //coun the number of digitsin a number

    private static int numberOfDigits(int n) {
        return (int) (Math.floor(Math.log10(n)) + 1);
    }

    //count of digits in factorial of n
    private static int numberOfDigitInFactortia(int n) {
        //definatley the faacotial will betoo big to keep
        //the other break up is
        // N! = 1 * 2* 3 * .....(n-1) * n
        //to find the digits
        //Math.floor(log10(N!)) + 1 = floor(log10(N * (N-1) ....2 * 1) + 1;
        //we know log(x*y) = log (x) + log(y);
        //hence Math.floor(log10(N!)) + 1 = floor(log10(N)  + log(N-1) + ....log(2) + log(1)) + 1;

        double log10Sum = IntStream.rangeClosed(1, n)
                .mapToDouble(Math::log10)
                .sum();
        return (int)Math.floor(log10Sum) + 1;
    }

    //The idea of prime is that a number is only divisibe by itself and 1
    // so you start with 2 and check if divisible
    //if yes return false
    //else keep going
    //we can optimize by goingonly till Math.sq(N) ?
    //
    // why cause after that it will not be divisible for sure
    private static boolean isPrime(int n) {
       return IntStream.rangeClosed(2, (int) Math.sqrt(n))
               .noneMatch(i -> n % i == 0);
    }

    //find how many number till given n have exactly 3 divisors
    //this means that it has to be a square of a prime number
    private static int haveExactly3Divisors(int n) {
        /*return (int)IntStream.rangeClosed(2, n)
                .mapToDouble(Math::sqrt)
                .mapToInt( i -> (int)i)
                .filter(MathsRevision::isPrime)
                .count();*/
        //see either we see all the numbers which is a waste right
        //cause we have to find till N
        //so the idea is any prime number which has sq less than N
        //so go only till Math.sqrt(N) and any prime number less than than
        //has a answer of sq
        return (int)IntStream.rangeClosed(2, (int)Math.sqrt(n))
                .filter(MathsRevision::isPrime)
                .count();
    }

    private static int numberOfTrailingZerosInFactorial(int n) {
        //ok so the naive way is to find the factorial first.
        //and then just count how many zeros it has ?
        //yes but how can we count that ?
        //number of trailing zeros ?
        // you find the prime factors of it
        // which will tell you how many 2*5 it has
        //otherwise let's see the other way
        //n! = n * (n-1) *
        //see 10 is made by 2*5, but we know lower bound if always 5
        // on every number how many 5 do you have ?
        // and how many 25 do you have
        // for example = 10
        //10/5 = 2 (no of 5 = 2)
        //but when its power of 5 then we have to add 1
        //so the idea is go through the number and then divide it by 5 then power of 5 untill
        //it becomes less than that
        return countOf5(n);
    }

    private static int countOf5(int n) {
        return IntStream.iterate(5, i -> i <=n, i -> i*5)
                .map(i -> n/i)
                .sum();
    }
}
