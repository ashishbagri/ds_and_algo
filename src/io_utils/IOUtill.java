package io_utils;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.stream.Stream;

public class IOUtill {

    private static final BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

    public static int[] getNextInt(String s, String delimiter) throws IOException {
        String[] strs = br.readLine().split(delimiter);
        return Stream.of(strs)
                .mapToInt(Integer::parseInt)
                .toArray();
    }
}
