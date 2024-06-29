package misc;

public class LogBase2 {

    public static void main(String[] args) {
        logBase2(1024);
    }

    private static void logBase2(final int N) {

        //if you want to use Math lib
        //see we know  loga b = loge b / loge a
        //log2 N = loge N / loge 2
        int count=0;
        int n = N;
        while(n>>1 != 0) {
            count++;
            n>>=1;
        }
        System.out.println(count);
        System.out.println((Math.log(N)/Math.log(2)));
    }
}