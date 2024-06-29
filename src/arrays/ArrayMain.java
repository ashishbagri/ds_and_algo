package arrays;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.function.BiConsumer;
import java.util.function.Consumer;
import java.util.stream.IntStream;

public class ArrayMain {

    public static void main(String[] args) throws IOException {
        int[] arr = new int[]{1,2,3,4,5,6,7};
        //rotateAnArrayKTimes(arr, 2);
        //reverseAnArray(arr);
        System.out.println(findMaxSumOfConsecutiveElements(new int[]{1, 4, 2, 10, 23, 3, 1, 0, 20}, 4));

        printArray(prefixSum(new int[] {10, 20, 10, 5, 15}));
        System.out.println(prefixProblem());
    }

    public static void rotateAnArrayKTimes(int[] arr, int k) {
        IntStream.range(0,k)
                .forEach( i -> singleLeftRotation(arr));
    }

    public static void reverseAnArray(int[] arr) {
        IntStream.range(0, arr.length/2)
                .forEach( i -> swap(arr, i, arr.length - 1 - i));
    }

    /**
     * Sample Problem: Consider an array of size N with all initial values as 0.
     * Perform given 'm' add operations from index 'a' to 'b'
     * and evaluate highest element in array.
     * An add operation adds 100 to all elements from index a to b (both inclusive).
     * @return
     */
    public static int prefixProblem() throws IOException {
        int n = 5;
        int m = 3;
        int[] arr = new int[n];
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        while (m-- > 0) {
            String[] in = br.readLine().split(" ");
            int a = Integer.parseInt(in[0]);
            int b = Integer.parseInt(in[1]);
            addToRange(arr, a, b);
        }
        return Arrays.stream(arr)
                .sum();
    }

    public static void addToRange(int[] arr, int start, int end) {
        IntStream.rangeClosed(start, end)
                .forEach(i -> arr[i]+=100);
    }

    public static int[] prefixSum(int[] arr) {
        return prefixSum(arr, 0, 1);
    }

    public static int[] prefixSumReverse(int[] arr) {
        return prefixSum(arr, arr.length - 1 , -1);
    }

    public static int[] prefixSum(int[] arr, int offset, int direction) {
        return arrayProcessing(arr, offset, (i, y) -> y[offset - i]  = y[offset - i -1*direction] + arr[offset - i]);
    }

    public static int[] arrayProcessing(int[] arr, int offset, BiConsumer<Integer, int[]> consumer) {
        final int[] result = new int[arr.length];
        result[offset - 0] = result[offset - 0];
        IntStream.range(1, arr.length)
                .forEach(i -> consumer.accept(i, result));
        return result;
    }

    /**
     * Given an array of integers of size 'n'.
     Our aim is to calculate the maximum sum of 'k'
     consecutive elements in the array.
     * @param arr : input
     * @param k : sliding window
     */
    public static int findMaxSumOfConsecutiveElements(int[] arr, int k) {
        return IntStream.rangeClosed(0, arr.length - k)
                .filter( i -> i < arr.length)
                .map(i -> sumOfRange(arr, i, i + k))
                //.peek(System.out::println)
                .max().orElse(0);
    }

    public static int sumOfRange(int[] arr, int start, int end) {
        return IntStream.range(start, end)
                .map(i -> arr[i])
                .sum();
    }

    public static void printArray(int[] arr) {
        Arrays.stream(arr)
                .forEach(i -> System.out.print(i+" "));
    }

    public static void swap(int[] arr, int i , int j) {
        int temp = arr[i];
        arr[i] = arr[j];
        arr[j] = temp;
    }

    public static void singleLeftRotation(int[] arr) {
        int temp = arr[0];
        int length = arr.length;
        IntStream.range(0, length - 1)
                .forEach( i -> arr[i] = arr[i+1]);
        arr[length-1] = temp;
    }
}