package recursion;

import java.util.stream.Collector;
import java.util.stream.IntStream;

public class Recursion {

    public static void main(String[] args) {
        System.out.println(isPalindrome("malayalam"));
        System.out.println(isPalindrome("max"));
        printAllSubsets("abc");
        System.out.println(joesphusProblem(5,2));
    }

    private static boolean isPalindrome(String str) {
        return isPalindrome(str, 0, str.length() - 1);
    }

    private static boolean isPalindrome(String str, int start, int end) {
        if(start >= end)
            return true;
        if(str.charAt(start) != str.charAt(end))
            return false;
        return isPalindrome(str, start + 1, end - 1);
    }

    private static void printAllSubsets(String str) {
        //I can print all subsets using bit logic too
        int n = 1<<str.length();
        IntStream.rangeClosed(0, n)
                .mapToObj(i -> IntStream.range(0, str.length())
                            .filter(j -> ((1<<j & i) != 0))
                            .mapToObj(str::charAt)
                            .collect(Collector.of(StringBuilder::new, StringBuilder::append, StringBuilder::append, StringBuilder::toString))
                ).forEach(System.out::println);

        //the other way is recursive way
        //in which we iterate over the string and generate all combinations with it
        printSubSet(str, str.length(), 0, "");
        System.out.println("=======");
    }

    private static void printSubSet(String str, int l, int index, String currentString) {
        if(l == index) {
            System.out.println(currentString);
            return;
        }
        printSubSet(str, l, index + 1, currentString+str.charAt(index));
        printSubSet(str, l, index + 1, currentString);
    }
    private static int joesphusProblem(int n, int k) {
        return (joesphusProblem(n-1, k)  + k-1)%n + 1;
    }

    private static void printAllPermutation(String str) {

    }

    private static void printAllPermutation(String str, int start, int end, String prefix) {

    }
}
