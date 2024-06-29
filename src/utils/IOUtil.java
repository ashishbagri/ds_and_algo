package utils;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.stream.IntStream;

public class IOUtil {

    private static final String SPACE = " ";
    private static final String COMMA = ",";

    private static final BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

    public static String readLine() {
        try {
            return br.readLine();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return null;
    }

    public static int readInt() {
        try {
            return Integer.parseInt(br.readLine());
        } catch (IOException e) {
            e.printStackTrace();
        }
        return -1;
    }

    public static int[] getInts() {

        String inLine = readLine();
        assert inLine != null;
        String deli = inLine.contains(COMMA) ? COMMA: SPACE;
        String[] line = inLine.split(deli);

        int[] ret = new int[line.length];
        IntStream.range(0, ret.length)
                .forEach( i -> ret[i] = Integer.parseInt(line[i]));

        return ret;
    }
}
